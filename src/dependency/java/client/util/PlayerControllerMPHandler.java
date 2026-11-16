package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PlayerControllerMPHandler extends PlayerControllerMP {
   public boolean enabled = false;
   public BlockPos blockPos2 = null;
   public Entity entity = null;

   public BlockPos getBlockPos2() {
      return this.blockPos2;
   }

   @Override
   public boolean matchesBlockPos(BlockPos blockPos, EnumFacing enumFacing) {
      this.enabled = true;
      this.blockPos2 = blockPos;
      boolean flag = super.matchesBlockPos(blockPos, enumFacing);
      this.enabled = false;
      return flag;
   }

   @Override
   public boolean onPlayerDamageBlock(BlockPos blockPos, EnumFacing enumFacing) {
      this.enabled = true;
      this.blockPos2 = blockPos;
      boolean flag = super.onPlayerDamageBlock(blockPos, enumFacing);
      this.enabled = false;
      return flag;
   }

   @Override
   public boolean sendUseItem(EntityPlayer player, World world, ItemStack stack) {
      this.enabled = true;
      boolean flag = super.sendUseItem(player, world, stack);
      this.enabled = false;
      return flag;
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player, Entity entity2) {
      this.entity = entity2;
      return super.matchesEntityPlayer(player, entity2);
   }

   @Override
   public boolean onPlayerRightClick(EntityPlayerSP player, WorldClient world, ItemStack stack, BlockPos blockPos, EnumFacing enumFacing, Vec3 vec) {
      this.enabled = true;
      this.blockPos2 = blockPos;
      boolean flag = super.onPlayerRightClick(player, world, stack, blockPos, enumFacing, vec);
      this.enabled = false;
      return flag;
   }

   public Entity getEntityAsEntity() {
      return this.entity;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player, Entity entity2, MovingObjectPosition hitResult) {
      this.entity = entity2;
      return super.matchesEntityPlayer(player, entity2, hitResult);
   }

   public PlayerControllerMPHandler(Minecraft mc, NetHandlerPlayClient netHandlerPlayClient) {
      super(mc, netHandlerPlayClient);
   }
}
