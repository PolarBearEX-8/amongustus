package net.optifine.override;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PlayerControllerOF extends PlayerControllerMP {
   private boolean acting = false;
   private BlockPos lastClickBlockPos = null;
   private Entity lastClickEntity = null;

   @Override
   public boolean sendUseItem(EntityPlayer player, World world, ItemStack stack) {
      this.acting = true;
      boolean flag = super.sendUseItem(player, world, stack);
      this.acting = false;
      return flag;
   }

   @Override
   public boolean clickBlock(BlockPos pos, EnumFacing facing) {
      this.acting = true;
      this.lastClickBlockPos = pos;
      boolean flag = super.clickBlock(pos, facing);
      this.acting = false;
      return flag;
   }

   @Override
   public boolean onPlayerDamageBlock(BlockPos pos, EnumFacing facing) {
      this.acting = true;
      this.lastClickBlockPos = pos;
      boolean flag = super.onPlayerDamageBlock(pos, facing);
      this.acting = false;
      return flag;
   }

   @Override
   public boolean onPlayerRightClick(EntityPlayerSP player, WorldClient world, ItemStack stack, BlockPos pos, EnumFacing facing, Vec3 vec) {
      this.acting = true;
      this.lastClickBlockPos = pos;
      boolean flag = super.onPlayerRightClick(player, world, stack, pos, facing, vec);
      this.acting = false;
      return flag;
   }

   public Entity getLastClickEntity() {
      return this.lastClickEntity;
   }

   @Override
   public boolean isPlayerRightClickingOnEntity(EntityPlayer player, Entity entity, MovingObjectPosition hit) {
      this.lastClickEntity = entity;
      return super.isPlayerRightClickingOnEntity(player, entity, hit);
   }

   public BlockPos getLastClickBlockPos() {
      return this.lastClickBlockPos;
   }

   public PlayerControllerOF(Minecraft mc2, NetHandlerPlayClient netHandlerPlayClient) {
      super(mc2, netHandlerPlayClient);
   }

   @Override
   public boolean interactWithEntitySendPacket(EntityPlayer player, Entity entity) {
      this.lastClickEntity = entity;
      return super.interactWithEntitySendPacket(player, entity);
   }

   public boolean isActing() {
      return this.acting;
   }
}
