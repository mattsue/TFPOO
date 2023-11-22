package rocket.tfpoo;

import java.util.*;
public class Trem {
    private int id;
    private ArrayList<Carro> carros;
    private boolean vagaoEngatado;
    private double capacidadeTotalPeso = 0;
    private int capacidadeTotalVagoes = 0;

    /**
     * Construtor da classe Trem.
     * @param id
     * @param locomotiva
     * @param gc
     */
    public Trem(int id, Locomotiva locomotiva, GaragemCarros gc){
        this.id = id;
        this.carros = new ArrayList<Carro>();
        engataLocomotiva(locomotiva, gc);
    }

    public int getId() {
        return id;
    }
    
    public int getSize(){
        return carros.size();
    }

    public int getQuantLocomotivas() {
        int cont = 0;
        for(Carro carro : carros){
            if(carro instanceof Locomotiva){
                cont++;
            }
        }
        return cont;
    }

    public int getQuantVagoes() {
        int cont = 0;
        for(Carro carro : carros){
            if(carro instanceof Vagao){
                cont++;
            }
        }
        return cont;
    }

    public Carro getCarro(int id) {
        int index = 0;
        int posicao = 0;
        for(Carro c : carros){
            if(c.getId() == id){
                posicao = index;
            }
            index++;
        }
        return carros.get(posicao);
    }

    public Carro getCarroByPos(int posicao) {
        return carros.get(posicao);
    }
    
    public boolean engataLocomotiva(Locomotiva locomotiva, GaragemCarros gc){
        if (vagaoEngatado == false) {
            carros.add(locomotiva);
            locomotiva.setIdTrem(this.id);
            this.capacidadeTotalPeso += locomotiva.getMaxPeso();
            this.capacidadeTotalVagoes += locomotiva.getMaxVagoes();
            if(carros.size()>1){
                this.capacidadeTotalPeso = capacidadeTotalPeso * 0.9;
                this.capacidadeTotalVagoes = (int) (capacidadeTotalVagoes * 0.9);
            }
            gc.removeCarro(locomotiva);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean engataVagao(Vagao vagao, GaragemCarros gc) {
        if (this.capacidadeTotalPeso > vagao.getCapacidade() && this.capacidadeTotalVagoes > 0){
            carros.add(vagao);
            vagaoEngatado = true;
            vagao.setIdTrem(this.id);
            this.capacidadeTotalPeso -= vagao.getCapacidade();
            this.capacidadeTotalVagoes -= 1;
            gc.removeCarro(vagao);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean desengataLocomotiva(GaragemCarros gc){
        boolean resposta = false;
        if(vagaoEngatado == false){
            Locomotiva locomotiva = (Locomotiva) carros.get(carros.size()-1);
            locomotiva.setIdTrem(0);
            if(carros.size()>1){
                this.capacidadeTotalPeso -= locomotiva.getMaxPeso() * 0.9;
                this.capacidadeTotalVagoes -= locomotiva.getMaxVagoes() * 0.9;
            }else{
                this.capacidadeTotalPeso -= locomotiva.getMaxPeso();
                this.capacidadeTotalVagoes -= locomotiva.getMaxVagoes();
            }
            carros.remove(locomotiva);
            gc.addCarro(locomotiva);
            resposta = true;
        }
        return resposta;
    }
    
    public boolean desengataVagao(GaragemCarros gc){
        boolean resposta = false;
        if(vagaoEngatado){
            if(getQuantVagoes() == 1){
                vagaoEngatado = false;
            }
            Vagao vagao = (Vagao) carros.get(carros.size()-1);
            vagao.setIdTrem(0);
            this.capacidadeTotalPeso += vagao.getCapacidade();
            this.capacidadeTotalVagoes += 1;
            carros.remove(vagao);
            gc.addCarro(vagao);
            resposta = true;
        }
        return resposta;
    }

    public void desengataTudo(GaragemCarros gc){
        int sizeC = getSize();
        int sizeV = getQuantVagoes();
        for(int i = 0; i < sizeC; i++){
            if(i<sizeV){
                desengataVagao(gc);
            }
            else{
                desengataLocomotiva(gc);
            }
        }
    }

    @Override
    public String toString() {
        String retorno = "Trem "+ id + ": ";
        for(Carro carro : carros){
            if(carro instanceof Locomotiva){
                retorno += "L" + carro.getId() + " ";
            }else{
                retorno += "V" + carro.getId() + " ";
            }
        }
        return retorno;
    }
}