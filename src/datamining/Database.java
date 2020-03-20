/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import representations.Variable;

/**
 *
 * @author jordan
 */
public class Database {
    
    private List<Variable> listeDeVariable;
    private List<Map<Variable,String>>listeDeTransaction;

    public Database(List<Variable> listeDeVariable, List<Map<Variable, String>> listeDeTransaction) {
        this.listeDeVariable = listeDeVariable;
        this.listeDeTransaction = listeDeTransaction;
    }
    
    /**
     * Permet de rendre une base de donnée non booleenne en booleene
     * @return  une base de donné booleenne
     */
    public BooleanDatabase makeBooleanDataBase(){
        // liste de toutes les variables initialement vide
        Set<Variable> finalListOfVariable=new HashSet();
        // liste des transaction initialement vide
        Set<Map<Variable, String>> finalListOfTransaction=new HashSet();
        // Domaine booleen
        Set<String> booleanDomain= new HashSet<>(Arrays.asList("1","0"));
        // on parcoure toute les transaction 
        for(Map<Variable,String> transaction:  listeDeTransaction){
             Map<Variable,String> currentTransaction=new HashMap();
            for(Variable var : this.listeDeVariable){
                // si la variable n'est pas booleenne
                 if(var.getDomaine().size()!=booleanDomain.size() &&
                    !(var.getDomaine().equals(booleanDomain))){
                     // on parcour son domaine afin de creer une variable booleenne
                    for(String dom: var.getDomaine() ){
                        String sousDomain;
                        
                        // si le domaine est egale à ce qui ce trouve dans transaction,
                        //le sous domaine de la variable booleen est 1 sinon on l'ignore;
                        if(dom.equals(transaction.get(var))){
                            sousDomain="1";
                            String variableName=var.getNom()+"_"+dom;
                            Variable currentVar=createBooleanVariable(variableName);
                            currentTransaction.put(currentVar, sousDomain); 
                            //on stocke la nouvelle variable dans la liste des variable final
                            if(!finalListOfVariable.contains(currentVar)){
                                finalListOfVariable.add(currentVar);
                            }
                            break;
                        }
                        
                    }
                }else{
                     //si la liste des variable ne contient pas encore la variable on l'ajoute
                     if(!finalListOfVariable.contains(var)){
                        finalListOfVariable.add(var);
                     }
                     // on ajoute la nouvelle transaction
                    currentTransaction.put(var, transaction.get(var));
                }
               
                 
            }
            // on ajoute la transaction à la liste des transactions
            finalListOfTransaction.add(currentTransaction);
        }
        return new BooleanDatabase(finalListOfVariable, finalListOfTransaction);
    }
    /**
     * Permet de creer une variable booleen
     * @param name nom de la variable à creer
     * @return une variable booleen
     */
    public Variable createBooleanVariable(String name){
        Set<String> booleanDomain= new HashSet<>(Arrays.asList("1","0"));
        return new Variable(name,booleanDomain);
    }  
    
    
}
