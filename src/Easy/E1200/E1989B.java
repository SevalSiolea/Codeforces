package Easy.E1200;

/**
 * Difficulty : E1200-medium<br>
 * Date : 2024.8.6<br>
 */
public class E1989B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[] results = new int[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            String subString = scanner.next();
            String subSequence = scanner.next();
            results[ i ] = solution( strategy, subString, subSequence );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static int solution( Strategy strategy, String subString, String subSequence ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( subString, subSequence );
            default:
                return -1;
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( M * N ), space O( 1 )<br>
     * Complexity : M - length of subString, N - length of subSequence<br>
     *
     * @param subString given subString
     * @param subSequence given subSequence
     * @return result
     */
    private static int solution1( String subString, String subSequence ) {

        int maxSubLength = 0;
        for( int i=0; i < subSequence.length(); i++ ) {
            int idxA = 0;
            int idxB = i;
            int subLength = 0;
            while( idxA < subString.length() && idxB < subSequence.length() ) {
                if( subString.charAt( idxA ) == subSequence.charAt( idxB ) ) {
                    idxB++;
                    subLength++;
                }
                idxA++;
            }
            if( maxSubLength < subLength )
                maxSubLength = subLength;
        }

        return subString.length() + subSequence.length() - maxSubLength;

    }

}