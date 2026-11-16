package cc.baritone.utils;

import net.minecraft.world.storage.WorldInfo;

public class DirectCustom {
   public final double value;
   public final double value2;
   public final double value3;
   public final double value4;

   public boolean checkIndex(int count, int count2) {
      return (double)count > this.value && (double)(count + 1) < this.value2 && (double)count2 > this.value3 && (double)(count2 + 1) < this.value4;
   }

   public boolean checkIndexByIndex(int count, int count2) {
      return (double)(count + 1) > this.value && (double)count < this.value2 && (double)(count2 + 1) > this.value3 && (double)count2 < this.value4;
   }

   public DirectCustom(WorldInfo worldInfo) {
      this.value = worldInfo.getValue9();
      this.value2 = worldInfo.getValue2();
      this.value3 = worldInfo.getValue();
      this.value4 = worldInfo.getValueAsDouble();
   }
}
