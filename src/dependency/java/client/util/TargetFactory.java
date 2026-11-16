package client.util;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.item.GoldenIngot;
import net.minecraft.item.GoldenOrb;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TargetFactory extends AuxiliaryLinkedListEntry {
   public boolean enabled;

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   public TargetFactory() {
   }

   public TargetFactory(World world, Random random, int count, int count2, int count3) {
      super(count, count2);
      List list = AngleService.resolveList(random, count3);
      InvalidBiomeIdManagerEntry invalidBiomeIdManagerEntry = new InvalidBiomeIdManagerEntry(world.getInvalidBiomeIdManager(), 0, random, (count << 4) + 2, (count2 << 4) + 2, list, count3);
      this.linkedList.add(invalidBiomeIdManagerEntry);
      invalidBiomeIdManagerEntry.applyGoldenIngot(invalidBiomeIdManagerEntry, this.linkedList, random);
      List list2 = invalidBiomeIdManagerEntry.list;
      List list3 = invalidBiomeIdManagerEntry.list2;

      while (!list2.isEmpty() || !list3.isEmpty()) {
         if (list2.isEmpty()) {
            int count4 = random.nextInt(list3.size());
            ((GoldenIngot)list3.remove(count4)).applyGoldenIngot(invalidBiomeIdManagerEntry, this.linkedList, random);
         } else {
            int count5 = random.nextInt(list2.size());
            ((GoldenIngot)list2.remove(count5)).applyGoldenIngot(invalidBiomeIdManagerEntry, this.linkedList, random);
         }
      }

      this.updateState();
      int count6 = 0;
      Iterator iterator = this.linkedList.iterator();

      while (iterator.hasNext()) {
         if (!((GoldenIngot)iterator.next() instanceof GoldenOrb)) {
            count6++;
         }
      }

      this.enabled = count6 > 2;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.enabled = tagCompound.matchesText("Valid");
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      tagCompound.applyText("Valid", this.enabled);
   }
}
