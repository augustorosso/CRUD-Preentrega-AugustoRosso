package com.techlab.articulo.model;

//public(puede ser utilizada desde otros paquetes)
public class Categoria {

    //atributos, encapsulados, no se puede acceder de manera directa desde otra clase, sino desde los metodos getter y setter
    private int codigo;
    private String nombre;
    private String descripcion;

    // constructor de objeto, se ejecuta cuando se crea objeto con new
    //this es el atributo, del otro lado esta el parametro que se recibe
    public Categoria(int codigo, String nombre, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // getters, permiten consultar valor
    // setters, permiten modificar valor

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // override para sobreescribir metodo de clase padre object
    // toString para que muestre texto visualmente correcto
    // devuelve concatenacion de strings
    @Override
    public String toString() {
        return "Categoria {" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}