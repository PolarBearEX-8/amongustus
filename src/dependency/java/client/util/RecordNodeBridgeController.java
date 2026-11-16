package client.util;

import java.util.ArrayList;
import java.util.List;

public class RecordNodeBridgeController {
   public float[] factorArray;
   public final List list = new ArrayList();
   public final List list2 = new ArrayList();

   public RecordNodeBridgeController() {
      this.factorArray = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
   }

   public RecordNodeBridge getRecordNodeBridge() {
      float[] factorArray2 = new float[this.list.size()];
      float[] factorArray3 = new float[this.list2.size()];

      for (int index = 0; index < this.list.size(); index++) {
         factorArray2[index] = (Float)this.list.get(index);
      }

      for (int index2 = 0; index2 < this.list2.size(); index2++) {
         factorArray3[index2] = (Float)this.list2.get(index2);
      }

      return new RecordNodeBridge(factorArray2, factorArray3);
   }

   public RecordNodeBridgeController resolveRecordNodeBridgeController(float[] factorArray2) {
      this.factorArray = (float[])factorArray2.clone();
      return this;
   }

   public RecordNodeBridgeController resolveRecordNodeBridgeController(float posX, float posX2, float posY, float posZ) {
      this.factorArray = new float[]{posX, posX2, posY, posZ};
      return this;
   }

   public RecordNodeBridgeController getRecordNodeBridgeController() {
      this.list2.add(this.factorArray[0]);
      this.list2.add(this.factorArray[1]);
      this.list2.add(this.factorArray[2]);
      this.list2.add(this.factorArray[3]);
      return this;
   }

   public RecordNodeBridgeController resolveRecordNodeBridgeController(float posX, float posY, float posZ) {
      this.list.add(posX);
      this.list.add(posY);
      this.list.add(posZ);
      return this;
   }
}
