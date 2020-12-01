import java.util.Objects;

public class Packet {
    private int id;
    private Router source;
    private Router destination;
    private String data;

    public Packet(Router source, Router destination, String data) {
        // temporary id for hashing later
        this.id = (int) (Math.random() * 1000000);
        this.source = source;
        this.destination = destination;
        this.data = data;

        this.id = this.hashCode();
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

    @Override
    public int hashCode() {
        return Objects.hash(id, source, destination, data);
    }

    public String getData() {
        return data;
    }
}
