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

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class MockInputFile {

    private final String architect = "{" +
            " \"ordre\": \"architectes\", " +
            "\"numero_de_permis\": \"A0001\"," +
            "\"nom\": \"Bond\"," +
            "\"prenom\": \"James\"," +
            "\"sexe\": \"1\"," +
            "\"cycle\": \"2016-2018\"," +
            "\"heures_transferees_du_cycle_precedent\": 7," +
            "\"activites\": [{" +
            "    \"description\": \"Séminaire sur l'architecture contemporaine\"," +
            "    \"categorie\": \"cours\"," +
            "    \"heures\": 17," +
            "    \"date\": \"2016-04-07\"" +
            " }]}";

    private final String psychologist = "{" +
            "\"ordre\": \"psychologues\"," +
            "  \"numero_de_permis\": \"12345-12\"," +
            "\"nom\": \"Bond\"," +
            "\"prenom\": \"James\"," +
            "\"sexe\": \"1\"," +
            "  \"cycle\": \"2016-2021\"," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire sur l'architecture contemporaine\"," +
            "      \"categorie\": \"cours\"," +
            "      \"heures\": 25," +
            "      \"date\": \"2016-04-07\"" +
            "    }]}";

    private final String geologist = "{" +
            " \"ordre\": \"géologues\"," +
            "  \"numero_de_permis\": \"BJ0001\"," +
            "\"nom\": \"Bond\"," +
            "\"prenom\": \"James\"," +
            "\"sexe\": \"0\"," +
            "  \"cycle\": \"2016-2019\"," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire sur l'architecture contemporaine\"," +
            "      \"categorie\": \"cours\"," +
            "      \"heures\": 22," +
            "      \"date\": \"2017-09-07\"" +
            "    }," +
            "    {" +
            "      \"description\": \"Rédaction pour le magazine Architecture moderne\"," +
            "      \"categorie\": \"projet de recherche\"," +
            "      \"heures\": 3," +
            "      \"date\": \"2016-10-22\"" +
            "    }," +
            "    {" +
            "      \"description\": \"Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans\"," +
            "      \"categorie\": \"groupe de discussion\"," +
            "      \"heures\": 1," +
            "      \"date\": \"2017-04-01\"" +
            "    }]}";

    private final String psychologistComplete = "{" +
            " \"ordre\": \"psychologues\"," +
            "  \"numero_de_permis\": \"54356-23\"," +
            "\"nom\": \"Bond\"," +
            "\"prenom\": \"James\"," +
            "\"sexe\": \"0\"," +
            "  \"cycle\": \"2016-2021\"," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire sur l'architecture contemporaine\"," +
            "      \"categorie\": \"cours\"," +
            "      \"heures\": 20," +
            "      \"date\": \"2016-04-07\"" +
            "    }," +
            "    {" +
            "      \"description\": \"Rédaction pour le magazine Architecture moderne\"," +
            "      \"categorie\": \"rédaction professionnelle\"," +
            "      \"heures\": 10," +
            "      \"date\": \"2016-10-22\"" +
            "    }," +
            "    {" +
            "      \"description\": \"Visite d'établissements architecturaux\"," +
            "      \"categorie\": \"cours\"," +
            "      \"heures\": 20," +
            "      \"date\": \"2016-04-02\"" +
            "    }," +
            "    {" +
            "      \"description\": \"Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans\"," +
            "      \"categorie\": \"groupe de discussion\"," +
            "      \"heures\": 30," +
            "      \"date\": \"2016-04-01\"" +
            "    }]}";

    private final String podiatrist = "{" +
            " \"ordre\": \"podiatres\"," +
            "  \"numero_de_permis\": \"00001\"," +
            "\"nom\": \"Bond\"," +
            "\"prenom\": \"James\"," +
            "\"sexe\": \"2\"," +
            "  \"cycle\": \"2016-2019\"," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire sur l'architecture contemporaine\"," +
            "      \"categorie\": \"cours\"," +
            "      \"heures\": 22," +
            "      \"date\": \"2016-08-07\"" +
            "    }," +
            "    {" +
            "      \"description\": \"Rédaction pour le magazine Architecture moderne\"," +
            "      \"categorie\": \"projet de recherche\"," +
            "      \"heures\": 3," +
            "      \"date\": \"2016-10-22\"" +
            "    }," +
            "    {" +
            "      \"description\": \"Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans\"," +
            "      \"categorie\": \"groupe de discussion\"," +
            "      \"heures\": 1," +
            "      \"date\": \"2016-09-01\"" +
            "    }]}";

    private final String architectInvalid = "{" +
            "  \"ordre\": \"architectes\"," +
            "  \"numero_de_permis\": \"H0001\"," +
            "\"nom\": \"Bond3\"," +
            "\"prenom\": \"James1\"," +
            "\"sexe\": \"\"," +
            "  \"cycle\": \"2015-2016\"," +
            "  \"heures_transferees_du_cycle_precedent\": 7," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire\"," +
            "      \"categorie\": \"jouet\"," +
            "      \"heures\": 5.3," +
            "      \"date\": \"2015-04-07\"" +
            "    }]}";

    private final String psychologistInvalid = "{" +
            "\"ordre\": \"psychologues\"," +
            "  \"numero_de_permis\": \"E2345-12\"," +
            "\"nom\": \"\"," +
            "\"prenom\": \"\"," +
            "\"sexe\": \"99\"," +
            "  \"cycle\": \"2014-2023\"," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire\"," +
            "      \"categorie\": \"jouet\"," +
            "      \"heures\": \"trois\"," +
            "      \"date\": \"2003-04-07\"" +
            "    }]}";

    private final String geologistInvalid = "{" +
            " \"ordre\": \"géologues\"," +
            "  \"numero_de_permis\": \"A80001\"," +
            "\"nom\": \"Bond3\"," +
            "\"prenom\": \"James1\"," +
            "\"sexe\": \"-1\"," +
            "  \"cycle\": \"2013-20121\"," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire\"," +
            "      \"categorie\": \"Baleine\"," +
            "      \"heures\": 5.6," +
            "      \"date\": \"2010-04-07\"" +
            "    }," +
            "    {" +
            "      \"description\": \"Rédaction\"," +
            "      \"categorie\": \"jouet\"," +
            "      \"heures\": 2," +
            "      \"date\": \"2016-10-22\"" +
            "    }]}";

    private final String podiatristInvalid = "{" +
            " \"ordre\": \"podiatres\"," +
            "  \"numero_de_permis\": \"A80001\"," +
            "\"nom\": \"Bond3\"," +
            "\"prenom\": \"James1\"," +
            "\"sexe\": \"1.3\"," +
            "  \"cycle\": \"2013-20121\"," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire\"," +
            "      \"categorie\": \"jouet\"," +
            "      \"heures\": \"neuf\"," +
            "      \"date\": \"2021-04-07\"" +
            "    }," +
            "    {" +
            "      \"description\": \"Rédaction\"," +
            "      \"categorie\": \"projet de recherche\"," +
            "      \"heures\": 2," +
            "      \"date\": \"2016-10-22\"" +
            "    }]" +
            "}";

    private final String wrongDateFormat = "{" +
            "\"ordre\": \"psychologues\"," +
            "  \"numero_de_permis\": \"E2345-12\"," +
            "\"nom\": \"Bond3\"," +
            "\"prenom\": \"James1\"," +
            "\"sexe\": \"1\"," +
            "  \"cycle\": \"2014-2023\"," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire\"," +
            "      \"categorie\": \"jouet\"," +
            "      \"heures\": \"trois\"," +
            "      \"date\": \"20-12\"" +
            "    }]}";

    private final String transferredHoursMinusOne = "{" +
            "\"ordre\": \"architectes\"," +
            "  \"numero_de_permis\": \"E2345-12\"," +
            "\"nom\": \"Bond3\"," +
            "\"prenom\": \"James1\"," +
            "\"sexe\": \"1\"," +
            "  \"heures_transferees_du_cycle_precedent\": -1," +
            "  \"cycle\": \"2014-2023\"," +
            "  \"activites\": [" +
            "    {" +
            "      \"description\": \"Séminaire\"," +
            "      \"categorie\": \"jouet\"," +
            "      \"heures\": \"trois\"," +
            "      \"date\": \"20-12\"" +
            "    }]}";

    public JSONObject getArchitect() {
        return  (JSONObject) JSONSerializer.toJSON(architect);
    }

    public JSONObject getPsychologist() {
        return (JSONObject) JSONSerializer.toJSON(psychologist);
    }

    public JSONObject getPsychologistComplete() {
        return (JSONObject) JSONSerializer.toJSON(psychologistComplete);
    }

    public JSONObject getGeologist() {
        return (JSONObject) JSONSerializer.toJSON(geologist);
    }

    public JSONObject getPodiatrist() {
        return (JSONObject) JSONSerializer.toJSON(podiatrist);
    }

    public JSONObject getArchitectInvalid() {
        return (JSONObject) JSONSerializer.toJSON(architectInvalid);
    }

    public JSONObject getPsychologistInvalid() {
        return (JSONObject) JSONSerializer.toJSON(psychologistInvalid);
    }

    public JSONObject getGeologistInvalid() {
        return (JSONObject) JSONSerializer.toJSON(geologistInvalid);
    }

    public JSONObject getPodiatristInvalid() {
        return (JSONObject) JSONSerializer.toJSON(podiatristInvalid);
    }

    public JSONObject getWrongDateFormat() {
        return (JSONObject) JSONSerializer.toJSON(wrongDateFormat);
    }

    public JSONObject getTransferredHoursMinusOne() { return (JSONObject) JSONSerializer.toJSON(transferredHoursMinusOne); }

}
