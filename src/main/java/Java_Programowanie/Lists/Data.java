package Java_Programowanie.Lists;

public class Data {

    private int integer;
    private char character;
    private Object object;

    public Data(int integer) {
        this.integer = integer;
    }

    public Data(char character) {
        this.character = character;
    }

    public Data(Object object) {
        this.object = object;
    }

    public int getInteger() {
        return integer;
    }

    public char getCharacter() {
        return character;
    }

    public Object getObject() {
        return object;
    }
}
