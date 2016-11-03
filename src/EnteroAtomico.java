import java.util.concurrent.atomic.AtomicInteger;


class GlobalVar {
	public final static AtomicInteger c1 = new AtomicInteger(0);
	public final static AtomicInteger c2 = new AtomicInteger(0);
}

class DosMutex extends Thread {
	public void inc1() {
		GlobalVar.c1.incrementAndGet();
	}
	
	public void inc2() {
		GlobalVar.c2.incrementAndGet();
	}
	
	@Override
	public void run() {
		this.inc1();
		this.inc2();
	}
}

public class EnteroAtomico {
	
	public static void main(String[] args) {
		int n;
		
		n = Integer.parseInt(args[0]);
		DosMutex hilos[];
		System.out.println("Creando " + n + " hilos");
		
		hilos = new DosMutex[n];
		
		for (int i = 0; i < n; i++) {
			hilos[i] = new DosMutex();
			hilos[i].start();
		}
		
		for (int i = 0; i < n; i++) {		
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}		
		}
		System.out.println("C1 = " + GlobalVar.c1 + " C2 = " + GlobalVar.c2);
	}

}
