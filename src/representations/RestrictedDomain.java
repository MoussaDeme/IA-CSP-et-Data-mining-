/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations;

import java.util.HashSet;
import java.util.Set;

/**
 *represente une variable et son sous domaine
 * @author deme
 */
public class RestrictedDomain {
    private Variable variable;
    private Set<String> sous_domaine;

    public RestrictedDomain(Variable variable, String... valeurs) {
        this.variable = variable; 
        this.sous_domaine = new HashSet<String>();
        for(String val: valeurs)
        {
           this.sous_domaine.add(val);
        }  
    }

    
    public Variable getVariable() {
        return variable;
    }

    public Set<String> getSous_domaine() {
        return sous_domaine;
    }
    
    
}
