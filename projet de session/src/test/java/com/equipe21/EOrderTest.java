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

class EOrderTest {

    @Test
    void fromTitleValid() {
        assertEquals(EOrder.PSYCHOLOGISTS, EOrder.fromTitle("psychologues"));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void fromTitleSpellingError() {
        assertNotEquals(EOrder.PSYCHOLOGISTS, EOrder.fromTitle("ypschologues"));
    }

    @ExpectSystemExitWithStatus(2)
    @Test
    void fromTitleNonExisting() {
        assertEquals(EOrder.INVALID_ORDER, EOrder.fromTitle("Steve"));
    }

    @Test
    void getTitleValid() {
        assertEquals("architectes", EOrder.ARCHITECTS.getTitle());
    }

    @Test
    void getTitleMixedTitles() {
        assertNotEquals("architectes", EOrder.INVALID_ORDER.getTitle());
    }

    @Test
    void getTitleInvalid() {
        assertEquals("invalid", EOrder.INVALID_ORDER.getTitle());
    }

    @Test
    void getOrderNull() {
        assertNull(EOrder.INVALID_ORDER.getOrder());
    }

    @Test
    void getOrderValid() {
        assertEquals(new Psychologist().getClass().getSimpleName(), EOrder.PSYCHOLOGISTS.getOrder().getClass().getSimpleName());
    }

    @Test
    void getOrderNotNull() {
        assertNotNull(EOrder.PSYCHOLOGISTS.getOrder());
    }

}
