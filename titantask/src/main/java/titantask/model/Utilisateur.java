package titantask.model;


public class Utilisateur {

    public Utilisateur() {
		super();
	}

	private String nom;
    private String fonction;
    private String email;
    private String password;
    private int id ;
    private boolean role;

    public Utilisateur(String nom, String fonction, String email, String password, int id, boolean role) {
        this.nom = nom;
        this.fonction = fonction;
        this.email = email;
        this.password = password;
        this.id = id;
        this.role = role;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", fonction='" + fonction + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +

                '}';
    }
}