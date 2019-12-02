public class QuickSort implements SortingInterface {

    /* Comparison counter for Sorting Interface */
    private int _comparisonCounter = 0;
    /* Time counter for Sorting Interface */
    private long _comparisonTime = 0;
    /* Swap counter for Sorting Interface */
    private int _swapCounter = 0;

    /**
     * quickSort
     * Purpose: Fit with expectation of program having a method called QuickSort
     * @param sortArray array to sort
     * @param size size of array
     * @return sorted array
     */
    public int[] QuickSort(int[] sortArray, int size)
    {
        return sort(sortArray, size);
    }

    /**
     * sort
     * Purpose: Sort array using quicksort algorithm
     * @param array array to sort
     * @return sorted array
     */
    public int[] sort(int[] array)
    {
        return sort(array, array.length);
    }

    /**
     * sort
     * Purpose: Sort array using quicksort algorithm
     * @param array array to sort
     * @param size size of array to sort
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
        long timeToStart = System.nanoTime();
        sort(localCopy, 0, size-1);
        _comparisonTime = System.nanoTime() - timeToStart;
        return localCopy;
    }
    /**
     * sort
     * Purpose: Implement quicksort algorithm in sorting array
     * @param array array to sort
     * @param begin beginning of subsection of array
     * @param end end of subsection of array
     */
    private void sort(int[] array, int begin, int end)
    {
        if(end - begin < 1) return;

        int pivot = array[begin];
        int leftPointer = begin + 1;
        int rightPointer = end;
        while(leftPointer <= rightPointer)
        {
            _comparisonCounter += 4; /* 4 comparisons will be made */

            if(array[leftPointer] >= pivot && array[rightPointer] <= pivot)
            {
                _swapCounter++; /* One swap is made */
                int tmp = array[leftPointer];
                array[leftPointer] = array[rightPointer];
                array[rightPointer] = tmp;
            }
            
            if(array[leftPointer] <= pivot) leftPointer++;
            if(array[rightPointer] > pivot) rightPointer--;
        }
        _swapCounter++; /* One more swap is made */
        array[begin] = array[rightPointer];
        array[rightPointer] = pivot;

        sort(array, begin, rightPointer-1);
        sort(array, leftPointer, end);
    }

    /* Required methods for interface */
    public int getComparisonCount() { return _comparisonCounter; }
    public long getComparisonTime() { return _comparisonTime; }
    public int getSwapCount() { return _swapCounter; }
}