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
 * Classe qui a pour fonction de valider les catégories d'activités de formation
 * à partir d'une 'Activity'.
 */

public class ValidationCategory implements Validation<Activity> {

    /**
     * Verifie si la categorie associee a 'Activity' est valide.
     * Ajoute un message d'erreur si la categorie est invalide.
     * @param value Une 'Activity' contenant les attributs d'une activite.
     * @return 'true' si la categorie existe dans 'ECategory', 'false' sinon.
     */
    public boolean validate(Activity value) {
        if(ECategory.isCategory(value.getCategory())) {
            return true;
        }
        JSONWriter.addError("L'activité " + value.getDescription() + " est dans une catégorie non reconnue. Elle sera ignorée.");
        return false;
    }
}
