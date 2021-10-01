public class Usuario {
    
    private String nome;
    private String matricula;
    private boolean adm;
    private String departamento;

    public Usuario(String nome, String matricula, boolean adm, String departamento) {
        this.nome = nome;
        this.matricula = matricula;
        this.adm = adm;
        this.departamento = departamento;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public boolean isAdm() {
        return adm;
    }
    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    @Override
    public String toString() {
        return "Usuario [adm=" + adm + ", nome=" + nome + ", matricula=" + matricula + ", Departamento= " + departamento + "]";
    }

    

}
