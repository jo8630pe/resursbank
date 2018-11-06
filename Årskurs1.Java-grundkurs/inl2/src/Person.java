public class Person {
	private String name; // personens namn
	private String date; // personens födelsedag
	/** Skapar en person med namnet name och födelsedag date */
	public Person(String name, String date) {
		this.name = name;
		this.date = date;
	}
	/** Returnerar namnet */
	public String getName() {
		return name;
	}
	/** Returnerar födelsedagen */
	public String getDate() {
		return date;
	}
	/** Returnerar en sträng som består av personens namn och
	födelsedag */
	public String toString() {
		return name + "\t" + date;
	}
}