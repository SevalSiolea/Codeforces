package Easy.E1200;

/**
 * Difficulty : E1200-easy<br>
 * Date : 2024.8.20<br>
 */
public class E1954C {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        String[][] results = new String[ testCount ][];
        for( int i=0; i < testCount; i++ ) {
            String x = scanner.next();
            String y = scanner.next();
            results[ i ] = solution( strategy, x, y );
        }

        for( int i=0; i < testCount; i++ )
            for( String str : results[ i ] )
                System.out.println( str );

    }

    private static String[] solution( Strategy strategy, String x, String y ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( x, y );
            default:
                return new String[] {};
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N ), space O( N )<br>
     * Complexity : N -length of str<br>
     *
     * @param x given x
     * @param y given y
     * @return result
     */
    private static String[] solution1( String x, String y ) {

        int length = x.length();

        String[] xs = x.split( "" );
        int[] xDigits = new int[ length ];
        for( int i=0; i < length; i++ )
            xDigits[ i ] = Integer.parseInt( xs[i] );

        String[] ys = y.split( "" );
        int[] yDigits = new int[ length ];
        for( int i=0; i < length; i++ )
            yDigits[ i ] = Integer.parseInt( ys[i] );


        int moreIdx = 0;
        while( moreIdx < length ) {
            if( xDigits[ moreIdx ] != yDigits[ moreIdx ] ) {
                if( xDigits[ moreIdx ] < yDigits[ moreIdx ] ) {
                    int temp = xDigits[ moreIdx ];
                    xDigits[ moreIdx ] = yDigits[ moreIdx ];
                    yDigits[ moreIdx ] = temp;
                }
                break;
            }
            moreIdx++;
        }

        for( int i = moreIdx + 1; i < length; i++ )
            if( xDigits[ i ] > yDigits[ i ] ) {
                int temp = xDigits[ i ];
                xDigits[ i ] = yDigits[ i ];
                yDigits[ i ] = temp;
            }

        StringBuilder xBuilder = new StringBuilder();
        for( int digit : xDigits )
            xBuilder.append( digit );

        StringBuilder yBuilder = new StringBuilder();
        for( int digit : yDigits )
            yBuilder.append( digit );

        return new String[] { xBuilder.toString(), yBuilder.toString() };

    }

}