
public class Levenshtein {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "solutiadsfon";
		String str2 = "execution";
		int sol = findDistance(str1, str2, str1.length(), str2.length());
		System.out.println(sol);
		
		sol = findDistance(str1, str2);
		System.out.println(sol);

	}
	
	private static int min(int a, int b) {return (a<b)?a:b;}
	private static int min(int a, int b, int c) {return min(min(a,b), c);}
	
	//recursive solution
	public static int findDistance(String str1, String str2, int size1, int size2)
	{
		//if str1 = empty, insert all 'n' characters of str2 into str1
		if(size1==0) return size2;
		if(size2==0) return size1;
		
		//if last character of str1 and str1 are equal, then
		//we need to calculate distance for strings ending at second last index
		//for both str1 and str2
		if(str1.charAt(size1-1) == str2.charAt(size2-1)) 
			return findDistance(str1, str2, size1-1, size2-1);
		
		//return minimimum of the three cases: deletion, insertion, substitution
		return min(1+findDistance(str1, str2, size1-1, size2),
				   1+findDistance(str1, str2, size1, size2-1),
				   1+findDistance(str1, str2, size1-1, size2-1)
				   );
	}
	
	//DP solution
	public static int findDistance(String str1, String str2)
	{
		int[][] distanceTable = new int[str1.length()+1][str2.length()+1];
		int numRows = str1.length()+1;
		int numCols = str2.length()+1;
		
		for(int r=0; r < numRows; r++){
			for(int c=0; c<numCols; c++){
				if(r==0) 
					distanceTable[r][c] = c;
				else if(c==0) 
					distanceTable[r][c] = r;
				else if(str1.charAt(r-1) == str2.charAt(c-1))
					distanceTable[r][c] = distanceTable[r-1][c-1];
				else
					distanceTable[r][c] = min(
												1+distanceTable[r-1][c],
												1+distanceTable[r][c-1],
												1+distanceTable[r-1][c-1]												
											  );
			}
		}
		return distanceTable[numRows-1][numCols-1];
	}
	
	

}
