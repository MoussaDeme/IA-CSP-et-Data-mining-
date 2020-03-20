/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import representations.Variable;

/**
 *
 * @author jordan
 */
public class FrequentItemSetMiner {
     
    private BooleanDatabase database;

    public FrequentItemSetMiner(BooleanDatabase database) {
        this.database = database;
    }
    
    /**
     * Retourne les itemsets dont la frequence depasse le seuil 
     * @param frequenceMin seuil de frequence 
     * @return Liste des itemSet Frequent
     */
    public Map<Set<Variable>, Integer> frequentItemsets(int frequenceMin){
        // liste des itemsetFrequent final
        Map<Set<Variable>, Integer> finalListOfItemset= new HashMap();
        // Liste des itemset A tester
        Deque<Set<Variable>> currentItemsetList= new LinkedList();
    //    Set<Itemset> listItemset=new HashSet();
        Set<Set<Variable>>  listeOfItemset= new HashSet();
        //frequence
        int frequency=0;
        //etage
        int stage=1;
        // taille de la size 
        int tmpSize=0;
        // on parcourt de la liste des variable de la base de donne
        for(Variable item:database.getListeDeVariable()){
            //item setTempon
            Set<Variable> itemTmp= new HashSet<Variable>();
            itemTmp.add(item);
            listeOfItemset.add(itemTmp);
        }
         
        do{
            currentItemsetList=new LinkedList();
            for(Set<Variable> itemset : listeOfItemset){
                frequency= getFrequenceInAllTransaction(itemset);
                System.out.println("OK"+frequency+"item"+itemset);
                if(frequency >= frequenceMin){
                     
                    currentItemsetList.push(itemset);
                   
                    finalListOfItemset.put(itemset, frequency);
                } 
            }
            if(currentItemsetList.size()>0){
                stage=stage+1;
//                System.out.println("je suis passé la");
                listeOfItemset=new HashSet(createNewListItem(stage, currentItemsetList));
//                System.out.println(listeOfItemset.size()+"rrrrrrrrrrrrrrr stage= "+stage);
            }else{
                listeOfItemset=new HashSet();
            }
        }while( listeOfItemset.size()>0);
        return  finalListOfItemset;
    }
    
    
    /**
     * Permet de creer une nouvelle list d'item;
     * @param stage etage sur le quel on se trouve
     * @param previousItemFrequent liste de itemset Precédent
     * @return un ensemble de itemset
     */
    public Set<Set<Variable>> createNewListItem(int stage, Deque<Set<Variable>> previousItemFrequent){
        
        // nouvelle liste d'itemset
        Set<Set<Variable>> listeVar=new HashSet();
        // itemsetCurrent 
        Set<Variable> current= new HashSet();
        // taille de la dernier liste d'itemset Frequent 
        int sizePreviousItemFrequent=previousItemFrequent.size();
        
        // tantque  liste des itemsets set précédent est 
        while(sizePreviousItemFrequent>0){
          
            current= previousItemFrequent.pop();
            sizePreviousItemFrequent--;
            //
            for(Set<Variable> item : previousItemFrequent){
                Set<Variable> nextItemset= new HashSet();
                
                nextItemset.addAll(current);
                nextItemset.addAll(item);
                if(nextItemset.size()==stage){
                    listeVar.add(nextItemset);
//                      System.out.println("je suis passé la bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                }
            }
//            System.out.println("OK");
        }
        return listeVar;
    }
    
    public int getFrequenceInAllTransaction(Set<Variable> itemset){
        int freq=0;
        boolean good;
       for(Map<Variable,String> transaction: database.getListeDeTransaction()){
           good=true;
           // si la transaction contient l'itemset alors
            if(transaction.keySet().containsAll(itemset)){
                // pour chaque variable de l'itemset
                for(Variable variable : itemset){
                    // si la variable n'a pas une valeur de 1 dans la transaction alors  met good à false
                   if(!transaction.get(variable).equals("1")){
                       good=false;
                   }  
                }
            }else{
                good=false;
            } 
            // si l'itemset respecte toutes les conditions de la transaction 
            //alors  on augmente la frequence 
            if(good){
                freq++;
            }
        }
        
       return freq;
    }
    
    
}