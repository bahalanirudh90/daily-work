package concepts;
public class GCD {
    public static int gcd(int m,int n){
        if(m % n == 0){
            return n;
        }
        else{
            return gcd(n,m % n);
        }
    }

    public static void main(String[] args) {
        int num1 = 205, num2 = 123;
        System.out.println(gcd(num1,num2));
    }
}
