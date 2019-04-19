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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe permettant d'écrire un fichier JSON
 * en sortie qui indique si la formation est complète
 * et les erreurs possibles.
 */

public class JSONWriter {

    private static ArrayList<String> errorMessages = new ArrayList<String>();
    private static boolean errorMessageEnabled = true;

    private static boolean complete;
    private static String filePath;

    /**
     * Crée une instance de JSONWriter.
     * 'complete' est 'true' si le cycle est complet, 'false' sinon.
     * @param filePath Chemin vers le fichier 'JSON' retourne par le programme.
     */
    JSONWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retourne un 'boolean' indiquant si la formation est complète.
     * @param complete  'true' si la formation est complete, 'false' sinon.
     */
    public static void setComplete(boolean complete) {
        JSONWriter.complete = complete;
    }

    /**
     * Genere le fichier JSON en sortie.
     * @throws IOException
     */
    public static void generate() throws IOException {
        File file = new File(JSONWriter.filePath);
        FileUtils.writeStringToFile(file, JSONWriter.getJSON().toString(2), "UTF-8");
    }

    /**
     * Retourne un 'JSONObject' représentant l'integralite du fichier de sortie.
     * @return Le 'JSONObject' se trouvant à être la racine(root) du fichier JSON final.
     */
    private static JSONObject getJSON() {
        JSONObject root = new JSONObject();
        root.put("complet", JSONWriter.complete);
        root.put("erreurs", JSONWriter.getErrorJSON());
        return root;
    }

    /**
     * Retourne un 'JSONArray' de tous les messages d'erreurs
     * ajoutes depuis le début de l'execution.
     * @return Le JSONArray contenant toutes les erreurs.
     */
    private static JSONArray getErrorJSON() {
        JSONArray errorsJSON = new JSONArray();
        errorsJSON.addAll(JSONWriter.errorMessages);
        return errorsJSON;
    }

    /**
     * Ajoute un message d'erreur à ajouter au fichier en sortie.
     * @param errorMsg Le message d'erreur à ajouter.
     * @return 'true' si le message a pu être ajouté a l'ArrayList.
     */
    public static boolean addError(String errorMsg) {
        if(JSONWriter.errorMessageEnabled) {
            JSONWriter.errorMessages.add(errorMsg);
        }
        return JSONWriter.errorMessageEnabled;
    }

    /**
     * Active le système d'ajoût de messages d'erreurs
     */
    public static void enableErrorMessaging() {
        JSONWriter.errorMessageEnabled = true;
    }

    /**
     * désactive le système d'ajoût de messages d'erreurs
     */
    public static void disableErrorMessaging() {
        JSONWriter.errorMessageEnabled = false;
    }

    /**
     * Quitte le programme lorsqu'on rencontre une erreur de structure.
     * Un message est affiché à la console et est inscrit dans le fichier JSON de sortie.
     * @param errorMsg Message d'erreur à ajouter
     */
    public static void exitOnStructureError(String errorMsg){
        prepareJsonForStructureExit(errorMsg);
        prepareStatsForStructureExit(errorMsg);

        try {
            JSONWriter.generate();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(errorMsg + "\nLes calculs ont été interrompus.");
        System.exit(2);
    }

    /**
     * Prépare le contenu de errorMessages avant de exitOnStructureError()
     * @param errorMsg  Message d'erreur à ajouter
     *
     */
    private static void prepareJsonForStructureExit(String errorMsg){
        JSONWriter.complete = false;
        JSONWriter.errorMessages.clear();
        JSONWriter.enableErrorMessaging();
        JSONWriter.addError(errorMsg);
    }

    /**
     * Prépare la sauvegarde avant de existOnStructureError()
     * @param errorMsg  Message d'erreur à ajouter
     */
    private static void prepareStatsForStructureExit(String errorMsg){
        SaveStatistics saveStats = new SaveStatistics();
        saveStats.executeCalculationForStructureExit(errorMsg);
    }

}
