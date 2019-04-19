/*
 * Copyright (c) 2019 equipe21
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.equipe21;

/**
 * Conserve les données des statistiques
 * calculées lors de l'exécution.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;

    private int nbMen;
    private int nbWomen;
    private int nbUnknownSex;
    private int nbTotalDeclarations;
    private int nbInvalidDeclarations;
    private int nbInvalidLicence;
    private HashMap<String, Integer> nbValidDeclarationsByOrder = new HashMap<>();
    private HashMap<String, Integer> nbInvalidDeclarationsByOrder = new HashMap<>();

    private int nbTotalActivities;
    private HashMap<String, Integer> nbActivitiesByCategory = new HashMap<>();



    public int getNbMen() {
        return nbMen;
    }

    public void setNbMen(int nbMen) {
        this.nbMen = nbMen;
    }

    public int getNbWomen() {
        return nbWomen;
    }

    public void setNbWomen(int nbWomen) {
        this.nbWomen = nbWomen;
    }

    public int getNbUnknownSex() {
        return nbUnknownSex;
    }

    public void setNbUnknownSex(int nbUnknownSex) {
        this.nbUnknownSex = nbUnknownSex;
    }

    public int getNbTotalDeclarations() { return nbTotalDeclarations; }

    public void setNbTotalDeclarations(int nbTotalDeclarations) { this.nbTotalDeclarations = nbTotalDeclarations; }

    public int getNbInvalidDeclarations() { return nbInvalidDeclarations; }

    public void setNbInvalidDeclarations(int nbInvalidDeclarations) {
        this.nbInvalidDeclarations = nbInvalidDeclarations;
    }

    public int getNbValidDeclarations() {
        return nbTotalDeclarations - nbInvalidDeclarations;
    }

    public int getNbInvalidLicence() {
        return nbInvalidLicence;
    }

    public void setNbInvalidLicence(int nbInvalidLicence) {
        this.nbInvalidLicence = nbInvalidLicence;
    }

    public HashMap<String, Integer> getNbValidDeclarationsByOrder() {
        return nbValidDeclarationsByOrder;
    }

    public void setNbValidDeclarationsByOrder(HashMap<String, Integer> nbValidDeclarationsByOrder) {
        this.nbValidDeclarationsByOrder = nbValidDeclarationsByOrder;
    }

    /**
     * Augmente le nombre de déclarations valides par ordre professionnel de 1.
     * Si l'ordre professionnel n'existe pas déjà, il est ajouté et initialisé à 1
     * @param order Correspond au titre de l'ordre du "CycleReport"
     */
    public void incrementValidDeclarationByOrder(String order){
        if(this.nbValidDeclarationsByOrder.containsKey(order)){
            this.nbValidDeclarationsByOrder.replace(order, nbValidDeclarationsByOrder.get(order)+1);
        }
        else{
            this.nbValidDeclarationsByOrder.put(order, 1);
        }
    }

    public HashMap<String, Integer> getNbInvalidDeclarationsByOrder() {
        return nbInvalidDeclarationsByOrder;
    }

    public void setNbInvalidDeclarationsByOrder(HashMap<String, Integer> nbInvalidDeclarationsByOrder) {
        this.nbInvalidDeclarationsByOrder = nbInvalidDeclarationsByOrder;
    }

    /**
     * Augmente le nombre de déclarations invalides par ordre professionnel de 1.
     * Si l'ordre professionnel n'existe pas déjà, il est ajouté et initialisé à 1
     * @param order Correspond au titre de l'ordre du "CycleReport"
     */
    public void incrementInvalidDeclarationByOrder(String order){
        if(this.nbInvalidDeclarationsByOrder.containsKey(order)){
            this.nbInvalidDeclarationsByOrder.replace(order, nbInvalidDeclarationsByOrder.get(order)+1);
        }
        else{
            this.nbInvalidDeclarationsByOrder.put(order, 1);
        }
    }

    public int getNbTotalActivities() {
        return nbTotalActivities;
    }

    public void setNbTotalActivities(int nbTotalActivities) {
        this.nbTotalActivities = nbTotalActivities;
    }

    public HashMap<String, Integer> getNbActivitiesByCategory() {
        return nbActivitiesByCategory;
    }

    public void setNbActivitiesByCategory(HashMap<String, Integer> nbActivitiesByCategory) {
        this.nbActivitiesByCategory = nbActivitiesByCategory;
    }

    /**
     * Augmente le nombre d'activités par catégories de 1.
     * Si la catégorie n'existe pas déjà, elle est ajouté et initialisée à 1
     * @param category  Catégorie d'une activité
     */
    public void incrementActivitiesByCategory(String category){
        if(this.nbActivitiesByCategory.containsKey(category)){
            this.nbActivitiesByCategory.replace(category, nbActivitiesByCategory.get(category)+1);
        }
        else{
            this.nbActivitiesByCategory.put(category, 1);
        }
    }

    /**
     *Affiche les statistiques sur le terminal.
     * @return les données de la classe formattées
     *  sous forme de String pour l'affichage à la console.
     */
    @Override
    public String toString() {
        return "Nombre total de déclarations: " + getNbTotalDeclarations() +
                "\nNombre de déclarations complètes: " + (getNbValidDeclarations()) +
                "\nNombre de déclarations incomplètes/invalides: " + getNbInvalidDeclarations() +
                "\n\nNombre de déclarations faites par des hommes: " + getNbMen() +
                "\nNombre de déclarations faites par des femmes: " + getNbWomen() +
                "\nNombre de déclarations faites par des gens de sexe inconnu: " + getNbUnknownSex() +
                "\n\nNombre total des activités: " + getNbTotalActivities() +
                "\nNombre d'activités par catégorie: " + statsHashMapToString(getNbActivitiesByCategory()) +
                "\n\nNombre de déclarations valides et complètes par ordre professionnel: " + statsHashMapToString(getNbValidDeclarationsByOrder()) +
                "\n\nNombre de déclarations invalides ou incomplètes par ordre professionnel: " + statsHashMapToString(getNbInvalidDeclarationsByOrder())+
                "\n\nNombre de déclarations soumises avec un numéro de permis invalide: " + getNbInvalidLicence();
    }

    /**
     * Inspiré de l'exemple sur
     * https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
     *
     * Construit un String à partir d'un Hashmap pour l'affichage à la console.
     * @param hm    Un Hashmap de l'objet "Statistics".
     * @return  Un String avec tous les "key: value"
     * formattés pour la sortie à la console.
     */
    private String statsHashMapToString(HashMap<String, Integer> hm) {
        if (hm.isEmpty()){
            return "\n    (Aucune)";
        }
        String map = "";
        Iterator iterator = hm.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry mapElement = (Map.Entry) iterator.next();
            map += "\n    " + mapElement.getKey() + ": " + mapElement.getValue();
        }
        return map;
    }
}
