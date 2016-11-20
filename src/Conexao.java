import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;


/**
 * Conexao.java
 * Essa classe, cria uma coleção de caminhos para as imagens das cartas,
 * quando solicitado, ela devolve um caminho aleatóriamente .
 */

public class Conexao {

    private File file = new File("doc/listaDeImagens.txt");
    private ArrayList <String> colecaoDeCaminhos = new ArrayList();
    private String imagemVazia = "images/cartas/empty.png";

    public Conexao(){
        coletaDados();
    }

    /**
     * Realiza a leitura do arquivo listaDeImagens e preenche o
     * ArrayList.
     */
    protected void coletaDados(){
        if(file.canRead()){
            try {
                Reader arquivo = new FileReader(file);
                BufferedReader leitor = new BufferedReader(arquivo);

                String line = "";

                while((line = leitor.readLine()) != null){
                    colecaoDeCaminhos.add(line);
                }

            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }

    /**
     * Retorna um String que é um caminho para uma imagem aleatoria
     */
    public String getImagem(){
        String imagem = "";
        
        if(!colecaoDeCaminhos.isEmpty()){
          //Gera um indice aleatorio baseado na quantidade de elementos do ArrayList
          int ran = (int)(Math.random() * colecaoDeCaminhos.size());
          //Guarda a imagem selecionada aleatoriamente
          imagem = colecaoDeCaminhos.get(ran);
          //Retira a imagem utilizada da coleção
          colecaoDeCaminhos.remove(imagem);
          //Retorna a imagem selecionada aleatoriamente
          return imagem;
        }else{
            return imagemVazia;
        }
        
    }

    }
