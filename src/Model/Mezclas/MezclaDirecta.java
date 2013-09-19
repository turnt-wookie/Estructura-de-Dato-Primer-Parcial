package Model.Mezclas;

import jxl.Cell;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Juan Pablo
 * Date: 16/09/13
 * Time: 03:34 PM
 */
public class MezclaDirecta {

    private String archivoPrincipal;

    /**
     * Algroitmo de ordenamiento
     *
     * @param  A  Informacion de la primer columna
     * @param  B  Informacion de la Segunda columna
     * @param  C  Informacion de la tercer columna
     * @param  Folder  ruta del archivo
     * @param  F  archivo
     * @param  F1  archivo 1
     * @param  F2  archivo  2
     * @param  Opcion  Ordenamiento mayor o menor
     * @throws IOException 
     * @throws WriteException  
     */
    public MezclaDirecta(Cell A[],Cell B[],Cell C[], String Folder, String F, String F1, String F2, int Opcion) throws IOException, WriteException {
        int n=0, part=1;
        ArrayList remover = new ArrayList();

        archivoPrincipal = F = Folder + F;
        F1 = Folder + F1;
        F2 = Folder + F2;


        Writefile test = new Writefile();
        test.setOutputFile(F);
        test.generate();



        for (int j=1; j<A.length; j++){
            test.addText(0, j, A[j].getContents());
        }
        for (int j=1; j<B.length; j++){
            test.addText(1, j, B[j].getContents());
        }
        for (int j=1; j<C.length; j++){
            if (!C[j].getContents().contains("S")){

                //Borrar lineas que los datos no funcionen

                test.addNumber(2, j, Double.parseDouble(C[j].getContents().replace(",","")));

            }else{
                remover.add(j);
                test.addText(2, j, C[j].getContents());

            }
            n = j;
        }




        test.removeRow(0);


        test.write();
        test.close();


        while(part<n){
            particiona(F, F1, F2, part);
            switch (Opcion) {
                case 1:
                    fusiona(F, F1, F2, part);
                    break;
                case 2:
                    fusionaM(F, F1, F2, part);
                    break;
                default:
                    fusiona(F, F1, F2, part);
                    break;

            }

            part*=2;
        }



    }


    /**
     * Particiona los datos en dos archivos
     *
     *
     * @param  F  archivo
     * @param  F1  archivo 1
     * @param  F2  archivo  2
     * @param part  
     */
    public void particiona(String F, String F1, String F2, int part){
        System.out.println("Particionando");
        int k, l;
        String[] r;

        Readfile archivoF = new Readfile(F);
        Writefile archivoF1 = new Writefile();
        Writefile archivoF2 = new Writefile();



        try {
            archivoF2.setOutputFile(F2);
            archivoF2.generate();


            archivoF1.setOutputFile(F1);
            archivoF1.generate();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (WriteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        while (archivoF.hasNextRow()){

            k=0;
            while(k<part && archivoF.hasNextRow()){
                r = archivoF.nextRow();
                try {
                    archivoF1.addNewRow(r);
                } catch (WriteException e) {
                    System.out.println("error al crear la nueva linea");
                }

                k++;
            }
            l=0;
            while(l<part && archivoF.hasNextRow()){
                r = archivoF.nextRow();
                try {
                    archivoF2.addNewRow(r);
                } catch (WriteException e) {
                    System.out.println("error al crear la nueva fila");
                }
                l++;
            }
        }
        archivoF.close();
        try {
            archivoF1.close();
            archivoF2.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (WriteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }


    /**
     * Algroitmo de ordenamiento de menor a mayor
     *
     *
     * @param  F  archivo
     * @param  F1  archivo 1
     * @param  F2  archivo  2
     * @param part
     * @throws WriteException  
     */
    public void fusiona(String F, String F1, String F2, int part) throws WriteException {
        System.out.println("Fucionando");
        int k,l;
        String [] r1={}, r2={};
        boolean b1,b2;

        Writefile archivoF =new Writefile();
        archivoF.setOutputFile(F);
        try {
            archivoF.generate();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Readfile archivoF1=new Readfile(F1);
        Readfile archivoF2=new Readfile(F2);


        {

            b1=true;
            b2=true;

            if(archivoF1.hasNextRow()){
                r1 = archivoF1.nextRow();
                b1=false;
            }

            if(archivoF2.hasNextRow()){
                r2 = archivoF2.nextRow();
                b2 = false;
            }

            while((archivoF1.hasNextRow()||!b1)&&(archivoF2.hasNextRow()||!b2)){

                k = 0;
                l = 0;

                while(((k<part) && (b1==false))&&((l<part)&&(b2==false))){

                    if(Double.parseDouble(r1[2])<= Double.parseDouble(r2[2])){  //Convertir a Double antes de chingar
                        archivoF.addNewRow(r1);
                        b1=true;
                        k++;
                        if(archivoF1.hasNextRow()){
                            r1 = archivoF1.nextRow();
                            b1=false;
                        }
                    }
                    else{
                        archivoF.addNewRow(r2);
                        b2=true;
                        l++;
                        if(archivoF2.hasNextRow()){
                            r2 = archivoF2.nextRow();
                            b2=false;
                        }

                    }

                }

                if(k<part){

                    while((k<part)&&(b1==false)){
                        archivoF.addNewRow(r1);
                        b1=true;
                        k++;
                        if(archivoF1.hasNextRow()){
                            r1 = archivoF1.nextRow();
                            b1=false;
                        }
                    }
                }
                if(l<part){

                    while((l<part)&&(b2==false)){
                        archivoF.addNewRow(r2);
                        b2=true;
                        l++;
                        if(archivoF2.hasNextRow()){
                            r2 = archivoF2.nextRow();
                            b2=false;
                        }
                    }
                }

            }
            if(b1==false){
                archivoF.addNewRow(r1);
            }
            if(b2==false){
                archivoF.addNewRow(r2);
            }
            while(archivoF1.hasNextRow()){
                r1 = archivoF1.nextRow();
                archivoF.addNewRow(r1);
            }
            while(archivoF2.hasNextRow()){
                r2 = archivoF2.nextRow();
                archivoF.addNewRow(r2);
            }

            archivoF1.close();
            archivoF2.close();
            try {
                archivoF.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }


        }
    }


    /**
     * Algroitmo de ordenamiento de mayor a menor
     *
     *
     * @param  F  archivo
     * @param  F1  archivo 1
     * @param  F2  archivo  2
     * @param part 
     * @throws WriteException  
     */
    public void fusionaM(String F, String F1, String F2, int part) throws WriteException {
        System.out.println("Fucionando");
        int k,l;
        String [] r1={}, r2={};
        boolean b1,b2;

        Writefile archivoF =new Writefile();
        archivoF.setOutputFile(F);
        try {
            archivoF.generate();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Readfile archivoF1=new Readfile(F1);
        Readfile archivoF2=new Readfile(F2);


        {

            b1=true;
            b2=true;

            if(archivoF1.hasNextRow()){
                r1 = archivoF1.nextRow();
                b1=false;
            }

            if(archivoF2.hasNextRow()){
                r2 = archivoF2.nextRow();
                b2 = false;
            }

            while((archivoF1.hasNextRow()||!b1)&&(archivoF2.hasNextRow()||!b2)){

                k = 0;
                l = 0;

                while(((k<part) && (b1==false))&&((l<part)&&(b2==false))){

                    if(Double.parseDouble(r1[2])>= Double.parseDouble(r2[2])){  //Convertir a Double antes de chingar
                        archivoF.addNewRow(r1);
                        b1=true;
                        k++;
                        if(archivoF1.hasNextRow()){
                            r1 = archivoF1.nextRow();
                            b1=false;
                        }
                    }
                    else{
                        archivoF.addNewRow(r2);
                        b2=true;
                        l++;
                        if(archivoF2.hasNextRow()){
                            r2 = archivoF2.nextRow();
                            b2=false;
                        }

                    }

                }

                if(k<part){

                    while((k<part)&&(b1==false)){
                        archivoF.addNewRow(r1);
                        b1=true;
                        k++;
                        if(archivoF1.hasNextRow()){
                            r1 = archivoF1.nextRow();
                            b1=false;
                        }
                    }
                }
                if(l<part){

                    while((l<part)&&(b2==false)){
                        archivoF.addNewRow(r2);
                        b2=true;
                        l++;
                        if(archivoF2.hasNextRow()){
                            r2 = archivoF2.nextRow();
                            b2=false;
                        }
                    }
                }

            }
            if(b1==false){
                archivoF.addNewRow(r1);
            }
            if(b2==false){
                archivoF.addNewRow(r2);
            }
            while(archivoF1.hasNextRow()){
                r1 = archivoF1.nextRow();
                archivoF.addNewRow(r1);
            }
            while(archivoF2.hasNextRow()){
                r2 = archivoF2.nextRow();
                archivoF.addNewRow(r2);
            }

            archivoF1.close();
            archivoF2.close();
            try {
                archivoF.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }


        }
    }


    /**
     * Regresa de la primer columna los datos para imprimirlos en el Grid
     *
     *
     * @return 
     */
    public String[] getA(){

        Cell [] arr;
        String [] strarr;

        Readfile archivoF = new Readfile(archivoPrincipal);
        arr = archivoF.getA();

        strarr= new String[arr.length];

        for (int i=0; i<arr.length; i++){
            strarr[i] = arr[i].getContents();
        }

        archivoF.close();
        return strarr;

    }


    /**
     * Regresa de la segunda columna los datos para imprimirlos en el Grid
     *
     *
     * @return 
     */
    public String[] getB(){

        Cell [] arr;
        String [] strarr;

        Readfile archivoF = new Readfile(archivoPrincipal);
        arr = archivoF.getB();

        strarr= new String[arr.length];

        for (int i=0; i<arr.length; i++){
            strarr[i] = arr[i].getContents();
        }

        archivoF.close();

        return strarr;

    }


    /**
     * Regresa de la tercer columna los datos para imprimirlos en el Grid
     *
     *
     * @return 
     */
    public double[] getC(){

        Cell [] arr;
        double [] strarr;

        Readfile archivoF = new Readfile(archivoPrincipal);
        arr = archivoF.getC();

        strarr= new double[arr.length];

        for (int i=0; i<arr.length; i++){
            strarr[i] = Double.parseDouble(arr[i].getContents());
        }

        archivoF.close();

        return strarr;

    }

}
