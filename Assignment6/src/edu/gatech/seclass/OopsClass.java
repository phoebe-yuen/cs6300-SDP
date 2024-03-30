package edu.gatech.seclass;

/**
 * This is a Georgia Tech provided code example for use in assigned
 * private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it
 * available on publicly viewable websites including repositories such
 * as Github and Gitlab.  Such sharing may be investigated as a GT
 * honor code violation. Created for CS6300 Spring 2021.
 *
 * Template provided for the White-Box Testing Assignment. Follow the
 * assignment directions to either implement or provide comments for
 * the appropriate methods.
 */

public class OopsClass {

    public static void exampleMethod1(int a) {
        // ...
        int x = a / 0; // Example of instruction that makes the method
                       // fail with an ArithmeticException
        // ...
    }

    public static int exampleMethod2(int a, int b) {
        // ...
        return (a + b) / 0; // Example of instruction that makes the
                            // method fail with an ArithmeticException
    }

    public static void exampleMethod3() {
        // NOT POSSIBLE: This method cannot be implemented because
        // <REPLACE WITH REASON> (this is the example format for a
        // method that is not possible) ***/
    }

    public static void oopsMethod1(int x, int y) { // Change the signature as needed
        // Either add a comment in the format provided above or
        // implement the method.
        int result = x / y;
        if (result < x){
            result = x;
        }
    }

    public static void oopsMethod2(int x,int y) { // Change the signature as needed
        // Either add a comment in the format provided above or
        // implement the method.
        int z= 0, result = 0;
        if (x != 0){
            z = x + 1;
        }
        else z = 0;
        if( y> 0){
            result = y/z;
        }
        else result = 0;
    }

    public static void oopsMethod3() {
        /* NOT POSSIBLE: This method cannot be implemented because
        Consider an if-else statement, oopsMethod3 must test both TRUE and FALSE cases
        to achieve 100% branch coverage. Either the TRUE or the FALSE case contains a
        division by zero fault so that the test suit reveals the fault.

        oopsMethod3 can achieve 100% statement coverage but less than 100% branch
        coverage by only testing the TRUE case of an if-else statement and the “else”
        part does not exist in the code. And the TRUE case does not contain division
        by zero error.

        However, conditions (1) and (2) in task 3 cannot be achieved together since
        achieving condition (1) needs a division by zero fault exits in the FALSE part
        of an if-else statement and the statement in FALSE part needs to be executed
        to achieve condition (2), which will reveal the fault. */
    }

    public static void oopsMethod4(int x , int y) { // Change the signature as needed
        // Either add a comment in the format provided above or
        // implement the method.

        if (x >0){
            x = 1;
        }
        else {
            x=0;
        }

        if (y >0){
            y = y/x;
        }
        else {
            y  = -1
            ;
        }

    }

    public static String[] oopsMethod5() {
        String a[] = new String[7];
        /*
				public static boolean providedOopsMethod(boolean a, boolean b) {
  					int x = 2;
                    int y = -4;
                    if(a)
                        x = y;
                    else
                        x = -2*x;
                    if(b)
                        y = 0-x;
                    return ((100/(x+y))>= 0);
				}
        */
        //
        // Replace the "?" in column "output" with "T", "F", or "E":
        //
        //         | a | b |output|
        //         ================
        a[0] =  /* | T | T | <T, F, or E> (e.g., "T") */ "E";
        a[1] =  /* | T | F | <T, F, or E> (e.g., "T") */ "F";
        a[2] =  /* | F | T | <T, F, or E> (e.g., "T") */ "E";
        a[3] =  /* | F | F | <T, F, or E> (e.g., "T") */ "F";
        // ================
        //
        // Replace the "?" in the following sentences with "NEVER",
        // "SOMETIMES" or "ALWAYS":
        //
        a[4] = /* Test suites with 100% statement coverage */ "ALWAYS";
               /*reveal the fault in this method.*/
        a[5] = /* Test suites with 100% branch coverage */ "ALWAYS";
               /*reveal the fault in this method.*/
        a[6] =  /* Test suites with 100% path coverage */ "ALWAYS";
                /*reveal the fault in this method.*/
        // ================
        return a;
    }
}
