import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Prog3 Class to run main
 */
public class Prog3 {

    /**
     * Main
     * Executes when java program is ran
     * @param args String array of arguments to specify file
     */
    public static void main(String[] args) {
        File fileToRead;
        Scanner fileReader;
        try
        {
            /* Open file and try to open scanner on file */
            fileToRead = new File(args[0]);
            fileReader = new Scanner(fileToRead);
        }
        catch(Exception e)
        {
            /* If file cannot open, tell user and close */
            System.out.println("Could not open file");
            return;
        }
        /* File opened correctly, create a list of strings to read the contents */
        List<String> lines = new ArrayList<String>();
        while(fileReader.hasNextLine())
            lines.add(fileReader.nextLine().trim());

        /* Always make sure we free resources */
        fileReader.close();

        /* Parse values into int double array */
        int[][] values = Parser.parse(lines.toArray(new String[lines.size()]));

		int[][] optimalCostMatrix = MinCanoeRental.findMin(values);
        /* Read out the solution to the given input */
		System.out.println("Minimum value is: ");
		/* Print out every column for the optimal cost matrix */
		for(int i = 0; i < values.length; i++)
		{
			/* Print out every row for the optimal cost */
			for(int j = 0; j < values.length; j++)
				System.out.print(optimalCostMatrix[i][j] + " ");
			System.out.println();
		}
		System.out.println("Path to achieve minimum value from post 0 to post " +
		values.length +" is: " + MinCanoeRental.findPost());
    }
}
