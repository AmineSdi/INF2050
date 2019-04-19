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
 * Classe qui a pour fonction de valider le cycle de formation
 * à partir d'un 'CycleReport'.
 */

public class ValidationCycle implements Validation<CycleReport> {

    private String validCycle;

    /**
     * Constructeur qui cree une instance de 'ValidationCycle'.
     * a partir d'un 'String'.
     * @param validCycle: String representant le cycle. Ex: "2018-2020".
     */
    ValidationCycle(String validCycle) {
        this.validCycle = validCycle;
    }

    /**
     * Verifie si le cycle associe au 'CycleReport' passe
     * en parametre correspond a l'attribut 'validCycle'. Arrete
     * l'execution du programme et laisse un message erreur sinon.
     * @param value Un CycleReport contenant un cycle a comparer.
     * @return 'true' si les 2 cycles sont egaux. Sinon
     *          retourne 'false' et ajoute un message d'erreurs.
     */
    public boolean validate(CycleReport value) {
        if(value.getCycle().equals(this.validCycle)) {
            return true;
        }
        return false;
    }
}
