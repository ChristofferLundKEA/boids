package main;

import main.simulation.FlockSimulation;
import main.spatial.*;

public class Microbench {
    private static int numberOfBoids = 1100;
    private static int iterations = 200;

    public static void main(String[] args) {
        System.out.println("Starting Microbench...");
        System.out.println("Boids: " + numberOfBoids + ", Iterations: " + iterations);
        System.out.println();

        // NaiveSpatialIndex
        System.out.println("NaiveSpatialIndex: ");
        SpatialIndex naive = new NaiveSpatialIndex();
        double naiveTime = benchmarkSpatialIndex(naive, numberOfBoids);
        System.out.print("Average time: " + naiveTime);
        System.out.println();
        System.out.println();

        // SpatialHashIndex
        System.out.println("SpatialHashIndex: ");
        SpatialIndex spatialHash = new SpatialHashIndex(1200, 800, 100);
        double hashTime = benchmarkSpatialIndex(spatialHash, numberOfBoids);
        System.out.print("Average time: " + hashTime);
        System.out.println();
        System.out.println();

         // KDTreeSpatialIndex
         System.out.println("KDTreeSpatialIndex: ");
         SpatialIndex kdTree = new KDTreeSpatialIndex();
         double kdTime = benchmarkSpatialIndex(kdTree, numberOfBoids);
         System.out.print("Average time: " + kdTime);
         System.out.println();
         System.out.println();

         // QuadTreeSpatialIndex
         System.out.println("QuadTreeSpatialIndex: ");
         SpatialIndex quadTree = new QuadTreeSpatialIndex(1200, 800);
         double quadTime = benchmarkSpatialIndex(quadTree, numberOfBoids);
         System.out.print("Average time: " + quadTime);
         System.out.println();
         System.out.println();
    }

    private static double benchmarkSpatialIndex(SpatialIndex spatialIndex, int numBoids) {
        FlockSimulation simulation = new FlockSimulation(1200, 800);
        simulation.setSpatialIndex(spatialIndex);

        for (int i = 0; i < numBoids; i++) {
            simulation.addBoid();
        }

        // Actual benchmark
        long totalTime = 0;

        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            simulation.update();
            long endTime = System.nanoTime();

            totalTime += (endTime - startTime);
        }

        double averageTimeMs = (totalTime / (double) iterations) / 1000000;
        return averageTimeMs;
    }
}