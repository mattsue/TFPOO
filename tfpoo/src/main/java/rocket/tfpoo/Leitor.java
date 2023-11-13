package rocket.tfpoo;
import java.io.*;
import java.util.*;

/**
 * Transforma o arquivo .csv em uma matriz manipulavel
 */
public class Leitor{

    private List<String[]> listaLinhas = new ArrayList<String[]>();
    private String linha;
    private final String[][] matrizString;
    private int tamLinha, tamColuna;

    /**
     * Acessa o arquivo .csv e constrói uma matriz
     * @param str
     */
    public Leitor(String str){
        if(str.equals("C")){
            try(BufferedReader data = new BufferedReader(new FileReader("composicao.csv"))){
                while((linha = data.readLine()) != null){
                    String[] itensLinha = linha.split(",");
                    listaLinhas.add(itensLinha);
                    tamColuna = itensLinha.length;
                    if(tamColuna!=2){
                        System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo composicao.csv");
                        System.exit(0);
                    }
                    tamLinha++;
                }
                data.close();
            }catch(Exception e){
                System.out.println(e);
            }

        }else if(str.equals("L")){
            try(BufferedReader data = new BufferedReader(new FileReader("locomotiva.csv"))){
                while((linha = data.readLine()) != null){
                    String[] itensLinha = linha.split(",");
                    listaLinhas.add(itensLinha);
                    tamColuna = itensLinha.length;
                    if(tamColuna != 3) {
                        System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo locomotiva.csv");
                        System.exit(0);
                    }
                    tamLinha++;
                }
                data.close();
            }catch(Exception e){
                System.out.println(e);
            }

        }else if(str.equals("V")){
            try(BufferedReader data = new BufferedReader(new FileReader("vagoes.csv"))){
                while((linha = data.readLine()) != null){
                    String[] itensLinha = linha.split(",");
                    listaLinhas.add(itensLinha);
                    tamColuna = itensLinha.length;
                    if(tamColuna != 2){
                        System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo vagao.csv");
                        System.exit(0);
                    }
                    tamLinha++;
                }
                data.close();
            }catch(Exception e){
                System.out.println(e);
            }
        }

        matrizString = new String[tamLinha][tamColuna];

        for(int i = 0; i < tamLinha; i++){
            for(int j = 0; j < tamColuna; j++){
                matrizString[i][j] = listaLinhas.get(i)[j];
            }
        }
    }
    public int getTamColuna(){
        return tamColuna;
    }

    public String[][] getMatriz(){
        return matrizString;
    }
}