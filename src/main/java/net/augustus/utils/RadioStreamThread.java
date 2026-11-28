package net.augustus.utils;

import net.augustus.ClientManagers;
import javazoom.jl.player.Player;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;
import net.augustus.modules.misc.ClientSettings;

public class RadioStreamThread extends Thread {
   private Player player = null;
   private String streamUrl;
   private final AtomicBoolean playing = new AtomicBoolean(false);
   private final AtomicBoolean restartRequested = new AtomicBoolean(false);

   private RadioStreamThread() {
   }

   public void playStream(String str) {
      this.playing.set(false);
      this.streamUrl = str;
      this.restartRequested.set(true);
   }

   public void stopStream() {
      this.playing.set(false);
   }

   @Override
   public void run() {

      while (true) {
         if (this.playing.get()) {
            try {
               this.player = new Player(new URL(this.streamUrl).openStream());
               this.player.setGain((float)(ClientManagers.moduleManager.radio.volume.getValue(true) * 0.8600000143051147 - 80.0));
               this.player.play(this.playing);
            } catch (Throwable e) {
            }
         } else if (this.restartRequested.get()) {
            this.restartRequested.set(false);
            this.playing.set(true);
         }

         this.sleepTick();
      }
   }

   private void sleepTick() {
      try {
         Thread.sleep(10L);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
   }

   private synchronized Player getPlayer() {
      return this.player;
   }

   public RadioStreamThread(RadioStreamThreadSynthetic synthetic) {
      this();
   }

   public static Player getPlayer(RadioStreamThread radioStreamThread) {
      return radioStreamThread.getPlayer();
   }
}
