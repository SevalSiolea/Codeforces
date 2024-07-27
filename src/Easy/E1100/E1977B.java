package Easy.E1100;

/**
 * Difficulty : E1100-hard<br>
 * Algorithm : constructive, math<br>
 * Feature : use 4-base to constructive a clever result<br>
 * Solution : solution1<br>
 * Date : 2024.7.27<br>
 */
public class E1977B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[][] results = new int[ testCount ][];
        for( int i=0; i < testCount; i++ )
            results[ i ] = solution( strategy, scanner.nextInt() );

        for( int i=0; i < testCount; i++ ) {
            System.out.println( results[ i ].length );
            for( int digit : results[ i ] )
                System.out.print( digit + " " );
            System.out.println();
        }
    }

    private static int[] solution( Strategy strategy, int num ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( num );
            default:
                return new int[] {};
        }
    }

    /**
     * Description : clever solution, constructive solution<br>
     * Complexity : timeO( logN ), space O( 1 )<br>
     * Complexity : N - binary digit count of num<br>
     * Hint : use 4-base to denote num and sum of array<br>
     * Feature : use 4-base to construct a clever result<br>
     *
     * <p>
     * Idea : consider a pair of neighboring binary digit a[ i ]a[ i+1 ]<br>
     * Idea : denote it as b in num or c in array<br>
     * Idea : in fact, b or c is a 4-base digit<br>
     * Idea : in num, a[ i ]a[ i+1 ] may be 00, 01, 10, 11<br>
     * Idea : so b may be 0, 1, 2, 3<br>
     * Idea : in array, a[ i ]a[ i+1 ] may be -1_0, 0_-1, 00, 01, 10<br>
     * Idea : so c may -2, -1, 0, 1, 2<br>
     *
     * <p>
     * Idea : consider how to denote num by sum of array<br>
     * Idea : firstly set c = b, if b is 0, 1, 2, it is possible<br>
     * Idea : if b is 3, set c = 3 = 4 - 1, thus set c = -1 and generate a carry<br>
     * Idea : if b is 3, and c get a carry, c = 3 + 1 = 4, thus set c = 0 and generate a carry<br>
     * Idea : after iterating over 4-base digits of num, we construct an array<br>
     *
     * <p>
     * Idea : however, this array maybe break rule 4<br>
     * Idea : which happens when c[ i ] is -1, 1 and c[ i+1 ] is 2<br>
     * Idea : to modify result, set c[ i+1 ] = 4 - 2, then c[ i+1 ] = -2 and generate a carry<br>
     * Idea : therefore c[ i ] is 0, 2 and c[ i+1 ] is -2, which does not break rule 4<br>
     *
     * @param num given num
     * @return result
     */
    private static int[] solution1( int num ) {

        // get 4-base digits of num
        int[] numDigits = new int[ 16 ];
        for( int i = 15; i >= 0; i-- ) {
            numDigits[ i ] = num % 4;
            num >>= 2;
        }

        // core code of getting correct 4-base result
        for( int i = 15; i > 0; i-- ) {
            if ( numDigits[ i ] == 2 && ( numDigits[ i - 1 ] == -1 || numDigits[ i - 1 ] == 1 || numDigits[ i - 1 ] == 3 ) ) {
                numDigits[ i ] = -2;
                numDigits[ i - 1 ] += 1;
            }
            if ( numDigits[ i ] == 3 ) {
                numDigits[ i ] = -1;
                numDigits[ i - 1 ] += 1;
            }
            if( numDigits[ i ] == 4 ) {
                numDigits[ i ] = 0;
                numDigits[ i - 1 ] += 1;
            }
        }

        // getting 2-base result by 4-base result
        int[] bitNumCos = new int[ 32 ];
        java.util.Arrays.fill( bitNumCos, 0 );
        for( int i = 0; i < numDigits.length; i++ )
            switch( numDigits[ i ] ) {
                case -2:
                    bitNumCos[ 2 * i ] = -1;
                    bitNumCos[ 2 * i + 1 ] = 0;
                    break;
                case -1:
                    bitNumCos[ 2 * i ] = 0;
                    bitNumCos[ 2 * i + 1 ] = -1;
                    break;
                case 0:
                    bitNumCos[ 2 * i ] = 0;
                    bitNumCos[ 2 * i + 1 ] = 0;
                    break;
                case 1:
                    bitNumCos[ 2 * i ] = 0;
                    bitNumCos[ 2 * i + 1 ] = 1;
                    break;
                case 2:
                    bitNumCos[ 2 * i ] = 1;
                    bitNumCos[ 2 * i + 1 ] = 0;
                    break;
            }

        int length = 0;
        for( int i=0; i < bitNumCos.length; i++)
            if( bitNumCos[ i ] != 0 ) {
                length = 32 - i;
                break;
            }

        int[] result = new int[ length ];
        for( int i = 0; i < length; i++ )
            result[ i ] = bitNumCos[ 31 - i ];

        return result;
    }

}