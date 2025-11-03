import java.util.*;

public class fractional_knapsack {

struct Item {
    int value;
    int weight;
    double ratio;
};

// Comparison function to sort items by value-to-weight ratio
boolean compareItems(Item a, Item b) {
    return a.ratio > b.ratio;
}

// Greedy algorithm for Fractional Knapsack
void fractionalKnapsack(std::vector<Item>& items, int capacity) {
    std::sort(items.begin(), items.end(), compareItems);

    double totalValue = 0.0;
    int currentWeight = 0;

    // Loop through all items
    for (const auto& item : items) {
        if (currentWeight + item.weight <= capacity) {
            // Take the whole item
            currentWeight += item.weight;
            totalValue += item.value;
        } else {
            // Take a fraction of the item
            int remainingWeight = capacity - currentWeight;
            totalValue += item.value * (static_cast<double>(remainingWeight) / item.weight);
            break; // Knapsack is full
        }
    }
    std::System.out.print( "Maximum value in knapsack: " << totalValue );
}

public static void main(String[] args) {
    std::vector<Item> items = {{60, 10}, {100, 20}, {120, 30}};
    int capacity = 50;

    // Calculate ratios
    for (auto& item : items) {
        item.ratio = static_cast<double>(item.value) / item.weight;
    }

    fractionalKnapsack(items, capacity);
    return 0;
}
}