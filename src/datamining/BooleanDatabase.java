    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.util.Map;
import java.util.Set;
import representations.Variable;

/**
 *
 * @author jordan
 */
public class BooleanDatabase {
    private Set<Variable> listeDeVariable;
    private Set<Map<Variable,String>>listeDeTransaction;

    public BooleanDatabase(Set<Variable>  listeDeVariable, Set<Map<Variable,String> > listeDeTransaction) {
        this.listeDeVariable = listeDeVariable;
        this.listeDeTransaction = listeDeTransaction;
    }
    
    public BooleanDatabase(BooleanDatabase bd) {
        this.listeDeVariable = bd.listeDeVariable;
        this.listeDeTransaction = bd.listeDeTransaction;
    }

    public Set<Variable>  getListeDeVariable() {
        return listeDeVariable;
    }

    public void setListeDeVariable(Set<Variable>  listeDeVariable) {
        this.listeDeVariable = listeDeVariable;
    }

    public Set<Map<Variable,String> > getListeDeTransaction() {
        return listeDeTransaction;
    }

    public void setListeDeTransaction(Set<Map<Variable,String> > listeDeTransaction) {
        this.listeDeTransaction = listeDeTransaction;
    }

}
