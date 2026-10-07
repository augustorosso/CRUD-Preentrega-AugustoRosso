package com.techlab.articulo.model;

//public(puede ser utilizada desde otros paquetes)
//abstracta, funciona como base para otra clases
public abstract class Articulo{

    //atributos, encapsulados, no se puede acceder de manera directa desde otra clase, sino desde los metodos getter y setter
    private int codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;

    // constructor de objeto, se ejecuta cuando se crea objeto con new
    //this es el atributo, del otro lado esta el parametro que se recibe
    public Articulo(int codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    //metodo abstracto, las clases hijas pueden modificar segun necesidades, sino sigue siendo abstracta
    public abstract String getDetalleEspecifico();
    public abstract String getTipoArticulo();

    // override para sobreescribir metodo de clase padre object
    // toString para que muestre texto visualmente correcto
    // devuelve concatenacion de strings
    @Override
    public String toString() {
        return "Articulo {"+"codigo="+codigo+
        ", nombre='"+nombre+'\''+
        ", precio="+precio+ 
        //del objeto categoria traigo el nombre
        ", categoria='" + categoria.getNombre()+'\''+
        //va a devolver valor segun clase hijo
        ", tipo='" + this.getTipoArticulo()+'\''+
        //va a devolver valor segun clase hijo
        ", detalle='" + this.getDetalleEspecifico()+'\''+
        '}';
    }
}