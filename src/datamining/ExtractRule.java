package datamining;


import java.util.HashSet;
import java.util.Set;
import representations.Variable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Permet de definir une regle extraite
 * @author
 */
public class ExtractRule {
    private Set<Variable> primice;
    private Set<Variable> conclusion;
    private double confiance;
    private int frequence;
   
    /**
     * Creer une regle à extraire
     * @param primice primice de la regle
     * @param conclusion conclusion de la regle
     */
    public ExtractRule(Set<Variable> primice, Set<Variable> conclusion) {
        this.primice = primice;
        this.conclusion = conclusion;
    }

    public Set<Variable> getPrimice() {
        return primice;
    }

    public void setPrimice(Set<Variable> primice) {
        this.primice = primice;
    }

    public Set<Variable> getConclusion() {
        return conclusion;
    }

    public void setConclusion(Set<Variable> conclusion) {
        this.conclusion = conclusion;
    }
    /**
     * Recuperer l'ensemble des variables qui constitue notre regle
     * @return 
     */
    public Set<Variable> getScope(){
        Set<Variable> allScope =new HashSet();
        allScope.addAll(this.primice);
        allScope.addAll(this.conclusion);
        return allScope;
    }

    public double getConfiance() {
        return confiance;
    }

    public void setConfiance(double confiance) {
        this.confiance = confiance;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
    
    
    
    @Override
    public String toString(){
        String p="";
        String q="";
        p= this.primice.toString();
        q=this.conclusion.toString();
        return "\nRegle : "+p+" -------> "+ q +"\n\n Frenquence= "+this.frequence + "confiance= "+ this.confiance;
    }
}
