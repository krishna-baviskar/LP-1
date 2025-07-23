import java.util.*;

class Process {
    String pid;
    int at, bt, ct, wt, tat, rt, priority;
    boolean completed = false;

    public Process(String pid, int at, int bt, int priority) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt;
        this.priority = priority;
    }
}

public class RoundandPrior {

    public static void RoundRobin(List<Process> processes, int quantum) {
        int time = 0;
        Queue<Process> queue = new LinkedList<>();
        int n = processes.size();
        int completed = 0;

        processes.sort(Comparator.comparingInt(p -> p.at));

        while (completed < n) {
            for (Process p : processes) {
                if (p.at <= time && !queue.contains(p) && !p.completed) {
                    queue.add(p);
                }
            }

            if (queue.isEmpty()) {
                time++;
                continue;
            }

            Process current = queue.poll();
            int execTime = Math.min(current.rt, quantum);
            time += execTime;
            current.rt -= execTime;

            for (Process p : processes) {
                if (p.at <= time && !queue.contains(p) && !p.completed) {
                    queue.add(p);
                }
            }

            if (current.rt == 0) {
                current.completed = true;
                current.ct = time;
                current.tat = current.ct - current.at;
                current.wt = current.tat - current.bt;
                completed++;
            } else {
                queue.add(current);
            }
        }

        printProcesses(processes, "Round Robin");
    }

    public static void PriorityScheduling(List<Process> processes) {
        int time = 0;
        int completed = 0;
        int n = processes.size();

        while (completed < n) {
            Process highest = null;

            for (Process p : processes) {
                if (!p.completed && p.at <= time) {
                    if (highest == null || p.priority < highest.priority) {
                        highest = p;
                    }
                }
            }

            if (highest == null) {
                time++;
                continue;
            }

            time += highest.bt;
            highest.ct = time;
            highest.tat = highest.ct - highest.at;
            highest.wt = highest.tat - highest.bt;
            highest.completed = true;
            completed++;
        }

        printProcesses(processes, "Priority Scheduling (Non-Preemptive)");
    }

    public static void printProcesses(List<Process> processes, String title) {
        System.out.println("\n" + title);
        System.out.println("PID\tAT\tBT\tPri\tCT\tTAT\tWT");
        for (Process p : processes) {
            System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\t%d\n",
                p.pid, p.at, p.bt, p.priority, p.ct, p.tat, p.wt);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes for Round Robin: ");
        int n1 = sc.nextInt();
        sc.nextLine();

        List<Process> rrProcesses = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            System.out.printf("Enter Process ID for process %d: ", i + 1);
            String pid = sc.nextLine();

            System.out.printf("Enter Arrival Time for process %s: ", pid);
            int at = sc.nextInt();

            System.out.printf("Enter Burst Time for process %s: ", pid);
            int bt = sc.nextInt();
            sc.nextLine();

            rrProcesses.add(new Process(pid, at, bt, 0));
        }

        System.out.print("Enter time quantum for Round Robin: ");
        int quantum = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter number of processes for Priority Scheduling: ");
        int n2 = sc.nextInt();
        sc.nextLine();

        List<Process> prProcesses = new ArrayList<>();

        for (int i = 0; i < n2; i++) {
            System.out.printf("Enter Process ID for process %d: ", i + 1);
            String pid = sc.nextLine();

            System.out.printf("Enter Arrival Time for process %s: ", pid);
            int at = sc.nextInt();

            System.out.printf("Enter Burst Time for process %s: ", pid);
            int bt = sc.nextInt();

            System.out.printf("Enter Priority for process %s: ", pid);
            int priority = sc.nextInt();
            sc.nextLine();

            prProcesses.add(new Process(pid, at, bt, priority));
        }

        RoundRobin(rrProcesses, quantum);
        PriorityScheduling(prProcesses);

        sc.close();
    }
}

