/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author dmariquele
 */
public class Pattern {
    private final String NUMERICO = ("^\\d+|^\\d+\\.?\\d+");
    private final String IDENTIFICADOR = ("^\\D\\w+|^\\D\\w?$");
    private final String ATRIBUICAO = (":=");
      
   public String isNumeric(){
    return this.NUMERICO;
   }
   
   public String isIdentifier(){
    return this.IDENTIFICADOR;
   }  
   
   public String isAssignment(){
    return this.ATRIBUICAO;
   }  
}
