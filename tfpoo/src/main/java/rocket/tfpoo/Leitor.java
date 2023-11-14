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
        int cont = 0;
        try(BufferedReader dataL = new BufferedReader(new FileReader("locomotiva.csv"))){
            double maxPeso;
            int maxVagoes;
            int idL;
            while((linha = dataL.readLine()) != null){
                cont++;
                String[] itensLinha = linha.split(",");
                if(itensLinha.length != 3) {
                    System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo locomotiva.csv");
                    System.exit(0);
                }else{
                    if(cont !=1){
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
            }
        dataL.close();
        }catch(Exception e){
            System.out.println(e);
        }

        cont = 0;
        try(BufferedReader dataV = new BufferedReader(new FileReader("vagoes.csv"))){
           int idV = 0;
           double capacidade = 0;
           while((linha = dataV.readLine()) != null){
            cont++;
               String[] itensLinha = linha.split(",");
               if(itensLinha.length != 2){
                   System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo vagao.csv");
                   System.exit(0);
               }else{ 
                    if(cont!=1){
                        try {
                            idV = Integer.parseInt(itensLinha[0]);
                            capacidade = Double.parseDouble(itensLinha[1]); 
                        } catch (Exception e) {
                            System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada inválida no arquivo vagao.csv");
                            System.exit(0);
                        }
                        Vagao vagao = new Vagao(idV, capacidade);
                        gc.addCarro(vagao);
                    }
               }
           }
           dataV.close();
       }catch(Exception e){
           System.out.println(e);
       }
       cont = 0;
       try(BufferedReader dataC = new BufferedReader(new FileReader("composicao.csv"))){
            while((linha = dataC.readLine()) != null){
                cont++;
                String[] itensLinha = linha.split(",");
                if(itensLinha.length !=2){
                    System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo composicao.csv");
                    System.exit(0);
                }else{
                    if(cont!=1){
                        System.out.println(itensLinha[0]);
                        int compId = 0; // ID do trem
                        int locomotivaId = 0; // ID da locomotiva
                        try {
                            compId = Integer.parseInt(itensLinha[0]); // ID do trem
                            locomotivaId = Integer.parseInt(itensLinha[1]); // ID da locomotiva
                        } catch (Exception e) {
                            System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada inválida no arquivo composicao.csv");
                            System.exit(0);
                        }
                        if(gc.verificaIdCarro(locomotivaId) == false){
                            System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada no campo 'locomotiva' inválida no arquivo composicao.csv");
                            System.exit(0);
                        }
                        if(patio.verificaIdTrem(compId) == true){
                            System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada no campo 'id' inválida no arquivo composicao.csv");
                            System.exit(0);
                        }
                        for (Carro c : gc.garagemCarro) {
                            if (c.getId() == locomotivaId) {
                                Trem t = new Trem(compId, (Locomotiva) c, gc);
                                patio.addTrem(t);
                                break;
                            }
                        }
                    }
                }
            }
            dataC.close();
        }catch(Exception e){
            System.out.println(e);
        }
   }
}