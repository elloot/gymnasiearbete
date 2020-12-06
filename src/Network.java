import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.Random;

public class Network {
    private Area[] areas;
    private int numAreas;
    private int routersPerArea;
    private static Random r = new Random();

    public Network(int numAreas, int routersPerArea) {
        this.numAreas = numAreas;
        areas = new Area[numAreas];
        this.routersPerArea = routersPerArea;
        this.populate();
        this.setLvlOneTwoNeighbours();

        // TODO: visualise graph
        Graph<Router, DefaultWeightedEdge> g = buildGraph();
        DijkstraShortestPath<Router, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(g);
        Router r1 = areas[3].getRouters()[0];
        Router r2 = areas[0].getRouters()[3];
        ShortestPathAlgorithm.SingleSourcePaths<Router, DefaultWeightedEdge> rPaths = dijkstraAlg.getPaths(r1);

        System.out.println(rPaths.getPath(r2));
    }

    public void setLvlOneTwoNeighbours() {
        for (int i = 0; i < areas.length; i++) {
            Area currentArea = areas[i];
            Router currentOneTwo = currentArea.getLvlOneTwo();
            Area nextArea = areas[i + 1 < areas.length ? i + 1 : 0];
            Router nextOneTwo = nextArea.getLvlOneTwo();
            currentArea.getLvlOneTwo().addNeighbour(createNeighbourLink(currentOneTwo, nextOneTwo));
        }
    }

    private Link createNeighbourLink(Router r1, Router r2) {
        return new Link(neighbourArray(r1, r2), randomWeight());
    }

    private int randomWeight() {
        return r.nextInt(10) + 1;
    }

    private Router[] neighbourArray(Router r1, Router r2) {
        return new Router[]{r1, r2};
    }

    // fills the Network with Areas and subsequent Routers
    public void populate() {
        int routerAddress = 1;
        int areaAddress = 1;
        for (int i = 0; i < numAreas; i++) {
            Router[] routers = new Router[routersPerArea];
            for (int j = 0; j < routersPerArea; j++) {
                routers[j] = createRouter(routerAddress, j);
                routerAddress++;
            }
            areas[i] = createArea(areaAddress, routers);
            areaAddress++;
        }
    }

    public Graph<Router, DefaultWeightedEdge> buildGraph() {
        Graph<Router, DefaultWeightedEdge> g = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        for (Area currentArea : areas) {
            for (Router currentRouter : currentArea.getRouters()) {
                for (Link currentNeighbourLink : currentRouter.getNeighbours()) {
                    Router r1 = currentNeighbourLink.getRouters()[0];
                    Router r2 = currentNeighbourLink.getRouters()[1];
                    g.addVertex(r1);
                    g.addVertex(r2);
                    g.addEdge(r1, r2);
                    g.setEdgeWeight(r1, r2, currentNeighbourLink.getWeight());
                }
            }
        }

        return g;
    }

    private Router createRouter(int routerAddress, int j) {
        // creates a new router, if the router is the last one to be created
        // in this loop,
        // set it to be a lvl 1-2 router
        return new Router(routerAddress, j % (routersPerArea - 1) == 0 && j != 0 ? 12 : 1);
    }

    private Area createArea(int areaAddress, Router[] routers) {
        return new Area(areaAddress, routers);
    }
}
