package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ChamsTargetEntity;
import client.setting.ColorSetting;
import client.setting.MultiSelectSetting;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import java.awt.Color;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class Chams extends Module {
    public MultiSelectSetting settingEntities = new MultiSelectSetting(this, "Entities", ChamsTargetEntity.values());
    public BooleanSetting settingColoredEntityChams = new BooleanSetting(this, "ColoredEntityChams", true);
    public BooleanSetting settingOnlyThroughWallsEntities;
    public BooleanSetting settingRenderTextureEntity;
    public ColorSetting settingColorEntity;
    public BooleanSetting settingChests;
    public BooleanSetting settingColoredChestChams;
    public BooleanSetting settingOnlyThroughWalls;
    public BooleanSetting settingRenderTexture;
    public ColorSetting settingColorChest;
    public ColorSetting settingColorEnderChest;
    public static boolean activeState;

    public Chams() {
        super("Chams", "Röntgenblick", new Color(91, 152, 222), Category.RENDER);
        BooleanSetting booleanSetting = new BooleanSetting(this, "OnlyThroughWallsEntities", false);
        this.settingOnlyThroughWallsEntities = (BooleanSetting)booleanSetting.getSetting(() -> this.settingColoredEntityChams.isEnabled());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "RenderTextureEntity", false);
        this.settingRenderTextureEntity = (BooleanSetting)booleanSetting2.getSetting(() -> this.isEntitiesVisible());
        ColorSetting colorSetting = new ColorSetting(this, "ColorEntity", new Color(0, 233, 255, 100));
        this.settingColorEntity = (ColorSetting)colorSetting.getSetting(() -> this.isColorEntityVisible());
        this.settingChests = new BooleanSetting(this, "Chests", false);
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "ColoredChestChams", true);
        this.settingColoredChestChams = (BooleanSetting)booleanSetting3.getSetting(() -> this.settingChests.isEnabled());
        BooleanSetting booleanSetting4 = new BooleanSetting(this, "OnlyThroughWalls", false);
        this.settingOnlyThroughWalls = (BooleanSetting)booleanSetting4.getSetting(() -> this.isColorEntityVisible2());
        BooleanSetting booleanSetting5 = new BooleanSetting(this, "RenderTexture", false);
        this.settingRenderTexture = (BooleanSetting)booleanSetting5.getSetting(() -> this.isColorEntityVisible3());
        ColorSetting colorSetting2 = new ColorSetting(this, "ColorChest", new Color(0, 233, 255, 100));
        this.settingColorChest = (ColorSetting)colorSetting2.getSetting(() -> this.isColorChestVisible());
        ColorSetting colorSetting3 = new ColorSetting(this, "ColorEnderChest", new Color(0, 233, 255, 100));
        this.settingColorEnderChest = (ColorSetting)colorSetting3.getSetting(() -> this.isColorEnderChestVisible());
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

    public void applyEntity(Entity entity) {
        if ((char)(this.settingColoredEntityChams.isEnabled() ? 1 : 0) == 0) {
            if ((char)(this.checkEntity(entity) ? 1 : 0) != 0) {
                GL11.glEnable(32823);
                GL11.glPolygonOffset(1.0F, -1100000.0F);
            }
        }
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public void updateState() {
        if ((char)(this.settingColoredEntityChams.isEnabled() ? 1 : 0) == 0) {
            if ((char)(GL11.glIsEnabled(32823) ? 1 : 0) != 0) {
                GL11.glDisable(32823);
                GL11.glPolygonOffset(1.0F, 1100000.0F);
            }
        }
    }

    public static boolean canExecute5() {
        return activeState;
    }

    private boolean isColorEntityVisible() {
        return this.settingColoredEntityChams.isEnabled();
    }

    public boolean checkEntity(Entity entity) {
        return entity instanceof EntityPlayerSP
            ? false
            : ChatUtil.checkEntity5(
                entity,
                ChamsTargetEntity.PLAYERS.isSelected(),
                ChamsTargetEntity.MOBS.isSelected(),
                
                ChamsTargetEntity.ANIMALS.isSelected(),
                ChamsTargetEntity.VILLAGER.isSelected(),
                ChamsTargetEntity.ARMOR_STANDS.isSelected(),
                ChamsTargetEntity.INVISIBLE.isSelected(),
                false,
                false,
                true
            );
    }

    public static boolean canExecute6() {
        return !canExecute5();
    }

    private boolean isColorChestVisible() {
        return !this.settingColoredChestChams.isVisible() ? false : this.settingColoredChestChams.isEnabled();
    }

    private boolean isColorEntityVisible2() {
        return !this.settingColoredChestChams.isVisible() ? false : this.settingColoredChestChams.isEnabled();
    }

    private boolean isColorEntityVisible3() {
        if (!this.settingColoredChestChams.isVisible()) {
            return false;
        } else {
            return !this.settingColoredChestChams.isEnabled() ? false : !this.settingOnlyThroughWalls.isEnabled();
        }
    }

    private boolean isColorEnderChestVisible() {
        return !this.settingColoredChestChams.isVisible() ? false : this.settingColoredChestChams.isEnabled();
    }

    private boolean isEntitiesVisible() {
        return !this.settingColoredEntityChams.isEnabled() ? false : !this.settingOnlyThroughWallsEntities.isEnabled();
    }
}
