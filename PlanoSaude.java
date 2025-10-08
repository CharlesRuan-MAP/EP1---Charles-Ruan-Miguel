import java.util.*;

public class PlanoSaude {
    private String nome;
    private Map<String, Double> descontosEspecialidades;
    private boolean internacaoGratuita;
    private double descontoIdoso;
    
    public PlanoSaude(String nome, boolean internacaoGratuita, double descontoIdoso) {
        this.nome = nome;
        this.internacaoGratuita = internacaoGratuita;
        this.descontoIdoso = descontoIdoso;
        this.descontosEspecialidades = new HashMap<>();
        
        if (nome.toLowerCase().contains("diamond")) {
            configurarDescontosPadrao(0.4, 0.35, 0.3, 0.25);
        } else if (nome.toLowerCase().contains("gold")) {
            configurarDescontosPadrao(0.3, 0.25, 0.2, 0.15);
        } else if (nome.toLowerCase().contains("silver")) {
            configurarDescontosPadrao(0.2, 0.15, 0.1, 0.05);
        } else {
            configurarDescontosPadrao(0.1, 0.08, 0.05, 0.02);
        }
    }
    
    private void configurarDescontosPadrao(double cardio, double pediatria, double geral, double outros) {
        descontosEspecialidades.put("cardiologia", cardio);
        descontosEspecialidades.put("pediatra", pediatria);
        descontosEspecialidades.put("diagnóstico", geral);
        descontosEspecialidades.put("clínico-geral", geral);
        descontosEspecialidades.put("outros", outros);
    }
    
    // 🔥 MÉTODO QUE ESTAVA FALTANDO
    public double calcularDescontoConsulta(String especialidade, int idadePaciente) {
        double desconto = descontosEspecialidades.getOrDefault(especialidade.toLowerCase(), 0.05);
        
        if (idadePaciente >= 60) {
            desconto += descontoIdoso;
        }
        
        return Math.min(desconto, 0.8);
    }
    
    public double calcularCustoInternacao(double custoOriginal, long diasInternacao) {
        if (internacaoGratuita && diasInternacao < 7) {
            return 0.0;
        }
        return custoOriginal;
    }
    
    // 🔥 MÉTODO QUE ESTAVA FALTANDO
    public String getNome() {
        return nome;
    }
    
    public boolean isInternacaoGratuita() {
        return internacaoGratuita;
    }
    
    public double getDescontoIdoso() {
        return descontoIdoso;
    }
    
    public String getInfoDescontos() {
        StringBuilder info = new StringBuilder();
        info.append("Plano: ").append(nome).append("\n");
        info.append("Internação < 7 dias grátis: ").append(internacaoGratuita ? "SIM" : "NÃO").append("\n");
        info.append("Desconto idoso: ").append(descontoIdoso * 100).append("%\n");
        info.append("Descontos por especialidade:\n");
        
        for (Map.Entry<String, Double> entry : descontosEspecialidades.entrySet()) {
            info.append("  - ").append(entry.getKey()).append(": ").append(entry.getValue() * 100).append("%\n");
        }
        
        return info.toString();
    }
    
    public String toString() {
        return nome + " | Internação <7d: " + (internacaoGratuita ? "GRÁTIS" : "PAGA") + 
               " | Idoso: +" + (descontoIdoso * 100) + "%";
    }
}