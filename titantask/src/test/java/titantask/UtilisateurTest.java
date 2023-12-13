package titantask;

import  org.junit.jupiter.api.Assertions;
import  org.junit.jupiter.api.Test;
public class  UtilisateurTest{
    private Utilisateur utilisateur;

 
    @Test
    public void testConstructor() {
        utilisateur = new Utilisateur("adil", "Developer", "adil@gmail.com", "password123", 1);

        Assertions.assertEquals("adil", utilisateur.getNom());
        Assertions.assertEquals("Developer", utilisateur.getFonction());
        Assertions.assertEquals("adil@gmail.com", utilisateur.getEmail());
        Assertions.assertEquals("password123", utilisateur.getPassword());
        Assertions.assertEquals(1, utilisateur.getId());
    }

    @Test
    public void testGettersAndSetters() {
        utilisateur.setNom("hamza");
        utilisateur.setFonction("ChefCuisinier");
        utilisateur.setEmail("ch@example.com");
        utilisateur.setPassword("newpassword");
        utilisateur.setId(1);

        Assertions.assertEquals("hamza", utilisateur.getNom());
        Assertions.assertEquals("ChefCuisinier", utilisateur.getFonction());
        Assertions.assertEquals("ch@example.com", utilisateur.getEmail());
        Assertions.assertEquals("newpassword", utilisateur.getPassword());
        Assertions.assertEquals(2, utilisateur.getId());
    }

}