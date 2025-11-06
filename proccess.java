import java.util.*;

class P {
    int id, at, bt, ct, tat, wt;
    P(int id,int at,int bt){this.id=id;this.at=at;this.bt=bt;}
}

public class proccess {
    public static void main(String[] a) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter no. of processes: ");
        int n=sc.nextInt(); P[] p=new P[n];
        for(int i=0;i<n;i++){
            System.out.print("AT P"+(i+1)+": ");int at=sc.nextInt();
            System.out.print("BT P"+(i+1)+": ");int bt=sc.nextInt();
            p[i]=new P(i+1,at,bt);
        }
        Arrays.sort(p,Comparator.comparingInt(x->x.at));
        int t=0; float twt=0,ttat=0;
        for(P x:p){
            if(t<x.at)t=x.at;
            x.ct=t+x.bt; x.tat=x.ct-x.at; x.wt=x.tat-x.bt; t=x.ct;
            twt+=x.wt; ttat+=x.tat;
        }
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for(P x:p)System.out.println("P"+x.id+"\t"+x.at+"\t"+x.bt+"\t"+x.ct+"\t"+x.tat+"\t"+x.wt);
        System.out.printf("\nAvg TAT: %.2f\nAvg WT: %.2f\n",ttat/n,twt/n);
    }
}