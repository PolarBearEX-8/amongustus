package client.util;

import client.event.PostMotionProcessor;
import java.util.HashMap;
import java.util.Map;

public class CustomListenerAdapter implements PostMotionProcessor {
   public Map map3;
   public Map map5;
   public Map map4;
   public Map map8;
   public Map map = new HashMap();
   public Map map7 = new HashMap();
   public byte[] byteFlagArray;
   public Map map6;
   public Map map2;

   public Map getMap7() {
      return this.map7;
   }

   public Map getMap4() {
      return this.map4;
   }

   public Map getMap8() {
      return this.map8;
   }

   @Override
   public void writeInt(ByteBufHandler byteBufHandler) throws Exception {
      byte[] byteFlagArray2 = this.byteFlagArray;
      byteBufHandler.applyByteArray(byteFlagArray2);
      Map map9 = this.map;
      byteBufHandler.applyMap3(map9);
      Map map10 = this.map7;
      byteBufHandler.applyMap2(map10);
      Map map11 = this.map8;
      byteBufHandler.applyMap2(map11);
      Map map12 = this.map5;
      byteBufHandler.applyMap4(map12);
      Map map13 = this.map6;
      byteBufHandler.applyMap4(map13);
      Map map14 = this.map3;
      byteBufHandler.applyMap(map14);
      Map map15 = this.map2;
      byteBufHandler.applyMap5(map15);
      Map map16 = this.map4;
      byteBufHandler.applyMap5(map16);
   }

   public Map getMap2() {
      return this.map2;
   }

   public Map getMap6() {
      return this.map6;
   }

   public Map getMap5() {
      return this.map5;
   }

   @Override
   public void readInt(ByteBufHandler byteBufHandler) throws Exception {
      this.byteFlagArray = byteBufHandler.getByteArray();
      this.map = byteBufHandler.getMap3();
      this.map7 = byteBufHandler.getMap5();
      this.map8 = byteBufHandler.getMap5();
      this.map5 = byteBufHandler.getMapAsMap();
      this.map6 = byteBufHandler.getMapAsMap();
      this.map3 = byteBufHandler.getMap();
      this.map2 = byteBufHandler.getMap2();
      this.map4 = byteBufHandler.getMap2();
   }

   public Map getMap() {
      return this.map;
   }

   public Map getMap3() {
      return this.map3;
   }

   public CustomListenerAdapter() {
      this.map8 = new HashMap();
      this.map5 = new HashMap();
      this.map6 = new HashMap();
      this.map3 = new HashMap();
      this.map2 = new HashMap();
      this.map4 = new HashMap();
   }

   public CustomListenerAdapter(byte[] byteValueArray, Map map9, Map map10, Map map11, Map map12, Map map13, Map map14, Map map15, Map map16) {
      this.map8 = new HashMap();
      this.map5 = new HashMap();
      this.map6 = new HashMap();
      this.map3 = new HashMap();
      this.map2 = new HashMap();
      this.map4 = new HashMap();
      this.byteFlagArray = byteValueArray;
      this.map = map9;
      this.map7 = map10;
      this.map8 = map11;
      this.map5 = map12;
      this.map6 = map13;
      this.map3 = map14;
      this.map2 = map15;
      this.map4 = map16;
   }
}
