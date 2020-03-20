/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.Boolean;
import java.util.ArrayDeque;
import java.util.Deque;
import ppc.Backtracking;
//import ppc.Backtracking;

/**
 *
 * @author 21912873
 *
 * cette classe nous permet de tester les differentes sur la methode
 * issatisfiedBy dans rule et de teste sur la dijonction en testant avec avec
 * des exemples satisfaisant et falsifiant la premisse et la conclusion
 */
public class Test {

    public static void main() {

        // VARIABLES
        Variable x0 = new Variable("x0", "0");
        Variable x1 = new Variable("x1", "0", "1");
        Variable x2 = new Variable("x2", "0", "1", "2");
        Variable x3 = new Variable("x3", "0", "1", "2", "3", "6");

        //ENSEMBLE DE TOUTE LES VARIABLES
        Set<Variable> allVariables = new HashSet<>();
        allVariables.add(x0);
        allVariables.add(x1);
        allVariables.add(x2);
        allVariables.add(x3);

        Set<Variable> allVariablesConclusion = new HashSet<>();
        allVariablesConclusion.add(x3);
        allVariablesConclusion.add(x2);
        // Rule : (!x0 && x1=0) -> (new RestrictedDomain(x2=1 || x3=2)
        // ======================
        Set<RestrictedDomain> premise = new HashSet<>();
        premise.add(new RestrictedDomain(x0, "0"));
        premise.add(new RestrictedDomain(x1, "0"));

        Set<RestrictedDomain> conclusion = new HashSet<>();
        conclusion.add(new RestrictedDomain(x2, "1"));
        conclusion.add(new RestrictedDomain(x3, "2", "6"));
        
        // DECLARATION DE NOTRE REGLE
        Constraint rule = new Rule(premise, conclusion);
        // Assignments =================================================
        System.out.println(rule.getScope());
        Map<Variable, String> satisfying1 = new HashMap<>();
        satisfying1.put(x0, "0");
        satisfying1.put(x1, "0");
        satisfying1.put(x2, "0");
        satisfying1.put(x3, "2");

        Map<Variable, String> satisfying2 = new HashMap<>();
        satisfying2.put(x0, "0");
        satisfying2.put(x1, "1");
        satisfying2.put(x2, "0");
        satisfying2.put(x3, "0");

        Map<Variable, String> satisfying3 = new HashMap<>();

        satisfying3.put(x0, "1");
        satisfying3.put(x1, "1");
        satisfying3.put(x2, "1");
        satisfying3.put(x3, "2");

        Map<Variable, String> falsifying = new HashMap<>();

        //falsifying.put(x0, "0");
        falsifying.put(x1, "0");
        falsifying.put(x2, "2");
        falsifying.put(x3, "1");

        // Tests =======================================================
        System.out.print("Testing scope: ");
        System.out.println(rule.getScope().equals(allVariables) ? "OK" : "KO");
        System.out.println(rule);
        System.out.print("Testing satisfying assignment: ");
        System.out.println(satisfying1);
        System.out.println(rule.isSatisfiedBy(satisfying1) ? "OK" : "KO");

        System.out.print("Testing satisfying assignment: ");
        System.out.println(satisfying2);
        System.out.println(rule.isSatisfiedBy(satisfying2) ? "OK" : "KO");

        System.out.print("Testing satisfying assignment: ");
        System.out.println(satisfying3);
        System.out.println(rule.isSatisfiedBy(satisfying3) ? "OK" : "KO");

        System.out.print("Testing falsifying assignment: ");
        System.out.println(falsifying);
        System.out.println(!rule.isSatisfiedBy(falsifying) ? "OK" : "KO");
 /*DIJONCTION*/
        System.out.println("TEST DE DIJONCTION");
        Disjonction disjonction = new Disjonction(conclusion);
        System.out.print("Testing scope: ");
        System.out.println(disjonction.getScope().equals(allVariablesConclusion) ? "OK" : "KO");
        System.out.println(disjonction);
        System.out.print("Testing satisfying assignment: ");
        System.out.println(satisfying1);
        System.out.println(disjonction.isSatisfiedBy(satisfying1) ? "OK" : "KO");

        System.out.print("Testing satisfying assignment: ");
        System.out.println(satisfying2);
        System.out.println(disjonction.isSatisfiedBy(satisfying2) ? "OK" : "KO");

        System.out.print("Testing satisfying assignment: ");
        System.out.println(satisfying3);
        System.out.println(disjonction.isSatisfiedBy(satisfying3) ? "OK" : "KO");

        System.out.println("FIN DIJONCTION");
        

    }
}
