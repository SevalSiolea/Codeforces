package Easy.E1200;

/**
 * Difficulty : E1200-medium<br>
 * Feature : a little difficult simulate solution<br>
 * Date : 2024.8.22<br>
 */
public class E1951B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int idx = scanner.nextInt() - 1;
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr, idx );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, int[] arr, int idx ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr, idx );
            default:
                return -1;
        }
    }

    /**
     * Description : simple solution, simulate solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - length of arr<br>
     *
     * @param arr given array
     * @param idx given index
     * @return result
     */
    private static int solution1( int[] arr, int idx ) {

        int first = arr.length;
        for( int i=0; i < arr.length; i++ )
            if( arr[ i ] > arr[ idx ] ) {
                first = i;
                break;
            }

        int second = arr.length;
        for( int i = first + 1; i < arr.length; i++ )
            if( arr[ i ] > arr[ idx ] ) {
                second = i;
                break;
            }

        if( idx > second )
            return Math.max( second - first - 1 + ( first == 0 ? 0 : 1 ), first - 1 );
        else if( idx < first )
            return first - 1;
        else
            return Math.max( idx - first - 1 + ( first == 0 ? 0 : 1 ), first - 1 );
    }

}
