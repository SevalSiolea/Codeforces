package Easy.E1200;

/**
 * Difficulty : E1200-medium<br>
 * Date : 2024.8.28<br>
 */
public class E1928B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, int[] arr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr );
            default:
                return -1;
        }
    }

    /**
     * Description : clever solution<br>
     * Complexity : time O( N * logN ), space O( N )<br>
     * Complexity : N - length of arr<br>
     *
     * @param arr given array
     * @return result
     */
    private static int solution1( int[] arr ) {
        java.util.HashSet<Integer> hashSet = new java.util.HashSet<>();
        for( int num : arr )
            hashSet.add( num );
        Integer[] hashArray = hashSet.toArray( new Integer[ 0 ] );
        java.util.Arrays.sort( hashArray );

        int result = 1;
        for( int start = 0, end = 0; end < hashArray.length; start++, end++ ) {
            while( end < hashArray.length && hashArray[ start ] + arr.length >= hashArray[ end ] + 1 )
                end++;
            result = Math.max( end - start, result );
        }

        return result;
    }

}