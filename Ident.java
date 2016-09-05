/**
 * Assignment 5                Ident.java             Due: 7 June 2013
 * Login in: cs12sbd
 **/

/**
 * Class Ident - Class header comment
 * This class has no public constructors.
 * The Ident class is defined as a string that is allowed in java.
 * @author Pin Chu cs12sbd
 */

import java.lang.*;
import java.util.*;

public class Ident extends ASTNode{
  private String value;
  
  /**
   * Contructor that sets the string parameter as a key value.
   */ 
  private Ident(String s){
    this.value = s;
  }
  
  /**
   * Checking if all the characters or letters are acceptable in java.
   */ 
  public static Ident parse(String s){
    String string = s.trim();
    
    if(Character.isJavaIdentifierStart(s.charAt(0))){
      for(int i = 0; i < string.length(); i++){
        if(!Character.isJavaIdentifierPart(s.charAt(i))){
          return null;
        }
      }
      return new Ident(string);
    }
    return null;
  }
  
  /**
   * This method uses a HashMap to correctly store and compute values
   * @Return double
   * @throws RuntimeException if the result is invalid in Java.
   */ 
  public double eval(Map<String,Double> symboltable)
  { 
    Double num = symboltable.get(this.value);
    if(num == null){
      throw new RuntimeException("Unidentified variable: " + this.value);
    }
    return num;
  }
  
  /**
   * Overriden
   * @Return String  the Ident name
   */ 
  public java.lang.String toString(){
    return this.value;
  }
}
