package Easy.E1200;

/**
 * Difficulty : E1200-hard<br>
 * Feature : the algorithm is very difficult to analyse, and is clever and subtle<br>
 * Solution : solution1<br>
 * Date : 2024.8.21<br>
 */
public class E1975C {

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
     * Description : clever solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - length of arr<br>
     *
     * <p>
     * Hint1 : if a subarray of length at least 2 contains the same elements<br>
     * Hint1 : we can change all elements of the array to this element<br>
     * Hint2 : suppose result is x, we can get a subarray of the same elements x by one operation<br>
     * Hint3 : in case of hint2, we can find a subarray of length 3 with medium y ( y >= x )<br>
     * Hint3 : thus we can use a better operation to replace the operation in hint2<br>
     *
     * <p>
     * Idea : just iterate over all subarray of length 3<br>
     * Idea : the max medium is the result<br>
     *
     * <p>
     * Idea : hint1 and hint2 is obvious, then we prove hint3<br>
     * Idea : in case of hint2, suppose the length of the subarray is n<br>
     * Idea : there exists ( n / 2 + 1 ) elements ys such that y >= x<br>
     * Idea : assume there does not exist a subarray in hints<br>
     * Idea : internal between any pair of ys is at least 2<br>
     * Idea : therefore, the least length of subarray in hint2 is ( n / 2 + 1 ) * 3 - 2<br>
     * Idea : we get a contradiction, so assumption is wrong<br>
     *
     * @param arr given array
     * @return result
     */
    private static int solution1( int[] arr ) {
        int result = Math.min( arr[ 0 ], arr[ 1 ] );
        for( int i=0; i < arr.length - 2; i++ )
            result = Math.max( result, medium( arr[ i ], arr[ i+1 ], arr[ i+2 ] ) );
        return result;
    }

    private static int medium( int num1, int num2, int num3 ) {
        if( num1 > num2 ) { int temp = num1; num1 = num2; num2 = temp; }
        if( num1 > num3 ) { int temp = num1; num1 = num3; num3 = temp; }
        if( num2 > num3 ) { int temp = num2; num2 = num3; num3 = temp; }
        return num2;
    }

}
