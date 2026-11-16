package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import client.event.EventWorldLoad;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.Custom2DItemsBronzeType;
import client.setting.MultiSelectSetting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Vec3;

public class Items2D extends Module implements RenderListener, TickListener, WorldLoadListener {
    public MultiSelectSetting settingItems = new MultiSelectSetting(this, "Items", Custom2DItemsBronzeType.values());
    public BooleanSetting settingRemove3DItems = new BooleanSetting(this, "Remove3DItems", true);
    public BooleanSetting settingShowStackSize = new BooleanSetting(this, "ShowStackSize", false);
    public BooleanSetting settingOnlyAtOptifineZoom = new BooleanSetting(this, "OnlyAtOptifineZoom", false);
    public ArrayList itemList3 = new ArrayList();
    public HashMap entryMap = new HashMap();

    public Items2D() {
        super("2DItems", "2DItems", new Color(66, 135, 245), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(40);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.entryMap.clear();
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        for (EntityItem entityItem : (Iterable<EntityItem>) this.itemList3) {
            Vec3 vec = (Vec3)this.entryMap.get(entityItem);
            if (vec.zCoord >= 0.0 && vec.zCoord <= 1.0) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableLighting();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                RenderHelper.enableGUIStandardItemLighting();
                minecraft.getRenderItem().renderItemAndEffectIntoGUI(entityItem.getItemStack(), (int)vec.xCoord, (int)vec.yCoord);
                if (this.settingShowStackSize.isEnabled()) {
                    minecraft.getRenderItem().renderItemOverlays(minecraft.fontRendererObj, entityItem.getItemStack(), (int)vec.xCoord, (int)vec.yCoord);
                }

                RenderHelper.disableStandardItemLighting();
                GlStateManager.disableBlend();
                GlStateManager.applyState3();
                GlStateManager.popMatrix();
            }
        }

        RenderUtils.processAction();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.itemList3.clear();
        this.entryMap.clear();
    }

    @Override
    public void onRender3D(float partialTicks) {
        this.entryMap.clear();
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);

        for (EntityItem entityItem : (Iterable<EntityItem>) this.itemList3) {
            Vec3 vec = new Vec3(
            entityItem.lastTickPosX + (entityItem.posX - entityItem.lastTickPosX) * (double)partialTicks - minecraft.getRenderManager().getRenderPosX(),
            entityItem.lastTickPosY + (entityItem.posY - entityItem.lastTickPosY) * (double)partialTicks + 0.4 - minecraft.getRenderManager().getRenderPosY(),
            entityItem.lastTickPosZ + (entityItem.posZ - entityItem.lastTickPosZ) * (double)partialTicks - minecraft.getRenderManager().getRenderPosZ()
            );
            this.entryMap.put(entityItem, RuntimeBridge.projectPoint(vec.xCoord, vec.yCoord, vec.zCoord, scaledResolution));
        }
    }

    @Override
    public void onRunTick() {
        this.itemList3.clear();

        for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
            if (entity instanceof EntityItem
            && ((EntityItem)entity).getItemStack() != null
            && this.checkItemStack(((EntityItem)entity).getItemStack())) {
                this.itemList3.add((EntityItem)entity);
            }
        }
    }

    public boolean checkItemStack(ItemStack itemStack) {
        if (this.settingOnlyAtOptifineZoom.isEnabled() && !minecraft.gameSettings.ofKeyBindZoom.isKeyDown()) {
            return false;
        } else if (Custom2DItemsBronzeType.BRONZE.isSelected() && itemStack.getItem().equals(Items.brick)) {
            return true;
        } else if (Custom2DItemsBronzeType.DIAMOND.isSelected() && itemStack.getItem().equals(Items.diamond)) {
            return true;
        } else if (Custom2DItemsBronzeType.EMERALD.isSelected() && itemStack.getItem().equals(Items.emerald)) {
            return true;
        } else if (Custom2DItemsBronzeType.GOLD.isSelected() && itemStack.getItem().equals(Items.goldIngot)) {
            return true;
        } else if (Custom2DItemsBronzeType.IRON.isSelected() && itemStack.getItem().equals(Items.ironIngot)) {
            return true;
        } else if (!Custom2DItemsBronzeType.ALL.isSelected()) {
            return false;
        } else if (itemStack.getItem().equals(Items.brick)) {
            return false;
        } else if (itemStack.getItem().equals(Items.diamond)) {
            return false;
        } else if (itemStack.getItem().equals(Items.emerald)) {
            return false;
        } else {
            return itemStack.getItem().equals(Items.goldIngot) ? false : !itemStack.getItem().equals(Items.ironIngot);
        }
    }
}
