
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * FrameProp.java
 * 
 */

public class FrameProp extends JFrame{

    private Border bordaCursor = BorderFactory.createTitledBorder("Selecione o Cursor");
    private Border bordaBaralho = BorderFactory.createTitledBorder("Selecione o Baralho");
    private Border bordaBotoes = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
    private Border bordaColor = BorderFactory.createTitledBorder("Selecione a cor de fundo");

    private JButton bAplicar, bCancelar, bPadrao;
    private JColorChooser colorChooser;
    private JComboBox listaBaralhos,
                        listaCursores;
    private Container tela;
    private ImageIcon [] imagensCursores = new ImageIcon[3];
    private ImageIcon [] imagensBaralho = new ImageIcon[6];
    private Config con;
    private Main fr;


    public FrameProp(Config con, JFrame fr){
        super("Propriedades");
        tela = getContentPane();
        tela.setLayout(new BorderLayout());

        this.con = con;
        this.fr =(Main) fr;

        carregaImagens();

        initComponentes();
        mudaIcone();

        setResizable(false);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponentes(){
        //Instanciação e configuração dos botões
        bCancelar = new JButton("Cancelar");
        bCancelar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               dispose();
           }
        });

        bAplicar = new JButton("Aplicar");
        bAplicar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){

               String cursor = "";
               String baralho = "";

               //Guarda o cursor escolhido
               switch(listaCursores.getSelectedIndex()){
                   case 0:
                       cursor = "images/cursor.png";
                       break;
                   case 1:
                       cursor = "images/cursor2.png";
                       break;
                   case 2:
                       cursor = "images/cursor3.gif";
                       break;
               }

               //Guarda o baralho escolhido
               switch(listaBaralhos.getSelectedIndex()){
                   case 0:
                       baralho = "images/cartas/backflip/blueflip.png";
                       break;
                   case 1:
                       baralho = "images/cartas/backflip/redflip.png";
                       break;
                   case 2:
                       baralho = "images/cartas/backflip/dragao.png";
                       break;
                   case 3:
                       baralho = "images/cartas/backflip/digital.png";
                       break;
                   case 4:
                       baralho = "images/cartas/backflip/orochimaru.png";
                       break;
                   case 5:
                       baralho = "images/cartas/backflip/misterio.png";
                       break;
               }
               
               //Grava o objeto com as configurações escolhidas
               con.setCarta(baralho);
               con.setCor(colorChooser.getColor());
               con.setCursor(cursor);

               gravarObjeto(con);

               //Reinicia o jogo
               fr.carregaConfig();
               fr.terminarJogo();
               fr.iniciarJogo();
               fr.setVisible(true);


               //fecha a janela
               dispose();
           }
        });

        bPadrao = new JButton("Restaurar Padrões");
        bPadrao.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               gravarObjeto(new Config());

               //Reinicia o jogo
               fr.carregaConfig();
               fr.terminarJogo();
               fr.iniciarJogo();
               fr.setVisible(true);

               //fecha a janela
               dispose();
           }
        });

        JPanel painelBotao = new JPanel();
        painelBotao.setLayout(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.setBorder(bordaBotoes);
        painelBotao.add(bPadrao);
        painelBotao.add(bAplicar);
        painelBotao.add(bCancelar);

        //------------------------------------------------
        
        listaCursores = new JComboBox(imagensCursores);
        listaCursores.setBorder(bordaCursor);


        listaBaralhos = new JComboBox(imagensBaralho);
        listaBaralhos.setBorder(bordaBaralho);
        listaBaralhos.setMaximumRowCount(3);


        JPanel painelCampo = new JPanel();
        painelCampo.setLayout(new BoxLayout(painelCampo, BoxLayout.X_AXIS));
       
        painelCampo.add(listaCursores);
        painelCampo.add(listaBaralhos);

        //------------------------------------------------
        colorChooser = new JColorChooser(con.getCor());
        colorChooser.setBorder(bordaColor);
        

        //Adicionar a tela
        tela.add(painelBotao, BorderLayout.SOUTH);
        tela.add(painelCampo, BorderLayout.NORTH);
        tela.add(colorChooser, BorderLayout.CENTER);
        
    }

    /**
     * 
     */
    public void carregaImagens(){
            imagensCursores[0] = new ImageIcon("images/cursor.png");
            imagensCursores[1] = new ImageIcon("images/cursor2.png");
            imagensCursores[2] = new ImageIcon("images/cursor3.gif");

            imagensBaralho[0] = new ImageIcon("images/cartas/backflip/blueflip.png");
            imagensBaralho[1] = new ImageIcon("images/cartas/backflip/redflip.png");
            imagensBaralho[2] = new ImageIcon("images/cartas/backflip/dragao.png");
            imagensBaralho[3] = new ImageIcon("images/cartas/backflip/digital.png");
            imagensBaralho[4] = new ImageIcon("images/cartas/backflip/orochimaru.png");
            imagensBaralho[5] = new ImageIcon("images/cartas/backflip/misterio.png");
    }

    /**
     * Método que altera o icone da janela
     */
    public void mudaIcone(){
        ImageIcon imgIcone = new ImageIcon("images/icone.gif");
        setIconImage(imgIcone.getImage());
    }

    /**
     * Grava o objeto
     */
    public void gravarObjeto(Object obj){
        try {
            FileOutputStream fileStream = new FileOutputStream("doc/propriedades.pdz");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(obj);
            os.close();

        } catch (Exception e) {
            System.out.println("Não é possível gravar o objeto !");
        }
    }

}
