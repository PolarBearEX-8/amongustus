package baritone.api.utils;

import net.minecraft.world.WorldSettings;
import baritone.api.BaritoneAPI;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public interface IPlayerController {
   boolean processRightClick(EntityPlayer player, World world, ItemStack stack);

   void syncHeldItem();

   boolean clickBlock(BlockPos pos, EnumFacing facing);

   ItemStack windowClick(int n, int n2, int n3, int n4, EntityPlayer player);

   boolean hasBrokenBlock();

   WorldSettings.GameType getGameType();

   void setHittingBlock(boolean flag);

   default double getBlockReachDistance() {
      return this.getGameType().isCreative() ? 5.0 : (double)((Float)BaritoneAPI.getSettings().blockReachDistance.value).floatValue();
   }

   boolean processRightClickBlock(EntityPlayerSP player, WorldClient world, ItemStack stack, BlockPos pos, EnumFacing facing, Vec3 vec);

   boolean onPlayerDamageBlock(BlockPos pos, EnumFacing facing);

   void resetBlockRemoving();
}
