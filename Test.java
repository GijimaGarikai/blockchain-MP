public class Test {
    public static void main(String[] args) {
      byte[] hashInts = new byte[5];
      for (int i = 0; i < hashInts.length; i++) {
        hashInts[i] = (byte) i;
      }
      String result = "";
      for (int i = 0; i < hashInts.length; i++) {
        result += String.format("%02X", hashInts[i]);
      }
      System.out.println(result);
      System.out.println("hi");
    }
    
}
