package net.minecraft.command;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public interface ICommandSender {
   Entity getCommandSenderEntity();

   void addChatMessage(IChatComponent component);

   boolean canCommandSenderUseCommand(int n, String str);

   Vec3 getPositionVector();

   BlockPos getPosition();

   boolean sendCommandFeedback();

   World getEntityWorld();

   String getName();

   void setCommandStat(CommandResultStats.Type type, int n);

   IChatComponent getDisplayName();
}
