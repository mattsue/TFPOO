package rocket.tfpoo;
import java.io.*;

/**
 * Transforma o arquivo .csv em uma matriz manipulavel
 */
public class Leitor{
    private String linha;
    private GaragemCarros gc;
    private Patio patio;
    /**
     * Acessa os arquivos .csv 
     */
    public Leitor(GaragemCarros gc, Patio patio){
        this.gc = gc;
        this.patio = patio;
    }
    public void function(){
        try(BufferedReader dataC = new BufferedReader(new FileReader("composicao.csv"))){
            while((linha = dataC.readLine()) != null){
                String[] itensLinha = linha.split(",");
                if(itensLinha.length !=2){
                    System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo composicao.csv");
                    System.exit(0);
                }
            }
            dataC.close();
        }catch(Exception e){
            System.out.println(e);
        }

        try(BufferedReader dataL = new BufferedReader(new FileReader("locomotiva.csv"))){
            double maxPeso;
            int maxVagoes;
            int idL;
            while((linha = dataL.readLine()) != null){
                String[] itensLinha = linha.split(",");
                if(itensLinha.length != 3) {
                    System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo locomotiva.csv");
                    System.exit(0);
                }else{
                    try {
                        idL = Integer.parseInt(itensLinha[0]);
                        maxPeso = Double.parseDouble(itensLinha[1]);
                        maxVagoes = Integer.parseInt(itensLinha[2]);
                        Locomotiva locomotiva = new Locomotiva(idL, maxPeso, maxVagoes);
                        gc.addCarro(locomotiva);
                    } catch (Exception e) {
                        System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada inválida no arquivo locomotiva.csv");
                        System.exit(0);
                    }
                }
            }
        dataL.close();
        }catch(Exception e){
            System.out.println(e);
        }

        try(BufferedReader data = new BufferedReader(new FileReader("vagoes.csv"))){
            while((linha = data.readLine()) != null){
                String[] itensLinha = linha.split(",");
                if(itensLinha.length != 2){
                    System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo vagao.csv");
                    System.exit(0);
                }else{
                    int idV = 0;
                double capacidade = 0.0; 
                try {
                    idV = Integer.parseInt(linha[0]);
                    capacidade = Double.parseDouble(linha[1]); 
                } catch (Exception e) {
                    System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada inválida no arquivo vagao.csv");
                    System.exit(0);
                }
                Vagao vagao = new Vagao(idV, capacidade);
                gc.addCarro(vagao);
                }
            }
            data.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}