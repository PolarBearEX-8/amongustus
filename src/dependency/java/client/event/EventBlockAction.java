package client.event;

import client.util.IBlockState;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.block.ExtendedBlockPosAdapter;
import net.minecraft.util.BlockPos;

public class EventBlockAction extends AbstractEvent {
   public ExtendedBlockPosAdapter extendedBlockPosAdapter;
   public IBlockState iBlockState;
   public BlockPos blockPos;
   public static final int count = 30;

   public BlockPos getBlockPos() {
      return this.blockPos != null
         ? this.blockPos
         : (this.blockPos = new BlockPos(this.extendedBlockPosAdapter.getX(), this.extendedBlockPosAdapter.getY(), this.extendedBlockPosAdapter.getZ()));
   }

   public static BlockPos onBlockAction(EventBlockAction event, BlockPos blockPos2) {
      return event.blockPos = blockPos2;
   }

   public void call(Object entry) {
      BlockActionListener blockActionListener = (BlockActionListener)entry;
      this.applyBlockActionListener(blockActionListener);
   }

   public void applyBlockActionListener(BlockActionListener blockActionListener) {
      blockActionListener.onBlockAction(this);
   }

   public static ExtendedBlockPosAdapter onBlockAction(EventBlockAction event, ExtendedBlockPosAdapter extendedBlockPosAdapter2) {
      return event.extendedBlockPosAdapter = extendedBlockPosAdapter2;
   }

   public BlockPos getBlockPosAsBlockPos() {
      return this.extendedBlockPosAdapter;
   }

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   public static IBlockState onBlockAction(EventBlockAction event, IBlockState blockState) {
      return event.iBlockState = blockState;
   }

   public EventBlockAction(ExtendedBlockPosAdapter extendedBlockPosAdapter2, IBlockState blockState) {
      this.extendedBlockPosAdapter = extendedBlockPosAdapter2;
      this.blockPos = null;
      this.iBlockState = blockState;
   }
}
