package Speedfast.servicio;

import java.util.ArrayList;
import java.util.List;

import Speedfast.modelo.Pedido;
import Speedfast.modelo.PedidoEncomienda;

public class ControladorDeEnvios {

    private List<Pedido> pedidosActivos;
    //Historial de entegas
    private List<Pedido> historialEntregas;

    public ControladorDeEnvios() {
        this.pedidosActivos = new ArrayList<>();
        this.historialEntregas = new ArrayList<>();
    }

    //Registra un Pedido
    public void registrarPedido(Pedido pedido) {
        pedidosActivos.add(pedido);
        System.out.println("[+] Pedido " + pedido.getCodigo() + " (" +
                pedido.tipoPedido() + ") registrando en el sistema");
    }

    /**
     * Despacha el Pedido Y lo agrega al historial de entregas realizadas
     */
    public void despacharPedido(Pedido pedido) {
        pedido.despachar();
        if ("DESPACHADO".equals(pedido.getEstado())) {
            historialEntregas.add(pedido);
        }
    }

    //Muestra el historial global de entregas realizadas
    public void verHistorialEntrega() {
        System.out.println("========================================");
        System.out.println("   HISTORIAL DE ENTREGAS REALIZADAS");
        System.out.println("========================================");
        if (historialEntregas.isEmpty()) {
            System.out.println("    (aun no hay entregas despachadas)");
        }  else {
            for (Pedido p : historialEntregas) {
                System.out.println("  * " + p.getCodigo() + " | " + p.tipoPedido() +
                        " | Cliente: " + p.getCliente() +
                        " | Estado: " + p.getEstado());
            }
        }
        System.out.println("========================================");
    }
}
