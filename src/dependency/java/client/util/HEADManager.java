package client.util;

import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

public class HEADManager {
   public String labelText9 = null;
   public int count2 = 0;
   public Proxy proxy = Proxy.NO_PROXY;
   public String labelText3 = null;
   public String labelText7 = null;
   public String labelText2 = null;
   public Map map = new LinkedHashMap();
   public byte[] byteFlagArray = null;
   public int count = 0;
   public static final String labelText4;
   public static final String labelText5;
   public static final String labelText;
   public static final String labelText6;
   public static final String labelText8;

   public void setCount(int count3) {
      this.count = count3;
   }

   public Map getMap() {
      return this.map;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public int getCount2() {
      return this.count2;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public String getLabelText9() {
      return this.labelText9;
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public String getLabelText7() {
      return this.labelText7;
   }

   public HEADManager(String text, int count3, Proxy proxy2, String text2, String text3, String text4, Map map2, byte[] byteValueArray) {
      this.labelText9 = text;
      this.count2 = count3;
      this.proxy = proxy2;
      this.labelText3 = text2;
      this.labelText7 = text3;
      this.labelText2 = text4;
      this.map = map2;
      this.byteFlagArray = byteValueArray;
   }

   static {
      String[] textArray = new String[]{"HEAD", "POST", "GET", "HTTP/1.0", "HTTP/1.1"};
      labelText = textArray[1];
      labelText8 = textArray[4];
      labelText5 = textArray[0];
      labelText4 = textArray[2];
      labelText6 = textArray[3];
   }
}
