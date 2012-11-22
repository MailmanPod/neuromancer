/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Fuyiko Kurosan
 */
public class Testing {

    private static int counter = 0;
    private static String name = "Neuromancer Class";
    private static String fileName = "D:\\Archivos\\Programacion\\Diseño de Lenguaje de Consulta (DLC)\\Clases\\"
            + "Semana 08 [Compresor]\\Semana 08 [Compresor]\\"
            + "DLC10-Compresor\\src\\compresor\\Compresor.java";
    private static String en = "D:\\Archivos\\Programacion\\Diseño de Lenguaje de Consulta (DLC)\\Clases\\"
            + "Semana 08 [Compresor]\\Semana 08 [Compresor]\\"
            + "DLC10-Compresor\\src\\compresor\\Compresor.crypto";

    private static String ter(double[] vectorTest) {
        StringBuilder builder = new StringBuilder();

        builder.append("\nEnter in ter method");

        for (int i = 0; i < vectorTest.length; i++) {
            builder.append(" Vector: ").append(i).append(" Content: ").append(vectorTest[i]);
        }

        counter += vectorTest.length;

        return builder.toString();
    }

    private static void encryption(String toEncrypt) throws FileNotFoundException, IOException {
        String nombre = fileName.substring(0, fileName.indexOf("."));

        // abro los archivos
        File f1 = new File(fileName);
        File f2 = new File(nombre + ".crypto");

        RandomAccessFile fuente = new RandomAccessFile(f1, "r");
        RandomAccessFile encriptado = new RandomAccessFile(f2, "rw");
        encriptado.setLength(0);

        byte[] content = new byte[(int) fuente.length()];
        int c = 0;

        while (fuente.getFilePointer() < fuente.length()) {
            byte car = fuente.readByte();
            content[c] = car;
            c++;
        }

        encriptado.writeUTF(toEncrypt);
        encriptado.writeLong(fuente.length());
        encriptado.write(content);

        fuente.close();
        encriptado.close();
    }

    private static String desencryption(String rsa) throws FileNotFoundException, IOException {
        // abro el archivo comprimido...
        File f1 = new File(en);
        RandomAccessFile encriptado = new RandomAccessFile(f1, "r");

        // ... y recupero el nombre del archivo original
        String password = encriptado.readUTF();
        
        long l = encriptado.readLong();

        // creo el archivo con el nombre del original
        File f2 = new File(fileName);
        if (f2.exists()) {
            f2.delete();
        }

        RandomAccessFile nuevo = new RandomAccessFile(f2, "rw");
        nuevo.setLength(0);

        byte[] content = new byte[(int) l];
        int c = 0;

        while (encriptado.getFilePointer() < encriptado.length()) {
            byte car = encriptado.readByte();
            content[c] = car;
            c++;
        }
        
        nuevo.write(content);
        
        nuevo.close();
        encriptado.close();
        
        return password;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        // TODO code application logic here

        RSAEncriptacion encriptacion = new RSAEncriptacion();
        RSAKeys keys = encriptacion.generarKeys();

        String password = "RSA Trola";

        String e = encriptacion.encriptar(password, keys.getModulus(), keys.getExponente());
        
        System.out.println("Encriptado: " + e);
        encryption(e);

        String encsas = desencryption(e); 
        String d = encriptacion.desencriptar(encsas, keys.getPrivateKey());
        
        System.out.println("Desecriptado: " + d);

        System.out.println();

        /*double[][] initial = { {1,2,3,4}, 
         {5,6,7,8}, 
         {9,10,11,12} };
        
         for(int i = 0; i < initial.length; i++){
         System.out.println(ter(initial[i]));
         }
         System.out.println("Amount of elements: " + counter);*/
    }
}
