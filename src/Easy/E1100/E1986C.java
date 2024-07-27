package Easy.E1100;

/**
 * Difficulty : E1100-medium<br>
 * Date : 2024.7.27<br>
 */
public class E1986C {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        String[] results = new String[ testCount ];
        for( int i=0; i < testCount; i++ ) {
            int strLength = scanner.nextInt();
            int updateCount = scanner.nextInt();
            String str = scanner.next();
            int[] idxArr = new int[ updateCount ];
            for( int j=0; j < updateCount; j++ )
                idxArr[ j ] = scanner.nextInt();
            String anotherStr = scanner.next();
            results[ i ] = solution( strategy, str, idxArr, anotherStr );
        }

        for( int i=0; i < testCount; i++ )
            System.out.println( results[ i ] );
    }

    private static String solution( Strategy strategy, String str, int[] idxArr, String anotherStr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( str, idxArr, anotherStr );
            default:
                return "";
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( M * logM ), space O( M )<br>
     * Complexity : N - length of str, M - times of operation<br>
     *
     * @param str given str
     * @param idxArr array of index of str
     * @param anotherStr given another str
     * @return replaced str
     */
    private static String solution1( String str, int[] idxArr, String anotherStr ) {

        int[] idxCounts = new int[ str.length() ];
        java.util.Arrays.fill( idxCounts, 0 );
        for( int idx : idxArr )
            idxCounts[ idx - 1 ]++;

        String[] strs = str.split( "" );
        String[] anotherStrs = anotherStr.split( "" );
        java.util.Arrays.sort( anotherStrs );

        int anotherIdx = 0;
        for( int i=0; i < idxCounts.length; i++ )
            if( idxCounts[ i ] != 0 )
                strs[ i ] = anotherStrs[ anotherIdx++ ];

        StringBuilder result = new StringBuilder();
        for( String s : strs )
            result.append( s );
        return result.toString();
    }

}
