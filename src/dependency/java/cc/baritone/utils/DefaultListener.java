package cc.baritone.utils;

import client.setting.WorldSettingsGameType;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public interface DefaultListener {
   boolean matchesEntityPlayer(EntityPlayer player, World world, ItemStack stack);

   void updateState();

   default double getValue() {
      return this.getWorldSettingsGameType().canExecute() ? 5.0 : (double)((Float)SettingsTxtManager.getSettings().blockReachDistance.dataRef).floatValue();
   }

   boolean isActive();

   boolean matchesBlockPos(BlockPos blockPos, EnumFacing enumFacing);

   boolean matchesEntityPlayerSP(EntityPlayerSP player, WorldClient world, ItemStack stack, BlockPos blockPos, EnumFacing enumFacing, Vec3 vec);

   boolean matchesBlockPosByBlockPos(BlockPos blockPos, EnumFacing enumFacing);

   void updateState2();

   void setEnabled(boolean enabled);

   WorldSettingsGameType getWorldSettingsGameType();

   ItemStack resolveItemStack(int count, int count2, int count3, int count4, EntityPlayer player);
}
