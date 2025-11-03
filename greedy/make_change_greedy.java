import java.util.*;

public class make_change_greedy {
// Greedy algorithm to find the minimum coins for a given amount
void findMinCoins(ArrayList<Integer>& coins, int amount) {
    std::sort(coins.rbegin(), coins.rend()); // Sort in descending order

    ArrayList<Integer> result;

    // Iterate through coins
    for (int coin : coins) {
        while (amount >= coin) {
            amount -= coin;
            result.push_back(coin);
        }
    }

    std::System.out.print( "Coins used to make change: ";
    for (int coin : result) {
        std::System.out.print( coin << " ";
    }
    std::System.out.print( "\n";
}

public static void main(String[] args) {
    ArrayList<Integer> coins = {1, 2, 5, 10, 20, 50, 100, 500, 2000};
    int amount = 93;
    findMinCoins(coins, amount);
    return 0;
}
}