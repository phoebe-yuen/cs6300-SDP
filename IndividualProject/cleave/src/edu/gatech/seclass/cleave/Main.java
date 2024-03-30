package edu.gatech.seclass.cleave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Empty Main class for compiling Individual Project.
    // DO NOT ALTER THIS CLASS or implement it.

    public static void main(String[] args) {
        // Empty Skeleton Method
        int i = 0;
        
        if (args == null || args.length == 0) {
            usage();
            return;
        }
        boolean onlyChar = false;
        boolean onlyField = false;
        String delimiter = null;
        String indexRangeArg = null;
        boolean error = false;
        while (i <= args.length - 2) {
            String arg = args[i];
            if (arg.equals("-c")) {
                if (onlyChar || onlyField) {
                    error = true;
                }
                onlyChar = true;
                i++;
                indexRangeArg = args[i];
            } else if (arg.equals("-f")) {
                if (onlyChar || onlyField) {
                    error = true;
                }
                onlyField = true;
                i++;
                indexRangeArg = args[i];
            } else if (arg.equals("-d")) {
                if (onlyChar) {
                    error = true;
                }
                i++;
                delimiter = args[i];
                if (delimiter.length() > 1) {
                    error = true;
                }
            }
            i++;
        }
        if (onlyChar && delimiter != null) {
            error = true;
        }
        if (onlyChar) {
            delimiter = "";
        } else if (onlyField && delimiter == null) {
            delimiter = "\t";
        }


        if (i >= args.length){
           error = true;
        }
        try {
            if (args[args.length-1] == null){
                System.err.println("Failed to open input file");
                return;
            }
            File file = new File(args[args.length-1]);
            if (file == null || !file.exists()){
                System.err.println("Failed to open input file");
                return;
            }
            if (error)
            {
                usage();
                return;
            }
            Scanner fr = new Scanner(file);
            ArrayList<IndexRange> indexRanges = null;
            try {
                indexRanges = prepareIndexRanges(indexRangeArg);
            } catch (Exception e) {
                usage();
                return;
            }
            boolean isEmpty = true;
            while (fr.hasNextLine()) {
                isEmpty = false;
                String line = fr.nextLine();
                String splitDelimiter = delimiter;
                if (delimiter != null && (delimiter.equals(">")||
                        delimiter.equals("<")||
                        delimiter.equals("*") ||
                        delimiter.equals("|") ||
                        delimiter.equals("&") ||
                        delimiter.equals(".") ||
                        delimiter.equals("+") ||
                        delimiter.equals("?") ||
                        delimiter.equals("^") ||
                        delimiter.equals("$") ||
                        delimiter.equals("(") ||
                        delimiter.equals(")") ||
                        delimiter.equals("[") ||
                        delimiter.equals("]") ||
                        delimiter.equals("{") ||
                        delimiter.equals("}") ||
                        delimiter.equals("\\")
                )
                ){
                    splitDelimiter = "\\"+delimiter;
                }
                String[] lineFields = line.split(splitDelimiter);
                boolean isLinePrinted = false;
                for (int index = 0; index < lineFields.length; index++) {
                    boolean found = false;
                    for (IndexRange indexRange : indexRanges) {
                        if (indexRange.index != null && index == indexRange.index) {
                            found = true;
                            break;
                        } else if (indexRange.startIndex != null
                                && indexRange.endIndex != null
                                &&
                                index >= indexRange.startIndex && index <= indexRange.endIndex) {
                            found = true;
                            break;
                        }

                    }

                    if (found) {
                        if (isLinePrinted) {
                            System.out.print(delimiter);
                        } else {
                            isLinePrinted = true;
                        }
                        System.out.print(lineFields[index]);
                    }
                }
                System.out.println();
            }
            if (isEmpty) {
                System.out.println();
            }
            fr.close();
        }
        catch (FileNotFoundException e) {
            System.err.println("Failed to open input file");
        }
        catch (IOException e) {
            System.err.println("Failed to open input file");
        } catch (Exception e) {
            usage();
        }

    }

    public static ArrayList<IndexRange> prepareIndexRanges(String indexRangeArg) throws Exception {
        ArrayList<IndexRange> indexList = new ArrayList<>();
        if (indexRangeArg.endsWith(",")){
            throw new Exception();
        }
        String[] rangeArgList = indexRangeArg.split(",");
        for (String arg : rangeArgList) {
            boolean range = false;
            IndexRange indexRange = new IndexRange();

            if (arg.contains("+")) {
                throw new Exception();
            }
            if (arg.contains("-")) {
                range = true;
            } else {
                indexRange.index = Integer.parseInt(arg) - 1;
                if (indexRange.index < 0) {
                    throw new Exception();
                }
                indexList.add(indexRange);
                continue;
            }

            if (range) {
                String[] rangeArg = arg.split("-");
                if (rangeArg[0].equals("")) {
                    indexRange.startIndex = 0;
                } else {
                    indexRange.startIndex = Integer.parseInt(rangeArg[0]) - 1;
                }
                if (rangeArg.length == 1) {
                    indexRange.endIndex = Integer.MAX_VALUE;
                } else {
                    indexRange.endIndex = Integer.parseInt(rangeArg[1]) - 1;
                }
                if (indexRange.startIndex < 0 || indexRange.endIndex < 0 ||
                        indexRange.startIndex > indexRange.endIndex) {
                    throw new Exception();
                }
                indexList.add(indexRange);
            }

        }
        return indexList;

    }

    static class IndexRange {
        Integer index;
        Integer startIndex;
        Integer endIndex;
    }

    private static void usage() {
        System.err.println("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>");
    }
}