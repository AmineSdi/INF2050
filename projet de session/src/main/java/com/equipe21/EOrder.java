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
 * enum representant les differents ordres
 * professionnels que le programme reconnait.
 */
    public enum EOrder {

        ARCHITECTS("architectes", new Architect()),
        GEOLOGISTS("géologues", new Geologist()),
        PSYCHOLOGISTS("psychologues", new Psychologist()),
        PODIATRIST("podiatres", new Podiatrist()),
        INVALID_ORDER("invalid", null);

        private String title;
        private Order order;

        /**
         * Cree une nouvel ordre professionnel.
         *
         * @param title Le titre donne a l'ordre a ajouter.
         * @param order La classe heritant de Order correspondant a l'ordre declare.
         */
        EOrder(String title, Order order) {
            this.title = title;
            this.order = order;
        }

        /**
         * Retourne l'ordre associe au titre passe en argument si le titre existe.
         *
         * @param title Le titre recherche.
         * @return L'ordre associe au titre passe en parametre ou un ordre
         * invalide si elle n'existe pas.
         */
        public static EOrder fromTitle(String title) {
            for (EOrder order : EOrder.values()) {
                if (order.getTitle().equals(title)) {
                    return order;
                }
            }
            JSONWriter.exitOnStructureError(String.format("L'ordre \"%s\" n'est pas supporté par le programme. ", title));
            return INVALID_ORDER;
        }

        /** Obtient le titre de l'ordre
         * @return  un String représentant le titre de l'ordre
         */
        public String getTitle() {
            return this.title;
        }

        /** Obtient l'objet correspondant à l'ordre
         * @return  un nouvel objet correspondant à l'ordre
         */
        public Order getOrder() {
            return this.order;
        }
    }

