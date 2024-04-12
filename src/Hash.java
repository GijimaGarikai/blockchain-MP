import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 * Hash wrapper class.
 * @author Pranav
 * @author Samuel A. Rebelsky
 * @author Garikai
 */
public class Hash {
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  private byte[] hash;
  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+
  /*
   * Hash(byte[] data)
   * constructs a new Hash object that contains the given hash (as an array of bytes).
   */
  public Hash(byte[] data) throws NoSuchAlgorithmException{
    this.hash = data;
  } //Hash(byte[] data)

  

  // +---------------+---------------------------------------------------
  // |    Methods    |
  // +---------------+

  /* byte[] getData()
   * returns the hash contained in this object.
   */
  public byte[] getData() {
    return this.hash;
  }// byte[] getData()
 
  /* boolean isValid()
   *  returns true if this hash meets the criteria for validity,
   *  i.e., its first three indices contain zeroes.
   */
  public boolean isValid() {
    for(int i = 0; i < 3; i++) {
      if(hash[i] != 0) {
        return false;
      } // if
    } // for-loop
    return true;
  } //boolean isValid()
 
  /* String toString()
   *   returns the string representation of the hash as 
   * a string of hexadecimal digits, 2 digits per byte.
   */
  public String toString() {
    int[] hashInts = new int[this.hash.length];
    for (int i = 0; i < hashInts.length; i++) {
        hashInts[i] = Byte.toUnsignedInt(this.hash[i]);
    } //for-loop
    String result = "";
    for (int i = 0; i < hashInts.length; i++) {
        result += String.format("%02X", hashInts[i]);
    }//for-loop
    return result;
  } //String toString()


  /* boolean equals(Object other)
   *  returns true if this hash is structurally equal to the argument.
   */
  public boolean equals (Object other) {
    if(other instanceof Hash) {
        Hash o = (Hash) other;
        return Arrays.equals(o.hash, this.hash);
    }
    return false;
  } // boolean equals(Object other)
}