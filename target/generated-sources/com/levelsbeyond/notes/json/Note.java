
package com.levelsbeyond.notes.json;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Note
 * <p>
 * An indexed note
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "body"
})
public class Note implements Serializable
{

    @JsonProperty("id")
    private long id;
    @JsonProperty("body")
    private String body;
    private final static long serialVersionUID = -6539380400185104987L;

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    public Note withId(long id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The body
     */
    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    /**
     * 
     * @param body
     *     The body
     */
    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    public Note withBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(body).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Note) == false) {
            return false;
        }
        Note rhs = ((Note) other);
        return new EqualsBuilder().append(id, rhs.id).append(body, rhs.body).isEquals();
    }

}
