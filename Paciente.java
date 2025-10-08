import java.util.*;

public class Paciente {
    private String nome;
    private String cpf;
    private int idade;
    private List<String> consultas;
    
    public Paciente(String nome, String cpf, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.consultas = new ArrayList<>();
    }
    
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public int getIdade() { return idade; }
    public List<String> getConsultas() { return consultas; }
    public void addConsulta(String consulta) { consultas.add(consulta); }
    
    public String toString() {
        return nome + " | CPF: " + cpf + " | Idade: " + idade;
    }
}