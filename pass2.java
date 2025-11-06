import java.util.*;

class ST { int index; String name; int address; ST(int i,String n,int a){index=i;name=n;address=a;} }
class LT { int index; String Literal; int address; LT(int i,String l,int a){index=i;Literal=l;address=a;} }

public class pass2 {
    public static void main(String[] args) {
        Map<Integer,ST> STAB=new HashMap<>(); 
        STAB.put(0,new ST(0,"ONE",205)); STAB.put(1,new ST(1,"TWO",209)); STAB.put(2,new ST(2,"RESULT",210));

        Map<Integer,LT> LTAB=new HashMap<>();
        LTAB.put(0,new LT(0,"='5'",211)); LTAB.put(1,new LT(1,"='3'",212));

        String IC[]={
            "AD 01 C 200","IS 04 RG 1 S 0","IS 01 RG 2 S 1","IS 01 RG 2 L 0","IS 05 RG 3 S 2",
            "IS 01 RG 2 L 1","DL 01 C 4","DL 02 C 2","DL 01 C 1","AD 02","DL 02 C 5","DL 02 C 3"
        };

        System.out.println("Final Machine Code:\n-------------------");
        for(String line:IC){
            String t[]=line.split("[ ,]+");
            String cls=t[0],op=t.length>1?t[1]:"",op1t=t.length>2?t[2]:"",op1=t.length>3?t[3]:"",op2t=t.length>4?t[4]:"",op2=t.length>5?t[5]:"";

            if(cls.equals("IS")){
                System.out.print(op+" ");
                if(op1t.equals("RG")) System.out.print(op1+" ");
                if(op2t.equals("S")) System.out.print(STAB.get(Integer.parseInt(op2)).address);
                if(op2t.equals("L")) System.out.print(LTAB.get(Integer.parseInt(op2)).address);
                System.out.println();
            }
            else if(cls.equals("DL")){
                if(op.equals("01")) System.out.println("00 0 "+op1);
                else if(op.equals("02")) System.out.println("00 0 "+op1);
            }
        }

        System.out.println("\nSYMBOL TABLE:"); 
        STAB.values().forEach(s->System.out.println(s.index+"\t"+s.name+"\t"+s.address));
        System.out.println("\nLITERAL TABLE:"); 
        LTAB.values().forEach(l->System.out.println(l.index+"\t"+l.Literal+"\t"+l.address));
    }
}