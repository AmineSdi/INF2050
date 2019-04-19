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

import net.sf.json.JSONObject;

import java.text.ParseException;

/**
 * Cette classe représente une activité du fichier JSON en entrée.
 * La classe extrait les éléments sous forme de String.
 */
public class Activity {

    private CycleReport cycleReport = null;
    private String description;
    private String category;
    private String hours;
    private String date;

    /**
     * Constructeur qui crée une instance de 'Activity'
     * contenant les attributs d'une activité du fichier JSON.
     * @param activity      l'activité dont les attibuts sont extraits
     * @param cycleReport   un objet contenant les attributs propres
     *                      à touts les activités déclarées par un membre
     */
    Activity(JSONObject activity, CycleReport cycleReport) {
        try {
            this.description = activity.getString("description");
            this.category = activity.getString("categorie");
            this.hours = activity.getString("heures");
            this.date = activity.getString("date");
            this.cycleReport = cycleReport;
        } catch (Exception e){
            JSONWriter.exitOnStructureError(String.format("Erreur de structure: Il manque le champ \"%s\" à l'une des activités.",e.getMessage().split("\"")[1]));
        }
    }

    /**
     * Calcule les heures totales en prenant en compte
     * le maximum pour une catégorie.
     * @return Le total d'heures ajusté selon le maximum, le cas échéant.
     */
    public int getRealHours() {
        ECategory category;
        int hours;
        try {
            category = ECategory.fromTitle(this.getCategory());
            hours = Integer.parseInt(this.getHours());
        } catch (Exception e) {
            return 0;
        }
        return category.getTrimHours(hours);
    }

    /**
     * Permet de valider la conformité d'une activité
     * @param validation: un objet implémentant 'Validation<Activity>'
     * @return true si 'this' réussit la validation, sinon false
     * @throws ParseException
     */
    public boolean validate(Validation<Activity> validation) throws ParseException {
        return validation.validate(this);
    }

    /**
     * Obtient la description propre à un activité.
     * @return La description de l'activité.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtient la catégorie déclarée pour une activité.
     * @return La catégorie de l'activité.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Obtient les heures déclarées pour une activité.
     * @return Les heures de l'activité.
     */
    public String getHours() {
        return hours;
    }

    /**
     * Obtient la date déclarée pour une activité.
     * @return La date d'une activité.
     */
    public String getDate() {
        return date;
    }

    /**
     * Obtient les éléments propres à toutes les activités déclarées.
     * @return un objet contenant les attributs du JSONArray des activités.
     */
    public CycleReport getCycleReport() {
        return cycleReport;
    }
}
