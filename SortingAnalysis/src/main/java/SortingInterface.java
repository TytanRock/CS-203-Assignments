public interface SortingInterface {
    public int[] sort(int[] values, int size);
    public int[] sort(int[] values);
    public int getComparisonCount();
    public long getComparisonTime();
    public int getSwapCount();
}