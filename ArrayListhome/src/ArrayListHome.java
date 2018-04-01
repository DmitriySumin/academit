import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<Integer> getNotEvenDigits(ArrayList<Integer> digitList) {
        for (int i = 0; i < digitList.size(); ++i) {
            if (digitList.get(i) % 2 == 0) {
                digitList.remove(i);
                --i;
            }
        }
        return digitList;
    }

    public static ArrayList<Integer> getDigitWithoutRepeat(ArrayList<Integer> digitList) {
        for (int i = 0; i < digitList.size(); ++i) {
            for (int j = i + 1; j < digitList.size(); ++j) {
                if (digitList.get(i).equals(digitList.get(j))) {
                    digitList.remove(j);
                }
            }
        }
        return digitList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("digitList.txt"))) {
            ArrayList<Integer> digitsList = new ArrayList<>();
            int count = scanner.nextInt();
            for (int i = 0; i < count; ++i) {
                digitsList.set(i, scanner.nextInt());
            }
            for (int i = 0; i < digitsList.size(); ++i) {
                System.out.print(digitsList.get(i) + " ");
            }
        }
    }
}
