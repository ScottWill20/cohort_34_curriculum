public class Exercise07 {

    // 1. Create a method.
    // Name: areInOrder
    // Inputs: int, int, int, int
    // Output: boolean
    // Description: return true if the four parameters are in ascending order.
    // Otherwise, returns false.

    public static boolean areInOrder(int a, int b, int c, int d){
        return a < b && b <= c && c <= d;
    }

    public static void main(String[] args) {
        System.out.println(areInOrder(1,2,3,4));
        System.out.println(areInOrder(5,4,3,2));
        // 2. Call your method in various ways to test it here.
    }
}
