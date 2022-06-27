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
        while(!Thread.currentThread().isInterrupted()) {
        	if (breakpointlist.get(RAM.getPCL())) {
        		//System.out.println("breakpoint");
				ApplicationGui.resumeButton.setEnabled(true);
				this.suspend();
				ApplicationGui.resumeButton.setEnabled(false);
			}
//        	System.out.println(RAM.getPCL());
//        	System.out.println(codeString.get(RAM.getPCL()));
        	decoder.DecodeStr(codeString.get(RAM.getPCL()));
        	
        	ApplicationGui.refresh();
        }
    }
	@SuppressWarnings("deprecation")
	public void cancel() { stop(); }
}
