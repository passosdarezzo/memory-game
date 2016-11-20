
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.Border;




public class Pontuacao extends JWindow{
    private String texto = "";
    private Font fonte = new Font("Times New Roman", Font.PLAIN, 45);
    private JLabel label;
    private JButton bOk;
    private Border borda = BorderFactory.createLineBorder(Color.BLACK, 4);
    private Tabuleiro tab;

    public Pontuacao(String texto, Tabuleiro tab){
        this.tab = tab;

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBorder(borda);

        label = new JLabel();
        label.setFont(fonte);
        label.setText(texto);

        bOk = new JButton("OK");
        bOk.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               dispose();
               reiniciar();
           }
        });

        painel.add(label, BorderLayout.CENTER);
        painel.add(bOk, BorderLayout.SOUTH);
        //---------
        add(painel);
        
        setVisible(true);
        setSize(400, 400);
        pack();
    }

     public void reiniciar(){
         tab.reinicia();
     }

    
}
