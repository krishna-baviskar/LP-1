import java.util.*;

class MNTEntry {
    int i; String n; int m;
    MNTEntry(int i, String n, int m) { this.i = i; this.n = n; this.m = m; }
}

public class Pass2Macro {
    static List<MNTEntry> MNT = Arrays.asList(
        new MNTEntry(1, "INCR", 0),
        new MNTEntry(2, "DECR", 3)
    );
    static List<String> MDT = Arrays.asList(
        "ADD AREG,#0", "SUB BREG,#1", "MEND",
        "SUB AREG,#0", "ADD BREG,#1", "MEND"
    );
    static Map<String, List<String>> ALA = new HashMap<>();
    static {
        ALA.put("INCR", Arrays.asList("A", "B"));
        ALA.put("DECR", Arrays.asList("X", "Y"));
    }

    public static void main(String[] args) {
        String[] prog = { "START 100", "INCR A,B", "MOVER CREG,=2", "DECR X,Y", "END" };
        List<String> out = pass2(prog);
        System.out.println("----- EXPANDED CODE -----");
        out.forEach(System.out::println);
    }

    static List<String> pass2(String[] prog) {
        List<String> out = new ArrayList<>();
        for (String line : prog) {
            line = line.trim(); if (line.isEmpty()) continue;
            String[] w = line.split("[ ,]+");
            MNTEntry f = MNT.stream().filter(e -> e.n.equals(w[0])).findFirst().orElse(null);
            if (f != null) {
                List<String> act = ALA.get(w[0]);
                for (int i = f.m; i < MDT.size() && !MDT.get(i).equals("MEND"); i++) {
                    String l = MDT.get(i);
                    for (int j = 0; j < act.size(); j++)
                        l = l.replace("#" + j, act.get(j));
                    out.add(l);
                }
            } else out.add(line);
        }
        return out;
    }
}