package client.util;

import client.event.PreRenderObserver;
import client.setting.ColorSetting;
import client.util.CosmeticsItemData;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.UUID;
import net.labymod.ConfigAccessor;
import net.labymod.ModelConfig;
import net.labymod.ModelData;
import net.labymod.SettingOverlay;
import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticItemRegistry
implements PreRenderObserver {
    public HashMap hashMap = new HashMap();

    public void updateCustomStringEntry(boolean bl, CosmeticsItemData cosmeticsItemData, AbstractClientPlayer abstractClientPlayer, float f, float f2, ColorSetting[] colorSettingArray) {
        CosmeticItemRegistry.getIndexArray();
        ModelData modelData = (ModelData)abstractClientPlayer.getMap2().get(cosmeticsItemData.getInteger());
        if (modelData != null) {
            if (modelData.isActive()) {
                for (int i = 0; i < modelData.model.length; ++i) {
                    SettingOverlay settingOverlay = modelData.model[i];
                    settingOverlay.scale = f;
                    modelData.scale = f;
                    settingOverlay.y = f2;
                    modelData.y = f2;
                    settingOverlay.color = colorSettingArray[i].getInt12(0);
                }
            }
            if (modelData.isActive() != bl) {
                modelData.setActive(bl);
            }
        } else {
            ModelData modelData2 = this.getModelData(cosmeticsItemData, true);
            modelData2.setActive(bl);
            abstractClientPlayer.getMap2().put(cosmeticsItemData.getInteger(), cloneModelData(modelData2));
        }
    }

    private ModelData resolveModelData(CosmeticsItemData cosmeticsItemData, boolean bl, CosmeticsItemData cosmeticsItemData2) {
        return this.resolveModelData(cosmeticsItemData, bl);
    }

    private ModelData resolveModelData(CosmeticsItemData cosmeticsItemData, boolean bl) {
        int n;
        ModelData modelData;
        long l = 0x2c63813b1c6aL;
        long l2 = 0x5594F64572D5L ^ l ^ 0x30BB385AD333L;
        CosmeticItemRegistry.getIndexArray();
        if (bl && (modelData = this.resolveModelData(n = cosmeticsItemData.getInteger().intValue())) != null && cosmeticsItemData.getCount2() == modelData.getVersion()) {
            return modelData;
        }
        ModelData modelData2 = new ModelData();
        modelData2.setVersion(cosmeticsItemData.getCount2());
        int n2 = cosmeticsItemData.getEnhancedListEntryArray().length;
        modelData2.setSubModels(this.resolveSettingOverlayArray(n2));
        if (cosmeticsItemData.isActive3()) {
            int n3 = cosmeticsItemData.getArrayList().size();
            modelData2.setTextureModels(this.resolveSettingOverlayArray(n3));
        }
        if (cosmeticsItemData.isActive5()) {
            int n4 = cosmeticsItemData.getArrayListAsArrayList().size();
            modelData2.setItemModels(this.resolveSettingOverlayArray(n4));
        }
        if (cosmeticsItemData.isActiveAsFlag()) {
            for (ModelConfig modelConfig : cosmeticsItemData.getModelConfigArray()) {
                Object object = modelConfig.value;
                if (object == null) continue;
                if (modelConfig.mult != 1.0f && object instanceof Number) {
                    object = Float.valueOf(((Number)object).floatValue() / modelConfig.mult);
                }
                ConfigAccessor.set(modelConfig, modelData2, object);
            }
        }
        return modelData2;
    }

    private SettingOverlay[] resolveSettingOverlayArray(int n) {
        SettingOverlay[] settingOverlayArray = new SettingOverlay[n];
        CosmeticItemRegistry.getIndexArray();
        for (int i = 0; i < settingOverlayArray.length; ++i) {
            settingOverlayArray[i] = new SettingOverlay();
        }
        return settingOverlayArray;
    }

    private ModelData resolveModelData(int n) {
        return null;
    }

    private static ModelData cloneModelData(ModelData modelData) {
        try {
            return (ModelData)MethodHandles.publicLookup().findVirtual(ModelData.class, "clone", MethodType.methodType(ModelData.class)).invokeExact(modelData);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public ModelData getModelData(CosmeticsItemData cosmeticsItemData, boolean bl) {
        return (ModelData)this.hashMap.getOrDefault(cosmeticsItemData, this.hashMap.computeIfAbsent(cosmeticsItemData, lambdaArgA -> this.resolveModelData(cosmeticsItemData, bl, (CosmeticsItemData)lambdaArgA)));
    }

    public static int[] getIndexArray() {
        return null;
    }

    @Override
    public void applyIndex(int n) {
    }

    public CosmeticItemRegistry(UUID uUID) {
    }
}

