package titantask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class  ManagmenetUtilisateurTest {

    private ManagmenetUtilisateur Mu; 


@Test
public void supUser(){
    Mu  = new ManagmenetUtilisateur();

    // Mu.supprimer(0);// return boolean value True 
    Assertions.assertEquals("True", Mu.supprimer(0)); 
}

}

