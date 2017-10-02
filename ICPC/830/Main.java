import java.util.Scanner;

public class Main {

    /*

2

16 12
b...c.fgh...
baabca.jkyyy
...bcak..yyy
....ca...yyy
zzzzzz...uuu
zzzzzz.s.uuu
zzzzzzsssuuu
.tttt.sssuuu
ttttt.sssuuu
.tttt.sss.u.
.........hh.
bbbb..fffhh.
bbbb..fffhh.
bbbbgggg.hh.
bbbbgggg.ppp
bbbbgggg.ppp

16 12
b...c.fgh...
baabca.jkyyy
...bcak..yyy
....ca...yyy
zzzzzz...uuu
zzzzzz.s.uuu
zzzzzzsssuuu
.tttt.sssuuu
ttttt.sssuuu
.tttt.sss.u.
.........hh.
bbbb..fffhh.
bbbb..fffhh.
bbbbgggg.hh.
bbbbgggg.ppp
bbbbgggg.ppp


    */

    static int sardines; // 1x1
    static int mackerels; // 1x2
    static int salmons; // 1x(x > 2)
    static int turtles; // Squares > 1
    static int groupers; // 2x>2
    static int dolphins; // 3x>3
    static int whales; // 4x>4
    static int sharks;

    static void reset() {

        sardines = 0;
        mackerels = 0;
        salmons = 0;
        turtles = 0;
        groupers = 0;
        dolphins = 0;
        whales = 0;
        sharks = 0;

    }

    private static class Values{
        int maxX = -1, maxY = -1, minX = -1, minY = -1, count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();

        for(int c=0;c<cases;c++) {

            if (c > 0) System.out.println();

            sc.nextLine();
            int lines = sc.nextInt();
            int cols = sc.nextInt();

            reset();

            char[][] matrix = new char[lines][cols];

            for(int i=0;i<lines;i++){
                String s = sc.next();
                for(int j=0;j<cols;j++){
                    matrix[i][j] = s.charAt(j);
                }
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {

                    char current = matrix[i][j];
                    if (current == '.') continue; 

                    Values v = rec(i, j, current, matrix, new Values());
                    CheckShape(v);

                }
            }

            printOutput();

        }
    }

    public static Values rec(int x, int y, char current, char[][] m, Values values){
        if (x < 0 || x >= m.length || y < 0 || y >= m[0].length) return values;
        if (m[x][y] != current) return values;
        m[x][y] = '.';

        rec(x, y+1, current, m, values); // UP
        rec(x, y-1, current, m, values); // DOWN
        rec(x-1, y, current, m, values); // LEFT
        rec(x+1, y, current, m, values); // RIGHT

        if (x > values.maxX || values.maxX == -1) values.maxX = x;
        if (x < values.minX || values.minX == -1) values.minX = x;
        if (y > values.maxY || values.maxY == -1) values.maxY = y;
        if (y < values.minY || values.minY == -1) values.minY = y;

        values.count++;
        return values;

    }

    public static void CheckShape(Values v) {

        int width = v.maxX - v.minX + 1;
        int height = v.maxY - v.minY + 1;

        int area = width * height;

        if (area != v.count) {

            // Irregular shape

            sharks++;

        }
        else {

            if (width > height) {

                int aux = width;
                width = height;
                height = aux;

            }

            if (width == 1 && height == 1) sardines++;
            else if (width == 1 && height == 2) mackerels++;
            else if (width == 1 && height > 2) salmons++;
            else if (width > 2 && height > 2 && width == height) turtles++;
            else if (width == 2 && height > 2) groupers++;
            else if (width == 3 && height > 3) dolphins++;
            else if (width == 4 && height > 4) whales++;

        }

    }

    static void printOutput() {

        System.out.print(sardines + " ");
        System.out.print(mackerels + " ");
        System.out.print(salmons + " ");
        System.out.print(groupers + " ");
        System.out.print(turtles + " ");
        System.out.print(dolphins + " ");
        System.out.print(whales + " ");
        System.out.print(sharks);

        System.out.println();

    }

}