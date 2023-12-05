package Cliente;

import Servidor.AgenteMMInterface;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
    public static void main(String[] args) {
        List<HistoricoCotacao> historicoCotacoes = new ArrayList<>();
        adicionarHistorico(historicoCotacoes);

        try{
            AgenteMMInterface agente = (AgenteMMInterface) Naming.lookup("AGENTE_MM");

            double probabilidade = agente.avaliarTendencia(historicoCotacoes);

            System.out.println("\n");

            String prob = String.valueOf(probabilidade) + "%";
            System.out.println("====================================");
            System.out.printf("|  PROBABILIDADE DE ALTA:  %-6s  |%n", prob);
            System.out.println("====================================");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao obter a probabilidade.");
            System.out.println(e.getMessage());
        }

    }

    private static void adicionarHistorico(List<HistoricoCotacao> historicoCotacoes){
        historicoCotacoes.add(new HistoricoCotacao("31-08-2023", 4.9213));
        historicoCotacoes.add(new HistoricoCotacao("01-09-2023", 4.9312));
        historicoCotacoes.add(new HistoricoCotacao("04-09-2023", 4.917));
        historicoCotacoes.add(new HistoricoCotacao("05-09-2023", 4.9699));
        historicoCotacoes.add(new HistoricoCotacao("06-09-2023", 4.9756));
        historicoCotacoes.add(new HistoricoCotacao("08-09-2023", 4.9829));
        historicoCotacoes.add(new HistoricoCotacao("11-09-2023", 4.936));
        historicoCotacoes.add(new HistoricoCotacao("12-09-2023", 4.9499));
        historicoCotacoes.add(new HistoricoCotacao("13-09-2023", 4.9165));
        historicoCotacoes.add(new HistoricoCotacao("14-09-2023", 4.8745));
        historicoCotacoes.add(new HistoricoCotacao("15-09-2023", 4.8683));
        historicoCotacoes.add(new HistoricoCotacao("18-09-2023", 4.8529));
        historicoCotacoes.add(new HistoricoCotacao("19-09-2023", 4.8569));
        historicoCotacoes.add(new HistoricoCotacao("20-09-2023", 4.8481));
        historicoCotacoes.add(new HistoricoCotacao("21-09-2023", 4.9223));
        historicoCotacoes.add(new HistoricoCotacao("22-09-2023", 4.9125));
        historicoCotacoes.add(new HistoricoCotacao("25-09-2023", 4.96));
        historicoCotacoes.add(new HistoricoCotacao("26-09-2023", 4.9711));
        historicoCotacoes.add(new HistoricoCotacao("27-09-2023", 5.0283));
        historicoCotacoes.add(new HistoricoCotacao("28-09-2023", 5.0469));
        historicoCotacoes.add(new HistoricoCotacao("29-09-2023", 5.007));
    }
	
}
