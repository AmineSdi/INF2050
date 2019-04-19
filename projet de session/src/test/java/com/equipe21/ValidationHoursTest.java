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

class ValidationHoursTest {

    MockInputFile file = new MockInputFile();
    private ValidationHours valH = new ValidationHours();

    @Test
    void validateHoursTruePsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(valH.validate(activity));
    }

    @Test
    void validateHoursTrueArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(valH.validate(activity));
    }

    @Test
    void validateHoursTrueGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(valH.validate(activity));
    }

    @Test
    void validateHoursTruePodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(valH.validate(activity));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateHoursFalsePsycologist() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertFalse(valH.validate(activity));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateHoursFalseArchitect() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertFalse(valH.validate(activity));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateHoursFalseGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertFalse(valH.validate(activity));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateHoursFalsePodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertFalse(valH.validate(activity));
    }

}