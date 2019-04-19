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
 * Classe qui a pour fonction de valider les heures déclarées pour
 * une activite de formation à partir d'une 'Activity'.
 */

public class ValidationHours implements Validation<Activity> {

    /**
     * Valide que les heures sont superieures a '1' et
     * comportent uniquement des entiers. Ajoute un message
     * d'erreur et arrete l'execution sinon.
     * @param value Une 'Activity' comprenant ses differents attributs.
     * @return 'true' si les heures sont valides, arrete l'execution sinon.
     */
    public boolean validate(Activity value) {
        if(value.getCycleReport().isOnlyIntegers(value.getHours())){
            if(Integer.parseInt(value.getHours()) > 0) {
                return true;
            }
        }
        JSONWriter.exitOnStructureError("Les heures de l'activité " + value.getDescription() + " ne sont pas valides. ");
        return false;
    }


}
