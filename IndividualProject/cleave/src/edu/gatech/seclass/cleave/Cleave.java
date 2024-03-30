package edu.gatech.seclass.cleave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cleave implements CleaveInterface {
    String filepath;
    String delimiter = null;
    ArrayList<IndexRange> indexRanges = new ArrayList<>();
    boolean onlyChar = false;
    boolean onlyField = false;
    static class IndexRange {
        Integer index;
        Integer startIndex;
        Integer endIndex;
    }
    /**
     * Reset the Cleave object to its initial state, for reuse.
     */
    @Override
    public void reset() {
        this.filepath = null;
        this.delimiter = "";
        this.indexRanges = new ArrayList<>();
        this.onlyChar = false;
        this.onlyField = false;
    }

    /**
     * Sets the path of the input file. This method has to be called
     * before invoking the {@link processFile()} methods.
     *
     * @param filepath The file path to be set.
     */
    @Override
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Sets the field delimiter. If this method is not called,
     * the default value of the delimeter is the TAB symbol.
     *
     * @param ch Field delimiter to be used.
     */
    @Override
    public void setDelimiter(char ch) {
        this.delimiter = String.valueOf(ch);
    }

    /**
     * Set Cleave's list of ranges, specified as a string that
     * contains comma-separated ranges.
     * It throws a {@link CleaveException} if the list is invalid.
     *
     * @param list Comma-separated list of ranges to be set.
     * @throws CleaveException
     */
    @Override
    public void setRangeList(String list) throws CleaveException {
        if (list == null || list.isEmpty())
            throw new CleaveException("Invalid range(s) provided");
        ArrayList<IndexRange> indexList = new ArrayList<>();
        if (list.endsWith(",")){
            throw new CleaveException("Invalid range(s) provided");
        }
        String[] rangeArgList = list.split(",");
        for (String arg : rangeArgList) {
            boolean range = false;
            IndexRange indexRange = new IndexRange();

            if (arg.contains("+")) {
                throw new CleaveException("Invalid range(s) provided");
            }
            if (arg.contains("-")) {
                range = true;
            } else {
                try {
                    indexRange.index = Integer.parseInt(arg) - 1;
                }
                catch (Exception e){
                    throw new CleaveException("Invalid range(s) provided");
                }
                if (indexRange.index < 0) {
                    throw new CleaveException("Invalid range(s) provided");
                }
                indexList.add(indexRange);
                continue;
            }

            if (range) {
                String[] rangeArg = arg.split("-");
                try {
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
                }
                catch (Exception e){
                    throw new CleaveException("Invalid range(s) provided");
                }

                if (indexRange.startIndex < 0 || indexRange.endIndex < 0 ||
                        indexRange.startIndex > indexRange.endIndex) {
                    throw new CleaveException("Invalid range(s) provided");
                }
                indexList.add(indexRange);
            }

        }
        indexRanges = indexList;
    }

    /**
     * Activates field processing mode of the cleave object.  After
     * setting, neither field nor character mode can be selected again
     * without first calling reset() on the object. If that happens,
     * the method throws a {@link CleaveException}.
     *
     * @throws CleaveException
     */
    @Override
    public void setFieldBasedProcessing() throws CleaveException {
        if (onlyChar || onlyField)
            throw new CleaveException("Processing mode already set");
        onlyField = true;
    }

    /**
     * Activates character processing mode of the cleave object.
     * After setting, neither field nor character mode can be selected
     * again without first calling reset() on the object. If that
     * happens, the method throws a {@link CleaveException}.
     *
     * @throws CleaveException
     */
    @Override
    public void setCharacterBasedProcessing() throws CleaveException {
        if (onlyChar || onlyField)
            throw new CleaveException("Processing mode already set");


        onlyChar = true;
    }

    /**
     * Returns a System.lineSeperator() delimited string that contains
     * selected parts of the lines in the file specified using {@link setFilepath}
     * and according to the current configuration, which is set
     * through calls to the other methods in the interface.
     * <p>
     * It throws a {@link CleaveException} if an error condition
     * occurs (e.g., when the specified file does not exist).
     *
     * @return A System.lineSeperator() delimited string
     * @throws CleaveException
     */
    @Override
    public String processFile() throws CleaveException {
        StringBuilder outputBuilder = new StringBuilder();

        try {
            if (this.filepath == null){
                throw new CleaveException("Missing input file");
            }
            File file = new File(this.filepath);
            if (file == null || !file.exists()){
                throw new CleaveException("Cannot read from file: "+this.filepath);
            }

            if (!onlyChar && !onlyField)
                throw new CleaveException("Processing mode not set");
            if (indexRanges.isEmpty())
                throw new CleaveException("Empty range list");
            if (onlyChar) {
                if (delimiter !=null && delimiter.length() > 0) {
                    throw new CleaveException("Delimiter not supported in character mode");
                }
                delimiter = "";
            } else if (onlyField && (delimiter == null|| delimiter.isEmpty())) {
                delimiter = "\t";
            }
            Scanner fr = new Scanner(file);
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
                            outputBuilder.append(delimiter);
                        } else {
                            isLinePrinted = true;
                        }
                        outputBuilder.append(lineFields[index]);
                    }
                }
                outputBuilder.append(System.lineSeparator());
            }
            if (isEmpty) {
                outputBuilder.append(System.lineSeparator());
            }
            fr.close();
        }
        catch (FileNotFoundException e) {
            throw new CleaveException("Cannot read from file: "+this.filepath);
        }
        catch (IOException e) {
            throw new CleaveException("Cannot read from file: "+this.filepath);
        }
        return outputBuilder.toString();
    }
}
