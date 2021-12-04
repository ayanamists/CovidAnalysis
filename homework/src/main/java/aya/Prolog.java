package aya;

import java.io.FileWriter;

public class Prolog {
    public static void main(String args[]){
        try{
            FileWriter fw=new FileWriter("sample2.pl");
            fw.write("insert(country(Name,Case),[],[country(Name,Case)]).\n" +
                    "insert(country(Name,Case),[country(NameH,CaseH)|T],[country(Name,Case),country(NameH,CaseH)|T]):- Case =< CaseH, !.\n" +
                    "insert(country(Name,Case),[country(NameH,CaseH)|T1],[country(NameH,CaseH)|T2]):- insert(country(Name,Case),T1,T2).\n" +
                    "\n" +
                    "\n" +
                    "insort([],[]).\n" +
                    "insort([country(NameH,CaseH)|T],Sorted):- insort(T,Sorted2), insert(country(NameH,CaseH),Sorted2,Sorted).\n");
            fw.write("insert(country(Name,Case),[],[country(Name,Case)]).\n" +
                    "insert(country(Name,Case),[country(NameH,CaseH)|T],[country(Name,Case),country(NameH,CaseH)|T]):- Case >= CaseH, !.\n" +
                    "insert(country(Name,Case),[country(NameH,CaseH)|T1],[country(NameH,CaseH)|T2]):- insert(country(Name,Case),T1,T2).\n" +
                    "\n" +
                    "\n" +
                    "insort([],[]).\n" +
                    "insort([country(NameH,CaseH)|T],Sorted):- insort(T,Sorted2), insert(country(NameH,CaseH),Sorted2,Sorted).\n");
            fw.close();
        }catch(Exception e){

        }
    }
}
