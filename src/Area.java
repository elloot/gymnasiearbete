import java.util.Arrays;
import java.util.Random;

public class Area {
    private int address;
    private Router[] routers;
    private static final Random r = new Random();

    public Area(int address, Router[] routers) {
        this.routers = routers;
        this.address = address;
        setNeighbours();
    }

    public Router getLvlOneTwo() {
        for (Router router : routers) {
            if (router.getLevel() == 12) {
                return router;
            }
        }
        return new Router(-1, 1);
    }

    private void setNeighbours() {
        // TODO: maybe change Routers neighbour array to be Set so each Router can have more than one neighbour
        // and we can be sure it doesn't have two of the same neighbours
        for (Router currentRouter : routers) {
            Router otherRouter = getDifferentRouter(currentRouter);
            Link neighbourLink = createNeighbourLink(currentRouter, otherRouter);
            // both routers add the same link between them
            currentRouter.addNeighbour(neighbourLink);
            otherRouter.addNeighbour(neighbourLink);
        }
    }

    private Router getDifferentRouter(Router r) {
        Router dR;
        // get a random router until the random router is different
        // from this router
        do {
            dR = getRandomRouter();
        } while (dR == r);
        return dR;
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

    private Router getRandomRouter() {
        return routers[r.nextInt(routers.length)];
    }

    @Override
    public String toString() {
        return "Area{" +
                "address=" + address +
                ", routers=" + Arrays.toString(routers) +
                '}';
    }
}
