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

class ActivityTest {
    MockInputFile file = new MockInputFile();

    //Tests pour information valide

    //Psychologue

    @Test
    void getRealHoursPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals(25, new Activity(report.getActivites().getJSONObject(0), report).getRealHours());
    }

    @Test
    void getHeuresPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("25", new Activity(report.getActivites().getJSONObject(0), report).getHours());
    }

    @Test
    void getDescriptionPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("Séminaire sur l'architecture contemporaine", new Activity(report.getActivites().getJSONObject(0), report).getDescription());
    }

    @Test
    void getCategoriePsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("cours", new Activity(report.getActivites().getJSONObject(0), report).getCategory());
    }

    @Test
    void getDatePsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertEquals("2016-04-07", new Activity(report.getActivites().getJSONObject(0), report).getDate());
    }

    //Architecte

    @Test
    void getRealHoursArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals(17, new Activity(report.getActivites().getJSONObject(0), report).getRealHours());
    }

    @Test
    void getHeuresArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals("17", new Activity(report.getActivites().getJSONObject(0), report).getHours());
    }

    @Test
    void getDescriptionArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals("Séminaire sur l'architecture contemporaine", new Activity(report.getActivites().getJSONObject(0), report).getDescription());
    }

    @Test
    void getCategorieArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals("cours", new Activity(report.getActivites().getJSONObject(0), report).getCategory());
    }

    @Test
    void getDateArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertEquals("2016-04-07", new Activity(report.getActivites().getJSONObject(0), report).getDate());
    }

    //Geologue

    @Test
    void getRealHoursGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals(22, new Activity(report.getActivites().getJSONObject(0), report).getRealHours());
    }

    @Test
    void getHeuresGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals("22", new Activity(report.getActivites().getJSONObject(0), report).getHours());
    }

    @Test
    void getDescriptionGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals("Séminaire sur l'architecture contemporaine", new Activity(report.getActivites().getJSONObject(0), report).getDescription());
    }

    @Test
    void getCategorieGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals("cours", new Activity(report.getActivites().getJSONObject(0), report).getCategory());
    }

    @Test
    void getDateGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertEquals("2017-09-07", new Activity(report.getActivites().getJSONObject(0), report).getDate());
    }

    //Podiatre

    @Test
    void getRealHoursPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals(22, new Activity(report.getActivites().getJSONObject(0), report).getRealHours());
    }

    @Test
    void getHeuresPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals("22", new Activity(report.getActivites().getJSONObject(0), report).getHours());
    }

    @Test
    void getDescriptionPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals("Séminaire sur l'architecture contemporaine", new Activity(report.getActivites().getJSONObject(0), report).getDescription());
    }

    @Test
    void getCategoriePodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals("cours", new Activity(report.getActivites().getJSONObject(0), report).getCategory());
    }

    @Test
    void getDatePodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertEquals("2016-08-07", new Activity(report.getActivites().getJSONObject(0), report).getDate());
    }

    //Tests pour information invalide

    //Psychologue

    @ExpectSystemExitWithStatus(2)
    @Test
    void getRealHoursWithThrowExceptionPsychologue() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        assertEquals(0, new Activity(report.getActivites().getJSONObject(0), report).getRealHours());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getNotRealHoursPsychologue() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        assertNotEquals(24, new Activity(report.getActivites().getJSONObject(0),report).getRealHours());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongDescriptionPsychologue() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        assertNotEquals("Séminaire sur l'architecture contemporaine", new Activity(report.getActivites().getJSONObject(0), report).getDescription());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongCategoriePsychologue() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        assertNotEquals("séminaire", new Activity(report.getActivites().getJSONObject(0), report).getCategory());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongHeuresPsychologue() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        assertNotEquals("25", new Activity(report.getActivites().getJSONObject(0), report).getHours());
    }


    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongDatePsychologue() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        assertNotEquals("2026-03-20", new Activity(report.getActivites().getJSONObject(0), report).getDate());
    }

    //Architecte

    @ExpectSystemExitWithStatus(2)
    @Test
    void getRealHoursWithThrowExceptionArchitect() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertEquals(0, new Activity(report.getActivites().getJSONObject(0), report).getRealHours());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getNotRealHoursArchitect() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertNotEquals(24, new Activity(report.getActivites().getJSONObject(0),report).getRealHours());
    }


    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongDescriptionArchitect() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertNotEquals("Séminaire sur l'architecture contemporaine", new Activity(report.getActivites().getJSONObject(0), report).getDescription());
    }


    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongCategorieArchitect() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertNotEquals("séminaire", new Activity(report.getActivites().getJSONObject(0), report).getCategory());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongHeuresArchitect() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertNotEquals("25", new Activity(report.getActivites().getJSONObject(0), report).getHours());
    }


    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongDateArchitect() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertNotEquals("2026-03-20", new Activity(report.getActivites().getJSONObject(0), report).getDate());
    }

    //Geologue

    @ExpectSystemExitWithStatus(2)
    @Test
    void getRealHoursWithThrowExceptionGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        assertEquals(0, new Activity(report.getActivites().getJSONObject(0), report).getRealHours());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getNotRealHoursGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        assertNotEquals(24, new Activity(report.getActivites().getJSONObject(0),report).getRealHours());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongDescriptionGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        assertNotEquals("Séminaire sur l'architecture contemporaine", new Activity(report.getActivites().getJSONObject(0), report).getDescription());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongCategorieGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        assertNotEquals("séminaire", new Activity(report.getActivites().getJSONObject(0), report).getCategory());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongHeuresGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        assertNotEquals("25", new Activity(report.getActivites().getJSONObject(0), report).getHours());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongDateGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        assertNotEquals("2026-03-20", new Activity(report.getActivites().getJSONObject(0), report).getDate());
    }

    //Podiatre

    @ExpectSystemExitWithStatus(2)
    @Test
    void getRealHoursWithThrowExceptionPodiatre() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        assertEquals(0, new Activity(report.getActivites().getJSONObject(0), report).getRealHours());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getNotRealHoursPodiatre() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        assertNotEquals(24, new Activity(report.getActivites().getJSONObject(0),report).getRealHours());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongDescriptionPodiatre() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        assertNotEquals("Séminaire sur l'architecture contemporaine", new Activity(report.getActivites().getJSONObject(0), report).getDescription());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongCategoriePodiatre() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        assertNotEquals("séminaire", new Activity(report.getActivites().getJSONObject(0), report).getCategory());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongHeuresPodiatre() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        assertNotEquals("25", new Activity(report.getActivites().getJSONObject(0), report).getHours());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void getWrongDatePodiatre() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        assertNotEquals("2026-03-20", new Activity(report.getActivites().getJSONObject(0), report).getDate());
    }
}