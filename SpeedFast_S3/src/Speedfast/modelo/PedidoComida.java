package Speedfast.modelo;

/**
 * Reutiliza los atributos de {@link Pedido}
 */
public class PedidoComida extends Pedido {
    private int tiempoPreparacionMin;

    public PedidoComida(String codigo, String cliente, String direccion,
                        double distanciakm, int tiempoPreparacionMin) {
        super(codigo, cliente, direccion, distanciakm);
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    @Override
    public String tipoPedido() {
        return "Comida";
    }

    @Override
    public int calcularTiempoEntrega(){
        return tiempoPreparacionMin + (int) Math.ceil(distanciaKm * 3);
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Motorista de turno";
        this.estado = "ASIGNADO";
        registrarEvento("Repartidor en moto asignado:  " + repartidor);
    }
}
