package com.example.appinmobiliaria.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Contrato implements Serializable {
    private int id;
    private String fecInicio;
    private String fecFin;
    private double monto;
    private boolean estado;
    private Inquilino inquilino;
    private Inmueble inmueble;



    public Contrato(int id, String fecInicio, String fecFin,
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

    public String getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(String fecInicio) {
        this.fecInicio = fecInicio;
    }

    public String getFecFin() {
        return fecFin;
    }

    public void setFecFin(String fecFin) {
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
