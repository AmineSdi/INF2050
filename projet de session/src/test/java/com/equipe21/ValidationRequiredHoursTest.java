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

class ValidationRequiredHoursTest {

    MockInputFile file = new MockInputFile();

    @Test
    void validateRequiredHoursMetPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertTrue(new ValidationRequiredHours(18).validate(report));
    }

    @Test
    void validateRequiredHoursMetArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertTrue(new ValidationRequiredHours(18).validate(report));
    }

    @Test
    void validateRequiredHoursMetGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertTrue(new ValidationRequiredHours(18).validate(report));
    }

    @Test
    void validateRequiredHoursMetPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertTrue(new ValidationRequiredHours(18).validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateRequiredHoursNotMetPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        assertFalse(new ValidationRequiredHours(200).validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateRequiredHoursNotMetArchitect() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertFalse(new ValidationRequiredHours(200).validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateRequiredHoursNotMetGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        assertFalse(new ValidationRequiredHours(200).validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateRequiredHoursNotMetPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        assertFalse(new ValidationRequiredHours(200).validate(report));
    }
}