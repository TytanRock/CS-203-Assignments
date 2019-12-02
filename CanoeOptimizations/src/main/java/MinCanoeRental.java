
/**
 * Class to wrap up the algorithm into a nicely packaged area
 */
public class MinCanoeRental
{
    /* Keep a reference to _cached part when findMin is called
    *  To find the post sequence afterwards */
    public static MatrixLayer _cachedPart = null;
    /**
     * findMin
     * Find the minimum value of the given sample input
     * @param matrix Integer array of input
     * @return Integer of minimum value possible
     */
    public static int[][] findMin(int[][] matrix)
    {
        int[][] optimalCostMatrix = new int[matrix.length][matrix.length];

        /* Create an array of Algorith Parts to hold onto */
        MatrixLayer[] allAlgorithms = new MatrixLayer[matrix.length];
        /* Specify the last algorithm part as special */
        MatrixLayer lastPart = null;
        /* Create a new algorithm part for each layer of the input */
        for(int n = 0; n < matrix.length; n++)
        {
            for(int i = n; i >=0; i--)
            {
                /* Take optimal cost of every layer and place it in an array */
                lastPart = new MatrixLayer(matrix, i, allAlgorithms, n);
                optimalCostMatrix[i][n] = lastPart._value;
            }
        }

        /* Cache the last algorithm part */
        _cachedPart = lastPart;
        /* Return the last part's value */
        return optimalCostMatrix;
    }
    /**
     * findPost
     * Find the post sequence for the given sample input
     * @return String of post sequence to find minimum value
     */
    public static String findPost() 
    {
        if(_cachedPart == null)
            return "Call findMi()n first"; 
        return "0->" + _cachedPart.getPost(); 
    }
}