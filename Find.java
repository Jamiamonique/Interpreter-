import java.util.ArrayList;
class Find{
  private static ArrayList<Integer> variable = new ArrayList<Integer>();
  private static int[] a1= new int[26];
  public static void store(char a2, int a3 ){
    int value = a2- 97;
    a1[value]=a3;
    variable.add(value);
  }
}
