import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;




public class MinesFinder extends JFrame {
    private JPanel painelPrincipal;
    private JButton jogoFacilButton;
    private JButton jogoDificilButton;
    private JButton jogoMedioButton;
    private JButton sairButton;
    public JLabel recordeFacil;
    public JLabel recordeMedio;
    public JLabel recordeDificil;
    private JLabel nomeFacil;
    private JLabel nomeMedio;
    private JLabel nomeDificil;

    private TabelaRecordes recordesFacil;
    private TabelaRecordes recordesMedio;
    private TabelaRecordes recordesDificil;



    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
        recordesFacil = new TabelaRecordes();
        recordesMedio = new TabelaRecordes();
        recordesDificil = new TabelaRecordes();
        lerRecordesDoDisco();

        recordeFacil.setText(recordesFacil.getTempoJogo() + "");
        nomeFacil.setText(recordesFacil.getNomeJogador());
        
        recordeMedio.setText(recordesMedio.getNomeJogador() + "" + recordesMedio.getTempoJogo());
        
        recordeDificil.setText(recordesDificil.getNomeJogador() + "" + recordesDificil.getTempoJogo());
        
        recordesFacil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesFacilActualizado(recordes);
            }
        });
        recordesMedio.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesMedioActualizado(recordes);
            }
        });
        recordesDificil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesDificilActualizado(recordes);
            }
        });



        recordeFacil.setText("" + recordesFacil.getTempoJogo());
        recordeMedio.setText("" + recordesMedio.getTempoJogo());
        recordeDificil.setText("" + recordesDificil.getTempoJogo());
        sairButton.addActionListener(this::btnSairActionPerformed);
        jogoFacilButton.addActionListener(this::btnJogoFacilActionPerformed);
        jogoMedioButton.addActionListener(this::btnJogoMedioActionPerformed);
        jogoDificilButton.addActionListener(this::btnJogoDificilActionPerformed);

    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void btnJogoFacilActionPerformed(java.awt.event.ActionEvent evt) {
        var janela = new JanelaDeJogo(new CampoMinado(9,9, 10),recordesFacil);
        janela.setVisible(true); // se não foi executado no construtor…
    }



    private void btnJogoMedioActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,16, 40),recordesMedio);
        janela.setVisible(true); // se não foi executado no construtor…

    }

    private void btnJogoDificilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,30, 90),recordesDificil);
        janela.setVisible(true); // se não foi executado no construtor…
    }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }

    private void recordesFacilActualizado(TabelaRecordes recordes) {
        //recordeFacil.setText(recordes.getTempoJogo() + " " + Long.toString(recordes.getTempoJogo()/1000));
        guardarRecordesDisco();
    }
    private void recordesMedioActualizado(TabelaRecordes recordes) {
        //recordeMedio.setText(recordes.getTempoJogo() + " " + Long.toString(recordes.getTempoJogo()/1000));
        guardarRecordesDisco();
    }
    private void recordesDificilActualizado(TabelaRecordes recordes) {
        //recordeDificil.setText(recordes.getTempoJogo() + " " + Long.toString(recordes.getTempoJogo()/1000));
        guardarRecordesDisco();
    }
    private void guardarRecordesDisco() {
        ObjectOutputStream oos = null;
        try {
            File f =new
                    File(System.getProperty("user.home")+File.separator+"minesfinder.recordes");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(recordesFacil);
            oos.writeObject(recordesMedio);
            oos.writeObject(recordesDificil);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }


    private void lerRecordesDoDisco() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+File.separator+"minesfinder.recordes");
        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                recordesFacil=(TabelaRecordes) ois.readObject();
                recordesMedio=(TabelaRecordes) ois.readObject();
                recordesDificil=(TabelaRecordes) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }



}
