package net.minecraft.block.state;

import com.google.common.base.Predicate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockWorldState {
   private final World world;
   private final BlockPos pos;
   private final boolean forceLoad;
   private IBlockState state;
   private TileEntity tileEntity;
   private boolean tileEntityInitialized;

   public TileEntity getTileEntity() {
      if (this.tileEntity == null && !this.tileEntityInitialized) {
         this.tileEntity = this.world.getTileEntity(this.pos);
         this.tileEntityInitialized = true;
      }

      return this.tileEntity;
   }

   public static Predicate hasState(Predicate predicate) {
      return new BlockWorldState.FilterPredicate(predicate);
   }

   public BlockPos getPos() {
      return this.pos;
   }

   public IBlockState getBlockState() {
      if (this.state == null && (this.forceLoad || this.world.isBlockLoaded(this.pos))) {
         this.state = this.world.getBlockState(this.pos);
      }

      return this.state;
   }

   public BlockWorldState(World world2, BlockPos pos2, boolean flag) {
      this.world = world2;
      this.pos = pos2;
      this.forceLoad = flag;
   }


   public static final class FilterPredicate implements Predicate {
      public final Predicate statePredicate;

      public FilterPredicate(Predicate predicate) {
         this.statePredicate = predicate;
      }

      public boolean apply(BlockWorldState blockWorldState) {
         return blockWorldState != null && this.statePredicate.apply(blockWorldState.getBlockState());
      }

      public boolean apply(Object obj) {
         return this.apply((BlockWorldState)obj);
      }
   }
}
