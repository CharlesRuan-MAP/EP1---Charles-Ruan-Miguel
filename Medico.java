public class Medico {
    private String nome;
    private String crm;
    private String especialidade;
    private double valorConsulta;
    
    public Medico(String nome, String crm, String especialidade, double valorConsulta) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.valorConsulta = valorConsulta;
    }
    
    public String getNome() { return nome; }
    public String getCrm() { return crm; }
    public String getEspecialidade() { return especialidade; }
    public double getValorConsulta() { return valorConsulta; }
    
    public String toString() {
        return nome + " | CRM: " + crm + " | " + especialidade + " | R$ " + valorConsulta;
    }
}