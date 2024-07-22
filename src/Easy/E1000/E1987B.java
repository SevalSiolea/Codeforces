package Easy.E1000;

/**
 * Difficulty : E1000-medium<br>
 * Algorithm : simulate<br>
 * Feature : 2 algorithms of simulate and calculate<br>
 * Solution : solution1, solution2<br>
 * Date : 2024.7.22<br>
 */
public class E1987B {

    private enum Strategy { STRATEGY1, STRATEGY2 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY2;

        int testCount = scanner.nextInt();
        long[] coins = new long[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            coins[ i ] = solution( strategy, arr );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( coins[ i ] );
    }

    private static long solution( Strategy strategy, int[] arr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr );
            case STRATEGY2:
                return solution2( arr );
            default:
                return -1L;
        }
    }

    /**
     * Description : simulate solution<br>
     * Complexity : time O( N * M ), space O( 1 )<br>
     * Complexity : N - length of arr, M - max of arr<br>
     *
     * @param arr given array
     * @return result
     */
    private static long solution1( int[] arr ) {

        long coin = 0L;

        for( int i=0; i < arr.length - 1; i++ )
            while( arr[ i ] > arr[ i+1 ] ) {
                for( int j = i; j < arr.length - 1; j++ ) {
                    if( arr[ j ] > arr[ j+1 ] ) {
                        arr[ j+1 ]++;
                        coin++;
                    }
                }
                coin++;
            }

        return coin;
    }

    /**
     * Description : calculate solution<br>
     * Complexity : time O( N * logN ), space O( N )<br>
     * Complexity : N - length of arr<br>
     *
     * @param arr given array
     * @return result
     */
    private static long solution2( int[] arr ) {

        int currentMax = arr[ 0 ];
        int[] increases = new int[ arr.length ];
        increases[ 0 ] = 0;
        for( int i=0; i < arr.length - 1; i++ )
            if( arr[ i+1 ] < currentMax )
                increases[ i+1 ] = currentMax - arr[ i+1 ];
            else {
                increases[ i+1 ] = 0;
                currentMax = arr[ i+1 ];
            }

        java.util.Arrays.sort( increases );

        long coin = 0L;
        for( int i=0; i < arr.length - 1; i++ )
            coin += (long) ( arr.length - i ) * ( increases[ i+1 ] - increases[ i ] );
        return coin;

    }

}