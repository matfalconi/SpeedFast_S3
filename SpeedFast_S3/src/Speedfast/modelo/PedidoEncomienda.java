package Speedfast.modelo;


/**
 * Reutiliza los atributos de {@link Pedido}
 */

public class PedidoEncomienda extends Pedido {

    private double pesoKg;

    public PedidoEncomienda(String codigo, String cliente, String direccion,
                            double distanciaKm, double pesoKg) {
        super (codigo, cliente, direccion, distanciaKm);
        this.pesoKg = pesoKg;
    }

    @Override
    public String tipoPedido(){
        return "Encomienda";
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.ceil(distanciaKm * 4) + (int) Math.ceil(pesoKg * 2);
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Conductor de camioneta";
        this.estado = "ASIGNADO";
        registrarEvento("Repartidor con camioneta: " + repartidor);
    }
}
