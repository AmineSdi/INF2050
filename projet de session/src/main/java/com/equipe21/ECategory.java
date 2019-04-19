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
 * enum representant les differentes categories
 * possibles associees a une activite.
 */

public enum ECategory {

    CLASS("cours"),
    WORKSHOP("atelier"),
    SEMINAR("séminaire"),
    COLLOQUY("colloque"),
    CONFERENCE("conférence"),
    DIRLECTURE("lecture dirigée"),
    PRESENTATION("présentation"),
    DISCUSSION_GR("groupe de discussion"),
    RESEARCH_PR("projet de recherche"),
    PRO_REDACTION("rédaction professionnelle"),
    INVALID_CATEGORY("catégorie invalide");

    private String title = "noname";
    private int minHours = 0;
    private int maxHours = -1;

    /**
     * Cree une categorie avec un titre.
     * @param title Le titre donne a la categorie.
     */
    ECategory(String title) {
        this.title = title;
    }

    /**
     * Verifie si le titre passe en argument appartient a une categorie reconnue.
     * @param title Le titre recherche.
     * @return true si le titre appartient a une categorie reconnue, sinon false.
     */
    public static boolean isCategory(String title) {
        for(ECategory cat : ECategory.values()) {
            if (title.equals(cat.getTitle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne la categorie associe au titre passe en argument si le titre existe.
     * @param title Le titre recherche.
     * @return La categorie associee au titre passe en parametre ou une categorie
     *          invalide si elle n'existe pas.
     */
    public static ECategory fromTitle(String title) {
        for(ECategory category : ECategory.values()) {
            if(category.getTitle().equals(title)) {
                return category;
            }
        }
        return INVALID_CATEGORY;
    }

    /**
     * Recoit un nombre d'heures associe a une categorie et les ajuste
     * au besoin. Une valeur superieure a 'maxHours' retournera
     * 'maxHours' Une valeur inferieure a 'minHours' retournera 'minHours'.
     * @param hours Les heures qui seront coupees au besoin.
     * @return Le nombre d'heures reelles ou ajustees.
     */
    public int getTrimHours(int hours) {
        if(maxHours == -1) { return hours; }

        if(hours > this.maxHours) {
            return this.maxHours;
        } else if(hours < this.minHours) {
            return this.minHours;
        }
        return hours;
    }

    /**
     * Obtient le titre associe a un element de l'enum.
     * @return  Le titre d'une categorie.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Obtient le minimum d'heures associe a un element de l'enum.
     * @return  Le minimum d'heures d'une categorie.
     */
    public int getMinHours() {
        return this.minHours;
    }

    /**
     * Obtient le maximum d'heures associe a un element de l'enum.
     * @return  Le maximum d'heures d'une categorie.
     */
    public int getMaxHours() {
        return this.maxHours;
    }

    /**
     * Redefinit le minimum d'heures associe a un element de l'enum.
     * @param minHeure Le minimum d'heures a redefinir.
     */
    public void setMinHours(int minHeure) {
        this.minHours = minHeure;
    }

    /**
     * Redefinit le maximum d'heures associe a un element de l'enum.
     * @param maxHeure Le maximum d'heures a redefinir.
     */
    public void setMaxHours(int maxHeure) {
        this.maxHours = maxHeure;
    }
}
