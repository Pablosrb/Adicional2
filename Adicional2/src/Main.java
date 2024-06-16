import com.google.gson.Gson;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Component c = new Component(0,"",0);
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;

        do {

            System.out.println("1. Dar de alta un SparePart y components");
            System.out.println("2. Buscar una pieza");
            System.out.println("3. Generar JSON a .txt");
            System.out.println("4. Importar pieza en JSON");
            System.out.println("Cualquier otra cosa, salir.");
            int menu = Integer.parseInt(sc.nextLine());

            if (menu == 1){
                altaComponent(c);
            } else if (menu == 2) {
                buscarComp(c);
            } else if (menu == 3) {
                generarJSON(c);
            } else if (menu == 4) {
                cargarJSON(c);
            } else {
                System.out.println("Saliendo...");
                seguir = false;
            }

        } while (seguir);

    }

    private static void altaComponent(Component c){
        Scanner sc = new Scanner(System.in);
        
        try{

            int codigo;
            String text;
            double price;

            System.out.println("------------");
            System.out.println("Información");
            System.out.println("Codigo: ");
            codigo = Integer.parseInt(sc.nextLine());
            System.out.println("Introduce la descripcion: ");
            text = sc.nextLine();
            System.out.println("Precio:");
            price = Double.parseDouble(sc.nextLine());

            System.out.println("Sencilla o Compleja");
            String tipo = sc.nextLine();

            if (tipo.toUpperCase().equals("SENCILLA")){
                if (!c.addComponent(new SparePart(codigo,text,price)) ) {
                    System.out.println("El codigo ya existe");
                } else {
                    System.out.println("Componente sencillo añadido correctamente");
                }

//                System.out.println("Introduce el codigo de la pieza donde quieras añadirlo");
//                System.out.println("Si la pieza no está dentro de un component complejo pulse 0");
//                int cod = Integer.parseInt(sc.nextLine());
//                if (cod == 0) {
//
//
//                } else {
//
//
//
//                }


            } else if (tipo.toUpperCase().equals("COMPLEJA")){

                if (!c.addComponent(new Component(codigo,text,price)) ) {
                    System.out.println("El codigo ya existe");
                } else {
                    System.out.println("Componente complejo añadido correctamente");
                }


            }

        }catch (NumberFormatException e){
            System.out.println("No se ha introducido la información correctamente");
            System.out.println("Vuelve a intentarlo");
        }
    }

    private static void buscarComp(Component c){
        Scanner sc = new Scanner(System.in);

        try{
            int codigo;

            System.out.println("------------");
            System.out.println("Información");
            System.out.println("Codigo: ");
            codigo = Integer.parseInt(sc.nextLine());

            if (c.getMisComponent().get(codigo) != null) {
                System.out.println(c.getComponents(codigo));
            } else {
                System.out.println("No se ha encontrado la pieza");
            }

        }catch (NumberFormatException e){
            System.out.println("No se ha introducido la el codigo correctamente para realizar la búsqueda");
            System.out.println("El codigo es un numero, Vuelve a intentarlo");
        }

    }


    private static void generarJSON(Component c) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre del archivo donde guardar");
        String filename = sc.nextLine();

        Gson gson = new Gson();
        String datos = gson.toJson(c.getMisComponent());
        if (ControladorFicheros.writeText(filename, datos, true)) {
            System.out.println("Datos guardados en el archivo");
        } else {
            System.out.println("Error al guardar los datos.");
        }
    }


//    public static void cargarJSON() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Introduzca el fichero para cargar la cuenta");
//        String filename = sc.nextLine();
//        if (!filename.isBlank()){
//            String json = ControladorFicheros.readText(filename);
//            Gson gson = new Gson();
//            Component component = gson.fromJson(json, Component.class);
//
//
//        }
//    }

    public static void cargarJSON(Component c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el fichero para cargar los datos:");
        String filename = sc.nextLine();

        String json = ControladorFicheros.readText(filename);
        Gson gson = new Gson();
        Component importedComponent = gson.fromJson(json, Component.class);

        c.getMisComponent().putAll(importedComponent.getMisComponent());
        System.out.println("Datos importados correctamente.");
    }



}