package Easy.E1000;

/**
 * Difficulty : E1000-easy<br>
 * Date : 2024.7.22<br>
 */
public class E1985C {

    private enum Strategy { STRATEGY1, STRATEGY2 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY2;

        int testCount = scanner.nextInt();
        int[] prefixes = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            prefixes[ i ] = solution( strategy, arr );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( prefixes[ i ] );
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
     * Description : simple solution<br>
     * Complexity : time O( N ^ 2 ), space O( 1 )<br>
     * Complexity : N - length of arr<br>
     * Feature : use double loops so spend too much time<br>
     *
     * @param arr given array
     * @return result
     */
    private static int solution1( int[] arr ) {

        int prefixCount = 0;
        for( int i=0; i < arr.length; i++ ) {
            long max = 0;
            long sum = 0;
            for( int j=0; j <= i; j++ ) {
                sum += arr[ j ];
                if( max < arr[ j ] )
                    max = arr[ j ];
            }
            if( sum == 2 * max )
                prefixCount++;
        }

        return prefixCount;
    }

    /**
     * Description : simple solution, optimized solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - length of arr<br>
     * Feature : decrease one loop compared to solution1<br>
     *
     * @param arr given array
     * @return result
     */
    private static int solution2( int[] arr ) {

        int prefixCount = 0;
        long sum = 0;
        long max = 0;
        for( int i=0; i < arr.length; i++ ) {
            sum += arr[ i ];
            if( max < arr[ i ] )
                max = arr[ i ];

            if( sum == 2 * max )
                prefixCount++;
        }

        return prefixCount;
    }

}