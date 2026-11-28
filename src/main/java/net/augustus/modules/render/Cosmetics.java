package net.augustus.modules.render;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.cosmetics.model.CosmeticModel;
import net.augustus.cosmetics.MoreCosmeticsCore;
import net.augustus.settings.ColorSetting;
import net.augustus.cosmetics.CosmeticSettingEntry;
import net.augustus.utils.TupleTriple;
import net.augustus.events.listeners.TickListener;
import net.augustus.cosmetics.CosmeticDataManager;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.labymod.ModelData;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;

public class Cosmetics extends Module implements TickListener {
   private final HashMap remotePlayerCosmetics;
   private final java.util.ArrayList localCosmeticEntries;
   private final TimeHelper timeHelper;
   private boolean pendingSyncOnEnable;
   private boolean pendingSyncOnDisable;
   private int lastSyncHash;
   public final BooleanValue hideCosmeticsOfOtherUsers;
   public final BooleanValue hintMessageThatRenderingOfCosmeticsCanBeDisabled;

   public Cosmetics() {
       super("Cosmetics", "Kosmetische Gegenstände", new Color(0, 244, 231, 255), Categorys.RENDER);
      this.remotePlayerCosmetics = new HashMap();
      this.localCosmeticEntries = new java.util.ArrayList();
      this.timeHelper = new TimeHelper();
      this.pendingSyncOnEnable = false;
      this.pendingSyncOnDisable = false;
      MoreCosmeticsCore.getInstance().getModelLoader().getModelsByName().forEach((obj, obj2) -> this.registerLocalCosmeticSetting((String)obj, (CosmeticModel)obj2));
      this.hideCosmeticsOfOtherUsers = new BooleanValue(this, "Hide cosmetics of other users", false);
      this.hintMessageThatRenderingOfCosmeticsCanBeDisabled = (BooleanValue)new BooleanValue(this, "Hint message that rendering of cosmetics can be disabled", true).withVisibility(() -> this.isCosmeticsHintVisible());
   }

   public void onEnable() {
      this.pendingSyncOnEnable = true;
   }

   public void onDisable() {
      this.pendingSyncOnDisable = true;
      this.clearLocalPlayerCosmetics();
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   public void onLateTick() {
   }

   public void applyPlayerCosmetics(AbstractClientPlayer clientPlayer) {
      if (clientPlayer instanceof EntityPlayerSP) {
         this.applyLocalPlayerCosmetics(clientPlayer);
      }
   }

   public void applyLocalPlayerCosmetics(AbstractClientPlayer clientPlayer) {
      if (this.isToggled()) {
         this.applyCosmeticEntries(this.localCosmeticEntries, clientPlayer);
      }
   }

   public void updateRemotePlayerCosmetics(String str, int n, String str2, int n2, Map map, Map map2, Map map3, Map map4, Map map5, Map map6, Map map7, Map map8) {
      TupleTriple tupleTriple = (TupleTriple)this.remotePlayerCosmetics.get(str);
      HashMap map9 = tupleTriple != null ? (HashMap)tupleTriple.getThird() : new HashMap();
      if (tupleTriple == null) {
         this.initRemoteCosmeticEntries(map9);
      }

      AtomicInteger atomicInteger = new AtomicInteger();
      atomicInteger.set(0);
      map9.forEach((k, v) -> applyRemoteCosmeticEntryData(n2, map, map2, map3, map4, map5, map6, map7, map8, atomicInteger, (String)k, (CosmeticSettingEntry)v));
      this.remotePlayerCosmetics.put(str, new TupleTriple(n, str2, map9));
   }

   private void initRemoteCosmeticEntries(HashMap map) {
      MoreCosmeticsCore.getInstance().getModelLoader().getModelsByName().forEach((obj, obj2) -> createRemoteCosmeticEntry(map, (String)obj, (CosmeticModel)obj2));
   }

   public void removeRemotePlayerCosmetics(String str) {
      TupleTriple tupleTriple = (TupleTriple)this.remotePlayerCosmetics.get(str);
      if (tupleTriple != null) {
         HashMap third = (HashMap)tupleTriple.getThird();
         EntityPlayer player = this.findWorldPlayerByName(str);
         if (player instanceof AbstractClientPlayer) {
            this.updateCosmeticEntries(third.values(), (AbstractClientPlayer)player, true);
            this.remotePlayerCosmetics.remove(str);
         }
      }
   }

   public void clearAllRemoteCosmetics() {
      this.remotePlayerCosmetics.forEach((obj, obj2) -> this.disableRemotePlayerEntry((String)obj, (TupleTriple)obj2));
      this.remotePlayerCosmetics.clear();
   }

   private void applyCosmeticEntries(Collection items, AbstractClientPlayer clientPlayer) {
      this.updateCosmeticEntries(items, clientPlayer, false);
   }

   private void updateCosmeticEntries(Collection items, AbstractClientPlayer clientPlayer, boolean flag) {
      if (clientPlayer != null && items != null) {
         for (CosmeticSettingEntry cosmeticSettingEntry : (Iterable<CosmeticSettingEntry>)(items)) {
            MoreCosmeticsCore.getInstance().getCosmeticDataManager().applyCosmeticToPlayer(!flag && cosmeticSettingEntry.getEnabledSetting().getValue(), cosmeticSettingEntry.getModel(), clientPlayer, (float)cosmeticSettingEntry.getScale(), (float)cosmeticSettingEntry.getHeight(), cosmeticSettingEntry.getColorSettings());
         }
      }
   }

   private EntityPlayer findWorldPlayerByName(String str) {
      return mc.theWorld != null && !mc.theWorld.playerEntities.isEmpty() ? (EntityPlayer)mc.theWorld.playerEntities.stream().filter(obj -> ((EntityPlayer)obj).getGameProfile() != null && ((EntityPlayer)obj).getGameProfile().getName().equals(str)).findFirst().orElse(null) : null;
   }

   public boolean isPendingSyncOnDisable() {
      return this.pendingSyncOnDisable;
   }

   public void setPendingSyncOnDisable(boolean flag) {
      this.pendingSyncOnDisable = flag;
   }

   public void initLocalPlayerModelData() {

      for (CosmeticSettingEntry cosmeticSettingEntry : (Iterable<CosmeticSettingEntry>)(this.localCosmeticEntries)) {
         CosmeticModel model = cosmeticSettingEntry.getModel();
         ModelData orCreateModelData = MoreCosmeticsCore.getInstance().getCosmeticDataManager().getOrCreateModelData(cosmeticSettingEntry.getModel(), true);
         mc.thePlayer.getCosmetics().putIfAbsent(model.getId(), orCreateModelData.clone_m1());
      }
   }

   public void clearLocalPlayerCosmetics() {
      EntityPlayerSP thePlayer = mc.thePlayer;
      this.updateCosmeticEntries(this.localCosmeticEntries, (AbstractClientPlayer)thePlayer, true);
   }

   private static boolean matchesPlayerName(String str, EntityPlayer player) {
      return player.getGameProfile() != null && player.getGameProfile().getName().equals(str);
   }

   private void disableRemotePlayerEntry(String str, TupleTriple tupleTriple) {
      if (tupleTriple != null && str != null) {
         HashMap third = (HashMap)tupleTriple.getThird();
         EntityPlayer player = this.findWorldPlayerByName(str);
         if (player instanceof AbstractClientPlayer) {
            this.updateCosmeticEntries(third.values(), (AbstractClientPlayer)player, true);
         }
      }
   }

   private static void createRemoteCosmeticEntry(HashMap map, String str, CosmeticModel cosmeticModel) {
      if (map.size() <= 40) {
         BooleanValue booleanValue = new BooleanValue(null, str, false);
         DoubleValue doubleSetting = new DoubleValue(null, str + "Scale", 1.0, 0.5, 2.0, 2);
         DoubleValue doubleSetting2 = new DoubleValue(null, str + "Height", 0.0, -2.0, 2.0, 2);
         ModelData orCreateModelData = MoreCosmeticsCore.getInstance().getCosmeticDataManager().getOrCreateModelData(cosmeticModel, true);
         int length = orCreateModelData.model.length;
         ColorSetting[] colorSettings = new ColorSetting[length];

         for (int i = 0; i < length; i++) {
            colorSettings[i] = new ColorSetting((net.augustus.modules.Module)null, str + "Color" + i, new Color(orCreateModelData.model[i].color));
         }

         map.put(str, new CosmeticSettingEntry(cosmeticModel, booleanValue, doubleSetting, doubleSetting2, colorSettings, null));
      }
   }

   private static void applyRemoteCosmeticEntryData(int maxCount, Map enabledMap, Map f1Map, Map f2Map, Map cMap, Map b1Map, Map b2Map, Map d1Map, Map d2Map, AtomicInteger atomicInteger, String str, CosmeticSettingEntry cosmeticSettingEntry) {
      Boolean bool = (Boolean)enabledMap.get(str);
      if (bool != null) {
         int n = 0;
         if (bool) {
            n = atomicInteger.incrementAndGet();
         }

         cosmeticSettingEntry.getEnabledSetting().setValue(bool && n <= maxCount);
         if (bool) {
            Float f = (Float)f1Map.get(str);
            if (f != null && !Float.isNaN(f)) {
               cosmeticSettingEntry.setScale((double)f.floatValue());
            }

            Float f2 = (Float)f2Map.get(str);
            if (f2 != null && !Float.isNaN(f2)) {
               cosmeticSettingEntry.setHeight((double)f2.floatValue());
            }

            int[] ints = (int[])cMap.get(str);
            boolean[] flags = (boolean[])b1Map.get(str);
            boolean[] flags2 = (boolean[])b2Map.get(str);
            double[] doubles = (double[])d1Map.get(str);
            double[] doubles2 = (double[])d2Map.get(str);

            for (int i = 0; i < cosmeticSettingEntry.getColorSettings().length; i++) {
               ColorSetting colorSetting = cosmeticSettingEntry.getColorSettings()[i];
               colorSetting.setPrimaryColor(new Color(ints[i], true));
               colorSetting.setRainbowSpeed(doubles[i]);
               colorSetting.setAstolfoSpeed(doubles2[i]);
               colorSetting.setRainbowEnabled(flags[i]);
               colorSetting.setAstolfoEnabled(flags2[i]);
            }
         }
      }
   }

   private boolean isCosmeticsHintVisible() {
      return !this.hideCosmeticsOfOtherUsers.getValue();
   }

   private void registerLocalCosmeticSetting(String str, CosmeticModel cosmeticModel) {
      SettingGroup settingGroup = (SettingGroup)(new SettingGroup(this, "Group" + str, true)).withDisplayName(str);
      BooleanValue booleanValue = new BooleanValue(this, str, false, settingGroup);
      DoubleValue doubleSetting = new DoubleValue(this, str + "Scale", 1.0, 0.5, 2.0, 2, settingGroup);
      DoubleValue doubleSetting2 = doubleSetting.visibleWhen(() -> booleanValue.getValue());
      doubleSetting = new DoubleValue(this, str + "Height", 0.0, -2.0, 2.0, 2, settingGroup);
      DoubleValue doubleSetting3 = doubleSetting.visibleWhen(() -> booleanValue.getValue());
      ModelData orCreateModelData = MoreCosmeticsCore.getInstance().getCosmeticDataManager().getOrCreateModelData(cosmeticModel, true);
      int length = orCreateModelData.model.length;
      ColorSetting[] colorSettings = new ColorSetting[length];

      for (int i = 0; i < length; i++) {
         String str2 = str + "Color" + i;
         Color color2 = new Color(orCreateModelData.model[i].color);
         colorSettings[i] = (ColorSetting)new ColorSetting(this, str2, color2, settingGroup).withVisibility(() -> booleanValue.getValue());
      }

      this.localCosmeticEntries.add(new CosmeticSettingEntry(cosmeticModel, booleanValue, doubleSetting2, doubleSetting3, colorSettings, null));
   }


}
