import java.util.ArrayList;

/**
 * 
 */

/**
 * @author kkraemer
 *
 */
public class CodeRunner extends Thread {
	private ArrayList<String> codeString;
	private int totalCyclesInst=1;
	
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
        	
        	if(RAM.getTMR0()<4||RAM.getTMR0()>=250||!globalthings.debugMode) {
        	//check for breakpoint
        	if ((boolean) ApplicationGui.table_1.getModel().getValueAt(globalthings.pcact, 0) ) {
        		//System.out.println("breakpoint");
				ApplicationGui.resumeButton.setEnabled(true);
				ApplicationGui.refresh();
				this.suspend();
				ApplicationGui.resumeButton.setEnabled(false);
			}
        	}

        	int cycleBeforeInst=globalthings.cycle;
        	
        	//do instruction
        	decoder.DecodeStr(codeString.get(globalthings.pcact));
        	ApplicationGui.refresh();
        	
        	//+1 cyclus standard
    		globalthings.cycle++;
    		globalthings.timePassed=(double)globalthings.cycle*(4000000/(double)globalthings.freqInt);
    		totalCyclesInst=(globalthings.cycle-cycleBeforeInst);
    		
    		//inc timer0
    		int TOCS=RAM.getT0CS();
			if(TOCS==0) {//timer mode internal takt
    			for (int i = 0; i < totalCyclesInst ; i++) {
    				RAM.inctimmer(); //increment for every cycle done
				}
    		}else if(TOCS==1) {//counter mode with rise/fall of RA4 dependent TOSE
    			//TODO implement counter mode (propably directly in gui)
    		}
    		
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
