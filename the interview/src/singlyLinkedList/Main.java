package singlyLinkedList;

public class Main {
    public static void main(String[] args) {

        SinglyLinkedList<String> linkedList = new SinglyLinkedList<>();
        linkedList.addLast("aba");
        linkedList.addLast("kada");
        linkedList.addLast("kada");
        linkedList.addFirst("ko");

//        System.out.println(linkedList.contains("ab"));
        for (String str : linkedList) {
            System.out.println(str);
        }
    }
}
