package Easy.E1000;

/**
 * Difficulty : E1000-medium<br>
 * Date : 2024.7.22<br>
 */
public class E1975B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[] results = new boolean[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] ? "YES" : "NO" );
    }

    private static boolean solution( Strategy strategy, int[] arr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr, 2 );
            default:
                return false;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N ), space O( N )<br>
     * Complexity : N - length of arr<br>
     * Feature : this solution can be expanded to any amount of factors<br>
     *
     * <p>
     * Idea : iterate over given array and check whether every element can be divided by min<br>
     * Idea : construct a new array consisting of remaining elements<br>
     * Idea : repeat the above process util there does not exist factors<br>
     *
     * @param arr given array
     * @param factorAmount 2 factors
     * @return result
     */
    private static boolean solution1( int[] arr, int factorAmount ) {

        int min = Integer.MAX_VALUE;
        for( int num : arr )
            min = Math.min( min, num );

        int[] tempArr = new int[ arr.length ];
        int length = 0;
        for( int num : arr )
            if( num % min != 0 )
                tempArr[ length++ ] = num;
        arr = java.util.Arrays.copyOf( tempArr, length );

        if( factorAmount == 1 )
            return arr.length == 0;
        else
            return solution1( arr, factorAmount - 1 );
    }

}