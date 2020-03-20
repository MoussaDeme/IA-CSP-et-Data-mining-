package ppc;

import java.util.*;

import representations.*;

/**
 * nous ressort l'ensemble des solutions des contraintes du csp
 * @author 
 */
public class Backtracking {

    private Set<Variable> scope;
    private Set<Constraint> contrainteSet;

    public Backtracking(Set<Constraint> contrainteSet) {

        this.contrainteSet = contrainteSet;
        this.scope = new HashSet<Variable>();
        for (Constraint cons : this.contrainteSet) {
            this.scope.addAll(cons.getScope());
        }
    }

    public List<Map<Variable, String>> allSolutions() {
        Map<Variable, String> partialAssignment = new HashMap<Variable, String>();
        Deque<Variable> unassignedVariables = new ArrayDeque<Variable>();
        List<Map<Variable, String>> res = new ArrayList<Map<Variable, String>>();
        for (Variable var : this.scope) {
            unassignedVariables.add(var);
        }
        this.solution(partialAssignment, unassignedVariables, res);

        return res;
    }

    protected void solution(Map<Variable, String> partialAssignment,
            Deque<Variable> unassignedVariables, List<Map<Variable, String>> accumulator) {
        boolean satisfaction = true;
        Map<Variable, String> copiepartialAssignment = null;
        if (unassignedVariables.isEmpty()) {

            accumulator.add(partialAssignment);
            //System.out.println(accumulator);

        } else {
            Variable variable = unassignedVariables.pop();
            for (String valeur : variable.getDomaine()) {
                partialAssignment.put(variable, valeur);
                copiepartialAssignment = new HashMap<>(partialAssignment);
                //System.out.println(partialAssignment.keySet());
                //if (partialAssignment.keySet().containsAll(this.scope) ) {
                satisfaction = true;
                for (Constraint contrainte : this.contrainteSet) {
                    if (contrainte.isSatisfiedBy(partialAssignment) == false) {
                        satisfaction = false;
                    }
                }
                if (satisfaction == true) {
                    solution(copiepartialAssignment, unassignedVariables, accumulator);
                }

                partialAssignment.remove(variable);
            }

            unassignedVariables.add(variable);
        }
    }
}


