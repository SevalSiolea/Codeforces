package Easy.E1100;

/**
 * Difficulty : E1100-easy<br>
 * Algorithm : math<br>
 * Feature : analysis need thinking, but algorithm is easy<br>
 * Date : 2024.7.27<br>
 */
public class E1984B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[] results = new boolean[ testCount ];
        for( int i=0; i < testCount; i++ )
            results[ i ] = solution( strategy, scanner.nextLong() );

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] ? "YES" : "NO" );
    }

    private static boolean solution( Strategy strategy, long num ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( num );
            default:
                return false;
        }
    }

    /**
     * Description : simple solution, math solution<br>
     * Complexity : time O( N ), space O( N );<br>
     * Complexity : digit count of num<br>
     * Feature : analysis need thinking, but algorithm is easy<br>
     *
     * @param num given num
     * @return result
     */
    private static boolean solution1( long num ) {
        String[] strs = String.valueOf( num ).split( "" );
        if( !strs[ 0 ].equals( "1" ) )
            return false;
        for( int i=1; i < strs.length - 1; i++ )
            if( strs[ i ].equals( "0" ) )
                return false;
        return !strs[ strs.length - 1 ].equals( "9" );
    }

}
