import java.util.*;
class NextFit{
 static class B{int s;boolean a;B(int s){this.s=s;}public String toString(){return"["+s+(a?" Alloc":" Free")+"]";}}
 static void alloc(List<B>mem,int[]p){
  int pos=0;
  for(int i=0;i<p.length;i++){
   boolean ok=false;int c=0,j=pos;
   while(c++<mem.size()){
    B b=mem.get(j);
    if(!b.a&&b.s>=p[i]){
     if(b.s>p[i]){int r=b.s-p[i];b.s=p[i];b.a=true;mem.add(j+1,new B(r));}
     else b.a=true;
     System.out.println("P"+(i+1)+"("+p[i]+") -> B"+(j+1));
     pos=(j+1)%mem.size();ok=true;break;
    }
    j=(j+1)%mem.size();
   }
   if(!ok)System.out.println("P"+(i+1)+"("+p[i]+") not allocated");
  }
  System.out.println("\nFinal Memory:");
  for(int i=0;i<mem.size();i++)System.out.println("B"+(i+1)+": "+mem.get(i));
 }
 public static void main(String[]a){
  Scanner s=new Scanner(System.in);
  System.out.print("Blocks: ");int m=s.nextInt();List<B>mem=new ArrayList<>();
  for(int i=0;i<m;i++)mem.add(new B(s.nextInt()));
  System.out.print("Processes: ");int n=s.nextInt(),p[]=new int[n];
  for(int i=0;i<n;i++)p[i]=s.nextInt();
  alloc(mem,p);
 }
}