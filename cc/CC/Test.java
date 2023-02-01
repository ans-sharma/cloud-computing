import java.util.Arrays;

class MultiReturn {
    int[] arr;
    char[] str;
    double[] arrDouble;
    MultiReturn(int[] arr, char[] str, double[] arrDouble) {
        this.arr = arr;
        this.str = str;
        this.arrDouble = arrDouble;
    }
}

public class Test {

    public static MultiReturn returnSomeValue() {
        int[] javaIntArray = { 1, 2, 3 };
        char[] javaCharArray = { 'a', 'b', 'c', 'd', 'e' };
        double[] javaDoubleArray = { 3.14, 22.8 };
        MultiReturn mr = new MultiReturn(javaIntArray, javaCharArray, javaDoubleArray);
        return mr;
    }

    public static void main(String[] args) {
        MultiReturn mr;
        mr = returnSomeValue();
        System.out.println(Arrays.toString(mr.arr));
        System.out.println(Arrays.toString(mr.str));
        System.out.println(Arrays.toString(mr.arrDouble));
    }
}
