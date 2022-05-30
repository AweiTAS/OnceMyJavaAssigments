public class Target{
	int target(num[], t){
		int high = num.length - 1, low = 0;
		while(high >= low){
			if(num[(high - low)/2] >= t) break;
			else low = (high - low)/2 + 1;
		}
		if(high < low){return -1;}
		int i = (high - low)/2 
		for(; num[i]<t ;i--);
		return i + 1;
	}
}

public class FindOne{
	int findOne(num[]){
		int m = 0;
		for(int i = 0; i<num.length ;i++){
			m = m^num[i];
		}
	}
}