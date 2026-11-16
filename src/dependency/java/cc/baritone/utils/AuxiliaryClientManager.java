package cc.baritone.utils;

public class AuxiliaryClientManager extends ExtendedClientManager {
   public AuxiliaryClientManager(int count) {
      super(String.format("Not enough arguments (expected at least %d)", count));
   }
}
