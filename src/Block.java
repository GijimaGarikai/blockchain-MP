import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Block class for Blockchain.
 * @author Pranav
 * @author Samuel A. Rebelsky
 * @author Garikai
 */
public class Block {
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
 
  private int blockNum;
  private int amountTransferred; 
  private long nonce;
  private Hash prevHash;
  private Hash currHash;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+
  /* Block(int num, int amount, Hash prevHash)
   * creates a new block from the specified parameters, performing the mining operation 
   * to discover the nonce and hash for this block given these parameters.
   */
  public Block(int num, int amount, Hash prevHash) {
    this.blockNum = num;
    this.amountTransferred = amount;
    this.prevHash = prevHash;
    
    try {
      mineBlock();
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  } // Block(int num, int amount, Hash prevHash)


  /* Block(int num, int amount, Hash prevHash, long nonce)
   * creates a new block from the specified parameters, using the provided nonce and 
   * additional parameters to generate the hash for the block. Because the nonce is 
   * provided, this constructor does not need to perform the mining operation; 
   * it can compute the hash directly.
   */
  public Block(int num, int amount, Hash prevHash, long nonce) {
    this.blockNum = num;
    this.amountTransferred = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
    
    try {
      makeHash();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } // try-catch
  } // Block(int num, int amount, Hash prevHash, long nonce)
  // +---------------+---------------------------------------------------
  // |    Methods    |
  // +---------------+

  /* 
   * int getNum():
   * returns the number of this block.
  */
  public int getNum() {
    return this.blockNum;
  } // getNum()

  /* 
   * int getAmount(): 
   * returns the amount transferred that is recorded in this block
  */
  public int getAmount() {
    return this.amountTransferred;
  } // getAmount()

  /* 
   * long getNonce(): 
   * returns the nonce of this block.
  */
  public long getNonce() {
    return this.nonce;
  } // getNonce()

  /* 
   * Hash getPrevHash(): 
   * returns the hash of the previous block in the blockchain.
  */
  public Hash getPrevHash() {
    return this.prevHash;
  } // getPrevHash()

  /* 
   * Hash getHash(): 
   * returns the hash of this block.
  */
  public Hash getHash() {
    return this.currHash;
  } // getHash()

  /*
   * String toString(): 
   * returns a string representation of the block as below :
   * Block <num> (Amount: <amt>, Nonce: <nonce>, prevHash: <prevHash>, hash: <hash>)
   */
  public String toString() {
    return String.format("Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s)",
                        this.blockNum, this.amountTransferred, this.nonce, 
                        (this.prevHash == null) ? "" : this.prevHash.toString(),
                        this.currHash.toString());
  } // toString()
 
  /* 
   * isEqual(Block blk): 
   * returns true if the hash of this.curHash and blk are equal
   * otherwise false.
  */
  public boolean isEqual(Block blk) {
    return this.currHash.equals(blk.getHash());
  } //  boolean isEqual(Block blk)

 
  // +---------------------+---------------------------------------------------
  // |   Private Methods   |
  // +---------------------+

  /* void mineBlock()
   * mines block
  */
  private void mineBlock() throws NoSuchAlgorithmException {
    this.nonce = 0;

    while(this.nonce <= Long.MAX_VALUE) {
      makeHash();

      if(this.currHash.isValid()) {
        return;
      } // if
      this.nonce++;
    } // while
  } // mineBlock()

  /* void makeHash()
   * finds a valid hash and updates this.currHash
  */
  private void makeHash() throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");

    byte[] blockNumBytes = ByteBuffer.allocate(Integer.BYTES).putInt(this.blockNum).array();
    byte[] amtTransBytes = ByteBuffer.allocate(Integer.BYTES).putInt(this.amountTransferred).array();
    byte[] nonceBytes = ByteBuffer.allocate(Long.BYTES).putLong(this.nonce).array();

    md.update(blockNumBytes);
    md.update(amtTransBytes);
    md.update(nonceBytes);

    if (this.prevHash != null) {
      md.update(this.prevHash.getData());
    }
    this.currHash = new Hash(md.digest());
  } //makeHash()
}// Block class
 

