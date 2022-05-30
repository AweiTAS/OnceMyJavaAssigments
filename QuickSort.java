import java.util.Arrays;

public class QuickSort{
	public static void main(String args[]){
		int nums[] = new int[args.length];
		for(int i = 0; i < args.length; i++){
			nums[i] = Integer.parseInt(args[i]);
		}
		kp(nums);
	}
	public static void kp(int nums[]){
		if(nums.length == 1){
			System.out.print(nums[0] + " ");
			return;
		}
		int pivotkey = nums[0];
		int i = 0, j = nums.length - 1;
		while(i < j){
			while ((i < j) && (nums[j] >= pivotkey)) j--;
			nums[i] = nums[j];
			while ((i < j) && (nums[i] <= pivotkey)) i++;
			nums[j] = nums[i];
		}
		if((i - 0) > 0)
			kp(Arrays.copyOfRange(nums, 0, i));
		System.out.print(pivotkey + " ");
		if((nums.length - (i + 1)) > 0)
			kp(Arrays.copyOfRange(nums, i + 1, nums.length));
	}
}