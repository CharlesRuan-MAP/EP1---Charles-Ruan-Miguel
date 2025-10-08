import java.util.*;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Main {
    static List<Paciente> pacientes = new ArrayList<>();
    static List<Medico> medicos = new ArrayList<>();
    static List<Internacao> internacoes = new ArrayList<>();
    static List<PlanoSaude> planos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
    System.out.println("iniciando sistema hospitalar");
    criarPlanosPadrao();
    carregarDados(); 
    if (medicos.isEmpty()) {
        medicos.add(new Medico("Marcos Nunes", "BA001", "Diagnóstico", 300.0));
        medicos.add(new Medico("Isabella Alvez", "BA002", "Cardiologia", 250.0));
    }
        boolean executando = true;
        while (executando) {
            System.out.println("\n=== HOSPITAL FCTE ===");

            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - cadastrar PacienteVIP");
            System.out.println("3 - Cadastrar Medico");
            System.out.println("4 - listar Pacientes");
            System.out.println("5 - Listar Medicos");
            System.out.println("6 - Agendar Consulta");
            System.out.println("7 - Historico do Paciente");
            System.out.println("8 - Registrar Internacao");
            System.out.println("9 - Listar Internacoes");
            System.out.println("10 - Dar Alta na Internacao");
            System.out.println("11 - Salvar Dados");
            System.out.println("12 - Sair");
            System.out.print("Escolha: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1: cadastrarPacienteNormal(); break;
                case 2: cadastrarPacienteVIP(); break;
                case 3: cadastrarMedico(); break;
                case 4: listarPacientes(); break;
                case 5: listarMedicos(); break;
                case 6: agendarConsulta(); break;
                case 7: verHistorico(); break;
                case 8: registrarInternacao(); break;
                case 9: listarInternacoes(); break;
                case 10: darAltaInternacao(); break;
                case 11: salvarDados(); break;
                case 12: 
                    salvarDados();
                    executando = false;
                    System.out.println("saindo");
                    break;
                default: System.out.println("error");
            }
        }
    }
    static void criarPlanosPadrao() {
        planos.add(new PlanoSaude("Diamond", true, 0.15));
        planos.add(new PlanoSaude("Gold", false, 0.10));
        planos.add(new PlanoSaude("Silver", false, 0.05));
        planos.add(new PlanoSaude("Basico", false, 0.02));
    }
    static void carregarDados() {
        System.out.println("carregando dados");
        
        try {
            File arquivo = new File("pacientes.csv");
            if (arquivo.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                String linha;
                reader.readLine();
                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(",");
                    if (dados.length >= 5) {
                        String nome = dados[0];
                        String cpf = dados[1];
                        int idade = Integer.parseInt(dados[2]);
                        String tipo = dados[3];
                        String planoNome = dados[4];
                        
                        if (tipo.equals("VIP")) {
                            PlanoSaude plano = null;
                            for (PlanoSaude p : planos) {
                                if (p.getNome().equals(planoNome)) {
                                    plano = p;
                                    break;
                                }
                            }
                            if (plano != null) {
                                pacientes.add(new PacienteVIP(nome, cpf, idade, plano));
                            }
                        } else {
                            pacientes.add(new Paciente(nome, cpf, idade));
                        }
                    }
                }
                reader.close();
                System.out.println("Pacientes carregados: " + pacientes.size());
            }
        } catch (Exception e) {
            System.out.println("erro ao carregar pacientes: " + e.getMessage());
        }
        try {
            File arquivo = new File("medicos.csv");
            if (arquivo.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                String linha;
                reader.readLine();
                
                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(",");
                    if (dados.length >= 4) {
                        String nome = dados[0];
                        String crm = dados[1];
                        String especialidade = dados[2];
                        double valor = Double.parseDouble(dados[3]);
                        medicos.add(new Medico(nome, crm, especialidade, valor));
                    }
                }
                reader.close();
                System.out.println("Medicos carregados: " + medicos.size());
            }
        } catch (Exception e) {
            System.out.println("erro ao carregar medicos: " + e.getMessage());
        }
    }
    static void cadastrarMedico() {
    System.out.println("\n--- CADASTRAR MEDICO ---");
    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("CRM: ");
    String crm = scanner.nextLine();
    System.out.print("Especialidade: ");
    String especialidade = scanner.nextLine();
    System.out.print("Valor da consulta R$ ");
    double valor = scanner.nextDouble();
    scanner.nextLine();
    
    medicos.add(new Medico(nome, crm, especialidade, valor));
    System.out.println("Medico cadastrado");
}
    static void cadastrarPacienteNormal() {
        System.out.println("\n--- CADASTRAR PACIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        
        pacientes.add(new Paciente(nome, cpf, idade));
        System.out.println("Paciente cadastrado");
    }
    
    static void cadastrarPacienteVIP() {
        System.out.println("\n--- CADASTRAR PACIENTEVIP ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nPlanos:");
        for (int i = 0; i < planos.size(); i++) {
            System.out.println((i + 1) + " - " + planos.get(i).getNome());
        }
        System.out.print("Escolha o plano: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        
        if (escolha < 1 || escolha > planos.size()) {
            System.out.println("Plano invalido");
            return;
        }
        
        PlanoSaude plano = planos.get(escolha - 1);
        PacienteVIP vip = new PacienteVIP(nome, cpf, idade, plano);
        pacientes.add(vip);
        
        System.out.println("Paciente VIP cadastrado");
        System.out.println("Plano: " + plano.getNome());
    }
    
    static void listarPacientes() {
        System.out.println("\n--- LISTA DE PACIENTES ---");
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado");
        } else {
            for (Paciente p : pacientes) {
                System.out.println(p);
            }
        }
    }
    static void listarMedicos() {
        System.out.println("\n--- LISTA DE MEDICOS ---");
        for (Medico m : medicos) {
            System.out.println(m);
        }
    }
    
    static void agendarConsulta() {
        System.out.println("\n--- AGENDAR CONSULTA ---");
        
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado");
            return;
        }
        listarPacientes();
        System.out.print("Nome do paciente: ");
        String nomePaciente = scanner.nextLine();
        
        Paciente paciente = null;
        for (Paciente p : pacientes) {
            if (p.getNome().equalsIgnoreCase(nomePaciente)) {
                paciente = p;
                break;
            }
        }
        if (paciente == null) {
            System.out.println("Paciente nao encontrado");
            return;
        }
        listarMedicos();
        System.out.print("Nome do medico: ");
        String nomeMedico = scanner.nextLine();
        
        Medico medico = null;
        for (Medico m : medicos) {
            if (m.getNome().equalsIgnoreCase(nomeMedico)) {
                medico = m;
                break;
            }
        }
        if (medico != null) {
            double valorFinal = medico.getValorConsulta();
            String infoDesconto = "";
            
            if (paciente instanceof PacienteVIP) {
                PacienteVIP vip = (PacienteVIP) paciente;
                double desconto = vip.calcularDescontoConsulta(medico.getEspecialidade());
                valorFinal = medico.getValorConsulta() * (1 - desconto);
                infoDesconto = " | Desconto: " + (desconto * 100) + "%";
            }
            
            String consulta = "Consulta com " + medico.getNome() + " - " + medico.getEspecialidade() + " - R$ " + String.format("%.2f", valorFinal) + infoDesconto;
            paciente.addConsulta(consulta);
            
            System.out.println("Consulta agendada");
            System.out.println(consulta);
        } else {
            System.out.println("Medico nao encontrado");
        }
    }
    static void verHistorico() {
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine();
        
        for (Paciente p : pacientes) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                System.out.println("\nHistorico de " + p.getNome() + ":");
                if (p.getConsultas().isEmpty()) {
                    System.out.println("Nenhuma consulta");
                } else {
                    for (String consulta : p.getConsultas()) {
                        System.out.println(" - " + consulta);
                    }
                }
                return;
            }
        }
        System.out.println("Paciente nao encontrado");
    }
    static void registrarInternacao() {
        System.out.println("\n--- REGISTRAR INTERNACAO ---");
        
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado");
            return;
        }
        listarPacientes();
        System.out.print("Nome do paciente: ");
        String nomePaciente = scanner.nextLine();
        
        Paciente paciente = null;
        for (Paciente p : pacientes) {
            if (p.getNome().equalsIgnoreCase(nomePaciente)) {
                paciente = p;
                break;
            }
        }
        if (paciente == null) {
            System.out.println("Paciente nao encontrado");
            return;
        }
        
        listarMedicos();
        System.out.print("Nome do medico: ");
        String nomeMedico = scanner.nextLine();
        Medico medico = null;
        for (Medico m : medicos) {
            if (m.getNome().equalsIgnoreCase(nomeMedico)) {
                medico = m;
                break;
            }
        }
        if (medico == null) {
            System.out.println("Medico nao encontrado");
            return;
        }
        System.out.print("Quarto: ");
        String quarto = scanner.nextLine();
        
        for (Internacao i : internacoes) {
            if (i.getQuarto().equals(quarto) && i.isAtiva()) {
                System.out.println("Quarto " + quarto + " ocupado");
                return;
            }
        }
        System.out.print("Custo diario R$ ");
        double custoDiario = scanner.nextDouble();
        scanner.nextLine();
        
        Internacao internacao = new Internacao(paciente, medico, LocalDate.now(), quarto, custoDiario);
        internacoes.add(internacao);
        
        System.out.println("Internacao registrada");
        System.out.println(internacao);
    }
    
    static void listarInternacoes() {
        System.out.println("\n--- LISTA DE INTERNACOES ---");
        if (internacoes.isEmpty()) {
            System.out.println("Nenhuma internacao");
        } else {
            for (Internacao i : internacoes) {
                System.out.println(i);
            }
        }
    }
    static void darAltaInternacao() {
        System.out.println("\n--- DAR ALTA ---");
        
        List<Internacao> ativas = new ArrayList<>();
        for (Internacao i : internacoes) {
            if (i.isAtiva()) {
                ativas.add(i);
            }
        }
        if (ativas.isEmpty()) {
            System.out.println("Nenhuma internacao ativa");
            return;
        }
        System.out.println("Internacoes ativas:");
        for (int i = 0; i < ativas.size(); i++) {
            System.out.println((i + 1) + " - " + ativas.get(i));
        }
        
        System.out.print("Escolha: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        
        if (escolha < 1 || escolha > ativas.size()) {
            System.out.println("Escolha invalida");
            return;
        }
        Internacao internacao = ativas.get(escolha - 1);
        LocalDate dataAlta = LocalDate.now();
        
        try {
            internacao.darAlta(dataAlta);
            System.out.println("Alta registrada");
            System.out.println("Custo total: R$ " + String.format("%.2f", internacao.calcularCustoTotal()));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    static void salvarDados() {
        System.out.println("\n--- SALVAR DADOS ---");
        
        try {
            PrintWriter writer = new PrintWriter("pacientes.csv");
            writer.println("Nome,CPF,Idade,Tipo,Plano");
            for (Paciente p : pacientes) {
                if (p instanceof PacienteVIP) {
                    PacienteVIP vip = (PacienteVIP) p;
                    writer.println(p.getNome() + "," + p.getCpf() + "," + p.getIdade() + ",VIP," + vip.getPlano().getNome());
                } else {
                    writer.println(p.getNome() + "," + p.getCpf() + "," + p.getIdade() + ",NORMAL,-");
                }
            }
            writer.close();
            System.out.println("Pacientes salvos");
        } catch (Exception e) {
            System.out.println("Erro ao salvar pacientes: " + e.getMessage());
        }
        
        try {
            PrintWriter writer = new PrintWriter("medicos.csv");
            writer.println("Nome,CRM,Especialidade,ValorConsulta");
            for (Medico m : medicos) {
                writer.println(m.getNome() + "," + m.getCrm() + "," + m.getEspecialidade() + "," + m.getValorConsulta());
            }
            writer.close();
            System.out.println("Medicos salvos");
        } catch (Exception e) {
            System.out.println("Erro ao salvar medicos: " + e.getMessage());
        }
        
        System.out.println("Dados salvos");
    }
}