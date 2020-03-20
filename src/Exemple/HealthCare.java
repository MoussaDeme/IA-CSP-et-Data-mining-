/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import planning.Action;
import planning.ActionRule;
import planning.PlanningProblem;
import planning.State;
import representations.Variable;

/**
 *
 * @author jordan
 */
public class HealthCare {
    private  int nbMedocAleatoire;
    
    /**
     * Cree une maladie
     * @param name correspond au nom de la maladie
     * @return retourne une variable qui represente la maladie et son domaine
     */
    public static Variable createDisease(String name){
        Set<String> domaine= new HashSet();
        domaine.add("TRUE");
        domaine.add("FALSE");
        return new Variable(name,domaine);
    }
    public static Variable createAngina(){
        Set<String> domaine= new HashSet();
        domaine.add("TRUE");
        domaine.add("FALSE");
        return new Variable("ANGINA",domaine);
    }
    
    public static Variable createPox(){
        Set<String> domaine= new HashSet();
        domaine.add("TRUE");
        domaine.add("FALSE");
        return new Variable("POX",domaine);
    }
    public static Variable createFlu(){
        Set<String> domaine=  new HashSet();
        domaine.add("TRUE");
        domaine.add("FALSE");
        return new Variable("FLU",domaine);
    }
    
    public static Variable createPlague(){
        Set<String> domaine= new HashSet();
        domaine.add("TRUE");
        domaine.add("FALSE");
        return new Variable("PLAGUE", domaine);
    }
    //frequency variable
    /**
     * Celle-ci  permet de representer les symptomes d'une maladie;
     * @param name C'est le nom du Symptoms
     * @return retourne une Variable representant un symbole et son domaine
     */
    public static Variable createFrequencyDisease(String name){
        Set<String> domaine =new HashSet();
        domaine.add("LOW");
        domaine.add("MEDIUM");
        domaine.add("HIGHT");
        domaine.add("NONE");
       return new Variable(name,domaine);
    }
    public static Variable createFever(){
        Set<String> domaine =new HashSet();
        domaine.add("LOW");
        domaine.add("MEDIUM");
        domaine.add("HIGHT");
        domaine.add("NONE");
       return new Variable("FEVER",domaine);
    }
    public static Variable createCough(){
        Set<String> domaine =new HashSet();
        domaine.add("LOW");
        domaine.add("MEDIUM");
        domaine.add("HIGHT");
        domaine.add("NONE");
       return new Variable("COUGH",domaine);
    }
    public static Variable createButtons(){
        Set<String> domaine =new HashSet();
        domaine.add("LOW");
        domaine.add("MEDIUM");
        domaine.add("HIGHT");
        domaine.add("NONE");
       return new Variable("BUTTONS",domaine);
    }
     
   // creation d'action ;
    public static Action createSyrupButtonHigh(){
        State precondition=new State();
        State effets= new State();
        Set<ActionRule> rules=new HashSet();
        precondition.getAffectation().put(createButtons(), "HIGHT");
        effets.getAffectation().put(createButtons(), "MEDIUM");
        rules.add(new ActionRule(precondition,effets));
        return new Action("SYRUP_BUTTONS_HIGH",rules); 
    }
    
    public static Action createSyrupFeverHigh(){
        State precondition=new State();
        State effets= new State();
        Set<ActionRule> rules=new HashSet();
        precondition.getAffectation().put(createButtons(), "HIGHT");
        effets.getAffectation().put(createButtons(), "MEDIUM");
        rules.add(new ActionRule(precondition,effets));
        return new Action("SYRUP_FEVER_HIGH",rules); 
    }
    public static Action createSyrupCoughHigh(){
        State precondition=new State();
        State effets= new State();
        Set<ActionRule> rules=new HashSet();
        precondition.getAffectation().put(createButtons(), "HIGHT");
        effets.getAffectation().put(createButtons(), "MEDIUM");
        rules.add(new ActionRule(precondition,effets));
        return new Action("SYRUP_COUGH_HIGH",rules); 
    }
    
    // Genere des medicament aleatoire
    /**
     * Utiliser pour generer des medicament aléatoirement 
     * @param medicament nombre de medicament à generer;
     * @return liste de medicament;
     */
    public static Set<Action> medecine(int medicament){
        Set<Action> actions=new HashSet();
        String domaines[]={"HIGH","MEDIUM","LOW"};
        String symtoms[]= {"FEVER","BUTTONS","COUGH"};
//        int i=this.nbMedocAleatoire;
        int i=0;
        int nbDomaine=domaines.length;
        int nbSymtoms=symtoms.length;
        char lettre='A';
        
        for(i=0,lettre='A';i<medicament;i++,lettre++){
            Set<ActionRule> rules=new HashSet(); 
            //id du symtome que le  medicament corrige
            int idMedicamentSatisfait=(int)(Math.random()*nbDomaine);
            for(int j=0; j<nbSymtoms; j++){
                // position de la valeur du sous domaine du symptom traité
                int pos= (int)(Math.random()*nbDomaine);
                State precondition=new State();
                State effets= new State();
                int domainePrecondition= (int)(Math.random()*nbDomaine);
                precondition.getAffectation().put(createFrequencyDisease(symtoms[j]), domaines[domainePrecondition]);
            
                if(pos!=idMedicamentSatisfait){
                    effets.getAffectation().put(createFrequencyDisease(symtoms[j]), "NONE");
               }else{
                    effets.getAffectation().put(createFrequencyDisease(symtoms[j]), domaines[idMedicamentSatisfait]); 
               }
//               System.out.println("ale");
               rules.add(new ActionRule(precondition,effets));
            }
//            System.out.println("alefin");
            actions.add(new Action("Medecine_"+lettre, rules));
        }
        
        return actions; 
    }
    
    
    
    // Action de guérison
    /**
     * Génère une action qui permet de soigner une maladie
     * @return retourn une action Qui va soigner n'importe quel symtoms
     */
    public static Action createHealt(){

        String maladies[]={"ANGINE","POX","FLU","PLAGUE"};
        int nbMaladie=maladies.length;
        Set<ActionRule> rules=new HashSet();
        for(int i=0; i<nbMaladie;i++){
          State effets= new State();  
          State precondition=new State();
          precondition.getAffectation().put(createFever(), "NONE");
          precondition.getAffectation().put(createCough(), "NONE");
          precondition.getAffectation().put(createButtons(), "NONE");
          precondition.getAffectation().put(createDisease(maladies[i]), "TRUE");
          effets.getAffectation().put(createDisease(maladies[i]), "FALSE");
          rules.add(new ActionRule(precondition,effets));
        }
        return new Action("HEAL",rules); 
    }
    
    
    
    // Instatiation d'un problème de planification
    /**
     * Permet de creer un probleme avec un etat i et un etat final
     */
    public PlanningProblem generateInitialState(){
        State initialState;
        State finalState;
        Set<Action> actions=new HashSet();
        Map<Variable,String> initialAffectation=new HashMap();
        Map<Variable,String> finalAffectation= new HashMap();
        //Nom des differentes maladie
        String maladies[]={"ANGINE","POX","FLU","PLAGUE"};
        int nbMaladie=maladies.length;
        // choix d'une maladie de maniere aléatoire
        int pos= (int)(Math.random()*nbMaladie);
        // on cree la maladie en fonction de son id
        Variable disease=createDisease(maladies[pos]);
        initialAffectation.put(disease, "TRUE"); // au depart on n'est malade 
        finalAffectation.put(disease, "FALSE"); // dans l'affectation final on met la maladie à fausse;
        String frequency[]={"HIGH","MEDIUM","LOW","NONE"};
        String symtoms[]= {"FEVER","BUTTONS","COUGH"};
        int nbFrequency=frequency.length;
        int nbSymtoms=symtoms.length;
        
        Set<ActionRule> rules=new HashSet(); 
        int idFrequency=0;
// ici on va faire des affectation pour le symtom au depart il on une valeur à la fin il sont tous à NONE
        for(int j=0; j<nbSymtoms; j++){
            idFrequency=(int)(Math.random()*nbFrequency);
              Variable symtomsDisease=createFrequencyDisease(symtoms[j]);
              initialAffectation.put(symtomsDisease,frequency[idFrequency]);
              finalAffectation.put(symtomsDisease, "NONE");

        }
        initialState=new State(initialAffectation);
        finalState= new State(finalAffectation);
        actions.addAll(HealthCare.medecine(10));
        actions.add(HealthCare.createSyrupButtonHigh());
        actions.add(HealthCare.createSyrupFeverHigh());
        actions.add(HealthCare.createSyrupCoughHigh());
        actions.add(HealthCare.createHealt());
        
        // creation d'un problème
        PlanningProblem p=new PlanningProblem(initialState,finalState, actions);
       
     
        
        return p;
//      System.out.println(p.dfsAlgorihtm(p.getEtatInitial(),new LinkedList(),new HashSet<>(Arrays.asList(p.getEtatfinal()))));
        
       
//       System.out.println("Je suis un l'etat initial generer");
//       System.out.println(initialState.toString());
//       System.out.println("Je suis un l'etat final correspondant");
//       System.out.println(finalState.toString());
       
       // creation des medicament qui soigne un patien
//       Action health= createHealt();
       // application des medicaments à l'etat initial de notre probleme p;
//       health.apply(finalState);
//                
//           System.out.println(health.getName()+"{");
//           for(ActionRule r:health.getRules()){
//               System.out.println("je suis la precondition"+ r.getPrecondition().toString());
//               System.out.println("je suis la conclusion"+ r.getEffets().toString());
//           }
//           System.out.println("}"+health.getName());
//       System.out.println("Avant la l'action");
      
       // affichage du l'etat initial du probleme
//       System.out.println(initialState);//.toString());
//       System.out.println("tout juste avant l'execution");
//       //application de health à l'etat initial;
//       health.apply(initialState);
//       // après l'application à l'etat initial
//       System.out.println("Apres la l'action");
//       System.out.println(initialState);               
    }
    
    
//    
//    public void TestHeal(){
//        Action health= createHealt();
//        State 
//    }
}