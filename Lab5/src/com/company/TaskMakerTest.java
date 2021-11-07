package com.company;

import static org.junit.jupiter.api.Assertions.*;

class TaskMakerTest {

    @org.junit.jupiter.api.Test
    void testEmptyText() {
        assertEquals("Text is empty",TaskMaker.Do(""));
    }

    @org.junit.jupiter.api.Test
    void testContentsText() {
        String test = "Some text without articles and other forbidden words";
        assertEquals(test,TaskMaker.Do(test));
    }

    @org.junit.jupiter.api.Test
    void testWorkCheck() {
        String expected = " email address identifies email box to which messages delivered.";
        String test = "An email address identifies an email box to which messages are delivered.";
        assertEquals(expected,TaskMaker.Do(test));
    }
}