package com.mycompany.kauppamatkustaja;
import java.util.*;

public class Kaupunki {
    private int Id;
    private List<Integer> Eta;
    
    public int getId(){
        return Id;
    }
    public void setId(int id){
        Id = id;
    }
    
    public List<Integer> getEta(){
        return Eta;
    }
    public void setEta(int index, int etaisyys){
        Eta.set(index, etaisyys);
    }
    
    public Kaupunki(int id){
        Id = id;
    }
    //Annetaan kaupungille satunnaiset etäisyydet muihin kaupunkeihin
    public void Etaisyydet(int id, List<Kaupunki> x){
        Eta = new ArrayList<>();
        Random r = new Random();
        int index = x.size() - id;
        int valimatkamax = 15;
        //Haetaan jo annetut etäisyydet muista kaupungeista
        for (int i = 0; i < id - 1; i++) {
            Eta.add(x.get(i).getEta().get(id - 1));
        }
        //Lisätään listaan "etäisyys" kaupunkiin itseensä
        //Arvona käytetään arvoa, joka ei ole satunnaisvälissä
        Eta.add(valimatkamax + 1);
        //Arvotaan etäisyydet loppuihin kaupunkeihin
        for (int i = 1; i <= index; i++) {
            Eta.add(r.nextInt((valimatkamax - 1) + 1) + 1);
        }
    }
    
    public void TulostaEtaisyydet(List<Kaupunki> k){
        int x = 0;
        
        for (int e : Eta) {            
            if(e != 16) {
                System.out.println(this.toString() + " -> " + k.get(x).toString() + ": " + e);
            }
            x++;
        }
    }
    
    @Override
    public String toString(){
        return "Kaupunki " + Id;
    }
}
