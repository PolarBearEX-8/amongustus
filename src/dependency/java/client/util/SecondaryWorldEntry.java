package client.util;

import com.google.common.base.Predicate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SecondaryWorldEntry {
   public final World world;
   public final BlockPos blockPos;
   public final boolean enabled;
   public IBlockState iBlockState;
   public TileEntity tileEntity;
   public boolean enabled2;

   public SecondaryWorldEntry(World world2, BlockPos blockPos2, boolean flag) {
      this.world = world2;
      this.blockPos = blockPos2;
      this.enabled = flag;
   }

   public IBlockState getIBlockState() {
      if (this.iBlockState == null && (this.enabled || this.world.checkBlockPos12(this.blockPos))) {
         this.iBlockState = this.world.getBlockState(this.blockPos);
      }

      return this.iBlockState;
   }

   public static Predicate resolvePredicate(Predicate predicate) {
      return new DynamicPredicateAdapter(predicate);
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public TileEntity getTileEntity() {
      if (this.tileEntity == null && !this.enabled2) {
         this.tileEntity = this.world.resolveTileEntity(this.blockPos);
         this.enabled2 = true;
      }

      return this.tileEntity;
   }
}
