package edu.gatech.seclass;

/**
 * Interface created for use in Georgia Tech CS6300.
 *
 * This is an interface for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * This interface should NOT be altered in any way.
 */
public interface MyStringInterface {

    /**
     * Returns the current string.
     * If the string has not been initialized with method setString, it should return null.
     *
     * @return Current string
     */
    String getString();

    /**
     * Sets the value of the current string
     *
     * @param string The value to be set
     */
    void setString(String string);

    /**
     * Returns the number of integer numbers in the current string, where an integer number is defined
     * as a contiguous sequence of digits [0-9].
     *
     * If the current string is empty, the method should return 0.
     *
     * Examples:
     * - This method would return 2 for string "My numbers are 11, 96, and thirteen"
     * - This method would return 3 for string "i l0ve 2 pr0gram."
     * - This method would return 2 for string "I don't handle real number such as 10.4"
     *
     * @return Number of integer numbers in the current string
     * @throws NullPointerException If the current string is null
     */
    int countNumbers();

    /**
     * Returns a string equivalent to the original string with n added to every integer number in
     * the string, where an integer number is defined as a contiguous sequence of digits, and signs
     * are ignored (i.e., all integers are considered to be positive).
     *
     * If 'reverse' is true, the order of the digits within every number will be reversed (after adding n).
     * If 'reverse' is false, the digits will remain in their original order within the string.
     *
     * Examples:
     * - For n=2 and reverse=false, "hello 90, bye 2" would be converted to "hello 92, bye 4".
     * - For n=11 and reverse=false, "hi 12345" would be converted to "hi 12356".
     * - For n=8 and reverse=true, "hello 90, bye 2" would be converted to "hello 89, bye 01".
     * - For n=0 and reverse=true, "12345" would be converted to "54321".
     * - For n=10 and reverse=false, "-12345" would be converted to "-12355".
     *
     * @param n amount to add to every number
     * @param reverse Boolean that indicates whether digits within a number should be reversed
     * @return String with n added to every number in the string, with the number reversed, if indicated
     * @throws NullPointerException     If the current string is null
     * @throws IllegalArgumentException If n is negative, and the current string is not null
     */
    String addNumber(int n, boolean reverse);

    /**
     * Replace the individual digits in the current string, between startPosition and endPosition
     * (included), with the corresponding name (i.e., string representation) of those digits.
     * The first character in the string is considered to be in Position 1.
     *
     * Examples:
     * - String "I'd b3tt3r put s0me d161ts in this 5tr1n6, right?, with startPosition=17 and endPosition=23 would be
     *   converted to "I'd b3tt3r put szerome donesix1ts in this 5tr1n6, right?"
     * - String "abc416d", with startPosition=2 and endPosition=7 would be converted to "abcfouronesixd"*
     *
     * @param startPosition Position of the first character to consider
     * @param endPosition   Position of the last character to consider

     * @throws NullPointerException        If the current string is null
     * @throws IllegalArgumentException    If "startPosition" < 1 or "startPosition" > "endPosition" (and the string
     *                                     is not null)
     * @throws MyIndexOutOfBoundsException If "endPosition" is out of bounds (i.e., greater than the length of the
     *                                     string), 1 <= "startPosition" <= "endPosition", and the string is not null
     */
    void convertDigitsToNamesInSubstring(int startPosition, int endPosition);
}
