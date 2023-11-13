package rocket.tfpoo;
import java.util.*;
/**
 *
 * @author e.patricio
 */
public class Tfpoo {

     public static void iniciaCsv(Patio patio, GaragemCarros gc){
        try {
            Leitor leitorC = new Leitor("C");
            Leitor leitorL = new Leitor("L");
            Leitor leitorV = new Leitor("V");

            String[][] matrizC = leitorC.getMatriz();
            String[][] matrizL = leitorL.getMatriz();
            String[][] matrizV = leitorV.getMatriz();

            for (String[] linha : matrizL) {
                if (linha == matrizL[0]) continue; // Pula a primeira linha do arquivo
                int idL = 0;
                double maxPeso = 0.0;
                int maxVagoes = 0;
                try {
                    idL = Integer.parseInt(linha[0]);
                    maxPeso = Double.parseDouble(linha[1]);
                    maxVagoes = Integer.parseInt(linha[2]);
                } catch (Exception e) {
                    System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: Entrada inválida no arquivo locomotiva.csv");
                    System.exit(0);
                }
                Locomotiva locomotiva = new Locomotiva(idL, maxPeso, maxVagoes);
                gc.addCarro(locomotiva);
            }

            for (String[] linha : matrizV) {
                if (linha == matrizV[0]) continue; // Pula a primeira linha do arquivo
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

            for (String[] linha : matrizC) {
                if (linha == matrizC[0]) continue; // Pula a primeira linha do arquivo
                int compId = 0; // ID do trem
                int locomotivaId = 0; // ID da locomotiva
                try {
                    compId = Integer.parseInt(linha[0]); // ID do trem
                    locomotivaId = Integer.parseInt(linha[1]); // ID da locomotiva
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
        } catch (Exception e) {
            System.out.println("Erro encontrado durante a leitura dos arquivos!! \nError: " + e.getMessage());
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Patio patio = new Patio();
        Scanner scanner = new Scanner(System.in);
        GaragemCarros gc = new GaragemCarros();
        int escolhaMenu = -1;
        int tremId, id;
        iniciaCsv(patio, gc);
        System.out.println("Bem-vindo ao sistema de trens =)");

        do{
            System.out.println("\n");
            System.out.println("         Menu         ");
            System.out.println("   1. Criar trem      ");
            System.out.println("   2. Editar trem     ");
            System.out.println("   3. Listar trens    ");
            System.out.println("   4. Desfazer trem   ");
            System.out.println("   5. Sair do programa");
            System.out.println("\n");
            System.out.print("Digite o número da opção desejada: ");
            escolhaMenu = scanner.nextInt();
            while(escolhaMenu < 1 || escolhaMenu > 5){
                System.out.println("Opção inválida. Digite novamente: ");
            }
            if(escolhaMenu == 1){
                System.out.println("Qual o identificador do trem? ");
                tremId = scanner.nextInt();
                while(patio.verificaIdTrem(tremId) == true){
                    System.out.println("Já existe um trem com esse identificador. Digite novamente: ");
                    tremId = scanner.nextInt();
                }
                System.out.println("Qual o identificador da primeira locomotiva? ");
                id = scanner.nextInt();
                while(gc.verificaIdCarro(id) == false || (gc.getCarro(id)) instanceof Vagao){
                    System.out.println("Essa locomotiva não está disponível. Digite novamente: ");
                    id = scanner.nextInt();
                }
                Locomotiva locoadd = (Locomotiva) gc.getCarro(id);
                patio.criaTrem(tremId,locoadd,gc);
                System.out.println("Trem criado com sucesso! =)");
            }
            if(escolhaMenu == 2){
                do{
                    System.out.println("\n");
                    System.out.println("          Menu de Opções            ");
                    System.out.println("   1. Inserir uma locomotiva      ");
                    System.out.println("   2. Inserir um vagão     ");
                    System.out.println("   3. Remover o último elemento   ");
                    System.out.println("   4. Listar locomotivas livres   ");
                    System.out.println("   5. Listar vagões livres   ");
                    System.out.println("   6. Voltar");
                    System.out.println("\n");
                    System.out.print("Digite o número da opção desejada: ");
                    escolhaMenu = scanner.nextInt();
                    while(escolhaMenu < 1 || escolhaMenu > 6){
                        System.out.println("Opção inválida. Digite novamente: ");
                    }
                    if(escolhaMenu == 1){
                        System.out.println("Qual o identificador do trem em que deseja adicionar? ");
                        tremId = scanner.nextInt();
                        while(patio.verificaIdTrem(tremId) == false){
                            System.out.println("Não existe um trem com esse identificador. Digite novamente: ");
                            tremId = scanner.nextInt();
                        }
                        System.out.println("Qual o identificador da locomotiva? ");
                        id = scanner.nextInt();
                        while(gc.verificaIdCarro(id) == false || (gc.getCarro(id)) instanceof Vagao){
                            System.out.println("Essa locomotiva não está disponível. Digite novamente: ");
                            id = scanner.nextInt();
                        }
                        Locomotiva locoadd = (Locomotiva) gc.getCarro(id);
                        Trem tremadd = patio.getTrem(tremId);
                        boolean engatado = tremadd.engataCarro(locoadd,gc);
                        if(engatado == true) {
                            System.out.println("Locomotiva adicionada com sucesso! =)");
                        }
                        else {
                            System.out.println("Não foi possível adicionar a locomotiva. Já existe um vagão engatado =(");
                        }
                    }
                    if(escolhaMenu == 2){
                        System.out.println("Qual o identificador do trem em que deseja adicionar? ");
                        tremId = scanner.nextInt();
                        while(patio.verificaIdTrem(tremId) == false){
                            System.out.println("Não existe um trem com esse identificador. Digite novamente: ");
                            tremId = scanner.nextInt();
                        }
                        System.out.println("Qual o identificador do vagão? ");
                        id = scanner.nextInt();
                        while(gc.verificaIdCarro(id) == false || (gc.getCarro(id)) instanceof Locomotiva){
                            System.out.println("Esse vagão não está disponível. Digite novamente: ");
                            id = scanner.nextInt();
                        }
                        Vagao vagaoAdd = (Vagao) gc.getCarro(id);
                        Trem tremadd = patio.getTrem(tremId);
                        boolean engatado = tremadd.engataCarro(vagaoAdd, gc);
                        if(engatado == true) {
                            System.out.println("Vagão adicionado com sucesso! =)");
                        }
                        else {
                            System.out.println("Não foi possível adicionar o vagão. Capacidade máxima do trem excedida! =(");
                        }
                    }
                    if(escolhaMenu == 3){
                        System.out.println("Qual o identificador do trem que deseja editar? ");
                        tremId = scanner.nextInt();
                        while(patio.verificaIdTrem(tremId) == false){
                            System.out.println("Não existe um trem com esse identificador. Digite novamente: ");
                            tremId = scanner.nextInt();
                        }
                        Trem tremadd = patio.getTrem(tremId);
                        if(tremadd.getQuantLocomotiva()<2){
                            System.out.println("Não é possível retirar o último elemento do trem. =(");
                        }
                        else{
                            tremadd.desengataCarro(gc);
                            System.out.println("Elemento removido com sucesso! =)");
                        }
                    }
                    if (escolhaMenu == 4) {
                        System.out.println("Locomotivas disponíveis:");
                        System.out.println(gc.toString(1));
                        System.out.println("6. Voltar");
                        scanner.next();
                        scanner.nextLine();

                    }
                    if (escolhaMenu == 5) {
                        System.out.println("Vagoes disponíveis:");
                        System.out.println(gc.toString(2));
                        System.out.println("6. Voltar");
                        scanner.next();
                        scanner.nextLine();
                    }
                }while(escolhaMenu != 6);
            }
            if(escolhaMenu == 3){
                for(Trem t : patio.trens){
                    System.out.println(t.toString());
                }
                System.out.println("6. Voltar");
                scanner.next();
                scanner.nextLine();
            }
            if (escolhaMenu == 4){
                System.out.println("Qual o identificador do trem que será removido? ");
                tremId = scanner.nextInt();
                while(patio.verificaIdTrem(tremId) == false){
                    System.out.println("Não existe um trem com esse identificador. Digite novamente: ");
                    tremId = scanner.nextInt();
                }
                patio.desfazTrem(tremId,gc);
                System.out.println("Trem removido com sucesso! =)");
            }
        }while(escolhaMenu != 5);
        System.out.println("Até a próxima =)");
        scanner.close();
    }
}
