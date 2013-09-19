package Model.Mezclas;

import jxl.Cell;
import jxl.write.WriteException;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Juan Pablo
 * Date: 17/09/13
 * Time: 11:35 PM
 */
public class MezclaEquilibrada {




    /**
     * Metodo que recibe 3 datos tipo celda y 5 tipo String, e inicia la Mezcla Equilibrada
     * @param A dato tipo Celda
     * @param B dato tipo Celda
     * @param C dato tipo Celda
     * @param Folder dato tipo String
     * @param F dato tipo String
     * @param F1 dato tipo String
     * @param F2 dato tipo String
     * @param F3 dato tipo String
     * @throws IOException
     * @throws WriteException
     */
    public MezclaEquilibrada(Cell A[],Cell B[],Cell C[], String Folder,String F, String F1,String F2, String F3) throws IOException, WriteException {

        int n=0, part=1;

        F = Folder + F;
        F1 = Folder + F1;
        F2 = Folder + F2;
        F3 = Folder + F3;


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
                test.addText(2, j, C[j].getContents());

            }
            n = j;
        }




        test.removeRow(0);


        test.write();
        test.close();

        particioninicial(F,F2,F3);

        boolean inLineF1 ;
        boolean inLineF3 ;

        boolean BAND = true;
        do {

            if (BAND == true){
                particionfusion(F2,F3,F,F1);
                BAND = false;
            }
            else{
                particionfusion(F,F1,F2,F3);
                BAND = true;
            }

            Readfile fr1 = new Readfile(F1);
            Readfile fr3 = new Readfile(F3);

            inLineF1 = fr1.hasNextRow();
            inLineF3 = fr3.hasNextRow();

            fr1.close();
            fr3.close();
        }
        while(inLineF3 || inLineF1);

    }


    /**
     * Metodo Que recibe tres datos tipo String, y realiza la primera particion del ordenamiento
     * @param F Fila 1 del arreglo
     * @param F2 Fila 2 del arreglo
     * @param F3 Fila 3 del arreglo
     * @throws IOException
     * @throws WriteException  
     */
    public static void particioninicial(String F,String F2, String F3) throws IOException, WriteException {


        String []R = {},Aux = {} ;
        boolean BAND= true;


        Readfile fr = new Readfile(F);

        Writefile pw2 = new Writefile();
        pw2.setOutputFile(F2);
        pw2.generate();


        Writefile pw3 = new Writefile();
        pw3.setOutputFile(F3);
        pw3.generate();


        boolean inLineF = fr.hasNextRow();

        if(inLineF){
            Aux = fr.nextRow();
            pw2.addNewRow(Aux);
            pw2.write();
            BAND = true;

        }

        while (inLineF){

            inLineF = fr.hasNextRow();
            if(inLineF){
                R =fr.nextRow();
                if ( Double.parseDouble(R[2]) >= Double.parseDouble(Aux[2]) ){
                    //if( R>= Aux){
                    Aux = R;
                    if(BAND == true){

                        pw2.addNewRow(Aux);
                        pw2.write();
                    }
                    else{

                        pw3.addNewRow(Aux);
                        pw3.write();
                    }
                }
                else{
                    Aux = R;
                    if(BAND == true){

                        pw3.addNewRow(Aux);
                        pw3.write();
                        BAND = false;
                    }
                    else{

                        pw2.addNewRow(Aux);
                        pw2.write();

                        BAND = true;
                    }
                }
            }





        } //fin while
        pw2.close();
        pw3.close();
        fr.close();
    }


    /**
     * Metodo que recibe 4 datos de tipo string y realiza las particiones fusiones del ordenamiento
     * @param FA
     * @param FB
     * @param FC
     * @param FD
     * @throws IOException
     * @throws WriteException  
     */
    public static void particionfusion(String FA, String FB, String FC, String FD) throws IOException, WriteException {

        String [] R1={}, R2={}, Aux={};
        boolean B,DELE1, DELE2;

        Readfile frFA = new Readfile(FA);

        Readfile frFB = new Readfile(FB);


        Writefile fwFC = new Writefile();
        fwFC.setOutputFile(FC);
        fwFC.generate();

        Writefile fwFD = new Writefile();
        fwFD.setOutputFile(FD);
        fwFD.generate();

        B = true;
        boolean inLineFA = frFA.hasNextRow();
        boolean inLineFB = frFB.hasNextRow();



        if(inLineFA){
            R1 = frFA.nextRow();
        }
        if(inLineFB){
            R2 = frFB.nextRow();
        }
        if (Double.parseDouble(R1[2]) < Double.parseDouble(R2[2])){
            //if(R1<R2){
            Aux = R1;
        }
        else{
            Aux = R2;
        }

        DELE1 = false;
        DELE2 = false;

        while((inLineFA||DELE1!=true)&&(inLineFB||DELE2!=true)){




            if(DELE1==true){
                inLineFA = frFA.hasNextRow();
                DELE1 = false;

            }
            if(DELE2 == true){
                inLineFB = frFB.hasNextRow();
                DELE2 = false;

            }
            if(inLineFA){
                R1 = frFA.nextRow();
            }
            else{
                R1=new String[] {};
            }
            if(inLineFB){
                R2 = frFB.nextRow();
            }
            else{
                R2= new String[] {};
            }


            if ( Double.parseDouble(R1[2]) < Double.parseDouble(R2[2])) {
                //if(R1<R2){
                if ( Double.parseDouble(R1[2]) >= Double.parseDouble(Aux[2])){
                    //if( R1>=Aux ){
                    //Aux = ayuda1(Aux,R1,FC,FD,B);
                    if (B){
                        if(R1.length<0){
                            fwFC.addNewRow(R1);
                        }
                    }
                    else{
                        if(R1.length<0){
                            fwFD.addNewRow(R1);
                        }

                    }
                    Aux = R1;
                    DELE1 = true;
                }
                else{
                    if ( Double.parseDouble(R2[2]) >= Double.parseDouble(Aux[2]) ){
                        //if(R2>= Aux){
                        //Aux = ayuda1(Aux,R2,FC,FD,B);
                        if (B){
                            if(R2.length<0){
                                fwFC.addNewRow(R2);
                            }
                        }
                        else{
                            if(R2.length<0){
                                fwFD.addNewRow(R2);
                            }

                        }
                        Aux = R2;
                        DELE2 = true;
                    }
                    else{
                        //Aux = ayuda2(Aux,R1,FC,FD,B);
                        if(B){
                            if(R1.length>0){
                                fwFD.addNewRow(R1);
                            }
                            B= false;
                        }
                        else{
                            if(R1.length>0){
                                fwFC.addNewRow(R1);
                            }
                            B = true;
                        }
                        B = !B;
                        DELE1 = true;
                    }
                }
            }
            else{
                if ( Double.parseDouble(R2[2]) >= Double.parseDouble(Aux[2])){
                    //if(R2>=Aux){
                    //Aux = ayuda1(Aux,R2,FC,FD,B);
                    if (B){
                        if(R2.length<0){
                            fwFC.addNewRow(R1);
                        }
                    }
                    else{
                        if(R2.length<0){
                            fwFD.addNewRow(R1);
                        }

                    }
                    Aux = R2;
                    DELE2 = true;
                }
                else{
                    if ( Double.parseDouble(R1[2]) >= Double.parseDouble(Aux[2])){
                        //if(R1>=Aux){
                        //Aux = ayuda1(Aux,R1,FC,FD,B);
                        if (B){
                            if(R1.length<0){
                                fwFC.addNewRow(R1);
                            }
                        }
                        else{
                            if(R1.length<0){
                                fwFD.addNewRow(R1);
                            }

                        }
                        Aux = R1;
                        DELE1 = true;
                    }
                    else{
                        //Aux = ayuda2(Aux,R2,FC,FD,B);
                        if(B){
                            if(R2.length>0){
                                fwFD.addNewRow(R2);
                            }
                            B= false;
                        }
                        else{
                            if(R2.length>0){
                                fwFC.addNewRow(R2);
                            }
                            B = true;
                        }
                        B = !B;
                        DELE2 = true;
                    }
                }
            }


        } //fin while


        if(DELE1 == true && !inLineFA){
            if(inLineFB){
                //ayuda3(Aux,R2,FB,FC,FD,B);
                Readfile frB = new Readfile(FB);
                boolean inLineF=false;

                if ( Double.parseDouble(R2[2]) >= Double.parseDouble(Aux[2])){
                    //if( R>=Aux ){
                    //Aux = ayuda1(Aux,R2,FC,FD,B);
                    if (B){
                        if(R2.length<0){
                            fwFC.addNewRow(R2);
                        }
                    }
                    else{
                        if(R2.length<0){
                            fwFD.addNewRow(R2);
                        }

                    }
                    Aux = R2;
                }
                else{
                    //Aux = ayuda2(Aux,R2,FC,FD,B);
                    Aux = R2;
                    if(B){
                        if(R2.length>0){
                            fwFD.addNewRow(R2);
                        }
                        B= false;
                    }
                    else{
                        if(R2.length>0){
                            fwFC.addNewRow(R2);
                        }
                        B = true;
                    }
                    B = !B;
                }


                inLineF= frB.hasNextRow();



                while(inLineF){

                    R2 = frB.nextRow();
                    if (Double.parseDouble(R2[2]) >= Double.parseDouble(Aux[2])){
                            //if(R>=Aux){
                        //Aux = ayuda1(Aux,R2,FC,FD,B);
                        if (B){
                            if(R2.length<0){
                                fwFC.addNewRow(R2);
                            }
                        }
                        else{
                            if(R2.length<0){
                                fwFD.addNewRow(R2);
                            }

                        }
                        Aux = R2;
                    }else{
                        //Aux = ayuda2(Aux,R2,FC,FD,B);
                        Aux = R2;
                        if(B){
                            if(R2.length>0){
                                fwFD.addNewRow(R2);
                            }
                            B= false;
                        }
                        else{
                            if(R2.length>0){
                                fwFC.addNewRow(R2);
                            }
                            B = true;
                        }
                        B = !B;
                    }
                }
            }
        }
        if(DELE2 == true && !inLineFB){
            if(inLineFA){
                //ayuda3(Aux,R1,FA,FC,FD,B);
                Readfile frA = new Readfile(FA);
                boolean inLineF=false;

                if ( Double.parseDouble(R1[2]) >= Double.parseDouble(Aux[2])){
                    //if( R>=Aux ){
                    //Aux = ayuda1(Aux,R2,FC,FD,B);
                    if (B){
                        if(R1.length<0){
                            fwFC.addNewRow(R1);
                        }
                    }
                    else{
                        if(R2.length<0){
                            fwFD.addNewRow(R1);
                        }

                    }
                    Aux = R1;
                }
                else{
                    //Aux = ayuda2(Aux,R2,FC,FD,B);
                    Aux = R1;
                    if(B){
                        if(R1.length>0){
                            fwFD.addNewRow(R1);
                        }
                        B= false;
                    }
                    else{
                        if(R2.length>0){
                            fwFC.addNewRow(R1);
                        }
                        B = true;
                    }
                    B = !B;
                }


                inLineF= frA.hasNextRow();



                while(inLineF){

                    R1 = frA.nextRow();
                    if (Double.parseDouble(R1[2]) >= Double.parseDouble(Aux[2])){
                        //if(R>=Aux){
                        //Aux = ayuda1(Aux,R2,FC,FD,B);
                        if (B){
                            if(R1.length<0){
                                fwFC.addNewRow(R1);
                            }
                        }
                        else{
                            if(R1.length<0){
                                fwFD.addNewRow(R1);
                            }

                        }
                        Aux = R1;
                    }else{
                        //Aux = ayuda2(Aux,R2,FC,FD,B);
                        Aux = R1;
                        if(B){
                            if(R1.length>0){
                                fwFD.addNewRow(R1);
                            }
                            B= false;
                        }
                        else{
                            if(R1.length>0){
                                fwFC.addNewRow(R1);
                            }
                            B = true;
                        }
                        B = !B;
                    }
                }
            }
        }

        fwFC.write();
        fwFC.close();
        fwFD.write();
        fwFD.close();

    }

}
