package rocket.tfpoo;

import java.util.*;
public class Patio {
    public ArrayList<Trem> trens = new ArrayList<Trem>();

    public Trem criaTrem(int tremId, Locomotiva locom, GaragemCarros gc) {
        Trem t = new Trem(tremId, locom, gc);
        trens.add(t);
        return t;
    }

    public void addTrem(Trem t){
        trens.add(t);
    }

    public void desfazTrem(int id, GaragemCarros gc){
        int count = 0;
        for(Trem t : trens){
            if (t.getId() == id){
                t.desengataTudo(gc);
                trens.remove(count);
                break;
            }
            count++;
        }
    }

    public boolean hasTrem(Trem t){
        return trens.contains(t);
    }

    public Trem getTrem(int idTrem) {
        for (Trem t : trens) {
            if (t.getId() == idTrem) {
                return t;
            }
        }
        return null;
    }

    public boolean verificaIdTrem(int id){
        for(Trem t: trens){
            if (t.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String retorno = "     Trens estacionados:\n";
        if(trens.size() <1){
            retorno += "- Nenhum";
        }
        for(Trem t : trens){
            retorno += "- " + t.toString() + "\n";
        }
        return retorno;
    }
}