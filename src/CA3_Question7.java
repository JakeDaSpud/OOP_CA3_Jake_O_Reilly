
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */
public class CA3_Question7
{

    Map<String, Block>

    //Simple storage class for holding quantity of, and price per stock
    static class Block {
        int StockAmount;
        double PricePer1Stock;

        Block(int StockAmount, double PricePer1Stock) {
            this.StockAmount = StockAmount;
            this.PricePer1Stock = PricePer1Stock;
        }

        public void setStockAmount(int stockAmount) {
            StockAmount = stockAmount;
        }

        public void setPricePer1Stock(double pricePer1Stock) {
            PricePer1Stock = pricePer1Stock;
        }

        public int getStockAmount() {
            return StockAmount;
        }

        public double getPricePer1Stock() {
            return PricePer1Stock;
        }

        @Override
        public String toString() {
            return "{" + StockAmount + " at " + PricePer1Stock + "/stock}";
        }
    }

    /*
    Will repeatedly ask the user to enter the commands in the format
    buy company qty price
    or
    sell company qty price
    or
    quit
     */
    public static void main(String[] args) {
        //Block Queue for buying and selling Stocks
        Queue<Block> ownedStocks = new ArrayDeque<>();
        Scanner in = new Scanner(System.in);

        String command = "";
        do {
            System.out.println("Total ownedStocks: " + ownedStocks + "\n");
            System.out.print("Enter BUY/SELL stock_quantity stock_price: ");

            command = in.next();

            //buying stocks
            //takes in qty and price per stock, makes a new block, adds to queue
            if(command.equalsIgnoreCase("buy")) {
                String company = in.next();

                int qty = in.nextInt();

                //why are you buying 0 of a stock, commitment issues?
                if (qty <= 0) {break;}

                double price = Math.abs(in.nextDouble());

                ownedStocks.offer(new Block(qty, price));
            }

            //selling stocks
            //affects the next polling entry of the queue:
            // - will NOT OVERDRAW qty from stock, can't sell more than is there
            // - will CALCULATE and PRINT OUT you the DIFFERENCE of the price you paid for those stocks:
            // -- [(Block.getPricePer1Stock() * qtyIAmSelling) - (currentInputSellPrice * qtyIAmSelling)]
            else if (command.equalsIgnoreCase("sell")) {
                String company = in.next();
                //remove any negative sign, no quantity < 0!
                int qty = Math.abs(in.nextInt());
                double price = in.nextDouble();
                double profit = 0, totalProfit = 0;

                //current stock being affected
                Block currentStock = ownedStocks.peek();

                //are there stocks?
                if (ownedStocks.isEmpty()) {
                    System.out.println("No stocks to sell.");
                }

                //this code works but doesn't do the whole queue switching...
                //there are stocks
                else {
                    while (qty > 0) {
                        //System.out.printf("Stock Attempting to be sold: Quantity[%d] Price[%f]\n", currentStock.getStockAmount(), currentStock.getPricePer1Stock());
                        //System.out.println("Amount left to sell: " + qty);

                        //Get current cost of this stock
                        double oldStockSum = currentStock.getPricePer1Stock() * qty;

                        //Iterating to "next" stock
                        currentStock.setStockAmount(currentStock.getStockAmount() - 1);
                        qty--;

                        profit = price - currentStock.getPricePer1Stock();
                        System.out.println("Profit of that stock: " + profit);

                        totalProfit += profit;

                        //if 0 or less amount, that stock block is donezo, get it out of the queue
                        if (currentStock.getStockAmount() < 1) {
                            System.out.println("Moving to next block of stocks...");
                            ownedStocks.poll();

                            //need to set new currentStock
                            currentStock = ownedStocks.peek();
                        }
                    }

                    System.out.println("Total profit of that sell command: " + totalProfit);

                }
            }

            //minor error catching
            else {
                System.out.println("\nError or Quit: incorrect command format, nothing happened...");
            }

        } while (!command.equalsIgnoreCase("quit"));
    }
}