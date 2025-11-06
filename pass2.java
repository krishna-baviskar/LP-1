import java.util.*;

public class pass2 {

	  public static void main(String[] args) {
	        // ----------------- SYMBOL TABLE -----------------
	        Map symbolTable = new HashMap<>();
	        symbolTable.put(0, new ST(0, "ONE", 205));
	        symbolTable.put(1, new ST(1, "TWO", 209));
	        symbolTable.put(2, new ST(2, "RESULT", 210));

	        // ----------------- LITERAL TABLE ----------------
	        Map literalTable = new HashMap<>();
	        literalTable.put(0, new LT(0, "='5'", 211));
	        literalTable.put(1, new LT(1, "='3'", 212));

	        // ----------------- INTERMEDIATE CODE -------------
	        String[] intermediateCode = {
	            "AD 01 C 200",
	            "IS 04 RG 1 S 0",
	            "IS 01 RG 2 S 1",
	            "IS 01 RG 2 L 0",
	            "IS 05 RG 3 S 2",
	            "IS 01 RG 2 L 1",
	            "DL 01 C 4",
	            "DL 02 C 2",
	            "DL 01 C 1",
	            "AD 02",
	            "DL 02 C 5",
	            "DL 02 C 3"
	        };

	        System.out.println("Final Machine Code:");
	        System.out.println("-------------------");

	        for (String line : intermediateCode) {
	            String tokens[] = line.split("[ ,]+");
     
	            // IS 04 RG 1 S 0   //  04 1 205
	            
	            String token_opcodeClass   = tokens[0]; // AD, IS, DL  //IS
	            String token_opcode        = tokens.length > 1 ? tokens[1] : null; //04
	            String token_operand1Type  = tokens.length > 2 ? tokens[2] : null; // RG
	            String token_operand1      = tokens.length > 3 ? tokens[3] : null; // 1
	            String token_operand2Type  = tokens.length > 4 ? tokens[4] : null; //S
	            String token_operand2      = tokens.length > 5 ? tokens[5] : null;  //0

	            // ---- Process Imperative Statements ----
	            if ("IS".equals(token_opcodeClass)) {
	                System.out.print(token_opcode + " ");

	                // Operand 1: Register
	                if ("RG".equals(token_operand1Type)) {
	                    System.out.print(token_operand1 + " ");
	                }

	                // Operand 2: Symbol
	                if ("S".equals(token_operand2Type)) {
	                    ST sym = symbolTable.get(Integer.parseInt(token_operand2));
	                    System.out.print(sym.address);
	                }

	                // Operand 2: Literal
	                if ("L".equals(token_operand2Type)) {
	                    LT lit = literalTable.get(Integer.parseInt(token_operand2));
	                    System.out.print(lit.address);
	                }

	                System.out.println();
	            }

	            // ---- Process Declarative Statements ----
	            else if ("DL".equals(token_opcodeClass)) {
	                if ("01".equals(token_opcode)) { // DC
	                    System.out.println("00 0 " + token_operand1);
	                }
	                if ("02".equals(token_opcode)) { // DS
	                   // System.out.println("00 0 " + "0".repeat(Integer.parseInt(token_operand1)));
	                	 System.out.println("00 0 " + token_operand1);
	                }
	            }

	            // ---- Assembler Directives (AD) - no machine code ----
	            else if ("AD".equals(token_opcodeClass)) {
	                continue;
	            }
	        }

	        // ----------------- Print Symbol & Literal Tables -----------------
	        System.out.println("\nSYMBOL TABLE:");
	        for (ST s : symbolTable.values()) {
	            System.out.println(s.index + "\t" + s.name + "\t" + s.address);
	        }

	        System.out.println("\nLITERAL TABLE:");
	        for (LT l : literalTable.values()) {
	            System.out.println(l.index + "\t" + l.Literal + "\t" + l.address);
	        }
	    }
	}
