package Speedfast.modelo;

/**
 * Reutiliza los atributos de {@link Pedido}
 */

public class PedidoExpress extends Pedido {
    private boolean prioridadMaxima;

    public PedidoExpress(String codigo, String cliente, String direccion,
                         double distanciaKm, boolean prioridadMaxima) {
        super(codigo, cliente, direccion, distanciaKm);
        this.prioridadMaxima = prioridadMaxima;
    }

    @Override
    public String tipoPedido() {
        return "Express";
    }

    @Override
    public int calcularTiempoEntrega() {
        double base = distanciaKm * 2;
        if (prioridadMaxima) {
            base = base * 0.8;
        }
        return Math.max(5, (int) Math.ceil(base));
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Repartidor express dedicado";
        this.estado = "ASIGNADO";
        registrarEvento("Repartidor express (prioritario) asignado:  "+ repartidor);
    }
}
