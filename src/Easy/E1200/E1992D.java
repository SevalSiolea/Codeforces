package Easy.E1200;

/**
 * Difficulty : E1200-medium<br>
 * Date : 2024.8.9<br>
 */
public class E1992D {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[] results = new boolean[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int length = scanner.nextInt();
            int jumpLength = scanner.nextInt();
            int swimLength = scanner.nextInt();
            String river = scanner.next();
            results[ i ] = solution( strategy, river, jumpLength, swimLength );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] ? "YES" : "NO" );
    }

    private static boolean solution( Strategy strategy, String river, int jumpLength, int swimLength ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( river, jumpLength, swimLength );
            default:
                return false;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - length of river<br>
     *
     * @param river given riven
     * @param jumpLength length of jump
     * @param swimLength all length of swim
     * @return result
     */
    private static boolean solution1( String river, int jumpLength, int swimLength ) {

        String[] rivers = ( "L" + river + "L" ).split( "" );

        if( jumpLength >= rivers.length - 1 ) return true;

        int current = 0;
        int nextSwim = 0;
        int allSwim = 0;
        for( int i=0; i < rivers.length; i++ ) {

            if( rivers[ i ].equals( "W" ) && i - current <= jumpLength )
                nextSwim = i;

            if( rivers[ i ].equals( "L" ) ) {
                if( i - current <= jumpLength ) {
                    current = i;
                    continue;
                }

                if( i - nextSwim > swimLength )
                    return false;

                for( int j = nextSwim; j < i; j++ )
                    if( rivers[ j ].equals( "C" ) )
                        return false;
                allSwim += i - nextSwim;
                current = i;
            }

        }

        return allSwim <= swimLength;
    }

}