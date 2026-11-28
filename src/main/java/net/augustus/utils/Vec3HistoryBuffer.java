package net.augustus.utils;

import java.util.ArrayList;
import net.minecraft.util.Vec3;

public class Vec3HistoryBuffer {
   private int maxSize;
   private ArrayList positions;

   public Vec3HistoryBuffer(int n) {
      this.positions = new ArrayList(n);
      this.maxSize = n;
   }

   public Vec3 getOldest() {
      return (Vec3)this.positions.get(0);
   }

   public void clear() {
      this.positions.clear();
   }

   public void add(Vec3 vec) {
      this.positions.add(vec);
      if (false && this.positions.size() > this.maxSize) {
         this.positions.remove(0);
      }
   }
}
