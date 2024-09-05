package Medium.M1300;

/**
 * Difficulty : M1300-easy<br>
 * Date : 2024.9.5<br>
 */
public class M1999E {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            results[ i ] = solution( strategy, start, end );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, int start, int end ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( start, end );
            default:
                return -1;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( logN + logM ), space O( 1 )<br>
     * Complexity : N - length of interval, M - max of interval<br>
     *
     * @param start start
     * @param end end
     * @return result
     */
    private static int solution1( int start, int end ) {
        int optAmount = 0;
        for( int i = start; i > 0; i /= 3 ) optAmount++;

        int result = optAmount * 2;
        int bound = (int) Math.pow( 3, optAmount );
        while( bound - 1 <= end ) {
            result += ( bound - 1 - start ) * optAmount;
            start = bound - 1;
            bound *= 3;
            optAmount++;
        }
        result += ( end - start ) * optAmount;

        return result;
    }

}