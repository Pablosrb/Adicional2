
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ControladorFicheros {

    public static boolean writeText(String fileName, String data, boolean sobreescribe) {

        //Prueba de grabación en texto

        FileWriter fw = null;
        PrintWriter pw = null;
        boolean resultado = false;

        try {

            if (sobreescribe) { //true
                fw = new FileWriter(fileName);
            } else { //False
                fw = new FileWriter(fileName,true);
            }
            //fw = new FileWriter(fileName); //Abrimos el fichero, modo append false
            pw = new PrintWriter(fw); //Creamos el ayudante

            pw.print(data);

            pw.flush();

            fw.close();
            fw = null;
            resultado = true;

        } catch (IOException e) {

            System.out.println(e.getMessage());

        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }

            }
        }
        return resultado;



    }

    public static String readText(String fileName) {  //Ineficiente

        // lectura en texto

        String resultado = "";
        FileReader fr = null;
        Scanner sc = null;


        try {

            fr = new FileReader(fileName);
            sc = new Scanner(fr);

            while (sc.hasNext()) {

                resultado += sc.nextLine() + "\n";

            }
            fr.close();
            fr = null;


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
            }
        }
        return resultado;

    }

}
