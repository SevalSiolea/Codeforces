package Easy.E1200;

/**
 * Difficulty : E1200-easy<br>
 * Date : 2024.8.22<br>
 */
public class E1954B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, int[] arr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr );
            default:
                return -1;
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
    private static int solution1( int[] arr ) {

        if( arr[ 0 ] != arr[ arr.length - 1 ] ) return 0;

        int same = arr[ 0 ];
        int min = Integer.MAX_VALUE;
        int current = 0;
        for( int i=0; i < arr.length; i++ ) {
            if( arr[ i ] == same )
                current++;
            else {
                min = Math.min( min, current );
                current = 0;
            }
        }
        min = Math.min( min, current );

        return ( min == arr.length ? -1 : min );

    }

}
