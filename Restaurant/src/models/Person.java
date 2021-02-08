package models;

public class Person {
	//Atributes of Person
	private String dni;
	private String name;
	private int age;
	
	//ToString
	@Override
	public String toString() {
		return "Person: Dni="+dni+"\n"+ 
				"Name="+name+"\n"+
				"age="+age+"\n";
	}
	
	
	
}
