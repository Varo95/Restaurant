package models;


import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    //Atributos de Persona
    private String dni;
    private String name;
    private int age;
    //Constructor
    public Person(String dni, String name, int age) {
        this.dni = dni;
        this.name = name;
        this.age = age;
    }
    //Getters y Setters
    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (this.getClass() == obj.getClass()) {
            Person other = (Person) obj;
            if (this.dni != null && dni.equals(other.getDni())) {
                result = true;
            } else {
                String n1 = other.dni.trim().toLowerCase();
                String n = dni.trim().toLowerCase();
                if (n.equals(n1)) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return  "<-Index---------------"+"\n"+
                "---Client Info---"+"\n"+
                "Dni= " + dni + "\n" +
                "Name= " + name + "\n" +
                "Age= " + age + "\n";
    }

}
