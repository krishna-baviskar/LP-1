import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int f=sc.nextInt();
        System.out.print("Enter number of pages: ");
        int n=sc.nextInt();
        int[] pages=new int[n];
        System.out.println("Enter page reference string:");
        for(int i=0;i<n;i++)pages[i]=sc.nextInt();

        Queue<Integer> q=new LinkedList<>();
        Set<Integer> s=new HashSet<>();
        int faults=0;

        System.out.println("\nPage\tFrames");
        for(int p:pages){
            if(!s.contains(p)){
                if(s.size()==f){int rem=q.poll();s.remove(rem);}
                s.add(p);q.add(p);faults++;
            }
            System.out.print(p+"\t"+q+"\n");
        }
        System.out.println("\nTotal Page Faults: "+faults);
    }
}