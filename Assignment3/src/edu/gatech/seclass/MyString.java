package edu.gatech.seclass;

public class MyString implements MyStringInterface {
    String string;

    public String getString() {
        return this.string;
    }

    public void setString(String string) {
        this.string = string;

    }

    public int countNumbers() {
        if (string == null) throw new NullPointerException();
        int count = 0;
        boolean checkDigit = false;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                if (!checkDigit) {
                    count++;
                    checkDigit = true;
                }
            } else {
                checkDigit = false;
            }
        }
        return count;
    }

    public String addNumber(int n, boolean reverse) {
        if (string == null) throw new NullPointerException();
        if (n<0 && string != null) throw new IllegalArgumentException();
        StringBuilder output = new StringBuilder();
        int currentNumber = -1;

        for (int i = 0; i < string.length(); i++) {
            char tempChar = string.charAt(i);
            if (Character.isDigit(tempChar)) {
                if (currentNumber == -1) {
                    currentNumber = 0;
                }
                currentNumber = currentNumber * 10;
                currentNumber = currentNumber + Integer.parseInt(String.valueOf(tempChar));

            } else {
                if (currentNumber >= 0) {
                    output.append(getOutputNumber(n, reverse, currentNumber));
                    currentNumber = -1;
                }
                output.append(tempChar);
            }
        }
        if (currentNumber >= 0) {
            output.append(getOutputNumber(n, reverse, currentNumber));
        }

        return output.toString();
    }

    public StringBuilder getOutputNumber(int n, boolean reverse, int inNumber) {

        inNumber += n;
        StringBuilder tempNumber = new StringBuilder(String.valueOf(inNumber));
        if (reverse) {
            tempNumber = tempNumber.reverse();
        }
        return tempNumber;
    }

    String[] digitNames = {"zero","one", "two","three","four","five","six","seven","eight","nine"};
    public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {
        if (string == null) throw new NullPointerException();
        if (startPosition<1 || startPosition>endPosition) throw new IllegalArgumentException();
        if (endPosition>string.length()) throw new MyIndexOutOfBoundsException();
        StringBuilder output = new StringBuilder();
        int startIndex= startPosition- 1;
        int endIndex = endPosition - 1;
        for (int i = 0; i<string.length(); i++){
            char tempChar = string.charAt(i);
            if (i >= startIndex && i<=endIndex && Character.isDigit(tempChar)){
                int currentNumber = Integer.parseInt(String.valueOf(tempChar));
                output.append(digitNames[currentNumber]);
            }
            else {
                output.append(tempChar);
            }
        }
        this.string = output.toString();
    }
}
