package client.util;

import client.setting.CombatNumberProperty;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class DampenerValidator implements ICommandSender {
   public final EntityPlayer entityPlayer;
   public final TileEntitySign tileEntitySign;

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
   }

   public DampenerValidator(TileEntitySign tileEntitySign2, EntityPlayer player) {
      this.tileEntitySign = tileEntitySign2;
      this.entityPlayer = player;
   }

   @Override
   public void applyCombatNumberProperty(CombatNumberProperty combatNumberProperty, int count) {
      TileEntitySign.resolveCommandStatsManager(this.tileEntitySign).applyICommandSender(this, combatNumberProperty, count);
   }

   @Override
   public BlockPos getBlockPos() {
      return this.tileEntitySign.blockPos;
   }

   @Override
   public Vec3 getVec3() {
      return new Vec3(
         (double)this.tileEntitySign.blockPos.getX() + 0.5,
         (double)this.tileEntitySign.blockPos.getY() + 0.5,
         (double)this.tileEntitySign.blockPos.getZ() + 0.5
      );
   }

   @Override
   public boolean matchesText(int count, String text) {
      return count <= 2;
   }

   @Override
   public IChatComponent getChatComponent() {
      return this.entityPlayer.getChatComponent();
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public Entity getEntity() {
      return this.entityPlayer;
   }

   @Override
   public String getText() {
      return this.entityPlayer.getText();
   }

   @Override
   public World getWorldObj() {
      return this.entityPlayer.getWorldObj();
   }
}
