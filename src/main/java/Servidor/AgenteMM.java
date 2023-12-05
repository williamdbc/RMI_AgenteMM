package Servidor;

import Cliente.HistoricoCotacao;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AgenteMM extends UnicastRemoteObject implements AgenteMMInterface {
    private static final long serialVersionUID = 1L;

    public AgenteMM() throws RemoteException {
            super();
    }

    @Override
    public double avaliarTendencia(List<HistoricoCotacao> historicoCotacoes) {
        double percentualProbabilidade = 0.0;

        System.out.println("\n\n\n\n\n");
        System.out.println("=============================================================================================================================");
        System.out.println("|    Data    |  Cotação (BRL)  |   M3   |   M6   |   M9   |   M3 > Cotação   |   M6 > M3   |   M9 > M6   |   M3 - Cotação   |");
        System.out.println("=============================================================================================================================");

        double m3 = 0;
        double m6 = 0;
        double m9 = 0;

        int tamanho = historicoCotacoes.size();

        for (int i = 0; i < tamanho; i++) {
            m3 = calcularMedia(historicoCotacoes, i, 3);
            m6 = calcularMedia(historicoCotacoes, i, 6);
            m9 = calcularMedia(historicoCotacoes, i, 9);

            mostrarPerformance(historicoCotacoes.get(i), m3, m6, m9);
        }

        percentualProbabilidade = calcularProbabilidade(m3, m6, m9);

        return percentualProbabilidade * 100; // Retorna a probabilidade apenas para o último item
    }

    private double calcularProbabilidade(double m3, double m6, double m9) {
        if (!(m6 > m3) && !(m9 > m6)) {
            return 1.0;
        } else if (!(m6 > m3) && m9 > m6) {
            return 0.75;
        } else if (m6 > m3 && m9 > m6) {
            return 0.0;
        } else if (m6 > m3 && !(m9 > m6)) {
            return 0.25;
        }
        return 0.0;
    }

    public void mostrarPerformance(HistoricoCotacao cotacao, double m3, double m6, double m9){
        System.out.printf("| %-10s |      %-10.4f | %.4f | %.4f | %.4f |      %-8s    |    %-8s |    %-8s |    %-10.7f    |\n",
            cotacao.getData(), cotacao.getCotacao(), m3, m6, m9,
            m3 > cotacao.getCotacao(), m6 > m3, m9 > m3, m3 - cotacao.getCotacao());

        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }

    private double calcularMedia(List<HistoricoCotacao> historicoCotacoes, int indiceAtual, int dias) {
        int tamanho = historicoCotacoes.size();
        int inicio = indiceAtual - dias + 1;
        inicio = Math.max(0, inicio); // Garante que o índice de início não seja negativo

        if (tamanho >= dias && indiceAtual >= dias - 1) {
            double soma = 0.0;
            for (int i = inicio; i <= indiceAtual; i++) {
                    soma += historicoCotacoes.get(i).getCotacao();
            }
            return soma / dias;
        }
        return 0.0;
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            AgenteMM agenteMM = new AgenteMM();

            Naming.rebind("AGENTE_MM", agenteMM);

            System.out.println("================================================");
            System.out.println("|   AgenteMM pronto para receber chamadas...   |");
            System.out.println("================================================");
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
        
    }

}
