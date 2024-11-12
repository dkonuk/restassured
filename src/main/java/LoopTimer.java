public class LoopTimer {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        for (long i = 0; i < 1_000_000_000L; i++) {
            // Empty loop body
        }

        long endTime = System.nanoTime();
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;

        System.out.printf("Execution time: %.2f seconds%n", durationInSeconds);
    }
}