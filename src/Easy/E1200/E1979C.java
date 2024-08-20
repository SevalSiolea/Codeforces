package Easy.E1200;

/**
 * Difficulty : E1200-medium<br>
 * Algorithm : math<br>
 * Date : 2024.8.20<br>
 */
public class E1979C {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[][] results = new int[ testCount ][];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr );
        }

        for( int i=0; i < testCount; i++ ) {
            for( int num : results[ i ] )
                System.out.print( num + " " );
            System.out.println();
        }

    }

    private static int[] solution( Strategy strategy, int[] arr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr );
            default:
                return new int[] {};
        }
    }

    /**
     * Description : math solution, constructive solution<br>
     * Complexity : time O( N ), space O( N )<br>
     * Complexity : N - length of arr<br>
     *
     * @param arr given array
     * @return result
     */
    private static int[] solution1( int[] arr ) {

        final long LCM20 = 232792560L;
        int[] result = new int[ arr.length ];

        long sum = 0L;
        for( int i=0; i < arr.length; i++ ) {
            result[ i ] = (int) ( LCM20 / arr[ i ] );
            sum += result[ i ];
        }

        if( sum < LCM20 )
            return result;
        else
            return new int[] { -1 };
    }

}