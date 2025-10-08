import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Internacao {
    private Paciente paciente;
    private Medico medicoResponsavel;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String quarto;
    private double custoDiario;
    private boolean ativa;

    public Internacao(Paciente paciente, Medico medicoResponsavel, LocalDate dataEntrada, String quarto, double custoDiario) {
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.dataEntrada = dataEntrada;
        this.quarto = quarto;
        this.custoDiario = custoDiario;
        this.ativa = true;
        this.dataSaida = null;
    }
    public void darAlta(LocalDate dataSaida) {
        if (dataSaida.isBefore(dataEntrada)) {
            throw new IllegalArgumentException("Error: datas erradas");
        }
        this.dataSaida = dataSaida;
        this.ativa = false;
    }
    public double calcularCustoTotal() {
        long dias;
        
        if (ativa) {
            dias = ChronoUnit.DAYS.between(dataEntrada, LocalDate.now());
            dias = Math.max(dias, 1);
        } else {
            dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
            dias = Math.max(dias, 1);
        }
        
        return dias * custoDiario;
    }
    
    public Paciente getPaciente() { return paciente; }
    public Medico getMedicoResponsavel() { return medicoResponsavel; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public LocalDate getDataSaida() { return dataSaida; }
    public String getQuarto() { return quarto; }
    public double getCustoDiario() { return custoDiario; }
    public boolean isAtiva() { return ativa; }
    
    public String toString() {
        String status = ativa ? "INTERNADO" : "ALTA";
        String periodo = dataEntrada + " a " + (ativa ? "---" : dataSaida);
        double custo = calcularCustoTotal();
        
        return "Paciente: " + paciente.getNome() + 
               " | Quarto: " + quarto + 
               " | " + periodo + 
               " | " + status +
               " | Custo: R$ " + String.format("%.2f", custo);
    }
}