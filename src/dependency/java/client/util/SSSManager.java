package client.util;

import client.setting.AutoPlayTargetEntity;
import client.setting.GameSettings;
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
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
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

public class SSSManager {
   public static final Marker marker = MarkerManager.getMarker("SOUNDS");
   public static final Logger logger = LogManager.getLogger();
   public final SoundHandler soundHandler;
   public final GameSettings gameSettings;
   public SoundSystemAdapter soundSystemAdapter;
   public boolean enabled;
   public int count = 0;
   public final Map map = HashBiMap.create();
   public final Map map4;
   public Map map5;
   public final Multimap multimap;
   public final List list;
   public final Map map3;
   public final Map map2;
   public final List list2 = new ArrayList();

   public static Logger getLogger() {
      return logger;
   }

   public void applyISoundByISound(ISound iSound) {
      if (this.enabled) {
         String text = (String)this.map4.get(iSound);
         if (text != null) {
            this.soundSystemAdapter.stop(text);
         }
      }
   }

   private static URL resolveURL(ResourceLocation location) {
      String text = String.format("%s:%s:%s", "mcsounddomain", location.getText(), location.getText1());
      URLStreamHandlerAdapter uRLStreamHandlerAdapter = new URLStreamHandlerAdapter(location);

      try {
         return new URL((URL)null, text, uRLStreamHandlerAdapter);
      } catch (MalformedURLException malformedURLException) {
         throw new Error("TODO: Sanely handle url exception! :D");
      }
   }

   public void updateState3() {
      this.count++;

      for (ISoundExtension iSoundExtension : (Iterable<ISoundExtension>)(Iterable<?>)this.list) {
         iSoundExtension.updateState();
         if (iSoundExtension.isEnabled()) {
            this.applyISoundByISound(iSoundExtension);
         } else {
            String text = (String)this.map4.get(iSoundExtension);
            this.soundSystemAdapter
               .setVolume(
                  text,
                  this.computeFactor(
                     iSoundExtension,
                     (AuxiliaryResourceLocationHolder)this.map5.get(iSoundExtension),
                     this.soundHandler.resolveCommonListEntryByResourceLocation(iSoundExtension.getPositionedSoundLocation()).getAutoPlayTargetEntity()
                  )
               );
            this.soundSystemAdapter.setPitch(text, this.computeFactor(iSoundExtension, (AuxiliaryResourceLocationHolder)this.map5.get(iSoundExtension)));
            this.soundSystemAdapter.setPosition(text, iSoundExtension.getXPosF(), iSoundExtension.getYPosF(), iSoundExtension.getZPosF());
         }
      }

      Iterator iterator = this.map.entrySet().iterator();

      while (iterator.hasNext()) {
         Entry entry = (Entry)iterator.next();
         String key = (String)entry.getKey();
         ISound value = (ISound)entry.getValue();
         if (!this.soundSystemAdapter.playing(key) && (Integer)this.map2.get(key) <= this.count) {
            int repeatDelay = value.getRepeatDelay();
            if (value.isRepeat() && repeatDelay > 0) {
               this.map3.put(value, this.count + repeatDelay);
            }

            iterator.remove();
            logger.debug(marker, "Removed channel {} because it's not playing anymore", new Object[]{key});
            this.soundSystemAdapter.removeSource(key);
            this.map2.remove(key);
            this.map5.remove(value);

            try {
               this.multimap
                  .remove(this.soundHandler.resolveCommonListEntryByResourceLocation(value.getPositionedSoundLocation()).getAutoPlayTargetEntity(), key);
            } catch (RuntimeException exception) {
            }

            if (value instanceof ISoundExtension) {
               this.list.remove(value);
            }
         }
      }

      Iterator iterator2 = this.map3.entrySet().iterator();

      while (iterator2.hasNext()) {
         Entry entry2 = (Entry)iterator2.next();
         if (this.count >= (Integer)entry2.getValue()) {
            ISound key2 = (ISound)entry2.getKey();
            if (key2 instanceof ISoundExtension) {
               ((ISoundExtension)key2).updateState();
            }

            this.applyISound(key2);
            iterator2.remove();
         }
      }
   }

   public void applyISound(ISound iSound, int count2) {
      this.map3.put(iSound, this.count + count2);
   }

   private synchronized void updateState2() {
      if (!this.enabled) {
         try {
            new Thread(new SoundEngineStartedRunnable(this), "Sound Library Loader").start();
         } catch (RuntimeException exception) {
            logger.error(marker, "Error starting SoundSystem. Turning off sounds & music", exception);
            this.gameSettings.updateAutoPlayTargetEntity(AutoPlayTargetEntity.MASTER, 0.0F);
            this.gameSettings.updateState();
         }
      }
   }

   public static SoundSystemAdapter resolveSoundSystemAdapter(SSSManager sSSManager, SoundSystemAdapter soundSystemAdapter2) {
      return sSSManager.soundSystemAdapter = soundSystemAdapter2;
   }

   public void updateState() {
      for (String text : (Iterable<String>)(Iterable<?>)this.map.keySet()) {
         if (this.matchesISound((ISound)this.map.get(text))) {
            logger.debug(marker, "Pausing channel {}", new Object[]{text});
            this.soundSystemAdapter.pause(text);
            this.list2.add(text);
         }
      }
   }

   public void applyEntityPlayer(EntityPlayer player, float factor) {
      if (this.enabled && player != null) {
         float factor2 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * factor;
         float factor3 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * factor;
         double offset = player.prevPosX + (player.posX - player.prevPosX) * (double)factor;
         double offset2 = player.prevPosY + (player.posY - player.prevPosY) * (double)factor + (double)player.getHeight2();
         double offset3 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)factor;
         float factor4 = MathHelper.cos((factor3 + 90.0F) * (float) (Math.PI / 180.0));
         factor3 = MathHelper.abs((factor3 + 90.0F) * (float) (Math.PI / 180.0));
         float factor5 = MathHelper.cos(-factor2 * (float) (Math.PI / 180.0));
         float factor6 = MathHelper.abs(-factor2 * (float) (Math.PI / 180.0));
         float factor7 = MathHelper.cos((-factor2 + 90.0F) * (float) (Math.PI / 180.0));
         factor2 = MathHelper.abs((-factor2 + 90.0F) * (float) (Math.PI / 180.0));
         float factor8 = factor4 * factor5;
         factor5 = factor3 * factor5;
         factor4 *= factor7;
         factor3 *= factor7;
         this.soundSystemAdapter.setListenerPosition((float)offset, (float)offset2, (float)offset3);
         this.soundSystemAdapter.setListenerOrientation(factor8, factor6, factor5, factor4, factor2, factor3);
      }
   }

   public static boolean matchesSSSManager(SSSManager sSSManager, boolean flag) {
      return sSSManager.enabled = flag;
   }

   public void updateState5() {
      if (this.enabled) {
         for (String text : (Iterable<String>)(Iterable<?>)this.map.keySet()) {
            this.soundSystemAdapter.stop(text);
         }

         this.map.clear();
         this.map3.clear();
         this.list.clear();
         this.multimap.clear();
         this.map5.clear();
         this.map2.clear();
      }
   }

   private float computeFactor(ISound iSound, AuxiliaryResourceLocationHolder auxiliaryResourceLocationHolder) {
      return (float)MathHelper.getDouble7((double)iSound.getPitch() * auxiliaryResourceLocationHolder.getValue(), 0.5, 2.0);
   }

   public static SoundSystemAdapter resolveSoundSystemAdapter(SSSManager sSSManager) {
      return sSSManager.soundSystemAdapter;
   }

   public void updateState7() {
      if (this.enabled) {
         this.updateState5();
         this.soundSystemAdapter.cleanup();
         this.enabled = false;
      }
   }

   public void applyAutoPlayTargetEntity(AutoPlayTargetEntity autoPlayTargetEntity, float factor) {
      if (this.enabled) {
         if (autoPlayTargetEntity == AutoPlayTargetEntity.MASTER) {
            this.soundSystemAdapter.setMasterVolume(factor);
         } else {
            for (String text : (Iterable<String>)(Iterable<?>)this.multimap.get(autoPlayTargetEntity)) {
               ISound iSound = (ISound)this.map.get(text);
               float factor2 = this.computeFactor(iSound, (AuxiliaryResourceLocationHolder)this.map5.get(iSound), autoPlayTargetEntity);
               if (factor2 <= 0.0F) {
                  this.applyISoundByISound(iSound);
               } else {
                  this.soundSystemAdapter.setVolume(text, factor2);
               }
            }
         }
      }
   }

   private float computeFactor(AutoPlayTargetEntity autoPlayTargetEntity) {
      return autoPlayTargetEntity != null && autoPlayTargetEntity != AutoPlayTargetEntity.MASTER ? this.gameSettings.getScaleFactor(autoPlayTargetEntity) : 1.0F;
   }

   public void applyISound(ISound iSound) {
      if (this.enabled) {
         if (this.soundSystemAdapter.getMasterVolume() <= 0.0F) {
            logger.debug(marker, "Skipped playing soundEvent: {}, master volume was zero", new Object[]{iSound.getPositionedSoundLocation()});
         } else {
            CommonListEntry commonListEntry = this.soundHandler.resolveCommonListEntryByResourceLocation(iSound.getPositionedSoundLocation());
            if (commonListEntry == null) {
               logger.warn(marker, "Unable to play unknown soundEvent: {}", new Object[]{iSound.getPositionedSoundLocation()});
            } else {
               AuxiliaryResourceLocationHolder auxiliaryResourceLocationHol = commonListEntry.getAuxiliaryResourceLocationHol();
               if (auxiliaryResourceLocationHol == SoundHandler.auxiliaryResourceLocationHolder) {
                  logger.warn(marker, "Unable to play empty soundEvent: {}", new Object[]{commonListEntry.getResourceLocation()});
               } else {
                  float volume = iSound.getVolume();
                  float factor = 16.0F;
                  if (volume > 1.0F) {
                     factor *= volume;
                  }

                  AutoPlayTargetEntity autoPlayTargetEntity = commonListEntry.getAutoPlayTargetEntity();
                  volume = this.computeFactor(iSound, auxiliaryResourceLocationHol, autoPlayTargetEntity);
                  double offset = (double)this.computeFactor(iSound, auxiliaryResourceLocationHol);
                  ResourceLocation resourceLocation = auxiliaryResourceLocationHol.getResourceLocation();
                  if (volume == 0.0F) {
                     logger.debug(marker, "Skipped playing sound {}, volume was zero.", new Object[]{resourceLocation});
                  } else {
                     boolean flag = iSound.isRepeat() && iSound.getRepeatDelay() == 0;
                     String text = MathHelper.processAction(ThreadLocalRandom.current()).toString();
                     if (auxiliaryResourceLocationHol.isEnabled()) {
                        this.soundSystemAdapter
                           .newStreamingSource(
                              false,
                              text,
                              resolveURL(resourceLocation),
                              resourceLocation.toString(),
                              flag,
                              iSound.getXPosF(),
                              iSound.getYPosF(),
                              iSound.getZPosF(),
                              iSound.getAttenuationType().getCount(),
                              factor
                           );
                     } else {
                        this.soundSystemAdapter
                           .newSource(
                              false,
                              text,
                              resolveURL(resourceLocation),
                              resourceLocation.toString(),
                              flag,
                              iSound.getXPosF(),
                              iSound.getYPosF(),
                              iSound.getZPosF(),
                              iSound.getAttenuationType().getCount(),
                              factor
                           );
                     }

                     logger.debug(
                        marker, "Playing sound {} for event {} as channel {}", new Object[]{auxiliaryResourceLocationHol.getResourceLocation(), commonListEntry.getResourceLocation(), text}
                     );
                     this.soundSystemAdapter.setPitch(text, (float)offset);
                     this.soundSystemAdapter.setVolume(text, volume);
                     this.soundSystemAdapter.play(text);
                     this.map2.put(text, this.count + 20);
                     this.map.put(text, iSound);
                     this.map5.put(iSound, auxiliaryResourceLocationHol);
                     if (autoPlayTargetEntity != AutoPlayTargetEntity.MASTER) {
                        this.multimap.put(autoPlayTargetEntity, text);
                     }

                     if (iSound instanceof ISoundExtension) {
                        this.list.add((ISoundExtension)iSound);
                     }
                  }
               }
            }
         }
      }
   }

   public void updateState6() {
      for (String text : (Iterable<String>)(Iterable<?>)this.list2) {
         logger.debug(marker, "Resuming channel {}", new Object[]{text});
         this.soundSystemAdapter.play(text);
      }

      this.list2.clear();
   }

   public static GameSettings resolveGameSettings(SSSManager sSSManager) {
      return sSSManager.gameSettings;
   }

   public void updateState4() {
      this.updateState7();
      this.updateState2();
   }

   public static Marker getMarker() {
      return marker;
   }

   public boolean matchesISound(ISound iSound) {
      if (!this.enabled) {
         return false;
      } else {
         String text = (String)this.map4.get(iSound);
         return text == null ? false : this.soundSystemAdapter.playing(text) || this.map2.containsKey(text) && (Integer)this.map2.get(text) <= this.count;
      }
   }

   private float computeFactor(ISound iSound, AuxiliaryResourceLocationHolder auxiliaryResourceLocationHolder, AutoPlayTargetEntity autoPlayTargetEntity) {
      return (float)MathHelper.getDouble7((double)iSound.getVolume() * auxiliaryResourceLocationHolder.getValue2(), 0.0, 1.0) * this.computeFactor(autoPlayTargetEntity);
   }

   public SSSManager(SoundHandler soundHandler2, GameSettings gameSettings2) {
      this.map4 = ((BiMap)this.map).inverse();
      this.map5 = Maps.newHashMap();
      this.multimap = HashMultimap.create();
      this.list = Lists.newArrayList();
      this.map3 = Maps.newHashMap();
      this.map2 = Maps.newHashMap();
      this.soundHandler = soundHandler2;
      this.gameSettings = gameSettings2;

      try {
         SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
         SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
      } catch (SoundSystemException soundSystemException) {
         logger.error(marker, "Error linking with the LibraryJavaSound plug-in", soundSystemException);
      }
   }
}
