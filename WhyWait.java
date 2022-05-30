public class WhyWait extends
Thread{
static int sum;
public void run() {
synchronized(this) {
for(int i=1; i<=10; i++)
{sum =sum+i; }
notify();
}
}public static void main(String... args)
throws Exception {
WhyWait ww = new WhyWait();
ww.start();
synchronized(ww) { //Line 1
try { ww.wait();
System.out.println("sum = " + ww.sum)
;
}catch(InterruptedException ie)
{
ie.printStackTrace();
System.exit(1);
}
}