package bst;

public class Person implements Comparable {
	private String name;
	private int id;
	
	public Person(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Person) {
			return id - ((Person) o).getId();
		}
		return compareTo(o);
	}
	
	
	@Override
	public String toString() {
		return name + " " + id;
	}
	
	
	

}
