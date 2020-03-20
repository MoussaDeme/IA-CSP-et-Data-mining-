/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import representations.RestrictedDomain;
import representations.Rule;
import representations.Variable;

/**
 *
 * @author jordan
 */
public class AssociationRuleMiner {
    private Map<Set<Variable>, Integer> itemsets;
    /**
     * Permet de creer  construteur de regle;
     * Prend en  une liste de d'itemset Frequent 
     * @param itemsets 
     */
    public AssociationRuleMiner(Map<Set<Variable>, Integer> itemsets) {
        this.itemsets = itemsets;
    }

    public Map<Set<Variable>, Integer> getItemsets() {
        return itemsets;
    }
    
    /**
     * Renvoie tout les regle generer à partie d'un item
     * @param item
     * @return Set<ExtractRule> ensemble de regle generer
     */
    public Set<ExtractRule> getAllRuleOfItem(Set<Variable> item){
        Set<ExtractRule> rule= new HashSet();
         // on parcourt la liste de tous les itemsets Frequent;
        for(Set<Variable> currentItem : this.itemsets.keySet()){
           // si la taille de mon item est superieur à celle du current  
           // et que mon item contient tout mon current item
            if(currentItem.size()<item.size() && item.containsAll(currentItem)){
                // comme premice on passe currentItem
                Set<Variable> p =new HashSet(currentItem);
                // comme conclusion on passe Item
                Set<Variable> q = new HashSet(item);
                //on retire à la conclusion les valeur qui son presente 
                // à la primice
                for(Variable variable: p){
                    if(q.contains(variable)){
                        q.remove(variable);
                    }
                }
                //si premisse different de conclusion 
                if(!p.equals(q)){
                    // on creer une nouvelle regle et on l'ajoute à l'ensemble de regle
                    rule.add(new ExtractRule(p,q));
                 
                }
                
            }
        }
        return rule;
    }
    
    /**
     * Get all the rule of that itemsets can form
     * @param minFreq minimal frequency of rule
     * @param minConf minimal confiency of rule
     * @return list of rule 
     */
    public Set<ExtractRule> generateAllRule(int minFreq, double minConf){
        //liste des regle potentielle
        Set<ExtractRule>listOfPossibleRule =new HashSet();
        // liste des qui respecte le seuil de frequence et de confiance
        Set<ExtractRule>finalRule =new HashSet();
        // Pour tout les items frequent on ajouute l'ensemble de regle qui en découle
        for(Set<Variable> item:itemsets.keySet() ){
            listOfPossibleRule.addAll(getAllRuleOfItem(item));
        }
        
        //pour chaque regle 
        for(ExtractRule r: listOfPossibleRule){
            // on calcule la confiance
            r.setConfiance(this.getConfiance(r));
            // on calcul la frequence
            r.setFrequence(this.getFrequency(r));
           // on verifie si la confiance et la frequence est superieur ou egale au seuil
            if(r.getConfiance()>= minConf && r.getFrequence()>= minFreq){
                // si oui on ajoute la regle à la variable
                finalRule.add(r);
//                System.exit(0);
            }

        }
      
        return finalRule; 
    }
     
    /**
     * Look if 2 motif have the same frequency
     * @param motif1 
     * @param motif2
     * @return boolean value;
     */
    public boolean haveSameFrequency(Set<Variable> motif1, Set<Variable> motif2){
        if(this.itemsets.get(motif1)== this.itemsets.get(motif2)){
            return true;
        }
        return false;
    }
    
    /**
     * Get the confiance of the Rule 
     * @param r is the Rule which we want to get Confiance
     * @return interger
     */
    public double getConfiance(ExtractRule r){
        //on recuperer la frequence de la premisse
        double  freqP= (double)this.itemsets.get(r.getPrimice());
        // On recupere la frequence de l'item
        double freqItem= (double)this.itemsets.get(r.getScope());
        // on calcul la confiance
        double conf=(freqItem/freqP)*100.0;

        return conf;
    }
    
    /**
     * Get the Frequency of the Rule 
     * @param r is the Rule which we want to get Frequency
     * @return interger
     */
    public int getFrequency(ExtractRule r){
        return this.itemsets.get(r.getScope());
    }
}
