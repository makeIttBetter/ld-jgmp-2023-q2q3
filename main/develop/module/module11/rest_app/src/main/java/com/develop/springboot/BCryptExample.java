package com.develop.springboot;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BCryptExample {
    private static final List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
    private final char[] vowels1 = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

    public static void main(String[] args) throws IOException {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String originalPassword = "testPassword"; // Replace with the input password to check
//
//        System.out.println("testPassword: " + encoder.encode(originalPassword));

//        --------------------
//        String result = gcdOfStrings("LEET", "CODE");
//
//
//        System.out.println(result);
//        System.out.println("hello world!");
//        --------------------

//        int[] candies = {2, 3, 5, 1, 3};
//
//        int maxArrValue = Arrays.stream(candies).max().getAsInt();
//        System.out.println(maxArrValue);

//        --------------------

//        String str = "A man, a plan, a canal: Panama";
//
//        String result = reverseVowels(str);
//
//        System.out.println(result);

//        -------------------------------
//        String line = "the     sky is blue";
//
//        String result = reverseWords(line);
//        System.out.println(result); // expected: "blue is sky the"

//        -------------------------------

//        int[] nums = readNumbersFromFile("C:\\ws\\projects\\ld-jgmp-2023-q2q3\\main\\develop\\module\\module11\\rest_app\\src\\main\\java\\com\\develop\\springboot\\test.txt");
//
//        long before = System.currentTimeMillis();
//        int[] result = productExceptSelf(nums);
//        long after = System.currentTimeMillis();
//
//        System.out.println("time: " + (after - before));
//
//        System.out.println(Arrays.toString(result));

//        --------------------------------

//        int[] nums = {2, 1, 5, 0, 4, 6};

//        long before = System.currentTimeMillis();
//        boolean result = increasingTriplet(nums);
//        long after = System.currentTimeMillis();
//
//        System.out.println("time: " + (after - before));
//
//        System.out.println("result: " + result);


//        ---------------------------------------

//        int[] nums = {0, 1, 0, 3, 12};
//
//        System.out.println("nums:" + Arrays.toString(nums));
//
//        long before = System.currentTimeMillis();
//        moveZeroes(nums);
//        long after = System.currentTimeMillis();
//        System.out.println("time: " + (after - before));
//
//        System.out.println("nums:" + Arrays.toString(nums));

//        ---------------------------------------------------

        String s = "abc";
        String t = "ahbgdc";

        boolean isSubsq = isSubsequence(s, t);

        System.out.println(isSubsq);
    }

    public static boolean isSubsequence(String s, String t) {
        int symbol = 0;
        char[] sArr = s.toCharArray();
        for (char ch : t.toCharArray()) {
            if (symbol < sArr.length && sArr[symbol] == ch) {
                symbol++;
            }
        }


        return symbol == sArr.length;
    }

//    ------------------------------------------

    public static void moveZeroes(int[] nums) {
        int[] sortedArr = new int[nums.length];

        int currentPosition = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                sortedArr[currentPosition] = nums[i];
                currentPosition++;
            }
        }

        for (int i = currentPosition; i < nums.length; i++) {
            sortedArr[i] = 0;
        }

        System.arraycopy(sortedArr, 0, nums, 0, nums.length);
    }


//    -----------------------------------

    public static boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int secondSmall = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= small) {
                small = num;
            } else if (num <= secondSmall) {
                secondSmall = num;
            } else {
                return true;
            }
        }

        return false;
    }

//    -----------------------------------------------

//    public static int[] productExceptSelf(int[] nums) {
//        int[] result = new int[nums.length];
//
//        for (int i = 0; i < nums.length; i++) {
//            int calcValue = multiplyAllExceptIndex(nums, i);
//            result[i] = calcValue;
//        }
//
//        return result;
//    }

    public static int[] readNumbersFromFile(String fileName) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(","); // Splitting by comma, adjust if necessary
                for (String token : tokens) {
                    numbers.add(Integer.parseInt(token.trim())); // Parsing and adding to list
                }
            }
        }
        // Converting list to array
        return numbers.stream().mapToInt(i -> i).toArray();
    }

    public static int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];

        // Calculate left products
        int leftProduct = 1;
        for (int i = 0; i < length; i++) {
            result[i] = leftProduct;
            leftProduct *= nums[i];
        }

        // Calculate right products and multiply with left products
        int rightProduct = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }


    private static int multiplyAllExceptIndex(int[] nums, int index) {
        int multiplicator = 1;
        for (int i = 0; i < nums.length; i++) {
            if (index != i) {
                multiplicator *= nums[i];
            }
        }
        return multiplicator;
    }


    //  -----------------------------------------
    public static String reverseWords(String s) {
        List<String> words = reverseArr(List.of(s.split("\\s+")));

        StringBuilder sb = new StringBuilder();
        words.forEach(word -> sb.append(word).append(" "));

        return sb.toString().trim();
    }

    private static <T> List<T> reverseArr(List<T> arr) {
        List<T> reversedArr = new ArrayList<>();
        for (int i = arr.size() - 1; i >= 0; i--) {
            reversedArr.add(arr.get(i));
        }
        return reversedArr;
    }


//    ------------------------------------------

    public static String reverseVowels(String s) {
        Map<Integer, Character> vowelsFound = findAllVowels(s);
        Map<Integer, Character> reversedVowels = reverseVowels(vowelsFound);
        String result = replaceVowels(s, reversedVowels);
        return result;
    }


    private static String replaceVowels(String s, Map<Integer, Character> reversedVowels) {
        char[] strChars = s.toCharArray();
        for (Map.Entry vowel : reversedVowels.entrySet()) {
            strChars[(int) vowel.getKey()] = (char) vowel.getValue();
        }
        return new String(strChars);
    }

    private static Map<Integer, Character> reverseVowels(Map<Integer, Character> vowelsFound) {
        List<Integer> indexes = new ArrayList<>(vowelsFound.keySet());
        List<Character> vowels = new ArrayList<>(vowelsFound.values());
        Collections.reverse(indexes);

        Map<Integer, Character> reversedVowels = new HashMap<>();
        for (int i = 0; i < indexes.size(); i++) {
            reversedVowels.put(indexes.get(i), vowels.get(i));
        }

        return reversedVowels;
    }

    private static Map<Integer, Character> findAllVowels(String s) {
        Map<Integer, Character> vowelsFound = new LinkedHashMap<>();
        char[] strChars = s.toCharArray();
        for (int i = 0; i < strChars.length; i++) {
            char current = strChars[i];
            if (vowels.contains(current)) {
                vowelsFound.put(i, current);
            }
        }
        return vowelsFound;
    }

//    -------------------------

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int freePlacesCount = countFreeSpaces(flowerbed);
        return freePlacesCount >= n;
    }

    private int countFreeSpaces(int[] flowerbed) {
        int[] calculatedArr = new int[flowerbed.length + 2];
        System.arraycopy(flowerbed, 0, calculatedArr, 1, flowerbed.length);
        calculatedArr[0] = 0;
        calculatedArr[calculatedArr.length - 1] = 0;

        int placesCounter = 0;
        int zeroCounter = 0;
        for (int value : calculatedArr) {
            if (zeroCounter == 3) {
                placesCounter++;
                zeroCounter = 0;
            }

            if (value == 0) {
                zeroCounter++;
            } else if (value == 1) {
                zeroCounter = 0;
            }
        }
        return placesCounter;
    }


//    -------------------------

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxArrCandy = findMaxValue(candies);
        List<Boolean> result = new ArrayList<>();
        for (int val : candies) {
            int resultedValue = val + extraCandies;
            result.add(resultedValue > maxArrCandy);
        }
        return result;
    }

    private int findMaxValue(int[] candies) {
        int max = 0;
        for (int val : candies) {
            max = val > max ? val : max;
        }
        return max;
    }

//    -------------------------


    public static String gcdOfStrings(String str1, String str2) {
        // Find the smaller string, as the common divisor can't be larger than the smallest string.
        String smaller = str1.length() < str2.length() ? str1 : str2;
        String larger = str1.length() >= str2.length() ? str1 : str2;

        // Start by assuming the entire smaller string is the common divisor
        String possibleDivisor = smaller;

        // Decrease the size of the possible divisor until it divides both strings or its length is 0
        while ((!larger.startsWith(possibleDivisor) || !smaller.startsWith(possibleDivisor) ||
                !stringIsRepeated(smaller, possibleDivisor) || !stringIsRepeated(larger, possibleDivisor)) && !possibleDivisor.isEmpty()) {
            // Decrease the length of the possible divisor by 1
            possibleDivisor = possibleDivisor.substring(0, possibleDivisor.length() - 1);
        }
        return possibleDivisor;
    }

    // Helper method to check if 'str' is made by repeating 'divisor'
    private static boolean stringIsRepeated(String str, String divisor) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < str.length() && !divisor.isEmpty()) {
            sb.append(divisor);
        }
        return sb.toString().equals(str);
    }


    public int[] twoSum(int[] nums, int target) {
        for (int i : nums) {
            for (int j : nums) {
                if (i + j == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public String mergeAlternately(String word1, String word2) {

        word1.charAt(12);

        char[] word1Chars = word1.toCharArray();
        char[] word2Chars = word2.toCharArray();

        int maxSize = word1Chars.length > word2Chars.length ? word1Chars.length : word2Chars.length;
        String result = "";
        for (int i = 0; i < maxSize; i++) {
            result += i >= word1Chars.length ? "" : word1Chars[i];
            result += i >= word2Chars.length ? "" : word2Chars[i];
        }
        return result;
    }

}
