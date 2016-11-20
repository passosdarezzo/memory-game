

/** 
 * SplashScreen.java
 * tela de abertura do programa
 * @author PassosDarezzo
 * criado em 17 de janeiro de 2009
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class SplashScreen extends JWindow{
    private JFrame refMainSplash;
    private Timer timer;
    
    public SplashScreen(JFrame ref){
        Container tela = getContentPane();
        tela.setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
        ImageIcon imgFundo = new ImageIcon("images/abertura.png");
        JLabel rotulo = new JLabel(imgFundo);
        tela.add(rotulo);

        refMainSplash = ref;
      
        //********************************************
        ActionListener fechar = new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
            setVisible(false);
            dispose();
            refMainSplash.setVisible(true);
            timer.stop();
        }};
        //*********************************************
        timer = new Timer(3000,fechar);
        timer.start();
        setVisible(true);
        pack();
        
        
    }

}
