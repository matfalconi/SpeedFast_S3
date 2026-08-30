package Speedfast;

import Speedfast.modelo.Pedido;
import Speedfast.modelo.PedidoComida;
import Speedfast.modelo.PedidoEncomienda;
import Speedfast.modelo.PedidoExpress;
import Speedfast.servicio.ControladorDeEnvios;

/**
 * Simulacion de entrega SpeedFast
 *
 * Demuestra requisitos solicitados
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("############################################");
        System.out.println("#     SPEEDFAST - SISTEMA DE ENTREGAS      #");
        System.out.println("############################################\n");

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        // --- Creacion de pedido de distintos tipo
        Pedido comida      = new PedidoComida("CMD-001", "ANA Pérez", "Av. Siempre Viva 742", 4.0, 20);
        Pedido encomienda  = new PedidoEncomienda("ENC-002", "Luis Rojas", "Calle Las Rosas 15", 8.5, 6.0);
        Pedido express     = new PedidoExpress("EXP-003", "Marta Diaz", "Pje. Los Aromos 3", 5.0, true);

        controlador.registrarPedido(comida);
        controlador.registrarPedido(encomienda);
        controlador.registrarPedido(express);

        System.out.println("\n===== 1. ASIGNACIÓN DE REPARTIDORES =====");

        comida.asignarRepartidor();
        encomienda.asignarRepartidor();

        express.asignarRepartidor("Carlos Soto");

        System.out.println("\n===== 2. RESUMEN Y TIEMPO ESTIMADO =====");
        comida.mostrarResumen();
        encomienda.mostrarResumen();
        express.mostrarResumen();

        System.out.println("\n===== 3. DESPACHO DE PEDIDOS =====");
        controlador.despacharPedido(comida);
        controlador.despacharPedido(express);

        System.out.println("\n===== 4. CANCELACIÓN DE UN PEDIDO =====");
        encomienda.cancelar();
        controlador.despacharPedido(encomienda);

        System.out.println("\n===== 5. HISTORIAL POR PEDIDO (Rastreable) =====");
        comida.verHistorial();
        encomienda.verHistorial();
        express.verHistorial();

        System.out.println();
        controlador.verHistorialEntrega();

        System.out.println("\nSimulacíon finalizada correctamente");

    }
}
