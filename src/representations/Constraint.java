/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations;

import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author
 * c'est une interface qui represente une contrainte
 */
public interface Constraint {
    /**
     * il nous retourne la liste de toute les variables
     * @return Set<Variable>
     */
    public Set<Variable> getScope();

    /**
     * nous permet de voir si la regle est satisfaite
     * @param VariableInstance
     * @return boolean
     */
    public boolean isSatisfiedBy(Map<Variable,String> VariableInstance);

    /**
     * sert a reduire le domaine des variables
     * @param assignationPartielle une liste des varibles non assignee completement
     * @param varTmpNonAssigne
     * @return
     */
    public boolean filter(Map<Variable, String> assignationPartielle, Deque<Variable> varTmpNonAssigne);
}
