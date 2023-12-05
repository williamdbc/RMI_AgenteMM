package Cliente;

import java.io.Serializable;

public class HistoricoCotacao implements Serializable {
	
    private String data;
    private double cotacao;

    public HistoricoCotacao(String data, double cotacao) {
        this.data = data;
        this.cotacao = cotacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getCotacao() {
        return cotacao;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }

}

