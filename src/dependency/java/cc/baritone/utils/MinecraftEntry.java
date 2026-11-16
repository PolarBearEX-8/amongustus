package cc.baritone.utils;

import client.setting.WorldSettingsGameType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public final class MinecraftEntry implements DefaultListener {
   public final Minecraft mc;

   @Override
   public void updateState() {
      this.mc.playerController.updateState();
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player, World world, ItemStack stack) {
      return this.mc.playerController.sendUseItem(player, world, stack);
   }

   @Override
   public boolean isActive() {
      return this.mc.playerController.blockPos.getY() == -1;
   }

   @Override
   public boolean matchesEntityPlayerSP(EntityPlayerSP player, WorldClient world, ItemStack stack, BlockPos blockPos, EnumFacing enumFacing, Vec3 vec) {
      return this.mc.playerController.onPlayerRightClick(player, world, stack, blockPos, enumFacing, vec);
   }

   @Override
   public boolean matchesBlockPos(BlockPos blockPos, EnumFacing enumFacing) {
      return this.mc.playerController.onPlayerDamageBlock(blockPos, enumFacing);
   }

   @Override
   public void updateState2() {
      this.mc.playerController.updateController();
   }

   @Override
   public boolean matchesBlockPosByBlockPos(BlockPos blockPos, EnumFacing enumFacing) {
      return this.mc.playerController.matchesBlockPos(blockPos, enumFacing);
   }

   @Override
   public void setEnabled(boolean enabled) {
      this.mc.playerController.state = enabled;
   }

   @Override
   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.mc.playerController.getWorldSettingsGameType();
   }

   @Override
   public ItemStack resolveItemStack(int count, int count2, int count3, int count4, EntityPlayer player) {
      return this.mc.playerController.windowClick(count, count2, count3, count4, player);
   }

   public MinecraftEntry(Minecraft mc2) {
      this.mc = mc2;
   }
}
