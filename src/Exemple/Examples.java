/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import representations.Rule;
import representations.Variable;

/**
 *
 * @author 
 */
public class Examples {
    
    private Set<Variable> variable;
    private Set<Rule> rules;
    
    
   /**
    * 
    */
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
      Deque<Variable> allVariable = new LinkedList<>(Arrays.asList(x3,x1,x2,x4));
}
