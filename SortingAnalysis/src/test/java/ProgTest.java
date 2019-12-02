import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.junit.Assert;

/* Run a series of tests to make sure all the sorting algorithms work */
public class ProgTest {
    @Test public void testInsertionCompareCount() {
        Assert.assertTrue(compareCount("items10.txt", 10, new InsertionSort()));
    }
    @Test public void testInsertionSwapCount() {
        Assert.assertTrue(swapCount("items10.txt", 10, new InsertionSort()));
    }
    @Test public void testInsertionCompareTime() {
        Assert.assertTrue(compareTime("items10.txt", 10, new InsertionSort()));
    }    
    @Test public void testInsertionSort10() {
        Assert.assertTrue(sortTest("items10.txt", 10, new InsertionSort()));
    }
    @Test public void testInsertionSort1000() {
        Assert.assertTrue(sortTest("items.txt", 1000, new InsertionSort()));
    }
    @Test public void testInsertionSort10000() {
        Assert.assertTrue(sortTest("items10000.txt", 10000, new InsertionSort()));
    }
    @Test public void testMergeCompareCount() {
        Assert.assertTrue(compareCount("items10.txt", 10, new MergeSort()));
    }
    @Test public void testMergeCompareTime() {
        Assert.assertTrue(compareTime("items10.txt", 10, new MergeSort()));
    }
    @Test public void testMergeSwapCount() {
        Assert.assertTrue(swapCount("items10.txt", 10, new MergeSort()));
    }
    @Test public void testMergeSort10() {
        Assert.assertTrue(sortTest("items10.txt", 10, new MergeSort()));
    }
    @Test public void testMergeSort1000() {
        Assert.assertTrue(sortTest("items.txt", 1000, new MergeSort()));
    }
    @Test public void testMergeSort10000() {
        Assert.assertTrue(sortTest("items10000.txt", 10000, new MergeSort()));
    }
    @Test public void testQuickCompareTime() {
        Assert.assertTrue(compareTime("items10.txt", 10, new QuickSort()));
    }    
    @Test public void testQuickCompareCount() {
        Assert.assertTrue(compareCount("items10.txt", 10, new QuickSort()));
    }    
    @Test public void testQuickSwapCount() {
        Assert.assertTrue(swapCount("items10.txt", 10, new QuickSort()));
    }
    @Test public void testQuickSort10() {
        Assert.assertTrue(sortTest("items10.txt", 10, new QuickSort()));
    }
    @Test public void testQuickSort1000() {
        Assert.assertTrue(sortTest("items.txt", 1000, new QuickSort()));
    }
    @Test public void testQuickSort10000() {
        Assert.assertTrue(sortTest("items10000.txt", 10000, new QuickSort()));
    }


    private boolean sortTest(String fileName, int arraySize, SortingInterface sort)
    {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File(fileName));
        } catch (Exception e) {
            return false;
        }
        int[] testArray = new int[arraySize];
        for(int i = 0; i < arraySize; i++)
            testArray[i] = fileReader.nextInt();
        fileReader.close();


        List<Integer> testList = new ArrayList<Integer>();
        for(int i : testArray)
            testList.add(i);
        Collections.sort(testList);

        int[] sortedArray = sort.sort(testArray);

        for(int i = 0; i < arraySize; i++)
        {
            if(sortedArray[i] != testList.get(i))
                return false;
        }
        return true;
    }
    private boolean compareCount(String fileName, int arraySize, SortingInterface sort)
    {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File(fileName));
        } catch (Exception e) {
            return false;
        }
        int[] testArray = new int[arraySize];
        for(int i = 0; i < arraySize; i++)
            testArray[i] = fileReader.nextInt();
        fileReader.close();


        List<Integer> testList = new ArrayList<Integer>();
        for(int i : testArray)
            testList.add(i);
        Collections.sort(testList);

        sort.sort(testArray);

        if(sort.getComparisonCount() == 0) return false;
        return true;
    }
    private boolean swapCount(String fileName, int arraySize, SortingInterface sort)
    {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File(fileName));
        } catch (Exception e) {
            return false;
        }
        int[] testArray = new int[arraySize];
        for(int i = 0; i < arraySize; i++)
            testArray[i] = fileReader.nextInt();
        fileReader.close();


        List<Integer> testList = new ArrayList<Integer>();
        for(int i : testArray)
            testList.add(i);
        Collections.sort(testList);

        sort.sort(testArray);

        if(sort.getSwapCount() == 0) return false;
        return true;
    }
    private boolean compareTime(String fileName, int arraySize, SortingInterface sort)
    {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File(fileName));
        } catch (Exception e) {
            return false;
        }
        int[] testArray = new int[arraySize];
        for(int i = 0; i < arraySize; i++)
            testArray[i] = fileReader.nextInt();
        fileReader.close();


        List<Integer> testList = new ArrayList<Integer>();
        for(int i : testArray)
            testList.add(i);
        Collections.sort(testList);

        sort.sort(testArray);

        if(sort.getComparisonTime() == 0) return false;
        return true;
    }
}
