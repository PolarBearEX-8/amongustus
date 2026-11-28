package net.augustus.utils.simulation;

import net.augustus.utils.rotation.RotationParamBruteforcer;
import java.util.ArrayList;
import java.util.HashMap;

public class CachedTargetPredictionProvider implements MovementPredictionProvider {
   public final SimulatedTargetEntity simulatedTarget;
   public final SimulatedPlayerEntity simulatedPlayer;
   public final RotationParamBruteforcer bruteforcer;

   public CachedTargetPredictionProvider(RotationParamBruteforcer rotationParamBruteforcer, SimulatedPlayerEntity simulatedPlayerEntity, SimulatedTargetEntity simulatedTargetEntity) {
      this.bruteforcer = rotationParamBruteforcer;
      this.simulatedPlayer = simulatedPlayerEntity;
      this.simulatedTarget = simulatedTargetEntity;
   }

   @Override
   public ArrayList predictTrajectory(int n, int n2, int n3, int n4) {
      HashMap targetPredictionCache = RotationParamBruteforcer.getTargetPredictionCache(this.bruteforcer);
      String str = n + "_" + n4;
      SimulatedTargetEntity simulatedTarget2 = this.simulatedTarget;
      SimulatedPlayerEntity simulatedPlayer2 = this.simulatedPlayer;
      return (ArrayList)targetPredictionCache.computeIfAbsent(str, obj -> {
         return TrajectoryPredictionUtil.predictTargetPositions(simulatedPlayer2, simulatedTarget2, n, n4, false);
      });
   }
}
