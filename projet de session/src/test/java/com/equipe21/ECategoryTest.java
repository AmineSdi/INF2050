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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ECategoryTest {

    @Test
    void isARealCategory() {
        assertTrue(ECategory.isCategory("présentation"));
    }

    @Test
    void isNotARealCategory() {
        assertFalse(ECategory.isCategory("presentation"));
    }

    @Test
    void fromTitleValid() {
        assertEquals(ECategory.CLASS, ECategory.fromTitle("cours"));
    }

    @Test
    void fromTitleInvalid() {
        assertEquals(ECategory.INVALID_CATEGORY, ECategory.fromTitle("course"));
    }

    @Test
    void getTrimHoursMoreThanMax() {
        ECategory.CLASS.setMaxHours(23);
        assertEquals(23, ECategory.CLASS.getTrimHours(24));
    }

    @Test
    void getTrimHoursLessThanMax() {
        ECategory.WORKSHOP.setMaxHours(23);
        assertEquals(22, ECategory.WORKSHOP.getTrimHours(22));
    }

    @Test
    void getTrimHoursEqualsMax() {
        ECategory.WORKSHOP.setMaxHours(23);
        assertEquals(23, ECategory.WORKSHOP.getTrimHours(23));
    }

    @Test
    void getTitle() {
        assertEquals("cours", ECategory.CLASS.getTitle());
    }

    @Test
    void getHeureMin() {
        ECategory.WORKSHOP.setMinHours(10);
        assertEquals(10, ECategory.WORKSHOP.getMinHours());
    }

    @Test
    void getHeureMax() {
        ECategory.WORKSHOP.setMaxHours(90);
        assertEquals(90, ECategory.WORKSHOP.getMaxHours());
    }

    @Test
    void setminHours() {
        ECategory.COLLOQUY.setMinHours(10);
        assertEquals(10, ECategory.COLLOQUY.getMinHours());
    }

    @Test
    void setmaxHours() {
        ECategory.COLLOQUY.setMaxHours(50);
        assertEquals(50, ECategory.COLLOQUY.getMaxHours());
    }
}