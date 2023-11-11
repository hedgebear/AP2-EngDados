package AP2.modelo;

public class Professor {

    private int id;
    private int codigo_professor;
    private String nome;
    private String cpf;
    private String especializacao;
    private String contaBanco;
    private String email;
    private int telefone;

 
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

    public Professor(int prof_id, int cod_prof, String nome_prof, String cpf_prof, String tel_prof, String email_prof,
            String espec_prof, String conta_prof) {
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
        return "{'professor':{'id': " + this.id + ", 'codigo_professor': " + this.codigo_professor 
        + ", 'nome': '" + this.nome + "', 'cpf': '" + this.cpf + "', 'especialização': '" + this.especializacao 
        + "', 'contaBanco': '" + this.contaBanco + "', 'Email': '" + this.email + "'}}";
    }

}