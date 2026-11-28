package net.minecraft.client.audio;

import java.lang.reflect.Type;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoundHandler implements ITickable, IResourceManagerReloadListener {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson GSON = new GsonBuilder().registerTypeAdapter(SoundList.class, new SoundListSerializer()).create();
   private static final ParameterizedType TYPE = new SoundHandler.DefaultParameterizedType();
   public static final SoundPoolEntry missing_sound = new SoundPoolEntry(new ResourceLocation("meta:missing_sound"), 0.0, 0.0, false);
   private final SoundRegistry sndRegistry = new SoundRegistry();
   private final SoundManager sndManager;
   private final IResourceManager mcResourceManager;

   public void pauseSounds() {
      this.sndManager.pauseAllSounds();
   }

   public void registerAugustusSound(ResourceLocation location, String str) {
      SoundList soundList = new SoundList();
      soundList.setSoundCategory(SoundCategory.AUGUSTUS);
      soundList.setReplaceExisting(false);
      SoundList.SoundEntry soundEntry = new SoundList.SoundEntry();
      soundEntry.setSoundEntryName(str);
      soundList.getSoundList().add(soundEntry);
      this.loadSoundResource(location, soundList);
   }

   public void resumeSounds() {
      this.sndManager.resumeAllSounds();
   }

   public void playSound(ISound sound) {
      this.sndManager.playSound(sound);
   }

   public void stopSounds() {
      this.sndManager.stopAllSounds();
   }

   public void playDelayedSound(ISound sound, int n) {
      this.sndManager.playDelayedSound(sound, n);
   }

   public boolean isSoundPlaying(ISound sound) {
      return this.sndManager.isSoundPlaying(sound);
   }

   public SoundHandler(IResourceManager resourceManager, GameSettings gameSettings) {
      this.mcResourceManager = resourceManager;
      this.sndManager = new SoundManager(this, gameSettings);
   }

   @Override
   public void update() {
      this.sndManager.updateAllSounds();
   }

   public void setListener(EntityPlayer player, float f) {
      this.sndManager.setListener(player, f);
   }

   private void loadSoundResource(ResourceLocation location, SoundList soundList) {
      boolean flag = !this.sndRegistry.containsKey(location);
      SoundEventAccessorComposite composite;
      if (!flag && !soundList.canReplaceExisting()) {
         composite = (SoundEventAccessorComposite)this.sndRegistry.getObject(location);
      } else {
         if (!flag) {
            LOGGER.debug("Replaced sound event location {}", new Object[]{location});
         }

         composite = new SoundEventAccessorComposite(location, 1.0, 1.0, soundList.getSoundCategory());
         this.sndRegistry.registerSound(composite);
      }

      for (SoundList.SoundEntry soundEntry : (Iterable<SoundList.SoundEntry>)(soundList.getSoundList())) {
         String soundEntryName = soundEntry.getSoundEntryName();
         ResourceLocation location2 = new ResourceLocation(soundEntryName);
         String str = soundEntryName.contains(":") ? location2.getResourceDomain() : location.getResourceDomain();
         Object obj;
         switch (soundEntry.getSoundEntryType()) {
            case FILE:
               ResourceLocation location3 = new ResourceLocation(str, "sounds/" + location2.getResourcePath() + ".ogg");
               InputStream in = null;

               try {
                  in = this.mcResourceManager.getResource(location3).getInputStream();
               } catch (FileNotFoundException e) {
                  LOGGER.warn("File {} does not exist, cannot add it to event {}", new Object[]{location3, location});
                  continue;
               } catch (IOException e2) {
                  LOGGER.warn("Could not load sound file " + location3 + ", cannot add it to event " + location, e2);
                  continue;
               } finally {
                  IOUtils.closeQuietly(in);
               }

               obj = new SoundEventAccessor(
                  new SoundPoolEntry(location3, (double)soundEntry.getSoundEntryPitch(), (double)soundEntry.getSoundEntryVolume(), soundEntry.isStreaming()),
                  soundEntry.getSoundEntryWeight()
               );
               break;
            case SOUND_EVENT:
               obj = new SoundHandler.DefaultSoundEventAccessor(this, str, soundEntry);
               break;
            default:
               throw new IllegalStateException("IN YOU FACE");
         }

         composite.addSoundToEventPool((ISoundEventAccessor)obj);
      }
   }

   @Override
   public void onResourceManagerReload(IResourceManager resourceManager) {
      this.sndManager.reloadSoundSystem();
      this.sndRegistry.clearMap();

      for (String str : (Iterable<String>)(resourceManager.getResourceDomains())) {
         try {
            for (IResource resource : (Iterable<IResource>)(resourceManager.getAllResources(new ResourceLocation(str, "sounds.json")))) {
               try {

                  for (Entry entry : (Iterable<Entry>)(this.getSoundMap(resource.getInputStream()).entrySet())) {
                     this.loadSoundResource(new ResourceLocation(str, (String)entry.getKey()), (SoundList)entry.getValue());
                  }
               } catch (RuntimeException e) {
                  LOGGER.warn("Invalid sounds.json", e);
               }
            }
         } catch (IOException e2) {
         }
      }

      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/alarm.ogg"), "alarm");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/loginFailed.ogg"), "loginFailed");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/loginSuccessful.ogg"), "loginSuccessful");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/toggleSound.ogg"), "toggleSound");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/toggleSound2.ogg"), "toggleSound2");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/welcome_to_augustus.ogg"), "welcome_to_augustus");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/buttonClick.ogg"), "buttonClick");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/enable.ogg"), "enable");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/halloweenToggleSound.ogg"), "halloweenToggleSound");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/jumpscare.ogg"), "jumpscare");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/disable.ogg"), "disable");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/mlg/hitmarker.ogg"), "mlg/hitmarker");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/knockknock.ogg"), "knockknock");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/levelup.ogg"), "levelup");
      this.registerAugustusSound(new ResourceLocation("augustus", "sounds/epsound.ogg"), "epsound");
   }

   public void setSoundLevel(SoundCategory soundCategory, float f) {
      if (soundCategory == SoundCategory.MASTER && f <= 0.0F) {
         this.stopSounds();
      }

      this.sndManager.setSoundCategoryVolume(soundCategory, f);
   }

   public Map getSoundMap(InputStream in) {
      Map map;
      try {
         map = (Map)GSON.fromJson(new InputStreamReader(in), TYPE);
      } finally {
         IOUtils.closeQuietly(in);
      }

      return map;
   }

   public static SoundRegistry accessSndRegistry(SoundHandler soundHandler) {
      return soundHandler.sndRegistry;
   }

   public void unloadSounds() {
      this.sndManager.unloadSoundSystem();
   }

   public SoundEventAccessorComposite getSound(ResourceLocation location) {
      return (SoundEventAccessorComposite)this.sndRegistry.getObject(location);
   }

   public void stopSound(ISound sound) {
      this.sndManager.stopSound(sound);
   }

   public SoundEventAccessorComposite getRandomSoundFromCategories(SoundCategory[] soundCategorys) {
      ArrayList list = Lists.newArrayList();

      for (ResourceLocation location : (Iterable<ResourceLocation>)(this.sndRegistry.getKeys())) {
         SoundEventAccessorComposite object = (SoundEventAccessorComposite)this.sndRegistry.getObject(location);
         if (ArrayUtils.contains(soundCategorys, object.getSoundCategory())) {
            list.add(object);
         }
      }

      return list.isEmpty() ? null : (SoundEventAccessorComposite)list.get(new Random().nextInt(list.size()));
   }

public static final class DefaultParameterizedType implements ParameterizedType {
      @Override
      public Type[] getActualTypeArguments() {
         return new Type[]{String.class, SoundList.class};
      }

      @Override
      public Type getRawType() {
         return Map.class;
      }

      @Override
      public Type getOwnerType() {
         return null;
      }
   }

   public static class DefaultSoundEventAccessor implements ISoundEventAccessor {
      public final ResourceLocation soundLocation;
      public final String resourceDomain;
      public final SoundList.SoundEntry soundEntry;
      public final SoundHandler soundHandler;

      @Override
      public int getWeight() {
         SoundEventAccessorComposite object = (SoundEventAccessorComposite)SoundHandler.accessSndRegistry(this.soundHandler).getObject(this.soundLocation);
         return object == null ? 0 : object.getWeight();
      }

      @Override
      public Object cloneEntryObject() {
         return this.cloneEntry();
      }

      public SoundPoolEntry cloneEntry() {
         SoundEventAccessorComposite object = (SoundEventAccessorComposite)SoundHandler.accessSndRegistry(this.soundHandler).getObject(this.soundLocation);
         return object == null ? SoundHandler.missing_sound : object.cloneEntry();
      }

      public DefaultSoundEventAccessor(SoundHandler soundHandler2, String str, SoundList.SoundEntry soundEntry2) {
         this.soundHandler = soundHandler2;
         this.resourceDomain = str;
         this.soundEntry = soundEntry2;
         this.soundLocation = new ResourceLocation(this.resourceDomain, this.soundEntry.getSoundEntryName());
      }
   }

}
