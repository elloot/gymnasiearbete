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
    }

    public ArrayList<Link> getNeighbours() {
        return neighbours;
    }

    public int getAddress() {
        return address;
    }
}
