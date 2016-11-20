import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Tabuleiro.java
 */
public class Tabuleiro {
    private static final int TOTALPARES = 14;

    private int qtdCartasViradas = 0,
                    delay = 540,
                    cliques,
                    paresFormados = 0;
    private String carta1 = "",
                    carta2 = "";
    private Carta cartaRef1, cartaRef2;
    private ActionListener executar;
    private Timer aguardar;
    private Main ref;
    private Tabuleiro tab;

    //Construtor da classe
    public Tabuleiro(JFrame ref){
        this.ref = (Main)ref;
        tab = (this);
        //executa checar cartas após o tempo determinado por aguardar, assim
        //a pessoa tem 2 seg para ver a carta.
        executar = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Aguardar e depois executar o método checarCatas()
                checarCartas();
                aguardar.stop();
            }
        };

        aguardar = new Timer(delay, executar);
    }

    public void cartaVirada(String ID, Carta ref){
        
        qtdCartasViradas ++;
        
        if(qtdCartasViradas == 1){
           carta1 = ID;
           cartaRef1 = ref;
        }else{
            carta2 = ID;
            cartaRef2 = ref;
            //Aguardar o tempo determinado por delay e chamar executar
            aguardar.start();
            
        }
        
    }

    
    /**
     * Verifica se as cartas são identicas, caso sejam chama o método
     * removerCarta() da classe Carta, caso contrário, desvira as cartas
     */
    public void checarCartas(){
        
        if(carta1.equalsIgnoreCase(carta2)){
            cartaRef1.removerCarta();
            cartaRef2.removerCarta();
            paresFormados ++;
            checarFimJogo();
        }else{
            cliques ++;
            cartaRef1.desvirarCarta();
            cartaRef2.desvirarCarta();
        }
        qtdCartasViradas = 0;
    }

    /**
     * checa se o jogo terminou
     */
    public void checarFimJogo(){
        if(paresFormados == TOTALPARES){
           //JOptionPane.showMessageDialog(null, "Total de cliques: " + cliques);
           new Pontuacao("Total de Tentativas: "
                   + cliques, tab).setLocationRelativeTo(ref);
        }
    }

    /**
     * Reinicia o jogo
     */
    public void reinicia(){
         ref.terminarJogo();
         ref.iniciarJogo();
         ref.setVisible(true);
    }

   
    
}
