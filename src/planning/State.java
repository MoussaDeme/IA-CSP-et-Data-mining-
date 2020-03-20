/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import representations.Variable;

/**
 *
 * @author
 */
public class State{

    private Map<Variable,String> affectation ;

    public State(State state ) {
        this.affectation  = new HashMap(state.getAffectation()) ;
    }
    public State(Map<Variable,String> affectation ) {
        this.affectation  = new HashMap(affectation);
    }

    public State() {
       affectation= new HashMap();
    }

    public Map<Variable,String>getAffectation () {
        return affectation ;
    }

    public void setAffectation (Map<Variable,String>affectation ) {
        this.affectation  = new HashMap(affectation) ;
    }
    /**
     * Permet de verifier si l'etat satisfait satisfait une la precondition d'une action
     * @param partialState etat partielle correspondant à la precondition d'une action
     * @return renvoie si oui ou non l'etat satisfait la précondition
     */
    public boolean satisfies(State partialState){

        for(Variable variable:partialState.getAffectation().keySet()){

            if(!(this.affectation.keySet().contains(variable)) ||
                (partialState.getAffectation().get(variable).equalsIgnoreCase(this.getAffectation().get(variable)))){

                return false;
            }

        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.getAffectation().hashCode();
    }

     @Override
    public boolean equals(Object o) {
        if(this==o) {
          return true;
        } else {
            if(o instanceof State) {
                // return the test of equality between their variable
                return this.getAffectation().equals(((State) o).getAffectation());
            } else {
                return false;
            }
        }
    }
    @Override
    public String toString(){
        String text="Je suis un état mes affectation sont:\n";
        for(Variable v:this.affectation.keySet()){
            text+=v.getNom()+" --- "+this.affectation.get(v)+"\n";
        }

        return text;
    }

    public String stateString(){
        String text="{";
        for(Variable v:this.affectation.keySet()){
            text+=v.getNom()+"="+ this.affectation.get(v)+",";
        }

        return text+"}";
    }



}
