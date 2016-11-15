/**
 * 
 */
package com.levelsbeyond.comparator;

import java.util.Comparator;

import com.levelsbeyond.notes.json.Note;

/**
 * @author bill
 *
 */
public class NoteComparator implements Comparator<Note> {

	@Override
	public int compare(Note note1, Note note2) {
		return Long.valueOf(note1.getId()).compareTo(Long.valueOf(note2.getId()));
	}
}
