import java.util.*;
import java.util.concurrent.*;
public class Main {
public static void main(String[] args){
  Scanner s = new Scanner(System.in);

  while(s.hasNext()){
    int count = s.nextInt();
    LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
      Stack<Integer> stack = new Stack<>();
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      boolean queueBoolean = true;
      boolean stackBoolean = true;
      boolean pqBoolean = true;
    for(int i=0;i<count;i++){
      int op = s.nextInt();
      int val = s.nextInt();

      if(queueBoolean){
        try{
          if(op == 1){
            queue.put(val);
          }else if(queue.poll() != val){
            throw new Exception();
          }
        }catch(Exception e){
          queueBoolean = false;
        }
      }

      if(stackBoolean){
        try{
          if(op == 1){
            stack.push(val);
          }else if(stack.pop() != val){
            throw new Exception();
          }
        }catch(Exception e){
          stackBoolean = false;
        }
      }

      if(pqBoolean){
        try{
          if(op == 1){
            pq.offer(val * -1);
          }else if(pq.poll() != val * -1){
            throw new Exception();
          }
        }catch(Exception e){
          pqBoolean = false;
        }
      }
    }

    int sum = 0;
    if(queueBoolean){
      sum++;
    }
    if(stackBoolean){
      sum++;
    }
    if(pqBoolean){
      sum++;
    }
    //System.out.println("------ " + sum);

    if(sum > 1){
      System.out.println("not sure");
    }else if(sum == 0){
      System.out.println("impossible");
    }else{
      if(queueBoolean){
        System.out.println("queue");
      }
      if(stackBoolean){
        System.out.println("stack");
      }
      if(pqBoolean){
        System.out.println("priority queue");
      }
    }
  }
}
}