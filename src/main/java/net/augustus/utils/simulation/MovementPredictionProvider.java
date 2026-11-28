package net.augustus.utils.simulation;

import java.util.ArrayList;

public interface MovementPredictionProvider {
   ArrayList predictTrajectory(int n, int n2, int n3, int n4);
}
