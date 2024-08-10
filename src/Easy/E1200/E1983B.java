package Easy.E1200;

/**
 * Difficulty : E1200-hard<br>
 * Algorithm : math<br>
 * Solution : solution1<br>
 * Feature : use mod theory to prove algorithm<br>
 * Date : 2024.8.10<br>
 */
public class E1983B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy =  Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[] results = new boolean[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            int[][] arrA = new int[ row ][ col ];
            for( int j=0; j < row; j++ ) {
                String[] nums = scanner.next().split( "" );
                for( int k=0; k < col; k++ )
                    arrA[ j ][ k ] = Integer.parseInt( nums[ k ] );
            }

            int[][] arrB = new int[ row ][ col ];
            for( int j=0; j < row; j++ ) {
                String[] nums = scanner.next().split( "" );
                for( int k=0; k < col; k++ )
                    arrB[ j ][ k ] = Integer.parseInt( nums[ k ] );
            }
            results[ i ] = solution( strategy, arrA, arrB );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] ? "YES" : "NO" );
    }

    private static boolean solution( Strategy strategy, int[][] arrA, int[][] arrB ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arrA, arrB );
            default:
                return false;
        }
    }

    /**
     * Description : clever solution, math solution<br>
     * Complexity : time O( M * N ), space O( M * N )<br>
     * Complexity : M, N - length of matrix<br>
     * Feature : use mod theory to prove algorithm<br>
     *
     * <p>
     * Idea : just verify whether sum of every row and column is equal<br>
     * Idea : we can prove it by mod theory<br>
     *
     * <p>
     * Idea : consider two easy matrix { { 1, 2 }, { 2, 1 } } and { { 2, 1 }, { 1, 2 } }<br>
     * Idea : we can add them any times and get a special matrix M[ m ][ n ]<br>
     * Idea : M[ 0 ][ 0 ] == 1, M[ 1 ][ 0 ] == 2<br>
     * Idea : M[ m-2 ][ n-1 ] == 1, M[ m-1 ][ n-1 ] == 2<br>
     * Idea : other elements of M are all 0<br>
     *
     * <p>
     * Idea : we can add M to A to make A == B<br>
     * Idea : iterate over every element and add M to A<br>
     * Idea : for element A[ i ][ j ], set M[ row - ( i-1 ) ][ col - ( j-1 ) ]<br>
     * Idea : add M[ 0 ][ 0 ] to A[ i ][ j ]<br>
     * Idea : and add M[ m-1 ][ n-1 ] to A[ row - 1 ][ col - 1 ]<br>
     * Idea : add 0M, 1M, 2M to A to make A[ i ][ j ] == B[ i ][ j ]<br>
     *
     * <p>
     * Idea : mod 3 result of sum of any row or column remain after an operation<br>
     * Idea : if sum of every row and column is equal<br>
     * Idea : A must be equal to B at the end<br>
     *
     * @param arrA given matrix A
     * @param arrB given matrix B
     * @return result
     */
    private static boolean solution1( int[][] arrA, int[][] arrB ) {

        int row = arrA.length;
        int col = arrA[ 0 ].length;

        for( int i = 0; i < row; i++ ) {
            int rowSumA = 0;
            for( int j=0; j < col; j++ )
                rowSumA += arrA[ i ][ j ];
            int rowSumB = 0;
            for( int j=0; j < col; j++ )
                rowSumB += arrB[ i ][ j ];
            if( ( rowSumA % 3 ) != ( rowSumB % 3 ) )
                return false;
        }

        for( int j=0; j < col; j++ ) {
            int colSumA = 0;
            for( int i=0; i < row; i++ )
                colSumA += arrA[ i ][ j ];
            int colSumB = 0;
            for( int i=0; i < row; i++ )
                colSumB += arrB[ i ][ j ];
            if( ( colSumA % 3 ) != ( colSumB % 3 ) )
                return false;
        }

        return true;
    }

}