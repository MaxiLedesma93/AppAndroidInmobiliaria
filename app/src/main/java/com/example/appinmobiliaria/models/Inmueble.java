package com.example.appinmobiliaria.models;

import java.io.Serializable;

public class Inmueble implements Serializable {
    private int id;
    private String direccion;
    private int ambientes;
    private String uso;
    private Propietario duenio;
    private Tipo tipo;
    private int importe;
    private String imgUrl;
    private byte[] imagen;
    private boolean disponible;

    public Inmueble() {
    }

    public Inmueble(int id, String direccion, int ambientes, String uso, Propietario duenio,
                    Tipo tipo, int importe, String imgUrl, byte[] imagen, boolean disponible) {
        this.id = id;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.uso = uso;
        this.duenio = duenio;
        this.tipo = tipo;
        this.importe = importe;
        this.imgUrl = imgUrl;
        this.imagen = imagen;
        this.disponible = disponible;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
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


    public Tipo getTipo() {
        return tipo;
    }


    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
