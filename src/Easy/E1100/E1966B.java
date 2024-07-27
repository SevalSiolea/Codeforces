package Easy.E1100;

/**
 * Difficulty : E1100-easy<br>
 * Feature : programming is a little difficult<br>
 * Date : 2024.7.27<br>
 */
public class E1966B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[] results = new boolean[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrRow = scanner.nextInt();
            int arrCol = scanner.nextInt();
            String[][] grid = new String[ arrRow ][ arrCol ];
            for( int j=0; j < arrRow; j++ )
                    grid[ j ] = scanner.next().split( "" );
            results[ i ] = solution( strategy, grid );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] ? "YES" : "NO" );
    }

    private static boolean solution( Strategy strategy, String[][] grid ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( grid );
            default:
                return false;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( M + N ), space O( 1 )<br>
     * Complexity : M - row of grid, N - column of grid<br>
     * Feature : programming is a little difficult<br>
     *
     * @param grid given grid
     * @return result
     */
    private static boolean solution1( String[][] grid ) {

        int row = grid.length;
        int col = grid[ 0 ].length;

        if( grid[ 0 ][ 0 ].equals( grid[ row - 1 ][ col - 1 ] ) )
            return true;
        if( grid[ row - 1 ][ 0 ].equals( grid[ 0 ][ col - 1 ] ) )
            return true;

        boolean inRow = ( grid[ 0 ][ 0 ].equals( grid[ 0 ][ col - 1 ] ) );
        if( inRow ) {
            for( int i=0; i < col; i++ )
                if( grid[ 0 ][ i ].equals( grid[ row - 1 ][ 0 ] ) )
                    return true;
            for( int i=0; i < col; i++ )
                if( grid[ row - 1 ][ i ].equals( grid[ 0 ][ 0 ] ) )
                    return true;
        } else {
            for( int i=0; i < row; i++ )
                if( grid[ i ][ 0 ].equals( grid[ 0 ][ col - 1 ] ) )
                    return true;
            for( int i=0; i < row; i++ )
                if( grid[ i ][ col - 1 ].equals( grid[ 0 ][ 0 ] ) )
                    return true;
        }

        return false;
    }

}