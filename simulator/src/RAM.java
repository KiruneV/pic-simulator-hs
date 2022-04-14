/***
 * 
 * @author Moritz
 *
 */
public class RAM {

	static int TMR0, PCL, STATUS, FSR, PORTA, PORTB, EEDATA, EEADR, PCLATH, INTCON, OPTION, TRSIA, TRISB, EECON1,
			EECON2;
	static int bank0[] = new int [0xFF];
	static int bank1[] = new int [0xFF];
	
	public static int getTMR0() {
		return TMR0;
	}

	public RAM() {
		bank0[0x01] = getTMR0();
		bank0[0x02] = getPCL();
		bank0[0x03] = getSTATUS();
		bank0[0x04] = getFSR();
		bank0[0x05] = getPORTA();
		bank0[0x06] = getPORTB();
		bank0[0x08] = getEEDATA();
		bank0[0x09] = getEEADR();
		bank0[0x0A] = getPCLATH();
		bank0[0x0B] = getINTCON();
		
		bank0[0x01] = getTMR0();
		bank0[0x02] = getPCL();
		bank0[0x03] = getSTATUS();
		bank0[0x04] = getFSR();
		bank0[0x05] = getPORTA();
		bank0[0x06] = getPORTB();
		bank0[0x08] = getEEDATA();
		bank0[0x09] = getEEADR();
		bank0[0x0A] = getPCLATH();
		bank0[0x0B] = getINTCON();
	}

	public static void setTMR0(int tMR0) {
		TMR0 = tMR0;
	}

	public static int getPCL() {
		return PCL;
	}

	public static void setPCL(int pCL) {
		PCL = pCL;
	}

	public static int getSTATUS() {
		return STATUS;
	}

	public static void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public static int getFSR() {
		return FSR;
	}

	public static void setFSR(int fSR) {
		FSR = fSR;
	}

	public static int getPORTA() {
		return PORTA;
	}

	public static void setPORTA(int pORTA) {
		PORTA = pORTA;
	}

	public static int getPORTB() {
		return PORTB;
	}

	public static void setPORTB(int pORTB) {
		PORTB = pORTB;
	}

	public static int getEEDATA() {
		return EEDATA;
	}

	public static void setEEDATA(int eEDATA) {
		EEDATA = eEDATA;
	}

	public static int getEEADR() {
		return EEADR;
	}

	public static void setEEADR(int eEADR) {
		EEADR = eEADR;
	}

	public static int getPCLATH() {
		return PCLATH;
	}

	public static void setPCLATH(int pCLATH) {
		PCLATH = pCLATH;
	}

	public static int getINTCON() {
		return INTCON;
	}

	public static void setINTCON(int iNTCON) {
		INTCON = iNTCON;
	}

	public static int getOPTION() {
		return OPTION;
	}

	public static void setOPTION(int oPTION) {
		OPTION = oPTION;
	}

	public static int getTRSIA() {
		return TRSIA;
	}

	public static void setTRSIA(int tRSIA) {
		TRSIA = tRSIA;
	}

	public static int getTRISB() {
		return TRISB;
	}

	public static void setTRISB(int tRISB) {
		TRISB = tRISB;
	}

	public static int getEECON1() {
		return EECON1;
	}

	public static void setEECON1(int eECON1) {
		EECON1 = eECON1;
	}

	public static int getEECON2() {
		return EECON2;
	}

	public static void setEECON2(int eECON2) {
		EECON2 = eECON2;
	}

	public static int[] getBank0() {
		return bank0;
	}

	public static void setBank0() {
		
	}

	public static int[] getBank1() {
		return bank1;
	}

	public static void setBank1(int[] bank1) {
		RAM.bank1 = bank1;
	}

}
