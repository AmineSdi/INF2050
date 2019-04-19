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

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Classe représentant les attributs propres à la
 * déclaration des activités de formation continue
 */
public class CycleReport {

    private String numLicense;
    private String familyName;
    private String name;
    private String sex;
    private String cycle;
    private String transHours = "0";
    private EOrder order;
    private JSONArray activites;
    private boolean cycleIsComplete = false;

    private ArrayList<Validation<Activity>> activityValidations = new ArrayList<Validation<Activity>>();
    private ArrayList<Validation<CycleReport>> reportValidations = new ArrayList<Validation<CycleReport>>();

    private int totalHours = 0;

    /**
     * Cree une instance de 'CycleReport' a partir d'un 'JSONObject'.
     * @param report 'JSONObject' root du fichier JSON.
     */
    CycleReport(JSONObject report) {
        setClassVariables(report);
        setArrayListforValidation(order);
        setTransHours(report);
        this.totalHours = this.getTotalValidHours();
        this.cycleIsComplete = this.validateReport();
    }

    /**
     * Initialise les variables de 'CycleReport'.
     * Quitte si des champs sont manquants dans le 'JSONObject'
     * @param report 'JSONObject' root du fichier JSON.
     */
    private void setClassVariables(JSONObject report){
        try {
            setVariablesFromJSONObject(report);
            this.order.getOrder().setCategoryEnum();
        } catch (Exception e){
            JSONWriter.exitOnStructureError(String.format("Erreur de structure: Le champ \"%s\" est manquant. ", e.getMessage().split("\"")[1]));
        }
    }

    /**
     * Obtient tous les variables depuis le JSONObject
     * @param report 'JSONObject' root du fichier JSON.
     */
    private void setVariablesFromJSONObject(JSONObject report) {
        this.numLicense = report.getString("numero_de_permis");
        this.familyName = report.getString("nom");
        this.name = report.getString("prenom");
        this.sex = report.getString("sexe");
        this.cycle = report.getString("cycle");
        this.activites = report.getJSONArray("activites");
        this.order = EOrder.fromTitle(report.getString("ordre"));
    }

    /**
     * Vérifie le nombre d'heures transférées et les limite au maximum, le cas échéant.
     * @return le nombre d'heures transférées réel ou ajusté
     *          au maximum, si le nombre le dépasse.
     */
    public int getFinalTransHours() {
        int hours = validIntTransHours();
        int maxHours = getOrder().getMaxTransferedHours();
        if(maxHours == -1) {
            return hours;
        }
        if(hours > maxHours) {
            hours = maxHours;
        }
        return hours;
    }

    /**
     * Calcule les heures totales des activites valides.
     * Une activite doit reussir toute les 'Validation<Activity>' pour etre consideree valide.
     * @return Le total d'heures valides incluant les heures transferees.
     */
    private int getTotalValidHours() {
        int total = 0;
        for(int i = 0; i < this.getActivites().size(); i++) {
            total += sumValidActivities(i);
        }
        return total + getFinalTransHours();
    }

    /**
     * Fait la somme des heures valides d'une activite.
     * @param index L'index d'une activité
     * @return  Le total des heures valides pour chaque activité.
     */
    private int sumValidActivities(int index) {
        int total = 0;
        Activity activity = new Activity(this.getActivites().getJSONObject(index), this);

        if(isValidActivity(activity)) {
            total += activity.getRealHours();
        }
        return total;
    }

    /**
     * Verifie que les activites sont valides. Si la methode
     * retourne false, les heures ne sont pas calculees.
     * @param activity
     * @return true si l'activité est valide, false sinon.
     */
    private boolean isValidActivity(Activity activity) {
        for(Validation<Activity> val : this.activityValidations) {
            try {
                if (!activity.validate(val))
                    return false;
            } catch (ParseException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Effectue toutes les validations de 'CycleReport' contenues dans 'reportValidations'
     * @return 'true' si toutes les validations retournent 'true', sinon 'false'
     */
    public boolean validateReport() {
        boolean valid = true;
        for(Validation<CycleReport> validation : reportValidations) {
            try{
                if(! validation.validate(this)) {
                    valid = false;
                }
            }catch(Exception e){
                valid = false;
            }
        }
        return valid;
    }

    /**
     * Retourne les heures transferees reelles si elles
     * sont valides.
     * @return  Les heures transferees reelles ou 0 si non valides.
     */
    private int validIntTransHours() {
        int hours = 0;
        if (isValidInteger(this.transHours)) {
            hours = Integer.parseInt(this.transHours);
        }
        return hours;
    }

    /**
     * Verifie que les heures transferees sont des entiers valides,
     * sinon, ajoute un message d'erreur et quitte.
     * @param hours Les heures transferees
     * @return true si les heures sont valides, quitte avec
     *          un message d'erreur sinon.
     */
    private boolean isValidInteger(String hours) {
        if (!isOnlyIntegers(hours)){
            JSONWriter.exitOnStructureError("Les heures transférées sont invalides.");
        }
        return true;
    }

    /**
     * Verifie que les heures ne contiennent que des valeurs
     * entre 0 et 9.
     * @param hours Les heures transferees
     * @return  true s'il n'y a que des valeurs numériques, false sinon.
     */
    public boolean isOnlyIntegers(String hours) {
        for(int i = 0; i < hours.length(); i++) {
            if (hours.charAt(i) < '0' || hours.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Obtient le numero de permis donne dans la declaration.
     * @return  Le numero de associe a l'objet.
     */
    public String getNumLicense() {
        return numLicense;
    }

    /**
     * Obtient le nom dans la declaration.
     * @return Le nom associe a l'objet.
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Obtient le prenom dans la declaration.
     * @return Le prenom associe a l'objet.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtient le sexe dans la declaration.
     * @return Le sexe associe a l'objet.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Obtient le cycle donne dans la declaration.
     * @return  Le cycle associe a l'objet en String.
     */
    public String getCycle() {
        return cycle;
    }

    /**
     * Obtient les heures transferees ajustees et valides.
     * @return  Les heures transferees du cycle precedant.
     */
    public int getTransHours() {
        return getFinalTransHours();
    }

    /**
     * Obtient la liste des activites declarees.
     * @return  Un JSONArray contenant la liste des activites.
     */
    public JSONArray getActivites() {
        return activites;
    }

    public boolean getCycleIsComplete() {
        return cycleIsComplete;
    }

    /**
     * Obtient l'ordre associe a la declaration.
     * @return  Un objet 'Order' propre a l'ordre declare.
     */
    public Order getOrder() {
        return order.getOrder();
    }

    /**
     * Obtient le nombre total d'heures valides dans la declaration.
     * @return  Le total des heures.
     */
    public int getTotalHours() {
        return totalHours;
    }

    /**
     * Determine le nombre d'heures transferees en fonction de l'ordre.
     * @param report    'JSONObject' root du fichier JSON.
     */
    private void setTransHours(JSONObject report) {
        try {
            this.transHours = report.getString("heures_transferees_du_cycle_precedent");
            if(this.order.getOrder().maxTransferedHours == -1) {
                JSONWriter.exitOnStructureError(String.format("Erreur de structure: L'ordre des %s ne devraient pas contenir de champ 'heures_transferees_du_cycle_precedent'", this.order.getTitle()));
            }
        } catch (JSONException e) {
            if (this.order.getOrder().getMaxTransferedHours() != -1){
                JSONWriter.exitOnStructureError(String.format("Erreur de structure: Le champ 'heures_transferees_du_cycle_precedent' est manquant."));
            }
        }
    }

    /**
     * Determine les ArrayList a utiliser en fonction de l'ordre declare.
     * @param order     L'ordre professionnel associe a une declaration.
     */
    private void setArrayListforValidation(EOrder order) {
        activityValidations = order.getOrder().getActivityValidations();
        reportValidations = order.getOrder().getCycleReportValidations();
    }
}
