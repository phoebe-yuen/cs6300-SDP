package edu.gatech.seclass;

import org.junit.Test;

/**
 * This is a Georgia Tech provided code example for use in assigned
 * private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it
 * available on publicly viewable websites including repositories such
 * as Github and Gitlab.  Such sharing may be investigated as a GT
 * honor code violation. Created for CS6300 Spring 2021.
 *
 * Junit test class provided for the White-Box Testing Assignment.
 * This class should not be altered.  Follow the directions to create
 * similar test classes when required.
 */

public class OopsClassTestBC2 {

    // This is an example of a test supposed to fail with an uncaught
    // exception.
    // Notes:
    // - Make sure NOT to use the "expected" notation and NOT to catch
    //   the exception.
    // - Unlike for normal tests, there is no need to have an assert
    //   in the test.
    @Test
    public void Test1() {
        OopsClass.oopsMethod2(0,22);
    }

    @Test
    public void Test2() {
        OopsClass.oopsMethod2(1,-1);
    }

}
