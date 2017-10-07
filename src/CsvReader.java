import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
	
	private String csvFile = "C:/Users/AlekSandR/workspace/GrahamAlgorithm/punktyPrzykladowe.csv";
	
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    
	public CsvReader() {
	}
	
	public List<Point> getPointsFromFile() {
		List<Point> list = new ArrayList<>();
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				String [] data = line.split(csvSplitBy);
				
				double x,y;
				
				if (data.length == 2) {
					x = Double.parseDouble(data[0]);
					y = Double.parseDouble(data[1]);
					Point point = new Point(x,y);
					list.add(point);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void readFile() {
	    try {
	
	        br = new BufferedReader(new FileReader(csvFile));
	        while ((line = br.readLine()) != null) {
	
	            // use comma as separator
	            String[] point = line.split(csvSplitBy);
	            
	            if (point.length == 2)
	            	System.out.println("P(" + point[0] + "," + point[1] + ")");
	
	        }
	
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	}
}
