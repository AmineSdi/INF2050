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
import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ValidationDateTest {
    private MockInputFile file = new MockInputFile();

    @Test
    void validateTruePsychologist() throws ParseException{
        CycleReport report = new CycleReport(file.getPsychologist());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(new ValidationDate("yyyy-MM-dd", "2016-01-01", "2021-01-01", report.getCycle()).validate(activity));
    }

    @Test
    void validateTrueArchitect() throws ParseException{
        CycleReport report = new CycleReport(file.getArchitect());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(new ValidationDate("yyyy-MM-dd", "2016-04-01", "2018-04-01", report.getCycle()).validate(activity));
    }

    @Test
    void validateTrueGeologist() throws ParseException{
        CycleReport report = new CycleReport(file.getGeologist());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(new ValidationDate("yyyy-MM-dd", "2016-06-01", "2019-06-01", report.getCycle()).validate(activity));
    }

    @Test
    void validateTruePodiatrist() throws ParseException{
        CycleReport report = new CycleReport(file.getPodiatrist());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(new ValidationDate("yyyy-MM-dd", "2016-06-01", "2019-06-01", report.getCycle()).validate(activity));
    }

    @Test
    void validateDateMin() throws ParseException{
        CycleReport report = new CycleReport(file.getPsychologist());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(new ValidationDate("yyyy-MM-dd", "2016-01-01", "2021-01-01", report.getCycle()).validate(activity));
    }

    @Test
    void validateDateMax() throws ParseException{
        CycleReport report = new CycleReport(file.getPsychologist());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertTrue(new ValidationDate("yyyy-MM-dd", "2016-01-1", "2021-01-01", report.getCycle()).validate(activity));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateFalseBeforeDate() throws ParseException{
        CycleReport report = new CycleReport(file.getGeologistInvalid());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertFalse(new ValidationDate("yyyy-MM-dd", "2016-06-01", "2019-06-01", report.getCycle()).validate(activity));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateFalseAfterDate() throws ParseException{
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertFalse(new ValidationDate("yyyy-MM-dd", "2016-01-01", "2019-06-01", report.getCycle()).validate(activity));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validateException() {
        CycleReport report = new CycleReport(file.getPodiatristInvalid());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertThrows(ParseException.class, () -> {
            new ValidationDate("yyyy-MM-dd", "1", "ghj", report.getCycle()).validate(activity);
        });
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void validDateWrongFormat() throws ParseException{
        CycleReport report = new CycleReport(file.getWrongDateFormat());
        Activity activity = new Activity(report.getActivites().getJSONObject(0),report);
        assertFalse(new ValidationDate("yyyy-MM-dd", "2017-03-20", "2020-03-20", report.getCycle()).validate(activity));
    }

}