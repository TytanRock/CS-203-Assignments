/**
 * Class where the algorithm really takes place in a recursive setting
 */
public class MatrixLayer 
{
    /* Values to hold onto that have use */
    int _value = Integer.MAX_VALUE; //Minimum value given by this layer
    int _post = -1;                 //Post to get minimum value
    MatrixLayer _nextPart = null;   //Next algorithm part to find next post

    /**
     * Constructor
     * Construct the algorithm part and do all the math needed to find the
     * minimum value in here
     * @param matrix Integer multi array of sample input
     * @param layer Current layer the algorithm is on
     * @param higherParts Algorithm Part array with all the previous parts
     */
    public MatrixLayer(int[][] matrix, int layer, MatrixLayer[] higherParts, int max)
    {
        /* Add this object to the array for later reference */
        higherParts[layer] = this;
        /* If this is the first layer, assign the only values possible and return */
        if(layer == max)
        {
            _value = matrix[layer][layer];
            _post = layer;
            _nextPart = null;
            return;
        }

        /* Otherwise check all the possible options available to it based on
        *  previous algorithm's minimum values and find the minimum of them all */
        for(int i = layer; i < max; i++)
        {
            int checkValue = matrix[layer][i] + higherParts[i+1]._value;
            if(checkValue < _value)
            {
                _value = checkValue;
                _nextPart = higherParts[i+1];
                _post = i;
            }
        }
        /* Ensure the minimum value is not the last value */
        if(matrix[layer][max] < _value)
        {
            _value = matrix[layer][max];
            _nextPart = null;
            _post = max;
        }
    }

    /**
     * getPost
     * Get this algorithm part's post information and prepend it to the
     * next algorithm part's post information
     * @return String of all the posts
     */
    public String getPost()
    {
        if(_nextPart == null) return (_post + 1) + "";
        return (_post + 1) + "->" + _nextPart.getPost();
    }
}