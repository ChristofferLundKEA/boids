package main.model;

import main.behavior.BehaviorStrategy;
import main.simulation.Forces;
import main.simulation.Vector2D;

import java.util.List;

public class RandomBoid implements BehaviorStrategy {

    @Override
    public Forces calculateForces(Boid boid, List<Boid> neighbors) {
        double randomX = (Math.random() - 0.5) * 0.02;
        double randomY = (Math.random() - 0.5) * 0.02;

        return new Forces(new Vector2D(randomX, randomY), Vector2D.ZERO, Vector2D.ZERO);
    }
}
