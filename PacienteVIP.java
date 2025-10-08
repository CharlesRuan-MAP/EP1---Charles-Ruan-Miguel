
public class PacienteVIP extends Paciente {
    private PlanoSaude plano;
    
    public PacienteVIP(String nome, String cpf, int idade, PlanoSaude plano) {
        super(nome, cpf, idade);
        this.plano = plano;
    }
    
    public double calcularDescontoConsulta(String especialidade) {
        return plano.calcularDescontoConsulta(especialidade, getIdade());
    }
    
    public PlanoSaude getPlano() { return plano; }
    
    public String toString() {
        return super.toString() + " [VIP - " + plano.getNome() + "]";
    }
}