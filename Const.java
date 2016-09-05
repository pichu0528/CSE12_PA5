/**
 * Assignment 5                Const.java             Due: 7 June 2013
 * Login in: cs12sbd
 **/

/**
 * Class Const - Class header comment
 * This class has no public constructors.
 * This class is to check the correct value of a constant. If the number was a 
 * double, then the method will return an exception.
 * @author Pin Chu cs12sbd
 */

import java.lang.*;
import java.util.*;

public class Const extends ASTNode{
  private double value;
  
  /**
   * Constructor to assign the passin values
   */ 
  private Const(double v){
    this.value = v;
  }
  
  /**
   * A Constant is any String s that can be parsed by java.lang.Double.parseDouble
   * Without throwing a NumberFormatException. Therefore, this parsing method
   * is to check whether the value of a constant is the value of the constant as 
   * a Java double.
   */ 
  public static Const parse(String s){
    String string = s.trim();
    if(s == null){
      string = string.trim();
    }
    if(string.length() == 0){
      return null;
    }
    try{
      double num = Double.parseDouble(string);
      return new Const(num);
    }
    catch(NumberFormatException e){
      return null;
    }
    
  }
  
  /**
   * Return number
   */ 
  public double eval(Map<String,Double> symtab){
    return this.value;
  }
}
