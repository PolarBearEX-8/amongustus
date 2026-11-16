package augustus.modules.movement;

import client.event.EventMove;
import client.event.MoveListener;
import client.module.Category;
import client.module.Module;
import client.setting.AutoJumpSlimeBlocksType;
import client.setting.BooleanSetting;
import client.setting.MultiSelectSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import java.awt.Color;
import net.minecraft.block.Block;

public class AutoJump extends Module implements MoveListener {
    public BooleanSetting settingInInv = new BooleanSetting(this, "InInv", false);
    public MultiSelectSetting settingBlocksToAutoJump = new MultiSelectSetting(this, "BlocksToAutoJump", AutoJumpSlimeBlocksType.values());

    public AutoJump() {
        super("AutoJump", "AutomatischerSprung", new Color(35, 52, 45), Category.MOVEMENT);
        AutoJumpSlimeBlocksType[] autoJumpSlimeBlocksTypeArray = AutoJumpSlimeBlocksType.values();
        int length = autoJumpSlimeBlocksTypeArray.length;
        if (0 < length) {
            AutoJumpSlimeBlocksType autoJumpSlimeBlocksTypeKind = autoJumpSlimeBlocksTypeArray[0];
            SettingGroup settingGroup2 = new SettingGroup(this, autoJumpSlimeBlocksTypeKind.getText(), true).getSettingGroup(autoJumpSlimeBlocksTypeKind::isSelected);
            String text = autoJumpSlimeBlocksTypeKind.getText() + "OnlyWhileMove";
            boolean enabled = autoJumpSlimeBlocksTypeKind == AutoJumpSlimeBlocksType.STAIRS;
            BooleanSetting booleanSetting = new BooleanSetting(this, text, enabled, settingGroup2);
            BooleanSetting setting = (BooleanSetting)booleanSetting.getSetting("OnlyWhileMove");
            AutoJumpSlimeBlocksType.getBooleanSetting(autoJumpSlimeBlocksTypeKind, setting);
            String text2 = autoJumpSlimeBlocksTypeKind.getText() + "OnlyAtSprint";
            boolean enabled2 = autoJumpSlimeBlocksTypeKind == AutoJumpSlimeBlocksType.STAIRS;
            BooleanSetting booleanSetting2 = new BooleanSetting(this, text2, enabled2, settingGroup2);
            BooleanSetting setting2 = (BooleanSetting)booleanSetting2.getSetting("OnlyAtSprint");
            AutoJumpSlimeBlocksType.getBooleanSetting2(autoJumpSlimeBlocksTypeKind, setting2);
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onMove(EventMove event) {
        if (minecraft.currentScreen == null || this.settingInInv.isEnabled()) {
            if (minecraft.thePlayer.onGround) {
                Block block = minecraft.theWorld.getIBlockState2(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 0.5, minecraft.thePlayer.posZ).getBlock();

                for (AutoJumpSlimeBlocksType autoJumpSlimeBlocksTypeKind : AutoJumpSlimeBlocksType.values()) {
                    if (autoJumpSlimeBlocksTypeKind.isSelected() && autoJumpSlimeBlocksTypeKind.checkBlock(block)) {
                        event.setState(true);
                        break;
                    }
                }
            }
        }
    }
}
