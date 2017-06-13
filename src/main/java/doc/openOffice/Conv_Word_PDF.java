package doc.openOffice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

//import org.artofsolving.jodconverter.OfficeDocumentConverter;
//import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
//import org.artofsolving.jodconverter.office.OfficeManager;

import com.sun.star.uno.Exception;

public class Conv_Word_PDF {
/*
    static OfficeDocumentConverter converter = null;
    public static OfficeManager officeManager = null;
*/

 /*   public static void main(String[] args) {

        try {
            initializeLibreOffice();

            //llamamos la metodo adecuado (1 o 2 parametros)
            if (args.length == 1){
                //1 parametro: se crea el PDF en la misma ruta donde esta el .doc
                if (args[0].contains("/") && args[0].endsWith(".doc")){
                    String sFichero = args[0];
                    File fichero = new File(sFichero);
                    if (fichero.exists()){
                        //a partir de la ruta de entrada dejamos el fichero de salida en la misma ruta
                        createPDFFile(args[0]);
                    }

                }
                else
                {
                    System.out.println("El párametro no es una ruta válida");
                }
            }
            else if (args.length == 2)
            {
                //2 parametros:urlEntrada y urlSalida
                if (args[0].contains("/") && args[0].endsWith(".doc") && args[1].contains("/") ){
                    String urlEntrada = args[0];
                    File fichero = new File(urlEntrada);
                    String urlSalida = args[1];
                    if (fichero.exists()){
                        //a partir de la ruta de entrada dejamos el fichero de salida en la misma ruta
                        createPDFFile(args[0],args[1]);
                    }

                }
                else
                {
                    //2 parametros:urlEntrada y true(se lo paso para distinguirlo del otro metodod de 2 parametros??)
                    String urlEntrada = args[0];
                    Boolean salidabytes = Boolean.parseBoolean(args[1]);
                    createPDFByte(args[0],args[1]);
                }

            }
            else if (args.length == 3)
            {
                if (args[0].contains("/") && args[0].endsWith(".doc") && args[1].contains("/") ){
                    String urlEntrada = args[0];
                    File fichero = new File(urlEntrada);
                    String urlSalida = args[1];
                    String strNombrePdf = args[2];
                    if (fichero.exists()){
                        //a partir de la ruta de entrada dejamos el fichero de salida en la misma ruta
                        createPDFFile(args[0],args[1],args[2]);
                    }

                }
            }
            else
            {
                System.out.println("El número de parametros no es correcto");
            }

            //cerramos el LibreOffice
            if (officeManager != null) {
                officeManager.stop();
            }


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }*/

    /**
     *
     * @param urlOrigen: ruta en la cual esta el archivo .doc
     * @param salidabytes
     * @return: devuelve un array de bytes del archivo .pdf
     */
    /*public static byte[] createPDFByte (String urlOrigen,String salidabytes){

        String urlDestino;
        byte[] buf = new byte[1024];
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {

            if (initializeLibreOffice()){

                //a partir de la urlOrigin, creo la urlDestino (mismo directorio)
                urlDestino = urlOrigen.replaceAll(".doc", ".pdf");
                //conversión a pdf
                converter.convert(new File(urlOrigen),new File(urlDestino));
                //fileinputstream
                fis = new FileInputStream(urlDestino);

                bos = new ByteArrayOutputStream();
                //leer bytes
                int byteleidos;
                while((byteleidos=fis.read(buf))!=-1){
                    bos.write(buf, 0, byteleidos);
                }

            }else{
                throw new Exception();
            }


        } catch (Throwable e) {
            e.printStackTrace();
        }finally{

            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //devuelvo el array de bytes del documento PDF
        System.out.println(Arrays.toString(bos.toByteArray()));
        return bos.toByteArray();

    }*/


    /**
     *
     * @param urlOrigin: ruta donde se encuentra el archivo .doc
     * @return : devuelve la misma ruta(archivo .pdf)
     */
   /* public static String createPDFFile (String urlOrigin) {

        String urlDestino = null;

        try {
            //1.- Init LibreOffice
            if (initializeLibreOffice()){
                //2.- call convert method
                //posición del último "/"
                int ultPosicion = 0;
                ultPosicion = urlOrigin.lastIndexOf("/");
                String nombrePDF = "";
                nombrePDF = urlOrigin.substring(ultPosicion + 1).replaceAll(".doc", ".pdf");

                urlDestino = urlOrigin.substring(0, ultPosicion + 1) + nombrePDF;
                //crea el PDF en la misma ruta que el archivo .doc
                converter.convert(new File(urlOrigin), new File(urlDestino));

            }else{
                throw new Exception();
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        //devuelve la ruta del pdf creado
        System.out.println(urlDestino);
        return urlDestino;
    }*/

    /**
     *
     * @param rutaEntrada: ruta donde se encuentra el archivo .doc
     * @param rutaSalida: ruta donde se deja el archivo .pdf
     * @return : devuelve la ruta en la cual esta el archivo .pdf
     */
    /*public static String createPDFFile (String rutaEntrada, String rutaSalida) {
        try {
            //1.- Init LibreOffice
            if (initializeLibreOffice()){
                //2.- call convert method
                int ultPosicion = 0;
                ultPosicion = rutaEntrada.lastIndexOf("/");
                String nombrePDF = "";
                nombrePDF = rutaEntrada.substring(ultPosicion + 1).replaceAll(".doc", ".pdf");
                //crea el PDF en la ruta que se le indique
                converter.convert(new File(rutaEntrada), new File(rutaSalida + "/" + nombrePDF));

            }else{
                throw new Exception();
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        //devuelve la ruta en la que se ha creado el PDF
        System.out.println(rutaSalida);
        return rutaSalida;
    }*/

    /**
     *
     * @param rutaEntrada: ruta donde se encuentra el archivo PDF
     * @param rutaSalida: ruta donde se deja el archivo PDF
     * @param nombrePDF: nombre que se le da al archivo PDF
     * @return
     */
   /* public static String createPDFFile (String rutaEntrada, String rutaSalida,String nombrePDF) {
        try {
            //1.- Init LibreOffice
            if (initializeLibreOffice()){
                //2.- call convert method
                //crea el PDF en la ruta que se le indique
                converter.convert(new File(rutaEntrada), new File(rutaSalida + "/" + nombrePDF + ".pdf"));

            }else{
                throw new Exception();
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        //devuelve el archivo PDF
        System.out.println(rutaSalida + "/" + nombrePDF + ".pdf");
        return rutaSalida + "/" + nombrePDF + ".pdf";
    }*/



    /*public static boolean initializeLibreOffice() {

        if (converter != null)
        {
            return true;
        }
        else
        {
            // 1) Start LibreOffice in headless mode.
            //OfficeManager officeManager = null;
            try {
                officeManager = new DefaultOfficeManagerConfiguration()
                        .setOfficeHome(new File("C:/Program Files (x86)/LibreOffice 3.5")).buildOfficeManager();
                officeManager.start();

                // 2) Create JODConverter converter
                converter = new OfficeDocumentConverter(officeManager);

            } finally {
                // 4) Stop LibreOffice in headless mode.
                //if (officeManager != null) {
                //	officeManager.stop();
                //	return false;
                //}
            }

            return true;
        }

    }*/

}