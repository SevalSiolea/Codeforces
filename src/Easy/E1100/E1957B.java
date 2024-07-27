package Easy.E1100;

/**
 * Difficulty : E1100-hard<br>
 * Algorithm : constructive, bitwise operation<br>
 * Solution : solution1<br>
 * Feature : a clever constructive algorithm<br>
 * Date : 2024.7.27<br>
 */
public class E1957B {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[][] results = new int[ testCount ][];
        for( int i=0; i < testCount; i++ ) {
            int length = scanner.nextInt();
            int sum = scanner.nextInt();
            results[ i ] = solution( strategy, length, sum );
        }

        for( int i=0; i < testCount; i++ ) {
            for ( int num : results[ i ] )
                System.out.print( num + " " );
            System.out.println();
        }
    }

    private static int[] solution( Strategy strategy, int length, int sum ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( length, sum );
            default:
                return new int[] {};
        }
    }

    /**
     * Description : clever solution, constructive solution<br>
     * Complexity : time O( N ), space O( N )<br>
     * Complexity : N - length of result<br>
     *
     * <p>
     * Idea : if length == 1, then result is sum<br>
     * Idea : else, consider construct 2 num, they have most 1 and other elements are all 0<br>
     * Idea : we can construct by iterate over binary form of sum from back to front<br>
     *
     * <p>
     * Idea : in the first situation, we never get a carry<br>
     * Idea : if sum[ i ] is 1, set num1[ i ] = 0 and num2[ i ] = 1<br>
     * Idea : if sum[ i ] is 0, set num1[ i ] = num2[ i ] = 1<br>
     * Idea : then generate a carry and go to the second situation<br>
     *
     * <p>
     * Idea : in the second situation, we always get a carry<br>
     * Idea : if sum[ i ] is 1, set num1[ i ] = 1 and num2[ i ] = 1<br>
     * Idea : if sum[ i ] is 0, set num1[ i ] = 0 and num2[ i ] = 1<br>
     * Idea : note if sum[ i ] is 1 and it is the highest digit<br>
     * Idea : we need set num1[ i ] = num1[ i ] = 0<br>
     *
     * <p>
     * Idea : if binary form of sum has n digits<br>
     * Idea : num2 has n-1 of 1 unless all digits of sum are 1<br>
     * Idea : it can be proved the perfect result<br>
     *
     * @param length length of result
     * @param sum given sum
     * @return result
     */
    private static int[] solution1( int length, int sum ) {

        if( length == 1 )
            return new int[] { sum };

        java.util.ArrayList<Integer> sumArrayList = new java.util.ArrayList<>();
        while( sum != 0 ) {
            sumArrayList.add( 0, sum % 2 );
            sum /= 2;
        }
        int[] sumBits = new int[ sumArrayList.size() ];
        for( int i=0; i < sumArrayList.size(); i++ )
            sumBits[ i ] = sumArrayList.get( i );


        int[] numBits1 = new int[ sumBits.length ];
        int[] numBits2 = new int[ sumBits.length ];
        boolean meetZero = false;
        for( int i = sumBits.length - 1; i >= 0; i-- )
            if( !meetZero ) {
                if ( sumBits[ i ] == 1 ) {
                    numBits1[ i ] = 0;
                    numBits2[ i ] = 1;
                } else {
                    numBits1[ i ] = 1;
                    numBits2[ i ] = 1;
                    meetZero = true;
                }
            } else {
                if( sumBits[ i ] == 0 ) {
                    numBits1[ i ] = 0;
                    numBits2[ i ] = 1;
                } else {
                    if( i == 0 ) {
                        numBits1[ i ] = 0;
                        numBits2[ i ] = 0;
                    } else {
                        numBits1[ i ] = 1;
                        numBits2[ i ] = 1;
                    }
                }
            }

        int[] result = new int[ length ];
        java.util.Arrays.fill( result, 0 );
        result[ 0 ] = countNum( numBits1 );
        result[ 1 ] = countNum( numBits2 );
        return result;
    }

    private static int countNum( int[] bitArr ) {
        int num = 0;
        for ( int bit : bitArr ) {
            num += bit;
            num <<= 1;
        }
        num >>= 1;
        return num;
    }

}