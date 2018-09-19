///////////////////////////////////
////javac -cp .:"json-simple-1.1.1.jar" HashApp.java
/////////////////////////////////////////////
import java.util.*;
import java.io.*;


public class HashApp{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice, r_num=0;//rehash number
		/////////////////////////////////////
		HashData dataTable = new HashData();
		int data_size =0;
		////////////////////////////////////
		HashTable hashTable_i = new HashTable();
		int table_i_size=0;
		////////////////////////////////////
		HashTable hashTable_m = new HashTable();
		int table_m_size=0;

		do{
			System.out.println("============================");
			System.out.println("Choose the following option:");
			System.out.println("1. Create Data Table");
			System.out.println("2. Create Hash Table increment double hash");
			System.out.println("3. Create Hash Table multiplicative double hash");
			System.out.println("4. Number of Entries");
			System.out.println("5. Find");
			System.out.println("6. Exit");
			System.out.println("============================");
			System.out.print("Choice: ");
			choice = sc.nextInt();
			switch(choice){
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////Create Data
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				case 1:
					System.out.print("Insert data size: ");
					data_size = sc.nextInt();
					dataTable.size = data_size;
					///////////////////////////////////////////////
					dataTable.iniTable(dataTable.table, data_size);
					System.out.println("------------Data Table------------");
					System.out.println("============================");
					//////////////////////////////////////////////
					for (int i=0; i<data_size; i++){
						System.out.println(dataTable.table[i].key + " ------ " + dataTable.table[i].total_slots 
							+ " ------ " + dataTable.table[i].avai_slots + " ------ " );
					}
					break;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////Hash Table Inc
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				case 2:
					int colli=0;
					System.out.print("Insert table size: ");
					table_i_size = sc.nextInt();
					hashTable_i.size = table_i_size;
					///////////////////////////////////////
					hashTable_i.iniTable(hashTable_i.h_table, table_i_size);
					///////////////////////////////////////
					System.out.print("Input number for rehashing: ");
					r_num = sc.nextInt();
					for (int i=0; i<data_size; i++){
						StringBuilder sb = new StringBuilder();
						char[] letters = dataTable.table[i].key.toCharArray();

						for (char ch : letters) {
    						sb.append((byte) ch);
						}

						colli += hashTable_i.addHashInc(sb.toString(), r_num, dataTable.table[i], hashTable_i);
					}
					System.out.println("Collison(s): " + colli);
					/////////////////////////////////////////
					System.out.println("------------Hash Table Inc------------");
					System.out.println("============================");

					for (int i=0; i< table_i_size; i++){
						System.out.println(hashTable_i.h_table[i].data.key + " ------ " + hashTable_i.h_table[i].data.total_slots 
							+ " ------ " + hashTable_i.h_table[i].data.avai_slots + " ------ " + hashTable_i.h_table[i].isEmpty);
					}
					System.out.println(hashTable_i.entryNum);
					break;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////Hash Table Mul
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				case 3:
					int colli_m =0;
					System.out.print("Insert table size: ");
					table_m_size = sc.nextInt();
					hashTable_m.size = table_m_size;
					///////////////////////////////////////
					hashTable_m.iniTable(hashTable_m.h_table, table_m_size);
					///////////////////////////////////////
					System.out.print("Input number for rehashing: ");
					r_num = sc.nextInt();
					for (int i=0; i<data_size; i++){
						StringBuilder sb = new StringBuilder();
						char[] letters = dataTable.table[i].key.toCharArray();

						for (char ch : letters) {
    						sb.append((byte) ch);
						}

						colli_m += hashTable_m.addHashInc(sb.toString(), r_num, dataTable.table[i], hashTable_m);
					}
					System.out.println("Collison(s): " + colli_m);
					/////////////////////////////////////////
					System.out.println("------------Hash Table Mul------------");
					System.out.println("============================");

					for (int i=0; i< table_m_size; i++){
						System.out.println(hashTable_m.h_table[i].data.key + " ------ " + hashTable_m.h_table[i].data.total_slots 
							+ " ------ " + hashTable_m.h_table[i].data.avai_slots + " ------ " + hashTable_m.h_table[i].isEmpty);
					}
					System.out.println(hashTable_m.entryNum);
					break;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////Entry Number
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				case 4:
					System.out.println("Number of Entry in Hash Table: " +hashTable_i.entryNum);
					break;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////Find
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				case 5:
					System.out.println("Input 0 for increment table. Input 1 for multiplicative table.");
					System.out.print("Enter: "); 
					int f_choice = sc.nextInt();
					while (f_choice !=0 && f_choice !=1){
						System.out.println("Input error!!");
						System.out.println("Input 0 for increment table. Input 1 for multiplicative table.");
						System.out.print("Enter: "); 
						f_choice = sc.nextInt();
					}
					System.out.print("Enter key to search: ");
					String s_key = sc.next();
					//////////////////////////////////////////////
					HashTable temp = new HashTable();
					Boolean flag = true;
					if (f_choice ==0){
						temp = hashTable_i;
						flag = false;
					}
					else if (f_choice == 1){
						temp = hashTable_m;
						flag = true;
					}
					////////////////////////////////////////
					HashEntry answer = new HashEntry();
					long startTime = System.nanoTime();
					//Find
					answer = temp.findEntry(s_key, temp, flag, r_num);
					long endTime = System.nanoTime();

					if (answer.key == null){
						System.out.println("===========No Result Found=========");	
					}
					else{
						System.out.println("===========Result Found==========");
						System.out.println(answer.key + " ------ " + answer.total_slots 
								+ " ------ " + answer.avai_slots + " ------ ");
					}
					System.out.println("==CPU time: " + (endTime-startTime) + " ==");
					break;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////END!!!!
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				default:
					System.out.println("Terminating........");
					break;
			} 
		} while (choice <6);		

	}
}