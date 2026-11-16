package augustus.modules.world;

import client.module.Category;
import client.module.Module;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.util.CombatUtil;
import client.util.MovingObjectType;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;

public class SmartRaytrace extends Module {
    public static Setting[] settingsArray;

    public SmartRaytrace() {
        super("SmartRaytrace", "SchlauerRaytrace", Color.black, Category.WORLD);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    public static Setting[] getSettingArray() {
        return settingsArray;
    }

    public void processAction() {
        if (minecraft.thePlayer != null) {
            if (minecraft.entityRenderer != null) {
                if (!CombatUtil.checkItemStack2(minecraft.thePlayer.getHeldItem())) {
                    if (minecraft.objectMouseOver == null || minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
                        boolean enabled = false;
                        ArrayList arrayList = new ArrayList();
                        MovingObjectPosition hitResult = null;

                        for (float i = 0.05F; i < 1.0F; i += 0.05F) {
                            minecraft.entityRenderer.processFloat5(i, false);
                            if (CombatUtil.checkItemStack2(minecraft.thePlayer.getHeldItem())) {
                                if (hitResult == null) {
                                    hitResult = minecraft.objectMouseOver;
                                }

                                if (minecraft.objectMouseOver.sideHit != EnumFacing.UP) {
                                    enabled = true;
                                    arrayList.add(minecraft.objectMouseOver);
                                }
                            }
                        }

                        if (enabled) {
                            minecraft.objectMouseOver = (MovingObjectPosition)arrayList.get(
                                SettingContainer.settingProvider.getInt2(0, arrayList.size() - 1)
                            );
                        }

                        if (hitResult != null && !enabled) {
                            minecraft.objectMouseOver = hitResult;
                        }

                        if (!enabled && hitResult == null) {
                            minecraft.entityRenderer.processFloat5(1.0F, false);
                        }
                    }
                }
            }
        }
    }

    public static void setSettingArray(Setting[] settingArray2) {
        settingsArray = settingArray2;
    }
}
