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

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe qui permet de lire le contenu
 * d'un fichier JSON.
 */

public class JSONReader {

    private final String FILE_NOT_FOUND = "Le fichier n'existe pas.";
    private final String FILE_NOT_JSON_FORMAT = "Le fichier source n'est pas sous un format JSON valide. ";

    private String filePath;
    private JSONObject root;

    /**
     * Cree une instance de 'JSONReader' a partir d'un chemin menant au fichier JSON.
     * @param filePath Le chemin vers le fichier JSON.
     * @throws IOException, JSONException
     */
    JSONReader(String filePath) throws IOException {
        this.filePath = filePath;
        try {
            String io = IOUtils.toString(new FileInputStream(filePath), "UTF-8");
            this.root = (JSONObject) JSONSerializer.toJSON(io);
        } catch (FileNotFoundException e) {
            exitUponInputError(FILE_NOT_FOUND);
        } catch (JSONException e){
            exitUponInputError(FILE_NOT_JSON_FORMAT);
        }
    }

    /**
     * Quitte le programme si le fichier input est invalide.
     * @param errorMsg
     */
    private void exitUponInputError(String errorMsg){
        System.out.println(errorMsg);
        System.exit(1);
    }

    /**
     * Cree un 'ArrayList<JSONobject>' contenant toutes les activites.
     * @return Un 'ArrayList<JSONobject>' contenant toutes les activites.
     */
    public ArrayList<JSONObject> getActivities() {
        ArrayList<JSONObject> activities = new ArrayList<JSONObject>();
        for(Object activity : this.root.getJSONArray("activites")){
            activities.add((JSONObject)activity);
        }
        return activities;
    }

    /**
     * Obtient le JSONObject contenant tout le contenu d'une declaration.
     * @return  Le JSONObject correspondant a une declaration de formation.
     */
    public JSONObject getRoot() {
        return this.root;
    }

}
