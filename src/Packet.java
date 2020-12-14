import java.util.Objects;

public class Packet<Data> {
    private int id;
    private Router source;
    private Router destination;
    private Data data;

    public Packet(Router source, Router destination, Data data) {
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

    public Data getData() {
        return data;
    }
}
