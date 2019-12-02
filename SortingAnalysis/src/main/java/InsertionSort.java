public class InsertionSort implements SortingInterface {

    /* Comparison counter for Sorting Interface */
    private int _comparisonCounter = 0;
    /* Time counter for Sorting Interface */
    private long _comparisonTime = 0;
    /* Swap counter for Sorting Interface */
    private int _swapCounter = 0;

    /**
     * insertionSort
     * Purpose: Fit with expectation of program having a method called InsertionSort
     * @param sortArray Array to sort
     * @param size Size of Array
     * @return sorted array
     */
    public int[] InsertionSort(int[] sortArray, int size)
    {
        return sort(sortArray, size);
    }

    /**
     * sort
     * Purpose: Sort array using insertion sort
     * @param array array to sort
     * @return sorted array 
     */
    public int[] sort(int[] array)
    {
        return sort(array, array.length);
    }

    /**
     * sort
     * Purpose: Sort array of given size using insertionSort
     * @param array array to sort
     * @param size size of array
     * @return sorted array
     */
    public int[] sort(int[] array, int size)
    {
        if(size == 0) return array;
        /* Zero all local variables */
        _comparisonCounter = 0;
        _comparisonTime = 0;
        _swapCounter = 0;
        /* Ensure local copy of array is not modifying caller's array */
        int[] localArray = array.clone();
        long timeToStart = System.nanoTime();
        privateSort(localArray, size);
        _comparisonTime = System.nanoTime() - timeToStart;
        return localArray;
    }
    /**
     * privateSort
     * Purpose: Sort array implementing InsertionSort
     * @param array array to sort
     * @param size size of array to sort
     */
    private void privateSort(int[] array, int size)
    {
        /* Use a for loop instead of recursion due to stackoverflows at large values */
        for(int end = 0; end < size - 1; end++)
        {
            int element = end + 1;
            for(int i = 0; i <= end; i++)
            {
                _comparisonCounter++; /* One comparison is made */
                if(array[i] > array[element])
                {
                    _swapCounter++; /* One swap is made, everything else is pushed */
                    int tmp = array[element];
                    for(int j = element; j > i; j--)
                    {
                        _swapCounter++; /* Pushing elements down the list counts as a swap */
                        array[j] = array[j-1];
                    }
                    array[i] = tmp;
                    break;
                }
            }
        }
    }

    /* Required methods for interface */
    public int getComparisonCount() { return _comparisonCounter; }
    public long getComparisonTime() { return _comparisonTime; }
    public int getSwapCount() { return _swapCounter; }
}