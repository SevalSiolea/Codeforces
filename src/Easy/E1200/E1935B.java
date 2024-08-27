package Easy.E1200;

import java.util.HashSet;

/**
 * Difficulty : E1200-hard<br>
 * Data structure : hashSet<br>
 * Algorithm : constructive<br>
 * Date : 2024.8.27<br>
 */
public class E1935B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        Interval[][] results = new Interval[ testCount ][];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[ arrLength ];
            for( int j=0; j < arrLength; j++ )
                arr[ j ] = scanner.nextInt();
            results[ i ] = solution( strategy, arr );
        }

        for( int i=0; i < testCount; i++ ) {
            if( results[ i ] == null ) { System.out.println( -1 ); continue; }
            System.out.println( results[ i ].length );
            for( Interval interval : results[ i ] )
                System.out.println( interval.left + " " + interval.right );
        }
    }

    private static Interval[] solution(Strategy strategy, int[] arr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr );
            default:
                return null;
        }
    }

    /**
     * Description : clever solution, constructive solution<br>
     * Complexity : time O( N ), space O( N )<br>
     * Complexity : N - length of arr<br>
     *
     * <p>
     * Hint1 : mex of arr is mex of result<br>
     * Hint2 : k segments can be merged into 2 segments<br>
     * Hint3 : just examine whether 2 segments has all nums from 0 to mex - 1<br>
     *
     * <p>
     * Idea : if 0 exists, then 0 will exist in certain segment<br>
     * Idea : mex of the segment > 0, so mex of all segments > 0<br>
     * Idea : after that, if 1 exists, mex of result > 0 by the same inference<br>
     * Idea : continue the inference until mex of result >= mex of arr<br>
     * Idea : obviously if result exists, mex of result == mex of arr<br>
     *
     * <p>
     * Idea : assume we get k segments as result, we can merge any 2 segments<br>
     * Idea : because all nums from 0 to mex - 1 exist, and mex does not exist<br>
     * Idea : continue the merge util result has 2 segments<br>
     *
     * <p>
     * Idea : finally we get the following algorithm<br>
     * Idea : iterate over arr until the first segment has all nums from 0 to mex - 1<br>
     * Idea : another part as the last segment and examine whether it has all nums from 0 to mex - 1<br>
     * Idea : if not, return null, else return first and last segment as result<br>
     *
     * @param arr given array
     * @return result
     */
    private static Interval[] solution1( int[] arr ) {
        HashSet<Integer> nums = new HashSet<>();
        for( int num : arr ) nums.add( num );
        int mex = 0; while( nums.contains( mex ) ) mex++;
        if( mex == 0 ) return new Interval[]{ new Interval( 1, 1 ), new Interval( 2, arr.length ) };

        int idx = -1;
        nums = new HashSet<>();
        while( nums.size() < mex && ++idx < arr.length ) // cant change the order
            if( arr[ idx ] < mex )
                nums.add( arr[ idx ] );
        if( nums.size() < mex ) return null;
        Interval first = new Interval( 1, idx + 1 );

        nums = new HashSet<>();
        while( ++idx < arr.length )
            if( arr[ idx ] < mex )
                nums.add( arr[ idx ] );
        if( nums.size() < mex ) return null;
        Interval last = new Interval( first.right + 1, arr.length );

        return new Interval[]{ first, last };
    }

    private static class Interval {
        public int left;
        public int right;
        public Interval(int left, int right ) { this.left = left; this.right = right; }
    }

}