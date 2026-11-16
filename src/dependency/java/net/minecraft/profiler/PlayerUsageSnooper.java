package net.minecraft.profiler;

import client.event.PostMotionNotifier;
import client.util.SnooperCountTimerTask;
import com.google.common.collect.Maps;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;
import java.util.Map.Entry;

public class PlayerUsageSnooper {
   public final Map map = Maps.newHashMap();
   public final Map map2 = Maps.newHashMap();
   public final String labelText = UUID.randomUUID().toString();
   public final URL uRL;
   public final PostMotionNotifier postMotionNotifier;
   public final Timer timer = new Timer("Snooper Timer", true);
   public final Object dataRef = new Object();
   public final long timestamp;
   public boolean enabled;
   public int count;

   public static Object resolveTarget(PlayerUsageSnooper playerUsageSnooper) {
      return playerUsageSnooper.dataRef;
   }

   public static int computeCount(PlayerUsageSnooper playerUsageSnooper) {
      return playerUsageSnooper.count;
   }

   public void cancel() {
      this.timer.cancel();
   }

   public static Map resolveMapByPlayerUsageSnoop(PlayerUsageSnooper playerUsageSnooper) {
      return playerUsageSnooper.map;
   }

   private void updateState() {
      this.updateState4();
      this.applyTextByText("snooper_token", this.labelText);
      this.applyText("snooper_token", this.labelText);
      this.applyText("os_name", System.getProperty("os.name"));
      this.applyText("os_version", System.getProperty("os.version"));
      this.applyText("os_architecture", System.getProperty("os.arch"));
      this.applyText("java_version", System.getProperty("java.version"));
      this.applyTextByText("version", "1.8.9");
      this.postMotionNotifier.applyPlayerUsageSnooperByPlayerUsageSnoop(this);
   }

   public static Map resolveMap(PlayerUsageSnooper playerUsageSnooper) {
      return playerUsageSnooper.map2;
   }

   public void updateState3() {
      this.applyText("memory_total", Runtime.getRuntime().totalMemory());
      this.applyText("memory_max", Runtime.getRuntime().maxMemory());
      this.applyText("memory_free", Runtime.getRuntime().freeMemory());
      this.applyText("cpu_cores", Runtime.getRuntime().availableProcessors());
      this.postMotionNotifier.applyPlayerUsageSnooper(this);
   }

   public String getLabelText() {
      return this.labelText;
   }

   private void updateState4() {
      List inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
      int count2 = 0;

      for (String text : (Iterable<String>)(Iterable<?>)inputArguments) {
         if (text.startsWith("-X")) {
            this.applyTextByText("jvm_arg[" + count2++ + "]", text);
         }
      }

      this.applyTextByText("jvm_args", count2);
   }

   public Map getMap() {
      LinkedHashMap map3 = Maps.newLinkedHashMap();
      synchronized (this.dataRef) {
         this.updateState3();

         for (Entry entry : (Iterable<Entry>)(Iterable<?>)this.map.entrySet()) {
            map3.put(entry.getKey(), entry.getValue().toString());
         }

         for (Entry entry2 : (Iterable<Entry>)(Iterable<?>)this.map2.entrySet()) {
            map3.put(entry2.getKey(), entry2.getValue().toString());
         }

         return map3;
      }
   }

   public static int computeCountByPlayerUsageSnoop(PlayerUsageSnooper playerUsageSnooper) {
      return playerUsageSnooper.count++;
   }

   public void applyTextByText(String text, Object entry) {
      synchronized (this.dataRef) {
         this.map2.put(text, entry);
      }
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public static PostMotionNotifier resolvePostMotionNotifier(PlayerUsageSnooper playerUsageSnooper) {
      return playerUsageSnooper.postMotionNotifier;
   }

   public static String resolveText(PlayerUsageSnooper playerUsageSnooper) {
      return playerUsageSnooper.labelText;
   }

   public void updateState2() {
      if (!this.enabled) {
         this.enabled = true;
         this.updateState();
         this.timer.schedule(new SnooperCountTimerTask(this), 0L, 900000L);
      }
   }

   public static URL resolveURL(PlayerUsageSnooper playerUsageSnooper) {
      return playerUsageSnooper.uRL;
   }

   public void applyText(String text, Object entry) {
      synchronized (this.dataRef) {
         this.map.put(text, entry);
      }
   }

   public PlayerUsageSnooper(String text, PostMotionNotifier postMotionNotifier2, long value) {
      try {
         this.uRL = new URL("http://snoop.minecraft.net/" + text + "?version=" + 2);
      } catch (MalformedURLException malformedURLException) {
         throw new IllegalArgumentException();
      }

      this.postMotionNotifier = postMotionNotifier2;
      this.timestamp = value;
   }
}
