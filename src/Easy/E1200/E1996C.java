package Easy.E1200;

/**
 * Difficulty : E1200-hard<br>
 * Algorithm : enumerate<br>
 * Feature : enumerate every English letter to save time by using more space<br>
 * Date : 2024.8.23<br>
 */
public class E1996C {

    private enum Strategy { STRATEGY1 }

    private static final int LETTER_AMOUNT = 26;

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[][] results = new int[ testCount ][];
        for( int i=0; i < testCount; i++ ) {
            int length = scanner.nextInt();
            int queryAmount = scanner.nextInt();
            String str1 = scanner.next();
            String str2 = scanner.next();
            interval[] intervals = new interval[ queryAmount ];
            for( int j=0; j < queryAmount; j++ ) {
                int start = scanner.nextInt() - 1;
                int end = scanner.nextInt() - 1;
                intervals[ j ] = new interval( start, end );
            }
            results[ i ] = solution( strategy, str1, str2, intervals );
        }

        for( int i=0; i < testCount; i++ )
            for( int result : results[ i ] )
                System.out.println( result );
    }

    private static int[] solution( Strategy strategy, String str1, String str2, interval[] intervals ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( str1, str2, intervals );
            default:
                return new int[]{};
        }
    }

    /**
     * Description : clever solution<br>
     * Complexity : time O( LETTER_AMOUNT * N ), space O( LETTER_AMOUNT * N )<br>
     * Complexity : N - times of queries, length of intervals<br>
     * Feature : enumerate every English letter to save time by using more space<br>
     *
     * @param str1 string 1
     * @param str2 string 2
     * @param intervals intervals of queries
     * @return result of queries
     */
    private static int[] solution1( String str1, String str2, interval[] intervals ) {
        int[][] prefixAmountArray1 = prefixAmountArray( str1 );
        int[][] prefixAmountArray2 = prefixAmountArray( str2 );

        int[] result = new int[ intervals.length ];
        for( int i=0; i < intervals.length; i++ ) {
            int resultTemp = 0;
            for( int j=0; j < LETTER_AMOUNT; j++ ) {
                int amount1 = prefixAmountArray1[ j ][ intervals[ i ].end + 1 ] - prefixAmountArray1[ j ][ intervals[ i ].start ];
                int amount2 = prefixAmountArray2[ j ][ intervals[ i ].end + 1 ] - prefixAmountArray2[ j ][ intervals[ i ].start ];
                resultTemp += Math.max( amount1 - amount2, 0 );
            }
            result[ i ] = resultTemp;
        }

        return result;
    }

    private static int[][] prefixAmountArray( String str ) {
        int[][] prefixAmountArray = new int[ LETTER_AMOUNT ][];

        for( int i=0; i < LETTER_AMOUNT; i++ ) {
            prefixAmountArray[ i ] = new int[ str.length() + 1 ];
            java.util.Arrays.fill( prefixAmountArray[ i ], 0 );
        }

        for( int i=0; i < str.length(); i++ ) {
            for( int j=0; j < LETTER_AMOUNT; j++ )
                prefixAmountArray[ j ][ i+1 ] = prefixAmountArray[ j ][ i ];
            prefixAmountArray[ str.charAt( i ) - 'a' ][ i+1 ]++;
        }

        return prefixAmountArray;
    }

    private static class interval{
        int start;
        int end;
        public interval( int start, int end ) { this.start = start; this.end = end; }
    }

}