import java.util.Arrays;
import java.util.Scanner;

/*
1
1
1  48 -1 -1 33 -1 63 18
30 51 -1  3 -1 -1 -1 -1
-1 -1 -1 -1 15 -1 -1 -1
-1 -1 -1 45 -1 -1 36 -1
-1 -1 25 -1  9 -1 21 60
-1 -1 -1 -1 24 57 12 -1
-1  6 -1 -1 39 -1 -1 -1
54 -1 42 -1 -1 -1 -1 -1
*/

public class Main {

  static int[][] m;
  static int[][] mm;
  static int[] rowSum;
  static int[] colSum;
  static boolean found;
  static boolean[] used;

  static void set( int x, int y, int val ) {
    rowSum[y] += val;
    colSum[x] += val;
    m[y][x] = val;
  }

  static void reset( int x, int y ) {
    rowSum[y] -= m[y][x];
    colSum[x] -= m[y][x];
    m[y][x] = -1;
  }

  static boolean check( int x, int y, int val ) {
    if ( rowSum[y] + val > 260 ) return false;
    if ( colSum[x] + val > 260 ) return false;
    return true;
  }

  static void clear() {
    int i, j;
    for ( i = 0; i < 8; i++ ) {
      rowSum[i] = colSum[i] = 0;
      for ( j = 0; j < 8; j++ ) m[i][j] = -1;
    }
    for ( i = 0; i <= 64; i++ ) used[i] = false;
    found = false;
  }

  public static void main( String[] args ) {

    Scanner sc = new Scanner( System.in );

    int casos = sc.nextInt();
    m = new int[8][8];
    mm = new int[8][8];
    used = new boolean[65];
    rowSum = new int[8];
    colSum = new int[8];

    for ( int i = 0; i < casos; i++ ) {

      clear();

      int dataSet = sc.nextInt();

      // Ler Entradas

      int posX = -1;
      int posY = -1;

      for ( int x = 0; x < 8; x++ )
        for ( int y = 0; y < 8; y++ ) {

          int val = sc.nextInt();

          m[y][x] = val;

          if ( val == 1 ) {
            posX = x;
            posY = y;
          }
          if ( val > 0 ) {
            used[val] = true;
            set( x, y, val );
          }
        }

      // Tentar preencher

      if ( posX >= 0 )
        rec( posX, posY, 1 );
      else
        for ( int k = 0; k < 8; k++ )
          for ( int l = 0; l < 8; l++ )
            if ( m[k][l] == -1 )
              rec( k, l, 1 );

      System.out.println( dataSet );
      for ( int k = 0; k < 8; k++ ) {
        for ( int l = 0; l < 8; l++ )
          System.out.printf( "%3d", mm[l][k] );
        if ( k != 7 ) System.out.println();
      }
      System.out.println();
    }
  }

  static void rec( int x, int y, int val ) {

    if ( found ) return;

    if ( val > 64 ) {
      int k, l;
      for ( k = 0; k < 8; k++ )
        for ( l = 0; l < 8; l++ )
          mm[k][l] = m[k][l];
      found = true;
      return;
    }

    if ( x < 0 || x > 7 ) return;
    if ( y < 0 || y > 7 ) return;

    if ( used[val] ) {

      if ( m[y][x] != val ) return;

//    System.out.println( "Certo: " + x + " " + y + " " + val );

      int xx, yy;
      for ( xx = x - 2; xx <= x + 2; xx += 4 )
        for ( yy = y - 1; yy <= y + 1; yy += 2 )
          rec( xx, yy, val + 1 );

      for ( xx = x - 1; xx <= x + 1; xx += 2 )
        for ( yy = y - 2; yy <= y + 2; yy += 4 )
          rec( xx, yy, val + 1 );

      return;
    }

    if ( m[y][x] != -1 ) return;

//  System.out.println( "Incerto: " + x + " " + y + " " + val );

    set( x, y, val );

    int xx, yy;
    for ( xx = x - 2; xx <= x + 2; xx += 4 )
      for ( yy = y - 1; yy <= y + 1; yy += 2 )
        rec( xx, yy, val + 1 );

    for ( xx = x - 1; xx <= x + 1; xx += 2 )
      for ( yy = y - 2; yy <= y + 2; yy += 4 )
        rec( xx, yy, val + 1 );

    reset( x, y );
  }

}
