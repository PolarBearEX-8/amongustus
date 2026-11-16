package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.item.GoldenIngot;
import net.minecraft.world.World;

public class TargetSynchronizer extends AuxiliaryLinkedListEntry {
   public TargetSynchronizer(World world, Random random, int count, int count2) {
      super(count, count2);
      ClassEntry2Entry classEntry2Entry = new ClassEntry2Entry(random, (count << 4) + 2, (count2 << 4) + 2);
      this.linkedList.add(classEntry2Entry);
      classEntry2Entry.applyGoldenIngot(classEntry2Entry, this.linkedList, random);
      List list = classEntry2Entry.list2;

      while (!list.isEmpty()) {
         int count3 = random.nextInt(list.size());
         ((GoldenIngot)list.remove(count3)).applyGoldenIngot(classEntry2Entry, this.linkedList, random);
      }

      this.updateState();
      this.applyWorld(world, random, 48, 70);
   }

   public TargetSynchronizer() {
   }
}
