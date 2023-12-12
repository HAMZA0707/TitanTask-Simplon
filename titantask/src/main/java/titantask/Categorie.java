package titantask;

public class Categorie {	
	private String name;
	
		
	
	@Override
	public String toString() {
		return "Categorie [name=" + name + "]";
	}


	public Categorie(String name) {
		super();
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
