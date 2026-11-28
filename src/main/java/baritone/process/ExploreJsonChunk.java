package baritone.process;

import com.google.gson.annotations.SerializedName;

public class ExploreJsonChunk {
   @SerializedName("x")
   public int x;
   @SerializedName("z")
   public int z;

   @Override
   public String toString() {
      return this.x + ", " + this.z;
   }
}
