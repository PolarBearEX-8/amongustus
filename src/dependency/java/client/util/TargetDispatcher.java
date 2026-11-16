package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.item.GoldenIngot;
import net.minecraft.world.World;

public class TargetDispatcher extends AuxiliaryLinkedListEntry {
   public TargetDispatcher(World world, Random random, int count, int count2) {
      super(count, count2);
      ClassEntry3ArrayEntry.updateState();
      ClassEntry3Entry classEntry3Entry = new ClassEntry3Entry(0, random, (count << 4) + 2, (count2 << 4) + 2);
      this.linkedList.add(classEntry3Entry);
      classEntry3Entry.applyGoldenIngot(classEntry3Entry, this.linkedList, random);
      List list = classEntry3Entry.list;

      while (!list.isEmpty()) {
         int count3 = random.nextInt(list.size());
         ((GoldenIngot)list.remove(count3)).applyGoldenIngot(classEntry3Entry, this.linkedList, random);
      }

      this.updateState();
      this.applyWorld(world, random, 10);
   }

   public TargetDispatcher() {
   }
}
