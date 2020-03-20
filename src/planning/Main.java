package planning;

import Exemple.HealthCare;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import representations.RestrictedDomain;
import representations.Rule;
import representations.Variable;

public class Main {

    public static void main() throws IOException {

        // Variables ===================================================
//      Variable x0 = Variable.makeBooleanVariable("x0");
//
//
        Variable x1 = new Variable("angine", new HashSet<String>(Arrays.asList("oui", "non")));
        Variable x2 = new Variable("fievre", new HashSet<String>(Arrays.asList("haute", "moyenne", "non")));
        Variable x3 = new Variable("toux", new HashSet<String>(Arrays.asList("oui", "non")));
        Variable x4 = new Variable("grippe", new HashSet<String>(Arrays.asList("oui", "non")));
        Variable x5 = new Variable("vaccination", new HashSet<String>(Arrays.asList("oui", "non")));
        Variable x6 = new Variable("fatigue", new HashSet<String>(Arrays.asList("oui", "non")));
        Variable x7 = new Variable("virus", new HashSet<String>(Arrays.asList("oui", "non")));
        Variable x8 = new Variable("sirop", new HashSet<String>(Arrays.asList("oui", "non")));
        Variable x9 = new Variable("allergie", new HashSet<String>(Arrays.asList("haute", "moyenne", "non")));
        Variable x10 = new Variable("oedeme", new HashSet<String>(Arrays.asList("oui", "non")));
        Variable x11 = new Variable("hypothermie", new HashSet<String>(Arrays.asList("oui", "non")));
        Variable x12 = new Variable("boutons", new HashSet<String>(Arrays.asList("oui", "non")));
        Set<Variable> allVariables = new HashSet<>(Arrays.asList(x2, x1, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12));
        Deque<Variable> allVariable = new LinkedList<>(Arrays.asList(x3, x1, x2, x4));
        HealthCare care = new HealthCare();
        // generation de probleme
        PlanningProblem p = care.generateInitialState();   
        State initialState= new State(p.getEtatInitial());
        
        System.out.println("Dans le BFS");
        Deque<Action> resBfs= p.bfsAlgorihtm();
        
        
        System.out.println("Dans le DFS");
        Deque<Action> res= p.dfsAlgorihtm(initialState ,new LinkedList(), new HashSet<>(Arrays.asList(p.getEtatfinal())));
        if(res!=null){
            System.out.println(" On est guerri  en prenant progressivement :");
            for(Action result:res){
                System.out.println("\t"+result.getName());
            }
            System.out.println("fin du DFS");
        }
    
       
//         test de la generation des Itemfrequent;
        
        System.out.println("avec le dfs on parcourt "+p.getSonde_dfs_dabord()+" noeuds");
        System.out.println("avec le bfs on parcourt "+p.getSonde_bfs()+" noeuds");
    }

}
//
//
