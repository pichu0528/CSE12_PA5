/**
 * Assignment 5                Expr.java             Due: 7 June 2013
 * Login in: cs12sbd
 **/

/**
 * Class Expr - Class header comment
 * This class has no public constructors.
 * It defines an expression for class. Expression could either be an assignment
 * or an operation.
 * @author Pin Chu cs12sbd
 */

import java.lang.*;
import java.util.*;

// Adds whatever to child
public class Expr extends ASTNode{
  private Expr(ASTNode n){super(n);}
  
  /**
   * Since expression could either be an assignment or an operation, this method
   * has both parse methods of the operation and assignment.
   */ 
  public static Expr parse(String s){
    Oprn result1 = Oprn.parse(s);
    if(result1 != null) 
      return new Expr(result1);
    
    Assmt result2 = Assmt.parse(s);
    if(result2 != null)
      return new Expr(result2);
    
    return null;
    
  }
  
  /**
   * This method uses a HashMap to correctly store and compute values
   * @Exception IllegalStateException if Expression has more than one kid.
   * @Return Double
   */ 
  public double eval(Map<String,Double> symtab){
    if(this.arity() > 1)
      throw new IllegalStateException();
    return getChild(0).eval(symtab);
  }
}
