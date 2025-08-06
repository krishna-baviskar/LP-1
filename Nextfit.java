import java.util.ArrayList;
import java.util.Scanner;

public class Nextfit{

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

    static void nextFit(ArrayList<Block> memory, int[] processes) {
        System.out.println("\nNext Fit Allocation with Fragmentation:");
        int lastPos = 0;

        for (int i = 0; i < processes.length; i++) {
            boolean allocated = false;
            int count = 0;
            int j = lastPos;

            while (count < memory.size()) {
                Block block = memory.get(j);

                if (!block.allocated && block.size >= processes[i]) {
                    if (block.size > processes[i]) {
                        int leftover = block.size - processes[i];
                        block.size = processes[i];
                        block.allocated = true;
                        memory.add(j + 1, new Block(leftover));
                    } else {
                        block.allocated = true;
                    }
                    System.out.println("Process " + (i + 1) + " of size " + processes[i] + " allocated to block " + (j + 1));
                    lastPos = (j + 1) % memory.size();
                    allocated = true;
                    break;
                }

                j = (j + 1) % memory.size();
                count++;
            }

            if (!allocated) {
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

        nextFit(memory, processes);
    }
}

