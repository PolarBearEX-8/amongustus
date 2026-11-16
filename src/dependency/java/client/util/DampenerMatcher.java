package client.util;

import client.setting.CombatNumberProperty;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class DampenerMatcher implements ICommandSender {
   public static final DampenerMatcher dampenerMatcher = new DampenerMatcher();
   public StringBuffer stringBuffer = new StringBuffer();

   @Override
   public Vec3 getVec3() {
      return new Vec3(0.0, 0.0, 0.0);
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
   public void applyCombatNumberProperty(CombatNumberProperty combatNumberProperty, int count) {
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      this.stringBuffer.append(iChatComponent.getString());
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(0, 0, 0);
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public World getWorldObj() {
      return VanillaRunnable.getVanillaRunnable().getWorldObj();
   }

   @Override
   public String getText() {
      return "Rcon";
   }

   @Override
   public Entity getEntity() {
      return null;
   }
}
