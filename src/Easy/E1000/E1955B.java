package Easy.E1000;

/**
 * Difficulty : E1000-medium<br>
 * Date : 2024.7.23<br>
 */
public class E1955B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[] results = new boolean[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            int[] arr = new int[ arrLength * arrLength ];
            for( int j=0; j < arrLength * arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr, arrLength, x, y );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] ? "YES" : "NO" );
    }

    private static boolean solution( Strategy strategy, int[] arr, int length, int x, int y ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr, length, x, y );
            default:
                return false;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N^2 * logN ), space O( N^2 )<br>
     * Complexity : N - length of matrix<br>
     *
     * @param arr given array
     * @param length length of matrix
     * @param x given x
     * @param y given y
     * @return result
     */
    private static boolean solution1( int[] arr, int length, int x, int y ) {

        // iterate over array and find min integer as a11
        int min = Integer.MAX_VALUE;
        for ( int num : arr)
            if ( num < min )
                min = num;

        // reconstruct matrix and store in a temp array
        int[] tempArr = new int[ length * length ];
        for( int i=0; i < length; i++ )
            for( int j=0; j < length; j++ )
                tempArr[ i * length + j ] = min + i * x + j * y;

        // sort and compare given array and temp array
        java.util.Arrays.sort( arr );
        java.util.Arrays.sort( tempArr );
        for( int i=0; i < arr.length; i++ )
            if( arr[ i ] != tempArr[ i ] )
                return false;

        return true;
    }

}
