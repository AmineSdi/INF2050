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

import java.util.ArrayList;

/**
 * Classe qui a pour fonction de valider si le minimum
 * d'heures a declarer pour certaines categories a ete atteint.
 */

public class ValidationMinimumCategory implements Validation<CycleReport> {

    private ECategory[] validCategories;
    private int minimum = 0;

    /**
     * Constructeur qui cree une instance de 'ValidationMinimumCategory'
     * @param catList: La liste des categories a prendre en compte pour cette validation
     * @param minimum: Le minimum d'heures a atteindre dans les 'validCategories'.
     */
    ValidationMinimumCategory(ECategory[] catList, int minimum) {
        this.validCategories = catList;
        this.minimum = minimum;
    }

    /**
     * Valide le total d'heures pour les categories qui ont un minimum a respecter.
     * @param value Un CycleReport contenant les attributs d'une declaration.
     * @return 'true' si le total des heures valides est superieur a 'minimum'.
     *          Sinon, 'false' et ajoute un message d'erreur.
     */
    public boolean validate(CycleReport value) {
        int totalHours = 0;
        totalHours = getTotalHours(value, totalHours);
        totalHours += value.getFinalTransHours();
        if(totalHours >= minimum) {
            return true;
        }
        JSONWriter.addError(String.format("Il manque " + (minimum - totalHours)+ " heure%s pour valider les catégories: " + getCategoryNames(), (minimum - totalHours == 1 ? "" : "s")));
        return false;
    }

    /**
     * Calcule le total des heures pour les categories qui ont un minimum a respecter.
     * @param value Un CycleReport contenant les attributs d'une declaration.
     * @param totalHours Le total des heures pour les categories valides.
     * @return  Le total des heures des categories valides.
     */
    private int getTotalHours(CycleReport value, int totalHours) {
        for(Object obj : value.getActivites().toArray()) {
            Activity activity = new Activity((JSONObject) obj, value);
            ECategory category = ECategory.fromTitle(activity.getCategory());

            totalHours += sumTotalHours(category, activity);
        }
        return totalHours;
    }

    /**
     * Additionne les heures pour les categorie qui ont un minimum a respecter.
     * @param category  Categories a prendre en compte
     * @param activity  L'activite a prendre en compte
     * @return  la somme des heures
     */
    private int sumTotalHours(ECategory category, Activity activity) {
        int totalHours = 0;

        for(ECategory validCat : this.validCategories) {
            if(validCat.equals(category)) {
                totalHours += activity.getRealHours();
            }
        }
        return totalHours;
    }

    /**
     * Retourne les categories concernes pour les heures minimum.
     * @return  Un ArrayList contenant les nom des categories concernees.
     */
    private ArrayList<String> getCategoryNames(){
        ArrayList<String> catArray = new ArrayList<>();
        for(ECategory category: validCategories){
            catArray.add(category.getTitle());
        }
        return catArray;
    }
}
