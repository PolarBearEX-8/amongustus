package net.minecraft.command;

import client.setting.CombatNumberProperty;
import client.util.StandardListener87EntryAdapter;
import client.util.VanillaRunnable;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandBlockOutputCommandSender implements ICommandSender {
   public final Entity entity;
   public final ICommandSender iCommandSender;
   public final BlockPos blockPos;
   public final double value;
   public final double value2;
   public final double value3;
   public final StandardListener87EntryAdapter standardListener87EntryAdapter;

   @Override
   public String getText() {
      return this.entity.getText();
   }

   @Override
   public Entity getEntity() {
      return this.entity;
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      this.iCommandSender.applyIChatComponent(iChatComponent);
   }

   @Override
   public Vec3 getVec3() {
      return new Vec3(this.value, this.value2, this.value3);
   }

   @Override
   public IChatComponent getChatComponent() {
      return this.entity.getChatComponent();
   }

   @Override
   public void applyCombatNumberProperty(CombatNumberProperty combatNumberProperty, int count) {
      this.entity.applyCombatNumberProperty(combatNumberProperty, count);
   }

   public CommandBlockOutputCommandSender(
      StandardListener87EntryAdapter standardListener87EntryAdapter2, Entity entity2, ICommandSender iCommandSender2, BlockPos blockPos2, double posX, double posY, double posZ
   ) {
      this.standardListener87EntryAdapter = standardListener87EntryAdapter2;
      this.entity = entity2;
      this.iCommandSender = iCommandSender2;
      this.blockPos = blockPos2;
      this.value = posX;
      this.value2 = posY;
      this.value3 = posZ;
   }

   @Override
   public boolean isActive() {
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      return vanillaRunnable == null || vanillaRunnable.worldServerArray[0].getDoFireTickManager().matchesText("commandBlockOutput");
   }

   @Override
   public World getWorldObj() {
      return this.entity.worldObj;
   }

   @Override
   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   @Override
   public boolean matchesText(int count, String text) {
      return this.iCommandSender.matchesText(count, text);
   }
}
