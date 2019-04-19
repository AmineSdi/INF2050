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

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe qui a pour fonction de valider les dates
 * d'activites de formation a partir d'une 'Activity'.
 */

public class ValidationDate implements Validation<Activity> {

    private String format;
    private String minDate;
    private String maxDate;
    private String cycle;

    /**
     * Constructeur qui cree un instance de 'ValidationDate'
     *
     * @param format:  Le format utilise pour convertir un 'String' en 'Date'.
     * @param minDate: La date minimum, en String, respectant le format.
     * @param maxDate: La date maximum, en String, respectant le format.
     * @param cycle: le cycle associe aux bornes.
     */
    public ValidationDate(String format, String minDate, String maxDate, String cycle) {
        this.format = format;
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.cycle = cycle;
    }

    //----------------------------------------

    /*** Retourne 'true' si la date se trouve dans l'intervalle definie
     * par les attributs 'minDate' et 'maxDate' et a le format ISO 8601.
     * Ajoute un message d'erreur si la date n'est pas dans l'intervalle.
     * @param value Une Activity comprenant ses differents attributs.
     * @return 'true' si le format et l'intervalle sont respectes, 'false' sinon.
     */
    public boolean validate(Activity value) throws ParseException{
        validateISOFormat(value);
        if(! value.getCycleReport().getCycle().equals(cycle)) {
            return false;
        }
        if(!validateRangeDates(value)) {
            JSONWriter.addError(String.format("La date est hors du cycle %s pour l'activité %s. Elle sera ignorée.",value.getCycleReport().getCycle(),value.getDescription()));
            return false;
        }
        return true;
    }

    /**
     * Verifie que la date est dans le bon intervalle.
     * @param value Une Activity comprenant ses differents attributs.
     * @return 'true' si la date est dans le bon intervalle,
     *          'false' sinon et s'il y a un ParseException.
     */
    private boolean validateRangeDates(Activity value) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        if (dateFormat.parse(value.getDate()).before(dateFormat.parse(this.minDate)) ||
                dateFormat.parse(value.getDate()).after(dateFormat.parse(this.maxDate))) {
            return false;
        }
        return true;
    }

    /**
     * Verifie que la date est en format ISO 8601. Arrete
     * l'execution du programme et laisse un message erreur sinon.
     * @param value Une Activity comprenant ses differents attributs.
     */
    private void validateISOFormat(Activity value){
        String ISORegex = "[0-9]{4}-([0][1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
        if (!value.getDate().matches(ISORegex)) {
            System.out.println(000);
            JSONWriter.exitOnStructureError(String.format("La date pour l'activité \"%s\" n'est pas sous le bon format.", value.getDescription()));
        }
    }
}
