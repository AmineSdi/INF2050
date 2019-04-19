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
 * Calcule les statistiques en tenant compte des statistiques des
 * exécutions précédentes et les met dans un objet "Statistics"
 */

import net.sf.json.JSONObject;

public class StatsCalculation {

    private CycleReport report;
    private Statistics pastStats;
    private Statistics newStats;

    /**
     * Construit un nouvel objet "StatsCalculation"
     * @param report    un objet contenant les attributs propres
     *                 à touts les activités déclarées par un membre
     * @param pastStats sauvegarde d'anciennes statistiques
     */
    public StatsCalculation(CycleReport report, Statistics pastStats) {
        this.report = report;
        this.pastStats = pastStats;
        this.newStats = new Statistics();
    }

    /**
     * Construit un nouvel objet "StatsCalculation"
     * @param pastStats sauvegarde d'anciennes statistiques
     */
    public StatsCalculation(Statistics pastStats) {
        this.report = null;
        this.pastStats = pastStats;
        this.newStats = new Statistics();
    }

    /**
     * Regroupe les méthodes pour le calcul des statistiques.
     * @param errorMsg Message d'erreur si la fonction est appelée lors
     *                 du traitement d'une déclaration invalide.
     */
    public void generateStats(String errorMsg) {
        incrementNbTotalDeclaration();
        setInvalidDeclarations();
        SetNumberBySex();
        setTotalActivities();
        setNbActivitiesByCategory();
        setNbDeclarationByOrder();
        setNbInvalidLicence(errorMsg);
    }

    /**
     * Ajoute une déclaration au nombre
     * total des déclarations et sauvegarde le nombre.
     */
    private void incrementNbTotalDeclaration(){
        newStats.setNbTotalDeclarations(pastStats.getNbTotalDeclarations() + 1);
    }

    /**
     * Ajoute une déclaration invalide au nombre
     * de déclarations invalides et sauvegarde le nombre.
     */
    private void setInvalidDeclarations(){
        if (report != null && report.getCycleIsComplete()){
            newStats.setNbInvalidDeclarations(pastStats.getNbInvalidDeclarations());
        }
        else
            newStats.setNbInvalidDeclarations(pastStats.getNbInvalidDeclarations()+1);
    }

    /**
     * Pour chaque sexe, ajuste le nombre en fonction du cycle
     * présent et de la valeur accumulée dans "pastStats"
     */
    private void SetNumberBySex() {
        newStats.setNbUnknownSex(getNumberBySex("0", pastStats.getNbUnknownSex()));
        newStats.setNbMen(getNumberBySex("1", pastStats.getNbMen()));
        newStats.setNbWomen(getNumberBySex("2", pastStats.getNbWomen()));
    }

    /**
     * Ajoute 1 au nombre par sexe si "sexe" correspond à la valeur du
     * "CycleReport" ou retourne la valeur accumulée dans "pastStats".
     * @param sex   numéro du sexe selon la norme ISO 5218
     * @param pastNumberBySex   le nombre accumulé pour les individus d'un sexe
     * @return  le total d'individus accumulé pour un sexe donné
     */
    private int getNumberBySex(String sex, int pastNumberBySex) {

        if (report != null && report.getSex().equals(sex)) {
            return pastNumberBySex + 1;
        }
        return pastNumberBySex;
    }

    /**
     * Sauvegarde le nombre total d'activités en ajoutant
     * celle de la déclaration en cours si celle-ci est valide
     */
    private void setTotalActivities() {
        int nbAct = 0;
        if (report != null){
            nbAct = report.getActivites().size();
        }
        newStats.setNbTotalActivities(pastStats.getNbTotalActivities() + nbAct);
    }

    /**
     * Augmente le nombre d'activités par catégories et sauvegarde.
     */
    private void setNbActivitiesByCategory() {
        if (report != null){
            for (Object activity: report.getActivites()) {
                JSONObject o = (JSONObject) activity;
                pastStats.incrementActivitiesByCategory(o.getString("categorie"));
            }
        }
        newStats.setNbActivitiesByCategory(pastStats.getNbActivitiesByCategory());
    }

    /**
     * Sauvegarde et augmente le nombre de déclarations
     * valides ou invalides par ordre pour la déclaration en cours.
     */
    private void setNbDeclarationByOrder() {
        if (report != null ){
            if(report.getCycleIsComplete()) {
                pastStats.incrementValidDeclarationByOrder(report.getOrder().getOrderTitle());
            }
            else{
                pastStats.incrementInvalidDeclarationByOrder(report.getOrder().getOrderTitle());
            }
        }
        newStats.setNbValidDeclarationsByOrder(pastStats.getNbValidDeclarationsByOrder());
        newStats.setNbInvalidDeclarationsByOrder(pastStats.getNbInvalidDeclarationsByOrder());
    }

    /**
     * Augmente le nombre de déclarations avec un permis invalide
     * si le permis est invalide. Sauvegarde le nombre de permis invalides.
     * @param errorMsg Message d'erreur si la fonction est appelée lors
     *                 du traitement d'une déclaration invalide.
     */
    private void setNbInvalidLicence(String errorMsg) {
        int invalidLicences = pastStats.getNbInvalidLicence();
        if(errorMsg.matches(".*permis.*")){
            invalidLicences+=1;
        }
        newStats.setNbInvalidLicence(invalidLicences);
    }
    
    public Statistics getNewStats() {
        return newStats;
    }
}