import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

class PhoneBook {
    private HashMap<String, ArrayList<Integer>> phoneBook;
    /*
        An arrayList, where names of the people are sorted by the amount of phone numbers they have.
        This is done by making pools - arrayLists for every amount of phone numbers, where person's
        name is located. This was made to elimanate pasting names in the middle of an array, instead
        pasting them at the end of the pool.
        ArrayList, где имена людей сортированы по количеству телефонов ("Вывод должен быть отсортирован 
        по убыванию числа телефонов.") у них. Реализовано, сделав отдельные ArrayList'ы для каждого
        числа телефонов. Это сделано для того, чтобы не вставлять имя в середину, а вместо этого
        вставлять его на конец внутреннкго ArrayList'а.
    */
    private ArrayList<Set<String>> poolsByNumAmount;

    PhoneBook() {
        this.phoneBook = new HashMap<String, ArrayList<Integer>>();
        this.poolsByNumAmount = new ArrayList<Set<String>>();
        this.poolsByNumAmount.add(new HashSet<String>());
    }

    private void updateUserByPool(String name, int previousPoolIndex) {
        // updates the user, changing their pool, if the amount of phone nums changed.
        ArrayList<Integer> userPhones = this.phoneBook.get(name);
        if (userPhones.size() > this.poolsByNumAmount.size()) {
            int poolsToAdd = userPhones.size() - this.poolsByNumAmount.size();
            for (int i = 0 ; i < poolsToAdd ; i++) {
                Set<String> new_pool = new HashSet<String>();
                poolsByNumAmount.add(new_pool);
            }
        }
        this.poolsByNumAmount.get(previousPoolIndex).remove(name);
        this.poolsByNumAmount.get(userPhones.size() - 1).add(name);
    }
    
    public void add(String name, Integer phoneNum) {
        if (phoneBook.containsKey(name)) {
            ArrayList<Integer> phoneNums = phoneBook.get(name);
            phoneNums.add(phoneNum);
            this.phoneBook.put(name, phoneNums);
            this.updateUserByPool(name, phoneNums.size() - 2);

      } else {
            ArrayList<Integer> phoneNums = new ArrayList<Integer>(Arrays.asList(phoneNum));
            this.phoneBook.put(name, phoneNums);
            this.poolsByNumAmount.get(0).add(name);
      }
    }

    public void removeUser(String name) {
        //removing from pool
        int amountOfNums = this.phoneBook.get(name).size();
        this.poolsByNumAmount.get(amountOfNums - 1).remove(name);
        //removing from the phone book
        this.phoneBook.remove(name);
    }
    
    public void removeNumber(Integer phoneNum, String name) {
        //removing from pool
        int amountOfNums = this.phoneBook.get(name).size();
        if (amountOfNums == 1) {
            this.removeUser(name);
            return;
        }
        this.phoneBook.get(name).remove(phoneNum);
        this.updateUserByPool(name, amountOfNums - 1);
    }

    public HashMap<String, ArrayList<Integer>> getPhoneBook() {
        return this.phoneBook;
    }

    public void printSorted() {
        // prints the phone book, sorted by the number of phones. Выводит телефонную книгу, 
        // сортированную по количеству телефонов у пользователя, по убыванию
        System.out.print("{");
        for (int poolIndex = 0 ; poolIndex < this.poolsByNumAmount.size() ; poolIndex++) {
            for (String name : this.poolsByNumAmount.get(this.poolsByNumAmount.size() - poolIndex - 1)) {
                System.out.printf("%s=%s, ", name, this.phoneBook.get(name).toString());
            }
        }
        System.out.print("\b\b}\n"); // slash b things are for erasing the last comma with a space. Несколько /b для того, чтобы удалить ненужныю запятую и пробел
    }

}

public class PhoneNums {
    public static void main(String[] args) {
        PhoneBook exampleBook = new PhoneBook();
        exampleBook.add("cat", 37103);
        exampleBook.add("cat", 14523721);
        exampleBook.add("cat", 2435529);

        exampleBook.add("doggy", 197142);
        
        exampleBook.add("snake", 34725);

        exampleBook.add("fishy", 1035679);
        exampleBook.add("fishy", 896597);

        exampleBook.add("bear", 745233);
        exampleBook.add("bear", 2025755);
        exampleBook.add("bear", 979660);
        exampleBook.add("bear", 135094);

        exampleBook.add("wolf", 14346046);
        exampleBook.add("wolf", 1608896);
       
        exampleBook.removeNumber(1035679, "fishy");
        
        exampleBook.printSorted();
    }
}
