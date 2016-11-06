package com.salay.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by Radek Salay on 6.11.2016.
 */
@Component
public class Ticket {
    private int id;
    @NotBlank(message = "Fill your first name.")
    private String firstName;
    @NotBlank(message = "Fill your last name.")
    private String lastName;
    @NotBlank(message = "Fill your email name.")
    private String email;
    private Timestamp date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) return false;
        if (firstName != null ? !firstName.equals(ticket.firstName) : ticket.firstName != null) return false;
        if (lastName != null ? !lastName.equals(ticket.lastName) : ticket.lastName != null) return false;
        if (email != null ? !email.equals(ticket.email) : ticket.email != null) return false;
        if (date != null ? !date.equals(ticket.date) : ticket.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
    public String toString() {
        return "FirstName: " + firstName + "  LastName: " + lastName + "  Email: " + email;
    }

}
