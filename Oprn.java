/**
 * Assignment 5                Oprn.java             Due: 7 June 2013
 * Login in: cs12sbd
 **/

/**
 * Class Oprn - Class header comment
 * This class has no public constructors.
 * This class is defined as a Term, an Oprn + Term, or an Oprn minus
 * Term.
 * @author Pin Chu cs12sbd
 */

import java.lang.*;
import java.util.*;

public class Oprn extends ASTNode{
  private char symbol;
  private Oprn(ASTNode n){super(n);}
  
  /**
   * 
   */ 
  private Oprn(char c, Oprn o, Term t){
    super(o,t);
    this.symbol = c;
  }
  
  /**
   * Checking if:
   * 1. Oprn is a term
   * 2. Oprn is Oprn plus Term
   * 3. Oprn is Oprn minus Term
   * @Throws StringIndexOutOfBoundsException e
   */ 
  public static Oprn parse(String s){
    String string = s.trim();
    Term result1 = Term.parse(s);
    if(result1 != null)
      return new Oprn(result1);
    
    for(int i = 0; i < string.length(); i++){
      if(string.charAt(i) == '-'){
        try{
          String left = string.substring(0,i);
          String right = string.substring(i+1);
          
          Oprn result2 = Oprn.parse(left);
          if(result2 != null)
          {
            Term result3 = Term.parse(right);
            if(result3 != null){
              return new Oprn('-', result2, result3);
            } 
          }
        }
        catch(StringIndexOutOfBoundsException e){
          continue;
        }
      }
      
      if(string.charAt(i) == '+'){
        try{
          String left = string.substring(0,i);
          String right = string.substring(i+1);
          Oprn result4 = Oprn.parse(left);
          if(result4 != null)
          {
            Term result5 = Term.parse(right);
            if(result5 != null)
              return new Oprn('+', result4, result5);
          }
        }
        catch(StringIndexOutOfBoundsException e){
          continue;
        }
      }
    }
    return null;
  }
  
  /**
   * This method uses a HashMap to correctly store and compute values
   */ 
  public double eval(Map<String,Double> symtab){
    double left = getChild(0).eval(symtab);
    if(this.arity() == 1)
      return left;
    
    double right = getChild(1).eval(symtab);
    if(this.symbol == '+')
      return left + right;
    else
      return left - right;
  }
  
}
