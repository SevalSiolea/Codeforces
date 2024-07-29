package Easy.E1100;

/**
 * Difficulty : E1100-hard<br>
 * Algorithm : max sum of substring, quick pow<br>
 * Solution : solution1<br>
 * Date : 2024.7.29<br>
 *
 * <p>
 * Feature : analysis of this problem is easy<br>
 * Feature : deal with big numbers by mod 1_000_000_007L<br>
 * Feature : use 2 classic algorithms<br>
 */
public class E1946B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int operateCount = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr, operateCount );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, int[] arr, int operateCount ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr, operateCount );
            default:
                return -1;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N + logM ), space O( N + logM )<br>
     * Complexity : N - length of arr, M - count of operations<br>
     * Feature : use 2 classic algorithms, max sum of substring and quick pow<br>
     * Feature : deal with big numbers by mod 1_000_000_007L<br>
     *
     * @param arr given array
     * @param operateCount count of operations
     * @return result
     */
    private static int solution1( int[] arr, int operateCount ) {

        long maxSum = 0L;
        long currentSum = 0L;
        for( int num : arr ) {
            currentSum += num;

            if( currentSum > maxSum )
                maxSum = currentSum;
            if( currentSum < 0 )
                currentSum = 0;
        }

        long arrSum = 0L;
        for( int num : arr )
            arrSum += num;

        long mod = 1_000_000_007L;
        long result = ( ( ( ( powerMod( operateCount ) % mod - 1 ) * ( maxSum % mod ) ) % mod + arrSum % mod ) % mod );
        return (int) ( ( result < 0 ) ? result + mod : result );

    }

    private static long powerMod( int pow ) {
        long mod = 1_000_000_007L;

        if( pow == 0 ) return 1;
        if( pow == 1 ) return 2;

        long tmp = powerMod( pow / 2 ) % mod;
        if( pow % 2 == 0 )
            return tmp * tmp;
        else
            return tmp * tmp * 2;
    }

}