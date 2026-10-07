// direccion paquete principal
package com.techlab.articulo;

// importacion herramientas
import java.util.ArrayList;
import java.util.Scanner;

// importacion clases
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.ArticuloAlimenticio;

// clase controladora app
public class App {
    //metodo para ejecutar programa
    public static void main(String[] args) {
        //leer info que ingresa usuario
        Scanner scanner = new Scanner(System.in);

        // lista en memoria RAM
        ArrayList<Articulo> articulos = new ArrayList<>();

        // lista en memoria RAM
        ArrayList<Categoria> categorias = new ArrayList<>();
        precargarCategorias(categorias);

        // variable para almacenar eleccion de usuario
        int opcion;

        // bucle do while, ejecuta al menos una vez
        do {
            System.out.println("MENÚ");
            System.out.println("1 - Ingresar articulo");
            System.out.println("2 - Listar articulos");
            System.out.println("3 - Consultar un articulo");
            System.out.println("4 - Modificar un articulo");
            System.out.println("5 - Eliminar un articulo");
            System.out.println("6 - Listar categorias");
            System.out.println("0 - Salir");

            // pedir opcion
            opcion = leerEntero(scanner, "Ingrese una opcion: ");

            // switch para analizar y buscar que case corresponde, break para terminar el switch
            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos, categorias);
                    break;
                case 2:
                    listarArticulos(articulos);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos, categorias);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 6:
                    listarCategorias(categorias);
                    break;   
                case 0:
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);

        scanner.close();
    }


    //pasando lista al metodo, void(no devuelve valor)
    public static void precargarCategorias(ArrayList<Categoria> categorias) {
        // crea nuevos objetos y se agregan al ArrayList
        categorias.add(new Categoria(1, "Electronica", "Productos tecnologicos y electronicos"));
        categorias.add(new Categoria(2, "Perifericos", "Accesorios para computadora"));
        categorias.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Articulos de limpieza del hogar"));
    }

    //lista donde se van a guardar los articulos, y lista de categorias disponibles, void(no devuelve valor)
    public static void ingresarArticulo(
        Scanner scanner, 
        ArrayList<Articulo> articulos, 
        ArrayList<Categoria> categorias) {
        System.out.println("--INGRESAR ARTICULO--");
        System.out.println("1 - Articulo electronico");
        System.out.println("2 - Articulo alimenticio");

        // variable tipo para guardar eleccion
        int tipo;


        //validacion para ingresar valor 1 o 2
        do {
            tipo = leerEntero(scanner, "Seleccione el tipo de articulo: ");

            if (tipo != 1 && tipo != 2) {
                System.out.println("Error: debe elegir 1 o 2");
            }
        } while (tipo != 1 && tipo != 2);

        int codigo = leerEnteroNoNegativo(scanner, "Seleccione el codigo de articulo: ");

        //validacion existencia codigo
        if (buscarArticuloPorCodigo(articulos, codigo) != null) {
            System.out.println("Ya existe un articulo con ese codigo.");
            return;
        }

        //pedido usuario, validaciones
        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del articulo: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio del articulo: ");

        // muestra lista donde fueron precargadas las categorias
        listarCategorias(categorias);
        // seleccion categoria
        Categoria categoria = pedirCategoriaExistente(scanner, categorias);

        // declaro variable sin asignar objeto(polimorfismo)
        Articulo articulo;

        // tipo de articulo, hereda de padre articulo
        if (tipo == 1) {
            int garantiaMeses = leerEnteroNoNegativo(scanner, "Ingrese la garantia en meses: ");
            articulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
        } else {
            int diasParaVencimiento = leerEnteroNoNegativo(scanner, "Ingrese los dias para vencimiento: ");
            articulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, diasParaVencimiento);
        }

        //se agrega el articulo
        articulos.add(articulo);

        System.out.println("Articulo ingresado correctamente");
        System.out.println("Resumen del objeto creado:");
        System.out.println(articulo);
    }

    // metodo para recorrer lista y mostrar, void(no devuelve valor)
    public static void listarArticulos(ArrayList<Articulo> articulos) {
        System.out.println("--LISTADO DE ARTICULOS--");

        // si la lista esta vacia
        if (articulos.isEmpty()) {
            System.out.println("No hay articulos cargados");
            //termina metodo, no llega al for
            return;
        }

        //toma cada objeto que esta dentro de articulos
        for (Articulo articulo : articulos) {
            //muestra articulo, puede variar segun objetos hijos
            System.out.println(articulo);
            // si es un articulo electronico
            if (articulo instanceof ArticuloElectronico) {}
        }
    }

    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        System.out.println("--CONSULTAR ARTICULO--");

        // si la lista esta vacia
        if (articulos.isEmpty()) {
            System.out.println("No hay articulos cargados");
            //termina metodo
            return;
        }

        //pedir codigo
        int codigo = leerEntero(scanner, "Ingrese el codigo del articulo a consultar: ");
        //polimorfismo(puede ser electronico o alimenticio), metodo busca en lista el codigo ingresado
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        // si no existe(nulo)
        if (articulo == null) {
            System.out.println("El articulo no existe.");
            //terminar metodo
            return;
        }

        System.out.println("Articulo encontrado:");
        System.out.println(articulo);
        //polimorfismo, puede variar segun objeto hijo
        System.out.println("Detalle especifico: " + articulo.getDetalleEspecifico());
    }

    //recibe lista de articulos y de categorias, void(no devuelve valor)
    public static void modificarArticulo(
        Scanner scanner, 
        ArrayList<Articulo> articulos, 
        ArrayList<Categoria> categorias) {
        System.out.println("--MODIFICAR ARTICULO--");

        //si lista esta vacia
        if (articulos.isEmpty()) {
            System.out.println("No hay articulos cargados");
            //termina metodo
            return;
        }

        //pedir codigo, validando
        int codigo = leerEntero(scanner, "Ingrese el codigo del articulo a modificar: ");
        //metodo para buscar codigo de articulo en lista
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        //si no encuentra(nulo)
        if (articulo == null) {
            System.out.println("El articulo no existe.");
            //termina metodo
            return;
        }

        //variable nuevos objetos, con validacion
        String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del articulo: ");
        double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese el nuevo precio del articulo: ");

        // lista categorias
        listarCategorias(categorias);
        // pedir nueva categoria
        Categoria nuevaCategoria = pedirCategoriaExistente(scanner, categorias);

        // articulo.setX -> articulo.X = nuevoX; se usa setter ya que es privado
        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoria);

        // si es electronico, valor especifico de objeto hijo
        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;

            int nuevaGarantia = leerEnteroNoNegativo(scanner, "Ingrese la nueva garantia en meses: ");
            electronico.setGarantiaMeses(nuevaGarantia);
        }

        // si es alimenticio, valor especifico de objeto hijo
        if (articulo instanceof ArticuloAlimenticio) {
            ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;

            int nuevosDias = leerEnteroNoNegativo(scanner, "Ingrese los nuevos dias para vencimiento: ");
            alimenticio.setDiasParaVencimiento(nuevosDias);
        }

        //luego de modificar
        System.out.println("Articulo modificado correctamente.");
    }

    //pedido usuario, lista articulos, void(no devuelve valor)
    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        System.out.println("--ELIMINAR ARTICULO--");

        // si lista esta vacia
        if (articulos.isEmpty()) {
            System.out.println("No hay articulos cargados");
            //termina metodo
            return;
        }

        //pedido codigo
        int codigo = leerEntero(scanner, "Ingrese el codigo del articulo a eliminar: ");
        //metodo para buscar ingresado en lista
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);

        // si es nulo(no lo encuentra)
        if (articulo == null) {
            System.out.println("El articulo no existe.");
            //termina metodo
            return;
        }

        // si encuentra, elimina con remove de lista
        articulos.remove(articulo);
        System.out.println("Articulo eliminado correctamente.");
    }


    //metodo para recorrer lista de categoria y mostrar en pantalla
    public static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("\n--- CATEGORIAS DISPONIBLES ---");

        //for-each, por cada categoria en categorias, guardar variable en categoria
        for (Categoria categoria : categorias) {
            //mostrar info, categoria es un objeto, en clase categoria hago toString
            System.out.println(categoria);
        }
    }

    //metodo para pedir un codigo de categoria, no es void ya que va a devolver el objeto categoria
    public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) {
        // repetir hasta que haya valor valido
        while (true) {
            //pedir codigo, con validacion
            int codigoCategoria = leerEntero(scanner, "Ingrese el codigo de la categoria: ");
            //metodo para buscar la categoria
            Categoria categoria = buscarCategoriaPorCodigo(categorias, codigoCategoria);
            //si encuentra(distinto de nulo)
            if (categoria != null) {
                // devuelve objeto
                return categoria;
            }
            //no entra al if, imprime mensaje
            System.out.println("Error: la categoria no existe");
        }
    }

    //metodo, devuelve objeto, no es void, recibe codigo y busca en lista articulos
    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {
        //for-each, por cada articulo en articulos, llama temporalmente articulo
        for (Articulo articulo : articulos) {
            //si el codigo de lista que se trae con el getter es igual al codigo
            if (articulo.getCodigo() == codigo) {
                //devuelve objeto y termina metodo
                return articulo;
            }
        }
        //si recorre lista y no encuentra devuelve nulo, termina metodo
        return null;
    }

    // similar a metodo arriba, devuelve objeto, recibe codigo y busca en lista categorias
    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        //for-each, por cada categoria en categorias, llama temporalmente categoria
        for (Categoria categoria : categorias) {
            //si el codigo de lista que se trae con el getter es igual al codigo
            if (categoria.getCodigo() == codigo) {
                //devuelve objeto y termina metodo
                return categoria;
            }
        }
        //si recorre lista y no encuentra devuelve nulo, termina metodo
        return null;
    }

    //metodo devuelve entero, recibe valor ingresado y mensaje a mostrar
    public static int leerEntero(Scanner scanner, String mensaje) {
        // repetir hasta que haya valor valido
        while (true) {
            //intentar ejecutar
            try {
                //imprimir mensaje
                System.out.print(mensaje);
                //conversion de ingresado a tipo int
                return Integer.parseInt(scanner.nextLine());
                //si hay error, catch, tipo de error guardado como e, vuelve a intentar
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }

    // devuelve entero
    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        // repetir hasta que haya valor valido
        while (true) {
            //reutiliza metodo
            int valor = leerEntero(scanner, mensaje);

            //si es menor a 0
            if (valor < 0) {
                System.out.println("Error: el valor no puede ser negativo");
                //saltar resto de vuelta y empezar devuelta el while
                continue;
            }
            //devuelve entero y termina metodo
            return valor;
        }
    }

    //devuelve texto
    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        // repetir hasta que haya valor valido
        while (true) {
            //mostrar mensaje y guardar valor ingresado
            System.out.print(mensaje);
            String texto = scanner.nextLine();

            //si el valor sin espacios NO está vacio
            if (!texto.trim().isEmpty()) {
                //devuelve valor sin espacios
                return texto.trim();
            }

            //fuera del if, imprime mensaje    
            System.out.println("El texto no puede estar vacio.");
        }
    }

    //devuelve double
    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        // repetir hasta que haya valor valido
        while (true) {
            //intentar ejecutar
            try {
                //imprimir mensaje, valor ingresado pasar a double guardado
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());
                // si es mayor o igual a 0
                if (valor >= 0) {
                    //devolver valor
                    return valor;
                }
                //si no imprimir mensaje
                System.out.println("El valor no puede ser negativo.");
                // si da error catch
            } catch (NumberFormatException e) {
                //imprimir mensaje
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }
}