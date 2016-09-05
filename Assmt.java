/**
* Assignment 5                Assmt.java             Due: 7 June 2013
* Login in: cs12sbd
**/

/**
* Class Assmt - Class header comment
* This class has no public constructors. 
* A class representing the Assmt nonterminal symbol in an abstract syntax tree. 
* It is supposed to assign the value on the right hand side of the = sign. 
* @author Pin Chu cs12sbd
*/

import java.lang.*;
import java.util.*;

public class Assmt extends ASTNode{
  private char symbol;
  
  /**
   * The Ident and the Expr classes that are passed in are passed to the ASTNode
   * constructor.
   * @Para Char c, Ident ident, Expr expr
   */ 
  private Assmt(char c, Ident ident, Expr expr){
    // Calls ASTNode's construcor
    super(ident,expr);
    // Assign the symbols
    this.symbol = c;
  }
  
  /**
   * This method calls parse method of Oprn and Assmt classes since Expr could 
   * be either operation or assignment
   * @Param String s 
   */ 
  public static Assmt parse(String s){
    String string = s.trim();
    for(int i = 0; i < string.length(); i++){
      if(string.charAt(i) == '='){
        String left = string.substring(0,i);
        String right = string.substring(i+1);
        Ident result1 = Ident.parse(left);
        if(result1 != null){
          Expr result2 = Expr.parse(right);
          if(result2 != null)
            return new Assmt('=', result1, result2);  
        }
        
      }
    }
    return null;
  }
  
  /**
   * This method uses a HashMap to correctly store and compute values
   */ 
  public double eval(Map<String,Double> symtab){
    String val1 = getChild(0).toString();
    Double val2 = getChild(1).eval(symtab);
    symtab.put(val1, val2);
    return val2;
  }
  
}
