package cc.baritone.utils;

import com.google.gson.annotations.SerializedName;

public class RecordNodeHandler {
   @SerializedName("x")
   public int count;
   @SerializedName("z")
   public int count2;

   @Override
   public String toString() {
      return this.count + ", " + this.count2;
   }
}
