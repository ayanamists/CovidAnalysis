package aya;

import java.io.FileWriter;

public class Prolog {
    public static void main(String args[]){
        try{
            FileWriter fw=new FileWriter("sample2.pl");
            fw.write("insertAsc(country(Name,Case),[],[country(Name,Case)]).\n" +
                    "insertAsc(country(Name,Case),[country(NameH,CaseH)|T],[country(Name,Case),country(NameH,CaseH)|T]):- Case =< CaseH, !.\n" +
                    "insertAsc(country(Name,Case),[country(NameH,CaseH)|T1],[country(NameH,CaseH)|T2]):- insertAsc(country(Name,Case),T1,T2).\n" +
                    "\n" +
                    "\n" +
                    "insortAsc([],[]).\n" +
                    "insortAsc([country(NameH,CaseH)|T],Sorted):- insortAsc(T,Sorted2), insertAsc(country(NameH,CaseH),Sorted2,Sorted).\n");
            fw.write("insertDesc(country(Name,Case),[],[country(Name,Case)]).\n" +
                    "insertDesc(country(Name,Case),[country(NameH,CaseH)|T],[country(Name,Case),country(NameH,CaseH)|T]):- Case >= CaseH, !.\n" +
                    "insertDesc(country(Name,Case),[country(NameH,CaseH)|T1],[country(NameH,CaseH)|T2]):- insertDesc(country(Name,Case),T1,T2).\n" +
                    "\n" +
                    "\n" +
                    "insortDesc([],[]).\n" +
                    "insortDesc([country(NameH,CaseH)|T],Sorted):- insortDesc(T,Sorted2), insertDesc(country(NameH,CaseH),Sorted2,Sorted).\n");
            fw.close();
        }catch(Exception e){

        }
    }
}
