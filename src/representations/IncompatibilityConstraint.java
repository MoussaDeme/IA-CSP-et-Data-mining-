/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  Permet de manipuler les regles avec seulement la premisse
 * @author deme
 */
public class IncompatibilityConstraint extends Rule {

    public IncompatibilityConstraint(Set<RestrictedDomain> premisse) {
        super(premisse);
    }
    @Override
    public Set<Variable> getScope() {
        Set<Variable> scope = new HashSet<>();
        for (RestrictedDomain conc : this.getPremisse()) {
            scope.add(conc.getVariable());
        }
        return scope;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> instance) {
        boolean verifiable = true,verifPrem=true;
        Set<Variable> scope = new HashSet<>();
        scope = this.getScope();
        for (Variable scope1 : scope) {
            Variable v = scope1;
            if (!instance.containsKey(v)) {
                verifiable = false;
            }
        }
        if (verifiable == true) {
            for (RestrictedDomain prem : this.getPremisse()) {
                Variable v = prem.getVariable();
                if (!prem.getSous_domaine().contains(instance.get(v))) {
                   verifPrem = false;
                    break;
                }
            }
           if(verifPrem==false)
           {
              return  false;
           }
        }
        return true;
    }
     
}
