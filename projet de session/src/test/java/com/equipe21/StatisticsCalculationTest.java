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

public class StatisticsCalculationTest {

    MockInputFile file = new MockInputFile();

    @Test
    void validateValidNumberOfMen() {
        CycleReport report = new CycleReport(file.getPsychologist());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(1, statsCalculation.getNewStats().getNbMen());
    }

    @Test
    void validateValidNumberOfMenUnchanged() {
        CycleReport report = new CycleReport(file.getGeologist());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(0, statsCalculation.getNewStats().getNbMen());
    }

    @Test
    void validateValidNumberOfWomen() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(1, statsCalculation.getNewStats().getNbWomen());
    }

    @Test
    void validateValidNumberOfWomenUnchanged() {
        CycleReport report = new CycleReport(file.getPsychologist());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(0, statsCalculation.getNewStats().getNbWomen());
    }

    @Test
    void validateValidNumberOfUnknownSex() {
        CycleReport report = new CycleReport(file.getGeologist());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(1, statsCalculation.getNewStats().getNbUnknownSex());
    }

    @Test
    void validateNumberOfUnknownSexUnchanged() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(0, statsCalculation.getNewStats().getNbUnknownSex());
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateNumberOfInvalidLicenseInvalid() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        ValidationLicenseNumber validationLicenseNumber = new ValidationLicenseNumber();
        validationLicenseNumber.validate(report);
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(1, statsCalculation.getNewStats().getNbInvalidLicence());
    }

    @Test
    void validateNumberOfInvalidLicenseValid() {
        CycleReport report = new CycleReport(file.getArchitect());
        ValidationLicenseNumber validationLicenseNumber = new ValidationLicenseNumber();
        validationLicenseNumber.validate(report);
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(0, statsCalculation.getNewStats().getNbInvalidLicence());
    }

    @Test
    void validateNbActivitiesByCategoryValid() {
        CycleReport report = new CycleReport(file.getPsychologistComplete());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertTrue(statsCalculation.getNewStats().getNbActivitiesByCategory().get("cours") == 2
                && statsCalculation.getNewStats().getNbActivitiesByCategory().get("groupe de discussion") == 1);
    }

    @Test
    void validateNbActivitiesByCategoryInvalid() {
        CycleReport report = new CycleReport(file.getArchitect());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertFalse(statsCalculation.getNewStats().getNbActivitiesByCategory().containsKey("cours")
                && statsCalculation.getNewStats().getNbActivitiesByCategory().containsKey("projet de recherche"));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateNbInvalidDeclarationsByOrder() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertTrue(statsCalculation.getNewStats().getNbInvalidDeclarationsByOrder().get("podiatres") == 1);
    }

    @Test
    void validateIncrementNbTotalDeclaration() {
        CycleReport report = new CycleReport(file.getGeologist());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(1, statsCalculation.getNewStats().getNbTotalDeclarations());
    }

    @Test
    void validateTotalActivities() {
        CycleReport report = new CycleReport(file.getGeologist());
        StatsCalculation statsCalculation = new StatsCalculation(report, new Statistics());
        statsCalculation.generateStats("");
        assertEquals(3, statsCalculation.getNewStats().getNbTotalActivities());
    }
}
