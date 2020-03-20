/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

/**
 *
 * @author jordan
 */
public class ActionRule {
    private State precondition;
    private State effets;

    public ActionRule(State precondition, State effets) {
        this.precondition = precondition;
        this.effets = effets;
    }

    public State getPrecondition() {
        return precondition;
    }

    public void setPrecondition(State precondition) {
        this.precondition = precondition;
    }

    public State getEffets() {
        return effets;
    }

    public void setEffets(State effets) {
        this.effets = effets;
    }
    @Override
    public String toString(){
        String texte="";
        texte+="{\ntprecodition"+ this.precondition.toString();
        texte+="effet"+ this.effets.toString();
        texte+="\n}";


        return texte;
    }
}
