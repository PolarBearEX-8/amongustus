package client.util;

import client.setting.CombatNumberProperty;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class AngleWatcher implements ICommandSender {
   public final TileEntitySign tileEntitySign;

   public AngleWatcher(TileEntitySign tileEntitySign2) {
      this.tileEntitySign = tileEntitySign2;
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public String getText() {
      return "Sign";
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
   }

   @Override
   public IChatComponent getChatComponent() {
      return new ChatComponentText(this.getText());
   }

   @Override
   public boolean matchesText(int count, String text) {
      return true;
   }

   @Override
   public BlockPos getBlockPos() {
      return this.tileEntitySign.blockPos;
   }

   @Override
   public void applyCombatNumberProperty(CombatNumberProperty combatNumberProperty, int count) {
   }

   @Override
   public Entity getEntity() {
      return null;
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
   public World getWorldObj() {
      return this.tileEntitySign.world;
   }
}
