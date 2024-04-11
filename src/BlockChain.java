import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.ArrayList;


/**
 * Blockchain class
 * @author Pranav
 * @author Samuel A. Rebelsky
 * @author Garikai
 */
public class BlockChain<T> {
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
 
  Node first;
  Node last;
  int numBlocks = 0;
  int blockNum = 0;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+
  /* BlockChain(int initialAmt)
   * creates a BlockChain that possess a single block the starts with the
   * given initial amount. Note that to create this block, the prevHash 
   * field should be ignored when calculating the block’s own nonce and hash.
   */
  public BlockChain(int initialAmt) {
    Block newBlock = new Block(this.blockNum++, initialAmt, null);
    this.numBlocks++;
    this.first = new Node(newBlock);
    this.last = this.first;
  } // BlockChain(int initialAmt)


  
  // +---------------+---------------------------------------------------
  // |    Methods    |
  // +---------------+

  /* mine(int amount)
   * mines a new candidate block to be added to the end of the chain. 
   * The returned Block should be valid to add onto this chain.
   */
  public Block mine(int amount){
    Block currBlock = new Block(this.blockNum++, amount, this.last.data.getHash());
    return currBlock;
  } // mine(int amount)
 
  /* mine(int amount)
   * returns the size of the blockchain. Note that number of 
   * the blocks provides a convenient method for quickly determining the size of the chain.
   */
  public int getSize() {
    return this.numBlocks;
  } // getSize()

  /* append(Block blk)
   * adds this block to the list, throwing an IllegalArgumentException if this block 
   * cannot be added to the chain (because it has an invalid hash, because its hash is 
   * inappropriate for the contents, or because the previous hash is incorrect).
   */
  public void append(Block blk) throws IllegalArgumentException{
    if(!blk.getHash().isValid()) {
      throw new IllegalArgumentException();
    }
    Node newNode = new Node(blk);
    this.last.next = newNode;
    this.last = this.last.next;
    this.numBlocks++;
  } // append(Block blk)

  /* boolean removeLast()
   * removes the last block from the chain, returning true. 
   * If the chain only contains a single block, then removeLast
   * does nothing and returns false.
   */
  public boolean removeLast(){
    if (this.numBlocks < 2) {
        return false;
    } // if
    Node curNode = this.first;
    while (!curNode.next.data.isEqual(this.last.data)) {
        curNode = curNode.next;
        // if we traverse whole list and do not find this.last
        if (curNode.next == null) {
          return false;
        } // if
    } // while
    this.last = curNode;
    this.last.next = null;
    this.numBlocks--;
    return true;
  } // boolean removeLast()

  /* Hash getHash()
   * returns the hash of the last block in the chain
   */
  public Hash getHash(){
    return this.last.data.getHash();
  } // Hash getHash()

  
  /* boolean isValidBlockChain()
   * walks the blockchain and ensures that its blocks 
   * are consistent (the balances are legal) and valid (as in append).
   */
  public boolean isValidBlockChain(){
    int balance = this.getBalance();
    return balance >= 0;

  } // boolean isValidBlockChain()

  /* void printBalances(PrintWriter pen)
   * prints Alexis’s and Blake’s respective balances in the form 
   * Alexis: <amt>, Blake: <amt> on a single line, e.g., Alexis: 300, Blake: 0.
   */
  void printBalances(PrintWriter pen){
    int initialBalance = this.first.data.getAmount();
    int alexisBalance = this.getBalance();
    int blakeBalance = initialBalance - alexisBalance;
    pen.print("Alexis: " + alexisBalance);
    pen.println(", Blake: " + blakeBalance);
  } // printBalances(PrintWriter pen)

  /* String toString(): 
   * returns a string representation of the BlockChain 
   * which is simply the string representation of each of its blocks, 
   * earliest to latest, one per line.
   */
  public String toString(){
    Node currNode = this.first;
    ArrayList<String> arr = new ArrayList<>();

    while(currNode != null) {
        arr.add(currNode.data.toString());
        currNode = currNode.next;
    }

    return String.join(", \n", arr);
  } // String toString()
  // +---------------------+---------------------------------------------------
  // |   Private Methods   |
  // +---------------------+

  private int getBalance() {
    int balance = 0;
    Node currNode = this.first;

    while(currNode != null){
      balance += currNode.data.getAmount();

      if(balance < 0 || !currNode.data.currHash.isValid()) {
        return -1;
      } // if
      currNode = currNode.next;
    } // while
    return balance;
  }

 
}// Block class
 

