import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * BlockChainDriver class.
 * @author Pranav
 * @author Samuel A. Rebelsky
 * @author Garikai
 */
public class BlockChainDriver {
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  static String[] comArr = {"mine", "append", "remove", "check", 
                            "report", "help", "quit"};
  static List<String> comList = Arrays.asList(comArr);

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+
  

  // +---------------+---------------------------------------------------
  // |    Methods    |
  // +---------------+

  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("Welcome! Type the given commands into the command line and enjoy this blockchain simulator!");
    if (args.length != 1 || !isNumeric(args[0])) {
      return;
    }
    Scanner eyes = new Scanner(System.in);
    String curLine;
    BlockChain blkchain = new BlockChain(Integer.parseInt(args[0]));
    printMenu(pen);

    while (true) {
      pen.println(blkchain.toString());
      pen.println("Command? ");
      curLine = eyes.nextLine();
      int command = comList.indexOf(curLine);
      if (command < 0) {
        pen.println("'" + curLine + "'" + " is an invalid command. Try again");
        continue;
      }
      executeCommand(blkchain, command, pen, eyes);
    }

  }

  /* isNumeric(String str)
   * checks if a string is numeric
   */
  public static boolean isNumeric(String str) {
    try {  
      Integer.parseInt(str);  
        return true;
    } catch(NumberFormatException e){  
      return false;  
    } 
  } // isNumeric(String str)


  /* printMenu(PrintWriter pen)
   * prints menu
   */
  private static void printMenu(PrintWriter pen) {
    pen.println("Valid commands: \n" +
    "\t append: appends a new block onto the end of the chain\n" +
    "\t remove: removes the last block from the end of the chain\n" +
    "\t check: checks that the block chain is valid\n" +
    "\t report: reports the balances of Alexis and Blake\n" +
    "\t help: prints this list of commands\n" +
    "\t quit: quits the program");
  } // printMenu(PrintWriter pen)

  /* executeCommand(BlockChain blkchain, int command, PrintWriter pen, Scanner eyes)
   * executes given command
   */
  private static void executeCommand(BlockChain blkchain, int command, PrintWriter pen, Scanner eyes) {
    switch(command) {
        case 0:
          pen.println("Amount transferred? (integer) ");
          int amt0 = Integer.parseInt(eyes.nextLine());
          Block currBlock = blkchain.mine(amt0);
          pen.println("amount = " + amt0 + ", nonce = " + currBlock.getNonce()+"\n");
          break; // case 0 | mine
        case 1:
          pen.println("Amount transferred? ");
          int amt1 = Integer.parseInt(eyes.nextLine());
          pen.println("Nonce? ");
          long nonce = Long.parseLong(eyes.nextLine());
          Block newBlock = new Block(blkchain.getSize(), amt1, blkchain.getHash(), nonce);
          try {
            blkchain.append(newBlock);
          } catch (Exception e) {
            pen.println("Invalid Hash/Block");
          } // try-catch
          pen.println("\n");
          break; // case 1 | append
        case 2:
        if (blkchain.removeLast()) {
            pen.println("Last item removed!\n");
          } // if
          else {
            pen.println("Removal failed!\n");
          } // else
          
          break; // case 2 | remove
        case 3:
          if (blkchain.isValidBlockChain()) {
            pen.println("Chain is valid!\n");
          } // if
          else {
            pen.println("Chain is not valid!\n");
          } // else
          break; // case 3 | check
        case 4:
          blkchain.printBalances(pen);
          pen.println("\n");
          break; // case 4 | report
        case 5:
          printMenu(pen);
          pen.println("\n");
          break; // case 5 | help
        case 6:
          pen.println("Program terminated\n");
          System.exit(0);
          break; // case 6 | quit
    } // switch
  } // executeCommand(BlockChain blkchain, int command, PrintWriter pen, Scanner eyes)

  
} 