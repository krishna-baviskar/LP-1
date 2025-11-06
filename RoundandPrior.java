import java.util.*;

class Process {
    String pid; int at, bt, ct, wt, tat, rt, priority; boolean completed = false;
    Process(String pid, int at, int bt, int pr){ this.pid=pid; this.at=at; this.bt=bt; this.rt=bt; this.priority=pr; }
}

public class RoundandPrior {
    static void RoundRobin(List<Process> p, int q){
        int t=0,c=0,n=p.size(); Queue<Process> ql=new LinkedList<>(); p.sort(Comparator.comparingInt(x->x.at));
        while(c<n){
            for(Process x:p) if(x.at<=t&&!ql.contains(x)&&!x.completed) ql.add(x);
            if(ql.isEmpty()){t++;continue;}
            Process cur=ql.poll(); int ex=Math.min(cur.rt,q); t+=ex; cur.rt-=ex;
            for(Process x:p) if(x.at<=t&&!ql.contains(x)&&!x.completed) ql.add(x);
            if(cur.rt==0){cur.completed=true;cur.ct=t;cur.tat=t-cur.at;cur.wt=cur.tat-cur.bt;c++;} else ql.add(cur);
        }
        print(p,"Round Robin");
    }

    static void PriorityScheduling(List<Process> p){
        int t=0,c=0,n=p.size();
        while(c<n){
            Process h=null;
            for(Process x:p) if(!x.completed&&x.at<=t&&(h==null||x.priority<h.priority)) h=x;
            if(h==null){t++;continue;}
            t+=h.bt; h.ct=t; h.tat=h.ct-h.at; h.wt=h.tat-h.bt; h.completed=true; c++;
        }
        print(p,"Priority Scheduling");
    }

    static void print(List<Process> p,String title){
        System.out.println("\n"+title+"\nPID\tAT\tBT\tPri\tCT\tTAT\tWT");
        for(Process x:p) System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\t%d\n",x.pid,x.at,x.bt,x.priority,x.ct,x.tat,x.wt);
    }

    public static void main(String[] a){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of processes for RR: "); int n1=sc.nextInt(); sc.nextLine();
        List<Process> rr=new ArrayList<>();
        for(int i=0;i<n1;i++){
            System.out.print("PID: "); String id=sc.nextLine();
            System.out.print("AT: "); int at=sc.nextInt();
            System.out.print("BT: "); int bt=sc.nextInt(); sc.nextLine();
            rr.add(new Process(id,at,bt,0));
        }
        System.out.print("Quantum: "); int q=sc.nextInt();
        System.out.print("Enter number of processes for Priority: "); int n2=sc.nextInt(); sc.nextLine();
        List<Process> pr=new ArrayList<>();
        for(int i=0;i<n2;i++){
            System.out.print("PID: "); String id=sc.nextLine();
            System.out.print("AT: "); int at=sc.nextInt();
            System.out.print("BT: "); int bt=sc.nextInt();
            System.out.print("Priority: "); int prt=sc.nextInt(); sc.nextLine();
            pr.add(new Process(id,at,bt,prt));
        }
        RoundRobin(rr,q); PriorityScheduling(pr); sc.close();
    }
}