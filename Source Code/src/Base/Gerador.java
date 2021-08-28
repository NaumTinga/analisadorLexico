package Base;

import ErrorManager.ErrorLexico;
import ErrorManager.Error;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class Gerador {

    private BufferedReader codigoFonte;
    private String arquivo;
    private String linha;
    private String caractere;
    private String palavra = "";
    private boolean comentario = false;
    private final ArrayList<String> OPERADORES_RELACIONAL = new ArrayList();
    private final ArrayList<String> OPERADORES_ARITMETICOS = new ArrayList();
    private final ArrayList<String> PALAVRAS_RESERVADA = new ArrayList();
    private final ArrayList<String> DELIMITADOR = new ArrayList();
    private final ArrayList<Token> tokens = new ArrayList();

    private String palavras_reservada[] = {"program", "var ", "array", "of", "char", "integer", "boolean", "begin", "end", "read", "write",
        "if", "then", "else", "while", "do", "not", "procedure", "true",
        "false", "div", "function"};

    private String delimitadores[] = {" ", ",", ".", ";", ":", ")", "(", "}", "{", "\\n"};
    private String operadores_relacional[] = {">","|", "<", ">=", "<=", "=", "and", "or"};
    private String operadores_aritmeticos[] = {"+", "-", "/", "*", "**"};

    public Gerador(String arquivo) {
        try {
            this.arquivo = arquivo; 
            
            PALAVRAS_RESERVADA.addAll(Arrays.asList(palavras_reservada));
            OPERADORES_RELACIONAL.addAll(Arrays.asList(operadores_relacional));
            OPERADORES_ARITMETICOS.addAll(Arrays.asList(operadores_aritmeticos));
            DELIMITADOR.addAll(Arrays.asList(delimitadores));
            
            codigoFonte = new BufferedReader(new FileReader(this.arquivo));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
        }
    }

    public void analisar() throws IOException {
        int numeroLinha = 0;
        while (true) {
            numeroLinha++;
            linha = codigoFonte.readLine();
            if (linha == null) {
                break;
            }
            linha = linha.split("\r")[0];
            caractere = "";
            for (int i = 0; i < linha.length(); i++) {
                caractere = linha.substring(i, i + 1);
                if (DELIMITADOR.contains(caractere)) {
                    if ((!comentario) && (palavra.length() >= 2) && (palavra.substring(0, 2).equals(
                            "//"))) {
                        palavra = "";
                        break;
                    }
                    if ((!comentario) && (palavra.length() >= 2) && (palavra.substring(0, 2).equals(
                            "{*"))) {
                        palavra = "";
                        comentario = true;
                    }
                    if ((comentario) && (palavra.length() >= 2) && (palavra.substring(0, 2).equals(
                            "*}"))) {
                        palavra = "";
                        comentario = false;
                    }

                    if (!comentario) {
                        if ((!palavra.equals("")) && (!palavra.contains("{*"))) {
                            this.addToken(palavra, numeroLinha);
                        }
                    }
                    palavra = "";

                } else {
                    palavra += caractere;
                }
            }

        }
    }

    private void addToken(String palavra, int numeroLinha) {
        Token token = new Token();
        Pattern pattern = new Pattern();
        if (palavra.matches(pattern.isNumeric())) { 
            token.setToken("NUMÉRICO");
            token.setLexema(palavra);
            tokens.add(token);
            return;
        }

        if (palavra.matches(pattern.isAssignment())) {
            token.setToken("ATRIBUICAO");
            token.setLexema(palavra);
            tokens.add(token);
            return;
        }

        if (OPERADORES_RELACIONAL.contains(palavra)) {
            token.setToken("OPERADOR RELACIONAL");
            token.setLexema(palavra);
            tokens.add(token);
            return;
        }
        
        if (OPERADORES_ARITMETICOS.contains(palavra)) {
            token.setToken("OPERADORES ARITMETICOS");
            token.setLexema(palavra);
            tokens.add(token);
            return;
        }

        if (PALAVRAS_RESERVADA.contains(palavra)) {
            token.setToken("PALAVRA RESERVADA");
            token.setLexema(palavra);
            tokens.add(token);
            return;
        }

        if (!palavra.equals(PALAVRAS_RESERVADA)) {
            if (palavra.matches(pattern.isIdentifier())) {
                token.setToken("IDENTIFICADOR");
                token.setLexema(palavra);
                tokens.add(token);
                Simbolos simbolo = new Simbolos();
                simbolo.setNome(palavra);
                Simbolos.addSimbolo(simbolo);
                return;
            }
        }

        Error erro = new ErrorLexico();
        erro.setDescricao("Identificador desconhecido: " + palavra);
        erro.setNomeArquivo(this.arquivo);
        erro.setNumLinha(numeroLinha);
        Error.addErro(erro);

    }

    public List<Token> getTokens() {
        return tokens;
    }
}
