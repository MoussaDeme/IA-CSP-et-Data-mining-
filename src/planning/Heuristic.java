/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import representations.Variable;

/**
 *
 * @author 
 */
public class Heuristic {
    
    public int getValHeuristique(State state){
         int res=0;
        for(Variable var : state.getAffectation().keySet()){
           
            String domaine=state.getAffectation().get(var);
            switch(domaine){
                case "LOW":
                    res+=1;
                break;
                case "MEDIUM":
                    res+=2;
                break;
                case "HIGHT": 
                   res+=3; 
                break;
                default:
                    res+=0;
                break;
            }
            
           
        } 
        
        return res;
    }
    
}
