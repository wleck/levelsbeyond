
package com.levelsbeyond.notes.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Notes
 * <p>
 * A collections of notes
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Notes"
})
public class Notes implements Serializable
{

    @JsonProperty("Notes")
    private List<Note> notes = new ArrayList<Note>();
    private final static long serialVersionUID = -6608796223464188536L;

    /**
     * 
     * @return
     *     The notes
     */
    @JsonProperty("Notes")
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * 
     * @param notes
     *     The Notes
     */
    @JsonProperty("Notes")
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Notes withNotes(List<Note> notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(notes).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Notes) == false) {
            return false;
        }
        Notes rhs = ((Notes) other);
        return new EqualsBuilder().append(notes, rhs.notes).isEquals();
    }

}
