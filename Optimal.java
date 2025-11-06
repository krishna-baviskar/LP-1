import java.util.*;

public class Optimal {
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
        for (int i = 0; i < n; i++) {
            int p = pages[i];
            if (!frames.contains(p)) {
                if (frames.size() == f) {
                    int farthest = -1, indexToReplace = -1;
                    for (int j = 0; j < f; j++) {
                        int framePage = frames.get(j);
                        int nextUse = Integer.MAX_VALUE;
                        for (int k = i + 1; k < n; k++) {
                            if (pages[k] == framePage) { nextUse = k; break; }
                        }
                        if (nextUse > farthest) {
                            farthest = nextUse;
                            indexToReplace = j;
                        }
                    }
                    frames.set(indexToReplace, p);
                } else frames.add(p);
                faults++;
            }
            System.out.println(p + "\t" + frames);
        }

        System.out.println("\nTotal Page Faults: " + faults);
    }
}