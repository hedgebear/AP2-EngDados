package modelo;

public class Professor {

    private int id;
    private String nome;
    private int codigo_professor;
    private String cpf;
    private int telefone;
    private String especializacao;
    private String email;
    private String contaBanco;

 
        public Professor(int id, int codigo_professor, String nome, String cpf, String especializacao, String contaBanco, String email, int telefone) {
        this.id = id;
        this.codigo_professor = codigo_professor;
        this.nome = nome;
        this.cpf = cpf;
        this.especializacao = especializacao;
        this.contaBanco = contaBanco;
        this.email = email;
        this.telefone = telefone;
    }

    public Professor(int codigo_professor, String nome, String cpf, String especializacao, String contaBanco, String email, int telefone) {
        this.codigo_professor = codigo_professor;
        this.nome = nome;
        this.cpf = cpf;
        this.especializacao = especializacao;
        this.contaBanco = contaBanco;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo_professor() {
        return codigo_professor;
    }

    public void setCodigo_professor(int codigo_professor) {
        this.codigo_professor = codigo_professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecialização(String especialização) {
        this.especializacao = especialização;
    }

    public String getContaBanco() {
        return contaBanco;
    }

    public void setContaBanco(String contaBanco) {
        this.contaBanco = contaBanco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone (int telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "{'Professor':{'id': " + this.id + ", 'nome': '" + this.nome 
        + "', 'codigo_professor': " + this.codigo_professor + ", 'cpf': '" + this.cpf 
        + "', 'telefone': " + this.telefone + ", 'especializacao': '" + this.especializacao 
        + "', 'email': '" + this.email + "', 'contaBanco': '" + this.contaBanco + "'}}";
    }


}