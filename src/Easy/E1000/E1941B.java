package Easy.E1000;

/**
 * Difficulty : E1000-easy<br>
 * Date : 2024.7.23<br>
 */
public class E1941B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[] results = new boolean[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] ? "YES" : "NO" );
    }

    private static boolean solution( Strategy strategy, int[] arr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr );
            default:
                return false;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - length of arr<br>
     *
     * @param arr given array
     * @return result
     */
    private static boolean solution1( int[] arr ) {

        for( int i=0; i < arr.length - 2; i++ ) {
            if( arr[ i ] == 0 ) continue;

            arr[ i+1 ] -= arr[ i ] * 2;
            arr[ i+2 ] -= arr[ i ];
            if( arr[ i+1 ] < 0 || arr[ i+2 ] < 0 )
                return false;
        }

        return arr[ arr.length - 1 ] == 0 && arr[ arr.length - 2 ] == 0;
    }

}
