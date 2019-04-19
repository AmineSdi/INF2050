/*
 *MIT License
 *
 *Copyright (c) 2019 equipe21
 *
 *Permission is hereby granted, free of charge, to any person obtaining a copy
 *of this software and associated documentation files (the "Software"), to deal
 *in the Software without restriction, including without limitation the rights
 *to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 *furnished to do so, subject to the following conditions:
 *
 *The above copyright notice and this permission notice shall be included in all
 *copies or substantial portions of the Software.
 *
 *THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *SOFTWARE.
 *
 */

package com.equipe21;

/**
 * Classe qui a pour fonction de valider
 * le permis en fonction de l'ordre.
 */

public class ValidationLicenseNumber implements Validation<CycleReport>{

    //Format des numéros de permis
    //Architectes: A ou T suivi de 4 chiffres, exemples: A0000 ou T0000
    private final String ARCHITECT_REGEX = "[A|T]([0-9]){4}";

    //Psychologues: 5 chiffres suivis d'un tiret, suivis de 2 chiffres, exemple: 51-51515
    private final String PSYCHOLOGIST_REGEX = "[0-9]{5}-[0-9]{2}";

    //Geologues: Première lettre du nom de famille suivie de la première lettre du prénom,
    //suivis de 4 chiffres, exemple: (nom: Jule, prenom: Cesar) alors CJ0000
    private final String GEOLOGIST_REGEX = "[A-Z]{2}[0-9]{4}";

    //Podiatres: 5 chiffres seulement, exemple: 50414
    private final String PODIATRIST_REGEX = "[0-9]{5}";

    /**
     * Valide le numéro de permis selon l'ordre. En utilisant
     * les regex, on vérifie si le numéro de permis pour un
     * ordre donné est sous le bon format. Sinon on appelle "errorMsg()".
     *
     * @param value: "CycleReport" courant.
     * @return true si le numéro de permis est valide, false sinon.
     */
    @Override
    public boolean validate(CycleReport value) {
        if(matchLicenseNumberAndOrder(value, ARCHITECT_REGEX, EOrder.ARCHITECTS)){
            return true;
        }else if(matchLicenseNumberAndOrder(value, PSYCHOLOGIST_REGEX, EOrder.PSYCHOLOGISTS)){
            return true;
        } else if(matchLicenseNumberAndOrder(value, PODIATRIST_REGEX, EOrder.PODIATRIST)){
            return true;
        } else if(matchGeologist(value)){
            return true;
        }
        return errorMsg();
    }

    /**
     * Valide le numéro de permis.
     *
     * @param value "CycleReport" courant.
     * @param regex Regex pour vérifier le format du numéro de permis
     * @param order Ordre à vérifier
     * @return true si le numero de permis correspond
     * au regex et que l'ordre est le bon, false sinon.
     */
    private boolean matchLicenseNumberAndOrder(CycleReport value, String regex, EOrder order) {
        return value.getNumLicense().matches(regex) && matchOrder(order.getTitle(), value);
    }

    /**
     * Vérifie le numéro de permis pour géologue et podiatre.
     *
     * @param value "CycleReport" courant.
     * @return true si le numéro de permis correspond
     * au regex et que l'ordre est le bon, false sinon.
     */
    private boolean matchGeologist(CycleReport value) {
        return value.getNumLicense().matches(GEOLOGIST_REGEX) &&
                (matchOrder(EOrder.GEOLOGISTS.getTitle(), value))
                && matchFirstLetterOfLastAndFirstName(value);
    }

    /**
     * Vérifie l'ordre inscrit dans le fichier.
     *
     * @param order le titre de l'ordre à vérifier.
     * @param value "CycleReport" courant.
     * @return true si l'ordre est le bon.
     */
    private boolean matchOrder(String order, CycleReport value){
        return value.getOrder().getOrderTitle().equals(order);
    }

    /**
     * Vérifie si la première lettre du nom et du prénom correspondent
     * au deux lettres du numéro de permis.
     *
     * @param value "CycleReport" courant.
     * @return true si les lettres sont les mêmes, false sinon.
     */
    private boolean matchFirstLetterOfLastAndFirstName(CycleReport value) {
        return value.getNumLicense().charAt(0) == value.getFamilyName().toUpperCase().charAt(0)
                && value.getNumLicense().charAt(1) == value.getName().toUpperCase().charAt(0);
    }

    /**
     * Permet d'écrire le message d'erreur et de l'ajouter.
     *
     * @return false seulement
     */
    private boolean errorMsg() {
        JSONWriter.enableErrorMessaging();
        JSONWriter.addError("Le numéro de permis n'est pas du bon format.");
        JSONWriter.exitOnStructureError("Le numéro de permis n'est pas du bon format.");
        return false;
    }
}
