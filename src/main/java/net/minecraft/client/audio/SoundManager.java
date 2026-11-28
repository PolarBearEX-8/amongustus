package net.minecraft.client.audio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import net.minecraft.client.Minecraft;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemLogger;
import paulscode.sound.Source;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import io.netty.util.internal.ThreadLocalRandom;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class SoundManager {
   private static final Marker LOG_MARKER = MarkerManager.getMarker("SOUNDS");
   private static final Logger logger = LogManager.getLogger();
   private final SoundHandler sndHandler;
   private final GameSettings options;
   private SoundManager.SoundSystemStarterThread sndSystem;
   private boolean loaded;
   private int playTime = 0;
   private final Map playingSounds = HashBiMap.create();
   private final Map invPlayingSounds;
   private Map playingSoundPoolEntries;
   private final Multimap categorySounds;
   private final List tickableSounds;
   private final Map delayedSounds;
   private final Map playingSoundsStopTime;
   private final List pausedChannels = new ArrayList();

   public void stopAllSounds() {
      if (this.loaded) {
         for (String str : (Iterable<String>)(this.playingSounds.keySet())) {
            this.sndSystem.stop(str);
         }

         this.playingSounds.clear();
         this.delayedSounds.clear();
         this.tickableSounds.clear();
         this.categorySounds.clear();
         this.playingSoundPoolEntries.clear();
         this.playingSoundsStopTime.clear();
      }
   }

   public void updateAllSounds() {
      this.playTime++;

      for (ITickableSound tickableSound : (Iterable<ITickableSound>)(this.tickableSounds)) {
         tickableSound.update();
         if (tickableSound.isDonePlaying()) {
            this.stopSound(tickableSound);
         } else {
            String str = (String)this.invPlayingSounds.get(tickableSound);
            this.sndSystem
               .setVolume(
                  str,
                  this.getNormalizedVolume(
                     tickableSound, (SoundPoolEntry)this.playingSoundPoolEntries.get(tickableSound), this.sndHandler.getSound(tickableSound.getSoundLocation()).getSoundCategory()
                  )
               );
            this.sndSystem.setPitch(str, this.getNormalizedPitch(tickableSound, (SoundPoolEntry)this.playingSoundPoolEntries.get(tickableSound)));
            this.sndSystem.setPosition(str, tickableSound.getXPosF(), tickableSound.getYPosF(), tickableSound.getZPosF());
         }
      }

      Iterator it = this.playingSounds.entrySet().iterator();

      while (it.hasNext()) {
         Entry entry = (Entry)it.next();
         String key = (String)entry.getKey();
         ISound value = (ISound)entry.getValue();
         if (!this.sndSystem.playing(key)) {
            int n = (Integer)this.playingSoundsStopTime.get(key);
            if (n <= this.playTime) {
               int repeatDelay = value.getRepeatDelay();
               if (value.canRepeat() && repeatDelay > 0) {
                  this.delayedSounds.put(value, this.playTime + repeatDelay);
               }

               it.remove();
               logger.debug(LOG_MARKER, "Removed channel {} because it's not playing anymore", new Object[]{key});
               this.sndSystem.removeSource(key);
               this.playingSoundsStopTime.remove(key);
               this.playingSoundPoolEntries.remove(value);

               try {
                  this.categorySounds.remove(this.sndHandler.getSound(value.getSoundLocation()).getSoundCategory(), key);
               } catch (RuntimeException e) {
               }

               if (value instanceof ITickableSound) {
                  this.tickableSounds.remove(value);
               }
            }
         }
      }

      Iterator it2 = this.delayedSounds.entrySet().iterator();

      while (it2.hasNext()) {
         Entry entry2 = (Entry)it2.next();
         if (this.playTime >= (Integer)entry2.getValue()) {
            ISound key2 = (ISound)entry2.getKey();
            if (key2 instanceof ITickableSound) {
               ((ITickableSound)key2).update();
            }

            this.playSound(key2);
            it2.remove();
         }
      }
   }

   private synchronized void loadSoundSystem() {
      if (!this.loaded) {
         try {
            new Thread(new SoundManager.WorkerTask(this), "Sound Library Loader").start();
         } catch (RuntimeException e) {
            logger.error(LOG_MARKER, "Error starting SoundSystem. Turning off sounds & music", e);
            this.options.setSoundLevel(SoundCategory.MASTER, 0.0F);
            this.options.saveOptions();
         }
      }
   }

   public boolean isSoundPlaying(ISound sound) {
      if (!this.loaded) {
         return false;
      } else {
         String str = (String)this.invPlayingSounds.get(sound);
         return str == null
            ? false
            : this.sndSystem.playing(str) || this.playingSoundsStopTime.containsKey(str) && (Integer)this.playingSoundsStopTime.get(str) <= this.playTime;
      }
   }

   private float getNormalizedPitch(ISound sound, SoundPoolEntry soundPoolEntry) {
      return (float)MathHelper.clamp_double((double)sound.getPitch() * soundPoolEntry.getPitch(), 0.5, 2.0);
   }

   private static URL getURLForSoundResource(ResourceLocation location) {
      String str = String.format("%s:%s:%s", "mcsounddomain", location.getResourceDomain(), location.getResourcePath());
      SoundManager.DefaultURLStreamHandler obj = new SoundManager.DefaultURLStreamHandler(location);

      try {
         return new URL((URL)null, str, obj);
      } catch (MalformedURLException e) {
         throw new Error("TODO: Sanely handle url exception! :D");
      }
   }

   public void reloadSoundSystem() {
      this.unloadSoundSystem();
      this.loadSoundSystem();
   }

   public static GameSettings accessOptions(SoundManager soundManager) {
      return soundManager.options;
   }

   private float getNormalizedVolume(ISound sound, SoundPoolEntry soundPoolEntry, SoundCategory soundCategory) {
      return (float)MathHelper.clamp_double((double)sound.getVolume() * soundPoolEntry.getVolume(), 0.0, 1.0) * this.getSoundCategoryVolume(soundCategory);
   }

   public static Marker accessLOG_MARKER() {
      return LOG_MARKER;
   }

   private float getSoundCategoryVolume(SoundCategory soundCategory) {
      return soundCategory != null && soundCategory != SoundCategory.MASTER ? this.options.getSoundLevel(soundCategory) : 1.0F;
   }

   public static SoundManager.SoundSystemStarterThread assignSndSystem(SoundManager soundManager, SoundManager.SoundSystemStarterThread soundSystemStarterThread) {
      return soundManager.sndSystem = soundSystemStarterThread;
   }

   public void pauseAllSounds() {
      for (String str : (Iterable<String>)(this.playingSounds.keySet())) {
         if (this.isSoundPlaying((ISound)this.playingSounds.get(str))) {
            logger.debug(LOG_MARKER, "Pausing channel {}", new Object[]{str});
            this.sndSystem.pause(str);
            this.pausedChannels.add(str);
         }
      }
   }

   public void stopSound(ISound sound) {
      if (this.loaded) {
         String str = (String)this.invPlayingSounds.get(sound);
         if (str != null) {
            this.sndSystem.stop(str);
         }
      }
   }

   public void setListener(EntityPlayer player, float f) {
      if (this.loaded && player != null) {
         float f2 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
         float f3 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
         double d = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
         double d2 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)player.getEyeHeight();
         double d3 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
         float f4 = MathHelper.cos((f3 + 90.0F) * (float) (Math.PI / 180.0));
         float f5 = MathHelper.sin((f3 + 90.0F) * (float) (Math.PI / 180.0));
         float f6 = MathHelper.cos(-f2 * (float) (Math.PI / 180.0));
         float f7 = MathHelper.sin(-f2 * (float) (Math.PI / 180.0));
         float f8 = MathHelper.cos((-f2 + 90.0F) * (float) (Math.PI / 180.0));
         float f9 = MathHelper.sin((-f2 + 90.0F) * (float) (Math.PI / 180.0));
         float f10 = f4 * f6;
         float f11 = f5 * f6;
         float f12 = f4 * f8;
         float f13 = f5 * f8;
         this.sndSystem.setListenerPosition((float)d, (float)d2, (float)d3);
         this.sndSystem.setListenerOrientation(f10, f7, f11, f12, f9, f13);
      }
   }

   public static SoundManager.SoundSystemStarterThread accessSndSystem(SoundManager soundManager) {
      return soundManager.sndSystem;
   }

   public void playDelayedSound(ISound sound, int n) {
      this.delayedSounds.put(sound, this.playTime + n);
   }

   public void setSoundCategoryVolume(SoundCategory soundCategory, float f) {
      if (this.loaded) {
         if (soundCategory == SoundCategory.MASTER) {
            this.sndSystem.setMasterVolume(f);
         } else {
            for (String str : (Iterable<String>)(this.categorySounds.get(soundCategory))) {
               ISound sound = (ISound)this.playingSounds.get(str);
               float normalizedVolume = this.getNormalizedVolume(sound, (SoundPoolEntry)this.playingSoundPoolEntries.get(sound), soundCategory);
               if (normalizedVolume <= 0.0F) {
                  this.stopSound(sound);
               } else {
                  this.sndSystem.setVolume(str, normalizedVolume);
               }
            }
         }
      }
   }

   public void resumeAllSounds() {
      for (String str : (Iterable<String>)(this.pausedChannels)) {
         logger.debug(LOG_MARKER, "Resuming channel {}", new Object[]{str});
         this.sndSystem.play(str);
      }

      this.pausedChannels.clear();
   }

   public void unloadSoundSystem() {
      if (this.loaded) {
         this.stopAllSounds();
         this.sndSystem.cleanup();
         this.loaded = false;
      }
   }

   public void playSound(ISound sound) {
      if (this.loaded) {
         if (this.sndSystem.getMasterVolume() <= 0.0F) {
            logger.debug(LOG_MARKER, "Skipped playing soundEvent: {}, master volume was zero", new Object[]{sound.getSoundLocation()});
         } else {
            SoundEventAccessorComposite sound2 = this.sndHandler.getSound(sound.getSoundLocation());
            if (sound2 == null) {
               logger.warn(LOG_MARKER, "Unable to play unknown soundEvent: {}", new Object[]{sound.getSoundLocation()});
            } else {
               SoundPoolEntry soundPoolEntry = sound2.cloneEntry();
               if (soundPoolEntry == SoundHandler.missing_sound) {
                  logger.warn(LOG_MARKER, "Unable to play empty soundEvent: {}", new Object[]{sound2.getSoundEventLocation()});
               } else {
                  float volume = sound.getVolume();
                  float f = 16.0F;
                  if (volume > 1.0F) {
                     f *= volume;
                  }

                  SoundCategory soundCategory = sound2.getSoundCategory();
                  float normalizedVolume = this.getNormalizedVolume(sound, soundPoolEntry, soundCategory);
                  double normalizedPitch = (double)this.getNormalizedPitch(sound, soundPoolEntry);
                  ResourceLocation soundPoolEntryLocation = soundPoolEntry.getSoundPoolEntryLocation();
                  if (normalizedVolume == 0.0F) {
                     logger.debug(LOG_MARKER, "Skipped playing sound {}, volume was zero.", new Object[]{soundPoolEntryLocation});
                  } else {
                     boolean flag = sound.canRepeat() && sound.getRepeatDelay() == 0;
                     String str = MathHelper.getRandomUuid(ThreadLocalRandom.current()).toString();
                     if (soundPoolEntry.isStreamingSound()) {
                        this.sndSystem
                           .newStreamingSource(
                              false,
                              str,
                              getURLForSoundResource(soundPoolEntryLocation),
                              soundPoolEntryLocation.toString(),
                              flag,
                              sound.getXPosF(),
                              sound.getYPosF(),
                              sound.getZPosF(),
                              sound.getAttenuationType().getTypeInt(),
                              f
                           );
                     } else {
                        this.sndSystem
                           .newSource(
                              false,
                              str,
                              getURLForSoundResource(soundPoolEntryLocation),
                              soundPoolEntryLocation.toString(),
                              flag,
                              sound.getXPosF(),
                              sound.getYPosF(),
                              sound.getZPosF(),
                              sound.getAttenuationType().getTypeInt(),
                              f
                           );
                     }

                     logger.debug(
                        LOG_MARKER,
                        "Playing sound {} for event {} as channel {}",
                        new Object[]{soundPoolEntry.getSoundPoolEntryLocation(), sound2.getSoundEventLocation(), str}
                     );
                     this.sndSystem.setPitch(str, (float)normalizedPitch);
                     this.sndSystem.setVolume(str, normalizedVolume);
                     this.sndSystem.play(str);
                     this.playingSoundsStopTime.put(str, this.playTime + 20);
                     this.playingSounds.put(str, sound);
                     this.playingSoundPoolEntries.put(sound, soundPoolEntry);
                     if (soundCategory != SoundCategory.MASTER) {
                        this.categorySounds.put(soundCategory, str);
                     }

                     if (sound instanceof ITickableSound) {
                        this.tickableSounds.add((ITickableSound)sound);
                     }
                  }
               }
            }
         }
      }
   }

   public static Logger accessLogger() {
      return logger;
   }

   public SoundManager(SoundHandler soundHandler, GameSettings gameSettings) {
      this.invPlayingSounds = ((BiMap)this.playingSounds).inverse();
      this.playingSoundPoolEntries = Maps.newHashMap();
      this.categorySounds = HashMultimap.create();
      this.tickableSounds = Lists.newArrayList();
      this.delayedSounds = Maps.newHashMap();
      this.playingSoundsStopTime = Maps.newHashMap();
      this.sndHandler = soundHandler;
      this.options = gameSettings;

      try {
         SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
         SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
      } catch (SoundSystemException e) {
         logger.error(LOG_MARKER, "Error linking with the LibraryJavaSound plug-in", e);
      }
   }

   public static boolean assignLoaded(SoundManager soundManager, boolean flag) {
      return soundManager.loaded = flag;
   }


   public static class WorkerTask implements Runnable {
      public final SoundManager soundManager;

      @Override
      public void run() {
         SoundSystemConfig.setLogger(new SoundManager.WorkerTask.DefaultSoundSystemLogger(this));
         SoundManager.assignSndSystem(this.soundManager, new SoundManager.SoundSystemStarterThread(this.soundManager, null));
         SoundManager.assignLoaded(this.soundManager, true);
         SoundManager.accessSndSystem(this.soundManager).setMasterVolume(SoundManager.accessOptions(this.soundManager).getSoundLevel(SoundCategory.MASTER));
         SoundManager.accessLogger().info(SoundManager.accessLOG_MARKER(), "Sound engine started");
      }

      public WorkerTask(SoundManager soundManager2) {
         this.soundManager = soundManager2;
      }


      public static class DefaultSoundSystemLogger extends SoundSystemLogger {
         public final SoundManager.WorkerTask startupTask;

         public void errorMessage(String str, String str2, int n) {
            if (!str2.isEmpty()) {
               SoundManager.accessLogger().error("Error in class '" + str + "'");
               SoundManager.accessLogger().error(str2);
            }
         }

         public void message(String str, int n) {
            if (!str.isEmpty()) {
               SoundManager.accessLogger().info(str);
            }
         }

         public DefaultSoundSystemLogger(SoundManager.WorkerTask obj) {
            this.startupTask = obj;
         }

         public void importantMessage(String str, int n) {
            if (!str.isEmpty()) {
               SoundManager.accessLogger().warn(str);
            }
         }
      }
   }

   public static final class DefaultURLStreamHandler extends URLStreamHandler {
      public final ResourceLocation soundLocation;

      public DefaultURLStreamHandler(ResourceLocation location) {
         this.soundLocation = location;
      }

      @Override
      public URLConnection openConnection(URL url) {
         return new SoundManager.DefaultURLStreamHandler.DefaultURLConnection(this, url);
      }


      public static class DefaultURLConnection extends URLConnection {
         public final SoundManager.DefaultURLStreamHandler streamHandler;

         @Override
         public InputStream getInputStream() throws IOException {
            return Minecraft.getMinecraft().getResourceManager().getResource(this.streamHandler.soundLocation).getInputStream();
         }

         @Override
         public void connect() throws IOException {
         }

         public DefaultURLConnection(SoundManager.DefaultURLStreamHandler obj, URL url2) {
            super(url2);
            this.streamHandler = obj;
         }
      }
   }

   public static class SoundSystemStarterThread extends SoundSystem {
      public final SoundManager soundManager;

      private SoundSystemStarterThread(SoundManager soundManager2) {
         this.soundManager = soundManager2;
      }

      public SoundSystemStarterThread(SoundManager soundManager2, SoundManager.WorkerTask obj) {
         this(soundManager2);
      }

      public boolean playing(String str) {
         synchronized (SoundSystemConfig.THREAD_SYNC) {
            if (this.soundLibrary == null) {
               return false;
            } else {
               Source source = (Source)this.soundLibrary.getSources().get(str);
               return source == null ? false : source.playing() || source.paused() || source.preLoad;
            }
         }
      }
   }
}
