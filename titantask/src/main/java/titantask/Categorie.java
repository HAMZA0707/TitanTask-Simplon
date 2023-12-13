package titantask;

public class Categorie {
	private int id;
	private String name;
	
		
	
	public Categorie(String name) {
		super();
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", name=" + name + "]";
	}
	
	
}
