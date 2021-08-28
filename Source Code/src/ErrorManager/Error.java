package ErrorManager;

import java.util.ArrayList;
import java.util.List;
 
public abstract class Error {
    
    private String descricao;
    private int numeroLinha;
    private String arquivo;
    private static final List<Error> erros = new ArrayList();
    
    
    public abstract String showErrors();
    
    public static final void addErro(Error error){
        Error.erros.add(error);
    }
    
    public static final void limparErros(){
        Error.erros.clear();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumLinha() {
        return numeroLinha;
    }

    public void setNumLinha(int numeroLinha) {
        this.numeroLinha = numeroLinha;
    }

    public String getNomeArquivo() {
        return arquivo;
    }

    public void setNomeArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public static List<Error> getErros() {
        return erros;
    }
    
    
}
