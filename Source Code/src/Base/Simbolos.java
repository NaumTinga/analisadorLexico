package Base;

import java.util.ArrayList;
import java.util.List;
 

public class Simbolos {
    
    private String nome;
    private static final ArrayList<Simbolos> simbolos = new ArrayList();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void addSimbolo(Simbolos simbolo) {
        if (!Simbolos.simbolos.contains(simbolo))
            Simbolos.simbolos.add(simbolo);
    }

    public static List<Simbolos> getTabelaDeSimbolos() {
        return simbolos;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
    
    @Override
    public boolean equals(Object obj){
        Simbolos s = (Simbolos) obj;
        return s.getNome().equals(this.nome);
    }
   
    
}
