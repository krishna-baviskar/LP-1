import java.util.*;
class MNTEntry{int i;String n;int m;MNTEntry(int i,String n,int m){this.i=i;this.n=n;this.m=m;}}
public class Macro1{
 static List<MNTEntry>MNT=new ArrayList<>();
 static List<String>MDT=new ArrayList<>();
 static Map<String,List<String>>ALA=new LinkedHashMap<>();
 public static void main(String[]a){
  String[]p={"MACRO","INCR &A,&B","ADD AREG,&A","SUB BREG,&B","MEND","MACRO","DECR &X,&Y","SUB AREG,&X","SUB BREG,&Y","MEND","START 100","INCR A,B","DECR 5,10","END"};
  pass1(p);print();
 }
 static void pass1(String[]p){
  boolean inM=false;String name="";int idx=0;List<String>f=new ArrayList<>();
  for(String l:p){l=l.trim();if(l.isEmpty())continue;String[]w=l.split("[ ,]+");
   if(l.equals("MACRO")){inM=true;name="";f.clear();continue;}
   if(inM){if(l.equals("MEND")){MDT.add("MEND");inM=false;continue;}
    if(name.equals("")){name=w[0];MNT.add(new MNTEntry(idx++,name,MDT.size()));for(int i=1;i<w.length;i++)f.add(w[i]);}
    else{StringBuilder sb=new StringBuilder();for(String x:w)sb.append(f.contains(x)?"#"+f.indexOf(x):x).append(" ");MDT.add(sb.toString().trim());}}
   else{for(MNTEntry e:MNT)if(e.n.equals(w[0])){List<String>a=new ArrayList<>();for(int i=1;i<w.length;i++)a.add(w[i]);ALA.put(w[0],a);break;}}
  }
 }
 static void print(){
  System.out.println("MNT:");for(MNTEntry e:MNT)System.out.println(e.i+" "+e.n+" "+e.m);
  System.out.println("\nMDT:");for(int i=0;i<MDT.size();i++)System.out.println(i+" "+MDT.get(i));
  System.out.println("\nALA:");for(var e:ALA.entrySet()){System.out.println(e.getKey()+": "+e.getValue());}
 }
}