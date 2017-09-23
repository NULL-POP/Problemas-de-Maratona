import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        while(!s.equals("END")){
            String moveStr = sc.next();
            char[] pos = s.toCharArray();
            char[] moves = moveStr.toCharArray();

            for(int i=0;i<moves.length;i++){
                char m = moves[i];
                switch(m){
                    case 'U':
                        up(pos, pos.length-1);
                        break;
                    case 'D':
                        down(pos, pos.length-1);
                        break;
                    case 'L':
                        left(pos, pos.length-1);
                        break;
                    case 'R':
                        right(pos, pos.length-1);
                        break;
                }

                //System.out.println(Arrays.toString(pos));

                if(pos[0] == '0'){
                    System.out.println("OUT");
                    break;
                }
            }

            if(pos[0] != '0'){
                for(int i=0;i<pos.length;i++){
                    System.out.print(pos[i]);
                }
                System.out.println();
            }

            s = sc.next();
        }
    }

    public static void up(char[] pos, int index){
        if(index < 0) {
            pos[0] = '0'; 
            return;
        }

        char cur = pos[index];
        if(cur == '1'){
            pos[index] = '4';
            up(pos, index-1);
        }else if(cur == '4'){
            pos[index] = '1';
        }else if(cur == '2'){
            pos[index] = '3';
            up(pos, index-1);            
        }else{
            pos[index] = '2';
        }
    }

    public static void down(char[] pos, int index){
        if(index < 0){
            pos[0] = '0'; 
            return;
        }

        char cur = pos[index];
        if(cur == '1'){
            pos[index] = '4';
        }else if(cur == '4'){
            pos[index] = '1';
            down(pos, index-1);
        }else if(cur == '2'){
            pos[index] = '3';
        }else{
            pos[index] = '2';
            down(pos, index-1);
        }

    }

    public static void left(char[] pos, int index){
        if(index < 0){
            pos[0] = '0'; return;
        }

        char cur = pos[index];
        if(cur == '1'){
            pos[index] = '2';
            left(pos, index-1);
        }else if(cur == '4'){
            pos[index] = '3';
            left(pos, index-1);
        }else if(cur == '2'){
            pos[index] = '1';
        }else{
            pos[index] = '4';
        }
    }

    public static void right(char[] pos, int index){
        if(index < 0){
            pos[0] = '0'; return;
        }

        char cur = pos[index];
        if(cur == '1'){
            pos[index] = '2';
        }else if(cur == '4'){
            pos[index] = '3';
        }else if(cur == '2'){
            pos[index] = '1';
            right(pos, index-1);            
        }else{
            pos[index] = '4';
            right(pos, index-1);
        }
    }
}