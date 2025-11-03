#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

// Helper function to remove leading zeros from a string number
std::string removeLeadingZeros(std::string s) {
    int i = 0;
    while (i < s.length() - 1 && s[i] == '0') {
        i++;
    }
    return s.substr(i);
}

// Helper function to add two string numbers
std::string addStrings(std::string num1, std::string num2) {
    std::string str = "";
    int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
    while (i >= 0 || j >= 0 || carry) {
        int sum = carry + (i >= 0 ? num1[i--] - '0' : 0) + (j >= 0 ? num2[j--] - '0' : 0);
        str = std::to_string(sum % 10) + str;
        carry = sum / 10;
    }
    return str;
}

// Helper function to subtract two string numbers (assumes num1 > num2)
std::string subtractStrings(std::string num1, std::string num2) {
    std::string str = "";
    int n1 = num1.length(), n2 = num2.length();
    std::reverse(num1.begin(), num1.end());
    std::reverse(num2.begin(), num2.end());
    int carry = 0;
    for (int i = 0; i < n2; i++) {
        int sub = ((num1[i] - '0') - (num2[i] - '0') - carry);
        if (sub < 0) {
            sub = sub + 10;
            carry = 1;
        } else {
            carry = 0;
        }
        str.push_back(sub + '0');
    }
    for (int i = n2; i < n1; i++) {
        int sub = ((num1[i] - '0') - carry);
        if (sub < 0) {
            sub = sub + 10;
            carry = 1;
        } else {
            carry = 0;
        }
        str.push_back(sub + '0');
    }
    std::reverse(str.begin(), str.end());
    return removeLeadingZeros(str);
}


// Function to multiply large numbers using Karatsuba algorithm
std::string karatsuba(std::string x, std::string y) {
    int n = std::max(x.length(), y.length());
    
    // Pad smaller number with leading zeros
    while (x.length() < n) x = "0" + x;
    while (y.length() < n) y = "0" + y;

    // Base case for recursion
    if (n == 1) return std::to_string((x[0] - '0') * (y[0] - '0'));

    int half = n / 2;
    int first_half = n - half;

    // Split numbers into two halves
    std::string a = x.substr(0, first_half);
    std::string b = x.substr(first_half);
    std::string c = y.substr(0, first_half);
    std::string d = y.substr(first_half);
    
    // Recursively compute the three products
    std::string p1 = karatsuba(a, c);
    std::string p2 = karatsuba(b, d);
    std::string p3 = karatsuba(addStrings(a, b), addStrings(c, d));
    
    // Calculate the middle term: (ad + bc) = p3 - (p1 + p2)
    std::string middle_term = subtractStrings(p3, addStrings(p1, p2));
    
    // Pad p1 and middle_term with zeros for final combination
    for (int i = 0; i < 2 * half; i++) p1.append("0");
    for (int i = 0; i < half; i++) middle_term.append("0");
    
    // Combine the results: p1 * 10^n + middle_term * 10^(n/2) + p2
    std::string result = addStrings(addStrings(p1, middle_term), p2);
    
    return removeLeadingZeros(result);
}

int main() {
    std::string num1 = "12345678901234567890";
    std::string num2 = "98765432109876543210";
    std::cout << "Product of " << num1 << " and " << num2 << " is:\n";
    std::cout << karatsuba(num1, num2) << std::endl;
    return 0;
}