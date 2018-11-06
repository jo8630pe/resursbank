public class Person {
	private String name; // personens namn
	private String date; // personens f�delsedag
	/** Skapar en person med namnet name och f�delsedag date */
	public Person(String name, String date) {
		this.name = name;
		this.date = date;
	}
	/** Returnerar namnet */
	public String getName() {
		return name;
	}
	/** Returnerar f�delsedagen */
	public String getDate() {
		return date;
	}
	/** Returnerar en str�ng som best�r av personens namn och
	f�delsedag */
	public String toString() {
		return name + "\t" + date;
	}
}