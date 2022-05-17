/***
 * 
 * @author Moritz
 *
 */
public class RAM {

	static int TMR0 = 0x01, PCL = 0x02, STATUS = 0x03, FSR = 0x04, PORTA = 0x05, PORTB = 0x06, EEDATA = 0x08,
			EEADR = 0x09, PCLATH = 0x0A, INTCON = 0x0B, OPTION = 0x81, TRISA = 0x85, TRISB = 0x86, EECON1 = 0x88,
			EECON2 = 0x89;
	static int bank[] = new int[0xFF];

	public RAM() {
		// bank 0 (00h - 7Fh)
		bank[TMR0] = 0b00000000;
		bank[PCL] = 0b00000000;
		bank[STATUS] = 0b00011000;
		bank[FSR] = 0b00000000;
		bank[PORTA] = 0b00000000;
		bank[PORTB] = 0b00000000;
		bank[EEDATA] = 0b00000000;
		bank[EEADR] = 0b00000000;
		bank[PCLATH] = 0b00000000;
		bank[INTCON] = 0b00000000;
		// bank 1 (80h - FFh)
		bank[OPTION] = 0b11111111;
		bank[TRISA] = 0b00011111;
		bank[TRISB] = 0b11111111;
		bank[EECON1] = 0b00000000;
		bank[EECON2] = 0b00000000;

	}
// brauchen wir nicht
//	public void resetRAM() {
//		// bank 0 (00h - 7Fh)
//		// bank[TMR0] unchanged
//		bank[PCL] = 0b00000000;
//		bank[STATUS] = bank[STATUS] & 0b000qq11;
//		// bank[FSR] unchanged
//		// bank[PORTA] unchanged
//		// bank[PORTB] unchanged
//		// bank[EEDATA] unchanged
//		// bank[EEADR] unchanged
//		bank[PCLATH] = 0b00000000;
//		bank[INTCON] = bank[INTCON] & 0b00000001;
//		// bank 1 (80h - FFh)
//		bank[OPTION] = 0b11111111;
//		bank[TRISA] = 0b00011111;
//		bank[TRISB] = 0b11111111;
//		bank[EECON1] = 0b0000q000;
//		bank[EECON2] = 0b00000000;
//		 
//	}

	public static int getContent(int address) {
		if (address == 0x01) {
			return getTMR0();
		} else if (address == 0x02 || address == 0x82) {
			return getPCL();
		} else if (address == 0x03 || address == 0x83) {
			return getSTATUS();
		} else if (address == 0x04 || address == 0x84) {
			return getFSR();
		} else if (address == 0x05) {
			return getPORTA();
		} else if (address == 0x06) {
			return getPORTB();
		} else if (address == 0x08) {
			return getEEDATA();
		} else if (address == 0x09) {
			return getEEADR();
		} else if (address == 0x0A || address == 0x8A) {
			return getPCLATH();
		} else if (address == 0x0B || address == 0x8B) {
			return getINTCON();
		} else if (address == 0x81) {
			return getOPTION();
		} else if (address == 0x85) {
			return getTRISA();
		} else if (address == 0x86) {
			return getTRISB();
		} else if (address == 0x88) {
			return getEECON1();
		} else if (address == 0x89) {
			return getEECON2();
		}
		return 0x00;
	}

	public static int getTMR0() {
		return bank[TMR0];
	}

	public static void setTMR0(int tMR0) {
		bank[TMR0] = tMR0;
	}

	public static int getPCL() {
		return bank[PCL];
	}

	public static void setPCL(int pCL) {
		bank[PCL] = pCL;
	}

	public static int getSTATUS() {
		return bank[STATUS];
	}

	public static void setSTATUS(int sTATUS) {
		bank[STATUS] = sTATUS;
	}

	public static int getIRP() {
		return (getSTATUS() & 0b10000000) >>> 7;
	}

	// STATUS
	public static void setIRP(int iRP) {
		iRP = iRP | 0b01111111;
		int newSTATUS = getSTATUS();
		newSTATUS = newSTATUS & iRP;
		setSTATUS(newSTATUS);
	}

	public static int getRP1() {
		return (getSTATUS() & 0b01000000) >>> 6;
	}

	public static void setRP1(int rP1) {
		rP1 = rP1 | 0b10111111;
		int newSTATUS = getSTATUS();
		newSTATUS = newSTATUS & rP1;
		setSTATUS(newSTATUS);
	}

	public static int getRP0() {
		return (getSTATUS() & 0b00100000) >>> 5;
	}

	public static void setRP0(int rP0) {
		rP0 = rP0 | 0b11011111;
		int newSTATUS = getSTATUS();
		newSTATUS = newSTATUS & rP0;
		setSTATUS(newSTATUS);
	}

	public static int getTO() {
		return (getSTATUS() & 0b00010000) >>> 4;
	}

	public static void setTO(int tO) {
		tO = tO | 0b11101111;
		int newSTATUS = getSTATUS();
		newSTATUS = newSTATUS & tO;
		setSTATUS(newSTATUS);
	}

	public static int getPD() {
		return (getSTATUS() & 0b00001000) >>> 3;
	}

	public static void setPD(int pD) {
		pD = pD | 0b11110111;
		int newSTATUS = getSTATUS();
		newSTATUS = newSTATUS & pD;
		setSTATUS(newSTATUS);
	}

	public static int getZ() {
		return (getSTATUS() & 0b00000100) >>> 2;
	}

	public static void setZ(int z) {
		z = z | 0b11111011;
		int newSTATUS = getSTATUS();
		newSTATUS = newSTATUS & z;
		setSTATUS(newSTATUS);
	}

	// check zero flag
	public static void checkZ(int i) {
		if (i == 0) {
			setZ(1);
		}
		setZ(0);
	}

	public static int getDC() {
		return (getSTATUS() & 0b00000010) >>> 1;
	}

	public static void setDC(int dC) {
		dC = dC | 0b11111101;
		int newSTATUS = getSTATUS();
		newSTATUS = newSTATUS & dC;
		setSTATUS(newSTATUS);
	}

	public static int getC() {
		return getSTATUS() & 0b00000010;
	}

	public static void setC(int c) {
		c = c | 0b11111110;
		int newSTATUS = getSTATUS();
		newSTATUS = newSTATUS & c;
		setSTATUS(newSTATUS);
	}

	public static int getFSR() {
		return bank[FSR];
	}

	public static void setFSR(int fSR) {
		bank[FSR] = fSR;
	}

	public static int getPORTA() {
		return bank[PORTA];
	}

	public static void setPORTA(int pORTA) {
		bank[PORTA] = pORTA;
	}

	// or getT0CKI
	public static int getRA4() {
		return (getPORTA() & 0b00010000) >>> 4;
	}

	public static void setRA4(int rA4) {
		rA4 = rA4 | 0b11101111;
		int newPORTA = getPORTA();
		newPORTA = newPORTA & rA4;
		setPORTA(newPORTA);
	}

	public static int getRA3() {
		return (getPORTA() & 0b00001000) >>> 3;
	}

	public static void setRA3(int rA3) {
		rA3 = rA3 | 0b11110111;
		int newPORTA = getPORTA();
		newPORTA = newPORTA & rA3;
		setPORTA(newPORTA);
	}

	public static int getRA2() {
		return (getPORTA() & 0b00000100) >>> 2;
	}

	public static void setRA2(int rA2) {
		rA2 = rA2 | 0b11111011;
		int newPORTA = getPORTA();
		newPORTA = newPORTA & rA2;
		setPORTA(newPORTA);
	}

	public static int getRA1() {
		return (getPORTA() & 0b00000010) >>> 1;
	}

	public static void setRA1(int rA1) {
		rA1 = rA1 | 0b11111101;
		int newPORTA = getPORTA();
		newPORTA = newPORTA & rA1;
		setPORTA(newPORTA);
	}

	public static int getRA0() {
		return getPORTA() & 0b00000001;
	}

	public static void setRA0(int rA0) {
		rA0 = rA0 | 0b11111110;
		int newPORTA = getPORTA();
		newPORTA = newPORTA & rA0;
		setPORTA(newPORTA);
	}

	public static int getPORTB() {
		return bank[PORTB];
	}

	public static void setPORTB(int pORTB) {
		bank[PORTB] = pORTB;
	}

	public static int getRB7() {
		return (getPORTB() & 0b10000000) >>> 7;
	}

	public static void setRB7(int rB7) {
		rB7 = rB7 | 0b01111111;
		int newPORTB = getPORTB();
		newPORTB = newPORTB & rB7;
		setPORTB(newPORTB);
	}

	public static int getRB6() {
		return (getPORTB() & 0b01000000) >>> 6;
	}

	public static void setRB6(int rB6) {
		rB6 = rB6 | 0b10111111;
		int newPORTB = getPORTB();
		newPORTB = newPORTB & rB6;
		setPORTB(newPORTB);
	}

	public static int getRB5() {
		return (getPORTB() & 0b00100000) >>> 5;
	}

	public static void setRB5(int rB5) {
		rB5 = rB5 | 0b11011111;
		int newPORTB = getPORTB();
		newPORTB = newPORTB & rB5;
		setPORTB(newPORTB);
	}

	public static int getRB4() {
		return (getPORTB() & 0b00010000) >>> 4;
	}

	public static void setRB4(int rB4) {
		rB4 = rB4 | 0b11101111;
		int newPORTB = getPORTB();
		newPORTB = newPORTB & rB4;
		setPORTB(newPORTB);
	}

	public static int getRB3() {
		return (getPORTB() & 0b00001000) >>> 3;
	}

	public static void setRB3(int rB3) {
		rB3 = rB3 | 0b11110111;
		int newPORTB = getPORTB();
		newPORTB = newPORTB & rB3;
		setPORTB(newPORTB);
	}

	public static int getRB2() {
		return (getPORTB() & 0b00000100) >>> 2;
	}

	public static void setRB2(int rB2) {
		rB2 = rB2 | 0b11111011;
		int newPORTB = getPORTB();
		newPORTB = newPORTB & rB2;
		setPORTB(newPORTB);
	}

	public static int getRB1() {
		return (getPORTB() & 0b00000010) >>> 1;
	}

	public static void setRB1(int rB1) {
		rB1 = rB1 | 0b11111101;
		int newPORTB = getPORTB();
		newPORTB = newPORTB & rB1;
		setPORTB(newPORTB);
	}

	// or getINT
	public static int getRB0() {
		return getPORTB() & 0b00000010;
	}

	public static void setRB0(int rB0) {
		rB0 = rB0 | 0b11111110;
		int newPORTB = getPORTB();
		newPORTB = newPORTB & rB0;
		setPORTB(newPORTB);
	}

	public static int getEEDATA() {
		return bank[EEDATA];
	}

	public static void setEEDATA(int eEDATA) {
		bank[EEDATA] = eEDATA;
	}

	public static int getEEADR() {
		return bank[EEADR];
	}

	public static void setEEADR(int eEADR) {
		bank[EEADR] = eEADR;
	}

	public static int getPCLATH() {
		return bank[PCLATH];
	}

	public static void setPCLATH(int pCLATH) {
		bank[PCLATH] = pCLATH;
	}

	public static int getINTCON() {
		return bank[INTCON];
	}

	public static void setINTCON(int iNTCON) {
		bank[INTCON] = iNTCON;
	}

	public static int getGIE() {
		return (getINTCON() & 0b10000000) >>> 7;
	}

	public static void setGIE(int gIE) {
		gIE = gIE | 0b01111111;
		int newINTCON = getINTCON();
		newINTCON = newINTCON & gIE;
		setINTCON(newINTCON);
	}

	public static int getEEIE() {
		return (getINTCON() & 0b01000000) >>> 6;
	}

	public static void setEEIE(int eEIE) {
		eEIE = eEIE | 0b10111111;
		int newINTCON = getINTCON();
		newINTCON = newINTCON & eEIE;
		setINTCON(newINTCON);
	}

	public static int getT0IE() {
		return (getINTCON() & 0b00100000) >>> 5;
	}

	public static void setT0IE(int t0IE) {
		t0IE = t0IE | 0b11011111;
		int newINTCON = getINTCON();
		newINTCON = newINTCON & t0IE;
		setINTCON(newINTCON);
	}

	public static int getINTE() {
		return (getINTCON() & 0b00010000) >>> 4;
	}

	public static void setINTE(int iNTE) {
		iNTE = iNTE | 0b11101111;
		int newINTCON = getINTCON();
		newINTCON = newINTCON & iNTE;
		setINTCON(newINTCON);
	}

	public static int getRBIE() {
		return (getINTCON() & 0b00001000) >>> 3;
	}

	public static void setRBIE(int rBIE) {
		rBIE = rBIE | 0b11110111;
		int newINTCON = getINTCON();
		newINTCON = newINTCON & rBIE;
		setINTCON(newINTCON);
	}

	public static int getT0IF() {
		return (getINTCON() & 0b00000100) >>> 2;
	}

	public static void setT0IF(int t0IF) {
		t0IF = t0IF | 0b11111011;
		int newINTCON = getINTCON();
		newINTCON = newINTCON & t0IF;
		setINTCON(newINTCON);
	}

	public static int getINTF() {
		return (getINTCON() & 0b00000010) >>> 1;
	}

	public static void setINTF(int iNTF) {
		iNTF = iNTF | 0b11111101;
		int newINTCON = getINTCON();
		newINTCON = newINTCON & iNTF;
		setINTCON(newINTCON);
	}

	public static int getRBIF() {
		return getINTCON() & 0b00000001;
	}

	public static void setRBIF(int rBIF) {
		rBIF = rBIF | 0b11111110;
		int newINTCON = getINTCON();
		newINTCON = newINTCON & rBIF;
		setINTCON(newINTCON);
	}

	public static int getOPTION() {
		return bank[OPTION];
	}

	public static void setOPTION(int oPTION) {
		bank[OPTION] = oPTION;
	}

	public static int getRBPU() {
		return (getOPTION() & 0b10000000) >>> 7;
	}

	public static void setRBPU(int rBPU) {
		rBPU = rBPU | 0b01111111;
		int newOPTION = getOPTION();
		newOPTION = newOPTION & rBPU;
		setOPTION(newOPTION);
	}

	public static int getINTEDG() {
		return (getOPTION() & 0b01000000) >>> 6;
	}

	public static void setINTEDG(int iNTEDG) {
		iNTEDG = iNTEDG | 0b10111111;
		int newOPTION = getOPTION();
		newOPTION = newOPTION & iNTEDG;
		setOPTION(newOPTION);
	}

	public static int getT0CS() {
		return (getOPTION() & 0b00100000) >>> 5;
	}

	public static void setT0CS(int t0CS) {
		t0CS = t0CS | 0b11011111;
		int newOPTION = getOPTION();
		newOPTION = newOPTION & t0CS;
		setOPTION(newOPTION);
	}

	public static int getT0SE() {
		return (getOPTION() & 0b00010000) >>> 4;
	}

	public static void setT0SE(int t0SE) {
		t0SE = t0SE | 0b11101111;
		int newOPTION = getOPTION();
		newOPTION = newOPTION & t0SE;
		setOPTION(newOPTION);
	}

	public static int getPSA() {
		return (getOPTION() & 0b00001000) >>> 3;
	}

	public static void setPSA(int pSA) {
		pSA = pSA | 0b11110111;
		int newOPTION = getOPTION();
		newOPTION = newOPTION & pSA;
		setOPTION(newOPTION);
	}

	public static int getPS2() {
		return (getOPTION() & 0b00000100) >>> 2;
	}

	public static void setPS2(int pS2) {
		pS2 = pS2 | 0b11111011;
		int newOPTION = getOPTION();
		newOPTION = newOPTION & pS2;
		setOPTION(newOPTION);
	}

	public static int getPS1() {
		return (getOPTION() & 0b00000010) >>> 1;
	}

	public static void setPS1(int pS1) {
		pS1 = pS1 | 0b11111101;
		int newOPTION = getOPTION();
		newOPTION = newOPTION & pS1;
		setOPTION(newOPTION);
	}

	public static int getPS0() {
		return getOPTION() & 0b00000001;
	}

	public static void setPS0(int pS0) {
		pS0 = pS0 | 0b11111110;
		int newOPTION = getOPTION();
		newOPTION = newOPTION & pS0;
		setOPTION(newOPTION);
	}

	public static int getTRISA() {
		return bank[TRISA];
	}

	public static void setTRISA(int tRISA) {
		bank[TRISA] = tRISA;
	}

	public static int getTRISB() {
		return bank[TRISB];
	}

	public static void setTRISB(int tRISB) {
		bank[TRISB] = tRISB;
	}

	public static int getEECON1() {
		return bank[EECON1];
	}

	public static void setEECON1(int eECON1) {
		bank[EECON1] = eECON1;
	}

	public static int getEEIF() {
		return (getEECON1() & 0b00010000) >>> 4;
	}

	public static void setEEIF(int eEIF) {
		eEIF = eEIF | 0b11101111;
		int newEECON1 = getEECON1();
		newEECON1 = newEECON1 & eEIF;
		setEECON1(newEECON1);
	}

	public static int getWRERR() {
		return (getEECON1() & 0b00001000) >>> 3;
	}

	public static void setWRERR(int wRERR) {
		wRERR = wRERR | 0b11110111;
		int newEECON1 = getEECON1();
		newEECON1 = newEECON1 & wRERR;
		setEECON1(newEECON1);
	}

	public static int getWREN() {
		return (getEECON1() & 0b00000100) >>> 2;
	}

	public static void setWREN(int wREN) {
		wREN = wREN | 0b11111011;
		int newEECON1 = getEECON1();
		newEECON1 = newEECON1 & wREN;
		setEECON1(newEECON1);
	}

	public static int getWR() {
		return (getEECON1() & 0b00000010) >>> 1;
	}

	public static void setWR(int wR) {
		wR = wR | 0b11111101;
		int newEECON1 = getEECON1();
		newEECON1 = newEECON1 & wR;
		setEECON1(newEECON1);
	}

	public static int getRD() {
		return getEECON1() & 0b00000001;
	}

	public static void setRD(int rD) {
		rD = rD | 0b11111110;
		int newEECON1 = getEECON1();
		newEECON1 = newEECON1 & rD;
		setEECON1(newEECON1);
	}

	public static int getEECON2() {
		return bank[EECON2];
	}

	public static void setEECON2(int eECON2) {
		bank[EECON2] = eECON2;
	}

	// set w register
	public static void setW(int w) {
		// TODO Auto-generated method stub

	}

	public static int getW() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int getRegisterContent(int f) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void setRegister(int d, int content, int f) {
		if (d == 0) {
			setW(content);
		} else {
			// store content in register f
			setRegisterContent(content, f);
		}

	}

	public static void setRegisterContent(int fContent, int f) {
		bank[f] = fContent;
	}

}
