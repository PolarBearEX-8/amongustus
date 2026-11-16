package client.util;

import java.util.ArrayList;
import net.minecraft.util.Vec3;

public class ArrayListWrapper {
   public int count;
   public ArrayList arrayList;

   public void clear() {
      this.arrayList.clear();
   }

   public Vec3 get() {
      return (Vec3)this.arrayList.get(0);
   }

   public void add(Vec3 vec) {
      this.arrayList.add(vec);
      if (this.arrayList.size() > this.count) {
         this.arrayList.remove(0);
      }
   }

   public ArrayListWrapper(int count2) {
      this.arrayList = new ArrayList(count2);
      this.count = count2;
   }
}
