/*****************************/
/* Cory Ness                 */
/* Login ID: ness2627        */
/* CS-203, Fall 2018         */
/* Programming Assignment 1  */
/*****************************/

import java.util.*;
import java.io.File;
/**
 * Main Class Prog1
 */
public class Prog1 {

    /****************************************************/
    /* Method: Main                                     */
    /* Purpose: Run when java application is called     */
    /* Parameters:                                      */
    /*    String[] Args - Command-line Arguments        */
    /* Returns void                                     */
    /****************************************************/
    public static void main(String[] args) {
        /* Input is a list of strings that holds all the data */
        String[] input;
        
        /* If command-line args is empty, prompt user */
        if(args == null || args.length == 0) 
        {
            input = new String[1];
            input[0] = promptUserForInput();
        }
        /* Otherwise use command line args */
        else
        {
            input = new String[args.length];
            /* Replace '-'s for spaces due to arg limitation in vsCode */
            for(int i = 0; i < args.length; i++)
                input[i] = args[i].replace('-', ' ');
        }
        
        /** 
         * If input line ends with .txt extension we know it is a txt file
         * and should read the text file for graph data
         **/ 
        if(input[0].endsWith(".txt"))
        {
            input = readFile(input[0]);
        }

        /* Start graph number at 1, and increment from there */
        int graphNumber = 1;
        for(String graphString : input)
        {
            /* Detail what graph this is and what the content was */
            System.out.println("Graph " + graphNumber++ + 
                               "'s input was: " + graphString);

            /* Execute full program with current graph */
            if(graphString != null)
                mainProgram(graphString);
            System.out.println();
        }
    }

    /****************************************************/
    /* Method: readFile                                 */
    /* Purpose: Read specified file and return an aray  */
    /*    of Strings to be parsed by a Graph            */
    /* Parameters:                                      */
    /*    String fileName - Name of file to read        */
    /* Returns: String array of text the file contains  */ 
    /****************************************************/
    private static String[] readFile(String fileName)
    {
        Scanner fileReader;
        try
        {
            /* If new scanner or file throws, just null fileReader */
            fileReader = new Scanner(new File(fileName));
        }
        catch(Exception e)
        {
            fileReader = null;
        }

        /* Create new list of Strings to add strings while reading file */
        List<String> tempInputList = new ArrayList<String>();
        try
        {
            /* Continually add strings until fileReader throws */
            String nextLine = fileReader.nextLine();
            while(true)
            {
                tempInputList.add(nextLine);
                nextLine = fileReader.nextLine();
            }
        }
        catch(Exception e)
        {
            //Reached end of file
        }

        /* Ensure fileReader is not null */
        if(fileReader != null)
        {
            /* Make sure we close the Scanner to free resources */
            fileReader.close();
        }
        /* Assign input to list of added inputs */
        return tempInputList.toArray(new String[0]);
    }

    /************************************************************************/
    /* Method: promptUserForInput                                           */
    /* Purpose: Prompt user for input if commandline arguments are empty    */
    /* Parameters:                                                          */
    /* Returns: String of input or file name user entered                   */
    /************************************************************************/
    private static String promptUserForInput(){
        /* If system does not support console, let user know */
        if(System.console() == null) {
            System.out.println("Please use command line arguments,"+ 
                            " this does not support console input");
            return "";
        }
        /* Prompt user for input line */
        System.out.println("Enter line of input or .txt file");
        String retval = System.console().readLine();


        return retval;
    }

    /************************************************************/
    /* Method: mainProgram                                      */
    /* Purpose: Create new graphs and print output to user      */
    /* Parameters:                                              */
    /*    String stringGraph - String to be parsed into a graph */
    /*  Returns void                                            */
    /************************************************************/
    private static void mainProgram(String stringGraph)
    {
        /* Create a new graph with the graph's input string */
        Graph graph;
        try
        {
            graph = new Graph(stringGraph);
        }
        catch(Exception e)
        {
            System.out.println("Line: " + stringGraph + 
                            "\nThis is not a valid line of input");
            return;
        }

        /* Print the components of the graph */
        System.out.println(graph.getComponents());
        /* Print the cycles of the graph */
        System.out.println(graph.getCycle());
    }
}
