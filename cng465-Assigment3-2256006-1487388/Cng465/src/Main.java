import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//AUTHORS ULAS CEM ERTEN 2256006 - OSMAN ALP SALKIN 1487388


public class Main {

	private static void read_File(ArrayList<AminoAcid> first_chain,ArrayList<AminoAcid> second_chain) throws NumberFormatException, IOException  {
		String name;
		
        String row = "";
		Scanner sc = new Scanner(System.in);
 		System.out.println("Paste the path of the file: "); 
 		name = sc.nextLine();
 		BufferedReader br = new BufferedReader(new FileReader(name));
        StringBuilder sb = new StringBuilder();
      
        
        boolean c_firstchain = true ;
        double x=0;
        double y=0;
        double z=0;
        int position = 0;
        int pre_position = -1000000;
        while ((row = br.readLine()) != null) {
             
        	if (row.startsWith("ATOM ")) {
            	if(row.contains("CB")) {
            		String [] splitted = row.replaceAll("(^\\s+|\\s+$)", "").split("\\s+");
            		x = Double.parseDouble(splitted[6]);
            		y = Double.parseDouble(splitted[7]);
            		z = Double.parseDouble(splitted[8]);       
            		position = Integer.parseInt(splitted[5]);
            		
            	}
            	if(row.contains("CA") && row.contains("GLY")) {
            		String [] splitted = row.replaceAll("(^\\s+|\\s+$)", "").split("\\s+");
            		x = Double.parseDouble(splitted[6]);
            		y = Double.parseDouble(splitted[7]);
            		z = Double.parseDouble(splitted[8]);
            		position = Integer.parseInt(splitted[5]);
            		
            	}
            	if ((row.contains("CA") && row.contains("GLY")) || (row.contains("CB")) ) {
            		AminoAcid new_amino = new AminoAcid(position,x,y,z);
            		if (position < pre_position) 
	            	{
	            		c_firstchain = false;
	            	}
	            	if (c_firstchain == true) 
	            	{
	            		first_chain.add(new_amino);
	            		
	            	}
	            	else 
	            	{
	            		second_chain.add(new_amino) ;
	            		
	            	}
	            	pre_position = position ;
	        	}}}
        	
        	}
	public static boolean check_matches(AminoAcid f1,AminoAcid f2) {
		if(Math.sqrt(((f1.x-f2.x)*(f1.x-f2.x)+(f1.y-f2.y)*(f1.y-f2.y)+(f1.z-f2.z)*(f1.z-f2.z)))<8)
			return true;
		
		else
			return false;
		
	}
        public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		ArrayList<AminoAcid> first_Chain = new ArrayList (); ;  
		ArrayList<AminoAcid> second_Chain = new ArrayList (); ; 
		ArrayList <Matches> match_List = new ArrayList();
		ArrayList <Hotspot> Hotspots = new ArrayList();
		
		read_File(first_Chain,second_Chain);
		int i,j;
		System.out.print("AminoAcids\n");
		for(i=0;i<first_Chain.size();i++) {
			
			System.out.println(first_Chain.get(i).seq +" , "+ first_Chain.get(i).x + " , "+first_Chain.get(i).y +" , "+ first_Chain.get(i).z);
		}
		
		 for(i=0;i<first_Chain.size();i++) {
			 for(j=0;j<second_Chain.size();j++) {
				 if(check_matches(first_Chain.get(i),second_Chain.get(j))) {
					 Matches match = new Matches(first_Chain.get(i),second_Chain.get(j));
					 match_List.add(match);
				 }
					 
					 
			 }
		 }
		 System.out.print("Hotspot 1\n");
		for(i=0;i<match_List.size();i++) {
			System.out.print("Pairs : "+ "Seq - " + match_List.get(i).first_AminoAcid.seq +" , " +"Seq - "+match_List.get(i).second_aminoAcid.seq);
			System.out.print("\n");
		}
		
		
	}
       

}
