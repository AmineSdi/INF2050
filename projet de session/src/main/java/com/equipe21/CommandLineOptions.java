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

import org.apache.commons.cli.*;

/**
 * Permet de gérer la lecture des options à la ligne de commande
 */

public class CommandLineOptions {

    private Options options;

    public CommandLineOptions() {
        defineOptions();
    }

    /**
     * Ajoute et définit les options de la ligne de commande
     * @return  options Les options de la ligne de commande
     */
    private void defineOptions() {
        Options options = new Options();
        options.addOption("S", false, "produit une liste de statistiques");
        options.addOption("SR", false, "réinitialise les statistiques");
        this.options = options;
    }

    /**
     * Prépare l'objet CommandLine
     * @param args      Arguments éventuels à la ligne de commande
     * @return  un objet CommandLine
     */
    public CommandLine getCommandLine(String[] args) {
        CommandLineParser cliParser = new DefaultParser();
        CommandLine cli = null;
        try {
            cli = cliParser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("Erreur lors du traitement de l'option à la ligne de commande.");
            System.exit(1);
        }
        return cli;
    }
}
