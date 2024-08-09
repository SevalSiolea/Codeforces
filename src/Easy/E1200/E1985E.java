package Easy.E1200;

/**
 * Difficulty : E1100-easy<br>
 * Algorithm : enumerate<br>
 * Feature : enumerate all situations because of small range<br>
 * Date : 2024.8.9<br>
 */
public class E1985E {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        long[] results = new long[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            long volume = scanner.nextLong();
            results[ i ] = solution( strategy, x, y, z, volume );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static long solution( Strategy strategy, int x, int y, int z, long volume ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( x, y, z, volume );
            default:
                return -1L;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N^2 ), space O( 1 )<br>
     * Complexity : N - max length of box<br>
     * Feature : enumerate all situations because of small range<br>
     *
     * @param x given x
     * @param y given y
     * @param z given z
     * @param volume given volume
     * @return result
     */
    private static long solution1( int x, int y, int z, long volume ) {

        if( x > y ) { int temp = x; x = y; y = temp; }
        if( x > z ) { int temp = x; x = z; z = temp; }
        if( y > z ) { int temp = y; y = z; z = temp; }

        long max = 0;
        long current = 0;
        for( long i=1; i <= x; i++ )
            for( long j=1; j <= y; j++ ) {
                long k = volume / ( i * j );
                if( k > z || i * j * k != volume )
                    continue;

                current = ( x + 1 - i ) * ( y + 1 - j ) * ( z + 1 - k );
                if( max < current )
                    max = current;
            }

        return max;
    }

}