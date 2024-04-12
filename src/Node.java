/**
 * Node class for use in BlockChain class
 * @author Pranav
 * @author Samuel A. Rebelsky
 * @author Garikai
 */

public class Node {
// +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  public Node next;
  public Block data;
  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+
  public Node(Block data, Node next) {
    this.data = data;
    this.next = next;
  }

  public Node(Block data) {
    this.data = data;
    this.next = null;
  }
}
