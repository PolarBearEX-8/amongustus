package client.setting;

import client.util.MinecraftAccessor;
import client.util.MoveUtil;
import java.util.function.Function;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedSandstoneStairs;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.ExtraBlockAdapter;

public enum AutoJumpSlimeBlocksType implements NamedOption {
   SLIME_BLOCKS("SlimeBlocks", false, AutoJumpSlimeBlocksType::resolveBooleanByBlock),
   STAIRS("Stairs", true, AutoJumpSlimeBlocksType::resolveBoolean2),
   HALF_SLABS("HalfSlabs", false, AutoJumpSlimeBlocksType::resolveBoolean);

   public final String labelText;
   public boolean enabled = true;
   public BooleanSetting booleanSetting;
   public BooleanSetting booleanSetting2;
   public Function<Block, Boolean> function;
   public static final AutoJumpSlimeBlocksType[] ENUM_VALUES = new AutoJumpSlimeBlocksType[]{SLIME_BLOCKS, STAIRS, HALF_SLABS};

   public boolean checkBlock(Block block) {
      return this.function.apply(block)
         && (MoveUtil.canExecute() || !this.booleanSetting.isEnabled())
         && (MinecraftAccessor.minecraft.thePlayer.isRiding() || !this.booleanSetting2.isEnabled());
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static BooleanSetting getBooleanSetting(AutoJumpSlimeBlocksType autoJumpSlimeBlocksType, BooleanSetting setting) {
      return autoJumpSlimeBlocksType.booleanSetting = setting;
   }

   private static Boolean resolveBoolean(Block block) {
      return block instanceof ExtraBlockAdapter && block.getCoordOffset6() == 0.5;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   private static Boolean resolveBoolean2(Block block) {
      return block instanceof BlockRedSandstoneStairs
         && (MinecraftAccessor.minecraft.thePlayer.posY % 1.0 != 0.0 || MinecraftAccessor.minecraft.thePlayer.isCollidedHorizontally);
   }

   public static BooleanSetting getBooleanSetting2(AutoJumpSlimeBlocksType autoJumpSlimeBlocksType, BooleanSetting setting) {
      return autoJumpSlimeBlocksType.booleanSetting2 = setting;
   }

   private static Boolean resolveBooleanByBlock(Block block) {
      return block instanceof BlockSlime;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private AutoJumpSlimeBlocksType(String text, boolean flag, Function<Block, Boolean> mapper) {
      this.labelText = text;
      this.enabled = flag;
      this.function = mapper;
   }
}
