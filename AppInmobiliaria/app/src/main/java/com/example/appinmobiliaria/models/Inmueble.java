package com.example.appinmobiliaria.models;

public class Inmueble {
    private int id;
    private String direccion;
    private int ambientes;
    private Uso uso;
    private Propietario duenio;
    private Tipo tipo;
    private int importe;
    private boolean disponible;



    public Inmueble(int id, String direccion, int ambientes, Uso uso, Propietario duenio, Tipo tipo,
                    int importe, boolean disponible) {
        this.id = id;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.uso = uso;
        this.duenio = duenio;
        this.tipo = tipo;
        this.importe = importe;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public Uso getUso() {
        return uso;
    }

    public void setUso(Uso uso) {
        this.uso = uso;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }


}
