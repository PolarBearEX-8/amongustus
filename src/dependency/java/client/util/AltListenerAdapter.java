package client.util;

import client.event.PostMotionProcessor;
import java.util.ArrayList;

public class AltListenerAdapter implements PostMotionProcessor {
   public byte[] byteFlagArray;
   public final ArrayList arrayList;
   public int count;

   public ArrayList getArrayList() {
      return this.arrayList;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      int count2 = this.count;
      byteBufHandler.writeInt(count2);
      int size = this.arrayList.size();
      byteBufHandler.writeInt(size);

      for (SplineEvaluator splineEvaluator : (Iterable<SplineEvaluator>)(Iterable)this.arrayList) {
         String labelText = splineEvaluator.getLabelText();
         byteBufHandler.applyTextByText(labelText);
         String labelText2 = splineEvaluator.getLabelText2();
         byteBufHandler.applyTextByText(labelText2);
         long timestamp = splineEvaluator.getTimestamp();
         byteBufHandler.writeLong(timestamp);
         boolean enabled = splineEvaluator.isEnabled();
         byteBufHandler.writeBoolean(enabled);
      }
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      int count2 = 0;
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.count = byteBufHandler.readInt();
      count2 = byteBufHandler.readInt();

      for (int index = 0; index < count2; index++) {
         this.arrayList.add(new SplineEvaluator(byteBufHandler.getTextAsText(), byteBufHandler.getTextAsText(), byteBufHandler.readLong(), byteBufHandler.readBoolean()));
      }
   }

   public AltListenerAdapter(ArrayList list, int count2, byte[] byteValueArray) {
      this.arrayList = list;
      this.count = count2;
      this.byteFlagArray = byteValueArray;
   }
}
