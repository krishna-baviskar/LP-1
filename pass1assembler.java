import java.util.*;

public class Pass1 {
    static Map<String,String> IS=new HashMap<>(),AD=new HashMap<>(),DL=new HashMap<>(),REG=new HashMap<>();
    static List<String> SYM=new ArrayList<>(),LIT=new ArrayList<>(),IC=new ArrayList<>();
    static Map<String,Integer> SYMADDR=new HashMap<>(),LITADDR=new HashMap<>();

    public static void main(String[]a){
        IS.put("ADD","01");IS.put("SUB","02");IS.put("MULT","03");IS.put("MOVER","04");
        IS.put("MOVEM","05");IS.put("COMP","06");IS.put("BC","07");IS.put("DIV","08");
        IS.put("READ","09");IS.put("PRINT","10");
        AD.put("START","01");AD.put("END","02");
        DL.put("DS","01");DL.put("DC","02");
        REG.put("AREG","1");REG.put("BREG","2");REG.put("CREG","3");REG.put("DREG","4");

        String p[]={"START 200","MOVER AREG,ONE","ADD BREG,TWO","ADD BREG,='5'",
            "MOVEM CREG,RESULT","ADD BREG,='3'","ONE DS 4","TWO DC 2","RESULT DS 1","END"};
        pass1(p);

        System.out.println("\nSYMTAB:");for(int i=0;i<SYM.size();i++)System.out.println(i+" "+SYM.get(i)+" "+SYMADDR.get(SYM.get(i)));
        System.out.println("\nLITTAB:");for(int i=0;i<LIT.size();i++)System.out.println(i+" "+LIT.get(i)+" "+LITADDR.get(LIT.get(i)));
        System.out.println("\nIC:");IC.forEach(System.out::println);
    }

    static void pass1(String[]p){
        int lc=0;String val=null;
        for(String l:p){
            String s[]=l.split("[ ,]+");
            if(AD.containsKey(s[0])){
                if(s[0].equals("START")){lc=Integer.parseInt(s[1]);IC.add("AD "+AD.get("START")+" C "+lc);}
                else if(s[0].equals("END")){
                    IC.add("AD "+AD.get("END"));
                    for(String lit:LIT){LITADDR.put(lit,lc);val=lit.substring(2,lit.length()-1);
                        IC.add("DL "+DL.get("DC")+" C "+val);lc++;}}
            }else if(IS.containsKey(s[0])){
                String ic="IS "+IS.get(s[0]);
                if(s.length>1&&REG.containsKey(s[1]))ic+=" RG "+REG.get(s[1]);
                if(s.length>2){
                    if(s[2].startsWith("=")){if(!LIT.contains(s[2]))LIT.add(s[2]);
                        ic+=" L "+LIT.indexOf(s[2]);}
                    else{if(!SYM.contains(s[2]))SYM.add(s[2]);
                        ic+=" S "+SYM.indexOf(s[2]);}}
                }
                IC.add(ic);lc++;
            }else{
                String lab=s[0];if(!SYM.contains(lab))SYM.add(lab);
                SYMADDR.put(lab,lc);
                if(DL.containsKey(s[1])){
                    if(s[1].equals("DS")){IC.add("DL "+DL.get("DS")+" C "+s[2]);lc+=Integer.parseInt(s[2]);}
                    else{IC.add("DL "+DL.get("DC")+" C "+s[2]);lc++;}}
            }
        }
    }
}