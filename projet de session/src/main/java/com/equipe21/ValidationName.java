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
 * Classe qui a pour fonction de valider
 * si le fichier en entrée contient un prenom.
 */
public class ValidationName implements Validation<CycleReport>{

    /**
     * Vérifie si le champ contenant le prénom est vide.
     * Si celui-ci est vide ou ne contient que des espaces,
     * la méthode retournera false et générera un message d'erreur.
     *
     * @param value: un CycleReport contenant un prénom à comparer.
     * @return True si le champ contient des caractères.
     */
    public boolean validate(CycleReport value) {
        if ((!value.getName().trim().isEmpty())) {
            return true;
        }
        errorMsg();
        return false;
    }

    /**
     * Permet d'écrire le message d'erreur et de l'ajouter.
     */
    private void errorMsg() {
        JSONWriter.enableErrorMessaging();
        JSONWriter.addError("Le champ contenant le prénom est vide.");
    }

}
