package rocket.tfpoo;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
                if(itensLinha.length !=3){
                    System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Número de colunas inválido no arquivo composicao.csv");
                    System.exit(0);
                }else{
                    if(cont!=1){
                        int compId = 0; // ID do trem
                        String[] locomotivas;
                        String[] vagoes;
                            compId = Integer.parseInt(itensLinha[0]); // ID do trem
                            locomotivas = itensLinha[1].split(";"); // IDs da locomotiva
                            vagoes = itensLinha[2].split(";");
                        for(String l: locomotivas){
                            if(gc.verificaIdCarro(Integer.parseInt(l)) == false){
                                System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada no campo 'locomotiva' inválida no arquivo composicao.csv");
                                System.exit(0);
                            }
                        }
                       for(String v: vagoes){
                            if(Integer.parseInt(v)>0){
                                if(gc.verificaIdCarro(Integer.parseInt(v)) == false){
                                    System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada no campo 'vagao' inválida no arquivo composicao.csv");
                                    System.exit(0);
                                }
                            }
                        }
                        if(patio.verificaIdTrem(compId) == true){
                            System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada no campo 'id' inválida no arquivo composicao.csv");
                            System.exit(0);
                        }
                        ArrayList<Carro> garagem = new ArrayList<Carro>();
                        for (Carro c : gc.garagemCarro) {
                            garagem.add(c);
                        }
                        Trem t = null;
                        for (Carro c : garagem) {
                            if (c.getId() == Integer.parseInt(locomotivas[0])) {
                                t = new Trem(compId, (Locomotiva) c, gc);
                                patio.addTrem(t);
                            }
                        }
                        if(locomotivas.length>1){
                            for (Carro c : garagem) {
                                for(int i = 1; i < locomotivas.length; i++){
                                    if (c.getId() == Integer.parseInt(locomotivas[i])) {
                                        t.engataLocomotiva((Locomotiva) c, gc);
                                    }
                                }
                            }
                        }
                        for (Carro c : garagem) {
                            for(String v : vagoes){
                                if (c.getId() == Integer.parseInt(v)) {
                                    t.engataVagao((Vagao) c, gc);
                                }
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

   public void reescreverLocomotiva() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("locomotiva.csv"))) {
            for (Carro carro : gc.garagemCarro) {
                if (carro instanceof Locomotiva) {
                    Locomotiva locomotiva = (Locomotiva) carro;
                    writer.write(locomotiva.getId() + "," + locomotiva.getMaxPeso() + "," + locomotiva.getMaxVagoes());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reescreverVagoes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vagoes.csv"))) {
            for (Carro carro : gc.garagemCarro) {
                if (carro instanceof Vagao) {
                    Vagao vagao = (Vagao) carro;
                    writer.write(vagao.getId() + "," + vagao.getCapacidade());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reescreverComposicao() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("composicao.csv"))) {
            writer.write("id,locomotivas,vagoes");
            writer.newLine();
    
            for (Trem t : patio.trens) {
                StringBuilder linha = new StringBuilder();
                linha.append(t.getId()).append(",");
    
                // Locomotivas
                for (int j = 0; j < t.getQuantLocomotivas(); j++) {
                    Locomotiva l = (Locomotiva) t.getCarroByPos(j);
                    linha.append(l.getId());
                    if (j < t.getQuantLocomotivas() - 1) {
                        linha.append(";");
                    }
                }
                if(t.getQuantVagoes()>0){
                    linha.append(",");
                }else{
                    linha.append(",-1");
                }
    
                // Vagoes
                for (int k = 0; k < t.getQuantVagoes(); k++) {
                    Vagao v = (Vagao) t.getCarroByPos(k + t.getQuantLocomotivas());
                    linha.append(v.getId());
                    if (k < t.getQuantVagoes() - 1) {
                        linha.append(";");
                    }
                }
    
                writer.write(linha.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo composicao.csv");
            e.printStackTrace();
        }
    }
    
    
    
}
