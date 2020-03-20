/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import representations.Constraint;
import representations.RestrictedDomain;
import representations.Rule;
import representations.Variable;

/**
 *
 * @author deme
 */
public class Test {
    public static void main()
    {
      Variable x1 = new Variable("angine","oui","non");
      Variable x2 = new Variable("fievre","haute","moyenne","basse");
      Variable x3 = new Variable("toux","oui","non"); 
      Variable x4 = new Variable("grippe","oui","non");
      Variable x5 = new Variable("vaccination","oui","non");
      Variable x6 = new Variable("fatigue","oui","non");
      Variable x7 = new Variable("virus","oui","non");
      Variable x8 = new Variable("sirop","oui","non");
      Variable x9 = new Variable("allergie","haute","moyenne","non");
      Variable x10 = new Variable("oedeme","oui","non");
      Variable x11 = new Variable("hypothermie","oui","non");
      Variable x12 = new Variable("boutons","oui","non");
      
      Set<Constraint> constraints = new HashSet<Constraint>();
      
       //        Rule : (!x0 && x1=0) -> (new RestrictedDomain(x2=1 || x3=2)
       //  =====================
       // rule1 : L'angine provoque une fièvre haute ou moyenne
        Set<RestrictedDomain> p1 = new HashSet<>();
        p1.add(new RestrictedDomain(x1, "oui"));

        Set<RestrictedDomain> c1 = new HashSet<>();
        c1.add(new RestrictedDomain(x2, "haute","moyenne"));
        
        Rule r1 = new Rule(p1, c1);
        constraints.add(r1);
        
       // rule2 : L'angine provoque la toux
        Set<RestrictedDomain> p2 = new HashSet<>();
        p2.add(new RestrictedDomain(x1, "oui"));

        Set<RestrictedDomain> c2 = new HashSet<>();
        c2.add(new RestrictedDomain(x3, "oui"));
        
        Rule r2 = new Rule(p2, c2);
        constraints.add(r2);
        
       //  rule3 : Une grippe, en l'absence de vaccination, provoque une fièvre haute
        Set<RestrictedDomain> p3 = new HashSet<>();
        p3.add(new RestrictedDomain(x4,"oui"));
        p3.add(new RestrictedDomain(x5,"non"));

        Set<RestrictedDomain> c3 = new HashSet<>();
        c3.add(new RestrictedDomain(x2, "haute"));
        
        Rule r3 = new Rule(p3, c3);
        //constraints.add(r3);
        
       //  rule4 : Une grippe, en l'absence de vaccination, provoque la fati
        Set<RestrictedDomain> p4 = new HashSet<>();
        p4.add(new RestrictedDomain(x4, "oui"));
        p4.add(new RestrictedDomain(x5,"non"));

        Set<RestrictedDomain> c4 = new HashSet<>();
        c4.add(new RestrictedDomain(x6, "oui"));
        
        Rule r4 = new Rule(p4, c4);
        //constraints.add(r4);
        
       //  rule5 : L'angine peut ou non être provoquée par un virus
        Set<RestrictedDomain> p5 = new HashSet<>();
        p5.add(new RestrictedDomain(x1, "oui"));
        
        Set<RestrictedDomain> c5 = new HashSet<>();
        c5.add(new RestrictedDomain(x7, "oui","non"));
        
        Rule r5 = new Rule(p5, c5);
        //constraints.add(r5);
        
       //  rule6 : Une grippe est toujours provoquée par un virus
        Set<RestrictedDomain> p6 = new HashSet();
        p6.add(new RestrictedDomain(x4,"oui"));
        
        Set<RestrictedDomain> c6 = new HashSet();
        c6.add(new RestrictedDomain(x7, "oui"));
        
        Rule r6 = new Rule(p6, c6);
        //constraints.add(r6);
        
        //regle 7 : La prise de sirop avec une allergie moyenne au sucre provoque des boutons
        Set<RestrictedDomain> p7 = new HashSet();
        p7.add(new RestrictedDomain(x8,"oui"));
        p7.add(new RestrictedDomain(x9,"moyenne"));
        
        Set <RestrictedDomain> c7 = new HashSet();
        c7.add(new RestrictedDomain(x12,"oui"));
        
        Rule r7 = new Rule(p7,c7);
        //constraints.add(r7);
        
        //regle 8 : La prise de sirop avec une allergie moyenne au sucre provoque des boutons
      
        Set<RestrictedDomain> p8 = new HashSet();
        p8.add(new RestrictedDomain(x8,"oui"));
        p8.add(new RestrictedDomain(x9,"haute"));
        
        Set <RestrictedDomain> c8 = new HashSet();
        c8.add(new RestrictedDomain(x10,"oui"));
        
        Rule r8 = new Rule(p8,c8);
        //constraints.add(r8);
        
        // rule 9 : On ne peut pas à la fois avoir une fièvre haute ou moyenne et être en hypothermie
        
        Set<RestrictedDomain> p9 = new HashSet();
        p9.add(new RestrictedDomain(x2, "haute","moyenne"));
        
        Set <RestrictedDomain> c9 = new HashSet();
        c9.add(new RestrictedDomain(x11,"non"));
        
        Rule r9 = new Rule(p9,c9);
        //constraints.add(r9);
        
        
        // Backtracking
        
        Backtracking search = new Backtracking(constraints);
        
        List<Map<Variable, String>> allSolutions;
        
        allSolutions = search.allSolutions();
        
        System.out.println("Nombre de solution du backtraking: "+allSolutions.size());    
        
        //System.out.println(allSolutions.get(0));
        
        /*Map<Variable, String> t = new HashMap<Variable, String>();
        t.put(x1, "oui");
        t.put(x2, "non");
        
        System.out.println("test : "+r1.isSatisfiedBy(t));
        */
        
        for(Map<Variable, String> allSolu: allSolutions)
        {
            System.out.println(allSolu);
            
           
        }
    }
}
