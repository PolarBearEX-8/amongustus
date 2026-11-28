package net.minecraft.entity.item;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityPainting extends EntityHanging {
   public EntityPainting.EnumArt art;

   public EntityPainting(World world, BlockPos pos, EnumFacing facing) {
      super(world, pos);
      ArrayList list = Lists.newArrayList();

      for (EntityPainting.EnumArt enumArt : EntityPainting.EnumArt.values()) {
         this.art = enumArt;
         this.updateFacingWithBoundingBox(facing);
         if (this.onValidSurface()) {
            list.add(enumArt);
         }
      }

      if (!list.isEmpty()) {
         this.art = (EntityPainting.EnumArt)list.get(this.rand.nextInt(list.size()));
      }

      this.updateFacingWithBoundingBox(facing);
   }

   @Override
   public void setLocationAndAngles(double d, double d2, double d3, float f, float f2) {
      BlockPos pos = this.hangingPosition.add(d - this.posX, d2 - this.posY, d3 - this.posZ);
      this.setPosition((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
   }

   @Override
   public void setPositionAndRotation2(double d, double d2, double d3, float f, float f2, int n, boolean flag) {
      BlockPos pos = this.hangingPosition.add(d - this.posX, d2 - this.posY, d3 - this.posZ);
      this.setPosition((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
   }

   @Override
   public int getHeightPixels() {
      return this.art.sizeY;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setString("Motive", this.art.title);
      super.writeEntityToNBT(tag);
   }

   public EntityPainting(World world) {
      super(world);
   }

   @Override
   public int getWidthPixels() {
      return this.art.sizeX;
   }

   public EntityPainting(World world, BlockPos pos, EnumFacing facing, String str) {
      this(world, pos, facing);

      for (EntityPainting.EnumArt enumArt : EntityPainting.EnumArt.values()) {
         if (enumArt.title.equals(str)) {
            this.art = enumArt;
            break;
         }
      }

      this.updateFacingWithBoundingBox(facing);
   }

   @Override
   public void onBroken(Entity entity) {
      if (this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).capabilities.isCreativeMode) {
               return;
            }
         }

         this.entityDropItem(new ItemStack(Items.painting), 0.0F);
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      String string = tag.getString("Motive");

      for (EntityPainting.EnumArt enumArt : EntityPainting.EnumArt.values()) {
         if (enumArt.title.equals(string)) {
            this.art = enumArt;
         }
      }

      if (this.art == null) {
         this.art = EntityPainting.EnumArt.KEBAB;
      }

      super.readEntityFromNBT(tag);
   }


   public enum EnumArt {
      KEBAB("Kebab", 16, 16, 0, 0),
      AZTEC("Aztec", 16, 16, 16, 0),
      ALBAN("Alban", 16, 16, 32, 0),
      AZTEC_2("Aztec2", 16, 16, 48, 0),
      BOMB("Bomb", 16, 16, 64, 0),
      PLANT("Plant", 16, 16, 80, 0),
      WASTELAND("Wasteland", 16, 16, 96, 0),
      POOL("Pool", 32, 16, 0, 32),
      COURBET("Courbet", 32, 16, 32, 32),
      SEA("Sea", 32, 16, 64, 32),
      SUNSET("Sunset", 32, 16, 96, 32),
      CREEBET("Creebet", 32, 16, 128, 32),
      WANDERER("Wanderer", 16, 32, 0, 64),
      GRAHAM("Graham", 16, 32, 16, 64),
      MATCH("Match", 32, 32, 0, 128),
      BUST("Bust", 32, 32, 32, 128),
      STAGE("Stage", 32, 32, 64, 128),
      VOID("Void", 32, 32, 96, 128),
      SKULL_AND_ROSES("SkullAndRoses", 32, 32, 128, 128),
      WITHER("Wither", 32, 32, 160, 128),
      FIGHTERS("Fighters", 64, 32, 0, 96),
      POINTER("Pointer", 64, 64, 0, 192),
      PIGSCENE("Pigscene", 64, 64, 64, 192),
      BURNING_SKULL("BurningSkull", 64, 64, 128, 192),
      SKELETON("Skeleton", 64, 48, 192, 64),
      DONKEY_KONG("DonkeyKong", 64, 48, 192, 112);

   public static final int MAX_NAME_LENGTH = "SkullAndRoses".length();
      public final String title;
      public final int sizeX;
      public final int sizeY;
      public final int offsetX;
      public final int offsetY;
   private EnumArt(String str, int n, int n2, int n3, int n4) {
         this.title = str;
         this.sizeX = n;
         this.sizeY = n2;
         this.offsetX = n3;
         this.offsetY = n4;
      }
   }
}
