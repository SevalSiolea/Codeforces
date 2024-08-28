package Easy.E1200;

/**
 * Difficulty : E1200-easy<br>
 * Date : 2024.8.28<br>
 */
public class E1933D {

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
        int min = Integer.MAX_VALUE;
        boolean onlyMin = true;
        for( int num : arr )
            if( num < min ) {
                min = num;
                onlyMin = true;
            } else if( num == min )
                onlyMin = false;

        if( onlyMin ) return true;
        for( int num : arr )
            if( num % min != 0 )
                return true;
        return false;
    }

}