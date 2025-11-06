import java.util.*;
class FirstFit {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter processes: ");
        int n = s.nextInt(), p[] = new int[n];
        System.out.print("Enter blocks: ");
        int m = s.nextInt(), b[] = new int[m];
        for (int i = 0; i < m; i++) b[i] = s.nextInt();
        for (int i = 0; i < n; i++) p[i] = s.nextInt();
        for (int i = 0; i < n; i++) {
            boolean f = false;
            for (int j = 0; j < m; j++)
                if (b[j] >= p[i]) { b[j] -= p[i]; System.out.println("P" + (i+1) + " -> B" + (j+1)); f = true; break; }
            if (!f) System.out.println("P" + (i+1) + " not allocated");
        }
        System.out.println("Remaining:");
        for (int i = 0; i < m; i++) System.out.println("B" + (i+1) + ": " + b[i]);
    }
}