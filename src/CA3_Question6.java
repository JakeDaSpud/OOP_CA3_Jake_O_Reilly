
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */
public class CA3_Question6
{

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
    }

    /*
    Will repeatedly ask the user to enter the commands in the format
    buy qty price
    or
    sell qty price
    or
    quit
     */
    public static void main(String[] args) {
        //Block Queue for buying and selling Stocks
        Queue<Block> ownedStocks = new ArrayDeque<>();
        Scanner in = new Scanner(System.in);

        String command = "";
        do {
            System.out.print("Enter BUY/SELL stock_quantity stock_price: ");

            command = in.next();

            //buying stocks
            //takes in qty and price per stock, makes a new block, adds to queue
            if(command.equalsIgnoreCase("buy")) {
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
                //remove any negative sign, no quantity < 0!
                int qty = Math.abs(in.nextInt());
                double price = in.nextDouble();
                double profit = 0, totalProfit = 0;

                Block currentStock = ownedStocks.peek();

                //are there stocks?
                if (ownedStocks.isEmpty()) {
                    System.out.println("No stocks to sell.");
                }

                else {
                    //stock still has more than 1+ left
                    while (ownedStocks.peek().getStockAmount() > 0) {
                        profit++;
                        ownedStocks.peek().setStockAmount(ownedStocks.peek().getStockAmount() - 1);
                    }

                    //if there's another stock, bring it up in the queue
                    if (ownedStocks.size() > 1) {
                        ownedStocks.poll();
                        System.out.println("DEBUG: queue polled, new ");
                    }
                }

                //this code works but doesn't do the whole queue switching...
                //there are stocks
//                else {
//                    System.out.printf("Stock Attempting to be sold: Quantity[%d] Price[%f]\n", currentStock.getStockAmount(), currentStock.getPricePer1Stock());
//
//                    if (currentStock.getStockAmount() >= qty) {
//
//                        double oldStockSum = currentStock.getPricePer1Stock() * qty;
//
//                        //sufficient stock amount, remove them and calculate the difference
//                        currentStock.setStockAmount(currentStock.getStockAmount() - qty);
//
//                        System.out.println("New stock quantity: " + currentStock.getStockAmount());
//
//                        System.out.println("Selling that stock earned you: " + ((qty * price) - oldStockSum));
//
//                        //if 0 or less amount, that stock is donezo, get it out of the queue
//                        if (currentStock.getStockAmount() < 1) {
//                            ownedStocks.poll();
//                        }
//                    }
//
//                    else {
//                        System.out.println("Not enough stocks to sell...");
//                    }
//                }
            }

            //minor error catching
            else {
                System.out.println("\nError or Quit: incorrect command format, nothing happened...");
            }

        } while (!command.equalsIgnoreCase("quit"));
    }
}