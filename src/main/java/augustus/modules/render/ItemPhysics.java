package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ItemPhysicsNoneType;
import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.EntityItem;
import net.minecraft.item.ItemStack;

public class ItemPhysics extends Module {
    public BooleanSetting settingRotate;

    public ItemPhysics() {
        super("ItemPhysics", "ArtikelPhysiken", new Color(65, 224, 145), Category.RENDER);
        this.settingRotate = new BooleanSetting(this, "Rotate", true);
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

    private int getIndex(ItemStack itemStack) {
        byte state = 1;
        if (itemStack.animationsToGo > 48) {
            return 5;
        } else if (itemStack.animationsToGo > 32) {
            return 4;
        } else if (itemStack.animationsToGo > 16) {
            return 3;
        } else {
            if (itemStack.animationsToGo > 1) {
                state = 2;
            }

            return state;
        }
    }

    public int getIndex(EntityItem entityItem, double x, double y3, double z, float value, IBakedModel internalListener) {
        ItemStack itemStack = entityItem.getItemStack();
        if (itemStack.getItem() == null) {
            return 0;
        } else if (internalListener.canExecute()) {
            int index = this.getIndex(itemStack);
            float value2 = -0.125F;
            float y = internalListener.getSpecialItemEntry().getAuxiliaryItemEntry(ItemPhysicsNoneType.GROUND).vector3f3.y;
            float value3 = (float)y3 + value2 + 0.25F * y;
            GlStateManager.translate((float)x, value3, (float)z);
            float value4 = ((float)entityItem.getIndex() + value) / 20.0F;
            if (!entityItem.onGround && this.settingRotate.isEnabled()) {
                GlStateManager.rotate((float)entityItem.getIndex() * 10.0F % 360.0F, 1.0F, 0.0F, 1.0F);
            }

            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            return index;
        } else {
            int index2 = this.getIndex(itemStack);
            float value5 = -0.125F;
            float value6 = -0.175F;
            float y2 = internalListener.getSpecialItemEntry().getAuxiliaryItemEntry(ItemPhysicsNoneType.GROUND).vector3f3.y;
            float value7 = (float)y3 + value6 + 0.25F * y2;
            GlStateManager.translate((float)x, value7, (float)z);
            if (minecraft.getRenderManager().gameSettings != null) {
                float value8 = ((float)entityItem.getIndex() + value) / 20.0F;
            }

            if (entityItem.onGround) {
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 1.0F);
            }

            if (!entityItem.onGround && this.settingRotate.isEnabled()) {
                GlStateManager.rotate((float)entityItem.getIndex() * 10.0F % 360.0F, 1.0F, 0.0F, 1.0F);
            }

            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            return index2;
        }
    }
}
