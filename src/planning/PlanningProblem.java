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
 * @author 
 */
public class PlanningProblem{
    
  
    private int sonde_dfs_dabord=0;
    
    private int sonde_dfs_iterative=0;
    
    private int sonde_bfs=0;
    
    
    protected State etatInitial;
    protected State etatfinal;
    protected Set<Action> listeAction;

    public PlanningProblem(State etatInitial, State etatfinal, Set<Action> listeAction) {
        this.etatInitial = etatInitial;
        this.etatfinal = etatfinal;
        this.listeAction = listeAction;
    }

    public State getEtatInitial() {
        return etatInitial;
    }

    public void setEtatInitial(State etatInitial) {
        this.etatInitial = etatInitial;
    }

    public State getEtatfinal() {
        return etatfinal;
    }

    public void setEtatfinal(State etatfinal) {
        this.etatfinal = etatfinal;
    }

    public Set<Action> getListeAction() {
        return listeAction;
    }

    public void setListeAction(Set<Action> listeAction) {
        this.listeAction = listeAction;
    }

    public int getSonde_dfs_dabord() {
        return sonde_dfs_dabord;
    }

    public void setSonde_dfs_dabord(int sonde_dfs_dabord) {
        this.sonde_dfs_dabord = sonde_dfs_dabord;
    }

    public int getSonde_dfs_iterative() {
        return sonde_dfs_iterative;
    }

    public void setSonde_dfs_iterative(int sonde_dfs_iterative) {
        this.sonde_dfs_iterative = sonde_dfs_iterative;
    }

    public int getSonde_bfs() {
        return sonde_bfs;
    }

    public void setSonde_bfs(int sonde_bfs) {
        this.sonde_bfs = sonde_bfs;
    }
    
    @Override
    public String toString(){
        String texte=" je suis un probleme voici:\n"
                + "mon etat initial:\n\t"+this.etatInitial.toString()
                + "mon etat final:\n\t"+this.etatfinal.toString()
                + "la liste de mes actions:\n";
                
        for(Action a: this.listeAction){
            texte+="\t"+a.getName()+"\t{"+a.getStringAction()+ "}\n";
        }
                
        return texte;
    }
    
    public Deque<Action> dfsAlgorihtm(State etat,Deque<Action> p, Set<State> closed){
       
        Deque<Action> plan= p;
        System.out.println(etat);
        if(etat.satisfies(this.etatfinal)){
            return plan;
        }else{
            for(Action action : this.listeAction){
                if(action.is_applicable(etat)){
                     
                    State next=action.apply(etat);
                     
                    if(!(closed.contains(next))){
                        this.sonde_dfs_dabord++;
                        plan.push(action);
                        closed.add(next);
                        Deque<Action> subPlan=(this.dfsAlgorihtm(next, plan, closed));
                        if(!(subPlan==null)){
                            return subPlan;
                        }else{
                            plan.pop();
                        }
                    }
                }
            }
            return plan;
        }
        
    }
    
//    public Deque<Action> dfs_iterative(State initial_state){                
//        for(int profondeur=1;profondeur<Double.POSITIVE_INFINITY; profondeur++){            
//            Deque<Action> resultat = dls(initial_state,new <Action>,new HashSet<State>(), profondeur);
//            if(!resultat.isEmpty()){                 
//                return resultat;
//            }
//        }
//        return null;
//    }   
    
    
    public Deque<Action> getBfsPlan(Map<State,State> father,  Map<State, Action> actions,State goal){
        Deque<Action> plan = new LinkedList();
        //action pour aller au but
        Action action = actions.get(goal);
        System.out.println("getBfsPlan"+ goal);
        System.out.println("\nbefore\n"+father+"\nafter\n");
        while(goal!=null && action!=null){
            plan.add(action);
            goal=father.get(goal);
            action=actions.get(goal);
            System.out.println("getBfsPlan"+ goal);
        }
        return plan;
    }
    public Deque<Action>  bfsAlgorihtm(){
      Deque<State> open = new LinkedList();
      Map<State, State> father= new HashMap();
      Map<State,Action> plan= new HashMap();
      Set<State> closed= new HashSet();
      State etati=new State(this.etatInitial);
      father.put(etati, null);
      open.add(this.etatInitial);
      while(open.size()!=0){
          
          State etat= new State(open.pop());
          closed.add(etat);
          for(Action action: this.getListeAction()){
              
              if(action.is_applicable(etat)){
                  State next= action.apply(etat);
                  
                  if(!closed.contains(next) && !open.contains(next)){
                      this.sonde_bfs++;
                      father.put(next, etat);
                      plan.put(next,action);

                      if(next.satisfies(this.etatfinal)){
                        return this.getBfsPlan(father, plan, next);
                      }else{
                         open.add(next);
                     }
                  }
              }

          }

      }
      return null;
    }
}