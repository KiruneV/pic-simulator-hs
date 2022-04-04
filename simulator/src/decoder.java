/**
 * 
 */

/**
 * @author jkraemer
 *
 */
public class decoder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecodeStr("FFF");
	}
	public static void DecodeStr(String inputStr) {
		String hexString="0x"+inputStr;
		int hexInt=Integer.decode(hexString);
		DecodeInt(hexInt);
		//System.out.println(Integer.toBinaryString(hexInt));
		//System.out.println(hexInt);
	}
	public static void DecodeInt(int inputInt) {
		int hauptmaske=0x7000;
		final int byteOriented=0b00000000000000;//00
		final int bitOriented=0b01000000000000;//01
		final int control=0b10000000000000;//10
		final int literal=0b11000000000000;//11
		//final int CLRWDT=0xFFFF;
		int hauptmaskeUndInput=hauptmaske&inputInt;
		switch (hauptmaskeUndInput) {
		case byteOriented:
			Decode00(inputInt);
			break;
		case bitOriented:
			Decode01(inputInt);
			break;
		case control:
			Decode10(inputInt);
			break;
		case literal:
			Decode11(inputInt);
			break;

		default:
			break;
		}
	}

	public static void Decode00(int inputInt) {
		final int CLRWDT=0b1100100;
		final int RETFIE=0b1001;
		final int RETURN=0b1000;
		final int SLEEP=0b01100011;
		switch (inputInt) {
		case CLRWDT:
			ByteOrientedOperations.CLRWDT();
			break;
		case RETFIE:
			ByteOrientedOperations.RETFIE();
			break;
		case RETURN:
			ByteOrientedOperations.RETURN();
			break;
		case SLEEP:
			ByteOrientedOperations.SLEEP();
			break;

		default:
			final int maske00=0xF00;

			final int ADDWF=0x700;
			final int ANDWF=0x500;
			final int CLRF_CLRW=0x100;
			final int _distinction=0x80;
			final int COMF=0x900;
			final int DECF=0x300;
			final int DECFSZ=0xB00;
			final int INCF=0xA00;
			final int INCFSZ=0xF00;
			final int IORWF=0x400;
			final int MOVF=0x800;
			final int MOVWF_NOP=0x0;
			final int RLF=0xD00;
			final int RRF=0xC00;
			final int SUBWF=0x200;
			final int SWAPF=0xE00;
			final int XORWF=0x600;

			switch (inputInt&maske00) {
			case ADDWF:
				ByteOrientedOperations.ADDWF(inputInt);
				break;
			case ANDWF:
				ByteOrientedOperations.ANDWF(inputInt);
				break;
			case CLRF_CLRW:
				if((inputInt&_distinction)==_distinction) {
					//1 CLRF
					ByteOrientedOperations.CLRF(inputInt);
				}else {
					//0 CLRW
					ByteOrientedOperations.CLRW();
				}
				break;
			case COMF:
				ByteOrientedOperations.COMF(inputInt);
				break;
			case DECF:
				ByteOrientedOperations.DECF(inputInt);
				break;
			case DECFSZ:
				ByteOrientedOperations.DECFSZ(inputInt);
				break;
			case INCF:
				ByteOrientedOperations.INCF(inputInt);
				break;
			case INCFSZ:
				ByteOrientedOperations.INCFSZ(inputInt);
				break;
			case IORWF:
				ByteOrientedOperations.IORWF(inputInt);
				break;
			case MOVF:
				ByteOrientedOperations.MOVF(inputInt);
				break;
			case MOVWF_NOP:
				if((inputInt&_distinction)==_distinction) {
					//1 MOVWF
					ByteOrientedOperations.MOVWF(inputInt);
				}else {
					//0 NOP
					ByteOrientedOperations.NOP();
				}
				break;
			case RLF:
				ByteOrientedOperations.RLF(inputInt);
				break;
			case RRF:
				ByteOrientedOperations.RRF(inputInt);
				break;
			case SUBWF:
				ByteOrientedOperations.SUBWF(inputInt);
				break;
			case SWAPF:
				ByteOrientedOperations.SWAPF(inputInt);
				break;
			case XORWF:
				ByteOrientedOperations.XORWF(inputInt);
				break;
			default:
				//System.out.println("ERROR no valid code");
				throw new Error("ERROR no valid code");
			}
			break;
		}
	}

	public static void Decode01(int inputInt) {

		final int maske01=0xC00;
		final int BCF=0x0;
		final int BSF=0x400;
		final int BTFSC=0x800;
		final int BTFSS=maske01;
		switch (inputInt&maske01) {
		case BCF:
			BitOrientedOperations.BCF(inputInt);
			break;
		case BSF:
			BitOrientedOperations.BSF(inputInt);
			break;
		case BTFSC:
			BitOrientedOperations.BTFSC(inputInt);
			break;
		case BTFSS:
			BitOrientedOperations.BTFSS(inputInt);
			break;
		default:
			//System.out.println("ERROR no valid code");
			throw new Error("ERROR no valid code");
		}

	}

	public static void Decode10(int inputInt) {
		final int maske10=0x800;
		switch (maske10&inputInt) {
		case maske10:
			ControlOperations.GOTO(inputInt);
			break;
		case 0x0:
			ControlOperations.CALL(inputInt);
			break;
		default:
			//System.out.println("ERROR no valid code");
			throw new Error("ERROR no valid code");
		}

	}

	public static void Decode11(int inputInt) {
		final int maske11=0xF00;
		/*
		 * ADDLW	111x
		 * ANDLW	1101
		 * IORLW	1000
		 * XORLW	1010
		 * SUBLW	110x
		 * MOVLW	00xx
		 * RETLW	01xx
		 */
		switch (inputInt&maske11) {
		case 0xF00:
		case 0xE00:
			LiteralOperations.ADDLW(inputInt);
			break;
		case 0x900:
			LiteralOperations.ANDLW(inputInt);
			break;
		case 0x800:
			LiteralOperations.IORLW(inputInt);
			break;
		case 0xA00:
			LiteralOperations.XORLW(inputInt);
			break;
		case 0xD00:
		case 0xC00:
			LiteralOperations.SUBLW(inputInt);
			break;
		default:
			switch (inputInt&0x400) {
			case 0x400:
				LiteralOperations.RETLW(inputInt);
				break;
			case 0x0:
				LiteralOperations.MOVLW(inputInt);
				break;
			default:
				//System.out.println("ERROR no valid code");
				throw new Error("ERROR no valid code");
			}
			break;
		}

	}

}
