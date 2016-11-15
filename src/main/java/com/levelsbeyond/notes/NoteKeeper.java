/**
 * 
 */
package com.levelsbeyond.notes;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.levelsbeyond.comparator.NoteComparator;
import com.levelsbeyond.notes.json.Note;
import com.levelsbeyond.notes.json.Notes;

/**
 * @author bill
 *
 */

@Path("/")
@Produces("application/json")
public class NoteKeeper {
	
	private Notes notes = new Notes();
	
	@GET
	@Path("/notes")
	public Notes getNotes(@QueryParam("query") String query) {

		if ( query != null && !query.isEmpty() ) {
			List<Note>noteList = notes.getNotes().stream()
					.filter(note -> note.getBody().contains(query))
					.collect(Collectors.toList());
			return new Notes().withNotes(noteList);
		}

		return notes;
	}
	
	@GET
	@Path("/notes/{id}")
	public Note getNote(@PathParam("id") Long id) {
		return getANote(id);
	}
	
	@PUT
	@Path("/notes/{id}")
	@Consumes("application/json")
	public Response updateNote(@PathParam("id") Long id, Note note) {
		Status status = Status.OK;
		
		List<Note> noteList = notes.getNotes();
		try {
			noteList.remove(getIndex(id));
			note.setId(id);
			noteList.add(note);
			noteList.sort(new NoteComparator());
		} catch ( IndexOutOfBoundsException e ) {
			status = Status.NOT_FOUND;
		}
		return Response.status(status).entity(note).build();
	}
	
	@POST
	@Path("/notes")
	@Consumes("application/json")
	public Response addNote(Note note) {
		List<Note>noteList = notes.getNotes();
		int id = noteList.size() + 1;
		note.setId(id);
		noteList.add(note);
		return Response.ok().entity(note).type(MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("/notes/{id}")
	public Response deleteNote(@PathParam("id") Long id) {
		List<Note>noteList = notes.getNotes();
		try {
			noteList.remove(getIndex(id));
			noteList.sort(new NoteComparator());
			noteList = noteList.stream().map(note -> {
					if ( note.getId() >= id ) note.setId(note.getId() - 1);
					return note;
				})
			.collect(Collectors.toList());
		} catch ( IndexOutOfBoundsException e ) {
			//NO-OP;
		}
		return Response.ok().entity(id).build();
	}

	private int getIndex(Long id) {
		return id.intValue() - 1;
	}
	private Note getANote(Long id) {
		return notes.getNotes().get(getIndex(id));
	}
}
