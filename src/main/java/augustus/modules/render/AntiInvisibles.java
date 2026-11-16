package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.AntiInvisiblesTargetEntity;
import client.setting.MultiSelectSetting;
import client.util.ChatUtil;
import java.awt.Color;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;

public class AntiInvisibles extends Module {
    public MultiSelectSetting settingEntities = new MultiSelectSetting(this, "Entities", AntiInvisiblesTargetEntity.values());

    public AntiInvisibles() {
        super("AntiInvisibles", "GegenUnsichtbare", new Color(87, 247, 196), Category.RENDER);
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

    public boolean checkEntity(Entity entity) {
        return entity instanceof EntityPlayerSP
            ? false
            : ChatUtil.checkEntity5(
                entity,
                AntiInvisiblesTargetEntity.PLAYER.isSelected(),
                AntiInvisiblesTargetEntity.MOB.isSelected(),
                
                AntiInvisiblesTargetEntity.ANIMAL.isSelected(),
                AntiInvisiblesTargetEntity.VILLAGER.isSelected(),
                AntiInvisiblesTargetEntity.ARMOR_STAND.isSelected(),
                true,
                false,
                false,
                true
            );
    }
}
