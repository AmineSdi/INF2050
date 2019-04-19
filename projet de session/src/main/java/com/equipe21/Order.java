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

import java.util.ArrayList;

/**
 * Classe parent des differents ordres professionnels.
 */
public abstract class Order {

    private ArrayList<Validation<Activity>> activityValidations = new ArrayList<Validation<Activity>>();
    private ArrayList<Validation<CycleReport>> cycleReportValidations = new ArrayList<Validation<CycleReport>>();

    protected int maxTransferedHours = -1;

    public abstract void setCategoryEnum();
    public abstract void setValidations();

    /**
     * Constructeur pour un Order.
     * @param maxTransferedHours    Le maximum d'heures transferees
     *                              permis pour un ordre professionnel.
     */
    public Order(int maxTransferedHours) {
        this.maxTransferedHours = maxTransferedHours;

        this.addActivityValidation(new ValidationCategory());

        this.setValidations();
    }

    /**
     * Ajoute une validation pour une 'Activity'
     * @param activityValidation
     * @return  'true' si la validation de l'Activity a ete ajoutee
     */
    protected boolean addActivityValidation(Validation<Activity> activityValidation) {
        return activityValidations.add(activityValidation);
    }

    /**
     * Ajoute une validation pour un 'CycleReport'
     * @param cycleReportValidation
     * @return  'true' si la validation de le CycleReport a ete ajoutee
     */
    protected boolean addCycleReportValidation(Validation<CycleReport> cycleReportValidation) {
        return cycleReportValidations.add(cycleReportValidation);
    }

    /**
     * Obtient une validation pour les 'Activity'
     * @return  Un ArrayList de Validation d'Activity
     */
    public ArrayList<Validation<Activity>> getActivityValidations() {
        return activityValidations;
    }

    /**
     * Obtient une validation pour un 'CycleReport'
     * @return  Un ArrayList de Validation de CycleReport
     */
    public ArrayList<Validation<CycleReport>> getCycleReportValidations() {
        return cycleReportValidations;
    }


    /**
     * Obtient le maximum d'heures transferees possible pour un ordre.
     * @return Le maximum d'heures transferees possible.
     */
    public int getMaxTransferedHours() {
        return maxTransferedHours;
    }

    /**
     * Permet de retourner le titre associe a un certain ordre professionnel.
     * @return  Le titre sous forme de String.
     */
    public abstract String getOrderTitle();


}
