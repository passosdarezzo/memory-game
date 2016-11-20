
import java.awt.Color;
import java.io.Serializable;

/**
 * Config.java
 * A classe Config realiza as configurações básicas do jogo e
 * guarda qualquer informação que seja alterada, e no caso do
 * arquivo de configuração desaparecer ou ser alterado de forma
 * incorreta um arquivo default será utilizado.
 *
 */

public class Config implements Serializable{
  //Declaração de variáveis.
  //
  private String pathCursor = "images/cursor.png",
            pathBackCarta = "images/cartas/backflip/blueflip.png";

  static final String path = "doc/propriedades.pdz";

  private Color corDeFundo = new Color(0, 135, 0);

  //Construtors da classe ------------------------------------------------------
  protected Config(String cursor, String carta, Color cor){
      pathCursor = cursor;
      pathBackCarta = carta;
      corDeFundo = cor;
  }

  protected Config(){
      new Config(pathCursor, pathBackCarta, corDeFundo);
  }

  
  //Métodos da classe ----------------------------------------------------------
  public Color getCor(){
      return corDeFundo;
  }

  public void setCor(Color cor){
      corDeFundo = cor;
  }

  public String getCursor(){
      return pathCursor;
  }

  public void setCursor(String cursor){
      pathCursor = cursor;
  }

  public void setCarta(String carta){
     pathBackCarta = carta;
  }

  public String getCarta(){
      return pathBackCarta;
  }

}
