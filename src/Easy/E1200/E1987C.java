package Easy.E1200;

/**
 * Difficulty : E1200-hard<br>
 * Algorithm : simulate, calculate<br>
 * Solution : solution2<br>
 * solution2 : analysis of solution2 is difficult<br>
 * Date : 2024.8.7<br>
 */
public class E1987C {

    private enum Strategy { STRATEGY1, STRATEGY2 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY2;

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
            case STRATEGY2:
                return solution2( arr );
            default:
                return -1;
        }
    }

    /**
     * Description : simple solution, simulate solution<br>
     * Complexity : time O( N * ( M + N ) ), space O( 1 )<br>
     * Complexity : N - length of arr, M - max of arr<br>
     *
     * @param arr given array
     * @return result
     */
    private static int solution1( int[] arr ) {

        int dieCount = 0;
        int time = 0;
        while( dieCount < arr.length ) {
            for( int i=0; i < arr.length - 1 - dieCount; i++ ) {
                if( arr[ i ] > arr[ i+1 ] )
                    arr[ i ]--;
            }
            if( arr[ arr.length - 1 - dieCount ] > 0 )
                arr[ arr.length - 1 - dieCount ]--;
            if( arr[ arr.length - 1 - dieCount ] == 0 )
                dieCount++;
            time++;
        }

        return time;
    }

    /**
     * Description : clever solution, calculate solution<br>
     * Complexity : time O( N ), space O( N )<br>
     * Complexity : N - length of arr<br>
     * Hint : analyse time of arr[ i ] decreasing to 0 from right to left<br>
     * Feature : analysis is difficult<br>
     *
     * <p>
     * Idea : denote time[ i ] as time of arr[ i ] decreasing to 0<br>
     * Idea : consider time[ i-1 ] by arr[ i-1 ], arr[ i ], time[ i ]<br>
     * Idea : if arr[ i-1 ] == arr[ i ] at some time, time [ i-1 ] = time[ i ] + 1<br>
     * Idea : else, arr[ i-1 ] decrease continuously, time[ i-1 ] = arr[ i-1 ]<br>
     * Idea : if arr[ i-1 ] <= arr[ i ], this is the first situation<br>
     * Idea : else, arr[ i-1 ] may be equal to arr[ i ], may be not<br>
     *
     * <p>
     * Idea : if arr[ i-1 ] <= time[ i ], arr[ i-1 ] == 0 and arr[ i ] > 0 at the end<br>
     * Idea : this is impossible, so arr[ i-1 ] == arr[ i ] must be true at some time<br>
     * Idea : thus time[ i-1 ] = time[ i ] + 1<br>
     * Idea : if arr[ i-1 ] > time[ i ], then arr[ i-1 ] >= time[ i ] + 1<br>
     * Idea : we cant determine which situation this is<br>
     * Idea : but time[ i ] >= arr[ i-1 ] >= time[ i ] + 1 is always true<br>
     * Idea : combine the above situations, we get time[ i-1 ] = max( arr[ i-1 ], time[ i ] + 1 )<br>
     *
     * @param arr given array
     * @return result
     */
    private static int solution2( int[] arr ) {

        int[] times = new int[ arr.length ];
        times[ arr.length - 1 ] = arr[ arr.length - 1 ];
        for( int i = arr.length - 2; i >= 0; i-- ) {
            times [ i ] = Math.max( arr[ i ], times[ i+1 ] + 1 );
        }

        return times[ 0 ];
    }

}