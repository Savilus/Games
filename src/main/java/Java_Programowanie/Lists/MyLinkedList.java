package Java_Programowanie.Lists;

public class MyLinkedList implements MyList{
   private Node head = new Node(null);
   private int size;

    //nastepnik
    //dane
    // poprzednik - nie trzeba
    // kiedy doszlismy do konca listy
    // jiesli nie bedzie nastepnego datawrapperu
    // next datawrapper jest nullem

    class Node<T> {
        private T data;
        private Node next;
        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

    }



   // private Node head;
    // private DataWrapper table[];
    @Override
    public int size() {
        // liczymy kazdym dodawaniu/odejmowaniu
        // nawigujemy po calej naszej liscie i zliczamy elementy
        return 0;
    }

    @Override
    public Data get(int index) {
        int countIndex = 0;
        // iterujemy po naszej liscie, za pomoca nexta
        // liczymy na ktorym jestesmy indexie
        // jesli znajdziemy - zwracamy
        // jesli doszlismy do konca a index jest wiekszy nix index oistatniego to mamy nulla
        return null;
    }

    @Override
    public void add(Data item) {
        if(this.head.next  == null){
            Node current = new Node(item, this.head);
            this.size++;
        } else if () {

        }
        // Node wrapper = new Node();
        // wrapper.data = item;
        // Noede last = ....;
        // last.next = wrapper;
        // zwiekszamy size o 1
    }

    @Override
    public void remove(Data item) {
        // pamietamy o 2 elementach, jak usuwamy srodkowy to poprzedni musi wskazywac na kolejnego
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        } else return false;
    }
}
