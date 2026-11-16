package augustus.modules.render;

import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import client.rotation.RotationHelper;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.DefaultVertexFormats;
import client.util.MovementHandler;
import client.util.ProjectileType;
import client.util.RandomUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntitySnowball;
import net.minecraft.entity.EntityThrownegg;
import net.minecraft.entity.EntityThrownenderpearl;
import net.minecraft.entity.EntityThrownexpbottle;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Projectiles extends Module implements RenderListener, TickListener {
    public ColorSetting settingColor;
    public BooleanSetting settingCalculateColor;
    public NumberSetting settingLineWidth;
    public ShaderOptions settingLineWidth2;
    public MultiSelectSetting settingItems;
    public ArrayList itemList3;

    public Projectiles() {
        super("Projectiles", "Projektile", new Color(80, 107, 91), Category.RENDER);
        Color color = new Color(21, 121, 230, 180);
        this.settingColor = new ColorSetting(this, "Color", color);
        this.settingCalculateColor = new BooleanSetting(this, "CalculateColor", true);
        this.settingLineWidth = new NumberSetting(this, "LineWidth", 4.0, 1.0, 12.0, 0);
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingLineWidth2 = shaderOptions.getShaderOptions(black).getShaderOptions2();
        ProjectileType[] projectileTypeArray = ProjectileType.values();
        this.settingItems = new MultiSelectSetting(this, "Items", projectileTypeArray);
        ArrayList arrayList = new ArrayList();
        this.itemList3 = arrayList;
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender3D(float partialTicks) {
        RenderUtils.updateState();
        GL11.glLineWidth((float)this.settingLineWidth.getValue() / 2.0F);
        this.updateState();
        GL11.glLineWidth(1.0F);
        RenderUtils.prepareScissor();
    }

    @Override
    public void onRunTick() {
        this.itemList3.clear();
        Iterator iterator = minecraft.theWorld.loadedEntityList.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity)iterator.next();
            if (entity.ticksExisted >= 0
            && !entity.onGround
            && (
            entity instanceof EntityArrow && ProjectileType.ARROW.isSelected()
            || entity instanceof EntitySnowball && ProjectileType.SNOW_BALL.isSelected()
            || entity instanceof EntityThrownegg && ProjectileType.EGG.isSelected()
            || entity instanceof EntityThrownenderpearl && ProjectileType.ENDER_PEARL.isSelected()
            || entity instanceof EntityThrownexpbottle && ProjectileType.XP_BOTTLE.isSelected()
            || entity instanceof EntityThrownpotion && ProjectileType.POTION.isSelected()
            || entity instanceof EntityFireball && ProjectileType.FIRE_BALL.isSelected()
            )) {
                RotationHelper rotationHelper = new RotationHelper();
                if ((byte)((entity instanceof EntityThrowable) ? 1 : 0) != 0) {
                    rotationHelper.updateAuxiliaryEntityAdapter((EntityThrowable)entity);
                    ArrayList arrayList = rotationHelper.getArrayList(300);
                    this.itemList3.add(arrayList);
                } else {
                    if ((byte)((entity instanceof EntityArrow) ? 1 : 0) == 0) {
                        boolean enabled = true;
                        int index = 0;
                        double posX = entity.posX;
                        double posY = entity.posY;
                        double posZ = entity.posZ;
                        double motionX = entity.motionX;
                        double motionY = entity.motionY;
                        double motionZ = entity.motionZ;
                        ArrayList arrayList2 = new ArrayList();
                        int index2 = Double.compare(posY, 0.0);

                        while (true) {
                            if (index2 < 0) {
                                do {
                                    enabled = false;
                                    index++;
                                    if (minecraft.theWorld.getMovingObjectPosition2(posX, posY, posZ, posX + motionX, posY + motionY, posZ + motionZ) != null) {
                                        enabled = false;
                                    }

                                    posX += motionX;
                                    posY += motionY;
                                    posZ += motionZ;
                                    float value = 0.95F;
                                    EntityFireball entityAdapter = (EntityFireball)entity;
                                    double offset = motionX + entityAdapter.lastTickPosZ2;
                                    double offset2 = motionY + entityAdapter.coordOffset;
                                    double offset3 = motionZ + entityAdapter.prevPosZ2;
                                    motionX = offset * (double)value;
                                    motionY = offset2 * (double)value;
                                    motionZ = offset3 * (double)value;
                                    arrayList2.add(new Vec3(posX, posY, posZ));

                                    outerLoop:
                                    while (!enabled) {
                                        this.itemList3.add(arrayList2);

                                        while (iterator.hasNext()) {
                                            entity = (Entity)iterator.next();
                                            if (entity.ticksExisted >= 0
                                            && !entity.onGround
                                            && (
                                            entity instanceof EntityArrow && ProjectileType.ARROW.isSelected()
                                            || entity instanceof EntitySnowball && ProjectileType.SNOW_BALL.isSelected()
                                            || entity instanceof EntityThrownegg && ProjectileType.EGG.isSelected()
                                            || entity instanceof EntityThrownenderpearl && ProjectileType.ENDER_PEARL.isSelected()
                                            || entity instanceof EntityThrownexpbottle && ProjectileType.XP_BOTTLE.isSelected()
                                            || entity instanceof EntityThrownpotion && ProjectileType.POTION.isSelected()
                                            || entity instanceof EntityFireball && ProjectileType.FIRE_BALL.isSelected()
                                            )) {
                                                RotationHelper rotationHelper2 = new RotationHelper();
                                                if (entity instanceof EntityThrowable) {
                                                    rotationHelper2.updateAuxiliaryEntityAdapter((EntityThrowable)entity);
                                                    ArrayList arrayList3 = rotationHelper2.getArrayList(300);
                                                    this.itemList3.add(arrayList3);
                                                } else {
                                                    if (!(entity instanceof EntityArrow)) {
                                                        enabled = true;
                                                        index = 0;
                                                        posX = entity.posX;
                                                        posY = entity.posY;
                                                        posZ = entity.posZ;
                                                        motionX = entity.motionX;
                                                        motionY = entity.motionY;
                                                        motionZ = entity.motionZ;
                                                        arrayList2 = new ArrayList();
                                                        continue outerLoop;
                                                    }

                                                    rotationHelper2.updateEntityArrow((EntityArrow)entity);
                                                    ArrayList arrayList4 = rotationHelper2.getArrayList4(300);
                                                    this.itemList3.add(arrayList4);
                                                }
                                            }
                                        }

                                        return;
                                    }
                                } while (index > 300);
                            } else {
                                while (true) {
                                    index++;
                                    if (minecraft.theWorld.getMovingObjectPosition2(posX, posY, posZ, posX + motionX, posY + motionY, posZ + motionZ) != null) {
                                        enabled = false;
                                    }

                                    posX += motionX;
                                    posY += motionY;
                                    posZ += motionZ;
                                    float value2 = 0.95F;
                                    EntityFireball entityAdapter2 = (EntityFireball)entity;
                                    double offset4 = motionX + entityAdapter2.lastTickPosZ2;
                                    double offset5 = motionY + entityAdapter2.coordOffset;
                                    double offset6 = motionZ + entityAdapter2.prevPosZ2;
                                    motionX = offset4 * (double)value2;
                                    motionY = offset5 * (double)value2;
                                    motionZ = offset6 * (double)value2;
                                    arrayList2.add(new Vec3(posX, posY, posZ));

                                    outerLoop:
                                    while (!enabled) {
                                        this.itemList3.add(arrayList2);

                                        while (iterator.hasNext()) {
                                            entity = (Entity)iterator.next();
                                            if (entity.ticksExisted >= 0
                                            && !entity.onGround
                                            && (
                                            entity instanceof EntityArrow && ProjectileType.ARROW.isSelected()
                                            || entity instanceof EntitySnowball && ProjectileType.SNOW_BALL.isSelected()
                                            || entity instanceof EntityThrownegg && ProjectileType.EGG.isSelected()
                                            || entity instanceof EntityThrownenderpearl && ProjectileType.ENDER_PEARL.isSelected()
                                            || entity instanceof EntityThrownexpbottle && ProjectileType.XP_BOTTLE.isSelected()
                                            || entity instanceof EntityThrownpotion && ProjectileType.POTION.isSelected()
                                            || entity instanceof EntityFireball && ProjectileType.FIRE_BALL.isSelected()
                                            )) {
                                                RotationHelper rotationHelper3 = new RotationHelper();
                                                if (entity instanceof EntityThrowable) {
                                                    rotationHelper3.updateAuxiliaryEntityAdapter((EntityThrowable)entity);
                                                    ArrayList arrayList5 = rotationHelper3.getArrayList(300);
                                                    this.itemList3.add(arrayList5);
                                                } else {
                                                    if (!(entity instanceof EntityArrow)) {
                                                        enabled = true;
                                                        index = 0;
                                                        posX = entity.posX;
                                                        posY = entity.posY;
                                                        posZ = entity.posZ;
                                                        motionX = entity.motionX;
                                                        motionY = entity.motionY;
                                                        motionZ = entity.motionZ;
                                                        arrayList2 = new ArrayList();
                                                        continue outerLoop;
                                                    }

                                                    rotationHelper3.updateEntityArrow((EntityArrow)entity);
                                                    ArrayList arrayList42 = rotationHelper3.getArrayList4(300);
                                                    this.itemList3.add(arrayList42);
                                                }
                                            }
                                        }

                                        return;
                                    }

                                    if (index <= 300) {
                                        break;
                                    }

                                    enabled = false;
                                }
                            }

                            index2 = Double.compare(posY, 0.0);
                        }
                    }

                    rotationHelper.updateEntityArrow((EntityArrow)entity);
                    ArrayList arrayList43 = rotationHelper.getArrayList4(300);
                    this.itemList3.add(arrayList43);
                }
            }
        }
    }

    public void updateState() {
        for (int i = 0; i < this.itemList3.size(); i++) {
            ArrayList arrayList = (ArrayList)this.itemList3.get(i);
            if (arrayList.size() > 1) {
                int int12 = this.settingColor.getInt12(0);
                if (this.settingCalculateColor.isEnabled()) {
                    Vec3 vec = (Vec3)arrayList.get(1);
                    double offset = Math.min(Math.max(minecraft.thePlayer.getDouble4(vec.xCoord, vec.yCoord, vec.zCoord), 6.0), 36.0) - 6.0;
                    float[] floatArray = RandomUtil.getFloatArray(int12);
                    int12 = Color.HSBtoRGB((float)(0.011415525F * offset), floatArray[1], floatArray[2]);
                }

                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                MovementHandler.processBoolean(
                this.settingLineWidth2.canExecute(), this.settingLineWidth2.canExecute2()
                );
                int index = int12;
                MovementHandler.applyRunnableTask(false, renderPass -> this.applyArrayList(index, arrayList, renderPass));
            }
        }
    }

    private void applyArrayList(int index2, ArrayList arrayList, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, index2);
        RenderUtils.setColor(this.settingLineWidth2.getIndex(renderPass, index));
        Tessellator tessellator = Tessellator.getTessellator();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);

        for (Vec3 vec : (Iterable<Vec3>) arrayList) {
            double x = (double)((float)vec.xCoord);
            double offset = x - minecraft.getRenderManager().getRenderPosX();
            double y = (double)((float)vec.yCoord);
            double offset2 = y - minecraft.getRenderManager().getRenderPosY();
            double z = (double)((float)vec.zCoord);
            worldRenderer.getWorldRenderer(offset, offset2, z - minecraft.getRenderManager().getRenderPosZ()).updateState();
        }

        tessellator.updateState();
    }
}
