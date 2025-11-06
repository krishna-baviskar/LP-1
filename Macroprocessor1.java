import java.util.*;

class MNTEntry {
    int i;
    String n;
    int m;

    MNTEntry(int i, String n, int m) {
        this.i = i;
        this.n = n;
        this.m = m;
    }
}

public class Macro1 {
    static List<MNTEntry> MNT = new ArrayList<>();
    static List<String> MDT = new ArrayList<>();
    static Map<String, List<String>> ALA = new LinkedHashMap<>();

    public static void main(String[] args) {
        String[] program = {
            "MACRO",
            "INCR &A,&B",
            "ADD AREG,&A",
            "SUB BREG,&B",
            "MEND",
            "MACRO",
            "DECR &X,&Y",
            "SUB AREG,&X",
            "SUB BREG,&Y",
            "MEND",
            "START 100",
            "INCR A,B",
            "DECR 5,10",
            "END"
        };

        pass1(program);
        print();
    }

    static void pass1(String[] program) {
        boolean inMacro = false;
        String name = "";
        int index = 0;
        List<String> formals = new ArrayList<>();

        for (String line : program) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("[ ,]+");

            if (line.equals("MACRO")) {
                inMacro = true;
                name = "";
                formals.clear();
                continue;
            }

            if (inMacro) {
                if (line.equals("MEND")) {
                    MDT.add("MEND");
                    inMacro = false;
                    continue;
                }

                if (name.equals("")) {
                    name = parts[0];
                    MNT.add(new MNTEntry(index++, name, MDT.size()));
                    for (int i = 1; i < parts.length; i++)
                        formals.add(parts[i]);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (String word : parts) {
                        if (formals.contains(word))
                            sb.append("#").append(formals.indexOf(word));
                        else
                            sb.append(word);
                        sb.append(" ");
                    }
                    MDT.add(sb.toString().trim());
                }
            } else {
                for (MNTEntry entry : MNT) {
                    if (entry.n.equals(parts[0])) {
                        List<String> actuals = new ArrayList<>();
                        for (int i = 1; i < parts.length; i++)
                            actuals.add(parts[i]);
                        ALA.put(parts[0], actuals);
                        break;
                    }
                }
            }
        }
    }

    static void print() {
        System.out.println("MNT (Macro Name Table):");
        for (MNTEntry e : MNT)
            System.out.println(e.i + " " + e.n + " " + e.m);

        System.out.println("\nMDT (Macro Definition Table):");
        for (int i = 0; i < MDT.size(); i++)
            System.out.println(i + " " + MDT.get(i));

        System.out.println("\nALA (Argument List Array):");
        for (var e : ALA.entrySet())
            System.out.println(e.getKey() + ": " + e.getValue());
    }
}