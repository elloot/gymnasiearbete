public final class Link implements Comparable {
    private Router[] routers;
    private int weight;

    public Link(Router[] routers, int weight) {
        this.routers = routers;
        this.weight = weight;
    }

    public Router[] getRouters() {
        return routers;
    }

    public int getWeight() {
        return weight;
    }

    public Link swapRouterPositions() {
        Router[] newRouterConfig = {this.routers[1], this.routers[0]};
        return new Link(newRouterConfig, this.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link other = (Link) o;
        Router currentFirstRouter = this.routers[0];
        Router currentSecondRouter = this.routers[1];
        Router otherFirstRouter = other.getRouters()[0];
        Router otherSecondRouter = other.getRouters()[1];

        return currentFirstRouter.equals(otherFirstRouter) && currentSecondRouter.equals(otherSecondRouter) || currentFirstRouter.equals(otherSecondRouter) && currentSecondRouter.equals(otherFirstRouter);
    }

    @Override
    public int compareTo(Object o) {
        Link other = (Link) o;
        return Integer.compare(this.weight, other.weight);
    }
}
