package client.event;

import client.util.IBlockState;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventBlockPlace extends AbstractEvent {
   public int count4;
   public static final int count3 = 55;
   public int count;
   public int count2;
   public IBlockState iBlockState;

   public int getIndex() {
      return this.count4;
   }

   public void call(Object entry) {
      BlockPlaceListener blockPlaceListener = (BlockPlaceListener)entry;
      this.applyBlockPlaceListener(blockPlaceListener);
   }

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   public int getIndex2() {
      return this.count2;
   }

   public void applyBlockPlaceListener(BlockPlaceListener blockPlaceListener) {
      blockPlaceListener.onBlockPlace(this);
   }

   public int getIndex3() {
      return this.count;
   }

   public EventBlockPlace(int count5, int count6, int count7, IBlockState blockState) {
      this.count2 = count5;
      this.count = count6;
      this.count4 = count7;
      this.iBlockState = blockState;
   }
}
