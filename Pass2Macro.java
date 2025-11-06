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

 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pass2Macro {
	
	    // Hard-coded MNT
	    static List MNT = Arrays.asList(
	            new MNTEntry(1, "INCR", 0), // MDT index where INCR starts
	            new MNTEntry(2, "DECR", 3)  // MDT index where DECR starts
	    );

	    // Hard-coded MDT
	    static List MDT = Arrays.asList(
	            "ADD AREG,#0",   // INCR first line
	            "SUB BREG,#1", // INCR second line
	            "MEND",          
	            "SUB AREG,#0",   // DECR first line
	            "ADD BREG,#1",   // DECR second line
	            "MEND"
	    );

	    // Hard-coded ALA (macro invocations and actual arguments)
	    static Map> ALA = new HashMap<>();
	    static {
	        ALA.put("INCR", Arrays.asList("A", "B"));
	        ALA.put("DECR", Arrays.asList("X", "Y"));
	    }

	    public static void main(String[] args) {
	        // Example program (input with macro calls)
	        String[] program = {
	                "START 100",
	                "INCR A,B",
	                "MOVER CREG,=2",
	                "DECR X,Y",
	                "END"
	        };

	        List expanded = pass2(program);

	        System.out.println("----- EXPANDED CODE -----");
	        for (String line : expanded) {
	            System.out.println(line);
	        }
	    }

	    // PASS 2: Expand macros using MNT, MDT, and ALA
	    static List     pass2(String[] program) {
	        List output = new ArrayList<>();

	        for (String line : program) {
	            line = line.trim();
	            if (line.isEmpty()) continue;

	            String[] parts = line.split("[ ,]+"); // START 100

	            // Check if this is a macro invocation/call
	            MNTEntry found = null;
	            for (MNTEntry e : MNT) {
	                if (e.name.equals(parts[0])) {
	                    found = e;
	                    break;
	                }
	            }

	            if (found != null) {
	                // Get actuals from ALA
	                List actuals = ALA.get(parts[0]);

	                // Expand MDT lines starting from mdtIndex until next MEND or next macro
	                int index = found.mdtIndex;
	                while (index < MDT.size() && !MDT.get(index).equals("MEND")) {
	                    String expLine = MDT.get(index);
	                    for (int i = 0; i < actuals.size(); i++) {
	                        expLine = expLine.replace("#" + i, actuals.get(i));
	                    }
	                    output.add(expLine);
	                    index++;
	                }
	            } else {
	                // Not a macro call → copy line
	                output.add(line);
	            }
	        }
	        return output;
	    }
	
}
