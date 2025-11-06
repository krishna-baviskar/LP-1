import java.util.*;

class Process {
    String pid; int at, bt, ct, wt, tat, rt; boolean done=false;
    Process(String id,int a,int b){pid=id;at=a;bt=b;rt=b;}
}

public class Scheduling {
    static void fcfs(List<Process> p){
        p.sort(Comparator.comparingInt(x->x.at)); int t=0;
        for(Process x:p){ if(t<x.at)t=x.at; x.ct=t+x.bt; x.tat=x.ct-x.at; x.wt=x.tat-x.bt; t=x.ct; }
        System.out.println("\n--- FCFS ---"); print(p);
    }

    static void sjfPre(List<Process> p){
        int t=0,done=0,n=p.size(); List<Process> q=new ArrayList<>(); for(Process x:p)q.add(new Process(x.pid,x.at,x.bt));
        while(done<n){
            List<Process> r=new ArrayList<>(); for(Process x:q)if(x.at<=t&&!x.done&&x.rt>0)r.add(x);
            if(r.isEmpty()){t++;continue;}
            Process cur=Collections.min(r,Comparator.comparingInt(x->x.rt)); cur.rt--; t++;
            if(cur.rt==0){cur.ct=t;cur.tat=t-cur.at;cur.wt=cur.tat-cur.bt;cur.done=true;done++;}
        }
        System.out.println("\n--- SJF Preemptive ---"); print(q);
    }

    static void print(List<Process> p){
        System.out.printf("PID\tAT\tBT\tCT\tTAT\tWT\n");
        for(Process x:p) System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\n",x.pid,x.at,x.bt,x.ct,x.tat,x.wt);
    }

    public static void main(String[]a){
        Scanner sc=new Scanner(System.in);
        System.out.print("No. of processes: "); int n=sc.nextInt(); sc.nextLine();
        List<Process> p=new ArrayList<>();
        for(int i=0;i<n;i++){
            System.out.print("PID: "); String id=sc.nextLine();
            System.out.print("AT: "); int at=sc.nextInt();
            System.out.print("BT: "); int bt=sc.nextInt(); sc.nextLine();
            p.add(new Process(id,at,bt));
        }
        fcfs(new ArrayList<>(p)); sjfPre(new ArrayList<>(p)); sc.close();
    }
}