import org.jgrapht.Graph;

import java.util.ArrayList;

public class Router {
    private ArrayList<Link> neighbours;
    private Router[][] shortestPaths;
    private Graph topologyGraph;
    private int address;

    public Router() {
        neighbours = new ArrayList<Link>();
    }

    public void forwardPacket(Packet packet) {
        if (packet.getDestination().equals(this)) {
            this.handlePacket();
        }
    }

    public ArrayList<Link> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Link link) {

    }

    public int getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Router router = (Router) o;
        return address == router.address;
    }
}
