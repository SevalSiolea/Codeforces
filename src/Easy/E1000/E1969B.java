package Easy.E1000;

/**
 * Difficulty : E1000-hard<br>
 * Algorithm : simulate, math<br>
 * Feature : a clever math algorithm in solution2<br>
 * Solution : solution2<br>
 * Date : 2024.7.23<br>
 * <p>
 * solution2 : a clever math algorithm<br>
 */
public class E1969B {

    private enum Strategy { STRATEGY1, STRATEGY2 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY2;

        int testCount = scanner.nextInt();
        long[] results = new long[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            String str = scanner.next();
            results[ i ] = solution( strategy, str );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static long solution( Strategy strategy, String str ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( str );
            case STRATEGY2:
                return solution2( str );
            default:
                return -1L;
        }
    }

    /**
     * Description : simple solution, simulate solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - length of str<br>
     *
     * <p>
     * Idea : select the substring of many 1's and single 0<br>
     * Idea : cyclically shift the substring, then increase start and end<br>
     * Idea : when increasing end, skip over all 1's until the first 0<br>
     *
     * @param str given binary string
     * @return result
     */
    private static long solution1( String str ) {

        String[] strs = str.split( "" );

        int start = 0;
        while( start < strs.length && strs[ start ].equals( "0" ) )
            start++;
        int end = start;
        while( end < strs.length && strs[ end ].equals( "1" ) )
            end++;

        long cost = 0L;
        while( end < strs.length ) {
            cost += end - start + 1;
            start++;
            end++;
            while( end < strs.length && strs[ end ].equals( "1" ) )
                end++;
        }

        return cost;
    }

    /**
     * Description : math solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - length of str<br>
     * Feature : a clever math solution<br>
     *
     * <p>
     * Idea : cost of given operation can be interpreted as follows<br>
     * Idea : pay 1 for the element of the end and pay 1 for any other element<br>
     * Idea : we need move every 0 to the left and move every 1 to the right<br>
     * Idea : pay at least 1 for every 0, if it has any 1 on the left<br>
     * Idea : pay at least n for every 1, if it has n 0's on the right<br>
     * Idea : we can prove the result is lower bound<br>
     *
     * @param str given binary string
     * @return result
     */
    private static long solution2( String str ) {

        String[] strs = str.split( "" );
        long cost = 0L;

        boolean preOne = false;
        for ( String s : strs ) {
            if ( s.equals( "1" ) )
                preOne = true;
            else
                cost += preOne ? 1L : 0L;
        }

        int zeroAmount = 0;
        for( int i = strs.length - 1; i >= 0; i-- ) {
            if( strs[ i ].equals( "0" ) )
                zeroAmount++;
            else
                cost += zeroAmount;
        }

        return cost;
    }

}