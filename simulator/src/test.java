import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test {

	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * 
	 * }
	 */

	@Test
	void test1() {
		decoder.DecodeStr("3011"); // movlw 11h
		assertEquals(0x11, RAM.getW());
		decoder.DecodeStr("3930"); // andlw 30h
		assertEquals(0x10, RAM.getW());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("380D"); // iorlw 0Dh
		assertEquals(0x1D, RAM.getW());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("3C3D"); // sublw 3Dh
		assertEquals(0x20, RAM.getW());
		assertEquals(1, RAM.getC());
		assertEquals(1, RAM.getDC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("3A20"); // xorlw 20h
		assertEquals(0x00, RAM.getW());
		assertEquals(1, RAM.getC());
		assertEquals(1, RAM.getDC());
		assertEquals(1, RAM.getZ());
		decoder.DecodeStr("3E25"); // addlw 35h
		assertEquals(0x25, RAM.getW());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("2806"); // goto ende
	}

	@Test
	void test2() {
		RAM.resetRAM();
		int status = RAM.getSTATUS();
		decoder.DecodeStr("3011"); // movlw 11h
		assertEquals(0x11, RAM.getW());
		assertEquals(status, RAM.getSTATUS());
		decoder.DecodeStr("2006"); // call up1
		// pcl veraendert?
		// ruecksprungadresse wird auf stack gelegt
		decoder.DecodeStr("3E25"); // addlw 25h
		assertEquals(0x36, RAM.getW());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0008"); // return
		decoder.DecodeStr("0000"); // nop
		assertEquals(0x36, RAM.getW());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("2008"); // call up2
		// pcl veraendert?
		// ruecksprungadresse wird auf stack gelegt
		// in w steht rueckgabewert
		decoder.DecodeStr("3477"); // retlw
		decoder.DecodeStr("0000"); // nop
		assertEquals(0x77, RAM.getW());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("2800"); // goto loop
		// pcl veraendert?
		decoder.DecodeStr("2809"); // goto ende
	}

	@Test
	void test3() {
		RAM.resetRAM();
		int wert1 = 0x0C;
		int wert2 = 0x0D;
		decoder.DecodeStr("3011"); // movlw 11h
		assertEquals(0x11, RAM.getW());
		decoder.DecodeStr("008C"); // movwf wert1
		assertEquals(0x11, RAM.getRegisterContent(wert1));
		decoder.DecodeStr("3014"); // movlw 14h
		assertEquals(0x14, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("070C"); // addwf wert1,w
		assertEquals(0x25, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078C"); // addwf wert1
		assertEquals(0x25, RAM.getW());
		assertEquals(0x36, RAM.getRegisterContent(wert1));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("050C"); // andwf wert1,w
		assertEquals(0x24, RAM.getW());
		assertEquals(0x36, RAM.getRegisterContent(wert1));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("008D"); // movwf wert2
		assertEquals(0x24, RAM.getW());
		assertEquals(0x36, RAM.getRegisterContent(wert1));
		assertEquals(0x24, RAM.getRegisterContent(wert2));
		decoder.DecodeStr("018C"); // clrf wert1
		assertEquals(0x24, RAM.getW());
		assertEquals(0, RAM.getRegisterContent(wert1));
		assertEquals(0x24, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(1, RAM.getZ());
		decoder.DecodeStr("090D"); // comf wert2,w
		assertEquals(0xDB, RAM.getW());
		assertEquals(0, RAM.getRegisterContent(wert1));
		assertEquals(0x24, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("030C"); // decf wert1,w
		assertEquals(0xFF, RAM.getW());
		assertEquals(0, RAM.getRegisterContent(wert1));
		assertEquals(0x24, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0A8D"); // incf wert2
		assertEquals(0xFF, RAM.getW());
		assertEquals(0, RAM.getRegisterContent(wert1));
		assertEquals(0x25, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("088C"); // movf wert1
		assertEquals(0xFF, RAM.getW());
		assertEquals(0, RAM.getRegisterContent(wert1));
		assertEquals(0x25, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(1, RAM.getZ());
		decoder.DecodeStr("048C"); // iorwf wert1
		assertEquals(0xFF, RAM.getW());
		assertEquals(0xFF, RAM.getRegisterContent(wert1));
		assertEquals(0x25, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("020D"); // subwf wert2,w
		assertEquals(0x26, RAM.getW());
		assertEquals(0xFF, RAM.getRegisterContent(wert1));
		assertEquals(0x25, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0E8D"); // swapf wert2
		assertEquals(0x26, RAM.getW());
		assertEquals(0xFF, RAM.getRegisterContent(wert1));
		assertEquals(0x52, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("068C"); // xorwf wert1
		assertEquals(0x26, RAM.getW());
		assertEquals(0xD9, RAM.getRegisterContent(wert1));
		assertEquals(0x52, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0100"); // clrw
		assertEquals(0x00, RAM.getW());
		assertEquals(0xD9, RAM.getRegisterContent(wert1));
		assertEquals(0x52, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(1, RAM.getZ());

		decoder.DecodeStr("020C"); // subwf wert1,w
		assertEquals(0xD9, RAM.getW());
		assertEquals(0xD9, RAM.getRegisterContent(wert1));
		assertEquals(0x52, RAM.getRegisterContent(wert2));
		// assertEquals(1, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("020D"); // subwf wert2,w
		assertEquals(0x79, RAM.getW());
		assertEquals(0xD9, RAM.getRegisterContent(wert1));
		assertEquals(0x52, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("028D"); // subwf wert2
		assertEquals(0x79, RAM.getW());
		assertEquals(0xD9, RAM.getRegisterContent(wert1));
		assertEquals(0xD9, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("028D"); // subwf wert2
		assertEquals(0x79, RAM.getW());
		assertEquals(0xD9, RAM.getRegisterContent(wert1));
		assertEquals(0x60, RAM.getRegisterContent(wert2));
		assertEquals(1, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("2815"); // goto ende
	}

	@Test
	void test4() {
		int wert1 = 0x0C;
		int wert2 = 0x0D;
		decoder.DecodeStr("3011"); // movlw 11h
		assertEquals(0x11, RAM.getW());
		decoder.DecodeStr("008C"); // movwf wert1
		assertEquals(0x11, RAM.getRegisterContent(wert1));
		decoder.DecodeStr("3E11"); // addlw 11h
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0D8C"); // rlf wert1
		assertEquals(0x22, RAM.getW());
		assertEquals(0x22, RAM.getRegisterContent(wert1));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0D8C"); // rlf wert1
		assertEquals(0x22, RAM.getW());
		assertEquals(0x44, RAM.getRegisterContent(wert1));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0D8C"); // rlf wert1
		assertEquals(0x22, RAM.getW());
		assertEquals(0x88, RAM.getRegisterContent(wert1));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0D0C"); // rlf wert1,w
		assertEquals(0x10, RAM.getW());
		assertEquals(0x88, RAM.getRegisterContent(wert1));
		assertEquals(0, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0D8C"); // rlf wert1
		assertEquals(0x10, RAM.getW());
		assertEquals(0x11, RAM.getRegisterContent(wert1));
		assertEquals(0, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0D0C"); // rlf wert1,w
		assertEquals(0x23, RAM.getW());
		assertEquals(0x11, RAM.getRegisterContent(wert1));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0C8C"); // rrf wert1
		assertEquals(0x23, RAM.getW());
		assertEquals(0x08, RAM.getRegisterContent(wert1));
		assertEquals(0, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("008D"); // movwf wert2
		assertEquals(0x23, RAM.getW());
		assertEquals(0x08, RAM.getRegisterContent(wert1));
		assertEquals(0x23, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0C8D"); // rrf wert2
		assertEquals(0x23, RAM.getW());
		assertEquals(0x08, RAM.getRegisterContent(wert1));
		assertEquals(0x91, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0C0D"); // rrf wert2,w
		assertEquals(0xC8, RAM.getW());
		assertEquals(0x08, RAM.getRegisterContent(wert1));
		assertEquals(0x91, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());

		decoder.DecodeStr("3009"); // movlw 9
		assertEquals(0x09, RAM.getW());
		assertEquals(0x08, RAM.getRegisterContent(wert1));
		assertEquals(0x91, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("008C"); // movwf wert1
		assertEquals(0x09, RAM.getW());
		assertEquals(0x09, RAM.getRegisterContent(wert1));
		assertEquals(0x91, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0100"); // clrw
		assertEquals(0x00, RAM.getW());
		assertEquals(0x09, RAM.getRegisterContent(wert1));
		assertEquals(0x91, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(1, RAM.getC());
		assertEquals(1, RAM.getZ());
		
		// loop1
		decoder.DecodeStr("3E01"); // addlw 1
		assertEquals(0x01, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078D"); // addwf wert2
		assertEquals(0x92, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0B8C"); // decfsz wert1
		assertEquals(0x08, RAM.getRegisterContent(wert1));
		// decoder.DecodeStr("2810"); // goto loop1
		decoder.DecodeStr("3E01"); // addlw 1
		assertEquals(0x02, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078D"); // addwf wert2
		assertEquals(0x94, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0B8C"); // decfsz wert1
		assertEquals(0x07, RAM.getRegisterContent(wert1));
		decoder.DecodeStr("3E01"); // addlw 1
		assertEquals(0x03, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078D"); // addwf wert2
		assertEquals(0x97, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0B8C"); // decfsz wert1
		assertEquals(0x06, RAM.getRegisterContent(wert1));
		decoder.DecodeStr("3E01"); // addlw 1
		assertEquals(0x04, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078D"); // addwf wert2
		assertEquals(0x9B, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0B8C"); // decfsz wert1
		assertEquals(0x05, RAM.getRegisterContent(wert1));
		decoder.DecodeStr("3E01"); // addlw 1
		assertEquals(0x05, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078D"); // addwf wert2
		assertEquals(0xA0, RAM.getRegisterContent(wert2));
		//assertEquals(1, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0B8C"); // decfsz wert1
		assertEquals(0x04, RAM.getRegisterContent(wert1));
		decoder.DecodeStr("3E01"); // addlw 1
		assertEquals(0x06, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078D"); // addwf wert2
		assertEquals(0xA6, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0B8C"); // decfsz wert1
		assertEquals(0x03, RAM.getRegisterContent(wert1));
		decoder.DecodeStr("3E01"); // addlw 1
		assertEquals(0x07, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078D"); // addwf wert2
		assertEquals(0xAD, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0B8C"); // decfsz wert1
		assertEquals(0x02, RAM.getRegisterContent(wert1));
		decoder.DecodeStr("3E01"); // addlw 1
		assertEquals(0x08, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078D"); // addwf wert2
		assertEquals(0xB5, RAM.getRegisterContent(wert2));
		assertEquals(1, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0B8C"); // decfsz wert1
		assertEquals(0x01, RAM.getRegisterContent(wert1));
		decoder.DecodeStr("3E01"); // addlw 1
		assertEquals(0x09, RAM.getW());
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("078D"); // addwf wert2
		assertEquals(0xBE, RAM.getRegisterContent(wert2));
		assertEquals(0, RAM.getDC());
		assertEquals(0, RAM.getC());
		assertEquals(0, RAM.getZ());
		decoder.DecodeStr("0B8C"); // decfsz wert1
		assertEquals(0x00, RAM.getRegisterContent(wert1));
		
		decoder.DecodeStr("30F0"); // movlw 0f0h

		decoder.DecodeStr("008C"); // movwf wert1

		decoder.DecodeStr("018D"); // clrf wert2

		decoder.DecodeStr("0100"); // clrw

		// loop2
		decoder.DecodeStr("070C"); // addwf wert1,w

		decoder.DecodeStr("0A8D"); // incf wert2

		decoder.DecodeStr("0F8C"); // incfsz wert1
		
		// decoder.DecodeStr("2818"); // goto loop2

		decoder.DecodeStr("281C"); // goto ende
		
	}

}
