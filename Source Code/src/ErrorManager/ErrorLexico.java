package ErrorManager;

public class ErrorLexico extends Error{
     
    @Override
    public String showErrors(){
        return "Erro na linha: " +getNumLinha()+" >>"+ getDescricao();
    }
}
