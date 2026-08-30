package Speedfast.modelo;

import java.util.ArrayList;
import java.util.List;

import Speedfast.interfaces.Cancelable;
import Speedfast.interfaces.Despachable;
import Speedfast.interfaces.Rastreable;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    protected String codigo;
    protected String cliente;
    protected String direccion;
    protected double distanciaKm;
    protected String repartidor;
    protected String estado;

    protected List<String> historial;

    public Pedido(String codigo, String cliente,String direccion, double distanciaKm) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.direccion = direccion;
        this.distanciaKm = distanciaKm;
        this.estado = "PENDIENTE";
        this.historial = new ArrayList<>();
        registrarEvento("Pedido creado (" + tipoPedido() + ")");
    }

    public abstract int calcularTiempoEntrega();

    public abstract String tipoPedido();

    public void asignarRepartidor() {
        this.repartidor = "Repartidor generico";
        this.estado = "ASIGNADO";
        registrarEvento("Repartidor asignado automaticamente: " +repartidor);
    }

    public void asignarRepartidor(String nombre) {
        this.repartidor = nombre;
        this.estado = "ASIGNADO";
        registrarEvento("Repartidor asignado manualmente: "+ nombre);
    }

    public void mostrarResumen() {
        System.out.println("----------------------------------------");
        System.out.println("  Resumen del pedido [" + tipoPedido() + "]");
        System.out.println("----------------------------------------");
        System.out.println("  Código      : " + codigo);
        System.out.println("  Cliente     : " + cliente);
        System.out.println("  Dirección   : " + direccion);
        System.out.println("  Distancia   : " + distanciaKm + " km");
        System.out.println("  Repartidor  : " + (repartidor == null ? "Sin asignar" : repartidor));
        System.out.println("  Estado      : " + estado);
        System.out.println("  Tiempo est. : " + calcularTiempoEntrega() + " min");
        System.out.println("----------------------------------------");
    }

    @Override
    public void despachar() {
        if ("CANCELADO".equals(estado)) {
            System.out.println("[!] No se puede despachar el pedido " + codigo + ": está CANCELADO.");
            return;
        }
        if (repartidor == null) {
            System.out.println("[!] No se puede despachar el pedido " + codigo + ": no tiene repartidor.");
            return;
        }
        this.estado = "DESPACHADO";
        registrarEvento("Pedido despachado con "+ repartidor);
        System.out.println("[>] Pedido " + codigo + " DESPACHADO. Llega en " +
        calcularTiempoEntrega() + " min aprox.");
    }

    @Override
    public void cancelar() {
        if ("DESPACHADO".equals(estado) || "ENTREGADO".equals(estado)) {
            System.out.println("[!] El pedido " + codigo + " ya fue " + estado.toLowerCase() +
                    ", no se puede cancelar.");
            return;
        }
        this.estado = "CANCELADO";
        registrarEvento("Pedido cancelado");
        System.out.println("[X] Pedido " + codigo + " CANCELADO.");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial del pedido " + codigo + ":");
        for (String evento : historial) {
            System.out.println("   -" + evento);
        }
    }

    protected void registrarEvento(String evento) {
        historial.add(evento);
    }

    public String getCodigo() { return codigo; }
    public String getEstado() { return estado; }
    public String getCliente() { return cliente; }


}

