package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntityMobspawner;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class StringEntryRegistry extends PassiveStringEntry {
   public final TileEntityMobspawner tileEntityMobspawner;

   public StringEntryRegistry(TileEntityMobspawner tileEntityMobspawner2) {
      this.tileEntityMobspawner = tileEntityMobspawner2;
   }

   @Override
   public void setPropertiesTypeDampenerSorter(PropertiesTypeDampenerSorter propertiesTypeDampenerSorter) {
      super.setPropertiesTypeDampenerSorter(propertiesTypeDampenerSorter);
      if (this.getWorld() != null) {
         this.getWorld().applyBlockPos2(this.tileEntityMobspawner.blockPos);
      }
   }

   @Override
   public World getWorld() {
      return this.tileEntityMobspawner.world;
   }

   @Override
   public void applyIndex(int count) {
      this.tileEntityMobspawner.world.applyBlockPosByBlockPos(this.tileEntityMobspawner.blockPos, Blocks.block29, count, 0);
   }

   @Override
   public BlockPos getBlockPos() {
      return this.tileEntityMobspawner.blockPos;
   }
}
