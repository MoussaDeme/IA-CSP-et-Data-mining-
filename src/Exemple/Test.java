package Exemple;
import static Exemple.HealthCare.createDisease;
import static Exemple.HealthCare.createFrequencyDisease;
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
import planning.Action;
import planning.ActionRule;
import planning.PlanningProblem;
import planning.State;
import ppc.Backtracking;
import representations.RestrictedDomain;
import representations.Rule;
import representations.Variable;



public class Test {

    public static void main() {

        HealthCare care = new HealthCare();
        
       PlanningProblem p= care.generateInitialState();
       
       System.out.println(p);
       
       
    }
}
