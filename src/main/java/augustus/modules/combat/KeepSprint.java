package augustus.modules.combat;

import augustus.custom.RuntimeBridge;
import client.event.EventChat;
import client.event.EventChatCallback;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.ChatUtil;
import java.awt.Color;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

public class KeepSprint extends Module implements EventChatCallback {
    public NumberSetting settingMoveSlowdownOnGround = new NumberSetting(this, "MoveSlowdownOnGround", 1.0, 0.0, 1.0, 2);
    public BooleanSetting settingSprintOnGround = new BooleanSetting(this, "SprintOnGround", true);
    public NumberSetting settingMoveSlowdownInAir = new NumberSetting(this, "MoveSlowdownInAir", 1.0, 0.0, 1.0, 2);
    public BooleanSetting settingSprintOnGroundInAir = new BooleanSetting(this, "SprintOnGroundInAir", true);
    public BooleanSetting settingSmart = new BooleanSetting(this, "Smart", true);

    public KeepSprint() {
        super("KeepSprint", "BeibehaltenSprinten", new Color(66, 132, 245), Category.COMBAT);
    }

    @Override
    public void onEnable() {
        this.registerEvent(2, 10);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onChat2(EventChat event) {
        if (this.settingSmart.isEnabled()) {
            if (event.canExecute()) {
                return;
            }

            MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
            if (objectMouseOver != null && objectMouseOver.entityHit != null) {
                EntityLivingBase livingEntity;
                if (!(objectMouseOver.entityHit instanceof EntityLivingBase)) {
                    livingEntity = null;
                } else {
                    livingEntity = (EntityLivingBase)objectMouseOver.entityHit;
                }

                EntityLivingBase entityLivingBase2 = ChatUtil.getEntityLivingBase2(livingEntity);
                if (entityLivingBase2 != null && !RuntimeBridge.raytraceEntity(entityLivingBase2, minecraft.thePlayer.rotationYaw)) {
                    return;
                }
            }
        }

        if (minecraft.thePlayer.onGround) {
            event.processAction(this.settingMoveSlowdownOnGround.getValue());
            event.setActive(this.settingSprintOnGround.isEnabled());
        } else {
            event.processAction(this.settingMoveSlowdownInAir.getValue());
            event.setActive(this.settingSprintOnGroundInAir.isEnabled());
        }
    }
}
