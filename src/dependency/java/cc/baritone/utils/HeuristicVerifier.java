package cc.baritone.utils;

import client.util.IBlockState;
import java.util.List;

public class HeuristicVerifier extends AbstractInternalListener {
   public final List list = new java.util.ArrayList();
   public InternalListenerRecord[] internalListenerRecordArray;

   private void updateState2() {
      this.internalListenerRecordArray = ((InternalListenerRecord[])this.list.toArray(new InternalListenerRecord[0]));

      for (InternalListenerRecord internalListenerRecord : this.internalListenerRecordArray) {
         this.count = Math.max(this.count, internalListenerRecord.count + internalListenerRecord.internalListener.getCount());
         this.count2 = Math.max(this.count2, internalListenerRecord.count2 + internalListenerRecord.internalListener.getCount2());
         this.count3 = Math.max(this.count3, internalListenerRecord.count3 + internalListenerRecord.internalListener.getCount3());
      }
   }

   @Override
   public IBlockState resolveIBlockState(int count, int count2, int count3, IBlockState blockState, List list2) {
      InternalListenerRecord internalListenerRecord = this.resolveInternalListenerRecord(count, count2, count3, blockState);
      if (internalListenerRecord == null) {
         throw new IllegalStateException("couldn't find schematic for this position");
      } else {
         return internalListenerRecord.internalListener.resolveIBlockState(count - internalListenerRecord.count, count2 - internalListenerRecord.count2, count3 - internalListenerRecord.count3, blockState, list2);
      }
   }

   public HeuristicVerifier(int count, int count2, int count3) {
      super(count, count2, count3);
      this.updateState2();
   }

   public void applyInternalListener(InternalListener internalListener, int count, int count2, int count3) {
      this.list.add(new InternalListenerRecord(internalListener, count, count2, count3));
      this.updateState2();
   }

   @Override
   public void updateState() {
      InternalListenerRecord[] internalListenerRecordArray2 = this.internalListenerRecordArray;
      int length = internalListenerRecordArray2.length;

      for (int index = 0; index < length; index++) {
         internalListenerRecordArray2[index].internalListener.updateState();
      }
   }

   @Override
   public boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState) {
      InternalListenerRecord internalListenerRecord = this.resolveInternalListenerRecord(count, count2, count3, blockState);
      return internalListenerRecord != null && internalListenerRecord.internalListener.matchesIBlockState(count - internalListenerRecord.count, count2 - internalListenerRecord.count2, count3 - internalListenerRecord.count3, blockState);
   }

   private InternalListenerRecord resolveInternalListenerRecord(int count, int count2, int count3, IBlockState blockState) {
      for (InternalListenerRecord internalListenerRecord : this.internalListenerRecordArray) {
         if (count >= internalListenerRecord.count
            && count2 >= internalListenerRecord.count2
            && count3 >= internalListenerRecord.count3
            && internalListenerRecord.internalListener.matchesIBlockState(count - internalListenerRecord.count, count2 - internalListenerRecord.count2, count3 - internalListenerRecord.count3, blockState)) {
            return internalListenerRecord;
         }
      }

      return null;
   }
}
