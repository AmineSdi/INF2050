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

/**
 * Classe utilisée si pour redéfinir certaines règles si
 * l'ordre déclaré est celui des architectes.
 */

public class Architect extends Order {

    /**
     * Constructeur permettant de manipuler les attributs d'un architecte.
     */

    public Architect() {
        super(7);
    }

    /**
     * Permet  de redéfinir les champs de chacune des valeurs
     * de 'ECategory' selon les spécifications des architectes.
     * Ne fait que modifier certain champs. Les valeurs par defaut
     * de 'ECategory' ne sont pas touchees.
     */
    @Override
    public void setCategoryEnum() {
        ECategory.PRESENTATION.setMaxHours(23);
        ECategory.DISCUSSION_GR.setMaxHours(17);
        ECategory.RESEARCH_PR.setMaxHours(23);
        ECategory.PRO_REDACTION.setMaxHours(17);
    }

    /**
     * Permet de modifier les attributs de Architect
     * selon les spécifications du cycle 2016-2018.
     * @param valCycle une validation des attributs d'un rapport
     * @param requiredHours
     * @return  Objet ValidationAll<CycleReport> testant la validité de tous les éléments
     */
    private ValidationAll<CycleReport> getValidationReqHours(Validation<CycleReport> valCycle, int requiredHours) {
        ValidationRequiredHours valReqHours = new ValidationRequiredHours(requiredHours);
        ValidationAll<CycleReport> valAll = new ValidationAll<CycleReport>(Arrays.asList(valCycle, valReqHours), (value) -> {
        });
        return valAll;
    }

    /**
     * Ajuste les spécificites des validations en fonction du cycle
     * concerne et les ajoute aux ArrayList pour la validation
     */
    @Override
    public void setValidations() {
        ValidationCycle valCycle1416 = new ValidationCycle("2014-2016");
        ValidationCycle valCycle1618 = new ValidationCycle("2016-2018");
        ValidationCycle valCycle1820 = new ValidationCycle("2018-2020");
        ValidationAny<CycleReport> valAnyCycle = validateCycle(valCycle1416, valCycle1618, valCycle1820);
        ValidationAll<CycleReport> valReqHours1416 = this.getValidationReqHours(valCycle1416, 42);
        ValidationAll<CycleReport> valReqHours1618 = this.getValidationReqHours(valCycle1618, 42);
        ValidationAll<CycleReport> valReqHours1820 = this.getValidationReqHours(valCycle1820, 40);
        ValidationAny<CycleReport> valAnyReqHours = validateRequiredHours(valReqHours1416, valReqHours1618, valReqHours1820);
        ValidationDate valDate1416 = new ValidationDate("yyyy-MM-dd", "2014-04-01", "2016-07-01", "2014-2016");
        ValidationDate valDate1618 = new ValidationDate("yyyy-MM-dd", "2016-04-01", "2018-04-01", "2016-2018");
        ValidationDate valDate1820 = new ValidationDate("yyyy-MM-dd", "2018-04-01", "2020-04-01","2018-2020");
        ValidationAny<Activity> valAnyDate = validateDate(valDate1416, valDate1618, valDate1820);
        addValidationCycle(valAnyCycle, valAnyReqHours);
        addValidationActivities(valAnyDate);
    }

    /**
     * Ajoute des validations à faire à l'ArrayList 'cycleReportValidations'
     * @param valAnyCycle
     * @param valAnyReqHours
     */
    private void addValidationCycle(ValidationAny<CycleReport> valAnyCycle, ValidationAny<CycleReport> valAnyReqHours) {
        this.addCycleReportValidation(valAnyCycle);
        this.addCycleReportValidation(valAnyReqHours);
        this.addCycleReportValidation(new ValidationName());
        this.addCycleReportValidation(new ValidationFamilyName());
        this.addCycleReportValidation(new ValidationSex());
        this.addCycleReportValidation(new ValidationLicenseNumber());
        ECategory[] categoriesArchitect = new ECategory[]
                {ECategory.CLASS, ECategory.WORKSHOP, ECategory.COLLOQUY, ECategory.SEMINAR, ECategory.CONFERENCE, ECategory.DIRLECTURE};
        this.addCycleReportValidation(new ValidationMinimumCategory(categoriesArchitect, 17));
    }

    /**
     * Ajoute des validations à faire à l'ArrayList 'activityValidation'
     * @param valAnyDate
     */
    private void addValidationActivities(ValidationAny<Activity> valAnyDate) {
        this.addActivityValidation(valAnyDate);
        this.addActivityValidation(new ValidationHours());
        this.addActivityValidation(new ValidationDescription());
    }

    /**
     * Retourne une ValidationAny<Activity> pour tester
     * l'appartenance d'une date a un cycle.
     * @param valDate1416   validation d'une date du cycle 2014-2016
     * @param valDate1618   validation d'une date du cycle 2016-2018
     * @param valDate1820   validation d'une date du cycle 2018-2020
     * @return  ValidationAny<Activity> testant l'appartenance à un cycle.
     */
    private ValidationAny<Activity> validateDate(ValidationDate valDate1416, ValidationDate valDate1618, ValidationDate valDate1820) {
        return new ValidationAny<>(Arrays.asList(valDate1416, valDate1618, valDate1820), (value) -> {
                JSONWriter.addError(String.format("La date '%s' n'est pas dans le cycle", value.getDate()));
            });
    }

    /**
     * Retourne une ValidationAny<CycleReport> pour tester
     * le nombre d'heures minimum requis.
     * @param valReqHours1416   validation des heures requis du cycle 2014-2016
     * @param valReqHours1618   validation des heures requis du cycle 2016-2018
     * @param valReqHours1820   validation des heures requis du cycle 2018-2020
     * @return  ValidationAny<CycleReport> testant le minimum d'heures requis.
     */
    private ValidationAny<CycleReport> validateRequiredHours(ValidationAll<CycleReport> valReqHours1416, ValidationAll<CycleReport> valReqHours1618, ValidationAll<CycleReport> valReqHours1820) {
        return new ValidationAny<>(Arrays.asList(valReqHours1416, valReqHours1618, valReqHours1820), (value) -> {
                JSONWriter.addError(String.format("Le nombre d'heures requis pour le cycle %s n'a pas été atteint (%d)",value.getCycle(), value.getTotalHours()));
            });
    }

    /**
     * Retourne une ValidationAny<CycleReport> pour tester
     * la validité du cycle pour l'ordre des architectes.
     * @param valCycle1416  validation du cycle 2014-2016
     * @param valCycle1618  validation du cycle 2016-2018
     * @param valCycle1820  validation du cycle 2018-2020
     * @return ValidationAny<CycleReport> qui teste la validite du cycle.
     */
    private ValidationAny<CycleReport> validateCycle(ValidationCycle valCycle1416, ValidationCycle valCycle1618, ValidationCycle valCycle1820) {
        return new ValidationAny<>(Arrays.asList(valCycle1416, valCycle1618, valCycle1820), (value) -> {
                JSONWriter.exitOnStructureError(String.format("Le cycle est invalide pour l'ordre des architectes."));
            });
    }

    /**
     * Obtient le titre representant l'ordre associe.
     * @return "architectes" en String.
     */
    public String getOrderTitle() {
        return EOrder.ARCHITECTS.getTitle();
    }
}
