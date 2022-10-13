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
	private ArrayList<Boolean> breakpointlist;
	//private Object lock;
	CodeRunner(ArrayList<String> codeString,ArrayList<Boolean> breakpointlist){
		this.codeString=codeString;
		this.breakpointlist=breakpointlist;
//		System.out.println(codeString);
	}
	@SuppressWarnings("removal")
	public void run() {
		globalthings.RunnerIsInterruped=false;
        while(!Thread.currentThread().isInterrupted()) {
        	
        	
        	if ((boolean) ApplicationGui.table_1.getModel().getValueAt(RAM.PC, 0) ) {
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
//        	}else
        	{
        	decoder.DecodeStr(codeString.get(RAM.PC));
        	//+1 cyclus standard
    		globalthings.cycle++;
    		globalthings.timePassed=((double)globalthings.cycle/(double)globalthings.freqInt)/1000;
    		
        	}
        	
        
        		//if(!globalthings.jumpPerformed) {
        			//RAM.setPCL(RAM.getPCL()+1);
        			//RAM.setRegisterContent(RAM.getPCL()+1, RAM.PCL);
        			//RAM.PC=(RAM.getPCL()|((RAM.getPCLATH()&0b11111)<<8))+1;
        			RAM.PC=RAM.PC+1;
        			RAM.setRegisterContent(RAM.PC&0b11111111, RAM.PCL);
        			//RAM.setRegisterContent((RAM.PC>>8),RAM.PCLATH);
        		//} 
        		
    		globalthings.jumpPerformed=false;
        	
    		
    		
        	ApplicationGui.refresh();
        	if(RAM.getGIE()==1&&RAM.getT0IE()==1&&RAM.getT0IF()==1) {
        		//TODO interrupt
        	}
        	

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
