package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import client.util.EnchantmentManager;
import java.awt.Color;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

public class NameTags extends Module {
    public ModeSetting settingMode = new ModeSetting(this, "Mode", "Custom", new String[]{"None", "Vanilla", "Custom"});
    public NumberSetting settingScale;
    public NumberSetting settingHeight;
    public BooleanSetting settingArmor;
    public BooleanSetting settingArmorDurability;
    public BooleanSetting settingArmorEnchantments;
    public BooleanSetting settingPotionEffects;
    public ModeSetting settingHealthMode;
    public BooleanSetting settingPing;
    public BooleanSetting settingThroughWalls;
    public BooleanSetting settingMyself;
    public BooleanSetting settingInvisible;
    public BooleanSetting settingInvisibleTeam;
    public BooleanSetting settingTransparent;
    public BooleanSetting settingShadow;
    public BooleanSetting settingShadowFont;
    public NumberSetting settingMaximumRenderDistance;
    public String protectionAbbrev;
    public String sharpnessAbbrev;
    public String unbreakingAbbrev;

    public NameTags() {
        super("NameTags", "Namensschilder", new Color(21, 35, 81), Category.RENDER);
        this.settingScale = new NumberSetting(this, "Scale", 1.2, 0.0, 3.0, 2).getNumberSetting(() -> this.isScaleVisible());
        this.settingHeight = new NumberSetting(this, "Height", 0.0, -3.0, 3.0, 2).getNumberSetting(() -> this.isHeightVisible());
        BooleanSetting booleanSetting = new BooleanSetting(this, "Armor", false);
        this.settingArmor = (BooleanSetting)booleanSetting.getSetting(() -> this.isHeightVisible4());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "ArmorDurability", false);
        this.settingArmorDurability = (BooleanSetting)booleanSetting2.getSetting(() -> this.isHeightVisible3());
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "ArmorEnchantments", false);
        this.settingArmorEnchantments = (BooleanSetting)booleanSetting3.getSetting(() -> this.isHeightVisible2());
        this.settingPotionEffects = new BooleanSetting(this, "PotionEffects", false);
        this.settingHealthMode = new ModeSetting(this, "HealthMode", "None", new String[]{"None", "Entity", "TabList"}).getModeSetting(() -> this.isHealthModeVisible());
        this.settingPing = new BooleanSetting(this, "Ping", false);
        this.settingThroughWalls = new BooleanSetting(this, "ThroughWalls", true);
        this.settingMyself = new BooleanSetting(this, "Myself", true);
        this.settingInvisible = new BooleanSetting(this, "Invisible", true);
        this.settingInvisibleTeam = new BooleanSetting(this, "InvisibleTeam", true);
        this.settingTransparent = new BooleanSetting(this, "Transparent", false);
        this.settingShadow = new BooleanSetting(this, "Shadow", true);
        BooleanSetting booleanSetting4 = new BooleanSetting(this, "ShadowFont", false);
        this.settingShadowFont = (BooleanSetting)booleanSetting4.getSetting(() -> this.isHealthModeVisible2());
        this.settingMaximumRenderDistance = new NumberSetting(this, "MaximumRenderDistance", 500.0, 0.0, 500.0, 0);
        this.protectionAbbrev = "p";
        this.sharpnessAbbrev = "s";
        this.unbreakingAbbrev = "u";
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        if (!EnchantmentManager.enchantmentManager6.getString().isEmpty()) {
            this.protectionAbbrev = I18n.getString2(EnchantmentManager.enchantmentManager6.getString()).substring(0, 1).toLowerCase();
        }

        if (!EnchantmentManager.enchantmentManager2.getString().isEmpty()) {
            this.sharpnessAbbrev = I18n.getString2(EnchantmentManager.enchantmentManager2.getString()).substring(0, 1).toLowerCase();
        }

        if (!EnchantmentManager.enchantmentManager1.getString().isEmpty()) {
            this.unbreakingAbbrev = I18n.getString2(EnchantmentManager.enchantmentManager1.getString()).substring(0, 1).toLowerCase();
        }
    }

    private boolean isScaleVisible() {
        return !this.settingMode.getSelected().equals("None");
    }

    public float getScaleFactor(EntityPlayer player) {
        return CombatUtil.getScaleFactor(this.settingHealthMode, player);
    }

    private boolean isHeightVisible() {
        return !this.settingMode.getSelected().equals("None");
    }

    public int getIndex(AbstractClientPlayer abstractClientPlayer) {
        return abstractClientPlayer.getNetworkPlayerInfo() == null ? -1 : abstractClientPlayer.getNetworkPlayerInfo().getIndexOffset3();
    }

    private boolean isHeightVisible2() {
        return !this.settingArmor.isVisible() ? false : this.settingArmor.isEnabled();
    }

    private boolean isHealthModeVisible() {
        return !this.settingMode.getSelected().equals("None");
    }

    private boolean isHeightVisible3() {
        return !this.settingArmor.isVisible() ? false : this.settingArmor.isEnabled();
    }

    private boolean isHealthModeVisible2() {
        return !this.settingShadow.isEnabled() ? false : this.settingTransparent.isEnabled();
    }

    private boolean isHeightVisible4() {
        return !this.settingMode.getSelected().equals("None");
    }
}
