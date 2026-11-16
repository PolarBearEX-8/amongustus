package client.util;

import augustus.modules.movement.FastLadder;
import client.setting.ModeOptionSupplier;
import client.setting.SettingGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.MathHelper;

public abstract class VectorSorter extends ModeOptionSupplier {
   public boolean checkDouble(double offset) {
      int count = MathHelper.ceiling_double_int(MinecraftAccessor.minecraft.thePlayer.posX);
      int count2 = MathHelper.ceiling_double_int(MinecraftAccessor.minecraft.thePlayer.getBoundingBox2().minY + offset);
      int count3 = MathHelper.ceiling_double_int(MinecraftAccessor.minecraft.thePlayer.posZ);
      Block block = MinecraftAccessor.minecraft.thePlayer.worldObj.getIBlockState(count, count2, count3).getBlock();
      return block == Blocks.block81 || block == Blocks.block144;
   }

   public VectorSorter(FastLadder fastLadder, SettingGroup settingGroup, String text) {
      super(fastLadder, settingGroup, text);
   }
}
