public class HashEntry{
	String key;
	int total_slots;
	int avai_slots;

	public HashEntry(String key, int total_slots, int avai_slots){
		this.key = key;
		this.total_slots = total_slots;
		this.avai_slots = avai_slots;
	}

	public HashEntry()
	{

	};
}