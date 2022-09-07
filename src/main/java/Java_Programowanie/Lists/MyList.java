package Java_Programowanie.Lists;

public interface MyList {
    int size();
    Data get(int index);
    void add(Data item);
    void remove(Data item);

    boolean isEmpty();
}
