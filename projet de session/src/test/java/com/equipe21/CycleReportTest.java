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

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CycleReportTest {
    private MockInputFile file = new MockInputFile();

    //Tests résultats valides

    //Nom de Famille, Prenom, et Sexe

    @Test
    void getFamilyNameTest() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("Bond", report.getFamilyName());
    }

    @Test
    void getNameTest() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("James", report.getName());
    }

    @Test
    void getSexTest() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("1", report.getSex());
    }

    //Psychologue

    @Test
    void getFinalHeuresTransfereesBiggerPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals(0, report.getFinalTransHours());
    }

    @Test
    void getTotalValidHoursPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals(25, report.getTotalHours());
    }

    @Test
    void getNoPermisPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("12345-12", report.getNumLicense());
    }

    @Test
    void getActivitesPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("[{\"description\":\"Séminaire sur l'architecture contemporaine\",\"categorie\":\"cours\",\"heures\":25,\"date\":\"2016-04-07\"}]", report.getActivites().toString());
    }

    @Test
    void getOrdrePsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals(EOrder.PSYCHOLOGISTS.getOrder().getClass().getSimpleName(), report.getOrder().getClass().getSimpleName());
    }

    @Test
    void getCyclePsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("2016-2021", report.getCycle());
    }

    @Test
    void getHeuresTransfereesPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals(0, report.getTransHours());
    }

    //Architecte

    @Test
    void getFinalHeuresTransfereesBiggerArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals(7, report.getFinalTransHours());
    }

    @Test
    void getTotalValidHoursArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals(24, report.getTotalHours());
    }

    @Test
    void getNoPermisArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals("A0001", report.getNumLicense());
    }

    @Test
    void getActivitesArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals("[{\"description\":\"Séminaire sur l'architecture contemporaine\",\"categorie\":\"cours\",\"heures\":17,\"date\":\"2016-04-07\"}]", report.getActivites().toString());
    }

    @Test
    void getOrdreArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals(EOrder.ARCHITECTS.getOrder().getClass().getSimpleName(), report.getOrder().getClass().getSimpleName());
    }

    @Test
    void getCycleArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals("2016-2018", report.getCycle());
    }

    @Test
    void getHeuresTransfereesArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals(7, report.getTransHours());
    }

    //Geologue

    @Test
    void getFinalHeuresTransfereesBiggerGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals(0, report.getFinalTransHours());
    }

    @Test
    void getTotalValidHoursGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals(26, report.getTotalHours());
    }

    @Test
    void getNoPermisGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals("BJ0001", report.getNumLicense());
    }

    @Test
    void getActivitesGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals("[{\"description\":\"Séminaire sur l'architecture contemporaine\",\"categorie\":\"cours\",\"heures\":22,\"date\":\"2017-09-07\"}," +
                "{\"description\":\"Rédaction pour le magazine Architecture moderne\",\"categorie\":\"projet de recherche\",\"heures\":3,\"date\":\"2016-10-22\"}," +
                "{\"description\":\"Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans\"," +
                "\"categorie\":\"groupe de discussion\",\"heures\":1,\"date\":\"2017-04-01\"}]", report.getActivites().toString());
    }

    @Test
    void getOrdreGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals(EOrder.GEOLOGISTS.getOrder().getClass().getSimpleName(), report.getOrder().getClass().getSimpleName());
    }

    @Test
    void getCycleGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals("2016-2019", report.getCycle());
    }

    @Test
    void getHeuresTransfereesGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals(0, report.getTransHours());
    }

    //Podiatre

    @Test
    void getFinalHeuresTransfereesBiggerPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals(0, report.getFinalTransHours());
    }

    @Test
    void getTotalValidHoursPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals(26, report.getTotalHours());
    }

    @Test
    void getNoPermisPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals("00001", report.getNumLicense());
    }

    @Test
    void getActivitesPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals("[{\"description\":\"Séminaire sur l'architecture contemporaine\",\"categorie\":\"cours\",\"heures\":22,\"date\":\"2016-08-07\"}," +
                "{\"description\":\"Rédaction pour le magazine Architecture moderne\",\"categorie\":\"projet de recherche\",\"heures\":3,\"date\":\"2016-10-22\"}," +
                "{\"description\":\"Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans\"," +
                "\"categorie\":\"groupe de discussion\",\"heures\":1,\"date\":\"2016-09-01\"}]", report.getActivites().toString());
    }

    @Test
    void getOrdrePodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals(EOrder.PODIATRIST.getOrder().getClass().getSimpleName(), report.getOrder().getClass().getSimpleName());
    }

    @Test
    void getCyclePodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals("2016-2019", report.getCycle());
    }

    @Test
    void getHeuresTransfereesPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals(0, report.getTransHours());
    }

    //Tests résultats non null

    //Nom de Famille, Prenom, et Sexe

    @Test
    void getFamilyNameNotNull() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertNotNull(report.getFamilyName());
    }

    @Test
    void getNameNotNull() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertNotNull(report.getName());
    }

    @Test
    void getSexNotNull() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertNotNull(report.getSex());
    }

    //Psychologues

    @Test
    void getCycleNotNullPsycologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertNotNull(report.getCycle());
    }

    @Test
    void getActivitesNotNullPsycologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertNotNull(report.getActivites());
    }

    @Test
    void getNoPermisNotNullPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertNotNull(report.getNumLicense());
    }

    //Architecte

    @Test
    void getCycleNotNullArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertNotNull(report.getCycle());
    }

    @Test
    void getActivitesNotNullArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertNotNull(report.getActivites());
    }

    @Test
    void getNoPermisNotNullArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertNotNull(report.getNumLicense());
    }

    //Geologue

    @Test
    void getCycleNotNullGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertNotNull(report.getCycle());
    }

    @Test
    void getActivitesNotNullGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertNotNull(report.getActivites());
    }

    @Test
    void getNoPermisNotNullGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertNotNull(report.getNumLicense());
    }

    //Podiatre

    @Test
    void getCycleNotNullPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertNotNull(report.getCycle());
    }

    @Test
    void getActivitesNotNullPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertNotNull(report.getActivites());
    }

    @Test
    void getNoPermisNotNullPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertNotNull(report.getNumLicense());
    }


    //Tests invalides

    @ExpectSystemExitWithStatus(2)
    @Test
    void setTransHoursMinusOne(){
        CycleReport report = new CycleReport(file.getTransferredHoursMinusOne());
    }
}