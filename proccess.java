import java.util.*;

class Process {
    int pid;
    int at, bt, ct, tat, wt;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
    }
}

public class proccess {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time for P" + (i + 1) + ": ");
            int at = sc.nextInt();
            System.out.print("Enter Burst Time for P" + (i + 1) + ": ");
            int bt = sc.nextInt();
            p[i] = new Process(i + 1, at, bt);
        }

        // Sort by Arrival Time
        Arrays.sort(p, Comparator.comparingInt(x -> x.at));

        int currentTime = 0;
        float totalWT = 0, totalTAT = 0;

        for (int i = 0; i < n; i++) {
            // If CPU is idle
            if (currentTime < p[i].at) {
                currentTime = p[i].at;
            }

            p[i].ct = currentTime + p[i].bt;              // Completion Time
            p[i].tat = p[i].ct - p[i].at;                 // Turnaround Time
            p[i].wt = p[i].tat - p[i].bt;                 // Waiting Time

            currentTime = p[i].ct;

            totalWT += p[i].wt;
            totalTAT += p[i].tat;
        }

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (Process process : p) {
            System.out.println("P" + process.pid + "\t" + process.at + "\t" + process.bt + "\t" +
                               process.ct + "\t" + process.tat + "\t" + process.wt);
        }

        System.out.printf("\nAverage Turnaround Time: %.2f", totalTAT / n);
        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
    }
}
