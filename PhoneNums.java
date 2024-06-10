import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

class PhoneBook {
    private HashMap<String, ArrayList<Integer>> phoneBook;

    PhoneBook() {
        this.phoneBook = new HashMap<String, ArrayList<Integer>>();
    }
    
    public void add(String name, Integer phoneNum) {
        if (phoneBook.containsKey(name)) {
            ArrayList<Integer> phoneNums = phoneBook.get(name);
            phoneNums.add(phoneNum);
            this.phoneBook.put(name, phoneNums);
      } else {
            ArrayList<Integer> phoneNums = new ArrayList<Integer>(Arrays.asList(phoneNum));
            this.phoneBook.put(name, phoneNums);
      }
    }

    public HashMap<String, ArrayList<Integer>> getPhoneBook() {
        return this.phoneBook;
    }

}

public class PhoneNums {
    public static void main(String[] args) {
        PhoneBook exampleBook = new PhoneBook();
        exampleBook.add("cat", 37103);
        exampleBook.add("cat", 14523721);
        exampleBook.add("doggy", 197142);
        System.out.println(exampleBook.getPhoneBook());
    }
}
