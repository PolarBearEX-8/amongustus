package net.minecraft.client.audio;

import client.event.PostMotionDispatcher;
import client.event.PreUpdateConsumer;
import client.setting.AutoPlayTargetEntity;
import client.setting.GameSettings;
import client.util.AltMapEntry;
import client.util.AuxiliaryListenerAdapter8;
import client.util.AuxiliaryResourceLocationHolder;
import client.util.BridgeStringEntry;
import client.util.CommonListEntry;
import client.util.CustomListEntry;
import client.util.EntryJsonDeserializer;
import client.util.ExternalIntArrayEntry;
import client.util.InternalParameterizedTypeAdapter;
import client.util.ListenerAdapter20;
import client.util.SSSManager;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoundHandler implements IResourceManagerReloadListener, PreUpdateConsumer {
   public static final Logger logger = LogManager.getLogger();
   public static final Gson gson = new GsonBuilder().registerTypeAdapter(CustomListEntry.class, new EntryJsonDeserializer()).create();
   public static final ParameterizedType parameterizedType = new InternalParameterizedTypeAdapter();
   public static final AuxiliaryResourceLocationHolder auxiliaryResourceLocationHolder = new AuxiliaryResourceLocationHolder(
      new ResourceLocation("meta:missing_sound"), 0.0, 0.0, false
   );
   public final AltMapEntry altMapEntry = new AltMapEntry();
   public final SSSManager sSSManager;
   public final IResourceManager iResourceManager;

   public void updateState4() {
      this.sSSManager.updateState5();
   }

   public void updateState3() {
      this.sSSManager.updateState6();
   }

   public boolean matchesISound(ISound iSound) {
      return this.sSSManager.matchesISound(iSound);
   }

   public void applyISound(ISound iSound, int count) {
      this.sSSManager.applyISound(iSound, count);
   }

   public Map resolveMap(InputStream inputStream) {
      Map map;
      try {
         map = (Map)gson.fromJson(new InputStreamReader(inputStream), parameterizedType);
      } finally {
         IOUtils.closeQuietly(inputStream);
      }

      return map;
   }

   private void applyResourceLocationByResourceLocation(ResourceLocation location, CustomListEntry customListEntry) {
      boolean flag = !this.altMapEntry.containsKey(location);
      CommonListEntry commonListEntry;
      if (!flag && !customListEntry.isEnabled()) {
         commonListEntry = (CommonListEntry)this.altMapEntry.getObject(location);
      } else {
         if (!flag) {
            logger.debug("Replaced sound event location {}", new Object[]{location});
         }

         commonListEntry = new CommonListEntry(location, 1.0, 1.0, customListEntry.getAutoPlayTargetEntity());
         this.altMapEntry.applyCommonListEntry(commonListEntry);
      }

      for (BridgeStringEntry bridgeStringEntry : (Iterable<BridgeStringEntry>)(Iterable)(customListEntry.getList())) {
         String labelText = bridgeStringEntry.getLabelText();
         ResourceLocation location2 = new ResourceLocation(labelText);
         String text = labelText.contains(":") ? location2.getText() : location.getText();
         Object entry;
         switch (ExternalIntArrayEntry.countArray[bridgeStringEntry.getMovementAlphaProperty().ordinal()]) {
            case 1:
               ResourceLocation location3 = new ResourceLocation(text, "sounds/" + location2.getText1() + ".ogg");
               InputStream inputStream = null;

               try {
                  inputStream = this.iResourceManager.resolveIResourceByResourceLocation(location3).getInputStream();
               } catch (FileNotFoundException fileNotFoundException) {
                  logger.warn("File {} does not exist, cannot add it to event {}", new Object[]{location3, location});
                  continue;
               } catch (IOException ioException) {
                  logger.warn("Could not load sound file " + location3 + ", cannot add it to event " + location, ioException);
                  continue;
               } finally {
                  IOUtils.closeQuietly(inputStream);
               }

               entry = new AuxiliaryListenerAdapter8(
                  new AuxiliaryResourceLocationHolder(location3, (double)bridgeStringEntry.getFactor2(), (double)bridgeStringEntry.getFactor(), bridgeStringEntry.isEnabled()), bridgeStringEntry.getCount()
               );
               break;
            case 2:
               entry = new ListenerAdapter20(this, text, bridgeStringEntry);
               break;
            default:
               throw new IllegalStateException("IN YOU FACE");
         }

         commonListEntry.add((PostMotionDispatcher)entry);
      }
   }

   public CommonListEntry resolveCommonListEntryByResourceLocation(ResourceLocation location) {
      return (CommonListEntry)this.altMapEntry.getObject(location);
   }

   public void updateState2() {
      this.sSSManager.updateState();
   }

   public static AltMapEntry resolveAltMapEntry(SoundHandler soundHandler) {
      return soundHandler.altMapEntry;
   }

   public void updateState5() {
      this.sSSManager.updateState7();
   }

   public CommonListEntry resolveCommonListEntry(AutoPlayTargetEntity[] autoPlayTargetEntityArray) {
      ArrayList list = Lists.newArrayList();

      for (ResourceLocation location : (Iterable<ResourceLocation>)(Iterable)(this.altMapEntry.getSet())) {
         CommonListEntry object = (CommonListEntry)this.altMapEntry.getObject(location);
         if (ArrayUtils.contains(autoPlayTargetEntityArray, object.getAutoPlayTargetEntity())) {
            list.add(object);
         }
      }

      return list.isEmpty() ? null : (CommonListEntry)list.get(new Random().nextInt(list.size()));
   }

   public void applyISound2(ISound iSound) {
      this.sSSManager.applyISound(iSound);
   }

   public void applyResourceLocation2(ResourceLocation location, String text) {
      CustomListEntry customListEntry = new CustomListEntry();
      customListEntry.setAutoPlayTargetEntity(AutoPlayTargetEntity.AUGUSTUS);
      customListEntry.setEnabled(false);
      BridgeStringEntry bridgeStringEntry = new BridgeStringEntry();
      bridgeStringEntry.setLabelText(text);
      customListEntry.getList().add(bridgeStringEntry);
      this.applyResourceLocationByResourceLocation(location, customListEntry);
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager2) {
      this.sSSManager.updateState4();
      this.altMapEntry.clear();

      for (String text : (Iterable<String>)(Iterable)(iResourceManager2.getSet())) {
         try {
            for (IResource iResource : (Iterable<IResource>)(Iterable)(iResourceManager2.resolveListByResourceLocation(new ResourceLocation(text, "sounds.json")))) {
               try {
                  for (Entry entry : (Iterable<Entry>)(Iterable)(this.resolveMap(iResource.getInputStream()).entrySet())) {
                     this.applyResourceLocationByResourceLocation(new ResourceLocation(text, (String)entry.getKey()), (CustomListEntry)entry.getValue());
                  }
               } catch (RuntimeException exception) {
                  logger.warn("Invalid sounds.json", exception);
               }
            }
         } catch (IOException ioException) {
         }
      }

      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/alarm.ogg"), "alarm");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/loginFailed.ogg"), "loginFailed");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/loginSuccessful.ogg"), "loginSuccessful");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/toggleSound.ogg"), "toggleSound");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/toggleSound2.ogg"), "toggleSound2");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/welcome_to_augustus.ogg"), "welcome_to_augustus");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/buttonClick.ogg"), "buttonClick");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/enable.ogg"), "enable");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/halloweenToggleSound.ogg"), "halloweenToggleSound");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/jumpscare.ogg"), "jumpscare");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/disable.ogg"), "disable");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/mlg/hitmarker.ogg"), "mlg/hitmarker");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/knockknock.ogg"), "knockknock");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/levelup.ogg"), "levelup");
      this.applyResourceLocation2(new ResourceLocation("augustus", "sounds/epsound.ogg"), "epsound");
   }

   public void applyEntityPlayer(EntityPlayer player, float factor) {
      this.sSSManager.applyEntityPlayer(player, factor);
   }

   @Override
   public void updateState() {
      this.sSSManager.updateState3();
   }

   public void applyISound(ISound iSound) {
      this.sSSManager.applyISoundByISound(iSound);
   }

   public void applyAutoPlayTargetEntity(AutoPlayTargetEntity autoPlayTargetEntity, float factor) {
      if (autoPlayTargetEntity == AutoPlayTargetEntity.MASTER && factor <= 0.0F) {
         this.updateState4();
      }

      this.sSSManager.applyAutoPlayTargetEntity(autoPlayTargetEntity, factor);
   }

   public SoundHandler(IResourceManager iResourceManager2, GameSettings gameSettings) {
      this.iResourceManager = iResourceManager2;
      this.sSSManager = new SSSManager(this, gameSettings);
   }
}
