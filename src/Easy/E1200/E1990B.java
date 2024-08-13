package Easy.E1200;

/**
 * Difficulty : E1200-medium<br>
 * Algorithm : constructive<br>
 * Date : 2024.8.13<br>
 */
public class E1990B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        boolean[][] results = new boolean[ testCount ][];
        for( int i=0; i < testCount; i++ ) {
            int arrLength = scanner.nextInt();
            int preLoc = scanner.nextInt();
            int sufLoc = scanner.nextInt();
            results[ i ] = solution( strategy, arrLength, preLoc, sufLoc );
        }

        for( int i=0; i < testCount; i++ ) {
            for( boolean result : results[ i ] )
                System.out.print( ( result ? "1" : "-1" ) + " " );
            System.out.println();
        }
    }

    private static boolean[] solution( Strategy strategy, int arrLength, int preLoc, int sufLoc ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arrLength, preLoc, sufLoc );
            default:
                return new boolean[] {};
        }
    }

    /**
     * Description : constructive solution<br>
     * Complexity : time O( N ), space O( N )<br>
     * Complexity : N - length of arr<br>
     *
     * @param arrLength length of result array
     * @param preLoc location of prefix
     * @param sufLoc location of suffix
     * @return result array
     */
    private static boolean[] solution1( int arrLength, int preLoc, int sufLoc ) {

        boolean[] result = new boolean[ arrLength ];
        preLoc--;
        sufLoc--;

        for( int i = sufLoc; i <= preLoc; i++ )
            result[ i ] = true;
        for( int i = sufLoc - 1; i >= 0; i-- )
            result[ i ] = !result[ i+1 ];
        for( int i = preLoc + 1; i < arrLength; i++ )
            result[ i ] = !result[ i-1 ];

        return result;
    }

}
