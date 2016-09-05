/**
 * Assignment 5                Term.java             Due: 7 June 2013
 * Login in: cs12sbd
 **/

/**
 * Class Term - Class header comment
 * This class has no public constructors.
 * It is defined as a Factor, a Term times Factor, or a Term divided by Factor.
 * @author Pin Chu cs12sbd
 */

import java.lang.*;
import java.util.*;

public class Term extends ASTNode{
  private char word;
  
  private Term(ASTNode n){super(n);}
  
  /**
   * Pass in to be as children
   */ 
  private Term(char c, Term term, Factor factor){
    super(term, factor);
    this.word = c;
  }
  
  /**
   * Checking if
   * 1. Term is a Factor
   * 2. Term is a Term times Factor
   * 3. Term is a Term divided by Factor
   * @Return Term
   */ 
  public static Term parse(java.lang.String s){
    String string = s.trim();
    Factor result1 = Factor.parse(string);
    if(result1 != null)
      return new Term(result1);
    for(int i = 0; i < string.length(); i++){
      if(string.charAt(i) == '*'){
        String left = string.substring(0,i);
        String right = string.substring(i+1);
        
        Term result2 = Term.parse(left);
        if(result2 != null){
          Factor result3 = Factor.parse(right);
          if(result3 != null)
            return new Term('*', result2, result3);
        }
      }
      
      if(string.charAt(i) == '/'){
        String left = string.substring(0,i);
        String right = string.substring(i+1);
        
        Term result4 = Term.parse(left);
        if(result4 != null){
          Factor result5 = Factor.parse(right);
          if(result5 != null)
            return new Term('/', result4, result5);
        }
      }
    }
    return null;
  }
  
  /**
   * This method uses a HashMap to correctly store and compute values
   */ 
  public double eval(java.util.Map<java.lang.String,java.lang.Double> symtab){
    double value1 = getChild(0).eval(symtab);
    if(this.arity() == 1)
      return value1;
    double value2 = getChild(1).eval(symtab);
    if(this.word == '*')
      return value1*value2;
    return value1/value2;
  }
}
