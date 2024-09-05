package Medium.M1300;

/**
 * Difficulty : M1300-medium<br>
 * Feature : analysis is difficult<br>
 * Date : 2024.9.5<br>
 */
public class M1991C {

    private enum Strategy { STRATEGY1, STRATEGY2 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY2;

        int testCount = scanner.nextInt();
        int[][] results = new int[ testCount ][];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr );
        }

        for( int i=0; i < testCount; i++ )
            if( results[ i ] != null ) {
                System.out.println( results[ i ].length );
                for( int num : results[ i ] )
                    System.out.print( num + " " );
                System.out.println();
            } else
                System.out.println( -1 );
    }

    private static int[] solution( Strategy strategy, int[] arr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr );
            case STRATEGY2:
                return solution2( arr );
            default:
                return new int[]{};
        }
    }

    /**
     * Description : clever solution, constructive solution<br>
     * Complexity : time O( N * logM ), space O( logM )<br>
     * Complexity : N - length of arr, M - max of arr<br>
     * Feature : analysis is difficult<br>
     *
     * @param arr given array
     * @return result
     */
    private static int[] solution1( int[] arr ) {
        for( int num : arr )
            if( ( num + arr[ 0 ] ) % 2 != 0 )
                return null;
        java.util.List<Integer> resultList = new java.util.ArrayList<>();

        while( true ) {
            int max = 0;
            int min = Integer.MAX_VALUE;
            for( int num : arr ) {
                if ( num > max ) max = num;
                if ( num < min ) min = num;
            }

            if( max == min && min == 0 ) break;
            if( max == 2 && min == 0 ) {
                resultList.add( 1 );
                resultList.add( 1 );
                break;
            }

            int mid = ( max + min ) / 2;
            if( ( mid + arr[ 0 ] ) % 2 != 0 ) mid++;
            resultList.add( mid );

            for( int i=0; i < arr.length; i++ )
                arr[ i ] = Math.abs( arr[ i ] - mid );

        }

        int[] result = new int[ resultList.size() ];
        for( int i=0; i < resultList.size(); i++ )
            result[ i ] = resultList.get( i );
        return result;
    }

    /**
     * Description : clever solution, constructive solution<br>
     * Complexity : time O( N + logM ), space O( logM )<br>
     * Complexity : N - length of arr, M - max of arr<br>
     * Feature : analysis is difficult<br>
     *
     * @param arr given array
     * @return result
     */
    private static int[] solution2( int[] arr ) {
        for( int num : arr )
            if( ( num + arr[ 0 ] ) % 2 != 0 )
                return null;

        java.util.List<Integer> resultList = new java.util.ArrayList<>();
        for( int mid = 1 << 29; mid >= 1; mid >>= 1 ) {
            resultList.add( mid );
            arr[ 0 ] = Math.abs( arr[ 0 ] - mid );
        }
        if( arr[ 0 ] == 1 ) resultList.add( 1 );

        int[] result = new int[ resultList.size() ];
        for( int i=0; i < resultList.size(); i++ )
            result[ i ] = resultList.get( i );
        return result;
    }

}