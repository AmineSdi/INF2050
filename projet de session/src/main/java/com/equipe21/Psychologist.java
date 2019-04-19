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

import java.util.Arrays;

public class Psychologist extends Order {
    final public String CYCLE               = "2016-2021";
    final public String MIN_DATE            = "2016-01-01";
    final public String MAX_DATE            = "2021-01-01";

    /**
     * Constructeur permettant de manipuler les attributs d'un psychologue.
     */
    public Psychologist() {
        super(-1);
    }

    /**
     * Permet  de redefinir les champs de chacune des valeurs
     * de 'ECategory' selon les spécifications des geologues.
     */
    @Override
    public void setCategoryEnum() {
        ECategory.CONFERENCE.setMaxHours(15);

    }

    /**
     * Ajuste les spécificites des validations en fonction du cycle
     * concerne et les ajoute aux ArrayList pour la validation
     */
    @Override
    public void setValidations() {
        ValidationCycle valCycle = new ValidationCycle(this.CYCLE);
        ValidationAny<CycleReport> valAnyCycle = new ValidationAny<>(Arrays.asList(valCycle), (value) -> {
            JSONWriter.exitOnStructureError(String.format("Le cycle est invalide pour l'ordre des psychologues"));
        });

        addValidationCycleReport(valAnyCycle);
        addValidationActivity();
    }

    /**
     * Ajoute des validations à faire à l'ArrayList 'activityValidation'
     */
    private void addValidationActivity() {
        this.addActivityValidation(new ValidationDate("yyyy-MM-dd", this.MIN_DATE, this.MAX_DATE, this.CYCLE));
        this.addActivityValidation(new ValidationHours());
        this.addActivityValidation(new ValidationDescription());
    }

    /**
     * Ajoute des validations à faire à l'ArrayList 'cycleReportValidations'
     * @param valAnyCycle   Une validation pour au moins un élément
     */
    private void addValidationCycleReport(ValidationAny<CycleReport> valAnyCycle) {
        this.addCycleReportValidation(valAnyCycle);
        this.addCycleReportValidation(new ValidationCycle(this.CYCLE));
        this.addCycleReportValidation(new ValidationMinimumCategory(new ECategory[] {ECategory.CLASS}, 25));
        this.addCycleReportValidation(new ValidationRequiredHours(90));
        this.addCycleReportValidation(new ValidationName());
        this.addCycleReportValidation(new ValidationFamilyName());
        this.addCycleReportValidation(new ValidationSex());
        this.addCycleReportValidation(new ValidationLicenseNumber());
    }

    /**
     * Obtient le titre representant l'ordre associe.
     * @return "psychologues" en String.
     */
    @Override
    public String getOrderTitle() {
        return EOrder.PSYCHOLOGISTS.getTitle();
    }

}


