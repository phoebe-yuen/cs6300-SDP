package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Junit test class created for use in Georgia Tech CS6300.
 *
 * You should implement your tests in this class.
 */

public class MyStringTest {

    private MyStringInterface mystring;

    @Before
    public void setUp() {
        mystring = new MyString();
    }

    @After
    public void tearDown() {
        mystring = null;
    }

    @Test
    // Description: Instructor-provided test 1
    public void testCountNumbersS1() {
        mystring.setString("My numbers are 11, 96, and thirteen");
        assertEquals(2, mystring.countNumbers());
    }

    @Test(expected = NullPointerException.class)
    // Description: check if string is null
    public void testCountNumbersS2() {
        mystring.countNumbers();
    }

    @Test
    // Description: Extracted integers from a string consisting of alphabets and special characters
    public void testCountNumbersS3() {
        mystring.setString("I l0ve pr0gramm!n9. Me2");
        assertEquals(4, mystring.countNumbers());
    }

    @Test
    // Description: Handle rational and irrational numbers as integers separated by special character
    public void testCountNumbersS4() {
        mystring.setString("I don't handle real number such as 10.4, 3/4, π, −0.86");
        assertEquals(6, mystring.countNumbers());
    }

    @Test
    // Description: Instructor-provided test 2
    public void testAddNumberS1() {
        mystring.setString("hello 90, bye 2");
        assertEquals("hello 92, bye 4", mystring.addNumber(2, false));
    }

    @Test
    // Description: When n is a double digit integer
    public void testAddNumberS2() {
        mystring.setString("hi 12345");
        assertEquals("hi 12356", mystring.addNumber(11, false));
    }

    @Test(expected = IllegalArgumentException.class)
    // Description: check if n is negative
    public void testAddNumberS3() {
       mystring.setString("Test 4 IllegalArgumentException");
       mystring.addNumber(-1,true);
    }

    @Test
    // Description: Reverse every digits in an integer when reverse is true
    public void testAddNumberS4() {
        mystring.setString("12345");
        assertEquals("54321", mystring.addNumber(0, true));
    }

    @Test
    // Description: Reverse every digits in an integer after adding n when reverse is true
    public void testAddNumberS5() {
        mystring.setString("hello 90, bye 2");
        assertEquals("hello 89, bye 01", mystring.addNumber(8, true));
    }

    @Test
    // Description: Negative sign is ignored
    public void testAddNumberS6() {
        mystring.setString("-12345");
        assertEquals("-12355", mystring.addNumber(10, false));
    }

    @Test
    // Description: Instructor-provided test 3
    public void testConvertDigitsToNamesInSubstringS1() {
        mystring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mystring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put szerome donesix1ts in this 5tr1n6, right?", mystring.getString());
    }

    @Test
    // Description: digits separated by empty space
    public void testConvertDigitsToNamesInSubstringS2() {
        mystring.setString("abc 4 1 6 d");
        mystring.convertDigitsToNamesInSubstring(1,11);
        assertEquals("abc four one six d", mystring.getString());
    }

    @Test(expected = IllegalArgumentException.class)
    // Description: check if startPosition is smaller than 1
    public void testConvertDigitsToNamesInSubstringS3() {
        mystring.setString("Test startPosition is smaller than 1");
        mystring.convertDigitsToNamesInSubstring(-1,2);
    }

    @Test(expected = IllegalArgumentException.class)
    // Description: check if startPosition is larger than endPosition
    public void testConvertDigitsToNamesInSubstringS4() {
        mystring.setString("Test startPosition is larger than endPosition");
        mystring.convertDigitsToNamesInSubstring(2,-1);
    }

    @Test(expected = MyIndexOutOfBoundsException.class)
    // Description: check if endPosition is out of bounds
    public void testConvertDigitsToNamesInSubstringS5() {
        mystring.setString("Test MyIndexOutOfBoundsException");
        mystring.convertDigitsToNamesInSubstring(1,100);
    }

    @Test
    // Description: Replace digits in the string consisting of special characters
    public void testConvertDigitsToNamesInSubstringS6() {
        mystring.setString("<33> T^T ..0_0..");
        mystring.convertDigitsToNamesInSubstring(1,16);
        assertEquals("<threethree> T^T ..zero_zero..", mystring.getString());
    }
}

