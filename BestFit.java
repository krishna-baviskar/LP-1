import java.util.*;

public class BestFit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] process = new int[n];

        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] memory = new int[m];

        for (int i = 0; i < m; i++) {
            System.out.print("Enter size of memory block " + (i + 1) + ": ");
            memory[i] = sc.nextInt();
        }

        System.out.println("\nInitial Memory:");
        for (int i = 0; i < m; i++) {
            System.out.println("Block " + (i + 1) + ": " + memory[i] + "KB");
        }

        for (int i = 0; i < n; i++) {
            System.out.print("Enter memory requirement for process " + (i + 1) + ": ");
            process[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            int bestIndex = -1;
            int minFit = Integer.MAX_VALUE;

            for (int j = 0; j < m; j++) {
                if (memory[j] >= process[i] && memory[j] - process[i] < minFit) {
                    minFit = memory[j] - process[i];
                    bestIndex = j;
                }
            }

            if (bestIndex != -1) {
                memory[bestIndex] -= process[i];
                System.out.println("Process " + (i + 1) + " allocated to Block " + (bestIndex + 1));
            } else {
                System.out.println("Memory allocation for process " + (i + 1) + " failed.");
            }
        }

        System.out.println("\nFragmented Memory:");
        for (int i = 0; i < m; i++) {
            System.out.println("Block " + (i + 1) + ": " + memory[i] + "KB");
        }

        sc.close();
    }
}

