import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.*;

/* Main class runs & outputs all classes
 * */
public class Main {
    /* main method takes fields and outputs
     * */
    public static void main(String[] args) {
        //#1
        System.out.println("Question 1: Euclid GCD");
        System.out.println(euclidGCD(4278, 8602));
        //#2
        System.out.println("\nQuestion 2: Subset Sum");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a target number:");
        int target = sc.nextInt();
        System.out.println(subsetSum(0,readArray(),target));
        //#3
        System.out.println("\nQuestion 3: Split Sum");
        System.out.println(splitSum(0, readArray(), 0, 0));
        sc.close();
        //#4
        System.out.println("\nQuestion 4: Elfish");
        System.out.println(elfish("whiteleaf", "e"));
    }//end main

    /**
     * readArray scans an inputted line and converts it to an int array
     *
     * @return integer []
     */
    private static int[] readArray() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a numbered list separated by spaces i.e. (2 3 4):");
        String[] strInput = sc.nextLine().split(" ");
        int[] input = new int[strInput.length];
        for (int i = 0; i < strInput.length; i++) {
            input[i] = Integer.parseInt(strInput[i]);
        }
        return input;
    }//end readArray

    /**
     * euclidGCD takes in two numbers and outputs their greatest common factor
     *
     * @param num1
     * @param num2
     * @return GCD
     */
    private static int euclidGCD(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 1;
        }
        if (num1 < num2) {
            int t = num1;
            num1 = num2;
            num2 = t;
        }
        //base case: if remainder is 0
        if (num1 % num2 == 0) {
            return num2;
        } else {
            //recursion case: run euclidGCD with num2 and quotient of num1/num2
            return euclidGCD(num2, num1 % num2);
        }
    }//end euclidGCD

    /**
     * subsetSum takes in an array and a desired target sum and outputs whether or not a subset of the array can sum to the target
     *
     * @param index
     * @param numList
     * @param target
     * @return boolean
     */
    private static boolean subsetSum(int index, int[] numList, int target) {
        index++;
        ArrayList<Integer> numList2 = new ArrayList<>();
        for (int j = 0; j < numList.length; j++) {
            if (numList[j] < target || numList[j] == target) {
                numList2.add(numList[j]);
            }
        }
        //base case 1: if all elements of array sum to target
        if (IntStream.of(numList).sum() == target) {
            return true;
        }
        for (int k = 1; k < numList2.size(); k++) {
            if (numList2.get(0) + numList2.get(k) == target) {
                return true;
            }
        }
        numList2.remove(0);
        if (numList2.size() == 0) {
            return false;
        }

        int[] numList3 = new int[numList2.size()];
        for (int i = 0; i < numList3.length; i++) {
            numList3[i] = numList2.get(i);
        }
        //recursion case: if no elements can be added to the first element of the array to equal the target, return subsetSum with a new array without first element
        return subsetSum(index, numList3, target);
    }//end subsetSum

    /**
     * splitSum takes in an array and outputs whether or not that array can be split in two equal sums
     *
     * @param index
     * @param numList
     * @param group1
     * @param group2
     * @return boolean
     */
    private static boolean splitSum(int index, int[] numList, int group1, int group2) {
        index++;
        ArrayList<Integer> arrGroup1 = new ArrayList<>();
        ArrayList<Integer> arrGroup2 = new ArrayList<>();

        //base case: if both groups are equal, return true
        if (group1 == group2 && group1 != 0) {
            return true;
        }
        if (index == numList.length) {
            return false;
        }
        if (IntStream.of(numList).sum() % 2 != 0) {
            return false;
        }
        if (numList.length == 2 && numList[0] == numList[1]) {
            return true;
        }
        for (int r = 1; r < numList.length; r++) {
            arrGroup2.add(numList[r]);
        }
        arrGroup1.add(numList[index - 1]);
        if (index > 1) {
            for (int i = 0; i <= index - 2; i++) {
                arrGroup2.remove(arrGroup2.get(0));
                arrGroup1.add(numList[i]);
            }
        }
        group1 = 0;
        for (int s = 0; s < arrGroup1.size(); s++) {
            group1 += arrGroup1.get(s);
        }
        group2 = 0;
        for (int s = 0; s < arrGroup2.size(); s++) {
            group2 += arrGroup2.get(s);
        }
        //recursion case: if two groups are not yet equal but have not tried all combinations, run again
        return splitSum(index, numList, group1, group2);
    }//end splitSum

    /**
     * elfish takes in a word and determines if it contains an e, l, and f
     *
     * @param word
     * @param s
     * @return boolean
     */
    private static boolean elfish(String word, String s) {
        //base case: this will be the last line to return true since the others must also be true
        if (word.contains(s) && s == "f") {
            return true;
        }
        //recursion case: runs elfish again if there is an l
        if (word.contains(s) && s == "l") {
            return elfish(word, "f");
        }
        //recursion case: runs elfish again if there is an e
        if (word.contains(s) && s == "e") {
            return elfish(word, "l");
        } else return false;
    } //end elfish
}//end Main
