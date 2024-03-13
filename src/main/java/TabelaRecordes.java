import java.io.Serializable;
import java.util.ArrayList;

public class TabelaRecordes implements Serializable {
    private String nomeJogador;
    private long tempoJogo;
    private transient ArrayList<TabelaRecordesListener> listeners;


    public TabelaRecordes() {
        this.nomeJogador = "Anonimo";
        this.tempoJogo = 9999999;
        listeners=new ArrayList<>();

    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public long getTempoJogo() {
        return tempoJogo;
    }

    public void setRecorde(String nomeJogador, long tempoJogo){
        if (tempoJogo<this.tempoJogo){
            this.tempoJogo = tempoJogo;
            this.nomeJogador = nomeJogador;
            notifyRecordesActualizados();
        }
    }

    public void addTabelaRecordesListener(TabelaRecordesListener list) {
        if (listeners == null) listeners = new ArrayList<>();
        listeners.add(list);
    }
    public void removeTabelaRecordesListener(TabelaRecordesListener list) {
        if (listeners != null) listeners.remove(list);
    }

    private void notifyRecordesActualizados() {
        if (listeners != null) {
            for (TabelaRecordesListener list:listeners)
                list.recordesActualizados(this);
        }
    }



}
