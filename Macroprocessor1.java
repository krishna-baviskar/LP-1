 public class MNTEntry {
	int index;
    String name;
    int mdtIndex;

    MNTEntry(int index, String name, int mdtIndex) {
        this.index = index;
        this.name = name;
        this.mdtIndex = mdtIndex;
    }
}
//Macroproccesor1
public class Macroprocessor1 {
    static List<MNTEntry> MNT = new ArrayList<>(); // Macro Name Table
    static List<String> MDT = new ArrayList<>();   // Macro Definition Table
    static Map<String, List<String>> ALA = new LinkedHashMap<>(); // MacroName -> Actual Args

    public static void main(String[] args) {
        // Example program with two macros
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

        // Print results
        printMNT();
        printMDT();
        printALA();
    }

    // ---------------- PASS 1 -----------------
    static void pass1(String[] program) {
        boolean inMacroDef = false;
        String macroName = "";
        int mntIndex = 0;
        List<String> formals = new ArrayList<>();

        for (int lineNo = 0; lineNo < program.length; lineNo++) {
            String line = program[lineNo].trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("[ ,]+"); //"ADD AREG,&A",

            if (line.equals("MACRO")) {
                inMacroDef = true;
                macroName = "";
                formals.clear();
                continue;
            }

            if (inMacroDef) {
                if (line.equals("MEND")) {
                    MDT.add("MEND");
                    inMacroDef = false;
                    continue;
                }

                if (macroName.equals("")) {
                    // Macro prototype
                    macroName = parts[0];
                    int mdtStart = MDT.size();
                    MNT.add(new MNTEntry(mntIndex++, macroName, mdtStart));

                    // Store formals
                    for (int i = 1; i < parts.length; i++) {
                        formals.add(parts[i]);
                    }
                } else {
                    // Replace formals with positional notations (#0, #1…)
                    StringBuilder mdtLine = new StringBuilder();
                    for (String word : parts) {
                        if (formals.contains(word)) {
                            int index = formals.indexOf(word);
                            mdtLine.append("#").append(index).append(" ");
                        } else {
                            mdtLine.append(word).append(" ");
                        }
                    }
                    MDT.add(mdtLine.toString().trim());
                }
            } else {
                MNTEntry found = null;
                for (MNTEntry e : MNT) {
                    if (e.name.equals(parts[0])) {
                        found = e;
                        break;
                    }
                }
                if (found != null) {
                    List<String> actuals = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        actuals.add(parts[i]);
                    }
                    ALA.put(parts[0], actuals);
                }
            }
        }
    }

    // ---------------- PRINT TABLES -----------------
    static void printMNT() {
        System.out.println("MNT (Macro Name Table):");
        System.out.println("Index\tName\tMDT Index");
        for (MNTEntry entry : MNT) {
            System.out.println(entry.index + "\t" + entry.name + "\t" + entry.mdtIndex);
        }
        System.out.println();
    }

    static void printMDT() {
        System.out.println("MDT (Macro Definition Table):");
        System.out.println("Index\tDefinition");
        for (int i = 0; i < MDT.size(); i++) {
            System.out.println(i + "\t" + MDT.get(i));
        }
        System.out.println();
    }

    static void printALA() {
        System.out.println("ALA (Actual Argument List Array):");
        for (Map.Entry<String, List<String>> entry : ALA.entrySet()) {
            System.out.println("Macro: " + entry.getKey());
            List<String> args = entry.getValue();
            for (int i = 0; i < args.size(); i++) {
                System.out.println("  #" + i + " -> " + args.get(i));
            }
        }
    }
}
  
