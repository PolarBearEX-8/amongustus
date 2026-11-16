package client.util;

import client.event.PostMotionProcessor;
import java.util.HashMap;
import java.util.Map;

public class CosmeticsDataManager implements PostMotionProcessor {
   public Map map4;
   public Map map3;
   public int count;
   public Map map7;
   public String labelText;
   public int count3 = 0;
   public String labelText2;
   public Map map2;
   public Map map6;
   public Map map8;
   public int count2;
   public Map map5 = new HashMap();
   public Map map;

   public Map getEntryMap7() {
      return this.map5;
   }

   public String getText() {
      return this.labelText;
   }

   public Map getEntryMap4() {
      return this.map2;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      int count6 = this.count2;
      byteBufHandler.writeInt(count6);
      Map map9 = this.map5;
      byteBufHandler.applyMap3(map9);
      Map map10 = this.map6;
      byteBufHandler.applyMap2(map10);
      Map map11 = this.map;
      byteBufHandler.applyMap2(map11);
      Map map12 = this.map4;
      byteBufHandler.applyMap4(map12);
      Map map13 = this.map2;
      byteBufHandler.applyMap4(map13);
      Map map14 = this.map3;
      byteBufHandler.applyMap(map14);
      Map map15 = this.map7;
      byteBufHandler.applyMap5(map15);
      Map map16 = this.map8;
      byteBufHandler.applyMap5(map16);
      String labelText3 = this.labelText;
      byteBufHandler.applyTextByText(labelText3);
      int count7 = this.count3;
      byteBufHandler.writeInt(count7);
      int count8 = this.count;
      byteBufHandler.writeInt(count8);
      String labelText4 = this.labelText2;
      byteBufHandler.applyTextByText(labelText4);
   }

   public Map getEntryMap() {
      return this.map4;
   }

   public Map getEntryMap6() {
      return this.map8;
   }

   public Map getEntryMap5() {
      return this.map6;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.count2 = byteBufHandler.readInt();
      this.map5 = byteBufHandler.getMap3();
      this.map6 = byteBufHandler.getMap5();
      this.map = byteBufHandler.getMap5();
      this.map4 = byteBufHandler.getMapAsMap();
      this.map2 = byteBufHandler.getMapAsMap();
      this.map3 = byteBufHandler.getMap();
      this.map7 = byteBufHandler.getMap2();
      this.map8 = byteBufHandler.getMap2();
      this.labelText = byteBufHandler.getTextAsText();
      this.count3 = byteBufHandler.readInt();
      this.count = byteBufHandler.readInt();
      this.labelText2 = byteBufHandler.getTextAsText();
   }

   public Map getEntryMap3() {
      return this.map7;
   }

   public int getIndex() {
      return this.count;
   }

   public Map getEntryMap8() {
      return this.map;
   }

   public String getText2() {
      return this.labelText2;
   }

   public int getIndex2() {
      return this.count3;
   }

   public Map getEntryMap2() {
      return this.map3;
   }

   public CosmeticsDataManager() {
      this.map6 = new HashMap();
      this.map = new HashMap();
      this.map4 = new HashMap();
      this.map2 = new HashMap();
      this.map3 = new HashMap();
      this.map7 = new HashMap();
      this.map8 = new HashMap();
   }
}
