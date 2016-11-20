
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Carta extends JLabel{
    //Imagem nas costas da carta
    private ImageIcon back;
    //Imagem Vazia
    private ImageIcon empty = new ImageIcon("images/cartas/empty.png");
    //Imagem da carta
    private ImageIcon imagem;
    //Borda da carta
    private Border bordaC = BorderFactory.createLineBorder(Color.ORANGE, 2);
    //Identificação
    private String ID;
    //Cria um Objeto Conexao que fornece uma lista de imagens aleatoria
    private Conexao conexaoRef;
    //Cria uma referência a um objeto Tabuleiro
    Tabuleiro tabuleiro;
    //Referência a um objeto carta
    Carta cartaRef;
    //Carta Ativa ou inativa
    private boolean statusDaCarta = true;
    //Indica quantas cartas estão viradas no tabuleiro
    static int cartasViradas;
    //Referência a um objeto Config
    private Config configuracao;

    public Carta(Tabuleiro tabuleiro, Conexao ref, Config conf){
        //Referência a um objeto Config
        configuracao = conf;
        //Imagem do Baralho
        back  = new ImageIcon(configuracao.getCarta());
        //Referência a um objeto Conexão
        conexaoRef = ref;
        //Aponta a referência para a própria carta
        cartaRef = this;
        //inicialização das cartasViradas
        cartasViradas = 0;
        //Cria uma referência a um objeto Tabuleiro
        this.tabuleiro = tabuleiro;
        //O caminho para a imagem será quem identificará a Carta
        ID = conexaoRef.getImagem();
        //Cria a imagem da carta
        imagem = new ImageIcon(ID);
        //Imagem da carta virada para baixo
        this.setIcon(back);
        this.addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e){
                  setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                  virarCarta();
           }

           public void mouseEntered(MouseEvent e){
               if(statusDaCarta == true){
                    setBorder(bordaC);
                    mudaCursor();
               }
           }

           public void mouseExited(MouseEvent e){
               setBorder(null);
               setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
           }
        });
        
    }
   
   /**
    * Vira a carta mostrando a imagem aleatória gerada com a classe Conexão
    */
   public void virarCarta(){
       if(statusDaCarta == true && cartasViradas < 2){
          cartasViradas ++;
          this.setIcon(imagem);
          statusDaCarta = false;
          tabuleiro.cartaVirada(ID, cartaRef);
       }
   }

    /**
    *
    */
   public void desvirarCarta(){
        this.setIcon(back);
        statusDaCarta = true;
        cartasViradas = 0;
   }

   /**
    *
    */
   public void removerCarta(){
       this.setIcon(empty);
       setBorder(null);
       cartasViradas = 0;
   }

   /**
    * muda o cursor quando ele se encontra em cima da carta
    */
   public void mudaCursor(){
       Cursor c;
       Toolkit tk = Toolkit.getDefaultToolkit();
       Image img = tk.getImage(configuracao.getCursor());
       c = tk.createCustomCursor(img, new Point(0, 0), "Slayer");
       setCursor(c);
   }

}

 