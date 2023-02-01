import java.util.Arrays;

class A {
    public String[] return2Elements() {
        int arr[][] = new int[2][2];
        arr[0][1] = 1;
        arr[0][1] = 2;
        arr[1][0] = 3;
        arr[1][1] = 4;
        String b[] = new String[] {Arrays.deepToString(arr), "5"};
        return b;
    }
}

public class TwoElementsReturn {
    public static void main(String[] args) {
        A a = new A();
        String b[] = a.return2Elements();
        System.out.println(Arrays.deepToString(b));
    }
}
