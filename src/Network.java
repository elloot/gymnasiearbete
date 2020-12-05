public class Network {
    private Area[] areas;
    private int numAreas;
    private int routersPerArea;

    public Network(int numAreas, int routersPerArea) {
        this.numAreas = numAreas;
        areas = new Area[numAreas];
        this.routersPerArea = routersPerArea;
        this.populate();
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
