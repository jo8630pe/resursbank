
public class Main {

	public static void main(String[] args) {
		int[] hours = new int[24];
		int h = 12;
		int m = 44;
		int len = 437;
		
		int endHour = h + len/60;
		int endMin = m + len%60;
		if(endMin > 59){
			endHour++;
			endMin %= 60;
		}
		
		System.out.println(endHour);
		System.out.println(endMin);
		
		for (int i = 0; i < hours.length; i++){
			hours[i] = 2;
		}
		
		//System.out.println(cost(h,m,len,hours));
	}
	public static int cost(int h, int m, int len, int[] hours){
		double sum = 0;
		int startMin = h*60 + m;
		int stopMin = startMin + len;
		int counter = 1;
		
		for(int i = startMin; i < stopMin; i++){
			sum += hours[i / 60] /60.0;
			//System.out.println(counter);
			//System.out.println(sum);
			counter++;
		}
		
		return (int) Math.round(sum);
	}

}
