package net.minecraft.entity;

import client.setting.MovementNumberProperty;
import client.util.IBlockState;
import client.util.VectorDispatcher;
import client.util.VectorFactory;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.world.World;

public class EntityCaveSpiderAdapter extends EntityMinecart {
   public final VectorDispatcher vectorDispatcher = new VectorFactory(this);
   public int count14 = 0;

   @Override
   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      this.vectorDispatcher.matchesEntityPlayerByEntityPlayer(player);
      return false;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.vectorDispatcher.applyNBTTagCompound(tagCompound);
      this.getDataWatcher().applyTargetByTarget(23, this.getVectorDispatcher().getLabelText());
      this.getDataWatcher().applyTargetByTarget(24, ChatComponentSerializer.toJson(this.getVectorDispatcher().getIChatComponent()));
   }

   @Override
   public void applyIndex4(int count) {
      super.applyIndex4(count);
      if (count == 24) {
         try {
            this.vectorDispatcher.setIChatComponent(ChatComponentSerializer.parseChatComponent(this.getDataWatcher().resolveText(24)));
         } catch (Throwable throwable) {
         }
      } else if (count == 23) {
         this.vectorDispatcher.applyText(this.getDataWatcher().resolveText(23));
      }
   }

   @Override
   public MovementNumberProperty getFURNACE() {
      return MovementNumberProperty.COMMAND_BLOCK;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, boolean flag) {
      if (flag && this.ticksExisted - this.count14 >= 4) {
         this.getVectorDispatcher().applyWorldByWorld(this.worldObj);
         this.count14 = this.ticksExisted;
      }
   }

   @Override
   public IBlockState getIBlockState() {
      return Blocks.block139.getDefaultState();
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.vectorDispatcher.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }

   public VectorDispatcher getVectorDispatcher() {
      return this.vectorDispatcher;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.getDataWatcher().applyTarget(23, "");
      this.getDataWatcher().applyTarget(24, "");
   }

   public EntityCaveSpiderAdapter(World world) {
      super(world);
   }

   public EntityCaveSpiderAdapter(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }
}
