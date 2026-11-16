package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UNKNOWNManager {
   public static final Logger logger = LogManager.getLogger();
   public static final String labelText4 = "scheduledExecutables";
   public static final String labelText6 = "tick";
   public static final String labelText2 = "preRenderErrors";
   public static final String labelText = "render";
   public static final String labelText3 = "display";
   public static final int count5 = "scheduledExecutables".hashCode();
   public static final int count2 = "tick".hashCode();
   public static final int count3 = "preRenderErrors".hashCode();
   public static final int count4 = "render".hashCode();
   public static final int count = "display".hashCode();
   public final List list = Lists.newArrayList();
   public final List list2 = Lists.newArrayList();
   public final Map map = Maps.newHashMap();
   public boolean enabled;
   public boolean enabled2 = true;
   public String labelText5 = "";
   public boolean enabled3 = this.enabled2;

   public String getText() {
      return this.list.size() == 0 ? "[UNKNOWN]" : (String)this.list.get(this.list.size() - 1);
   }

   public void applyClass(Class clazz) {
      if (this.enabled) {
         this.applyTextByText(clazz.getSimpleName());
      }
   }

   public void updateState() {
   }

   public void applyText(String text) {
      if (this.enabled3) {
         this.updateState();
         this.applyTextByText(text);
      }
   }

   public List resolveList(String text) {
      String text2 = text;
      if (!this.enabled) {
         return null;
      } else {
         long value = this.map.containsKey("root") ? (Long)this.map.get("root") : 0L;
         long value2 = this.map.containsKey(text) ? (Long)this.map.get(text) : -1L;
         ArrayList list3 = Lists.newArrayList();
         if (text.length() > 0) {
            text2 = text + ".";
         }

         long value3 = 0L;

         for (String text3 : (Iterable<String>)(Iterable<?>)this.map.keySet()) {
            if (text3.length() > text2.length() && text3.startsWith(text2) && text3.indexOf(".", text2.length() + 1) < 0) {
               value3 += (Long)this.map.get(text3);
            }
         }

         float factor = (float)value3;
         if (value3 < value2) {
            value3 = value2;
         }

         if (value < value3) {
            value = value3;
         }

         for (String text4 : (Iterable<String>)(Iterable<?>)this.map.keySet()) {
            if (text4.length() > text2.length() && text4.startsWith(text2) && text4.indexOf(".", text2.length() + 1) < 0) {
               long value4 = (Long)this.map.get(text4);
               double offset = (double)value4 * 100.0 / (double)value3;
               double offset2 = (double)value4 * 100.0 / (double)value;
               String text5 = text4.substring(text2.length());
               list3.add(new SecondaryComparableAdapter(text5, offset, offset2));
            }
         }

         for (String text6 : (Iterable<String>)(Iterable<?>)this.map.keySet()) {
            this.map.put(text6, (Long)this.map.get(text6) * 950L / 1000L);
         }

         if ((float)value3 > factor) {
            list3.add(
               new SecondaryComparableAdapter(
                  "unspecified", (double)((float)value3 - factor) * 100.0 / (double)value3, (double)((float)value3 - factor) * 100.0 / (double)value
               )
            );
         }

         Collections.sort(list3);
         list3.add(0, new SecondaryComparableAdapter(text2, 100.0, (double)value3 * 100.0 / (double)value));
         return list3;
      }
   }

   public void applyTextByText(String text) {
      if (MBSManager.isEnabled()) {
         int hash = text.hashCode();
         if (hash == count5 && text.equals("scheduledExecutables")) {
            MBSManager.nativeLongEntry.nanoTime();
         } else if (hash == count2 && text.equals("tick") && BuildManager.currentThread()) {
            MBSManager.nativeLongEntry.updateState();
            MBSManager.nativeLongEntry3.nanoTime();
         } else if (hash == count3 && text.equals("preRenderErrors")) {
            MBSManager.nativeLongEntry3.updateState();
         }
      }

      if (BuildManager.canExecute3()) {
         int hash2 = text.hashCode();
         if (hash2 == count4 && text.equals("render")) {
            GlStateManager.stateFlag2 = false;
         } else if (hash2 == count && text.equals("display")) {
            GlStateManager.stateFlag2 = true;
         }
      }
   }

   public void updateState2() {
      this.map.clear();
      this.labelText5 = "";
      this.list.clear();
      this.enabled3 = this.enabled2;
   }
}
