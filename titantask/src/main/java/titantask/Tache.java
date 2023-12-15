package titantask;

import java.time.LocalDateTime;

public class Tache {
	private int id ;
	private String name ;
	private String description ;
	private LocalDateTime date_de_creation ;
	private LocalDateTime date_de_miseajour ;
	private Priorite priorite ;
	private String categorie;
	private int id_utilisateur;
	
	
	
	
	
	
	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tache(String name, String description, LocalDateTime date_de_creation, LocalDateTime date_de_miseajour, Priorite priorite,
			String categorie, int idU) {
		super();
		this.name = name;
		this.description = description;
		this.date_de_creation = date_de_creation;
		this.date_de_miseajour = date_de_miseajour;
		this.priorite = priorite;
		this.categorie = categorie;	
		this.id_utilisateur = idU;

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
	public LocalDateTime getDate_de_creation() {
		return date_de_creation;
	}
	public void setDate_de_creation(LocalDateTime date_de_creation) {
		this.date_de_creation = date_de_creation;
	}
	public LocalDateTime getDate_de_miseajour() {
		return date_de_miseajour;
	}
	public void setDate_de_miseajour(LocalDateTime date_de_miseajour) {
		this.date_de_miseajour = date_de_miseajour;
	}
	public Priorite getPriorite() {
		return priorite;
	}
	public void setPriorite(Priorite priorite) {
		this.priorite = priorite;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	

	@Override
	public String toString() {
		return "Tache [ name=" + name + ", description=" + description + ", date_de_creation="
				+ date_de_creation + ", date_de_miseajour=" + date_de_miseajour + ", priorite=" + priorite
				+ ", categorie=" + categorie + "]";
	}
	
}
