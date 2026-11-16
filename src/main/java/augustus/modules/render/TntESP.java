package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.EntityEspRenderer;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.EnchantmentManager;
import client.util.LvlManager;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

public class TntESP extends Module implements RenderListener, TickListener {
    public ArrayList trackedTntEntities = new ArrayList();
    public ColorSetting settingColor = new ColorSetting(this, "Color", new Color(255, 255, 255, 40));
    public BooleanSetting settingDeathCircle = new BooleanSetting(this, "DeathCircle", true);
    public ColorSetting settingDeathColor;
    public NumberSetting settingSmoothness;
    public BooleanSetting settingDisplayDamage;
    public NumberSetting settingXCoord;
    public NumberSetting settingYCoord;
    public double reachDistance;
    public EntityEspRenderer entityEspRenderer;
    public DamageSource explosionSource;

    public TntESP() {
        super("TntESP", "TntESP", new Color(109, 199, 117), Category.RENDER);
        ColorSetting colorSetting = new ColorSetting(this, "DeathColor", new Color(255, 0, 0, 40));
        this.settingDeathColor = (ColorSetting)colorSetting.getSetting(() -> this.settingDeathCircle.isEnabled());
        this.settingSmoothness = new NumberSetting(this, "Smoothness", 32.0, 8.0, 60.0, 0);
        this.settingDisplayDamage = new BooleanSetting(this, "DisplayDamage", true);
        this.settingXCoord = new NumberSetting(this, "X-Coord", 50.0, 0.0, 100.0, 2).getNumberSetting(() -> this.settingDisplayDamage.isEnabled());
        this.settingYCoord = new NumberSetting(this, "Y-Coord", 45.0, 0.0, 100.0, 2).getNumberSetting(() -> this.settingDisplayDamage.isEnabled());
        this.reachDistance = 0.0;
        this.entityEspRenderer = new EntityEspRenderer();
        this.explosionSource = new DamageSource("explosion");
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.settingDisplayDamage.isEnabled() && this.reachDistance > 0.0) {
            String text = "§cDamage: §7" + RuntimeBridge.formatNumberString(this.reachDistance, 3);
            int index = (int)((double)(scaledResolution.getScaledHeight() - minecraft.fontRendererObj.indexOffset) / 100.0 * this.settingYCoord.getValue());
            int index2 = (int)((double)(scaledResolution.getScaledWidth() - minecraft.fontRendererObj.getIndex(text)) / 100.0 * this.settingXCoord.getValue());
            minecraft.fontRendererObj.getIndex(text, index2, index, Color.white.getRGB());
        }

        RenderUtils.processAction();
    }

    @Override
    public void onRender3D(float partialTicks) {
        RenderUtils.updateState();
        int smoothness = (int)this.settingSmoothness.getValue();
        int int12 = this.settingColor.getInt12(0);
        byte state = (byte)(this.settingDeathCircle.isEnabled() ? this.settingDeathColor.getInt12(0) : 0);

        for (EntityPrimedtnt entityPrimedtnt : (Iterable<EntityPrimedtnt>) this.trackedTntEntities) {
            GL11.glPushMatrix();
            float value = entityPrimedtnt.height / 16.0F;
            float value2 = (float)(
            entityPrimedtnt.lastTickPosX + (entityPrimedtnt.posX - entityPrimedtnt.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosX()
            );
            float value3 = (float)(
            entityPrimedtnt.lastTickPosY
            + (double)value
            + (entityPrimedtnt.posY + (double)value - (entityPrimedtnt.lastTickPosY + (double)value)) * (double)minecraft.getTimer().scaleFactor6
            - minecraft.getRenderManager().getRenderPosY()
            );
            float value4 = (float)(
            entityPrimedtnt.lastTickPosZ
            + (entityPrimedtnt.posZ - entityPrimedtnt.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6
            - minecraft.getRenderManager().getRenderPosZ()
            );
            GL11.glTranslated((double)value2, (double)value3, (double)value4);
            Sphere sphere = new Sphere();
            sphere.setDrawStyle(100011);
            Sphere sphere2 = new Sphere();
            sphere2.setDrawStyle(100012);
            if (this.settingDeathCircle.isEnabled()) {
                RenderUtils.setColor(state);
                float doubleVal = (float)this.getDouble();
                sphere.draw(doubleVal, smoothness, smoothness);
                sphere2.draw(doubleVal, smoothness, smoothness);
            }

            RenderUtils.setColor(int12);
            sphere.draw(8.0F, smoothness, smoothness);
            sphere2.draw(8.0F, smoothness, smoothness);
            GL11.glPopMatrix();
        }

        RenderUtils.prepareScissor();
    }

    @Override
    public void onPostTick() {
        this.trackedTntEntities.clear();
        this.reachDistance = 0.0;
        if (minecraft.theWorld != null) {
            if (minecraft.thePlayer != null) {
                for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
                    if (entity instanceof EntityPrimedtnt) {
                        EntityPrimedtnt entityPrimedtnt = (EntityPrimedtnt)entity;
                        this.entityEspRenderer
                        .processDouble(minecraft.getRenderViewEntity().posX, minecraft.getRenderViewEntity().posY, minecraft.getRenderViewEntity().posZ);
                        if (this.entityEspRenderer.checkAxisAlignedBB(entity.getBoundingBox2().offset(9.0, 9.0, 9.0))
                        && !this.trackedTntEntities.contains(entity)) {
                            this.trackedTntEntities.add(entityPrimedtnt);
                            this.reachDistance = this.reachDistance + this.getCoordinate(entityPrimedtnt);
                        }
                    }
                }
            }
        }
    }

    public float getScaleFactor(DamageSource damageSource, float value, boolean enabled) {
        float value2 = value;
        if (damageSource.canExecute2()) {
            return value;
        } else {
            if (minecraft.thePlayer.checkPotion(Potion.potion12) && damageSource != DamageSource.damageSource10) {
                int index3 = (minecraft.thePlayer.getPotionEffect(Potion.potion12).getIndexOffset2() + 1) * 5;
                int index4 = 25 - index3;
                value2 = value * (float)index4 / 25.0F;
            }

            if (value2 <= 0.0F) {
                return 0.0F;
            } else {
                int index5 = 0;
                LvlManager.storageEspRenderer.damageSource = damageSource;

                for (int i = 0; i < minecraft.thePlayer.inventoryPlayer.armorInventory.length; i++) {
                    ItemStack itemStack = minecraft.thePlayer.inventoryPlayer.armorInventory[i];
                    if (itemStack != null && itemStack.getItem() instanceof ItemGoldenBoots) {
                        float value3 = 0.0F;
                        if (LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, itemStack) > 0) {
                            int index = LvlManager.storageEspRenderer
                            .getIndex(
                            EnchantmentManager.enchantmentManager6, LvlManager.getIndex(EnchantmentManager.enchantmentManager6.indexOffset, itemStack)
                            );
                            value3 = (float)(index + 1 >> 1) + (float)((index >> 1) + 1) / (enabled ? 1.0F : 2.0F);
                        }

                        index5 = (int)((float)index5 + value3);
                    }
                }

                if (index5 > 20) {
                    index5 = 20;
                }

                if (index5 <= 0) {
                    return value2;
                } else {
                    int index2 = 25 - index5;
                    return value2 * (float)index2 / 25.0F;
                }
            }
        }
    }

    public float getScaleFactor2(DamageSource damageSource, float value, boolean enabled) {
        float value2 = value;
        if (!damageSource.canExecute() && minecraft.thePlayer.isBlocking() && value > 0.0F) {
            value2 = (1.0F + value) * 0.5F;
        }

        value2 = this.getScaleFactor(damageSource, value2, minecraft.thePlayer);
        value2 = this.getScaleFactor(damageSource, value2, enabled);
        return Math.max(value2 - minecraft.thePlayer.getScaleFactor13(), 0.0F);
    }

    public float getFloat() {
        float value = 0.0F;

        for (int i = 0; i < minecraft.thePlayer.inventoryPlayer.armorInventory.length; i++) {
            ItemStack itemStack = minecraft.thePlayer.inventoryPlayer.armorInventory[i];
            if (itemStack != null && itemStack.getItem() instanceof ItemGoldenBoots) {
                float indexOffset4 = (float)((ItemGoldenBoots)itemStack.getItem()).indexOffset4;
                value += indexOffset4;
            }
        }

        return value;
    }

    private float getScaleFactor(DamageSource damageSource, float value, EntityPlayer player) {
        float value2 = value;
        if (!damageSource.canExecute()) {
            float value3 = 25.0F - this.getFloat();
            value2 = value * value3 / 25.0F;
        }

        return value2;
    }

    private double getCoordinate(EntityPrimedtnt entityPrimedtnt) {
        float value = 8.0F;
        double offset = entityPrimedtnt.posY + (double)(entityPrimedtnt.height / 16.0F);
        if (minecraft.thePlayer.canExecute4()) {
            return 0.0;
        } else {
            double offset2 = minecraft.thePlayer.getDouble4(entityPrimedtnt.posX, offset, entityPrimedtnt.posZ) / (double)value;
            if (!(offset2 <= 1.0)) {
                return 0.0;
            } else {
                double posX = minecraft.thePlayer.posX;
                double posX2 = entityPrimedtnt.posX;
                double offset3 = posX - posX2;
                double offset4 = minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2() - offset;
                double offset5 = minecraft.thePlayer.posZ - entityPrimedtnt.posZ;
                if ((double)MathHelper.sqrt_double(offset3 * offset3 + offset4 * offset4 + offset5 * offset5) != 0.0) {
                    double scaleFactor = (double)minecraft.theWorld.getScaleFactor(entityPrimedtnt.posX, offset, entityPrimedtnt.posZ, minecraft.thePlayer.getBoundingBox2());
                    double offset6 = (1.0 - offset2) * scaleFactor;
                    return (double)this.getScaleFactor2(
                    this.explosionSource.getDamageSource().getDamageSource4(),
                    (float)((int)((offset6 * offset6 + offset6) / 2.0 * 8.0 * (double)value + 1.0)),
                    true
                    );
                } else {
                    return 0.0;
                }
            }
        }
    }

    private double getDouble() {
        float value = 8.0F;
        if (!minecraft.thePlayer.canExecute4()) {
            float value2 = 0.0F;

            while (value2 < 8.0F) {
                double offset = (double)value2 / (double)value;
                if (offset <= 1.0) {
                    double offset2 = 1.0 - offset;
                    double offset3 = offset2 * 1.0;
                    if (minecraft.thePlayer.getFloat2()
                    - this.getScaleFactor2(
                    this.explosionSource.getDamageSource().getDamageSource4(),
                    (float)((int)((offset3 * offset3 + offset3) / 2.0 * 8.0 * (double)value + 1.0)),
                    true
                    )
                    > 0.0F) {
                        return (double)value2;
                    }
                }

                value2 += 0.1F;
            }
        }

        return 0.0;
    }
}
