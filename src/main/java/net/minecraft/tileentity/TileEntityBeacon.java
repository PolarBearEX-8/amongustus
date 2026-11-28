package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;

public class TileEntityBeacon extends TileEntityLockable implements IInventory, ITickable {
   public static final Potion[][] effectsList = new Potion[][]{
      {Potion.moveSpeed, Potion.digSpeed}, {Potion.resistance, Potion.jump}, {Potion.damageBoost}, {Potion.regeneration}
   };
   private final List beamSegments = Lists.newArrayList();
   private long beamRenderCounter;
   private float beamRenderScale;
   private boolean isComplete;
   private int levels = -1;
   private int primaryEffect;
   private int secondaryEffect;
   private ItemStack payment;
   public String customName;

   @Override
   public ItemStack getStackInSlot(int n) {
      return n == 0 ? this.payment : null;
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.worldObj.getTileEntity(this.pos) != this
         ? false
         : player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void clear() {
      this.payment = null;
   }

   @Override
   public int getSizeInventory() {
      return 1;
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new ContainerBeacon(inventoryPlayer, this);
   }

   @Override
   public int getInventoryStackLimit() {
      return 1;
   }

   public void updateBeacon() {
      this.updateSegmentColors();
      this.addEffectsToPlayers();
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (n != 0 || this.payment == null) {
         return null;
      } else if (n2 >= this.payment.stackSize) {
         ItemStack payment2 = this.payment;
         this.payment = null;
         return payment2;
      } else {
         this.payment.stackSize -= n2;
         return new ItemStack(this.payment.getItem(), n2, this.payment.getMetadata());
      }
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound tag = new NBTTagCompound();
      this.writeToNBT(tag);
      return new S35PacketUpdateTileEntity(this.pos, 3, tag);
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return stack.getItem() == Items.emerald || stack.getItem() == Items.diamond || stack.getItem() == Items.gold_ingot || stack.getItem() == Items.iron_ingot;
   }

   @Override
   public double getMaxRenderDistanceSquared() {
      return 65536.0;
   }

   public void setName(String str) {
      this.customName = str;
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      this.primaryEffect = this.isBeaconEffect(tag.getInteger("Primary"));
      this.secondaryEffect = this.isBeaconEffect(tag.getInteger("Secondary"));
      this.levels = tag.getInteger("Levels");
   }

   @Override
   public void setField(int n, int n2) {
      switch (n) {
         case 0:
            this.levels = n2;
            break;
         case 1:
            this.primaryEffect = this.isBeaconEffect(n2);
            break;
         case 2:
            this.secondaryEffect = this.isBeaconEffect(n2);
      }
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      if (n == 0) {
         this.payment = stack;
      }
   }

   @Override
   public String getGuiID() {
      return "minecraft:beacon";
   }

   @Override
   public int getField(int n) {
      switch (n) {
         case 0:
            return this.levels;
         case 1:
            return this.primaryEffect;
         case 2:
            return this.secondaryEffect;
         default:
            return 0;
      }
   }

   private int isBeaconEffect(int n) {
      if (n >= 0 && n < Potion.potionTypes.length && Potion.potionTypes[n] != null) {
         Potion potion = Potion.potionTypes[n];
         return potion != Potion.moveSpeed
               && potion != Potion.digSpeed
               && potion != Potion.resistance
               && potion != Potion.jump
               && potion != Potion.damageBoost
               && potion != Potion.regeneration
            ? 0
            : n;
      } else {
         return 0;
      }
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("Primary", this.primaryEffect);
      tag.setInteger("Secondary", this.secondaryEffect);
      tag.setInteger("Levels", this.levels);
   }

   @Override
   public void update() {
      if (this.worldObj.getTotalWorldTime() % 80L == 0L) {
         this.updateBeacon();
      }
   }

   private void addEffectsToPlayers() {
      if (this.isComplete && this.levels > 0 && !this.worldObj.isRemote && this.primaryEffect > 0) {
         double d = (double)(this.levels * 10 + 10);
         byte b = 0;
         if (this.levels >= 4 && this.primaryEffect == this.secondaryEffect) {
            b = 1;
         }

         int x = this.pos.getX();
         int y = this.pos.getY();
         int z = this.pos.getZ();
         AxisAlignedBB box = new AxisAlignedBB((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1))
            .expand(d, d, d)
            .addCoord(0.0, (double)this.worldObj.getHeight(), 0.0);
         List entitiesWithinAABB = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, box);

         for (EntityPlayer player : (Iterable<EntityPlayer>)(entitiesWithinAABB)) {
            player.addPotionEffect(new PotionEffect(this.primaryEffect, 180, b, true, true));
         }

         if (this.levels >= 4 && this.primaryEffect != this.secondaryEffect && this.secondaryEffect > 0) {
            for (EntityPlayer player2 : (Iterable<EntityPlayer>)(entitiesWithinAABB)) {
               player2.addPotionEffect(new PotionEffect(this.secondaryEffect, 180, 0, true, true));
            }
         }
      }
   }

   private void updateSegmentColors() {
      int levels2 = this.levels;
      int x = this.pos.getX();
      int y = this.pos.getY();
      int z = this.pos.getZ();
      this.levels = 0;
      this.beamSegments.clear();
      this.isComplete = true;
      TileEntityBeacon.BeamSegment beamSegment = new TileEntityBeacon.BeamSegment(EntitySheep.getDyeRgb(EnumDyeColor.WHITE));
      this.beamSegments.add(beamSegment);
      boolean flag = true;
      BlockPos.MutableBlockPos pos2 = new BlockPos.MutableBlockPos();

      for (int i = y + 1; i < 256; i++) {
         IBlockState blockState = this.worldObj.getBlockState(pos2.set(x, i, z));
         float[] floats;
         if (blockState.getBlock() == Blocks.stained_glass) {
            floats = EntitySheep.getDyeRgb((EnumDyeColor)blockState.getValue(BlockStainedGlass.COLOR));
         } else {
            if (blockState.getBlock() != Blocks.stained_glass_pane) {
               if (blockState.getBlock().getLightOpacity() >= 15 && blockState.getBlock() != Blocks.bedrock) {
                  this.isComplete = false;
                  this.beamSegments.clear();
                  break;
               }

               beamSegment.incrementHeight();
               continue;
            }

            floats = EntitySheep.getDyeRgb((EnumDyeColor)blockState.getValue(BlockStainedGlassPane.COLOR));
         }

         if (!flag) {
            floats = new float[]{(beamSegment.getColors()[0] + floats[0]) / 2.0F, (beamSegment.getColors()[1] + floats[1]) / 2.0F, (beamSegment.getColors()[2] + floats[2]) / 2.0F};
         }

         if (Arrays.equals(floats, beamSegment.getColors())) {
            beamSegment.incrementHeight();
         } else {
            beamSegment = new TileEntityBeacon.BeamSegment(floats);
            this.beamSegments.add(beamSegment);
         }

         flag = false;
      }

      if (this.isComplete) {
         for (int j = 1; j <= 4; this.levels = j++) {
            int n = y - j;
            if (n < 0) {
               break;
            }

            boolean flag2 = true;

            for (int k = x - j; k <= x + j && flag2; k++) {
               for (int m = z - j; m <= z + j; m++) {
                  Block block = this.worldObj.getBlockState(new BlockPos(k, n, m)).getBlock();
                  if (block != Blocks.emerald_block && block != Blocks.gold_block && block != Blocks.diamond_block && block != Blocks.iron_block) {
                     flag2 = false;
                     break;
                  }
               }
            }

            if (!flag2) {
               break;
            }
         }

         if (this.levels == 0) {
            this.isComplete = false;
         }
      }

      if (!this.worldObj.isRemote && this.levels == 4 && levels2 < this.levels) {
         for (EntityPlayer player : (Iterable<EntityPlayer>)(this.worldObj
            .getEntitiesWithinAABB(
               EntityPlayer.class,
               new AxisAlignedBB((double)x, (double)y, (double)z, (double)x, (double)(y - 4), (double)z).expand(10.0, 5.0, 10.0)
            ))) {
            player.triggerAchievement(AchievementList.fullBeacon);
         }
      }
   }

   @Override
   public boolean hasCustomName() {
      return this.customName != null && this.customName.length() > 0;
   }

   @Override
   public String getName() {
      return this.hasCustomName() ? this.customName : "container.beacon";
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (n == 0 && this.payment != null) {
         ItemStack payment2 = this.payment;
         this.payment = null;
         return payment2;
      } else {
         return null;
      }
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   public List getBeamSegments() {
      return this.beamSegments;
   }

   @Override
   public int getFieldCount() {
      return 3;
   }

   public float shouldBeamRender() {
      if (!this.isComplete) {
         return 0.0F;
      } else {
         int n = (int)(this.worldObj.getTotalWorldTime() - this.beamRenderCounter);
         this.beamRenderCounter = this.worldObj.getTotalWorldTime();
         if (n > 1) {
            this.beamRenderScale -= (float)n / 40.0F;
            if (this.beamRenderScale < 0.0F) {
               this.beamRenderScale = 0.0F;
            }
         }

         this.beamRenderScale += 0.025F;
         if (this.beamRenderScale > 1.0F) {
            this.beamRenderScale = 1.0F;
         }

         return this.beamRenderScale;
      }
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public boolean receiveClientEvent(int n, int n2) {
      if (n == 1) {
         this.updateBeacon();
         return true;
      } else {
         return super.receiveClientEvent(n, n2);
      }
   }


   public static class BeamSegment {
      private final float[] colors;
      private int height;

      public int getHeight() {
         return this.height;
      }

      public void incrementHeight() {
         this.height++;
      }

      public float[] getColors() {
         return this.colors;
      }

      public BeamSegment(float[] floats) {
         this.colors = floats;
         this.height = 1;
      }
   }
}
