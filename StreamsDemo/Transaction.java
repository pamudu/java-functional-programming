package StreamsDemo;

public class Transaction {
    private Type type;
    private int id;
    private int value;

    public Transaction(Type type, int id, int value) {
        this.type = type;
        this.id = id;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public enum Type {
        GROCERY, CONSUMER_ELECTRONICS
    }
}
