import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.*;

public class Router {
    private ArrayList<Link> neighbours;
    private Map<Router, List<Router>> dijkstraShortestPaths;
    private Map<Router, List<Router>> aStarShortestPaths;
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
            System.out.println("Packet arrived! Data: " + packet.getData());
        } else {
            List<Router> dijkstraShortestPath = getDijkstraShortestPath(packet.getDestination());
            dijkstraShortestPath.get(1).forwardPacket(packet);
        }
    }

    private List<Router> getDijkstraShortestPath(Router destination) {
        return dijkstraShortestPaths.get(destination);
    }

    public void setTopologyGraph(Graph<Router, Network.WeightedEdge> g) {
        this.topologyGraph = g;
    }

    public void getDijsktraShortestPaths() {
        DijkstraShortestPath<Router, Network.WeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(this.topologyGraph);
        ShortestPathAlgorithm.SingleSourcePaths<Router, Network.WeightedEdge> singleSourcePaths = dijkstraAlg.getPaths(this);
        dijkstraShortestPaths = new HashMap<>();
        for (Router r : topologyGraph.vertexSet()) {
            if (!r.equals(this)) {
                GraphPath<Router, Network.WeightedEdge> p = singleSourcePaths.getPath(r);
                dijkstraShortestPaths.put(r, p.getVertexList());
            }
        }
    }

    public void getAStarShortestPaths() {
        AStarAdmissibleHeuristic<Router> h = new AStarAdmissibleHeuristic<Router>() {
            @Override
            public double getCostEstimate(Router router, Router v1) {
                return Math.abs(v1.getAddress() - router.getAddress());
            }
        };
        AStarShortestPath<Router, Network.WeightedEdge> aStarAlg = new AStarShortestPath<>(this.topologyGraph, h);
        ShortestPathAlgorithm.SingleSourcePaths<Router, Network.WeightedEdge> singleSourcePaths = aStarAlg.getPaths(this);
        aStarShortestPaths = new HashMap<>();
        for (Router r : topologyGraph.vertexSet()) {
            if (!r.equals(this)) {
                GraphPath<Router, Network.WeightedEdge> p = singleSourcePaths.getPath(r);
                aStarShortestPaths.put(r, p.getVertexList());
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
