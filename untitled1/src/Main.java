import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        int res[] = new int[10];

        res[0] = getCount0(n);
        for (int i = 1; i < 10; i++) {
            res[i] = getCount(n, i);
        }

        for (int i = 0; i < 10; i++) {
            if(i!=0){
                System.out.print(" ");
            }
            System.out.print(res[i]);
        }

    }

    public static int getCount(int num, int target) {

        int base = 1;
        int sum = 0;
        int n = num;

        while (n != 0) {

            sum += base * (n / 10);

            int cur = n % 10;
            if (cur == target) {
                sum += num % base + 1;
            } else if (cur > target) {
                sum += base;
            }

            base *= 10;
            n = n / 10;
        }

        return sum;

    }

    public static int getCount0(int num) {

        int base = 1;
        int sum = 0;
        int n = num;

        while (n != 0) {

            sum += base * (n / 10 -1);

            int cur = n % 10;
            if (cur == 0) {
                sum += num % base + 1;
            }
            else if (cur > 0) {
                sum += base;
            }

            base *= 10;
            n = n / 10;
        }

        return sum;

    }
}