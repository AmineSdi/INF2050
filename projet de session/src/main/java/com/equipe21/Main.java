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

import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.cli.*;

/**
 * Classe principale qui permet d'appeler les outils
 * de lecture et d'ecriture de fichiers JSON.
 */

public class Main {

    private static final String NO_FILE = "Aucun fichier en argument.";
    private static final String WRONG_INPUT_FORMAT = "Mauvais format de fichier d'entrée.\nLe fichier doit avoir une extension.'.json' ou .txt";
    private static final String WRONG_OUTPUT_FORMAT = "Mauvais format de fichier de sortie.\nLe fichier doit être un .json ou .txt";
    private static final String NO_OUTPUT_FILE = "Aucun argument pour le fichier de sortie.";
    private static final String TOO_MANY_ARGUMENTS = "Il y a trop d'arguments en paramètre. Veuillez n'en mettre que deux";
    private static final String CONFIRM_RESET = "Souhaitez-vous vraiment réinitialiser les statistiques? (oui/non)";
    private static final String MAX_ARGS_SR = "L'option -SR ne peut prendre pas prendre de fichier en argument.";
    private static final String MAX_ARGS_S = "L'option -S ne peut prendre pas prendre de fichier en argument.";
    /**
     * Permet de faire un pont entre les outils de lecture du fichier
     * recu en parametre et l'outil d'ecriture du fichier en sortie.
     * @param args  Reçoit le fichier JSON et celui pour la sortie.
     * @throws IOException
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        CommandLineOptions cliOptions = new CommandLineOptions();
        CommandLine cli = cliOptions.getCommandLine(args);
        checkCommandLineArgs(cli, args);

        JSONWriter writer = new JSONWriter(args[1]);
        JSONReader reader = new JSONReader(args[0]);
        CycleReport report = new CycleReport(reader.getRoot());
        writer.setComplete(report.getCycleIsComplete());
        writer.generate();
        saveStatistics(report);
    }

    /**
     * Vérifie si le logiciel est lancé avec le bon nombre d'arguments.
     * Lance un message d'erreur personnalisé selon le nombre d'arguments.
     * @param args Fichiers en argument
     */
    private static void validateNumberOfArgs(String[] args) {
        if (args.length < 1) {
            exitUponFileError(NO_FILE);
        }
        if (args.length == 1) {
            exitUponFileError(NO_OUTPUT_FILE);
        }
        if (args.length > 2) {
            exitUponFileError(TOO_MANY_ARGUMENTS);
        }
    }

    /**
     * Verifie la validite des fichiers a partir de son extension.
     * Si l'output est invalide, arrete l'execution avec un message d'erreur.
     * @param output    Fichier JSON en argument pour ecriture.
     * @param input     Fichier JSON en argument pour lecture.
     */
    private static void validateFilenameExtension(String input, String output) {
        if (!input.endsWith(".json") && !input.endsWith(".txt")) {
            exitUponFileError(WRONG_INPUT_FORMAT);
        }
        if (!output.endsWith(".json") && !output.endsWith(".txt")) {
            exitUponFileError(WRONG_OUTPUT_FORMAT);
        }
    }

    /**
     * Affiche un message d'erreur à la console et termine le logiciel.
     * @param errorMessage Message d'erreur à lancer à la console.
     */
    private static void exitUponFileError(String errorMessage) {
        System.out.println(errorMessage);
        System.exit(1);
    }

    /**
     * Vérifie si une option passée est passée à la ligne de commande
     * pour générer un fichier de statistique ou les réinitialiser.
     * @param cli   L'outil pour lire à la ligne de commande
     */
    private static void checkCommandLineArgs(CommandLine cli, String[] args) throws IOException, ClassNotFoundException {
        if (cli.hasOption("S")) {
            chooseOptionS(cli);
        } else if (cli.hasOption("SR")) {
            chooseOptionSR(cli);
        } else {
            validateNumberOfArgs(args);
            validateFilenameExtension(args[0], args[1]);
        }
    }

    /**
     * Si l'option S est utilisée, vérifie le nombre de paramètres.
     * @param cli     L'outil pour lire à la ligne de commande
     */
    private static void chooseOptionS(CommandLine cli) {
        if (cli.getArgs().length == 0) {
            SaveStatistics saveStats = new SaveStatistics();
            saveStats.showStats();
            System.exit(0);
        } else {
            exitUponFileError(MAX_ARGS_S);
        }
    }

    /**
     * Si l'option SR est utilisée, vérifie le nombre
     * de paramètres et demande une confirmation.
     * @param cli   L'outil pour lire à la ligne de commande
     */
    private static void chooseOptionSR(CommandLine cli) {
        if (cli.getArgs().length == 0) {
            if (confirmResetStats()) {
                SaveStatistics saveStats = new SaveStatistics();
                saveStats.resetStats();
            }
            System.exit(0);
        } else {
            exitUponFileError(MAX_ARGS_SR);
        }
    }

    /**
     * Confirme avec l'utilisateur la réinitialisation des statistiques
     * @return  true si l'utilisateur confirme
     */
    private static boolean confirmResetStats() {
        String answer = "";
        do {
            System.out.println(CONFIRM_RESET);
            Scanner scan = new Scanner(System.in);
            answer = scan.next();
        } while(!answer.equals("oui") && !answer.equals("non"));

        return answer.equals("oui");
    }

    /**
     * Appelle les méthodes de sauvegarde de statistiques
     * @param report Le "CycleReport" courant
     */
    private static void saveStatistics(CycleReport report) {
        SaveStatistics saveStats = new SaveStatistics();
        saveStats.executeCalculation(report);
    }
}
