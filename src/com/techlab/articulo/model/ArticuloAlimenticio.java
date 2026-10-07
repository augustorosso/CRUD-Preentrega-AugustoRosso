package com.techlab.articulo.model;

//herencia de clase padre articulo
public class ArticuloAlimenticio extends Articulo {

    // atributo propio
    private int diasParaVencimiento;

    // constructor
    public ArticuloAlimenticio(int codigo, String nombre, double precio, Categoria categoria, int diasParaVencimiento) {
        //super referencia a clase padre
        super(codigo, nombre, precio, categoria);
        //inicializacion atributo propio
        this.diasParaVencimiento = diasParaVencimiento;
    }

    //getter atributo propio para acceder
    public int getDiasParaVencimiento() {
        return diasParaVencimiento;
    }

    //setter atributo propio para modificar
    public void setDiasParaVencimiento(int diasParaVencimiento) {
        this.diasParaVencimiento = diasParaVencimiento;
    }

    // sobreescribo getter de clase padre
    @Override
    public String getTipoArticulo() {
        return "Alimenticio";
    }

    // sobreescribo getter de clase padre
    @Override
    public String getDetalleEspecifico() {
        return "Dias para vencimiento: " + diasParaVencimiento;
    }

    // sobreescribo funcion de clase padre, llama constructor padre y agrega subtipo
    @Override
    public String toString() {
        return super.toString() + " [subtipo alimenticio]";
    }
}