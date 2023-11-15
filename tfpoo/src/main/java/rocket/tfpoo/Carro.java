package rocket.tfpoo;

/**
 *
 * @author e.patricio
 */
public abstract class Carro {
    public int id;
    public int idTrem;

    public Carro(int id){
        this.id = id;
        this.idTrem = 0;
    }

    public int getId(){
        return id;
    }

    public int getIdTrem(){
        return idTrem;
    }

    public void setIdTrem(int idTrem) {
        this.idTrem = idTrem;
    }

    public String toString(){
        return "Carro [" + id + "]" + " (Tipo: ";
    }
}
