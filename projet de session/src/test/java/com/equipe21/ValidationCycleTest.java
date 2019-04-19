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

class ValidationCycleTest {
    private MockInputFile file = new MockInputFile();

    @Test
    void validateTruePsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertTrue(new ValidationCycle("2016-2021").validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateFalsePsychologist() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        assertFalse(new ValidationCycle("2018-2018").validate(report));
    }

    @Test
    void validateTrueArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertTrue(new ValidationCycle("2016-2018").validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateFalseArchitect20142016() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertFalse(new ValidationCycle("2016-2018").validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateFalseArchitect20182020() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertFalse(new ValidationCycle("2016-2018").validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateFalseArchitect20162018() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertFalse(new ValidationCycle("2016-2018").validate(report));
    }

    @Test
    void validateTrueGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertTrue(new ValidationCycle("2016-2019").validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateFalseGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        assertFalse(new ValidationCycle("2018-2018").validate(report));
    }

    @Test
    void validateTruePodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertTrue(new ValidationCycle("2016-2019").validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateFalsePodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        assertFalse(new ValidationCycle("2018-2018").validate(report));
    }
}