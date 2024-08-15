package Easy.E1200;

/**
 * Difficulty : E1200-hard<br>
 * Algorithm : greedy, dynamic programming, two pointers<br>
 * Date : 2024.8.15<br>
 *
 * <p>
 * Solution : solution1, solution4<br>
 * Solution1 : greedy algorithm<br>
 * Solution4 : dynamic programming algorithm<br>
 */
public class E1982C {

    private enum Strategy { STRATEGY1, STRATEGY2, STRATEGY3, STRATEGY4 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int min = scanner.nextInt();
            int max = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr, min, max );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, int[] arr, int min, int max ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr, min, max );
            case STRATEGY2:
                return solution2( arr, min, max );
            case STRATEGY3:
                return solution3( arr, min, max );
            case STRATEGY4:
                return solution4( arr, min, max );
            default:
                return -1;
        }
    }

    /**
     * Description : greedy solution<br>
     * Complexity : time O( N ), space O( 1 )<br>
     * Complexity : N - amount of cards<br>
     *
     * <p>
     * Idea : greedy algorithm is easy<br>
     * Idea : use two pointers to find rounds one by one<br>
     * Idea : however, the correctness is difficult to prove<br>
     *
     * <p>
     * Idea : use two pointers start and end to find rounds one by one<br>
     * Idea : set start = end = 0, current sum = arr[ start ]<br>
     * Idea : increase end until sum >= min<br>
     * Idea : if sum <= max, we find the round<br>
     * Idea : else, increase start until sum <= max<br>
     * Idea : if sum < min then, we must increase end again<br>
     * Idea : increase end and start until we find the round<br>
     * Idea : after we find the round, set start = end = end + 1,<br>
     * Idea : continuously find the next round until end of the array<br>
     *
     * <p>
     * Idea : then prove the correctness of the algorithm<br>
     * Idea : consider a certain round<br>
     * Idea : we cant increase rounds by moving the round forwards<br>
     * Idea : because we cant use idx such that idx < end as end of the round<br>
     * Idea : any idx does not satisfy the requirements because sum is too big or too small<br>
     * Idea : sum is too big when increasing end to make sum >= min firstly<br>
     * Idea : at the time, sum > max<br>
     * Idea : sum is too small when increasing start to make sum < max firstly<br>
     * Idea : at the time, sum < min<br>
     * Idea : therefore, we cant find a start idx to make the certain end idx satisfy the requirements<br>
     * Idea : thus there does not exist another round before the round<br>
     * Idea : we cant increase rounds by moving the round backwards neither<br>
     * Idea : because there does not exist any round between the round and the next round<br>
     *
     * @param arr given array
     * @param min min of sum
     * @param max max of sum
     * @return result
     */
    private static int solution1( int[] arr, int min, int max ) {

        int round = 0;

        int start = 0;
        int end = 0;
        while( start < arr.length ) {
            int sum = arr[ start ];
            while( true ) {
                if( sum < min ) {
                    if( end < arr.length - 1 ) {
                        end++;
                        sum += arr[ end ];
                    } else
                        return round;
                } else if( sum > max ) {
                    start++;
                    sum -= arr[ start - 1 ];
                } else {
                    end++;
                    start = end;
                    round++;
                    break;
                }
            }
        }

        return round;

    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N^2 ), space O( N )<br>
     * Complexity : N - amount of cards<br>
     * Feature : brute force algorithm, spend too much time<br>
     *
     * <p>
     * Idea : iterate over the given array to find end of round for every idx<br>
     * Idea : if there does not exist a round, set ends[ idx ] = -1<br>
     * Idea : finally, use dynamic programming to iterate over rounds reversely<br>
     *
     * @param arr given array
     * @param min min of sum
     * @param max max of sum
     * @return result
     */
    private static int solution2( int[] arr, int min, int max ) {

        int[] ends = new int[ arr.length ];
        java.util.Arrays.fill( ends, -1 );
        for( int i=0; i < arr.length; i++ ) {
            int sum = arr[ i ];
            int end = i;
            while( sum < min && end + 1 < arr.length )
                sum += arr[ ++end ];
            if( sum >= min && sum <= max )
                ends[ i ] = end;
        }

        int[] rounds = new int[ arr.length + 1 ];
        rounds[ rounds.length - 1 ] = 0;
        for( int i = rounds.length - 2; i >= 0; i-- )
            rounds[ i ] = ( ends[ i ] == -1 ) ? rounds[ i+1 ] : ( rounds[ ends[ i ] + 1 ] + 1 );

        return rounds[ 0 ];
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( N^2 ), space O( N )<br>
     * Complexity : N - amount of cards<br>
     * Feature : brute force algorithm, spend too much time<br>
     *
     * <p>
     * Idea : solution3 is similar to solution2<br>
     * Idea : but we iterate over rounds from front to back<br>
     * Idea : steps is a little different, but essence is the same<br>
     *
     * @param arr given array
     * @param min min of sum
     * @param max max of sum
     * @return result
     */
    private static int solution3( int[] arr, int min, int max ) {

        int[] rounds = new int[ arr.length + 1 ];
        rounds[ 0 ] = 0;

        for( int i=0; i < arr.length; i++ ) {
            int sum = arr[ i ];
            int end = i;
            while( sum < min && end + 1 < arr.length )
                sum += arr[ ++end ];
            if( sum >= min && sum <= max )
                rounds[ end + 1 ] = Math.max( rounds[ i ] + 1, rounds[ end + 1 ] );
            rounds[ i+1 ] = Math.max( rounds[ i ], rounds[ i+1 ] );
        }

        return rounds[ rounds.length - 1 ];
    }

    /**
     * Description : dynamic programming solution<br>
     * Complexity : time O( N * logN ), space O( N )<br>
     * Complexity : N - amount of cards<br>
     * Feature : dynamic programming and two pointers<br>
     *
     * <p>
     * Idea : solution4 optimize solution2 and solution3<br>
     * Idea : use dynamic programming to iterate over rounds<br>
     * Idea : however, use two pointers to iterate over the given array<br>
     * Idea : do less repeated work to decrease time complexity<br>
     *
     * <p>
     * Idea : essence of solution4 is different<br>
     * Idea : find answer of subquestion when iterating is essence of dynamic programming<br>
     * Idea : solution2 and solution3 is just simple iterating<br>
     *
     * @param arr given array
     * @param min min of sum
     * @param max max of sum
     * @return result
     */
    private static int solution4( int[] arr, int min, int max ) {

        int[] rounds = new int[ arr.length + 1 ];
        rounds[ 0 ] = 0;

        int sum = 0;
        int end = -1;
        for( int i=0; i < arr.length; i++ ) {
            rounds[ i+1 ] = Math.max( rounds[ i ], rounds[ i+1 ] );
            if( end < i ) {
                end = i;
                sum = 0;
            }
            while( end < arr.length && sum < min )
                sum += arr[ end++ ];
            if( sum >= min && sum <= max )
                rounds[ end ] = Math.max( rounds[ end], rounds[ i ] + 1 );
            sum -= arr[ i ];
        }

        return rounds[ rounds.length - 1 ];
    }

}