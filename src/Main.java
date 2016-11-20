import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame{
    private Carta [] carta = new Carta[28];
    private Container tela;
    private JMenuBar barra;
    private JMenu opcoes, sobre;
    private JMenuItem novoItem,
                    sairItem,
                    sobreItem,
                    itemPropriedades;
    private JFrame mainRef;
    private Config configuracao;

    /**
     * Construtor da classe Main
     */
    public Main(){
        super("PassosDarezzo - Jogo da Memória");
        //Carrega as configurações do jogo
        carregaConfig();

        tela = getContentPane();
        tela.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        

        //Cria uma referência para o frame
        mainRef = (this);
        //Inicializa os componentes
        initComponentes();
        //Inicializa o jogo
        iniciarJogo();
        //Muda o icone da janela
        mudaIcone();
        //tela.add(label);
        setSize(640, 540);
        setResizable(false);
    }

    /**
     * Inicializa os componentes
     */
    private void initComponentes(){

        sobre = new JMenu("Sobre");
        opcoes = new JMenu("Opções");

        //Itens do menu opções
        novoItem = new JMenuItem("Novo Jogo");
        novoItem.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               terminarJogo();
               iniciarJogo();
               setVisible(true);
           }
        });

        sairItem = new JMenuItem("Sair");
        sairItem.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               System.exit(0);
           }
        });

        itemPropriedades = new JMenuItem("Propriedades");
        itemPropriedades.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               FrameProp app = new FrameProp(configuracao, mainRef);
               app.setLocationRelativeTo(tela);

           }
        });

        //Item do menu Sobre
        sobreItem = new JMenuItem("Sobre o autor");
        sobreItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Sobre().setLocationRelativeTo(tela);
            }
        });

        //adicionando os itens ao menu OPÇÕES
        opcoes.add(novoItem);
        opcoes.add(itemPropriedades);
        opcoes.addSeparator();
        opcoes.add(sairItem);

        //adicionando os itens ao menu SOBRE
        sobre.add(sobreItem);

        //Barra de Menu
        barra = new JMenuBar();
        setJMenuBar(barra);
        barra.add(opcoes);
        barra.add(sobre);

        //Tela de abertura
        SplashScreen abertura = new SplashScreen(mainRef);
        abertura.setLocationRelativeTo(null);

    }

    /**
     * Cria os objetos carta e os adiciona na tela
     */
    private void carregaCartas(Tabuleiro tabuleiro, Conexao conexao, Config con){
        for(int i = 0; i < 28; i++){
            carta[i] = new Carta(tabuleiro, conexao, con);
            tela.add(carta[i]);
        }
    }

    /**
     * Método que altera o icone da janela
     */
    public void mudaIcone(){
        ImageIcon imgIcone = new ImageIcon("images/icone.gif");
        setIconImage(imgIcone.getImage());
    }

    /**
     * Inicia o jogo
     */
    public void iniciarJogo(){
        tela.setBackground(configuracao.getCor());
       carregaCartas(new Tabuleiro(mainRef), new Conexao(), configuracao);
    }

    /**
     * Termina o jogo limpando as variáveis e retirando os objetos
     */
    public void terminarJogo(){

        for(int i = 0; i < 28; i++){
            tela.remove(carta[i]);
        }
    }

    /**
     * Carrega as configurações
     */
    public void carregaConfig(){
        try {
            FileInputStream fileStream = new FileInputStream("doc/propriedades.pdz");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            Object one = os.readObject();
            configuracao = (Config) one;
            os.close();

        } catch (Exception e) {
            configuracao = new Config();

        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLocationRelativeTo(null);

        //DisplayMode dm = new DisplayMode(800, 600, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
        //Screen s = new Screen();
		//try{
		//	s.setFullScreen(dm, app);
			
		//}catch(Exception ex){}
    }

}
