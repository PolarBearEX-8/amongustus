package client.setting;

public enum MovementWeightProperty {
    SHA3_224(224),
    SHA3_384(384),
    SHA3_256(256),
    SHA3_512(512);

   public final int count;

   public int getCount() {
      return this.count;
   }

   private MovementWeightProperty(int count2) {
      this.count = count2;
   }



}
