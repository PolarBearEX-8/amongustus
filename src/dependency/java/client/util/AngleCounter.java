package client.util;

import client.event.ComplexListener;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityCauldron;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnchanttable;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityTrap;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class AngleCounter {
   public static void applyTileEntity(TileEntity tileEntity) {
      BlockPos blockPos = tileEntity.getBlockPos();
      if (resolveText(tileEntity) == null) {
         String text = BuildManager.resolveTextByText(resolveText(blockPos));
         matchesTileEntity(tileEntity, text);
      }
   }

   public static String resolveText(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return resolveTextByTileEntity(iBlockAccess.resolveTileEntity(blockPos));
   }

   public static String resolveTextByTileEntity(TileEntity tileEntity) {
      if (!(tileEntity instanceof ComplexListener)) {
         return null;
      } else {
         ComplexListener complexListener = (ComplexListener)tileEntity;
         applyTileEntity(tileEntity);
         return !complexListener.processAction() ? null : complexListener.getText();
      }
   }

   public static boolean matchesTileEntity(TileEntity tileEntity, String text) {
      if (tileEntity instanceof TileEntityBeacon) {
         ((TileEntityBeacon)tileEntity).labelText = text;
         return true;
      } else if (tileEntity instanceof TileEntityCauldron) {
         ((TileEntityCauldron)tileEntity).labelText = text;
         return true;
      } else if (tileEntity instanceof TileEntityEnchanttable) {
         ((TileEntityEnchanttable)tileEntity).labelText = text;
         return true;
      } else if (tileEntity instanceof TileEntityFurnace) {
         ((TileEntityFurnace)tileEntity).labelText = text;
         return true;
      } else if (tileEntity instanceof TileEntityChest) {
         ((TileEntityChest)tileEntity).labelText = text;
         return true;
      } else if (tileEntity instanceof TileEntityTrap) {
         ((TileEntityTrap)tileEntity).labelText = text;
         return true;
      } else if (tileEntity instanceof TileEntityHopper) {
         ((TileEntityHopper)tileEntity).labelText = text;
         return true;
      } else {
         return false;
      }
   }

   public static String resolveText(TileEntity tileEntity) {
      if (tileEntity instanceof TileEntityBeacon) {
         return ((TileEntityBeacon)tileEntity).labelText;
      } else if (tileEntity instanceof TileEntityCauldron) {
         return ((TileEntityCauldron)tileEntity).labelText;
      } else if (tileEntity instanceof TileEntityEnchanttable) {
         return ((TileEntityEnchanttable)tileEntity).labelText;
      } else if (tileEntity instanceof TileEntityFurnace) {
         return ((TileEntityFurnace)tileEntity).labelText;
      } else {
         if (tileEntity instanceof ComplexListener) {
            ComplexListener complexListener = (ComplexListener)tileEntity;
            if (complexListener.processAction()) {
               return complexListener.getText();
            }
         }

         return null;
      }
   }

   public static String resolveText(BlockPos blockPos) {
      TileEntity tileEntity = FovProvider.resolveTileEntity(blockPos);
      return tileEntity == null ? null : resolveText(tileEntity);
   }
}
