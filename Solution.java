class Solution {
    public static void main(String args[]) {
		try{
			int x = 1/0;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("Here is finally");
		}
			System.out.println("Here is after finally");
		int next[] = findNext(args[1]);
		int i = 0, j = 0;
		while(i < args[0].length()){
			for(;j<args[1].length();i++,j++){
				if(args[0].charAt(i) != args[1].charAt(j)){
					if(next[j] == 0) i++;
					else j = next[j];
				}
			}
			if(j == args[1].length()){ System.out.println(i + ""); return;}
		}
			
    }
	
	public static int[] findNext(String s){
		int next[] = new int[s.length()];
		next[0] = 0;
		for(int i = 1; i<s.length(); i++){
			int j = next[i-1];
			while(s.charAt(j+1) != s.charAt(i) && j != 0) j = next[j];
			next[i] = j + 1;
		}
		return next;
	}
}