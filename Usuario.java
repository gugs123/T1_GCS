public class Usuario {
    
    private String nome;
    private String matricula;
    private boolean adm;
    private String departamento;
    private char inicialNome;
    private char inicialSobrenome;

    public Usuario(String nome, String sobrenome, String matricula, boolean adm, String departamento) {
        this.nome = nome;
        this.matricula = matricula;
        this.adm = adm;
        this.departamento = departamento;
        this.inicialNome = nome.charAt(0);
        this.inicialSobrenome = sobrenome.charAt(0);
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
    
    public char getInicialNome() {
        return inicialNome;
    }

    public void setInicialNome(char inicialNome) {
        this.inicialNome = inicialNome;
    }

    public char getInicialSobrenome() {
        return inicialSobrenome;
    }

    public void setInicialSobrenome(char inicialSobrenome) {
        this.inicialSobrenome = inicialSobrenome;
    }

    //Adciona visto ser necessario paro o departamento
    public String getDepartamento() {
        return this.departamento;
    }

    @Override
    public String toString() {
        return "Usuario [adm=" + adm + ", nome=" + nome + ", matricula=" + matricula + ", Departamento= " + departamento + "]";
    }

    

}
