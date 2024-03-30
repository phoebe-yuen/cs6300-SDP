package edu.gatech.seclass.cleave;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class MyMainTest {
	
    // Place all  of your tests in this class, optionally using MainTest.java as an example.
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    /*
     *  TEST UTILITIES
     */

    // Create File Utility
    private File createTmpFile() throws Exception {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    // Write File Utility
    private File createInputFile(String input) throws Exception {
        File file =  createTmpFile();

        OutputStreamWriter fileWriter =
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        fileWriter.write(input);

        fileWriter.close();
        return file;
    }

    /*
     *   TEST CASES
     */

    // Frame #: 1
    @Test
    public void cleaveTest1() {

        String args[] = {"-c", "2"};
        Main.main(args);
        assertEquals("Failed to open input file", errStream.toString().trim());
    }

    // Frame #: 2
    @Test
    public void cleaveTest2() throws Exception{
        File inputFile = createInputFile("");

        String args[] = {"-c", "15", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals(""+System.lineSeparator(), afterFile);
    }

    // Frame #: 3
    @Test
    public void cleaveTest3() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"-c", "1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("1"+System.lineSeparator(), afterFile);
    }

    // Frame #: 4
    @Test
    public void cleaveTest4() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"-c", "9", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals(""+System.lineSeparator(), afterFile);
    }

    // Frame #: 5
    @Test
    public void cleaveTest5() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"15", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 6
    @Test
    public void cleaveTest6() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"-z","15", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 7
    @Test
    public void cleaveTest7() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 8
    @Test
    public void cleaveTest8() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"-c", "0", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 9
    @Test
    public void cleaveTest9() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"-c", "1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("1"+System.lineSeparator(), afterFile);
    }

    // Frame #: 10
    @Test
    public void cleaveTest10() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"-c", "-2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12"+System.lineSeparator(), afterFile);
    }

    // Frame #: 11
    @Test
    public void cleaveTest11() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"-c", "+2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 12
    @Test
    public void cleaveTest12() throws Exception{
        File inputFile = createInputFile("12345678");

        String args[] = {"-c", "10-2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 13
    @Test
    public void cleaveTest13() throws Exception{
        File inputFile = createInputFile("123\t456\t789");

        String args[] = {"-f", "1,3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("123\t789"+System.lineSeparator(), afterFile);
    }

    // Frame #: 14
    @Test
    public void cleaveTest14() throws Exception{
        File inputFile = createInputFile("123\t456\t789");

        String args[] = {"-f", "1+3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 15
    @Test
    public void cleaveTest15() throws Exception{
        File inputFile = createInputFile("123,456,789");

        String args[] = {"-f", "1-2","-d", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 16
    @Test
    public void cleaveTest16() throws Exception{
        File inputFile = createInputFile("123\t4,5,6,\t789");

        String args[] = {"-f", "1-2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("123\t4,5,6,"+System.lineSeparator(), afterFile);
    }

    // Frame #: 17
    @Test
    public void cleaveTest17() throws Exception{
        File inputFile = createInputFile("123,456,789");

        String args[] = {"-f", "1-2", "-d", ",",inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("123,456"+System.lineSeparator(), afterFile);
    }

    // Frame #: 18
    @Test
    public void cleaveTest18() throws Exception{
        File inputFile = createInputFile("123,456,789");

        String args[] = {"-f", "1-2", "-d", ",,", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();
        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 19
    @Test
    public void cleaveTest19() throws Exception{
        File inputFile = createInputFile("123\t456\t789");

        String args[] = {"-f", "2,3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("456\t789"+System.lineSeparator(), afterFile);
    }

    // Frame #: 20
    @Test
    public void cleaveTest20() throws Exception{
        File inputFile = createInputFile("123\t456\t789");

        String args[] = {"-f", "1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("123"+System.lineSeparator(), afterFile);
    }

    // Frame #: 21
    @Test
    public void cleaveTest21() throws Exception{
        File inputFile = createInputFile("12\t34\t56\t78\t90");

        String args[] = {"-f", "1-2,4", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12\t34\t78"+System.lineSeparator(), afterFile);
    }

    // Frame #: 22
    @Test
    public void cleaveTest22() throws Exception{
        File inputFile = createInputFile("12\t34\t56\t78\t90");

        String args[] = {"-f", "1-2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12\t34"+System.lineSeparator(), afterFile);
    }

    // Frame #: 23
    @Test
    public void cleaveTest23() throws Exception{
        File inputFile = createInputFile("123456789");

        String args[] = {"-c", "1,3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("13"+System.lineSeparator(), afterFile);
    }

    // Frame #: 24
    @Test
    public void cleaveTest24() throws Exception{
        File inputFile = createInputFile("123456789");

        String args[] = {"-c", "1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("1"+System.lineSeparator(), afterFile);
    }

    // Frame #: 25
    @Test
    public void cleaveTest25() throws Exception{
        File inputFile = createInputFile("123456789");

        String args[] = {"-c", "1-2,4", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("124"+System.lineSeparator(), afterFile);
    }

    // Frame #: 26
    @Test
    public void cleaveTest26() throws Exception{
        File inputFile = createInputFile("123456789");

        String args[] = {"-c", "1-3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("123"+System.lineSeparator(), afterFile);
    }

    // Frame #: 27
    @Test
    public void cleaveTest27() throws Exception{
        File inputFile = createInputFile("123,456,789");

        String args[] = {"-d", ",", "-f", "1,3",inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("123,789"+System.lineSeparator(), afterFile);
    }

    // Frame #: 28
    @Test
    public void cleaveTest28() throws Exception{
        File inputFile = createInputFile("123,456,789");

        String args[] = {"-d", ",","-f","1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("123"+System.lineSeparator(), afterFile);
    }

    // Frame #: 29
    @Test
    public void cleaveTest29() throws Exception{
        File inputFile = createInputFile("12,34,56,78,90");

        String args[] = {"-d", ",", "-f", "1-2,4", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12,34,78"+System.lineSeparator(), afterFile);
    }

    // Frame #: 30
    @Test
    public void cleaveTest30() throws Exception{
        File inputFile = createInputFile("12,34,56,78,90");

        String args[] = {"-d", ",", "-f", "1-2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12,34"+System.lineSeparator(), afterFile);
    }

    // Frame #: 31
    @Test
    public void cleaveTest31() throws Exception{
        File inputFile = createInputFile("123\t456\t789"+System.lineSeparator()+"ab\tcd\tef"+System.lineSeparator());

        String args[] = {"-f", "1,3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("123\t789"+System.lineSeparator()+"ab\tef"+System.lineSeparator(), afterFile);
    }

    // Frame #: 32
    @Test
    public void cleaveTest32() throws Exception{
        File inputFile = createInputFile("123\t456\t789"+System.lineSeparator()+"ab\tcd\tef"+System.lineSeparator());

        String args[] = {"-f", "1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("123"+System.lineSeparator()+"ab"+System.lineSeparator(), afterFile);
    }

    // Frame #: 33
    @Test
    public void cleaveTest33() throws Exception{
        File inputFile = createInputFile("12\t34\t56\t78\t90"+System.lineSeparator()+"a\tb\tc\td\te"+System.lineSeparator());

        String args[] = {"-f", "1-2,4", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12\t34\t78"+System.lineSeparator()+"a\tb\td"+System.lineSeparator(), afterFile);
    }

    // Frame #: 34
    @Test
    public void cleaveTest34() throws Exception{
        File inputFile = createInputFile("12\t34\t56\t78\t90"+System.lineSeparator()+"a\tb\tc\td\te"+System.lineSeparator());

        String args[] = {"-f", "1-3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12\t34\t56"+System.lineSeparator()+"a\tb\tc"+System.lineSeparator(), afterFile);
    }

    // Frame #: 35
    @Test
    public void cleaveTest35() throws Exception{
        File inputFile = createInputFile("12345"+System.lineSeparator()+"abcde");

        String args[] = {"-c", "1,3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("13"+System.lineSeparator()+"ac"+System.lineSeparator(), afterFile);
    }

    // Frame #: 36
    @Test
    public void cleaveTest36() throws Exception{
        File inputFile = createInputFile("12345"+System.lineSeparator()+"abcde");

        String args[] = {"-c", "1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("1"+System.lineSeparator()+"a"+System.lineSeparator(), afterFile);
    }

    // Frame #: 37
    @Test
    public void cleaveTest37() throws Exception{
        File inputFile = createInputFile("12345"+System.lineSeparator()+"abcde");

        String args[] = {"-c", "1-3,5", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("1235"+System.lineSeparator()+"abce"+System.lineSeparator(), afterFile);
    }

    // Frame #: 38
    @Test
    public void cleaveTest38() throws Exception{
        File inputFile = createInputFile("12345"+System.lineSeparator()+"abcde");

        String args[] = {"-c", "1-3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("123"+System.lineSeparator()+"abc"+System.lineSeparator(), afterFile);
    }

    // Frame #: 39
    @Test
    public void cleaveTest39() throws Exception{
        File inputFile = createInputFile("12,34,56,78,90"+System.lineSeparator()+"a,b,c,d,e");

        String args[] = {"-d", ",", "-f", "1,3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12,56"+System.lineSeparator()+"a,c"+System.lineSeparator(), afterFile);
    }

    // Frame #: 40
    @Test
    public void cleaveTest40() throws Exception{
        File inputFile = createInputFile("12,34,56,78,90"+System.lineSeparator()+"a,b,c,d,e");

        String args[] = {"-d", ",", "-f", "1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12"+System.lineSeparator()+"a"+System.lineSeparator(), afterFile);
    }

    // Frame #: 41
    @Test
    public void cleaveTest41() throws Exception{
        File inputFile = createInputFile("12,34,56,78,90"+System.lineSeparator()+"a,b,c,d,e");

        String args[] = {"-d", ",", "-f", "1-3,5", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12,34,56,90"+System.lineSeparator()+"a,b,c,e"+System.lineSeparator(), afterFile);
    }

    // Frame #: 42
    @Test
    public void cleaveTest42() throws Exception{
        File inputFile = createInputFile("12,34,56,78,90"+System.lineSeparator()+"a,b,c,d,e");

        String args[] = {"-d", ",", "-f", "1-3", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("12,34,56"+System.lineSeparator()+"a,b,c"+System.lineSeparator(), afterFile);
    }



    // Frame #: 43
    @Test
    public void cleaveTest43() throws Exception{
        File inputFile = createInputFile("12345"+System.lineSeparator()+"abcde");

        String args[] = {"-c", "2-", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("2345"+System.lineSeparator()+"bcde"+System.lineSeparator(), afterFile);
    }

    // Frame #: 44
    @Test
    public void cleaveTest44() throws Exception{
        File inputFile = createInputFile("1\t2\t3\t4\t5");

        String args[] = {"-f", "-2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("1\t2"+System.lineSeparator(), afterFile);
    }

    // Frame #: 45
    @Test
    public void cleaveTest45() throws Exception{
        File inputFile = createInputFile("1\t2\t3\t4\t5");

        String args[] = {"-f", "2-", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("2\t3\t4\t5"+System.lineSeparator(), afterFile);
    }


    // Frame #: 46
    @Test
    public void cleaveTest46() throws Exception{
        File inputFile = createInputFile("1\t2\t3\t4\t5");

        String args[] = {"-f", "0", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 47
    @Test
    public void cleaveTest47() throws Exception{
        File inputFile = createInputFile("1\t2\t3\t4\t5");

        String args[] = {"-f", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 48
    @Test
    public void cleaveTest48() throws Exception{
        File inputFile = createInputFile("1,2,3,4,5");

        String args[] = {"-d", ":", "-f", "1-2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("1,2,3,4,5"+System.lineSeparator(), afterFile);
    }


    // Frame #: 49
    @Test
    public void cleaveTest49() throws Exception{
        File inputFile = createInputFile("1,2,3,4,5");

        String args[] = {"-d", "1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 50
    @Test
    public void cleaveTest50() throws Exception{
        File inputFile = createInputFile("1,2,3,4,5");

        String args[] = {"-f", "1", "-c", "1", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 51
    @Test
    public void cleaveTest51() throws Exception{
        File inputFile = createInputFile("1,2,3,4,5");

        String args[] = {"-c", "1", "-d", ",", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 52
    @Test
    public void cleaveTest52() throws Exception{
        File inputFile = createInputFile("1,2,3,4,5");

        String args[] = {"-c", "1", "-c","2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 53
    @Test
    public void cleaveTest53() throws Exception{
        File inputFile = createInputFile("1\t2\t3\t4\t5");

        String args[] = {"-f", "1", "-f","2", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 54
    @Test
    public void cleaveTest54() throws Exception{
        File inputFile = createInputFile("1\t2\t3\t4\t5");

        String args[] = {"-f", "-d", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }


    // Frame #: 55
    @Test
    public void cleaveTest55() throws Exception{
        File inputFile = createInputFile("1\t2\t3\t4\t5");

        String args[] = {"-c", "-d", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }

    // Frame #: 56
    @Test
    public void cleaveTest56() throws Exception{
        File inputFile = createInputFile("1\t2\t3\t4\t5");

        String args[] = {"-c", "0", "-f", "0", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }


    // Frame #: 57
    @Test
    public void cleaveTest57() throws Exception{
        File inputFile = createInputFile("1;2;3;4;5");

        String args[] = {"-d", "1", "-f", "0", inputFile.getPath()};
        Main.main(args);
        var afterFile = outStream.toString();

        assertEquals("Usage: cleave [-c <list> | -f <list> [-d <delim>]] <filename>", errStream.toString().trim());
    }



}
