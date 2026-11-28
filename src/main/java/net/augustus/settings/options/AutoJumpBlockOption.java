package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.settings.Setting;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.UnaryFunction;
import net.augustus.modules.movement.Fly;
import net.augustus.settings.BooleanValue;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public enum AutoJumpBlockOption implements SelectableOption {
   SLIME_BLOCKS("SlimeBlocks", false, obj -> ((IBlockState)obj).getBlock() instanceof BlockSlime),
   STAIRS("Stairs", true, obj -> {
      return ((IBlockState)obj).getBlock() instanceof BlockStairs && (MC.mc.thePlayer.posY % 1.0 != 0.0 || MC.mc.thePlayer.isCollidedHorizontally);
   }),
   HALF_SLABS("HalfSlabs", false, obj -> {
      return ((IBlockState)obj).getBlock() instanceof BlockSlab && ((IBlockState)obj).getBlock().getBlockBoundsMaxY() == 0.5 && ((IBlockState)obj).getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM;
   });
   private final String name;
   private boolean selected = true;
   private BooleanValue onlyWhileMovingSetting;
   private BooleanValue onlyWhileSprintingSetting;
   private UnaryFunction blockMatcher;

   private AutoJumpBlockOption(String str, boolean flag, UnaryFunction unaryFunction) {
      this.name = str;
      this.selected = flag;
      this.blockMatcher = unaryFunction;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public boolean shouldAutoJump(IBlockState state) {
      if (!(Boolean)this.blockMatcher.apply(state)) {
         return false;
      }
      if (!MoveUtil.isMoving() && this.onlyWhileMovingSetting.getValue()) {
         return false;
      }
      if (MC.mc.thePlayer.isSprinting()) {
         return true;
      }
      return !this.onlyWhileSprintingSetting.getValue();
   }

   public static BooleanValue setOnlyWhileMovingSetting(AutoJumpBlockOption autoJumpBlockOption, BooleanValue booleanValue) {
      return autoJumpBlockOption.onlyWhileMovingSetting = booleanValue;
   }

   public static BooleanValue setOnlyWhileSprintingSetting(AutoJumpBlockOption autoJumpBlockOption, BooleanValue booleanValue) {
      return autoJumpBlockOption.onlyWhileSprintingSetting = booleanValue;
   }


}
