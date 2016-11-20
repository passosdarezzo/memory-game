
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sobre extends JFrame{
    private JLabel label;
    private String autor;
    private JButton bOk;
    private JPanel painel;
    
    public Sobre(){
        super("Sobre o Autor");
        Container tela = getContentPane();
        tela.setLayout(new FlowLayout(FlowLayout.LEFT));

        mudaIcone();

        autor = "Cristiano dos Passos,  e-mail : passosdarezzo@hotmail.com";
        label = new JLabel(autor);

        bOk = new JButton("Sair");
        bOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        painel = new JPanel();
        painel.setLayout(new FlowLayout(FlowLayout.LEFT));
        painel.add(bOk);

        tela.add(label);
        tela.add(painel);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(360, 100);
        setVisible(true);
    }

     public void mudaIcone(){
        ImageIcon imgIcone = new ImageIcon("images/icone.gif");
        setIconImage(imgIcone.getImage());
    }

    
}

