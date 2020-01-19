package com.mycompany.kauppamatkustaja;
import java.util.*;

public class Main {
    
    private static List<Kaupunki> Kaupungit;
    private static int Matka = 0;
    
    public static void main(String[] args){
        TeeKaupungit(4);
        LuoEta();
        //TulostaKaupungit();
        TulostaEta();
        
        AhneAlgoritmi(1);
        System.out.println("");
        System.out.println("Matkan pituus: " + Matka);


    }
    //Luodaan Kaupunki -oliot ja lisätään ne listaan
    public static void TeeKaupungit(int lkm){
        Kaupungit = new ArrayList<>();
        
        for (int i = 1; i <= lkm; i++) {
            Kaupungit.add(new Kaupunki(i));
        }
    }
    //Luodaan/haetaan jokaiselle kaupungille etäisyydet
    public static void LuoEta(){
        for (Kaupunki city : Kaupungit) {
            city.Etaisyydet(city.getId(), Kaupungit);
        }
    }
    
    public static void TulostaKaupungit(){
        for (Kaupunki city : Kaupungit) {
            System.out.println(city.toString());
        }
    }
    
    public static void TulostaEta(){
        for (Kaupunki city : Kaupungit){
            city.TulostaEtaisyydet(Kaupungit);
            System.out.println("");
        }
    }
    
    public static void AhneAlgoritmi(int lahtoid){
        //Luodaan kaksi kopiota lähtökaupungin Eta -listasta
        List<Integer> x = new ArrayList<>();
        x.addAll(Kaupungit.get(lahtoid - 1).getEta());
        
        List<Integer> y = new ArrayList<>();
        y.addAll(Kaupungit.get(lahtoid - 1).getEta());
        //Muuttujat nykyiselle kaupungille ja seuraavalle kaupungille
        int currentindex = lahtoid - 1;
        int nextindex = 0;

        //For -loop jokaisen kaupungin läpikäyntiä varten
        for (int i = 0; i < Kaupungit.size() - 1; i++) {
            //Järjestetään kopiolista nousevaan järjestykseen
            Collections.sort(x);
            //Käydään läpi toisen kopiolistan jäsenet ja etsitään
            //listalta pienin arvo järjestetyn kopion x avulla
            for (int e : y) {
                if (e == x.get(0)) {
                    for (int j = 0; j < y.size(); j++) {
                        if (e == y.get(j)) {
                            nextindex = j;
                        }
                    }
                }
            }
            //Merkataan nykyinen kaupunki "käydyksi" jokaisen kaupungin etäisyyslistaan
            for (Kaupunki k : Kaupungit) {
                k.setEta(currentindex, 16);
            }
            //Lisätään matkaan seuraavaksi kuljettava matka
            Matka += y.get(nextindex);
            //Reitin tulostus
            System.out.print(Kaupungit.get(currentindex).toString() + " --" + y.get(nextindex) + "--> ");
            //Viimeisen kaupungin tulostus
            if (i == Kaupungit.size() - 2) {
                System.out.println(Kaupungit.get(nextindex).toString());
            }
            //Tyhjennetään ja tallenetaan kopioihin seuraavan kaupungin etäisyyslista
            x.clear();
            x.addAll(Kaupungit.get(nextindex).getEta());
            y.clear();
            y.addAll(Kaupungit.get(nextindex).getEta());
            //"Vaihdetaan" sijaintia
            currentindex = nextindex;
        }
    }
}
