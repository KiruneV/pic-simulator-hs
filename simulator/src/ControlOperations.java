/***
 * 
 * @author Moritz
 *
 */
public class ControlOperations { // operations that start with 10
	static int bitmask = 0x7FF; // 00 0111 1111 1111
	static int k;

	// call subroutine
	public static void CALL(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// TODO lege address act pcl+1 in stack
		globalthings.stack8.push(RAM.getPCL()+1);
		RAM.setPCL(k);
		globalthings.jumpPerformed=true;
		// setAddress(k); 
	}

	// unconditional branch
	public static void GOTO(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// TODO jump to address k
		RAM.setPCL(k);
		//System.out.println("goto"+k+"pcl:"+RAM.getPCL());
		globalthings.jumpPerformed=true;
		
		// setAddress(k);
	}
	
}
