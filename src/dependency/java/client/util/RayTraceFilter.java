package client.util;

import client.setting.CombatNumberProperty;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RayTraceFilter implements ICommandSender {
   public final ICommandSender iCommandSender;
   public final CommandStatsManager commandStatsManager;

   @Override
   public Entity getEntity() {
      return this.iCommandSender.getEntity();
   }

   @Override
   public Vec3 getVec3() {
      return this.iCommandSender.getVec3();
   }

   @Override
   public BlockPos getBlockPos() {
      return this.iCommandSender.getBlockPos();
   }

   @Override
   public World getWorldObj() {
      return this.iCommandSender.getWorldObj();
   }

   @Override
   public boolean isActive() {
      return this.iCommandSender.isActive();
   }

   @Override
   public IChatComponent getChatComponent() {
      return this.iCommandSender.getChatComponent();
   }

   @Override
   public boolean matchesText(int count, String text) {
      return true;
   }

   @Override
   public void applyCombatNumberProperty(CombatNumberProperty combatNumberProperty, int count) {
      this.iCommandSender.applyCombatNumberProperty(combatNumberProperty, count);
   }

   @Override
   public String getText() {
      return this.iCommandSender.getText();
   }

   public RayTraceFilter(CommandStatsManager commandStatsManager2, ICommandSender iCommandSender2) {
      this.commandStatsManager = commandStatsManager2;
      this.iCommandSender = iCommandSender2;
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      this.iCommandSender.applyIChatComponent(iChatComponent);
   }
}
