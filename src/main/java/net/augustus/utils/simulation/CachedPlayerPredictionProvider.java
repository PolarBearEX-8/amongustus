package net.augustus.utils.simulation;

import net.augustus.utils.rotation.RotationParamBruteforcer;
import java.util.ArrayList;
import java.util.HashMap;

public class CachedPlayerPredictionProvider implements MovementPredictionProvider {
   public final SimulatedPlayerEntity simulatedPlayer;
   public final RotationParamBruteforcer bruteforcer;

   public CachedPlayerPredictionProvider(RotationParamBruteforcer rotationParamBruteforcer, SimulatedPlayerEntity simulatedPlayerEntity) {
      this.bruteforcer = rotationParamBruteforcer;
      this.simulatedPlayer = simulatedPlayerEntity;
   }

   private static ArrayList computePlayerTrajectory(SimulatedPlayerEntity simulatedPlayerEntity, int n, int n2, int n3, int n4, String str) {
      return TrajectoryPredictionUtil.predictPlayerPositions(simulatedPlayerEntity, n, n2, n3, n4, true);
   }

   @Override
   public ArrayList predictTrajectory(int n, int n2, int n3, int n4) {
      HashMap playerPredictionCache = RotationParamBruteforcer.getPlayerPredictionCache(this.bruteforcer);
      String str = n + "_" + n2 + "_" + n3 + "_" + n4;
      SimulatedPlayerEntity simulatedPlayer2 = this.simulatedPlayer;
      return (ArrayList)(playerPredictionCache.computeIfAbsent(str, obj -> computePlayerTrajectory(simulatedPlayer2, n, n2, n3, n4, (String)obj)));
   }
}
