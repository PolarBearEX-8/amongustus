package net.minecraft.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMinecartCommandBlock extends EntityMinecart {
   private final CommandBlockLogic commandBlockLogic = new EntityMinecartCommandBlock.DefaultCommandBlockLogic(this);
   private int activatorRailCooldown = 0;

   @Override
   public boolean interactFirst(EntityPlayer player) {
      this.commandBlockLogic.tryOpenEditCommandBlock(player);
      return false;
   }

   @Override
   public void entityInit() {
      super.entityInit();
      this.getDataWatcher().addObject(23, "");
      this.getDataWatcher().addObject(24, "");
   }

   @Override
   public void onDataWatcherUpdate(int n) {
      super.onDataWatcherUpdate(n);
      if (n == 24) {
         try {
            this.commandBlockLogic.setLastOutput(IChatComponent.Serializer.jsonToComponent(this.getDataWatcher().getWatchableObjectString(24)));
         } catch (Throwable e) {
         }
      } else if (n == 23) {
         this.commandBlockLogic.setCommand(this.getDataWatcher().getWatchableObjectString(23));
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      this.commandBlockLogic.writeDataToNBT(tag);
   }

   @Override
   public EntityMinecart.EnumMinecartType getMinecartType() {
      return EntityMinecart.EnumMinecartType.COMMAND_BLOCK;
   }

   public CommandBlockLogic getCommandBlockLogic() {
      return this.commandBlockLogic;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.commandBlockLogic.readDataFromNBT(tag);
      this.getDataWatcher().updateObject(23, this.getCommandBlockLogic().getCommand());
      this.getDataWatcher().updateObject(24, IChatComponent.Serializer.componentToJson(this.getCommandBlockLogic().getLastOutput()));
   }

   @Override
   public void onActivatorRailPass(int n, int n2, int n3, boolean flag) {
      if (flag && this.ticksExisted - this.activatorRailCooldown >= 4) {
         this.getCommandBlockLogic().trigger(this.entityWorld);
         this.activatorRailCooldown = this.ticksExisted;
      }
   }

   @Override
   public IBlockState getDefaultDisplayTile() {
      return Blocks.command_block.getDefaultState();
   }

   public EntityMinecartCommandBlock(World world, double d, double d2, double d3) {
      super(world, d, d2, d3);
   }

   public EntityMinecartCommandBlock(World world) {
      super(world);
   }


   public static class DefaultCommandBlockLogic extends CommandBlockLogic {
      public final EntityMinecartCommandBlock commandSenderEntity;

      @Override
      public int getCommandBlockType() {
         return 1;
      }

      public DefaultCommandBlockLogic(EntityMinecartCommandBlock block) {
         this.commandSenderEntity = block;
      }

      @Override
      public BlockPos getPosition() {
         return new BlockPos(this.commandSenderEntity.posX, this.commandSenderEntity.posY + 0.5, this.commandSenderEntity.posZ);
      }

      @Override
      public Vec3 getPositionVector() {
         return new Vec3(this.commandSenderEntity.posX, this.commandSenderEntity.posY, this.commandSenderEntity.posZ);
      }

      @Override
      public void updateCommand() {
         this.commandSenderEntity.getDataWatcher().updateObject(23, this.getCommand());
         this.commandSenderEntity.getDataWatcher().updateObject(24, IChatComponent.Serializer.componentToJson(this.getLastOutput()));
      }

      @Override
      public World getEntityWorld() {
         return this.commandSenderEntity.entityWorld;
      }

      @Override
      public void fillInInfo(ByteBuf buf) {
         buf.writeInt(this.commandSenderEntity.getEntityId());
      }

      @Override
      public Entity getCommandSenderEntity() {
         return this.commandSenderEntity;
      }
   }
}
