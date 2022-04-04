/***
 * 
 * @author Moritz
 *
 */
public class ControlOperations { // operations that start with 10
	int bitmask = 0x7FF; // 00 0111 1111 1111
	int k;

	// call subroutine
	public void CALL(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// TODO lege address k in stack
	}

	// unconditional branch
	public void GOTO(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
		// TODO jump to address k
	}

	public void setRegister(int d, int w) {
		if (d == 0) {
			setW(w);
		} else {
			// store f back in register f
			setF(w);
		}

	}

	// set zero flag
	public void setZ(int i) {
		// TODO Auto-generated method stub

	}

	// check zero flag
	public void checkZ(int i) {
		if (i == 0) {
			setZ(1);
		}
		setZ(0);
	}

	// set carry bit
	public void setC(int i) {
		// TODO Auto-generated method stub

	}

	// get carry bit
	public int getC() {
		// TODO Auto-generated method stub
		return 0;
	}

	// set digit carry
	public void setDC(int i) {
		// TODO Auto-generated method stub

	}

	// set w register
	public void setW(int w) {
		// TODO Auto-generated method stub

	}

	public int getW() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void setF(int f) {
		// TODO Auto-generated method stub

	}
}
