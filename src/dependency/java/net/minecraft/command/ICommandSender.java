package net.minecraft.command;

import client.setting.CombatNumberProperty;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public interface ICommandSender {
   boolean matchesText(int count, String text);

   Vec3 getVec3();

   void applyCombatNumberProperty(CombatNumberProperty combatNumberProperty, int count);

   String getText();

   boolean isActive();

   IChatComponent getChatComponent();

   void applyIChatComponent(IChatComponent iChatComponent);

   World getWorldObj();

   BlockPos getBlockPos();

   Entity getEntity();
}
