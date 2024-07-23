package Easy.E1000;

/**
 * Difficulty : E1000-medium<br>
 * Date : 2024.7.23<br>
 */
public class E1931C {

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

        int prefix = arr[ 0 ];
        int prefixLength = 1;
        while( prefixLength < arr.length && arr[ prefixLength - 1 ] == arr[ prefixLength ] )
            prefixLength++;

        int suffix = arr[ arr.length - 1 ];
        int suffixLength = 1;
        while( arr.length - 1 - suffixLength >= 0 && arr[ arr.length - suffixLength ] == arr[ arr.length - 1 - suffixLength ] )
            suffixLength++;

        if( prefix == suffix )
            return Math.max( arr.length - prefixLength - suffixLength, 0 );
        else
            return arr.length - Math.max ( prefixLength, suffixLength );

    }

}