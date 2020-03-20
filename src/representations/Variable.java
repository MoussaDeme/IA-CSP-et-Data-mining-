
package representations;

import static java.lang.Boolean.FALSE;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author
 * cette classe represente une variable avec son nom et son domaine
 */
public class Variable {
    
    private String nom;
    
    private Set<String> domaine;
    /**
     * 
     * @param nom nom de variable
     * @param valeurs valeurs tableau pour remplir l'ensemble de domaine 
     */
    public Variable(String nom, String... valeurs) {
        this.nom = nom;
        this.domaine = new HashSet<String>();
        for(String val: valeurs)
        {
           this.domaine.add(val);
        }       
    }
    /**
     * un second constructeur qui utilise l'ensemble de domaine directement
     * @param nom nom de domaine
     * @param domaine domaine de la variable
     */
    public Variable(String nom, Set<String> domaine) {
        this.nom = nom;
        this.domaine = domaine;
    }

    public String getNom() {
        return nom;
    }

    public Set<String> getDomaine() {
        return domaine;
    }

    public void setDomaine(String... valeurs) {
        for(String val: valeurs)
        {
           this.domaine.add(val);
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDomaine(Set<String> domaine) {
        this.domaine = domaine;
    }
    
    /**
     * Hash
     * @return the hashcode
     */
    @Override
    public int hashCode(){
      int result = 17;
      result = 31 * this.nom.hashCode();
      return result;
    }
    
    /**
     * The equals test
     * @param o the other object of the comparison
     * @return true if the variable and the object have the same name
     */
    @Override
    public boolean equals(Object o) {
        if(this==o) {
          return true;
        } else {
            if(o instanceof Variable) {
                return this.nom.equals(((Variable) o).getNom());
            } else {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
