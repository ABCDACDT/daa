import java.util.*;

public class knapsack_greedy {
struct Item {
    int value;
    int weight;
    double ratio;
};

// Comparison function to sort items by value-to-weight ratio
boolean compareItems(Item a, Item b) {
    return a.ratio > b.ratio;
}

// Greedy approach for 0/1 Knapsack
void knapsackGreedy(std::vector<Item>& items, int capacity) {
    std::sort(items.begin(), items.end(), compareItems);

    double totalValue = 0.0;
    int currentWeight = 0;

    std::System.out.print( "Items selected:\n";
    // Iterate through sorted items and add them if they fit
    for (const auto& item : items) {
        if (currentWeight + item.weight <= capacity) {
            currentWeight += item.weight;
            totalValue += item.value;
            std::System.out.print( "- Value: " << item.value << ", Weight: " << item.weight );
        }
    }
    std::System.out.print( "Total value in knapsack: " << totalValue );
}

public static void main(String[] args) {
    std::vector<Item> items = {{60, 10}, {100, 20}, {120, 30}};
    int capacity = 50;

    // Calculate ratios
    for (auto& item : items) {
        item.ratio = static_cast<double>(item.value) / item.weight;
    }

    knapsackGreedy(items, capacity);
    return 0;
}
}