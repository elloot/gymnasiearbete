public class Computer {
    private Router router;

    public Computer(Router router) {
        this.router = router;
    }

    private void sendPacket(Packet packet) {
        this.router.forwardPacket(packet);
    }

    public <Data>Packet<Data> createPacket(Data data, Router destination) {
        Packet<Data> packet = new Packet<>(this.router, destination, data);
        this.sendPacket(packet);
        return packet;
    }
}
