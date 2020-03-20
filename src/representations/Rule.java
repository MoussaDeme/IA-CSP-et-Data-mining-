/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * c'est une regle disposant d'une premisse te d'une conclusion
 * @author deme 
 */
public class Rule implements Constraint {

    private Set<RestrictedDomain> premisse;
    private Set<RestrictedDomain> conclusion;

    public Rule(Set<RestrictedDomain> permisse, Set<RestrictedDomain> conclusion) {
        this.premisse = permisse;
        this.conclusion = conclusion;
    }

    public Rule(Set<RestrictedDomain> conclusion) {
        this.conclusion = conclusion;
    }

    Rule() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Set<RestrictedDomain> getPremisse() {
        return premisse;
    }

    public Set<RestrictedDomain> getConclusion() {
        return conclusion;
    }




    @Override
    public boolean isSatisfiedBy(Map<Variable, String> instance) {
        boolean verifiable = true, verifPrem = true, verifConc = false;
        Set<Variable> scope = new HashSet<>();
        scope = this.getScope();
        for (Variable scope1 : scope) {
            Variable v = scope1;
            if (!instance.containsKey(v)) {
                verifiable = false;
            }
        }
        if (verifiable == true) {
            for (RestrictedDomain prem : premisse) {
                Variable v = prem.getVariable();
                if (!prem.getSous_domaine().contains(instance.get(v))) {
                    verifPrem = false;
                }
                break;

            }
            if (verifPrem == true) {
                for (RestrictedDomain conc : conclusion) {
                    Variable v = conc.getVariable();
                    if (conc.getSous_domaine().contains(instance.get(v))) {
                        verifConc = true;
                        break;
                    }
                }
                if (verifConc == false) {
                    return false;
                }
                return true;
              }
            else
            {
               return true;
            }
        } else {
           return true;
        }
    }
    @Override
    public Set<Variable> getScope() {
        Set<Variable> scope = new HashSet<>();
        for(RestrictedDomain prem: this.premisse)
        {
          scope.add(prem.getVariable());
        }
        for(RestrictedDomain conc: this.conclusion)
        {
          scope.add(conc.getVariable());
        }
        return scope;
    }

    @Override
      public boolean filter(Map<Variable, String> assignationPartielle, Deque<Variable> varTmpNonAssigne){

          Map<Variable, String> copieAssignationPartielle =new HashMap(assignationPartielle);
          boolean  isFilter=false;
          for(Variable variable: varTmpNonAssigne){
              Set<String> nouveauDomaine=new HashSet();
             for(String domaine: variable.getDomaine()){
                copieAssignationPartielle.put(variable, domaine);
                if(!this.isSatisfiedBy(copieAssignationPartielle)){
                    isFilter=true;
                }else{
                   nouveauDomaine.add(domaine);
                }
             }
             copieAssignationPartielle.remove(variable);

//             if(nouveauDomaine.size()!=0){
//                variable.setDomaine(nouveauDomaine);
//            }

            if(this.getScope().size()== copieAssignationPartielle.size() ){
                if( nouveauDomaine.size()==0){
                    varTmpNonAssigne.pop();
                }else{
                     variable.setDomaine(nouveauDomaine);
                }
            }
          }

          return isFilter;
      }

}
