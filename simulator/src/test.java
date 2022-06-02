import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

	@Test
	void test1() {
		decoder.DecodeStr("3011");
		assertEquals(0x11, RAM.getW());
	}
	
}
