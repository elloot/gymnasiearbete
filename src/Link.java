public final class Link implements Comparable {
    private Router[] routers;
    private int weight;

    public Link(Router[] routers, int weight) {
        this.routers = routers;
        this.weight = weight;
    }

    @Override
    public int compareTo(Object o) {
        Link other = (Link) o;
        return Integer.compare(this.weight, other.weight);
    }
}
