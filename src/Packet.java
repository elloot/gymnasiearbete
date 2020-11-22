public class Packet {
    private int id;
    private Router source;
    private Router destination;
    private String data;

    public Packet(int id, Router source, Router destination, String data) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public Router getSource() {
        return source;
    }

    public Router getDestination() {
        return destination;
    }

    public String getData() {
        return data;
    }
}
