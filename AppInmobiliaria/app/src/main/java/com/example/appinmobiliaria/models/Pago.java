package com.example.appinmobiliaria.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Pago implements Serializable {
    private int id;
    private int numPago;
    private LocalDate fechaPago;
    private double importe;
    private String detalle;
    private Contrato contrato;
    private boolean estado;

    public Pago(int id, int numPago, LocalDate fechaPago, double importe, String detalle,
                Contrato contrato, boolean estado) {
        this.id = id;
        this.numPago = numPago;
        this.fechaPago = fechaPago;
        this.importe = importe;
        this.detalle = detalle;
        this.contrato = contrato;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPago() {
        return numPago;
    }

    public void setNumPago(int numPago) {
        this.numPago = numPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
