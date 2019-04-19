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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Validation de type 'All' qui sert a creer un 'AND' entre
 * les validations passées au constructeurs. Vérifie que toutes
 * les validations passées au constructeur sont valides.
 */
public class ValidationAll<T> implements Validation <T> {

    private List<Validation<T>> validations = new ArrayList<>();
    private Consumer<T> noMatchHandler = null;

    /**
     * @param validations Les validations qui seront testees.
     * @param noMatchHandler fonction de type 'void func(T value)' ou '(T value) -> {print(123)}'
     *                       qui sera executer si tout les test echouent.
     */
    ValidationAll(List<Validation<T>> validations, Consumer<T> noMatchHandler) {
        this.validations = validations;
        this.noMatchHandler = noMatchHandler;

    }

    /**
     * Valide si toutes les validations sont vraies,
     * sinon execute la fonction passee au constructeur.
     * @param value: valeur a valider.
     * @return 'true' si toutes les validations sont valides.
     */
    public boolean validate(T value) throws ParseException {
        for (Validation<T> validation: validations) {
            if( ! validation.validate(value) ) {
                this.noMatchHandler.accept(value);
                return false;
            }
        }
        return true;
    }
}

