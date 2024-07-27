package Easy.E1100;

/**
 * Difficulty : E1100-medium<br>
 * Algorithm : enumerate<br>
 * Date : 2024.7.27<br>
 */
public class E1976B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        long[] results = new long[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arrA = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arrA[ j ] = scanner.nextInt();
            int[] arrB = new int[ arrLength + 1 ];
            for( int j=0; j < arrLength + 1; j++ )
                arrB[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arrA, arrB );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static long solution( Strategy strategy, int[] arrA, int[] arrB ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arrA, arrB );

            default:
                return -1L;
        }
    }

    /**
     * Description : simple solution, enumerate solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - length of array A<br>
     * Hint : consider every situation for copying arrB[ n ]<br>
     *
     * @param arrA given array A
     * @param arrB given array B
     * @return result
     */
    private static long solution1( int[] arrA, int[] arrB ) {

        long cost = 0L;
        for( int i=0; i < arrA.length; i++ )
            cost += Math.abs( arrA[ i ] - arrB[ i ] );

        int costEnd = Integer.MAX_VALUE;
        int end = arrB[ arrB.length - 1 ];
        for( int i=0; i < arrA.length; i++ ) {
            if( arrA[ i ] >= arrB[ i ] && arrB[ i ] > end )
                costEnd = Math.min( costEnd, arrB[ i ] - end );
            else if( arrB[ i ] >= arrA[ i ] && arrA[ i ] > end )
                costEnd = Math.min( costEnd, arrA[ i ] - end );
            else if( arrA[ i ] <= arrB[ i ] && arrB[ i ] < end )
                costEnd = Math.min( costEnd, end - arrB[ i ] );
            else if( arrB[ i ] <= arrA[ i ] && arrA[ i ] < end )
                costEnd = Math.min( costEnd, end - arrA[ i ] );
            else {
                costEnd = 0;
                break;
            }
        }

        return cost + costEnd + 1;
    }

}