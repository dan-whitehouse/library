package org.ricone.library.client.oneroster.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonRootName("users")
@JacksonXmlRootElement(localName = "users")
public class Users extends ResponseModel implements Serializable {
    @JsonProperty("users")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "user")
    private List<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    public Users(List<User> users) {
        this.users = users;
    }

    @JsonProperty("users")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "user")
    public List<User> getUsers() {
        return this.users;
    }

    @JsonProperty("users")
    @JacksonXmlElementWrapper(useWrapping = false) @JacksonXmlProperty(localName = "user")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean isEmpty() {
        return users.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(this.users, users.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}