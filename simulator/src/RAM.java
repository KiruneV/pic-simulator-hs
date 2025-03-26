/***
 * main:
 * @author Moritz
 * implementaion of fixes related to the coderunner/pc:
 * @author kkraemer
 * 
 */
public class RAM {

	static int TMR0 = 0x01, PCL = 0x02, STATUS = 0x03, FSR = 0x04, PORTA = 0x05, PORTB = 0x06, EEDATA = 0x08,
			EEADR = 0x09, PCLATH = 0x0A, INTCON = 0x0B, OPTION = 0x81, TRISA = 0x85, TRISB = 0x86, EECON1 = 0x88,
			EECON2 = 0x89;
	static int bank[] = new int[256];
	static int w;
	public static int PC;

	public RAM() {
		for (int i = 0; i < bank.length; i++) {
			bank[i] = 0;
		}
		w=0;
		
		PC=0;
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

	/**
	 * 
	 */
	public static void resetRAM() {
		for (int i = 0; i < bank.length; i++) {
			bank[i] = 0;
		}
		globalthings.stack8.clear();
		globalthings.tacktVT=0;
		globalthings.cycle=0;
		globalthings.timePassed=0;
		globalthings.jumpPerformed=false;
		globalthings.callPerformed=false;
		globalthings.GOTOPerformed=false;
		globalthings.started=false;
		globalthings.prescaler=0;
		globalthings.pcact=0;
		w=0;
		PC=0;
//		// bank 0 (00h - 7Fh)
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


	public static int getTMR0() {
		return bank[TMR0];
	}

	public static void setTMR0(int tMR0) {
		globalthings.timerstop=2;
		
		bank[TMR0] = tMR0;
	}
	
	/**
	 * increment timer with prescaler
	 * @author johannes
	 */
	public static void inctimmer() {
		//		without prescaler
		//		int timer =  RAM.getTMR0();
		//		
		//			if(timer >=  0xFF) {
		//                int intcon = RAM.getINTCON();
		//                intcon =  (intcon | 0b00000100);
		//                RAM.setINTCON(intcon);
		//                timer=timer%0xFF;
		//            }else {
		//            	timer++;
		//            }
		//		
		//		RAM.setTMR0(timer);

		int timer = RAM.getTMR0();
		
		int PSA=RAM.getPSA();
		if (PSA == 0) {
			// prescaler is active
			int ps20 = RAM.getOPTION() & 0b00000111;
			int prescalermax = 2 << ps20;
			globalthings.prescaler++;
			if (globalthings.prescaler < prescalermax) {
				return;
			}

		}


		if(globalthings.timerstop>0) {
			globalthings.timerstop--;
			return;
		}
		
		
		if (timer >= 0xFF) {
			int intcon = RAM.getINTCON();
			intcon |= 0b00000100;
			timer %= 0xFF;
			RAM.setINTCON(intcon);
			RAM.setT0IF(1);
			RAM.setTMR0(timer);
			if(PSA==0) {
				globalthings.prescaler = 0;
			}
			//return;
		} else {
			timer++;
			if(PSA==0) {
				globalthings.prescaler = 0;
			}
		}
		
		//RAM.setTMR0(timer);
		bank[TMR0] = timer;
		return;
	}

	public static int getPCL() {
		return bank[PCL];
	}

	public static void setPCL(int pCL) {
		int temp=pCL;
		
		int temppcLback=temp&0b11111111;
		bank[PCL] = temppcLback;
		RAM.PC=temppcLback|((RAM.getPCLATH()& 0b00011111) << 8);
		
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

	public static void setIRP(int iRP) {
		int newSTATUS = getSTATUS();
		if (iRP == 0) {
			newSTATUS = newSTATUS & 0b01111111;
		} else {
			newSTATUS = newSTATUS | 0b10000000;
		}
		setSTATUS(newSTATUS);
	}

	public static int getRP1() {
		return (getSTATUS() & 0b01000000) >>> 6;
	}

	public static void setRP1(int rP1) {
		int newSTATUS = getSTATUS();
		if (rP1 == 0) {
			newSTATUS = newSTATUS & 0b10111111;
		} else {
			newSTATUS = newSTATUS | 0b01000000;
		}
		setSTATUS(newSTATUS);
	}

	public static int getRP0() {
		return (getSTATUS() & 0b00100000) >>> 5;
	}

	public static void setRP0(int rP0) {
		int newSTATUS = getSTATUS();
		if (rP0 == 0) {
			newSTATUS = newSTATUS & 0b11011111;
		} else {
			newSTATUS = newSTATUS | 0b00100000;
		}
		setSTATUS(newSTATUS);
	}

	public static int getTO() {
		return (getSTATUS() & 0b00010000) >>> 4;
	}

	public static void setTO(int tO) {
		tO = tO | 0b11101111;
		int newSTATUS = getSTATUS();
		if (tO == 0) {
			newSTATUS = newSTATUS & 0b11101111;
		} else {
			newSTATUS = newSTATUS | 0b00010000;
		}
		setSTATUS(newSTATUS);
	}

	public static int getPD() {
		return (getSTATUS() & 0b00001000) >>> 3;
	}

	public static void setPD(int pD) {
		int newSTATUS = getSTATUS();
		if (pD == 0) {
			newSTATUS = newSTATUS & 0b11110111;
		} else {
			newSTATUS = newSTATUS | 0b00001000;
		}
		setSTATUS(newSTATUS);
	}

	public static int getZ() {
		return (getSTATUS() & 0b00000100) >>> 2;
	}

	public static void setZ(int z) {
		int newSTATUS = getSTATUS();
		if (z == 0) {
			newSTATUS = newSTATUS & 0b11111011;
		} else {
			newSTATUS = newSTATUS | 0b00000100;
		}
		setSTATUS(newSTATUS);
	}

	public static void checkZ(int i) {
		if (i == 0) {
			setZ(1);
		} else {
			setZ(0);
		}
	}

	public static int getDC() {
		return (getSTATUS() & 0b00000010) >>> 1;
	}

	public static void setDC(int dC) {
		int newSTATUS = getSTATUS();
		if (dC == 0) {
			newSTATUS = newSTATUS & 0b11111101;
		} else {
			newSTATUS = newSTATUS | 0b00000010;
		}
		setSTATUS(newSTATUS);
	}

	public static int getC() {
		return getSTATUS() & 0b00000001;
	}

	public static void setC(int c) {
		int newSTATUS = getSTATUS();
		if (c == 0) {
			newSTATUS = newSTATUS & 0b11111110;
		} else {
			newSTATUS = newSTATUS | 0b00000001;
		}
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
		int newPORTA = getPORTA();
		if (rA4 == 0) {
			newPORTA = newPORTA & 0b11101111;
		} else {
			newPORTA = newPORTA | 0b00010000;
		}
		setPORTA(newPORTA);
	}

	public static int getRA3() {
		return (getPORTA() & 0b00001000) >>> 3;
	}

	public static void setRA3(int rA3) {
		int newPORTA = getPORTA();
		if (rA3 == 0) {
			newPORTA = newPORTA & 0b11110111;
		} else {
			newPORTA = newPORTA | 0b00001000;
		}
		setPORTA(newPORTA);
	}

	public static int getRA2() {
		return (getPORTA() & 0b00000100) >>> 2;
	}

	public static void setRA2(int rA2) {
		int newPORTA = getPORTA();
		if (rA2 == 0) {
			newPORTA = newPORTA & 0b11111011;
		} else {
			newPORTA = newPORTA | 0b00000100;
		}
		setPORTA(newPORTA);
	}

	public static int getRA1() {
		return (getPORTA() & 0b00000010) >>> 1;
	}

	public static void setRA1(int rA1) {
		int newPORTA = getPORTA();
		if (rA1 == 0) {
			newPORTA = newPORTA & 0b11111101;
		} else {
			newPORTA = newPORTA | 0b00000010;
		}
		setPORTA(newPORTA);
	}

	public static int getRA0() {
		return getPORTA() & 0b00000001;
	}

	public static void setRA0(int rA0) {
		int newPORTA = getPORTA();
		if (rA0 == 0) {
			newPORTA = newPORTA & 0b11111110;
		} else {
			newPORTA = newPORTA | 0b00000001;
		}
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
		int newPORTB = getPORTB();
		if (rB7 == 0) {
			newPORTB = newPORTB & 0b01111111;
		} else {
			newPORTB = newPORTB | 0b10000000;
		}
		setPORTB(newPORTB);
	}

	public static int getRB6() {
		return (getPORTB() & 0b01000000) >>> 6;
	}

	public static void setRB6(int rB6) {
		int newPORTB = getPORTB();
		if (rB6 == 0) {
			newPORTB = newPORTB & 0b10111111;
		} else {
			newPORTB = newPORTB | 0b01000000;
		}
		setPORTB(newPORTB);
	}

	public static int getRB5() {
		return (getPORTB() & 0b00100000) >>> 5;
	}

	public static void setRB5(int rB5) {
		int newPORTB = getPORTB();
		if (rB5 == 0) {
			newPORTB = newPORTB & 0b11011111;
		} else {
			newPORTB = newPORTB | 0b00100000;
		}
		setPORTB(newPORTB);
	}

	public static int getRB4() {
		return (getPORTB() & 0b00010000) >>> 4;
	}

	public static void setRB4(int rB4) {
		int newPORTB = getPORTB();
		if (rB4 == 0) {
			newPORTB = newPORTB & 0b11101111;
		} else {
			newPORTB = newPORTB | 0b00010000;
		}
		setPORTB(newPORTB);
	}

	public static int getRB3() {
		return (getPORTB() & 0b00001000) >>> 3;
	}

	public static void setRB3(int rB3) {
		int newPORTB = getPORTB();
		if (rB3 == 0) {
			newPORTB = newPORTB & 0b11110111;
		} else {
			newPORTB = newPORTB | 0b00001000;
		}
		setPORTB(newPORTB);
	}

	public static int getRB2() {
		return (getPORTB() & 0b00000100) >>> 2;
	}

	public static void setRB2(int rB2) {
		int newPORTB = getPORTB();
		if (rB2 == 0) {
			newPORTB = newPORTB & 0b11111011;
		} else {
			newPORTB = newPORTB | 0b00000100;
		}
		setPORTB(newPORTB);
	}

	public static int getRB1() {
		return (getPORTB() & 0b00000010) >>> 1;
	}

	public static void setRB1(int rB1) {
		int newPORTB = getPORTB();
		if (rB1 == 0) {
			newPORTB = newPORTB & 0b11111101;
		} else {
			newPORTB = newPORTB | 0b00000010;
		}
		setPORTB(newPORTB);
	}

	public static int getRB0() {
		return getPORTB() & 0b00000001;
	}

	public static void setRB0(int rB0) {
		int newPORTB = getPORTB();
		if (rB0 == 0) {
			newPORTB = newPORTB & 0b11111110;
		} else {
			newPORTB = newPORTB | 0b00000001;
		}
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
		int newINTCON = getINTCON();
		if (gIE == 0) {
			newINTCON = newINTCON & 0b01111111;
		} else {
			newINTCON = newINTCON | 0b10000000;
		}
		setINTCON(newINTCON);
	}

	public static int getEEIE() {
		return (getINTCON() & 0b01000000) >>> 6;
	}

	public static void setEEIE(int eEIE) {
		int newINTCON = getINTCON();
		if (eEIE == 0) {
			newINTCON = newINTCON & 0b10111111;
		} else {
			newINTCON = newINTCON | 0b01000000;
		}
		setINTCON(newINTCON);
	}

	public static int getT0IE() {
		return (getINTCON() & 0b00100000) >>> 5;
	}

	public static void setT0IE(int t0IE) {
		int newINTCON = getINTCON();
		if (t0IE == 0) {
			newINTCON = newINTCON & 0b11011111;
		} else {
			newINTCON = newINTCON | 0b00100000;
		}
		setINTCON(newINTCON);
	}

	public static int getINTE() {
		return (getINTCON() & 0b00010000) >>> 4;
	}

	public static void setINTE(int iNTE) {
		int newINTCON = getINTCON();
		if (iNTE == 0) {
			newINTCON = newINTCON & 0b11101111;
		} else {
			newINTCON = newINTCON | 0b00010000;
		}
		setINTCON(newINTCON);
	}

	public static int getRBIE() {
		return (getINTCON() & 0b00001000) >>> 3;
	}

	public static void setRBIE(int rBIE) {
		int newINTCON = getINTCON();
		if (rBIE == 0) {
			newINTCON = newINTCON & 0b11110111;
		} else {
			newINTCON = newINTCON | 0b00001000;
		}
		setINTCON(newINTCON);
	}

	public static int getT0IF() {
		return (getINTCON() & 0b00000100) >>> 2;
	}

	public static void setT0IF(int t0IF) {
		int newINTCON = getINTCON();
		if (t0IF == 0) {
			newINTCON = newINTCON & 0b11111011;
		} else {
			newINTCON = newINTCON | 0b00000100;
		}
		setINTCON(newINTCON);
	}

	public static int getINTF() {
		return (getINTCON() & 0b00000010) >>> 1;
	}

	public static void setINTF(int iNTF) {
		int newINTCON = getINTCON();
		if (iNTF == 0) {
			newINTCON = newINTCON & 0b11111101;
		} else {
			newINTCON = newINTCON | 0b00000010;
		}
		setINTCON(newINTCON);
	}

	public static int getRBIF() {
		return getINTCON() & 0b00000001;
	}

	public static void setRBIF(int rBIF) {
		int newINTCON = getINTCON();
		if (rBIF == 0) {
			newINTCON = newINTCON & 0b11111110;
		} else {
			newINTCON = newINTCON | 0b00000001;
		}
		setINTCON(newINTCON);
	}

	public static int getOPTION() {
		return bank[OPTION];
	}

	public static void setOPTION(int oPTION) {
		globalthings.prescaler=0;
		bank[OPTION] = oPTION;
	}

	public static int getRBPU() {
		return (getOPTION() & 0b10000000) >>> 7;
	}

	public static void setRBPU(int rBPU) {
		int newOPTION = getOPTION();
		if (rBPU == 0) {
			newOPTION = newOPTION & 0b01111111;
		} else {
			newOPTION = newOPTION | 0b10000000;
		}
		setOPTION(newOPTION);
	}

	public static int getINTEDG() {
		return (getOPTION() & 0b01000000) >>> 6;
	}

	public static void setINTEDG(int iNTEDG) {
		int newOPTION = getOPTION();
		if (iNTEDG == 0) {
			newOPTION = newOPTION & 0b10111111;
		} else {
			newOPTION = newOPTION | 0b01000000;
		}
		setOPTION(newOPTION);
	}

	public static int getT0CS() {
		return (getOPTION() & 0b00100000) >>> 5;
	}

	public static void setT0CS(int t0CS) {
		int newOPTION = getOPTION();
		if (t0CS == 0) {
			newOPTION = newOPTION & 0b11011111;
		} else {
			newOPTION = newOPTION | 0b00100000;
		}
		setOPTION(newOPTION);
	}

	public static int getT0SE() {
		return (getOPTION() & 0b00010000) >>> 4;
	}

	public static void setT0SE(int t0SE) {
		int newOPTION = getOPTION();
		if (t0SE == 0) {
			newOPTION = newOPTION & 0b11101111;
		} else {
			newOPTION = newOPTION | 0b00010000;
		}
		setOPTION(newOPTION);
	}

	public static int getPSA() {
		return (getOPTION() & 0b00001000) >>> 3;
	}

	public static void setPSA(int pSA) {
		int newOPTION = getOPTION();
		if (pSA == 0) {
			newOPTION = newOPTION & 0b11110111;
		} else {
			newOPTION = newOPTION | 0b00001000;
		}
		setOPTION(newOPTION);
	}

	public static int getPS2() {
		return (getOPTION() & 0b00000100) >>> 2;
	}

	public static void setPS2(int pS2) {
		int newOPTION = getOPTION();
		if (pS2 == 0) {
			newOPTION = newOPTION & 0b11111011;
		} else {
			newOPTION = newOPTION | 0b00000100;
		}
		setOPTION(newOPTION);
	}

	public static int getPS1() {
		return (getOPTION() & 0b00000010) >>> 1;
	}

	public static void setPS1(int pS1) {
		int newOPTION = getOPTION();
		if (pS1 == 0) {
			newOPTION = newOPTION & 0b11111101;
		} else {
			newOPTION = newOPTION | 0b00000010;
		}
		setOPTION(newOPTION);
	}

	public static int getPS0() {
		return getOPTION() & 0b00000001;
	}

	public static void setPS0(int pS0) {
		int newOPTION = getOPTION();
		if (pS0 == 0) {
			newOPTION = newOPTION & 0b11111110;
		} else {
			newOPTION = newOPTION | 0b00000001;
		}
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
		if (eEIF == 0) {
			newEECON1 = newEECON1 & 0b11101111;
		} else {
			newEECON1 = newEECON1 | 0b00010000;
		}
		setEECON1(newEECON1);
	}

	public static int getWRERR() {
		return (getEECON1() & 0b00001000) >>> 3;
	}

	public static void setWRERR(int wRERR) {
		int newEECON1 = getEECON1();
		if (wRERR == 0) {
			newEECON1 = newEECON1 & 0b11110111;
		} else {
			newEECON1 = newEECON1 | 0b00001000;
		}
		setEECON1(newEECON1);
	}

	public static int getWREN() {
		return (getEECON1() & 0b00000100) >>> 2;
	}

	public static void setWREN(int wREN) {
		int newEECON1 = getEECON1();
		if (wREN == 0) {
			newEECON1 = newEECON1 & 0b11111011;
		} else {
			newEECON1 = newEECON1 | 0b00000100;
		}
		setEECON1(newEECON1);
	}

	public static int getWR() {
		return (getEECON1() & 0b00000010) >>> 1;
	}

	public static void setWR(int wR) {
		int newEECON1 = getEECON1();
		if (wR == 0) {
			newEECON1 = newEECON1 & 0b11111101;
		} else {
			newEECON1 = newEECON1 | 0b00000010;
		}
		setEECON1(newEECON1);
	}

	public static int getRD() {
		return getEECON1() & 0b00000001;
	}

	public static void setRD(int rD) {
		int newEECON1 = getEECON1();
		if (rD == 0) {
			newEECON1 = newEECON1 & 0b11111110;
		} else {
			newEECON1 = newEECON1 | 0b00000001;
		}
		setEECON1(newEECON1);
	}

	public static int getEECON2() {
		return bank[EECON2];
	}

	public static void setEECON2(int eECON2) {
		bank[EECON2] = eECON2;
	}

	// set w register
	public static void setW(int oW) {
		oW=checkCarry(oW);
		checkZ(oW);
		w = oW;
	}

	public static int getW() {
		return w;
	}

	/**
	 * gets value from bank
	 * @param address address in bank
	 * @return value from specified address
	 */
	public static int getRegisterContent(int address) {
		int RPO=getRP0();
		if (RPO == 1) { // access bank 1
			address += 0x80;
		}
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
		} else if (address <= 0xFF && address >= 0x00) {
			return bank[address];
		}
		return 0x00;
	}
	
	/**
	 * gets value from entire ram range
	 * @param address address in bank
	 * @return value from specified address
	 */
	public static int getRegisterfull(int address) {
		
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
		} else if (address <= 0xFF && address >= 0x00) {
			return bank[address];
		}
		return 0x00;
	}
	
	
	/**
	 * checks if a value is over 255 then sets it to that cap and sets the carrybit
	 * @param result value to be checked
	 * @return the corrected input
	 */
	public static int checkCarry(int result) {
		if (result > 0xFF) {
			RAM.setC(1);
			result = result & 0xFF;
		}
		
		
		return result;
	}

	/**
	 * sets value either in w or bank
	 * @param d if 0 sets w else bank
	 * @param content to be inserted
	 * @param f address in bank
	 */
	public static void setRegister(int d, int content, int f) {
		
		if (d == 0) {
			setW(content);
		} else {
			// store content in register f
			setRegisterContent(content, f);
		}
	}

	/**
	 * sets value in bank
	 * @param fContent to be inserted
	 * @param address address in bank
	 */
	public static void setRegisterContent(int fContent, int address) {
		if (address <= 0x7F) {
			int RPO=getRP0();
			if (RPO == 1) { // access bank 1
				address += 0x80;
			}
			if(globalthings.changeStatus) {
				fContent=checkCarry(fContent);
				checkZ(fContent);
			}
			
			if (address == 0x01) {
				setTMR0(fContent);
			} else if (address == 0x02 || address == 0x82) {
				setPCL(fContent);
			} else if (address == 0x03 || address == 0x83) {
				setSTATUS(fContent);
			} else if (address == 0x04 || address == 0x84) {
				setFSR(fContent);
			} else if (address == 0x05) {
				setPORTA(fContent);
			} else if (address == 0x06) {
				setPORTB(fContent);
			} else if (address == 0x08) {
				setEEDATA(fContent);
			} else if (address == 0x09) {
				setEEADR(fContent);
			} else if (address == 0x0A || address == 0x8A) {
				setPCLATH(fContent);
			} else if (address == 0x0B || address == 0x8B) {
				setINTCON(fContent);
			} else if (address == 0x81) {
				setOPTION(fContent);
			} else if (address == 0x85) {
				setTRISA(fContent);
			} else if (address == 0x86) {
				setTRISB(fContent);
			} else if (address == 0x88) {
				setEECON1(fContent);
			} else if (address == 0x89) {
				setEECON2(fContent);
			} else if (address <= 0xFF && address >= 0x00) {
				bank[address] = fContent;
			}else {
				System.out.println("setRegisterContent falsch");
			}
		} else {
			System.out.println("setRegisterContent falsch");
		}
	}
	
	/**
	 * sets value in bank absolute
	 * @param fContent to be inserted
	 * @param address address in bank
	 */
	public static void setRegisterContentAbs(int fContent, int address) {
		if (address <= 0x7F) {
			
			
			
			if (address == 0x01) {
				setTMR0(fContent);
			} else if (address == 0x02 || address == 0x82) {
				setPCL(fContent);
			} else if (address == 0x03 || address == 0x83) {
				setSTATUS(fContent);
			} else if (address == 0x04 || address == 0x84) {
				setFSR(fContent);
			} else if (address == 0x05) {
				setPORTA(fContent);
			} else if (address == 0x06) {
				setPORTB(fContent);
			} else if (address == 0x08) {
				setEEDATA(fContent);
			} else if (address == 0x09) {
				setEEADR(fContent);
			} else if (address == 0x0A || address == 0x8A) {
				setPCLATH(fContent);
			} else if (address == 0x0B || address == 0x8B) {
				setINTCON(fContent);
			} else if (address == 0x81) {
				setOPTION(fContent);
			} else if (address == 0x85) {
				setTRISA(fContent);
			} else if (address == 0x86) {
				setTRISB(fContent);
			} else if (address == 0x88) {
				setEECON1(fContent);
			} else if (address == 0x89) {
				setEECON2(fContent);
			} else if (address <= 0xFF && address >= 0x00) {
				bank[address] = fContent;
			}else {
				System.out.println("setRegisterContent falsch");
			}
		} else {
			System.out.println("setRegisterContent falsch");
		}
	}


}
