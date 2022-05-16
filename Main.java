import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        CircularLinkedList<String> newCLL = new CircularLinkedList<>();
        newCLL.add("Begin");
        newCLL.add("Two");
        newCLL.add("Three");
        newCLL.add("Four");
        newCLL.add("Five");


        // System.out.println(newCLL.remove("Five")); 
        newCLL.remove(5);


        Iterator<String> iterate = newCLL.iterator();
        int total = newCLL.getSize();

        System.out.println(total);

        int counter = 0;
        while(iterate.hasNext() && counter < newCLL.getSize()){
            System.out.println(iterate.next());
            counter++;
        }
    }


}
