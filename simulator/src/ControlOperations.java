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
		// TODO lege address k in stack
		// setAddress(k); 
	}

	// unconditional branch
	public static void GOTO(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// TODO jump to address k
		// setAddress(k);
	}
	
}
