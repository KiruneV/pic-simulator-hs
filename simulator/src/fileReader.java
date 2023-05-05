import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author jkraemer
 *
 */
public class fileReader {

	
	@SuppressWarnings("rawtypes")
	public static ArrayList[] linesCodeLineswithcodeCodestring;
	

	/**
	 * 
	 * @param filepath
	 * @return {lines,lineswithcode,code,codeString} of file as ArrayList[]
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList[] readFileLines(String filepath) throws FileNotFoundException, IOException{
		
		File fp = new File(filepath);
		FileReader fr = new FileReader(fp);
		BufferedReader br = new BufferedReader(fr);

		ArrayList<String> lines = new ArrayList<>();
		ArrayList<Integer> code = new ArrayList<>();
		ArrayList<Integer> lineswithcode = new ArrayList<>();
		ArrayList<String> codeString = new ArrayList<>();
		ArrayList<String> codeStringZeile = new ArrayList<>();
		
		String line;
		int counter =0;
		while((line = br.readLine()) != null) { 
			lines.add(line); 
			if (Character.isDigit(line.charAt(0))) {
				code.add(Integer.decode("0x"+line.substring(5, 9)));
				codeString.add(line.substring(5, 9));
				lineswithcode.add(counter);
				codeStringZeile.add(line);
			}
			
			counter++;
		}

		ArrayList[] x = new ArrayList[]{lines,code,lineswithcode,codeString,codeStringZeile};
		fr.close();
		linesCodeLineswithcodeCodestring=x;
		return x;
	}

}
