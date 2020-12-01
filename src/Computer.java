public class Computer {
    private Router router;

    public Computer(Router router) {
        this.router = router;
    }

    private void sendPacket(Packet packet) {
        this.router.forwardPacket(packet);
    }

    public Packet createPacket(String data, Router destination) {
        Packet packet = new Packet(this.router, destination, data);
        this.sendPacket(packet);
        return packet;
    }
}
