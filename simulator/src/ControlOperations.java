/***
 * 
 * @author Moritz
 * @author jkraemer
 *
 */
public class ControlOperations { // operations that start with 10
	static int bitmask = 0x7FF; // 00 0111 1111 1111
	static int k;

	// call subroutine
	// status affected: none
	public static void CALL(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
//		int temppcl=(RAM.getPCLATH())>>3;
//		temppcl=temppcl<<8;
//		k=(k&0b11111111111)|temppcl;
		// lege address act pc in stack
	
		globalthings.stack8.push(RAM.PC);
		//globalthings.jumpadress=k;
		RAM.PC=k|((RAM.getPCLATH()&0b11000)<<8);
		//RAM.setPCL(k);
		globalthings.jumpPerformed=true;
		globalthings.callPerformed=true;
		globalthings.cycle++;
		// setAddress(k); 
	}

	// unconditional branch
	// status affected: none
	public static void GOTO(int hexInt) {
		k = hexInt & bitmask; // select k out of the hexInt
//		int temppcl=(RAM.getPCLATH())>>3;
//		temppcl=temppcl<<8;
//		k=(k&0b11111111111)|temppcl;
		RAM.PC=k|((RAM.getPCLATH()& 0b00011000) << 8) ;
		// jump to address k
		//globalthings.jumpadress=k;
		//RAM.setPCL(k);
		//System.out.println("goto"+k+"pcl:"+RAM.getPCL());
		globalthings.jumpPerformed=true;
		globalthings.GOTOPerformed=true;
		globalthings.cycle++;
		
		// setAddress(k);
	}
	
}
