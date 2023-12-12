package titantask;

import java.time.LocalDateTime;

public class Tache {
	private int id ;
	private String name ;
	private String description ;
	private LocalDateTime date_de_creation ;
	private LocalDateTime date_de_miseajour ;
	private String priorite ;
	private Categorie categorie;
	
	
	
	
	
	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tache(String name, String description, Date date_de_creation, Date date_de_miseajour, String priorite,
			Categorie categorie) {
		super();
		this.name = name;
		this.description = description;
		this.date_de_creation = date_de_creation;
		this.date_de_miseajour = date_de_miseajour;
		this.priorite = priorite;
		this.categorie = categorie;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate_de_creation() {
		return date_de_creation;
	}
	public void setDate_de_creation(Date date_de_creation) {
		this.date_de_creation = date_de_creation;
	}
	public Date getDate_de_miseajour() {
		return date_de_miseajour;
	}
	public void setDate_de_miseajour(Date date_de_miseajour) {
		this.date_de_miseajour = date_de_miseajour;
	}
	public String getPriorite() {
		return priorite;
	}
	public void setPriorite(String priorite) {
		this.priorite = priorite;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	

	@Override
	public String toString() {
		return "Tache [id=" + id + ", name=" + name + ", description=" + description + ", date_de_creation="
				+ date_de_creation + ", date_de_miseajour=" + date_de_miseajour + ", priorite=" + priorite
				+ ", categorie=" + categorie + "]";
	}
	
}
