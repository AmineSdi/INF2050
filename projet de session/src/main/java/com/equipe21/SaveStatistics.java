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
 * Sauvegarde et charge les statistiques d'un
 * objet "Statistics" sous forme de fichier .bin
 */

import java.io.*;

public class SaveStatistics {

    private Statistics pastStats;

    public SaveStatistics() {
    }

    /**
     * Effectue toutes les étapes de l'accumulation et de
     * la sauvegarde des statistiques en temps normal
     * @param report    Le CycleReport du cycle courant
     */
    public void executeCalculation(CycleReport report) {
        loadStats();
        StatsCalculation stats = new StatsCalculation(report, pastStats);
        stats.generateStats("");
        saveStats(stats.getNewStats());
    }

    /**
     * Effectue toutes les étapes de l'accumulation et de la
     * sauvegarde des statistiques si le programme doit
     * s'arrêter avant la fin
     * @param errorMsg    Message d'erreur pour un permis invalide
     */
    public void executeCalculationForStructureExit(String errorMsg) {
        loadStats();
        StatsCalculation stats = new StatsCalculation(pastStats);
        stats.generateStats(errorMsg);
        saveStats(stats.getNewStats());
    }

    /**
     * Si l'option -SR est choisie, les statistiques sont
     * réinitialisées et l'objet est sauvegardé dans son nouvel état
     */
    public void resetStats() {
        saveStats(new Statistics());
        System.out.println("Les statistiques ont été réinitialisées.");
    }

    /**
     * Affiche les statistiques à la console
     */
    public void showStats() {
        loadStats();
        System.out.println("----- Statistiques -----\n");
        System.out.println(pastStats.toString());
    }

    /**
     * Sauvegarde les statistiques pour récupération
     * lors d'une exécution ultérieure
     */
    private void saveStats(Statistics stats) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("statsSaved.bin"));
            output.writeObject(stats);
            output.close();
        } catch (IOException e) {
            System.out.println("Un problème a été rencontré lors de la sauvegarde des statistiques");
        }
    }

    /**
     * Charge les statistiques des exécutions précédentes ou
     * crée une nouvelle sauvegarde si celle-ci n'existe pas.
     */
    private void loadStats() {
        try {
            inputPastStats();
        } catch (ClassNotFoundException e) {
            System.out.println("Les statistiques n'ont pu être récupérées.\nElles ont été réinitialisées.");
            saveStats(new Statistics());
        } catch (IOException e) {
            saveStats(new Statistics());
        }
    }

    /**
     * Récupère les statistiques passées depuis la sauvegarde
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private void inputPastStats() throws ClassNotFoundException, IOException {
        checkIfFileExists();
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("statsSaved.bin"));
        this.pastStats = (Statistics) input.readObject();
        input.close();
    }

    /**
     * Crée le fichier de sauvegarde s'il n'existe pas
     */
    private void checkIfFileExists() {
        File file = new File("statsSaved.bin");
        if (!file.exists()) {
            saveStats(new Statistics());
        }
    }
}
