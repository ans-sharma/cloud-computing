// incentive mechanism with knapsack, user allocation with maximum profit

import java.util.ArrayList;
import java.util.Scanner;

class User {
    int id;
    int VMCount;
    int price;

    User(int id, int VMCount, int price) {
        this.id = id;
        this.VMCount = VMCount;
        this.price = price;
    }
}

class Result {
    String combination;
    int maxObjective;

    Result(String combination, int maxObjective) {
        this.combination = combination;
        this.maxObjective = maxObjective;
    }
}

public class incentiveAlgo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<User> list = new ArrayList<User>();

        System.out.print("Enter total number of VM: ");
        int totalVM = sc.nextInt();

        System.out.print("Enter the number of Users: ");
        int n = sc.nextInt();

        takeInput(list, n);

        Result object = result(list, totalVM);
        System.out.println("Machine assigned to User(all users are present): " + object.combination);
        System.out.println("Maximum Profit(all users are present): " + object.maxObjective);

        System.out.println("Now We're calculating incentives: ");

        for (int i = 0; i < object.combination.length(); i++) {
            if (object.combination.charAt(i) == '0') {
                System.out.println("Incentive for User" + i + ": " + 0);
            } else {
                User removedUser = list.remove(i);
                Result obj = result(list, totalVM);
                int incentive = removedUser.price - object.maxObjective + obj.maxObjective;
                System.out.println("Incentive for User" + i + ": " + incentive);
                list.add(i, removedUser);
            }
        }

    }

    public static void takeInput(ArrayList<User> list, int n) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < n; i++) {
            System.out.print("Enter number of required VM for user " + i + ": ");
            int count = sc.nextInt();
            System.out.print("Enter price for user " + i + ": ");
            int pr = sc.nextInt();
            list.add(new User(i, count, pr));
        }
    }

    public static Result result(ArrayList<User> list, int totalVM) {

        int max = Integer.MIN_VALUE;
        int combination = 0;

        for (int i = 0; i < (int) Math.pow(2, list.size()); i++) {
            String str = decimalToBinary(i, list.size());
            // System.out.println(i);
            int totalPrice = 0;
            int assignedVM = 0;

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '1') {
                    totalPrice += list.get(j).price;
                    assignedVM += list.get(j).VMCount;
                    if (assignedVM > totalVM) {
                        break;
                    }
                }
            }

            if (totalPrice > max && assignedVM <= totalVM) {
                max = totalPrice;
                combination = i;
            }
        }

        return new Result(decimalToBinary(combination, list.size()), max);

    }

    public static String decimalToBinary(int num, int n) {

        String res = "";
        while (num != 0) {
            res = Integer.toString(num % 2) + res;
            num /= 2;
        }
        while (res.length() < n) {
            res = "0" + res;
        }
        return res;
    }

}
