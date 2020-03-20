/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *    Cette classe represente un disjonction represente une regle avec la conclusion
 *
 * @author 21912873
 */
public class Disjonction extends Rule {

    /**
     *
     * @param conclusion
     */
    public Disjonction(Set<RestrictedDomain> conclusion) {
        super(conclusion);
    }

    @Override
    public Set<Variable> getScope() {
        Set<Variable> scope = new HashSet<>();
        for (RestrictedDomain conc : this.getConclusion()) {
            scope.add(conc.getVariable());
        }
        System.out.println("scope"+scope);//to drop
        return scope;

    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> instance) {
        boolean verifiable = true,verifRest=false;
        Set<Variable> scope = new HashSet<>();
        scope = this.getScope();
        for (Variable scope1 : scope) {
            Variable v = scope1;
            if (!instance.containsKey(v)) {
                verifiable = false;
                break;
            }
        }
        if (verifiable == true) {
            for (RestrictedDomain conc : this.getConclusion()) {
                Variable v = conc.getVariable();
                if (conc.getSous_domaine().contains(instance.get(v))) {
                   verifRest = true;
                    break;
                }
            }
           if(verifRest==false)
           {
              return  false;
           }
        }
        return true;
    }


}
