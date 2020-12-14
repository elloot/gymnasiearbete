import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.*;

public class Router {
    private ArrayList<Link> neighbours;
    private Map<Router, List<Router>> shortestPaths;
    private Graph<Router, Network.WeightedEdge> topologyGraph;
    private int address;
    private int level;

    public Router(int address, int level) {
        neighbours = new ArrayList<Link>();
        this.address = address;
        this.level = level;
    }

    public void forwardPacket(Packet packet) {
        if (packet.getDestination().equals(this)) {
//            this.handlePacket();
        } else {
//            this.getShortestPath(packet.getDestination());
            // determine which router is next in the shortest path and forward packet to that router
        }
    }

    public void setTopologyGraph(Graph<Router, Network.WeightedEdge> g) {
        this.topologyGraph = g;
    }

    public void getShortestPaths() {
        DijkstraShortestPath<Router, Network.WeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(this.topologyGraph);
        ShortestPathAlgorithm.SingleSourcePaths<Router, Network.WeightedEdge> singleSourcePaths = dijkstraAlg.getPaths(this);
        shortestPaths = new HashMap<>();
        for (Router r : topologyGraph.vertexSet()) {
            if (!r.equals(this)) {
                GraphPath<Router, Network.WeightedEdge> p = singleSourcePaths.getPath(r);
                shortestPaths.put(r, p.getVertexList());
            }
        }
    }

    public ArrayList<Link> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Link link) {
        neighbours.add(link);
    }

    public int getAddress() {
        return address;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Router router = (Router) o;
        return address == router.address;
    }

    @Override
    public String toString() {
        return "Router{" +
                "address=" + address +
                ", level=" + level +
                '}';
    }
}
