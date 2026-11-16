package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.EntityBlaze;
import net.minecraft.entity.EntityLavaslime;
import net.minecraft.entity.EntityPigzombie;
import net.minecraft.entity.EntitySkeleton;

public class VirtualListEntry extends InternalRandomEntryAdapter {
   public List list = Lists.newArrayList();

   public VirtualListEntry() {
      this.list.add(new ClassHolder(EntityBlaze.class, 10, 2, 3));
      this.list.add(new ClassHolder(EntityPigzombie.class, 5, 4, 4));
      this.list.add(new ClassHolder(EntitySkeleton.class, 10, 4, 4));
      this.list.add(new ClassHolder(EntityLavaslime.class, 3, 4, 4));
   }

   @Override
   public boolean checkIndex(int count, int count2) {
      int count3 = count >> 4;
      int count4 = count2 >> 4;
      this.random.setSeed((long)(count3 ^ count4 << 4) ^ this.world.getTimestamp());
      this.random.nextInt();
      return this.random.nextInt(3) != 0
         ? false
         : (count != (count3 << 4) + 4 + this.random.nextInt(8) ? false : count2 == (count4 << 4) + 4 + this.random.nextInt(8));
   }

   @Override
   public AuxiliaryLinkedListEntry resolveAuxiliaryLinkedListEntry(int count, int count2) {
      return new TargetSynchronizer(this.world, this.random, count, count2);
   }

   @Override
   public String getText() {
      return "Fortress";
   }

   public List getListAsList() {
      return this.list;
   }
}
