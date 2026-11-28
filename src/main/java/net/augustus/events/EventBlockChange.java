package net.augustus.events;

import net.augustus.events.listeners.BlockChangeListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public class EventBlockChange extends AbstractEvent<BlockChangeListener> {
   private BlockPos blockPos;
   private IBlockState blockState;
   private int y;
   public static final int ID = 55;
   private int z;
   private int x;

   public EventBlockChange(int n, int n2, int n3, IBlockState state, BlockPos pos) {
      this.x = n;
      this.y = n2;
      this.z = n3;
      this.blockState = state;
      this.blockPos = pos;
   }

   public int getY() {
      return this.y;
   }

   public int getX() {
      return this.x;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }


   @Override
   public void call(BlockChangeListener listener) {
      listener.onBlockChange(this);
   }

   public IBlockState getBlockState() {
      return this.blockState;
   }

   public int getZ() {
      return this.z;
   }
}
