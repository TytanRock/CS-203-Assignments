/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test public void testAppDoesntCrash() {
        Object o = null;
        try
        {
            // Command line argument-specified input
            Prog1.main(new String[] {"1"});
            // Null object passed
            Prog1.main(null);
            // File passed
            Prog1.main(new String[] {"SampleInput.txt"});
            // Invalid file passed
            Prog1.main(new String[] {".txt"});
            // Bad input string passed
            Prog1.main(new String[] {"1 (1 (3,2)"});
            o = new Object();
        }
        catch(Exception e)
        { }
        assertNotNull(o);
    }
}