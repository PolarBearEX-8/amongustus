package augustus.modules.legit;

import client.ClientGlobals;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class HitBox extends Module {
    public NumberSetting settingExpandXZ = new NumberSetting(this, "ExpandXZ", 0.0, 0.0, 1.0, 2);
    public NumberSetting settingExpandY = new NumberSetting(this, "ExpandY", 0.0, 0.0, 1.0, 2);
    public BooleanSetting settingServerHitBox = new BooleanSetting(this, "ServerHitBox", false);
    public BooleanSetting setting18Hitbox = new BooleanSetting(this, "1.8Hitbox", true);
    public static Setting[] settingArray;

    public HitBox() {
        super("HitBox", "SchlagBox", new Color(180, 207, 130), Category.LEGIT);
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

    public AxisAlignedBB getAxisAlignedBB(Entity entity, float value) {
        AxisAlignedBB boundingBox2 = entity.getBoundingBox2();
        float value2 = (float)((double)value + ClientGlobals.moduleManager.hitBox.settingExpandXZ.getValue());
        float value3 = (float)((double)value + ClientGlobals.moduleManager.hitBox.settingExpandY.getValue());
        if (!ClientGlobals.moduleManager.hitBox.settingServerHitBox.isEnabled()) {
            return boundingBox2.offset((double)value2, (double)value3, (double)value2);
        } else {
            if (entity instanceof EntityLivingBase) {
                Vec3 vec = new Vec3((double)entity.indexOffset2 / 32.0, (double)entity.indexOffset3 / 32.0, (double)entity.indexOffset / 32.0);
                float value4 = entity.width / 2.0F;
                boundingBox2 = boundingBox2.getAxisAlignedBB4(
                    new AxisAlignedBB(
                            vec.xCoord - (double)value4,
                            vec.yCoord,
                            vec.zCoord - (double)value4,
                            vec.xCoord + (double)value4,
                            vec.yCoord + (double)entity.height,
                            vec.zCoord + (double)value4
                        )
                        .offset((double)value, (double)value, (double)value)
                );
            }

            return boundingBox2.offset((double)value2, (double)value3, (double)value2);
        }
    }

    public static void setSettingArray(Setting[] settingArray2) {
        settingArray = settingArray2;
    }

    public static Setting[] getSettingArray() {
        return settingArray;
    }
}
