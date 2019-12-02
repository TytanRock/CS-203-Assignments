import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class Prog2 {

    /* Variable to name the size of every sample for statistical significance */
    private static final int SAMPLE_SIZE = 50;
    /* Variable to name the maximum size a list can be to be sorted */
    private static final int MAX_SIZE = 15000;
    /* Variable to name the increment size for lists to be sorted */
    private static final int SIZE_INCREMEMNT = 1000;
    /* Variable to decide what list generation we're using */
    private static final ListGeneration GENERATION_STYLE = ListGeneration.Sorted;

    /* Enum to help decide what list generation we're using */
    private enum ListGeneration 
    {
        Random,
        Sorted,
        ReverseSorted,
    };

    /**
     * Main
     * Purpose: Run the program
     * @param args An array of strings that get passed, possibly containing
     *  file name to write analysis to.
     */
    public static void main(String[] args) {
        File analysisFile = null;
        /* If args is not null, use args as the specified file to print to */
        try
        {
            if(args != null && args[0] != null)
            {
                analysisFile = new File(args[0] + ".csv");
                analysisFile.delete();
                analysisFile.createNewFile();
            }
        }
        catch(Exception ex)
        {
            /* Ensure file is null */
            analysisFile = null;
        }
        /* Keep these as null, we assign them in the testSort method */
        long[] timings = null;
        int[] comparisons = null;
        int[] swaps = null;
        /* Have all the sorting algorithms initialized */
        InsertionSort insertionSort = new InsertionSort();
        QuickSort quickSort = new QuickSort();
        MergeSort mergeSort = new MergeSort();
        
        
        /* Create FileWriter */
        FileWriter writer;
        try
        {
            writer = new FileWriter(analysisFile, true);
        }
        catch(Exception ex)
        {
            /* If there's an exception, don't write to the file */
            writer = null;
        }
        generateHeaders(writer);
        
        for(int n = 0; n <= MAX_SIZE; n += SIZE_INCREMEMNT)
        {
            testAllSorts(insertionSort, mergeSort, quickSort,
                        SAMPLE_SIZE, n, timings, comparisons, swaps,
                        GENERATION_STYLE, writer);
        }

        try
        {
            writer.close();
        }
        catch(Exception ex)
        {
            /* Do nothing */
        }
    }

    /**
     * TestAllSorts
     * Purpose: Test insertion, merge, and quick sort with the given parameters
     * @param insertion     Local variable of insertionSort algorithm
     * @param merge         Local variable of mergeSort algorithm
     * @param quick         Local variable of quickSort algorithm
     * @param numberOfLoops Number of times we should sort to get an estimate
     * @param sizeOfLists   Size of the lists to sort
     * @param timing        Long array reference to hold timing statistics
     * @param comparisons   Int array reference to hold comparison statistics
     * @param swaps         Int array reference to hold swap statistics
     * @param generation    Type of generation to use 
     * @param writer        Reference to file writer to write statistics to
     */
    public static void testAllSorts(InsertionSort insertion, MergeSort merge,
                                    QuickSort quick, 
                                    int numberOfLoops, int sizeOfLists,
                                    long[] timing, int[] comparisons, int[] swaps, 
                                    ListGeneration generation, FileWriter writer)
    {
        if(writer != null)
            QuickWrite("" + sizeOfLists, writer);

        testSort(insertion, numberOfLoops, sizeOfLists, timing, 
                comparisons, swaps, generation, writer);
        testSort(merge, numberOfLoops, sizeOfLists, timing, 
                comparisons, swaps, generation, writer);
        testSort(quick, numberOfLoops, sizeOfLists, timing, 
                comparisons, swaps, generation, writer);

        if(writer != null)
            QuickWrite("\n", writer);
    }

    /**
     * testSort
     * Purpose: Test sorting a list with the given parameters
     * @param sortAlgorithm Sorting algorithm to use
     * @param numberOfLoops Number of times to sort to get statistics
     * @param sizeOfLists   Size of lists to sort
     * @param timing        Long array with timing statistics
     * @param comparisons   Int array with comparison statistics
     * @param swaps         Int array with swap statistics
     * @param generation    Type of list generation to use
     * @param writer        Reference to file writer to write statistics to
     */
    public static void testSort(SortingInterface sortAlgorithm, int numberOfLoops, int sizeOfLists,
                                long[] timing, int[] comparisons, int[] swaps, 
                                ListGeneration generation, FileWriter writer)
    {
        if(writer == null)
            System.out.println(sortAlgorithm.getClass().getSimpleName()  + " at " 
            + sizeOfLists + " elements: ");
        
        int[] testArray;
        timing = new long[numberOfLoops];
        comparisons = new int[numberOfLoops];
        swaps = new int[numberOfLoops];
        /* Test Insertion Sort numberOfLoops times with random lists of size sizeOfLists, getting statistics */
        for(int i = 0; i < numberOfLoops; i++)
        {
            testArray = null;
            switch(generation)
            {
                case Random:
                    testArray = generateRandomList(sizeOfLists);
                    break;
                case Sorted:
                    testArray = generateSortedList(sizeOfLists);
                    break;
                case ReverseSorted:
                    testArray = generateReversedList(sizeOfLists);
                    break;
            }
            sortAlgorithm.sort(testArray);
            timing[i] = sortAlgorithm.getComparisonTime();
            comparisons[i] = sortAlgorithm.getComparisonCount();
            swaps[i] = sortAlgorithm.getSwapCount();
        }

        printTimingStatistics(timing, writer);
        printComparisonStatistics(comparisons, writer);
        printSwapStatistics(swaps, writer);
    }

    /**
     * printTimingStatistics
     * Purpose: Write timing statistics to file or print them to console
     * @param array Timing statistics
     * @param analysisFile FileWriter reference to write to file
     */
    public static void printTimingStatistics(long[] array, FileWriter analysisFile)
    {
        float total = 0;
        for(long i : array)
            total += i;
        total = total / array.length;
        total = ((float)total)/1000000000;
        if(analysisFile == null)
        {
            System.out.println("  Timing Statistics:");
            System.out.println("    " + total + "s on average");
        }
        else
        {
            QuickWrite("," + total, analysisFile);
        }
    }

    /**
     * printComparisonStatistics
     * Purpose: Write comparison statistics to file or print them to console
     * @param array Comparison statistics
     * @param analysisFile FileWriter reference to write to file
     */
    public static void printComparisonStatistics(int[] array, FileWriter analysisFile)
    {
        float total = 0;
        for(int i : array)
            total += i;
        total = total / array.length;
        if(analysisFile == null)
        {
            System.out.println("  Comparisons:");
            System.out.println("    " + total + " comparisons on average");
        }
        else
        {
            QuickWrite("," + total, analysisFile);
        }
    }

    /**
     * printSwapStatistics
     * Purpose: Write swap statistics to file or print them to console
     * @param array Swap statistics
     * @param analysisFile FileWriter reference to write to file
     */
    public static void printSwapStatistics(int[] array, FileWriter analysisFile)
    {
        float total = 0;
        for(int i : array)
            total += i;
        total = total / array.length;
        if(analysisFile == null)
        {
            System.out.println("  Swaps:");
            System.out.println("    " + total + " swaps on average");
        }
        else
        {
            QuickWrite("," + total, analysisFile);
        }
    }

    /**
     * generateRandomList
     * Purpose: Generate a random list of size size
     * @param size Size of list to be generated
     * @return int[] of size size
     */
    public static int[] generateRandomList(int size)
    {
        int[] retval = new int[size];
        Random rand = new Random();
        for(int i = 0; i < size; i++)
            retval[i] = rand.nextInt();
        return retval;
    }
    /**
     * generateSortedList
     * Purpose: Generate a sorted list of size size
     * @param size Size of list to be generated
     * @return int[] of size size
     */
    public static int[] generateSortedList(int size)
    {
        int retval[] = new int[size];
        for(int i = 0; i < size; i++)
            retval[i] = i;
        return retval;
    }
    /**
     * generateReversedList
     * Purpose: Generate a reverse-sorted list of size size
     * @param size Size of list to be generated
     * @return int[] of size size
     */
    public static int[] generateReversedList(int size)
    {
        int[] retval = new int[size];
        for(int i = 0; i < size; i++)
            retval[i] = size - i;
        return retval;
    }
    /**
     * generateHeaders
     * Purpose: Print headers to file if it exists
     * @param writer FileWriter reference to file to write to
     */
    public static void generateHeaders(FileWriter writer)
    {
        if(writer == null) return;

        QuickWrite("n," + 
                    "Insertion Sort Timing, Insertion Sort Comparisons, Insertion Sort Swaps," +
                    "Merge Sort Timing, Merge Sort Comparisons, Merge Sort Swaps," +
                    "Quick Sort Timing, Quick Sort Comparisons, Quick Sort Swaps,\n",
                    writer);
        
    }
    /**
     * QuickWrite
     * Purpose: Print to file if we don't care about any exceptions getting thrown
     * @param mess Message to write
     * @param writer FileWriter reference to write file to if it exists.
     */
    private static void QuickWrite(String mess, FileWriter writer)
    {
        try
        {
            writer.write(mess);
        }
        catch(Exception ex)
        {
            /* Not used */
        }
    }
}
