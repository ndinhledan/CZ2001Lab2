import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HashData{
	
	public static int size;
	public HashEntry[] table = new HashEntry[2000];



	public HashData(int size){
		this.size = size;
	}

	public HashData(){

	};
/////////////////////////////////////////////////////////////////////

	public void insert(HashEntry[] table, String key, int total_Lots, int avai_Lots, int index){
		table[index].key = key;
		table[index].total_slots = total_Lots;
		table[index].avai_slots = avai_Lots; 
	}

	public void iniTable(HashEntry[] table, int size){
		int counter =0;
		int d = (int) (Math.random()*200);
		System.out.println(d);

		for (int i=0; i<size; i++){
			table[i] = new HashEntry();
		}

		JSONParser parser = new JSONParser();
		// parse in the json file to a json object 
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader("data.json"));


			JSONArray item = (JSONArray) obj.get("items");

			JSONObject item_obj = (JSONObject) item.get(0);
			JSONArray c_data = (JSONArray) item_obj.get("carpark_data");
			
	
			for (int i =0; i<size; i++){
				JSONObject c_obj = (JSONObject) c_data.get((i+d)%1800);

				JSONArray c_info = (JSONArray) (c_obj.get("carpark_info"));

				JSONObject tmp_info = (JSONObject) (c_info.get(0));

				int total_Lots = Integer.parseInt((String)tmp_info.get("total_lots"));
				int avai_Lots = Integer.parseInt((String)tmp_info.get("lots_available"));
					

				String carpark_num = (String) (c_obj.get("carpark_number"));
				insert(table, carpark_num, total_Lots, avai_Lots, counter);
				counter++;
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
}