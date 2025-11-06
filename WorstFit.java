import java.util.*;

public class WorstFit {
    static class Block {
        int size; boolean alloc=false;
        Block(int s){size=s;}
        public String toString(){return"["+size+(alloc?" Allocated":" Free")+"]";}
    }

    static void worstFit(List<Block> mem,int[] p){
        System.out.println("\n--- Worst Fit Allocation ---");
        for(int i=0;i<p.length;i++){
            int w=-1;
            for(int j=0;j<mem.size();j++){
                Block b=mem.get(j);
                if(!b.alloc&&b.size>=p[i]&&(w==-1||b.size>mem.get(w).size))w=j;
            }
            if(w!=-1){
                Block b=mem.get(w);
                if(b.size>p[i]){mem.add(w+1,new Block(b.size-p[i]));b.size=p[i];}
                b.alloc=true;
                System.out.println("P"+(i+1)+"("+p[i]+")→B"+(w+1));
            }else System.out.println("P"+(i+1)+"("+p[i]+") not allocated");
        }
        System.out.println("\nFinal Blocks:");
        for(int i=0;i<mem.size();i++)System.out.println("B"+(i+1)+": "+mem.get(i));
    }

    public static void main(String[]a){
        Scanner sc=new Scanner(System.in);
        System.out.print("Blocks: ");int m=sc.nextInt();List<Block> mem=new ArrayList<>();
        for(int i=0;i<m;i++){System.out.print("Size of B"+(i+1)+": ");mem.add(new Block(sc.nextInt()));}
        System.out.print("Processes: ");int n=sc.nextInt();int[] p=new int[n];
        for(int i=0;i<n;i++){System.out.print("Size of P"+(i+1)+": ");p[i]=sc.nextInt();}
        worstFit(mem,p);
    }
}