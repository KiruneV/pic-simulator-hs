import java.util.ArrayList;

/**
 * 
 */

/**
 * @author johannes kraemer
 *
 */
public class CodeRunner extends Thread {
	private ArrayList<String> codeString;
	
	CodeRunner(ArrayList<String> codeString){
		this.codeString=codeString;
//		System.out.println(codeString);
	}
	@SuppressWarnings("removal")
	public void run() {
		globalthings.RunnerIsInterruped=false;
        while(!Thread.currentThread().isInterrupted()) {
        	
    		
        	//timer interupt
        	if(RAM.getGIE()==1&&RAM.getT0IE()==1&&RAM.getT0IF()==1) {
        		int intcon = RAM.getINTCON();
    			intcon = (byte) (intcon & 0b01111111);
    			RAM.setINTCON(intcon);
    			ControlOperations.CALL(0x0004);
    			globalthings.cycle++;
    			globalthings.jumpPerformed=false;
        		globalthings.callPerformed=false;
        	}
        	
        	//fetch pc
        	globalthings.pcact=RAM.PC;
        	
        	//check for breakpoint
        	if ((boolean) ApplicationGui.table_1.getModel().getValueAt(globalthings.pcact, 0) ) {
        		//System.out.println("breakpoint");
				ApplicationGui.resumeButton.setEnabled(true);
				ApplicationGui.refresh();
				this.suspend();
				ApplicationGui.resumeButton.setEnabled(false);
			}

        	//do instruction
        	decoder.DecodeStr(codeString.get(globalthings.pcact));
        	
        	//+1 cyclus standard
    		globalthings.cycle++;
    		globalthings.timePassed=((double)globalthings.cycle/(double)globalthings.freqInt)/1000;
    		
    		//inc timer0 without scaler
    		int timer =  RAM.getTMR0();
            if(timer >=  0xFF) {
                int intcon = RAM.getINTCON();
                intcon =  (intcon | 0b00000100);
                RAM.setINTCON(intcon);
                timer=timer%0xFF;
            }else {
            	timer++;
            }
    		RAM.setTMR0(timer);
    		
    		//inc PC
        	if(!globalthings.callPerformed&&!globalthings.GOTOPerformed) {
            RAM.PC=RAM.PC+1;}
        	RAM.bank[RAM.PCL] = (byte) RAM.PC;//sync pc pcl
        		
    		//reset helpchecks
        	globalthings.started=true;
        	globalthings.jumpPerformed=false;
    		globalthings.callPerformed=false;
    		globalthings.GOTOPerformed=false;
    		
        	ApplicationGui.refresh();
        	
        	
    		
        }
        globalthings.RunnerIsInterruped=true;
    }
}
