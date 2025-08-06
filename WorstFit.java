import java.util.ArrayList;
import java.util.Scanner;

public class WorstFit {

    static class Block {
        int size;
        boolean allocated;

        Block(int size) {
            this.size = size;
            this.allocated = false;
        }

        @Override
        public String toString() {
            return "[" + size + (allocated ? " Allocated" : " Free") + "]";
        }
    }

    static void worstFit(ArrayList<Block> memory, int[] processes) {
        System.out.println("\nWorst Fit Allocation with Fragmentation:");

        for (int i = 0; i < processes.length; i++) {
            int worstIdx = -1;
            for (int j = 0; j < memory.size(); j++) {
                Block block = memory.get(j);
                if (!block.allocated && block.size >= processes[i]) {
                    if (worstIdx == -1 || block.size > memory.get(worstIdx).size) {
                        worstIdx = j;
                    }
                }
            }

            if (worstIdx != -1) {
                Block block = memory.get(worstIdx);
                if (block.size > processes[i]) {
                    int leftover = block.size - processes[i];
                    block.size = processes[i];
                    block.allocated = true;
                    memory.add(worstIdx + 1, new Block(leftover));
                } else {
                    block.allocated = true;
                }
                System.out.println("Process " + (i + 1) + " of size " + processes[i] + " allocated to block " + (worstIdx + 1));
            } else {
                System.out.println("Process " + (i + 1) + " of size " + processes[i] + " not allocated");
            }
        }

        System.out.println("\nFinal memory blocks state:");
        for (int i = 0; i < memory.size(); i++) {
            System.out.println("Block " + (i + 1) + ": " + memory.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        ArrayList<Block> memory = new ArrayList<>();
        System.out.println("Enter sizes of memory blocks:");
        for (int i = 0; i < m; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            memory.add(new Block(sc.nextInt()));
        }

        System.out.print("\nEnter number of processes: ");
        int n = sc.nextInt();
        int[] processes = new int[n];
        System.out.println("Enter sizes of processes:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            processes[i] = sc.nextInt();
        }

        worstFit(memory, processes);
    }
}

