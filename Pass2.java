import java.util.*;

public class LRU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int f = sc.nextInt();
        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++) pages[i] = sc.nextInt();

        ArrayList<Integer> frames = new ArrayList<>();
        int faults = 0;

        System.out.println("\nPage\tFrames");
        for (int p : pages) {
            if (!frames.contains(p)) {
                if (frames.size() == f) frames.remove(0); // remove least recently used
                frames.add(p); faults++;
            } else { // recently used -> move to end
                frames.remove((Integer)p);
                frames.add(p);
            }
            System.out.println(p + "\t" + frames);
        }

        System.out.println("\nTotal Page Faults: " + faults);
    }
}