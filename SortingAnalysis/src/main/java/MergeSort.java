public class MergeSort implements SortingInterface {

    /* Comparison counter for Sorting Interface */
    private int _comparisonCounter = 0;
    /* Time counter for Sorting Interface */
    private long _comparisonTime = 0;
    /* Swap counter for Sorting Interface */
    private int _swapCounter = 0;

    /**
     * mergeSort
     * Purpose: Fit with expectation of program having a method called MergeSort
     * @param sortArray array to sort
     * @param size size of array
     * @return sorted array
     */
    public int[] MergeSort(int[] sortArray, int size)
    {
        return sort(sortArray, size);
    }

    /**
     * sort
     * Purpose: Sort array with mergesort algorithm
     * @param array array to sort
     * @return sorted array
     */
    public int[] sort(int[] array)
    {
        return sort(array, array.length);
    }

    /**
     * sort
     * Purpose: Sort array of given size with mergesort algorithm
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
        int[] localCopy = array.clone();
        /* Time only the sorting */
        long timeToStart = System.nanoTime();
        sort(localCopy, 0, (size-1) / 2, size-1);
        _comparisonTime = System.nanoTime() - timeToStart;
        return localCopy;
    }
    /**
     * sort
     * Purpose: Sort array implementing mergeSort algorithm
     * @param array array to sort
     * @param begin Where the sub array starts
     * @param mid Where the sub array's midpoint is
     * @param end Where the sub array ends
     */
    private void sort(int[] array, int begin, int mid, int end)
    {
        if(begin == end) return;
        sort(array, begin, (begin + mid) / 2, mid);
        sort(array, mid+1, (mid + end) / 2, end);

        int[] sortedTempArray = new int[1 + end - begin];
        int beginIterator = begin;
        int midIterator = mid+1;
        int sortIterator = 0;
        while(beginIterator <= mid && midIterator <= end)
        {
            _comparisonCounter++; /* One comparison is made */
            if(array[beginIterator] < array[midIterator])
            {
                sortedTempArray[sortIterator++] = array[beginIterator++];
            }
            else
            {
                sortedTempArray[sortIterator++] = array[midIterator++];
            }
            _swapCounter++; /* One swap must be made */
        }
        /* No more swaps */
        while(beginIterator <= mid)
        {
            sortedTempArray[sortIterator++] = array[beginIterator++];
        }
        while(midIterator <= end)
        {
            sortedTempArray[sortIterator++] = array[midIterator++];
        }
        for(int i = 0; i < sortedTempArray.length; i++)
        {
            array[begin+i] = sortedTempArray[i];
        }
    }
    
    /* Required methods for interface */
    public int getComparisonCount() { return _comparisonCounter; }
    public long getComparisonTime() { return _comparisonTime; }
    public int getSwapCount() { return _swapCounter; }
}