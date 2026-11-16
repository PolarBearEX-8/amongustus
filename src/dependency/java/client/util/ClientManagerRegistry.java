package client.util;

public class ClientManagerRegistry extends VectorDirector {
   @Override
   public int computeCount(int count, int count2, int count3, int count4) {
      return this.computeCount(new int[]{count, count2, count3, count4});
   }

   public ClientManagerRegistry(long value, ComparingBiomesManager comparingBiomesManager) {
      super(value, comparingBiomesManager);
   }
}
