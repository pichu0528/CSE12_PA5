/**
 * Assignment 5                Factor.java             Due: 7 June 2013
 * Login in: cs12sbd
 **/

/**
 * Class Factor - Class header comment
 * This class has no public constructors.
 * Factor class is defined as a constant, an identifier, or an expression in 
 * parentheses.
 * @author Pin Chu cs12sbd
 */

import java.lang.*;
import java.util.*;

// Passin as a child
public class Factor extends ASTNode{
  private Factor(ASTNode n){super(n);}
  
  /**
   * Check three different parts:
   * 1. Check if the factor is a constant. 
   * 2. Check if the factor is an identifier.
   * 3. Check if the factor is an expression in parentheses.
   * @Return Factor a new factor object
   */ 
  public static Factor parse(String s){
    String string = s.trim();
    
    Const result1 = Const.parse(string);
    if(result1 != null)
      return new Factor(result1);
    
    Ident result2 = Ident.parse(string);
    if(result2 != null)
      return new Factor(result2);
    
    if(string.charAt(0) == '(' && string.charAt(string.length() - 1) == ')')
    {
      System.out.println(string.substring(1, string.length()-1));
      Expr result3 = Expr.parse(string.substring(1,s.length() -1));
      if(result3 != null)
        return new Factor(result3);
    }
    return null;
    
  }
  
  /**
   * This method uses a HashMap to correctly store and compute values
   */ 
  public double eval(Map<String,Double> symtab){
    return getChild(0).eval(symtab);
  }
}
