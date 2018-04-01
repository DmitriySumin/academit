import ru.academit.myArrayList.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList();
        list.addElement(1);
        list.addElement(2);
        list.addElement(3, 3);
        list.getRemoveElement(3);
        list.trimToSize();
        System.out.println(list);
    }
}
