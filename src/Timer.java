public class Timer {
    long startTime = 0;

    public Timer() {}

    public void start() {
        startTime = System.nanoTime();
    }

    public double nextMillis() {
        long totalTime = (System.nanoTime() - startTime) / 1000000;
        startTime = System.nanoTime();
        return totalTime;
    }
}
