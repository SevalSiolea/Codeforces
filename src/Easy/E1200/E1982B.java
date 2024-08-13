package Easy.E1200;

/**
 * Difficulty : E1200-medium<br>
 * Date : 2024.8.13<br>
 */
public class E1982B {

    private enum Strategy { STRATEGY1, STRATEGY2 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY2;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int num = scanner.nextInt();
            int divisor = scanner.nextInt();
            int count = scanner.nextInt();
            results[ i ] = solution( strategy, num, divisor, count );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, int num, int divisor, int count ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( num, divisor, count );
            case STRATEGY2:
                return solution2( num, divisor, count );
            default:
                return -1;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - amount times of operations<br>
     *
     * @param num given x
     * @param divisor given y
     * @param count count of operations
     * @return result
     */
    private static int solution1( int num, int divisor, int count ) {

        for( int i=0; i < count; i++ ) {
            num++;
            while( num % divisor == 0 )
                num /= divisor;
        }

        return num;
    }

    /**
     * Description : simple solution, optimized solution<br>
     * Complexity : time O( logN ), space O( 1 )<br>
     * Complexity : N - amount times of operations<br>
     *
     * @param num given x
     * @param divisor given y
     * @param count count of operations
     * @return result
     */
    private static int solution2( int num, int divisor, int count ) {

        for( int i=0; i < count; i++ ) {
            if( num == 1 )
                return num + ( count - i ) % ( divisor - 1 );

            int diff = divisor - num % divisor;
            if( i + diff <= count ) {
                num += diff;
                i += diff - 1;
            } else
                return num + ( count - i );

            while( num % divisor == 0 )
                num /= divisor;

        }

        return num;
    }

}