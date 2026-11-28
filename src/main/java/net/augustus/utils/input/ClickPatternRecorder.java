package net.augustus.utils.input;

import net.augustus.events.EventManager;
import net.augustus.events.listeners.TickListener;
import net.augustus.modules.modes.clicking.ClickPattern;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import net.augustus.utils.interfaces.MC;
import org.lwjgl.input.Mouse;

public class ClickPatternRecorder implements TickListener, MC {
   private boolean cancelled;
   private ArrayList recordedRotations;
   private boolean sneaking;
   private float anchorPitch;
   private boolean recording;
   private String patternName;
   private Thread recorderThread = null;
   private float anchorYaw;
   private boolean running;
   private static final String PATTERN_DIR = ClientPaths.CLIENT_DIR + File.separator + "clickpattern";

   public boolean stopAndSave(String str) {
      this.recording = false;
      EventManager.getEventBus().unsubscribe(40, this);
      this.setPatternName(str);
      this.setRunning(false);
      return true;
   }

   public synchronized ArrayList getRecordedRotations() {
      return this.recordedRotations;
   }

   public ClickPatternRecorder() {
      this.recording = false;
      this.running = false;
      this.sneaking = false;
      this.cancelled = false;
      this.patternName = "";
      this.anchorYaw = 0.0F;
      this.anchorPitch = 0.0F;
      this.recordedRotations = new ArrayList();
   }

   public static String[] getPatternNames(ArrayList list) {
      String[] strings = new String[list.size()];

      for (int i = 0; i < list.size(); i++) {
         strings[i] = ((ClickPattern)list.get(i)).name;
      }

      return strings;
   }

   public synchronized void setRunning(boolean flag) {
      this.running = flag;
   }

   @Override
   public void onEarlyTick() {
      if (this.recording) {
         if (!this.isSneaking()) {
            this.anchorYaw = mc.thePlayer.rotationYaw;
            this.anchorPitch = mc.thePlayer.rotationPitch;
         }

         this.setSneaking(mc.thePlayer.isSneaking());
         if (this.isSneaking()) {
            this.getRecordedRotations().add(new float[]{mc.thePlayer.rotationYaw - this.anchorYaw, mc.thePlayer.rotationPitch - this.anchorPitch});
         }
      }
   }

   private void sleepQuarterMillis() {
      try {
         Thread.sleep(0L, 250000);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
   }

   public void cancel() {
      this.recording = false;
      EventManager.getEventBus().unsubscribe(40, this);
      this.setCancelled(true);
      this.setRunning(false);
   }

   public synchronized boolean cancelled() {
      return this.cancelled;
   }

   public synchronized String getPatternName() {
      return this.patternName;
   }

   public synchronized void setPatternName(String str) {
      this.patternName = str;
   }

   public synchronized void setCancelled(boolean flag) {
      this.cancelled = flag;
   }

   public synchronized void setSneaking(boolean flag) {
      this.sneaking = flag;
   }

   public synchronized boolean isSneaking() {
      return this.sneaking;
   }

   public boolean startRecording() {
      if (!this.recording && !this.isRunning()) {
         this.recording = true;
         EventManager.getEventBus().subscribe(40, this);
         this.setCancelled(false);
         this.setRunning(true);
         this.setSneaking(false);
         this.recorderThread = new Thread(() -> this.recordLoop());
         this.recorderThread.start();
         return true;
      } else {
         return false;
      }
   }

   public synchronized boolean isRunning() {
      return this.running;
   }

   private void recordLoop() {
      ArrayList list = new ArrayList();
      int keyCode = mc.gameSettings.keyBindAttack.getKeyCode();
      long currentTimeMs = System.currentTimeMillis();
      boolean flag = false;

      while (true) {
         if (!this.isRunning()) {
            if (this.cancelled()) {
               return;
            }

            if (list.isEmpty()) {
               return;
            }

            if (list.size() % 2 != 0) {
               list.remove(list.size() - 1);
            }

            JsonObject json = new JsonObject();
            JsonArray array = new JsonArray();
            JsonArray array2 = new JsonArray();

            for (int n : (Iterable<Integer>)(list)) {
               array.add(n);
            }

            for (int i = 0; i < this.getRecordedRotations().size(); i++) {
               float[] floats = (float[])this.getRecordedRotations().get(i);
               JsonArray array3 = new JsonArray();

               for (float f : floats) {
                  array3.add(f);
               }

               array2.add(array3);
            }

            json.add("Delays", array);
            json.add("Rotations", array2);
            ClientFileUtil.writeJsonObject(PATTERN_DIR, this.getPatternName() + ".json", json);
            return;
         }

         this.sleepQuarterMillis();
         if (!this.isSneaking()) {
            while (Mouse.next()) {
            }

            flag = false;
            if (list.size() % 2 != 0) {
               list.remove(list.size() - 1);
            }
         } else {
            while (Mouse.next()) {
               if ((Mouse.getEventButton() - 100) == keyCode) {
                  boolean eventButtonState = Mouse.getEventButtonState();
                  if (flag || eventButtonState) {
                     long l2 = System.currentTimeMillis();
                     if (!flag) {
                        currentTimeMs = System.currentTimeMillis();
                     } else {
                        list.add((int)(l2 - currentTimeMs));
                        currentTimeMs = l2;
                     }

                     flag = true;
                  }
               }
            }
         }
      }
   }

}
