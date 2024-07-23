package Easy.E1000;

/**
 * Difficulty : E1000-medium<br>
 * Feature : little integers represent different solutions<br>
 * Date : 2024.7.23<br>
 */
public class E1927C {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[] results = new boolean[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int lengthA = scanner.nextInt();
            int lengthB = scanner.nextInt();
            int k = scanner.nextInt();
            int[] arrA = new int[ lengthA ];
            for( int j=0; j < lengthA; j++ )
                arrA[ j ] = scanner.nextInt();
            int[] arrB = new int[ lengthB ];
            for( int j=0; j < lengthB; j++ )
                arrB[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arrA, arrB, k );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] ? "YES" : "NO" );
    }

    private static boolean solution( Strategy strategy, int[] arrA, int[] arrB, int k ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arrA, arrB, k );
            default:
                return false;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N1 + N2 ), space O( N1 + N2 )<br>
     * Complexity : N1 - length of array A, N2 - length of array B<br>
     * Feature : little integers represent occurring count<br>
     * Feature : 0 in neither, 1 in A, 2 in B, 3 = 1 + 2 in both<br>
     *
     * @param arrA array A
     * @param arrB array B
     * @param k given k
     * @return result
     */
    private static boolean solution1( int[] arrA, int[] arrB, int k ) {

        // iterate over A and determine every number in A or in both
        int[] resultA = new int[ k ];
        java.util.Arrays.fill( resultA, 0 );
        for( int num : arrA )
            if ( num <= k )
                resultA[ num - 1 ] = 1;

        // iterate over B and determine every number in B or in both
        int[] resultB = new int[ k ];
        java.util.Arrays.fill( resultB, 0 );
        for( int num : arrB )
            if ( num <= k )
                resultB[ num - 1 ] = 2;

        // summary occurring count and return result
        int[] result = new int[] { 0, 0, 0, 0 };
        for( int i=0; i < k; i++ )
            result[ resultA[ i ] + resultB[ i ] ]++;
        return ( result[ 0 ] == 0 ) && ( result[ 1 ] <= k / 2 ) && ( result[ 2 ] <= k / 2 );
    }

}
