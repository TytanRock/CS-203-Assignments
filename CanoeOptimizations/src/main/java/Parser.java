
/**
 * Helper class to parse string input into a int double array
 */
public class Parser
{
    /**
     * Parse string into a multi array to work with
     * @param val String value to parse
     * @return Multi Array of parsed values
     */
    public static int[][] parse(String[] lines)
    {
        int n = Integer.parseInt(lines[0])-1;
        int[][] ret = new int[n][n];

        /* Initialize array to all 0's */
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                ret[i][j] = 0;

        /* Cascade down the multi array with input */
        for(int i = 0; i < n; i++)
        {
            /* Break input based on whitespace */
            String[] nums = lines[i+1].trim().split("\\s+"); 
            for(int j = i; j < n; j++)
            {
                ret[i][j] = Integer.parseInt(nums[j-i]);
            }
        }

        return ret;
    }
}