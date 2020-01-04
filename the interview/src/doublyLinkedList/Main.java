package doublyLinkedList;

public class Main {
    public static void main(String[] args) {

        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addFirst(0);

//        System.out.println(linkedList.removeLast());
        for (Integer integer : linkedList) {
            System.out.println(integer);
        }
    }
}
