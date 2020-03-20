package datamining;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import representations.Variable;


/**
 * 
 * @author jordan
 * Classe principale pour gerer l'extraction des itemset frequent 
 * Faire la propotionalisation d'une base de donnée
 * Gerer les base de donné booleane
 * Extraire egalement les regles
 */
public class Main{

   public static void main() throws IOException {

      // Variables ===================================================
       
      Variable x1 = new Variable("angine",new HashSet<String>(Arrays.asList("oui","non")));
      Variable x2 = new Variable("fièvre",new HashSet<String>(Arrays.asList("haute","moyenne","non")));
      Variable x3 = new Variable("toux",new HashSet<String>(Arrays.asList("oui","non"))); 
      Variable x4 = new Variable("grippe",new HashSet<String>(Arrays.asList("oui","non")));
      Variable x5 = new Variable("vacciné(e)",new HashSet<String>(Arrays.asList("oui","non")));
      Variable x6 = new Variable("fatigué(e)",new HashSet<String>(Arrays.asList("oui","non")));
      Variable x7 = new Variable("virus",new HashSet<String>(Arrays.asList("oui","non")));
      Variable x8 = new Variable("prise_sirop",new HashSet<String>(Arrays.asList("oui","non")));
      Variable x9 = new Variable("allergie_sucre",new HashSet<String>(Arrays.asList("haute","moyenne","basse")));
      Variable x10 = new Variable("œdème",new HashSet<String>(Arrays.asList("oui","non")));
      Variable x11 = new Variable("hypothermie",new HashSet<String>(Arrays.asList("oui","non")));
      Variable x12 = new Variable("boutons",new HashSet<String>(Arrays.asList("oui","non")));
      Set<Variable> allVariables = new HashSet<>(Arrays.asList(x2,x1,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12));
      
        //lecture de la base de donnéé
       DBReader database= new DBReader(allVariables);
//       System.out.println(database);
       Database bdd=null;
       try{
          // Ouverture du fichier
           bdd=database.importDB("database/exemple_db.csv");
           System.out.println(bdd);
           System.out.println("OK");
       }catch(Exception e){
           e.getMessage();
       }
       
       // transformation de la base de donnée en booleenne
       BooleanDatabase db= bdd.makeBooleanDataBase();
    
       FrequentItemSetMiner itemsets=new FrequentItemSetMiner(db);
       
      //generation des itemset frequents;
      Map<Set<Variable>, Integer>  itemFrequent= itemsets.frequentItemsets(120);
      
       AssociationRuleMiner ruleMiner=new AssociationRuleMiner(itemFrequent);
       Set<ExtractRule> rule = ruleMiner.generateAllRule(120,80);
       
       System.out.println(itemFrequent.keySet().size());
       
//       System.out.println(db.getListeDeTransaction());
       
       for(ExtractRule r: rule){
           System.out.println(r.toString());
       }
       System.out.println("on a "+itemFrequent.size()   +" items frequents");
       System.out.println("on a extrait "+ rule.size()+" regles");
       

    }
 
   public static Map<Variable,String> getListOfMap(Set<Variable>listeVariable, String val1,String val2,String val3,String val4,String val5){
       Map<Variable,String> transaction= new HashMap();
       for(Variable variable: listeVariable){
           if(variable.getNom()=="A"){
               transaction.put(variable, val1);
           }
           if(variable.getNom()=="B"){
               transaction.put(variable, val2);
           }
           if(variable.getNom()=="C"){
               transaction.put(variable, val3);
           }
           if(variable.getNom()=="D"){
               transaction.put(variable, val4);
           }
           if(variable.getNom()=="E"){
               transaction.put(variable, val5);
           }
           
       }
       return transaction;
   }
}