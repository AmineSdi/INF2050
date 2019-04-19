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

public class ValidationLicenseNumberTest {
    MockInputFile file = new MockInputFile();

    @Test
    void validatePsychologist() {
        CycleReport report = new CycleReport(file.getPsychologist());
        assertTrue(new ValidationLicenseNumber().validate(report));
    }

    @Test
    void validateArchitect() {
        CycleReport report = new CycleReport(file.getArchitect());
        assertTrue(new ValidationLicenseNumber().validate(report));
    }

    @Test
    void validateGeologist() {
        CycleReport report = new CycleReport(file.getGeologist());
        assertTrue(new ValidationLicenseNumber().validate(report));
    }

    @Test
    void validatePodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatrist());
        assertTrue(new ValidationLicenseNumber().validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateInvalidPsychologist() {
        CycleReport report = new CycleReport(file.getPsychologistInvalid());
        assertFalse(new ValidationLicenseNumber().validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateInvalidArchitect() {
        CycleReport report = new CycleReport(file.getArchitectInvalid());
        assertFalse(new ValidationLicenseNumber().validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateInvalidGeologist() {
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        assertFalse(new ValidationLicenseNumber().validate(report));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateInvalidPodiatrist() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        assertFalse(new ValidationLicenseNumber().validate(report));
    }
}
