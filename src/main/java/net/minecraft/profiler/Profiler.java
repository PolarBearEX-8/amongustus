package net.minecraft.profiler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.optifine.Lagometer;
import net.optifine.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Profiler {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final String SCHEDULED_EXECUTABLES = "scheduledExecutables";
   private static final String TICK = "tick";
   private static final String PRE_RENDER_ERRORS = "preRenderErrors";
   private static final String RENDER = "render";
   private static final String DISPLAY = "display";
   private static final int HASH_SCHEDULED_EXECUTABLES = "scheduledExecutables".hashCode();
   private static final int HASH_TICK = "tick".hashCode();
   private static final int HASH_PRE_RENDER_ERRORS = "preRenderErrors".hashCode();
   private static final int HASH_RENDER = "render".hashCode();
   private static final int HASH_DISPLAY = "display".hashCode();
   private final List sectionList = Lists.newArrayList();
   private final List timestampList = Lists.newArrayList();
   private final Map profilingMap = Maps.newHashMap();
   public boolean profilingEnabled;
   public boolean profilerGlobalEnabled = true;
   private String profilingSection = "";
   private boolean profilerLocalEnabled = this.profilerGlobalEnabled;

   public void endSection() {
   }

   public void endStartSection(String str) {
      if (this.profilerLocalEnabled) {
         this.endSection();
         this.startSection(str);
      }
   }

   public void startSection(String str) {
      if (Lagometer.isActive()) {
         int n = str.hashCode();
         if (n == HASH_SCHEDULED_EXECUTABLES && str.equals("scheduledExecutables")) {
            Lagometer.timerScheduledExecutables.start();
         } else if (n == HASH_TICK && str.equals("tick") && Config.isMinecraftThread()) {
            Lagometer.timerScheduledExecutables.end();
            Lagometer.timerTick.start();
         } else if (n == HASH_PRE_RENDER_ERRORS && str.equals("preRenderErrors")) {
            Lagometer.timerTick.end();
         }
      }

      if (Config.isFastRender()) {
         int n2 = str.hashCode();
         if (n2 == HASH_RENDER && str.equals("render")) {
            GlStateManager.clearEnabled = false;
         } else if (n2 == HASH_DISPLAY && str.equals("display")) {
            GlStateManager.clearEnabled = true;
         }
      }
   }

   public String getNameOfLastSection() {
      return this.sectionList.size() == 0 ? "[UNKNOWN]" : (String)this.sectionList.get(this.sectionList.size() - 1);
   }

   public List getProfilingData(String str) {
      if (!this.profilingEnabled) {
         return null;
      } else {
         long l = this.profilingMap.containsKey("root") ? (Long)this.profilingMap.get("root") : 0L;
         long l2 = this.profilingMap.containsKey(str) ? (Long)this.profilingMap.get(str) : -1L;
         ArrayList list = Lists.newArrayList();
         if (str.length() > 0) {
            str = str + ".";
         }

         long l3 = 0L;

         for (String str2 : (Iterable<String>)(this.profilingMap.keySet())) {
            if (str2.length() > str.length() && str2.startsWith(str) && str2.indexOf(".", str.length() + 1) < 0) {
               l3 += (Long)this.profilingMap.get(str2);
            }
         }

         float f = (float)l3;
         if (l3 < l2) {
            l3 = l2;
         }

         if (l < l3) {
            l = l3;
         }

         for (String str3 : (Iterable<String>)(this.profilingMap.keySet())) {
            if (str3.length() > str.length() && str3.startsWith(str) && str3.indexOf(".", str.length() + 1) < 0) {
               long l4 = (Long)this.profilingMap.get(str3);
               double d = (double)l4 * 100.0 / (double)l3;
               double d2 = (double)l4 * 100.0 / (double)l;
               String str4 = str3.substring(str.length());
               list.add(new Profiler.Result(str4, d, d2));
            }
         }

         for (String str5 : (Iterable<String>)(this.profilingMap.keySet())) {
            this.profilingMap.put(str5, (Long)this.profilingMap.get(str5) * 950L / 1000L);
         }

         if ((float)l3 > f) {
            list.add(
               new Profiler.Result("unspecified", (double)((float)l3 - f) * 100.0 / (double)l3, (double)((float)l3 - f) * 100.0 / (double)l)
            );
         }

         Collections.sort(list);
         list.add(0, new Profiler.Result(str, 100.0, (double)l3 * 100.0 / (double)l));
         return list;
      }
   }

   public void clearProfiling() {
      this.profilingMap.clear();
      this.profilingSection = "";
      this.sectionList.clear();
      this.profilerLocalEnabled = this.profilerGlobalEnabled;
   }

   public void startSection2(Class clazz) {
      if (this.profilingEnabled) {
         this.startSection(clazz.getSimpleName());
      }
   }


   public static final class Result implements Comparable {
      public double usePercentage;
      public double totalUsePercentage;
      public String profilerName;

      public int getColor() {
         return (this.profilerName.hashCode() & 11184810) + 4473924;
      }

      public Result(String str, double d, double d2) {
         this.profilerName = str;
         this.usePercentage = d;
         this.totalUsePercentage = d2;
      }

      @Override
      public int compareTo(Object other) {
         return this.compareTo((Result)other);
      }

      public int compareTo(Result other) {
         return other.usePercentage < this.usePercentage ? -1 : (other.usePercentage > this.usePercentage ? 1 : other.profilerName.compareTo(this.profilerName));
      }
   }
}
