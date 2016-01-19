package TouristRoutes;

/**
 * Created by aluno on 1/19/16.
 */
public abstract class Transporte {
    //Terrestres , Aquaticos , Aereo
    private String tipo;

    public Transporte(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Transporte{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
