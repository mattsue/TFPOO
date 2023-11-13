package rocket.tfpoo;

/**
 * A classe Vagao representa um vagão que pode ser engatado em um trem.
 * Cada vagão possui um identificador, uma capacidade e pode ser engatado em um trem.
 *
 * @author 4gu1rr3 (Yasmin Aguirre)
 * @version 1.0
 */
public class Vagao extends Carro{
    private double capacidade;

    /**
     * Construtor da classe Vagao.
     *
     * @param id
     * @param capacidade
     */
    public Vagao(int id, double capacidade) {
        super(id);
        this.capacidade = capacidade;
    }

    /**
     * Obtém o identificador do vagão.
     *
     * @return O identificador do vagão.
     */
    @Override
    public int getId() {
        return super.getId();
    }

    /**
     * Obtém a capacidade do vagão.
     *
     * @return A capacidade do vagão.
     */
    public double getCapacidade() {
        return capacidade;
    }

    /**
     * Obtém o identificador do trem ao qual o vagão está engatado.
     *
     * @return O identificador do trem ao qual o vagão está engatado, ou 0 se não estiver engatado em nenhum trem.
     */
    @Override
    public int getIdTrem() {
        return super.getIdTrem();
    }

    /**
     * Define o identificador do trem ao qual o vagão está engatado.
     *
     * @param idTrem
     */
    @Override
    public void setIdTrem(int idTrem) {
        super.setIdTrem(idTrem);
    }

    /**
     * Retorna uma representação em formato de string do vagão.
     *
     * @return Uma string no formato "Vagão[v{id}, Capacidade = {capacidade}]".
     */
    @Override
    public String toString() {
        return super.toString() + "Vagão, Capacidade = " + capacidade + "]";
    }
}
