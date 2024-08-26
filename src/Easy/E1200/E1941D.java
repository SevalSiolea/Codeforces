package Easy.E1200;

import java.util.HashSet;

/**
 * Difficulty : E1200-medium<br>
 * Data structure : hashSet<br>
 * Date : 2024.8.26<br>
 */
public class E1941D {

    private enum Strategy { STRATEGY1 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        int[][] results = new int[ testCount ][];
        for( int i=0; i < testCount; i++ ) {
            int playerAmount = scanner.nextInt();
            int moveAmount = scanner.nextInt();
            int originalPlayer = scanner.nextInt();
            Move[] moves = new Move[ moveAmount ];
            for( int j=0; j < moveAmount; j++ ) {
                int distance = scanner.nextInt();
                String directionStr = scanner.next();
                int direction = directionStr.equals( "?" ) ? 0 : ( directionStr.equals( "0" ) ? 1 : -1 );
                moves[ j ] = new Move( distance, direction );
            }
            results[ i ] = solution( strategy, playerAmount, moves, originalPlayer );
        }

        for( int i=0; i < testCount; i++ ) {
            System.out.println( results[ i ].length );
            for( int player : results[ i ] )
                System.out.print( player + " " );
            System.out.println();
        }
    }

    private static int[] solution( Strategy strategy, int playerAmount, Move[] moves, int originalPlayer ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( playerAmount, moves, originalPlayer );
            default:
                return new int[]{};
        }
    }

    /**
     * Description : simple solution<br>
     * Complexity : time O( M * N ), space O( M )<br>
     * Complexity : M - times of moves, N - total amount of players<br>
     * Feature : use hashSet<br>
     *
     * @param playerAmount total amount of players
     * @param moves all moves
     * @param originalPlayer original player
     * @return result
     */
    private static int[] solution1( int playerAmount, Move[] moves, int originalPlayer ) {

        for( int i=0; i < moves.length; i++ )
            if( moves[ i ].direction != 0 ) {
                originalPlayer = moves[ i ].move( playerAmount, originalPlayer )[ 0 ];
                moves[ i ] = null;
            }

        HashSet<Integer> finalPlayers = new HashSet<>();
        int idx = -1;
        while( ++idx < moves.length )
            if( moves[ idx ] != null ) {
                int[] players = moves[ idx ].move( playerAmount, originalPlayer );
                finalPlayers.add( players[ 0 ] );
                finalPlayers.add( players[ 1 ] );
                break;
            }
        if( idx == moves.length ) return new int[]{ originalPlayer };

        while( ++idx < moves.length )
            if( moves[ idx ] != null ) {
                HashSet<Integer> midPlayers = new HashSet<>();
                for( int player : finalPlayers ) {
                    int[] players = moves[ idx ].move( playerAmount, player );
                    midPlayers.add( players[ 0 ] );
                    midPlayers.add( players[ 1 ] );
                }
                finalPlayers = midPlayers;
            }

        int[] result = finalPlayers.stream().mapToInt( i -> i ).toArray();
        java.util.Arrays.sort( result );
        return result;
    }

    private static class Move{
        public int distance;
        public int direction;

        public Move( int distance, int direction ) { this.distance = distance; this.direction = direction; }

        public int[] move( int playerAmount, int player ) {
            if( direction != 0 ) {
                player += direction > 0 ? distance : -distance;
                if( player > playerAmount ) player -= playerAmount;
                if( player <= 0 ) player += playerAmount;
                return new int[]{ player };
            } else {
                int player1 = player + distance;
                if( player1 > playerAmount ) player1 -= playerAmount;
                int player2 = player - distance;
                if( player2 <= 0 ) player2 += playerAmount;
                return new int[]{ player1, player2 };
            }
        }
    }

}