package Easy.E1100;

/**
 * Difficulty : E1100-easy<br>
 * Algorithm : enumerate<br>
 * Date : 2024.7.27<br>
 */
public class E1950D {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[] results = new boolean[ testCount ];
        for( int i=0; i < testCount; i++ )
            results[ i ] = solution( strategy, scanner.nextInt() );

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] ? "YES" : "NO" );
    }

    private static boolean solution( Strategy strategy, int num ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( num );
            default:
                return false;
        }
    }

    /**
     * Description : simple solution, enumerate solution<br>
     * Complexity : time O( 1 ), space O( 1 )<br>
     *
     * @param num given num
     * @return result
     */
    private static boolean solution1( int num ) {

        int[] binaryDecimals = new int[] { 1, 10, 11, 101, 111, 1001, 1011, 1101, 1111,
                10001, 10011, 10101, 10111, 11001, 11011, 11101, 11111 };
        for( int binaryDecimal : binaryDecimals )
            if( num == binaryDecimal )
                return true;

        int[] factors = new int[] { 10, 11, 101, 111 };
        for( int factor : factors )
            if( num % factor == 0 )
                return solution1( num / factor );

        return false;

    }

}