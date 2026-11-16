package augustus.modules.render;

import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.CosmeticsNetworkHandler;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.CosmeticItem;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.AutoReportCache;
import client.util.BooleanSupplier;
import client.util.CosmeticItemRegistry;
import client.util.CosmeticsDataManager;
import client.util.CosmeticsItemData;
import client.util.CosmeticsItemSetting;
import client.util.ModuleDisableRule;
import client.util.MoreCosmeticsManager;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.labymod.ModelData;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;

public class Cosmetics extends Module implements TickListener {
    public HashMap cosmeticsMap = new HashMap();
    public ArrayList itemList3 = new ArrayList();
    public TimeHelper timer = new TimeHelper();
    public boolean enabled2 = false;
    public boolean expanded2 = false;
    public int keyCode2;
    public static boolean activeState;

    public Cosmetics() {
        super("Cosmetics", "Kosmetische Gegenstände", new Color(0, 244, 231, 255), Category.RENDER);
        HashMap entryMap2 = MoreCosmeticsManager.getMoreCosmeticsManager().getFailedToLoadMaskFromManager().getEntryMap2();
        BiConsumer biConsumer = (object, object2) -> this.updateText((String)object, (CosmeticsItemData)object2);
        entryMap2.forEach(biConsumer);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.expanded2 = true;
        this.applyCollection(this.itemList3, minecraft.thePlayer, true);
    }

    @Override
    public void onPreDisable() {
        this.enabled2 = true;
    }

    @Override
    public void onTick() {
        if (this.timer.hasReached(5000L) && CosmeticsNetworkHandler.isToggled() && minecraft.thePlayer != null) {
            boolean enabled = false;
            boolean completed = false;
            byte state = ((byte)(CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().canExecute() ? 1 : 0));
            int entityId = minecraft.thePlayer.getEntityId();
            if (state == entityId) {
                Stream stream = this.itemList3.stream();
                Predicate predicate = object -> ((CosmeticItem)object).canExecute();
                if (!stream.anyMatch(predicate)) {
                    enabled = false;
                    completed = true;
                }
            }
            if (!completed) {
                enabled = true;
            }


            if (enabled || this.enabled2) {
                Iterator iterator = this.itemList3.iterator();

                while (true) {
                    Object object2;
                    if (iterator.hasNext()) {
                        object2 = iterator.next();
                    } else {
                        this.enabled2 = false;
                        object2 = this;
                    }

                    ((CosmeticItem)object2).canExecute();
                }
            }
        }
    }

    public void updateState() {
        this.cosmeticsMap.forEach((object, object2) -> this.updateText((String)object, (ModuleDisableRule)object2));
        this.cosmeticsMap.clear();
    }

    private void applyCollection(Collection collection, AbstractClientPlayer abstractClientPlayer, boolean enabled) {
        if (abstractClientPlayer != null) {
            if (collection == null) {
                return;
            }

            for (CosmeticItem colorSettingArrayEntry : (Iterable<CosmeticItem>) collection) {
                CosmeticItemRegistry hashMapEntry = MoreCosmeticsManager.getMoreCosmeticsManager().getHashMapEntry();
                boolean enabled3;
                if (!enabled && colorSettingArrayEntry.getBooleanSetting().isEnabled()) {
                    enabled3 = true;
                } else {
                    enabled3 = false;
                }

                CosmeticsItemData customStringEntry = colorSettingArrayEntry.getCustomStringEntry();
                float colorSettingArrayEntry2 = (float)colorSettingArrayEntry.getDouble2();
                float colorSettingArrayEntry3 = (float)colorSettingArrayEntry.getDouble();
                ColorSetting[] colorSettingArray = colorSettingArrayEntry.getColorSettingArray();
                hashMapEntry.updateCustomStringEntry(enabled3, customStringEntry, abstractClientPlayer, colorSettingArrayEntry2, colorSettingArrayEntry3, colorSettingArray);
            }
        }
    }

    private void applyCollection2(Collection collection, AbstractClientPlayer abstractClientPlayer) {
        this.applyCollection(collection, abstractClientPlayer, false);
    }

    private void updateText(String text, ModuleDisableRule supplementalClientManager) {
        if (supplementalClientManager != null) {
            if (text == null) {
                return;
            }

            HashMap map = (HashMap)supplementalClientManager.getEntry();
            EntityPlayer player = this.getPlayer(text);
            if (player instanceof AbstractClientPlayer) {
                Collection collection = map.values();
                AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)player;
                this.applyCollection(collection, abstractClientPlayer, true);
            }
        }
    }

    private void applyHashMap(HashMap map) {
        MoreCosmeticsManager.getMoreCosmeticsManager()
        .getFailedToLoadMaskFromManager()
        .getEntryMap2()
        .forEach((object, object2) -> applyHashMap2(map, (String)object, (CosmeticsItemData)object2));
    }

    private static void updateAuxiliaryMapEntry(CosmeticsDataManager auxiliaryMapEntry, AtomicInteger atomicInteger, String text, CosmeticItem colorSettingArrayEntry) {
        Boolean booleanVal = (Boolean)auxiliaryMapEntry.getEntryMap2().get(text);
        if (booleanVal != null) {
            int index2 = 0;
            if (booleanVal) {
                index2 = atomicInteger.incrementAndGet();
            }

            BooleanSetting booleanSetting = colorSettingArrayEntry.getBooleanSetting();
            boolean enabled;
            if (booleanVal && index2 < auxiliaryMapEntry.getIndex2()) {
                enabled = true;
            } else {
                enabled = false;
            }

            booleanSetting.setState3(enabled);
            if (booleanVal) {
                Float floatVal = (Float)auxiliaryMapEntry.getEntryMap3().get(text);
                if (floatVal != null && !Float.isNaN(floatVal)) {
                    double offset = (double)floatVal.floatValue();
                    colorSettingArrayEntry.processDouble(offset);
                }

                Float floatVal2 = (Float)auxiliaryMapEntry.getEntryMap6().get(text);
                if (floatVal2 != null && !Float.isNaN(floatVal2)) {
                    double offset2 = (double)floatVal2.floatValue();
                    colorSettingArrayEntry.processAction(offset2);
                }

                int[] colorArray = (int[])auxiliaryMapEntry.getEntryMap7().get(text);
                boolean[] conditionArray = (boolean[])auxiliaryMapEntry.getEntryMap5().get(text);
                boolean[] conditionArray2 = (boolean[])auxiliaryMapEntry.getEntryMap8().get(text);
                double[] coordArray = (double[])auxiliaryMapEntry.getEntryMap().get(text);
                double[] coordArray2 = (double[])auxiliaryMapEntry.getEntryMap4().get(text);

                for (int i = 0; i < colorSettingArrayEntry.getColorSettingArray().length; i++) {
                    ColorSetting colorSetting = colorSettingArrayEntry.getColorSettingArray()[i];
                    Color color = new Color(colorArray[i], true);
                    colorSetting.applyColor(color);
                    double offset3 = coordArray[i];
                    colorSetting.updateDouble4(offset3);
                    double offset4 = coordArray2[i];
                    colorSetting.updateDouble6(offset4);
                    boolean enabled3 = conditionArray[i];
                    colorSetting.setState6(enabled3);
                    boolean enabled4 = conditionArray2[i];
                    colorSetting.setState3(enabled4);
                }
            }
        }
    }

    private void updateText(String text, CosmeticsItemData customStringEntry) {
        SettingGroup settingGroup = (SettingGroup)new SettingGroup(this, "Group" + text, true).getSetting(text);
        BooleanSetting booleanSetting = new BooleanSetting(this, text, false, settingGroup);
        NumberSetting numberSetting = new NumberSetting(this, text + "Scale", 1.0, 0.5, 2.0, 2, settingGroup);
        NumberSetting numberSetting2 = numberSetting.getNumberSetting(booleanSetting::isEnabled);
        NumberSetting numberSetting4 = new NumberSetting(this, text + "Height", 0.0, -2.0, 2.0, 2, settingGroup).getNumberSetting(booleanSetting::isEnabled);
        ModelData modelData = MoreCosmeticsManager.getMoreCosmeticsManager().getHashMapEntry().getModelData(customStringEntry, true);
        int length = modelData.model.length;
        ColorSetting[] colorSettingArray = new ColorSetting[length];
        int index = 0;
        if (0 >= length) {
            this.itemList3.add(new CosmeticItem(customStringEntry, booleanSetting, numberSetting2, numberSetting4, colorSettingArray, null));
        } else {
            ColorSetting colorSetting = new ColorSetting(this, text + "Color" + index, new Color(modelData.model[index].color), settingGroup);
            colorSettingArray[index] = (ColorSetting)colorSetting.getSetting(booleanSetting::isEnabled);
            index++;

            while (index < length) {
                ColorSetting colorSetting2 = new ColorSetting(this, text + "Color" + index, new Color(modelData.model[index].color), settingGroup);
                colorSettingArray[index] = (ColorSetting)colorSetting2.getSetting(booleanSetting::isEnabled);
                index++;
            }

            this.itemList3.add(new CosmeticItem(customStringEntry, booleanSetting, numberSetting2, numberSetting4, colorSettingArray, null));
        }
    }

    public boolean isExpanded2() {
        return this.expanded2;
    }

    private static void applyHashMap2(HashMap map, String text, CosmeticsItemData customStringEntry) {
        if (map.size() <= 40) {
            BooleanSetting booleanSetting = new BooleanSetting(null, text, false);
            NumberSetting numberSetting = new NumberSetting(null, text + "Scale", 1.0, 0.5, 2.0, 2);
            NumberSetting numberSetting2 = new NumberSetting(null, text + "Height", 0.0, -2.0, 2.0, 2);
            ModelData modelData = MoreCosmeticsManager.getMoreCosmeticsManager().getHashMapEntry().getModelData(customStringEntry, true);
            int length = modelData.model.length;
            ColorSetting[] colorSettingArray = new ColorSetting[length];
            int index = 0;
            if (0 >= length) {
                map.put(text, new CosmeticItem(customStringEntry, booleanSetting, numberSetting, numberSetting2, colorSettingArray, null));
            } else {
                colorSettingArray[index] = new ColorSetting(null, text + "Color" + index, new Color(modelData.model[index].color));
                index++;

                while (index < length) {
                    colorSettingArray[index] = new ColorSetting(null, text + "Color" + index, new Color(modelData.model[index].color));
                    index++;
                }

                map.put(text, new CosmeticItem(customStringEntry, booleanSetting, numberSetting, numberSetting2, colorSettingArray, null));
            }
        }
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public void updateAuxiliaryMapEntry(CosmeticsDataManager auxiliaryMapEntry) {
        String text = auxiliaryMapEntry.getText();
        ModuleDisableRule supplementalClientManager = (ModuleDisableRule)this.cosmeticsMap.get(text);
        HashMap map;
        if (supplementalClientManager != null) {
            map = (HashMap)supplementalClientManager.getEntry();
        } else {
            map = new HashMap();
        }

        if (supplementalClientManager == null) {
            this.applyHashMap(map);
        }

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(0);
        BiConsumer biConsumer = (object, object2) -> updateAuxiliaryMapEntry(auxiliaryMapEntry, (AtomicInteger)atomicInteger, (String)object, (CosmeticItem)object2);
        map.forEach(biConsumer);
        HashMap settingCosmetics2 = this.cosmeticsMap;
        Integer integer = auxiliaryMapEntry.getIndex();
        String text2 = auxiliaryMapEntry.getText2();
        ModuleDisableRule supplementalClientManager2 = new ModuleDisableRule(integer, text2, map);
        settingCosmetics2.put(text, supplementalClientManager2);
    }

    private EntityPlayer getPlayer(String text) {
        if (minecraft.theWorld == null) {
            return null;
        } else {
            return minecraft.theWorld.itemList7.isEmpty()
            ? null
            : ((EntityPlayer)(minecraft.theWorld.itemList7.stream().filter(object -> checkEntityPlayer(text, ((EntityPlayer)(object)))).findFirst().orElse(null)));
        }
    }

    public void applyAbstractClientPlayer(AbstractClientPlayer abstractClientPlayer) {
        if (abstractClientPlayer instanceof EntityPlayerSP) {
            this.applyAbstractClientPlayer2(abstractClientPlayer);
        } else if (abstractClientPlayer.getGameProfile() != null) {
            ModuleDisableRule supplementalClientManager = (ModuleDisableRule)this.cosmeticsMap.get(abstractClientPlayer.getGameProfile().getName());
            if (supplementalClientManager != null) {
                if (!AutoReportCache.checkEntityPlayer(abstractClientPlayer, (Integer)null, (String)supplementalClientManager.getEntry3())) {
                    return;
                }

                HashMap map = (HashMap)supplementalClientManager.getEntry();
                if (map != null) {
                    this.applyCollection2(map.values(), abstractClientPlayer);
                }
            }
        }
    }

    public void applyAbstractClientPlayer2(AbstractClientPlayer abstractClientPlayer) {
        if (this.isToggled()) {
            this.applyCollection2(this.itemList3, abstractClientPlayer);
        }
    }

    public void updateTertiaryStringEntry(CosmeticsItemSetting tertiaryStringEntry) {
        ModuleDisableRule supplementalClientManager = (ModuleDisableRule)this.cosmeticsMap.get(tertiaryStringEntry.getText());
        if (supplementalClientManager != null) {
            HashMap map = (HashMap)supplementalClientManager.getEntry();
            String text = tertiaryStringEntry.getText();
            EntityPlayer player = this.getPlayer(text);
            if (player instanceof AbstractClientPlayer) {
                Collection collection = map.values();
                AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)player;
                this.applyCollection(collection, abstractClientPlayer, true);
                this.cosmeticsMap.remove(tertiaryStringEntry.getText());
            }
        }
    }

    public static boolean canExecute5() {
        return activeState;
    }

    private static boolean checkEntityPlayer(String text, EntityPlayer player) {
        return player.getGameProfile() == null ? false : player.getGameProfile().getName().equals(text);
    }

    public static boolean canExecute6() {
        return !canExecute5();
    }

    public void setExpanded2(boolean enabled) {
        this.expanded2 = enabled;
    }
}
