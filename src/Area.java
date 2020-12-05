import java.util.Arrays;

public class Area {
    private int address;
    private Router[] routers;

    public Area(int address, Router[] routers) {
        this.routers = routers;
        this.address = address;
    }

    public Router getLvlOneTwo() {
        for (Router router : routers) {
            if (router.getLevel() == 12) {
                return router;
            }
        }

        return new Router(-1, 1);
    }

    @Override
    public String toString() {
        return "Area{" +
                "address=" + address +
                ", routers=" + Arrays.toString(routers) +
                '}';
    }
}
