package com.example.appinmobiliaria.models;

import java.time.LocalDate;

public class Contrato {
    private int id;
    private LocalDate fecInicio;
    private LocalDate fecFin;
    private double monto;
    private boolean estado;
    private Inquilino inquilino;
    private Inmueble inmueble;



    public Contrato(int id, LocalDate fecInicio, LocalDate fecFin,
                    double monto, boolean estado, Inquilino inquilino, Inmueble inmueble) {
        this.id = id;
        this.fecInicio = fecInicio;
        this.fecFin = fecFin;
        this.monto = monto;
        this.estado = estado;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(LocalDate fecInicio) {
        this.fecInicio = fecInicio;
    }

    public LocalDate getFecFin() {
        return fecFin;
    }

    public void setFecFin(LocalDate fecFin) {
        this.fecFin = fecFin;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


}
