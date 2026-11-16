package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.item.GoldenDagger;
import net.minecraft.item.GoldenMaterial;
import net.minecraft.util.BlockPos;

public class ClassEntry3Entry extends GoldenMaterial {
   public InternalClassEntry internalClassEntry;
   public GoldenDagger goldenDagger;
   public List list = Lists.newArrayList();

   @Override
   public BlockPos getBlockPos() {
      return this.goldenDagger != null ? this.goldenDagger.getBlockPos() : super.getBlockPos();
   }

   public ClassEntry3Entry(int count, Random random, int count2, int count3) {
      super(0, random, count2, count3);
   }

   public ClassEntry3Entry() {
   }
}
