package Easy.E1000;

/**
 * Difficulty : E1000B-medium<br>
 * Algorithm : constructive<br>
 * Feature : use number theory to constructive and code is easy<br>
 * Date : 2024.7.23<br>
 */
public class E1930B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[][] results = new int[ testCount ][];
        for( int i=0; i < testCount; i++ )
            results[ i ] = solution( strategy, scanner.nextInt() );

        for( int i=0; i < testCount; i++ ) {
            for (int num : results[i])
                System.out.print(num + " ");
            System.out.println();
        }
    }

    private static int[] solution( Strategy strategy, int length ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( length );
            default:
                return new int[] {};
        }
    }

    /**
     * Description : simple solution, constructive solution<br>
     * Complexity : time O( N ), space O( N )<br>
     * Complexity : N - length of result<br>
     * Hint : for any x > length / 2, there does not exist y such that y is divided by x<br>
     * Idea : construct array = { 1, n, 2, n-1, ... }<br>
     *
     * @param length length of array
     * @return result
     */
    private static int[] solution1( int length ) {
        int[] arr = new int[ length ];
        for( int i=0; i < length; i++ )
            arr[ i ] = ( i % 2 == 0 ) ? ( i / 2 + 1 ) : ( length - i / 2 );
        return arr;
    }

}
