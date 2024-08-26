package Easy.E1200;

import java.util.HashMap;

/**
 * Difficulty : E1200-medium<br>
 * Data structure : hashSet<br>
 * Algorithm : greedy, enumerate<br>
 * Date : 2024.8.26<br>
 */
public class E1934B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ )
            results[ i ] = solution( strategy, scanner.nextInt() );

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, int value ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( value );
            default:
                return -1;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( 1 ), space O( 1 )<br>
     * Hint : greedy algorithm, but need a little modify<br>
     *
     * <p>
     * Idea : if value >= 30, use 15<br>
     * Idea : if 15 <= value < 30, use 15 except value == 20 or 23<br>
     * Idea : if value < 15, use perfect int hashMap<br>
     *
     * @param value value
     * @return result
     */
    private static int solution1( int value ) {
        HashMap<Integer, Integer[]> perfect = new HashMap<>();
        perfect.put( 2, new Integer[]{ 1, 1 } );
        perfect.put( 4, new Integer[]{ 1, 3 } );
        perfect.put( 5, new Integer[]{ 1, 1, 3 } );
        perfect.put( 7, new Integer[]{ 1, 6 } );
        perfect.put( 8, new Integer[]{ 1, 1, 6 } );
        perfect.put( 9, new Integer[]{ 3, 6 } );
        perfect.put( 11, new Integer[]{ 1, 10 } );
        perfect.put( 12, new Integer[]{ 6, 6 } );
        perfect.put( 13, new Integer[]{ 3, 10 } );
        perfect.put( 14, new Integer[]{ 1, 3, 10 } );
        perfect.put( 20, new Integer[]{ 10, 10 } );
        perfect.put( 23, new Integer[]{ 3, 10, 10 } );

        int result = value / 15;
        value %= 15;
        if( result > 0 ) { result--; value += 15; }

        if( value >= 15 )
            if( perfect.containsKey( value ) )
                return result + perfect.get( value ).length;
            else {
                result++; value -= 15;
            }

        if( value == 0 ) return result;
        if( value == 1 || value == 3 || value == 6 || value == 10 )
            return result + 1;
        return result + perfect.get( value ).length;
    }

}