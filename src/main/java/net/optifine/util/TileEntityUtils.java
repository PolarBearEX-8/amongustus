package net.optifine.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldNameable;
import net.optifine.config.Config;

public class TileEntityUtils {
   public static boolean setTileEntityRawName(TileEntity tileEntity, String str) {
      if (tileEntity instanceof TileEntityBeacon) {
         ((TileEntityBeacon)tileEntity).customName = str;
         return true;
      } else if (tileEntity instanceof TileEntityBrewingStand) {
         ((TileEntityBrewingStand)tileEntity).customName = str;
         return true;
      } else if (tileEntity instanceof TileEntityEnchantmentTable) {
         ((TileEntityEnchantmentTable)tileEntity).customName = str;
         return true;
      } else if (tileEntity instanceof TileEntityFurnace) {
         ((TileEntityFurnace)tileEntity).furnaceCustomName = str;
         return true;
      } else if (tileEntity instanceof TileEntityChest) {
         ((TileEntityChest)tileEntity).customName = str;
         return true;
      } else if (tileEntity instanceof TileEntityDispenser) {
         ((TileEntityDispenser)tileEntity).customName = str;
         return true;
      } else if (tileEntity instanceof TileEntityHopper) {
         ((TileEntityHopper)tileEntity).customName = str;
         return true;
      } else {
         return false;
      }
   }

   public static String getTileEntityRawName(TileEntity tileEntity) {
      if (tileEntity instanceof TileEntityBeacon) {
         return ((TileEntityBeacon)tileEntity).customName;
      } else if (tileEntity instanceof TileEntityBrewingStand) {
         return ((TileEntityBrewingStand)tileEntity).customName;
      } else if (tileEntity instanceof TileEntityEnchantmentTable) {
         return ((TileEntityEnchantmentTable)tileEntity).customName;
      } else if (tileEntity instanceof TileEntityFurnace) {
         return ((TileEntityFurnace)tileEntity).furnaceCustomName;
      } else {
         if (tileEntity instanceof IWorldNameable) {
            IWorldNameable worldNameable = (IWorldNameable)tileEntity;
            if (worldNameable.hasCustomName()) {
               return worldNameable.getName();
            }
         }

         return null;
      }
   }

   public static String getServerTileEntityRawName(BlockPos pos) {
      TileEntity tileEntity = IntegratedServerUtils.getTileEntity(pos);
      return tileEntity == null ? null : getTileEntityRawName(tileEntity);
   }

   public static String getTileEntityName(IBlockAccess world, BlockPos pos) {
      return getTileEntityName(world.getTileEntity(pos));
   }

   public static String getTileEntityName(TileEntity tileEntity) {
      if (!(tileEntity instanceof IWorldNameable)) {
         return null;
      } else {
         IWorldNameable worldNameable = (IWorldNameable)tileEntity;
         updateTileEntityName(tileEntity);
         return !worldNameable.hasCustomName() ? null : worldNameable.getName();
      }
   }

   public static void updateTileEntityName(TileEntity tileEntity) {
      BlockPos pos = tileEntity.getPos();
      if (getTileEntityRawName(tileEntity) == null) {
         String str = getServerTileEntityRawName(pos);
         str = Config.normalize(str);
         setTileEntityRawName(tileEntity, str);
      }
   }
}
