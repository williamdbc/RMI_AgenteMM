package Servidor;

import Cliente.HistoricoCotacao;

import java.rmi.*;
import java.util.List;

public interface AgenteMMInterface extends Remote {
    double avaliarTendencia(List<HistoricoCotacao> historicoCotacoes) throws RemoteException;
}
