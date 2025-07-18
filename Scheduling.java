import java.util.*;

class Process {
    String pid;
    int at, bt, ct, wt, tat, rt;
    boolean completed = false;

    public Process(String pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt; 
    }
}

public class Scheduling {

    public static void fcfs(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.at));
        int time = 0;

        for (Process p : processes) {
            if (time < p.at) time = p.at;
            p.ct = time + p.bt;
            p.tat = p.ct - p.at;
            p.wt = p.tat - p.bt;
            time = p.ct;
        }

        System.out.println("\n------- FCFS Scheduling -------");
        printTable(processes);
    }

    public static void sjfPreemptive(List<Process> plist) {
        int time = 0, completed = 0;
        int n = plist.size();
        List<Process> processes = new ArrayList<>();
        for (Process p : plist) {
            processes.add(new Process(p.pid, p.at, p.bt)); // deep copy
        }

        Process current = null;
        while (completed < n) {
            List<Process> ready = new ArrayList<>();
            for (Process p : processes) {
                if (p.at <= time && !p.completed && p.rt > 0) {
                    ready.add(p);
                }
            }

            if (!ready.isEmpty()) {
                current = ready.stream().min(Comparator.comparingInt(p -> p.rt)).get();
                current.rt -= 1;
                time++;

                if (current.rt == 0) {
                    current.ct = time;
                    current.tat = current.ct - current.at;
                    current.wt = current.tat - current.bt;
                    current.completed = true;
                    completed++;
                }
            } else {
                time++;
            }
        }

        System.out.println("\n------ SJF Preemptive Scheduling ------");
        printTable(processes);
    }

    public static void printTable(List<Process> processes) {
        System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s\n", "PID", "AT", "BT", "CT", "TAT", "WT");
        for (Process p : processes) {
            System.out.printf("%-5s %-5d %-5d %-5d %-5d %-5d\n",
                    p.pid, p.at, p.bt, p.ct, p.tat, p.wt);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        sc.nextLine();  

        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.printf("Enter Process ID for process %d: ", i + 1);
            String pid = sc.nextLine();

            System.out.printf("Enter Arrival Time for process %s: ", pid);
            int at = sc.nextInt();

            System.out.printf("Enter Burst Time for process %s: ", pid);
            int bt = sc.nextInt();
            sc.nextLine(); // Consume newline

            processes.add(new Process(pid, at, bt));
        }

        fcfs(new ArrayList<>(processes));
        sjfPreemptive(new ArrayList<>(processes));

        sc.close();
    }
}

