package net.augustus.modules.modes.fastladder;

import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.movement.FastLadder;
import net.augustus.settings.SettingGroup;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;

public abstract class FastLadderMode extends AbstractSubMode {

   public FastLadderMode(FastLadder fastLadder, SettingGroup doubleValue, String str) {
      super(fastLadder, doubleValue, str);
   }

   public boolean isClimbableAtYOffset(double d) {
      int n = MathHelper.floor_double(mc.thePlayer.posX);
      int n2 = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minY + d);
      int n3 = MathHelper.floor_double(mc.thePlayer.posZ);
      Block block = mc.thePlayer.entityWorld.getBlockState2(n, n2, n3).getBlock();
      return block == Blocks.ladder || block == Blocks.vine && ((FastLadder)this.parentModule).vines.getValue();
   }



}
