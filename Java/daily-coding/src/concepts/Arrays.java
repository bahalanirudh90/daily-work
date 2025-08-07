package concepts;
public class Arrays {
    public static void printPairs(int numbers[]){
        int totalPairs = 0;
        for(int i=0;i<numbers.length;i++){
            int curr = numbers[i];
            for(int j=i+1;j<numbers.length;j++){
                System.out.print("("+curr+","+numbers[j]+")");
                totalPairs++;
            }
            System.out.println();
        }
        System.out.println("total pairs : "+totalPairs);
    }
    public static void printSubarray(int numbers[]){
        int ts = 0;
        for(int i=0;i<numbers.length;i++){
            int start = i;
            for(int j=i;j<numbers.length;j++){
                int end = j;
                for(int k=start;k<=end;k++){
                    System.out.print(numbers[k]+" ");
                }
                ts++;
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("Total Subarrays:"+ts);
    }
    public static void maxSubarraySum(int a[]){
        int curr = 0;
        int max = Integer.MIN_VALUE;
        int i,j,k;
        for(i=0;i<a.length;i++){
            for(j=i;j<a.length;j++){
                curr = 0;
                for(k=i;k<=j;k++){
                    curr += a[k];
                }
                System.out.println("Current sum:"+curr);
                if(max < curr){
                    max = curr ;
                }
            }
        }
        System.out.println("Max sum:"+max);
    }
    public static void PREFIXSUM(int a[]){
        int i = 0 ,j = 0;
        int curr = 0;
        int max = Integer.MIN_VALUE;
        int[] prefix = new int[a.length];
        prefix[0] = a[0];
        for(i=1;i<prefix.length;i++){
            prefix[i] = prefix[i-1] + a[i];
        }
        for(i=0;i<a.length;i++){
            int start = i;
            for(j=i;j<a.length;j++){
                int end = j;
                curr = start == 0 ? prefix[end] : prefix[j] - prefix[i - 1];
                if(max < curr){
                    max = curr;
                }
            }
        }
        System.out.println("Max sum : "+max);
    }
    public static void kadanes(int a[]){
        int ms = Integer.MIN_VALUE;
        int cs = 0;
        for(int i=0;i<a.length;i++){
            cs = cs + a[i];
            if(cs < 0){
                cs = 0;
            }
            ms = Math.max(cs,ms);
        }
        System.out.println("Max subarray sum:"+ms);
    }
    public static void main(String[] args) {
        int[] numbers = {2,4,6,8,10};
        int[] test = {1,-2,6,-1,3};
        //printPairs(numbers);
        //printSubarray(numbers);
        //maxSubarraySum(numbers);
        //PREFIXSUM(test);

        int a[] = {-2,-3,4,-1,-2,1,5,-3};
        kadanes(a);
    }
}
