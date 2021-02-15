package models;


public class Person {
	//Atributes of Person
	private String dni;
	private String name;
	private int age;
	
	
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
		boolean result=false;
		if(this==obj) {
			result=true;
		}else if (this.getClass()==obj.getClass()) {
			Person other = (Person) obj;
			if (this.dni != null && dni.equals(other.getDni())) {
				result = true;
			}else {
				String n1=other.dni.trim().toLowerCase();
				String n=dni.trim().toLowerCase();
				if(n.equals(n1)) {
					result=true;
				}
			}
		}
		return result;
	}


	//ToString
	@Override
	public String toString() {
		return "Person: Dni="+dni+"\n"+ 
				"Name="+name+"\n"+
				"age="+age+"\n";
	}
	
	
	
}
