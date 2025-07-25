import java.util.Scanner;

public class FirstFit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int process[] = new int[n];
        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        
	System.out.println("intial memory :");
        int memory[] = new int[m];
        for (int i = 0; i < m; i++){
        	System.out.println(memory[i]+ "KB");
        }
        

        for (int i = 0; i < m; i++) {
            System.out.print("Enter size of memory block " + (i + 1) + ": ");
            memory[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            System.out.print("Enter memory requirement for process " + (i + 1) + ": ");
            process[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            boolean allocated = false;

            for (int j = 0; j < m; j++) {
                if (memory[j] >= process[i]) {
                    memory[j] -= process[i];
                    System.out.println("Memory allocated to process " + (i + 1) + " in block " + (j + 1));
                    allocated = true;
                    break;
                }
            }
            

            if (!allocated) {
                System.out.println("Memory allocation for process " + (i + 1) + " failed.");
            }
        }
        System.out.println("Fragmented Memory : ");
        for (int i = 0; i < m; i++){
        	System.out.println(memory[i]+ "KB");
        }

        sc.close();
    }
}

