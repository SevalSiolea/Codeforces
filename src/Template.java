/**
 * Template of codeforces problems.
 *
 * <p>
 * Difficulty : package difficulty + codeforces difficulty + actual difficulty<br>
 * package difficulty : Easy(E), Medium(M), Hard(H), Expert(EX), Master(MA)<br>
 * codeforces difficulty : E1000, E1100, E1200, M1300, M1400, M1500, H1600, H1700, H1800<br>
 * codeforces difficulty : EX1900, EX2000, MA2100, MA2200<br>
 * actual difficulty : easy, medium, hard compared to average codeforces difficulty<br>
 * actual difficulty : for example, E1300-hard is harder than average difficulty of E1300<br>
 *
 * <p>
 * Data structure tag : data structure of this problem<br>
 * data structure : list, arrayList, linkedList, stack, queue, deque<br>
 * data structure : tree, binary tree, binary search tree<br>
 * data structure : AVLTree, splay tree, red-black tree, segment tree<br>
 * data structure : heap, binary heap, leftist heap, skew heap, binomial queue<br>
 * data structure : collection, hashtable, string, array, matrix, union-find sets<br>
 *
 * <p>
 * Algorithm tag : algorithm of this problem<br>
 * algorithm : recursion, divide and conquer, greedy, backtracking<br>
 * algorithm : dynamic programming, memoization search<br>
 * algorithm : sort, bucket sort, radix sort<br>
 * algorithm : graph, BFS, DFS, shortest path, flow network, spanning tree<br>
 * algorithm : simulate, enumerate, brute force, math, constructive<br>
 * algorithm : two pointers, sliding window, randomized, bitwise operation<br>
 * algorithm : binary search, quick select, KMP<br>
 *
 * <p>
 * Feature : describe features which make this problem valuable<br>
 *
 * <p>
 * Solution : solution list of solutions which is valuable<br>
 * solution : every solution and its description follows<br>
 *
 * <p>
 * Date : date of this problem<br>
 */
public class Template {

    private enum Strategy { STRATEGY1, STRATEGY2, STRATEGY3 }

    public static void main( String[] args ) {

        java.util.Scanner scanner = new java.util.Scanner( System.in );
        Strategy strategy = Strategy.STRATEGY1;

        int testCount = scanner.nextInt();
        long[] results = new long[ testCount ];
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

    private static long solution( Strategy strategy, int[] arr ) {
        switch( strategy ) {
            case STRATEGY1:
                return solution1( arr );
            case STRATEGY2:
                return solution2( arr );
            case STRATEGY3:
                return solution3( arr );
            default:
                return -1L;
        }
    }

    /**
     * Description : brief description of this solution<br>
     * Complexity : time complexity and space complexity without arr<br>
     * Feature : valuable highlights of this solution<br>
     * Hint : key point of this solution<br>
     * Idea : thought of how to analyse and solve this problem<br>
     * Steps : detailed steps of this solution<br>
     *
     * @param arr param list
     * @return result
     */
    private static long solution1( int[] arr ) { return 0L; }

    private static long solution2( int[] arr ) { return 0L; }

    private static long solution3( int[] arr ) { return 0L; }

}