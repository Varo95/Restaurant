package com.restaurant.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    @Id
    private String dni;
    private String name;
    private int age;

    public Person(final String dni, final String name, final int age) {
        this.dni = dni;
        this.name = name;
        this.age = age;
    }

    public Person() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(dni, person.dni);
    }

    @Override
    public int hashCode() {
        int result = dni != null ? dni.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return  "Dni = " + dni + "\n" +
                "Name = " + name + "\n" +
                "Age = " + age + "\n";
    }

}
