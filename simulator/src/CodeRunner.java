import java.util.ArrayList;

/**
 * 
 */

/**
 * @author johannes kraemer
 *
 */
public class CodeRunner extends Thread {
	private ArrayList<String> codeString;//linesCodeLineswithcodeCodestring[3]
	//private Object lock;
	CodeRunner(ArrayList<String> codeString){
		this.codeString=codeString;
//		System.out.println(codeString);
	}
	@SuppressWarnings("removal")
	public void run() {
		globalthings.RunnerIsInterruped=false;
        while(!Thread.currentThread().isInterrupted()) {
        	
    		
        	//TODO checktimer interupt
        	if(RAM.getGIE()==1&&RAM.getT0IE()==1&&RAM.getT0IF()==1) {
        		//TODO interrupt
        	}
        	
        	//fetch
        	
        	globalthings.pcact=RAM.PC;
        	
        	
        	if ((boolean) ApplicationGui.table_1.getModel().getValueAt(globalthings.pcact, 0) ) {
        		//System.out.println("breakpoint");
				ApplicationGui.resumeButton.setEnabled(true);
				ApplicationGui.refresh();
				this.suspend();
				ApplicationGui.resumeButton.setEnabled(false);
			}
//        	System.out.println(RAM.getPCL());
//        	System.out.println(codeString.get(RAM.getPCL()));
//        	if(globalthings.CALLGOTOPerformed) {
//        		decoder.DecodeStr(codeString.get(globalthings.jumpadress));

        	
        	decoder.DecodeStr(codeString.get(globalthings.pcact));
        	//+1 cyclus standard
    		globalthings.cycle++;
    		globalthings.timePassed=((double)globalthings.cycle/(double)globalthings.freqInt)/1000;
    		
    		//TODO inc timer0
    		
    		
    		//inc PC
        	if(!globalthings.callPerformed&&!globalthings.GOTOPerformed) {
            RAM.PC=RAM.PC+1;}
        	RAM.bank[RAM.PCL] = (byte) RAM.PC;//sync pc pcl
        		
    		
        	globalthings.started=true;
        	globalthings.jumpPerformed=false;
    		globalthings.callPerformed=false;
    		globalthings.GOTOPerformed=false;
    		
        	ApplicationGui.refresh();
        	
        	

//    		if(!globalthings.jumpPerformed) {
//    			RAM.setPCL(RAM.getPCL()+1);
//    			
//    		}
//    		if(!globalthings.jumpPerformed) {
//    			RAM.PC=RAM.getPCL()|((RAM.getPCLATH()&0b11111)<<8);
//    		}
//    		globalthings.jumpPerformed=false;
    		
        }
        globalthings.RunnerIsInterruped=true;
    }
}
