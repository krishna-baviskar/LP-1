import java.util.*;
class BestFit {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter no. of processes: ");
        int n = s.nextInt(), p[] = new int[n];
        System.out.print("Enter no. of blocks: ");
        int m = s.nextInt(), b[] = new int[m];
        for (int i = 0; i < m; i++) b[i] = s.nextInt();
        for (int i = 0; i < n; i++) p[i] = s.nextInt();
        for (int i = 0; i < n; i++) {
            int idx = -1, min = 9999;
            for (int j = 0; j < m; j++)
                if (b[j] >= p[i] && b[j] - p[i] < min) { min = b[j] - p[i]; idx = j; }
            if (idx != -1) { b[idx] -= p[i]; System.out.println("P" + (i + 1) + " -> B" + (idx + 1)); }
            else System.out.println("P" + (i + 1) + " not allocated");
        }
        System.out.println("Remaining blocks:");
        for (int i = 0; i < m; i++) System.out.println("B" + (i + 1) + ": " + b[i]);
    }
}