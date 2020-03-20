/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.Map;
import java.util.Set;
import representations.RestrictedDomain;
import representations.Variable;

/**
 *
 * @author
 */
public class Action {
    private String name;
    private Set<ActionRule> rules;

    public Action(String name, Set<ActionRule> rules) {
        this.name = name;
        this.rules = rules;
    }
    public Action(Set<ActionRule> rules) {
        this.rules = rules;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<ActionRule> getRules() {
        return rules;
    }

    public void setRules(Set<ActionRule> rules) {
        this.rules = rules;
    }

    // meme valeur dans l'état et dans l'état partiel
    /**
     * applicable  pour au moins 1 une affectation
     * @param etat
     * @return
     */
    public boolean is_applicable(State etat){
        for(ActionRule rule: this.rules){
            if(etat.satisfies(rule.getPrecondition())){
                return true;
            }
        }
        return false;
    }
    /**
     * Permet d'appliquer une action sur un etat afin de changer sa valeur
     * @param state etat sur lequel on veut agir
     * @return retourn l'etat ayant subit des changement;
     */
    public State apply(State state){
        State currentState= new State(state);
        for(ActionRule rule : this.rules){

        }
        if(this.is_applicable(currentState)){
            for(ActionRule rule : this.rules){
                if(currentState.satisfies(rule.getPrecondition())){  
                    Map<Variable,String> affectation = rule.getEffets().getAffectation();
                    for(Variable effet: affectation.keySet()){
                       Object val=currentState.getAffectation().replace(effet, affectation.get(effet));
                    }
                }
            }
        }
        return currentState;
    }
    public String getStringAction(){
        String text="";

        for(ActionRule r : this.getRules()){
            text+="\n\tprecondition: "+r.getPrecondition().stateString()+"  effets : "+ r.getEffets().stateString()+"\n\n";
        }
        return text;
    }

    Integer getCostAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }






}
