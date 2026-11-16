package client.util;

public class ClientManager53Entry {
   public RotationPool rotationPool;
   public int count;
   public final GoalSetupManager goalSetupManager;

   public ClientManager53Entry(GoalSetupManager goalSetupManager2, int count2, RotationPool rotationPool2) {
      this.goalSetupManager = goalSetupManager2;
      this.count = count2;
      this.rotationPool = rotationPool2;
   }
}
