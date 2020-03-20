/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jordan
 */
public class PlanningProblemWithCost extends PlanningProblem {
    //cette sonde renvoie 364 noeuds en moyenne avec un domaine de deux couleurs
    private int sonde_disjktra=0;
    //cette sonde renvoie 67 noeuds en moyenne avec un domaine de deux couleurs
    private int sonde_ASTAR=0;
    public PlanningProblemWithCost(State etatInitial, State etatfinal, Set<Action> listeAction) {
        super(etatInitial, etatfinal, listeAction);
    }
  
    public Deque<Action> Disjktra(){
        Map<State,Integer> distance=new HashMap();
        Map<State,State> father= new HashMap();
        Map<State,Action> plan= new HashMap();
        Set<State> goals= new HashSet();
        Deque<State> open =new LinkedList();

        open.push(this.etatfinal);
        distance.put(etatfinal, 0);
        father.put(etatfinal, null);
       
        while( open!=null){
           State etat= new State(); //Argmin à trouver

           if(etat.satisfies(this.etatInitial)){
               goals.add(etat);
           }
           
           for(Action action: this.listeAction){
               if(action.is_applicable(etat)){
                   State next=new State();
                   next=action.apply(etat);
                   if(!distance.containsKey(next)){
                      distance.put(next, Integer.MAX_VALUE);
                   }
                   int distanceMin=distance.get(etat)+ action.getCostAction();
                   if(distance.get(next)> distanceMin){
                      distance.replace(next,distanceMin);
//                     if(father.containsKey(next)){
//                          father.replace(next, etat);
//                     }else{
                          father.put(next, etat);
//                     }
//                     if(plan.containsKey(next)){
//                          plan.replace(next, action);
//                     }else{
                          plan.put(next, action);
//                     }
                     open.add(next);
                   }
               }
           }
        }


        return getDijkstraPlan(father,plan,goals,distance);
    }

    public Deque<Action> getDijkstraPlan(Map<State,State> father,Map<State,Action> actions,Set<State> goals,Map<State,Integer> distance){ 

        Deque<Action> plan=new LinkedList();
        State goal=null;
        while(goal!=null){
            plan.add(actions.get(goal));
            goal= father.get(goal);
        }

        return plan;
    }
    
    
    public Deque<Action>  Astart(){
        Map<State,Integer> distance=new HashMap();
        Map<State,State> father= new HashMap();
        Map<State,Action> plan= new HashMap();
        Set<State> goals= new HashSet();
        Deque<State> open =new LinkedList();
        Map<State, Integer> value=new HashMap();
        
        
        open.add(this.etatInitial);
        father.put(this.etatInitial, null);
        distance.put(this.etatInitial, null);
        value.put(this.etatInitial,2);//heristic(problem.initial_state);
         State next;
        while(open.isEmpty()){
            State state = new State();
            
            if(state.satisfies(this.etatfinal)){
                // à controller
                return (Deque<Action>) this.getBfsPlan(father, plan, state);
            }else{
                open.remove(state);
                for(Action action : this.getListeAction()){
                    if(action.is_applicable(state)){
                       next= action.apply(state);
                       if(!distance.keySet().contains(next)){
                           distance.put(next, Integer.MAX_VALUE);
                       }
                       // a changer le cout de l'action mettre getCost;
                       if( distance.get(next)> distance.get(state)+ action.getCostAction()){
                           distance.replace(next, distance.get(state)+ action.getCostAction());
                           value.put(next, distance.get(next)+ 2);// heuristique(next));
                           father.put(next, state);
                           plan.put(next,action);
                           open.add(next);
                       }
                    }
                }
            }
            
            
        }
        
        return null;
    }
    
    
    
    
}
