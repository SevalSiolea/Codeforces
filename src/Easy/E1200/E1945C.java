package Easy.E1200;

/**
 * Difficulty : E1200-medium<br>
 * Date : 2024.8.23<br>
 */
public class E1945C {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            String str = scanner.next();
            results[ i ] = solution( strategy, str );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, String str ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( str );
            default:
                return -1;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - length of str<br>
     *
     * @param str given array
     * @return result
     */
    private static int solution1( String str ) {

        String[] strs = str.split( "" );

        int count0 = 0;
        int count1 = 0;
        for( int i=0; i < strs.length; i++ )
            if( strs[ i ].equals( "0" ) )
                count0++;
            else if( strs[ i ].equals( "1" ) )
                count1++;

        int location = ( count1 >= count0 ) ? -1 : -2;
        int current0 = 0;
        int current1 = 0;
        for( int i=0; i < strs.length; i++ ) {
            if( strs[ i ].equals( "0" ) )
                current0++;
            else if( strs[ i ].equals( "1" ) )
                current1++;
            if( current0 >= current1 && count1 - current1 >= count0 - current0 )
                if( ( (long) ( i - location ) ) * ( (long) ( i + location + 2 - strs.length ) ) < 0L ) // equal to compare abs
                    location = i;

        }

        return location + 1;

    }

}
