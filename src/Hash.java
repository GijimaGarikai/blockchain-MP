import java.security.MessageDigest;
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
  private byte[] initial_data;
  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+
  public Hash(byte[] data) throws NoSuchAlgorithmException{
    this.initial_data = data;
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(data);
    this.hash = md.digest();
  }

  

  // +---------------+---------------------------------------------------
  // |    Methods    |
  // +---------------+
  public byte[] getData() {
    return this.hash;
  }

  public boolean isValid() {
    for(int i = 0; i < 3; i++) {
      if(hash[i] != 0) {
        return false;
      } // if
    } // for-loop
    return true;
  }

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
  }

  public boolean equals (Object other) {
    if(other instanceof Hash) {
        Hash o = (Hash) other;
        return Arrays.equals(o.hash, this.hash);
    }
    return false;
  }
}