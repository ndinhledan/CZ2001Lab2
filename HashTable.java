public class HashTable{
	public static class HashCell{
		int isEmpty =1; //1 for emtpty, 0 for filled, -1 for grave
		HashEntry data = new HashEntry();
	}

	public HashCell[] h_table = new HashCell[3000];
	public int size;
	public int entryNum =0;

	////////////////////////////////////////////////
	public HashTable(int size){
		this.size = size;
	}

	public HashTable(){

	};

	///////////////////////////////////////////////

	public void iniTable(HashCell[] table, int size){
		for (int i=0; i<size; i++){
			table[i] = new HashCell();
		}
	}

	//////////////////////////////////////////////////////
	////functions for hashing
	//////////////////////////////////////////////////

	public long hash(long key, int t_size){
		return key%t_size;
	}

	public long incHash(long key, int num){
		return (key%num) +1;
	}

	public long mulHash(int key, int size, int num){
		long a = (int) 8*(size/num) +5;
		return ((key*a) % 23);
	}

	////////////////////////////////////////////////////////

	public int addHashInc(String key_s, int num, HashEntry data, HashTable table){ //num for rehash
		long key = Long.parseLong(key_s);
		int loc = (int) hash(key, table.size);
		int cod = loc;
		int colli=0;

		if (table.h_table[loc].isEmpty ==0){
			do {
				colli++;
				long d = incHash(loc, num);
				loc = (int) hash(loc+d, table.size);
			} while (table.h_table[loc].isEmpty ==0 && loc!=cod); 
			if (loc == cod) {
				System.out.println("Cannot add!!");
				System.out.printf("%s has %d collision(s)!\n", data.key, colli);
				return colli; // cannot find space
			}
		}
		table.h_table[loc].data.key = data.key;
		table.h_table[loc].data.total_slots = data.total_slots;
		table.h_table[loc].data.avai_slots = data.avai_slots;
		table.h_table[loc].isEmpty =0;
		table.entryNum++;
		if (colli !=0) {
			System.out.printf("%s has %d collision(s)!\n", data.key, colli);
	}
	return colli;
}

	public int addHashMul(String key_s, int num, HashEntry data, HashTable table){ //num for rehash
		long key = Long.parseLong(key_s);
		int loc = (int) hash(key, table.size);
		int cod = loc;
		int colli =0;

		if (table.h_table[loc].isEmpty ==0){
			do {
				long d = mulHash(loc, table.size, num);
				loc = (int) hash(loc+d, table.size);
			} while (table.h_table[loc].isEmpty ==0 && loc!=cod); 
			if (loc == cod) {
				System.out.println("Cannot add!!");
				System.out.printf("%s has %d collision(s)!\n", data.key, colli);
				return colli ; // cannot find space
			}
		}
		table.h_table[loc].data.key = data.key;
		table.h_table[loc].data.total_slots = data.total_slots;
		table.h_table[loc].data.avai_slots = data.avai_slots;
		table.h_table[loc].isEmpty =0;
		table.entryNum++;
		if (colli !=0) {
			System.out.printf("%s has %d collision(s)!\n", data.key, colli);
	}
	return colli;
}

	//////////////////////////////////////////////////////////////////////

	public HashEntry findEntry(String key_s, HashTable table, Boolean flag, int num){
		//////flag False inc, flg true mul
		long d;
		int compare =0;

		StringBuilder sb = new StringBuilder();
		char[] letters = key_s.toCharArray();

		for (char ch : letters) {
    		sb.append((byte) ch);
		}

		//System.out.println(sb.toString());

		long key = Long.parseLong(sb.toString());
		int cod = (int) hash(key, table.size);
		int loc = cod;
		while (table.h_table[loc].isEmpty ==0){
			//System.out.println(table.h_table[loc].data.key);
			//System.out.println(key_s);
			compare++;
			if (table.h_table[loc].data.key.equals(key_s)){
				System.out.println("==Number of key comparisons: " + compare);				
				return table.h_table[loc].data;
			}
			else {
				if (flag) d = mulHash(loc, table.size, num); 
				else d  = incHash(loc, num);

				loc = (int) hash(loc+d, table.size);
				if (loc == cod) {
					System.out.println("==Number of key comparisons: " + compare);
					return new HashEntry();
				}
			}
		}
		System.out.println("==Number of key comparisons: " + compare);
		return new HashEntry();
	} 	
}