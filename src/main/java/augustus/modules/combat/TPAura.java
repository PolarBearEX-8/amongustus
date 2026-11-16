package augustus.modules.combat;

import client.ClientGlobals;
import client.event.EventMove;
import client.event.MoveListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.PathRenderer;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.BooleanSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.setting.TPAuraTargetEntity;
import client.util.ChatUtil;
import client.util.MovingObjectType;
import client.util.PathNode;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.block.BlockPlaceData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class TPAura extends Module implements TickListener, RenderListener, MoveListener {
    public NumberSetting settingMaxDistance;
    public NumberSetting settingMaxTeleportDistance;
    public NumberSetting settingAttacksPerSecond;
    public BooleanSetting settingNoswing;
    public BooleanSetting settingFly;
    public SettingGroup settingTarget;
    public MultiSelectSetting settingTargets;
    public NumberSetting settingMaxTargets;
    public NumberSetting settingMinhurttime;
    public BooleanSetting settingDebuglines;
    public BooleanSetting settingLegit;
    public TimeHelper timer;
    public ArrayList itemList4;
    public ArrayList itemList3;
    public ArrayList teleportPath;
    public Rotation rotation;
    public boolean expanded2;
    public boolean enabled2;

    public TPAura() {
        super("TPAura", "Ferntötung mittels Teleportation", Color.RED, Category.COMBAT);
        this.settingMaxDistance = new NumberSetting(this, "MaxDistance", 50.0, 3.0, 150.0, 0);
        this.settingMaxTeleportDistance = new NumberSetting(this, "MaxTeleportDistance", 5.0, 2.0, 9.0, 0);
        this.settingAttacksPerSecond = new NumberSetting(this, "AttacksPerSecond", 2.0, 1.0, 20.0, 0);
        this.settingNoswing = new BooleanSetting(this, "Noswing", false);
        this.settingFly = new BooleanSetting(this, "Fly", true);
        this.settingTarget = new SettingGroup(this, "Target");
        TPAuraTargetEntity[] tPAuraTargetEntityArray = TPAuraTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", tPAuraTargetEntityArray, this.settingTarget);
        this.settingMaxTargets = new NumberSetting(this, "MaxTargets", 1.0, 1.0, 10.0, 0, this.settingTarget);
        this.settingMinhurttime = new NumberSetting(this, "Minhurttime", 3.0, 0.0, 10.0, 0, this.settingTarget);
        this.settingDebuglines = new BooleanSetting(this, "Debuglines", false);
        this.settingLegit = new BooleanSetting(this, "Legit", false);
        this.timer = new TimeHelper();
        ArrayList arrayList = new ArrayList();
        this.teleportPath = arrayList;
        this.rotation = new Rotation();
        this.expanded2 = false;
        this.enabled2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(32);
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
        if (this.expanded2 || this.enabled2) {
            event.setScaleFactor7(this.enabled2 ? 0.0F : 1.0F);
            event.setState4(false);
            event.setState(minecraft.thePlayer.isCollidedHorizontally);
            event.setScaleFactor2(0.0F);
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslated(
        -minecraft.getRenderManager().getRenderPosX(), -minecraft.getRenderManager().getRenderPosY(), -minecraft.getRenderManager().getRenderPosZ()
        );
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GlStateManager.disableAlpha();
        GL11.glDepthMask(false);
        if (this.itemList4 != null) {
            if (this.settingDebuglines.isEnabled()) {
                GlStateManager.color4f2(1.0F, 0.0F, 0.0F, 0.3F);
            } else {
                GlStateManager.color4f2(1.0F, 0.0F, 0.0F, 0.15F);
            }

            GL11.glBegin(3);
            Iterator iterator = this.itemList4.iterator();

            while (iterator.hasNext()) {
                Vec3 vec2 = ((PathNode)iterator.next()).getBlockPlaceData().getVec3();
                GL11.glVertex3d(vec2.xCoord + 0.5, vec2.yCoord + 0.01, vec2.zCoord + 0.5);
            }

            GL11.glEnd();
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 0.9F);
            GL11.glBegin(3);

            for (Vec3 vec : (Iterable<Vec3>) this.itemList3) {
                GL11.glVertex3d(vec.xCoord, vec.yCoord + 0.01, vec.zCoord);
            }

            GL11.glEnd();
            if (this.settingDebuglines.isEnabled()) {
                GlStateManager.color4f2(0.0F, 0.0F, 1.0F, 0.4F);
                Iterator iterator2 = this.teleportPath.iterator();

                while (iterator2.hasNext()) {
                    for (Vec3[] vecArray : (Vec3[][])iterator2.next()) {
                        GL11.glBegin(3);
                        GL11.glVertex3d(vecArray[0].xCoord, vecArray[0].yCoord, vecArray[0].zCoord);
                        GL11.glVertex3d(vecArray[1].xCoord, vecArray[1].yCoord, vecArray[1].zCoord);
                        GL11.glEnd();
                    }
                }
            }
        }

        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GlStateManager.enableLighting();
        GL11.glEnable(3008);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2848);
    }

    @Override
    public void onPreTick() {
        this.rotation = ClientGlobals.rotationManager.getRotation3();
        TimeHelper timer2 = this.timer;
        double offset = 1000.0 / this.settingAttacksPerSecond.getValue();
        if (timer2.hasReachedDouble(offset)) {
            this.timer.reset();
            this.expanded2 = true;
            double value = this.settingMaxDistance.getValue();
            double offset2 = value * this.settingMaxDistance.getValue();
            ArrayList arrayList = new ArrayList();

            for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
                if (entity != minecraft.thePlayer && entity instanceof EntityLivingBase) {
                    EntityLivingBase livingEntity = (EntityLivingBase)entity;
                    if (!(entity.getCoordinate(minecraft.thePlayer) > offset2)) {
                        double hurtTime = (double)livingEntity.hurtTime;
                        if (!(hurtTime > this.settingMinhurttime.getValue()) && !(entity instanceof EntityPlayerSP)) {
                            boolean selected = TPAuraTargetEntity.PLAYER.isSelected();
                            boolean selected2 = TPAuraTargetEntity.MOB.isSelected();
                            boolean selected3 = TPAuraTargetEntity.ANIMAL.isSelected();
                            boolean selected4 = TPAuraTargetEntity.VILLAGER.isSelected();
                            boolean selected5 = TPAuraTargetEntity.INVISIBLE.isSelected();
                            TPAuraTargetEntity tPAuraTargetEntity = TPAuraTargetEntity.DEAD;
                            if (ChatUtil.checkEntity3(
                            entity, selected, selected2, selected3, selected4, false, selected5, true, true, true, tPAuraTargetEntity.isSelected(), true
                            )) {
                                arrayList.add(entity);
                            }
                        }
                    }
                }
            }

            arrayList.sort(Comparator.comparingDouble(object -> getCoordinate((Entity)object)));
            int index = 0;
            if (!arrayList.isEmpty()) {
                Vec3 vec3 = minecraft.thePlayer.getVec32(1.0F);
                Vec3 vec4 = minecraft.thePlayer.getVec32(1.0F);
                BlockPlaceData blockPlaceData = new BlockPlaceData((int)Math.floor(vec4.xCoord), (int)Math.floor(vec4.yCoord), (int)Math.floor(vec4.zCoord));
                if (minecraft.thePlayer.boundingBox3 != null) {
                    int minX = (int)minecraft.thePlayer.boundingBox3.minX;
                    int maxY = (int)minecraft.thePlayer.boundingBox3.maxY;
                    blockPlaceData = new BlockPlaceData(minX, maxY, (int)minecraft.thePlayer.boundingBox3.minZ);
                }

                ArrayList arrayList2 = new ArrayList();
                this.itemList4 = new ArrayList();
                this.itemList3 = new ArrayList();
                this.teleportPath = new ArrayList();
                this.itemList3.add(minecraft.thePlayer.getVec32(1.0F));
                int index2 = 100;
                boolean enabled = this.settingFly.isEnabled();
                long time = (long)(35.0 / this.settingMaxTargets.getValue());
                float[] factorArray = new float[2];
                factorArray[0] = minecraft.thePlayer.rotationYaw;
                factorArray[1] = minecraft.thePlayer.rotationPitch;

                while (index2 > 0) {
                    ArrayList arrayList3 = new ArrayList();

                    for (Entity entity2 : (Iterable<Entity>) arrayList) {
                        Vec3 vec5 = entity2.getVec32(1.0F);
                        BlockPlaceData blockPlaceData2 = new BlockPlaceData((int)Math.floor(vec5.xCoord), (int)Math.floor(vec5.yCoord), (int)Math.floor(vec5.zCoord));
                        PathRenderer renderListenerAdapter = new PathRenderer(blockPlaceData, blockPlaceData2, false, false, enabled);
                        renderListenerAdapter.state = false;
                        ArrayList arrayList22 = renderListenerAdapter.getArrayList2(true, time);
                        arrayList3.add(entity2);
                        if (arrayList22 != null && !arrayList22.isEmpty()) {
                            Collections.reverse(arrayList22);
                            int index3 = 0;
                            this.itemList4.addAll(arrayList22);

                            Vec3 vec;
                            do {
                                int index4 = index3 + 1;
                                index3 = this.getIndex(arrayList22, vec4, index4);
                                vec = ((PathNode)arrayList22.get(index3)).getBlockPlaceData().getVec3().getVec312(0.5, 0.0, 0.5);
                                if (index3 == arrayList22.size() - 1) {
                                    vec = vec5;
                                }

                                this.applyVec3(vec, entity2);
                                vec4 = vec;
                                arrayList2.add(vec);
                            } while (index3 < arrayList22.size() - 1);

                            this.applyEntity(entity2);
                            if (this.settingLegit.isEnabled()) {
                                this.applyEntity2(entity2);
                            }

                            blockPlaceData = new BlockPlaceData((int)Math.round(vec.xCoord), (int)Math.round(vec.yCoord), (int)Math.round(vec.zCoord));
                            double offset3 = (double)(++index);
                            if (offset3 >= this.settingMaxTargets.getValue()) {
                                index2 = 0;
                            }
                            break;
                        }

                        double offset4 = (double)(++index);
                        if (offset4 >= this.settingMaxTargets.getValue()) {
                            index2 = 0;
                            break;
                        }
                    }

                    if (arrayList.isEmpty()) {
                        break;
                    }

                    index2--;
                    arrayList.removeAll(arrayList3);
                    Vec3 vec2 = vec4;
                    arrayList.sort(Comparator.comparingDouble(object2 -> getCoordinate(vec2, (Entity)object2)));
                }

                if (!arrayList2.isEmpty()) {
                    this.applyArrayList(arrayList2, vec3);
                }

                if (this.settingLegit.isEnabled()) {
                    minecraft.thePlayer.rotationYaw = factorArray[0];
                    minecraft.thePlayer.rotationPitch = factorArray[1];
                }
            }

            this.expanded2 = false;
        }
    }

    private void applyArrayList(ArrayList arrayList, Vec3 vec) {
        Collections.reverse(arrayList);
        arrayList.add(vec);
        if (this.settingLegit.isEnabled()) {
            Iterator iterator = arrayList.iterator();

            while (iterator.hasNext()) {
                this.getIndex((Vec3)iterator.next());
            }
        } else {
            Iterator iterator2 = arrayList.iterator();
            NetHandlerPlayClient netHandlerPlayClient;
            if (iterator2.hasNext()) {
                Vec3 vec2 = (Vec3)iterator2.next();
                minecraft.thePlayer
                .netHandlerPlayClient
                .handlePacket(new C04PacketPlayerPosition(vec2.xCoord, vec2.yCoord, vec2.zCoord, minecraft.thePlayer.onGround));

                while (iterator2.hasNext()) {
                    Vec3 vec3 = (Vec3)iterator2.next();
                    minecraft.thePlayer
                    .netHandlerPlayClient
                    .handlePacket(new C04PacketPlayerPosition(vec3.xCoord, vec3.yCoord, vec3.zCoord, minecraft.thePlayer.onGround));
                }

                netHandlerPlayClient = minecraft.thePlayer.netHandlerPlayClient;
            } else {
                netHandlerPlayClient = minecraft.thePlayer.netHandlerPlayClient;
            }

            netHandlerPlayClient.handlePacket(
            new C04PacketPlayerPosition(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ, minecraft.thePlayer.onGround)
            );
        }
    }

    private void applyEntity(Entity entity) {
        if (!this.settingNoswing.isEnabled()) {
            minecraft.thePlayer.respawnPlayer();
        }

        minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C02PacketUseEntity(entity, UseEntityAction.ATTACK));
    }

    private void processFloat(float value, float value2) {
        Rotation rotation8 = SimulatedPlayer.getRotation8(
        SimulatedPlayer.getFloat9(this.rotation.yaw, value, 180.0F),
        SimulatedPlayer.getFloat9(this.rotation.pitch, value2, 180.0F),
        
        this.rotation.yaw,
        this.rotation.pitch
        );
        this.rotation.yaw = rotation8.yaw;
        this.rotation.pitch = rotation8.pitch;
        minecraft.thePlayer.rotationYaw = rotation8.yaw;
        minecraft.thePlayer.rotationPitch = rotation8.pitch;
    }

    private static double getCoordinate(Entity entity) {
        return entity.getCoordinate(minecraft.thePlayer);
    }

    private void applyVec3(Vec3 vec, Entity entity) {
        if (!this.settingLegit.isEnabled()) {
            minecraft.thePlayer
            .netHandlerPlayClient
            .handlePacket(new C04PacketPlayerPosition(vec.xCoord, vec.yCoord, vec.zCoord, minecraft.thePlayer.onGround));
            this.itemList3.add(vec);
        } else {
            int index = this.getIndex(vec);

            while (index > 0) {
                index--;
                entity.updateState();
            }
        }
    }

    private int getIndex(Vec3 vec) {
        int index = 0;

        while ((double)index < Math.max(10.0, 15.0 * this.settingMaxTeleportDistance.getValue()) && minecraft.thePlayer.getCoordinate(vec) > 0.8) {
            index++;
            double offset = vec.xCoord + 0.5 - minecraft.thePlayer.posX;
            float value = (float)(MathHelper.getDouble6(vec.zCoord + 0.5 - minecraft.thePlayer.posZ, offset) * 180.0 / Math.PI - 90.0);
            float value2 = (float)ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            this.processFloat(value, value2);
            minecraft.thePlayer.updateState();
            this.itemList3.add(new Vec3(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ));
        }

        return index;
    }

    private void applyEntity2(Entity entity) {
        this.expanded2 = false;
        this.enabled2 = true;
        Vec3 vec = SimulatedPlayer.getVec3(entity, (short)20072, -521758288);
        double offset = vec.xCoord - minecraft.thePlayer.posX;
        double offset2 = vec.yCoord - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
        double offset3 = vec.zCoord - minecraft.thePlayer.posZ;
        float value = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
        float value2 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
        this.processFloat(value, value2);
        minecraft.thePlayer.updateState();
        entity.updateState();
        this.expanded2 = true;
        this.enabled2 = false;
    }

    private static double getCoordinate(Vec3 vec, Entity entity) {
        return entity.getDouble3(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    public boolean checkVec3(Vec3 vec, Vec3 vec2) {
        Vec3[][] vecMatrix = new Vec3[6][];
        Vec3 vec3 = vec2.getVec311(vec).addVector(0.0, 1.0, 0.0).normalize().getVec3(0.6);
        vecMatrix[0] = new Vec3[]{vec, vec2};
        vecMatrix[1] = new Vec3[]{vec.getVec312(0.0, 1.5, 0.0), vec2.getVec312(0.0, 1.5, 0.0)};
        vecMatrix[2] = new Vec3[]{vecMatrix[0][0].getVec36(vec3), vecMatrix[0][1].getVec36(vec3)};
        vecMatrix[3] = new Vec3[]{vecMatrix[1][0].getVec36(vec3), vecMatrix[1][1].getVec36(vec3)};
        vecMatrix[4] = new Vec3[]{vecMatrix[0][0].getVec311(vec3), vecMatrix[0][1].getVec311(vec3)};
        vecMatrix[5] = new Vec3[]{vecMatrix[1][0].getVec311(vec3), vecMatrix[1][1].getVec311(vec3)};

        for (Vec3[] vecArray : vecMatrix) {
            Vec3 vec4 = vecArray[0];
            Vec3 vec5 = vecArray[1];
            MovingObjectPosition movingObjectPosition5 = minecraft.theWorld
            .getMovingObjectPosition5(vec4.xCoord, vec4.yCoord, vec4.zCoord, vec5.xCoord, vec5.yCoord, vec5.zCoord, false, false, false);
            boolean enabled;
            if (movingObjectPosition5 != null && movingObjectPosition5.typeOfHit == MovingObjectType.BLOCK) {
                enabled = true;
            } else {
                enabled = false;
            }

            if (enabled) {
                this.teleportPath.add(vecMatrix);
                return true;
            }
        }

        return false;
    }

    private int getIndex(ArrayList arrayList, Vec3 vec, int index) {
        int index2 = index;
        if (index >= arrayList.size()) {
            return arrayList.size() - 1;
        } else {
            Vec3 vec2 = ((PathNode)arrayList.get(index)).getBlockPlaceData().getVec3().getVec312(0.5, 0.0, 0.5);
            if (vec2.dotProduct(vec) > this.settingMaxTeleportDistance.getValue() * this.settingMaxTeleportDistance.getValue()) {
                return index == index ? index : index - 1;
            } else {
                boolean enabled = this.checkVec3(vec, vec2);

                while (!enabled) {
                    if (++index2 >= arrayList.size()) {
                        return arrayList.size() - 1;
                    }

                    Vec3 vec3 = ((PathNode)arrayList.get(index2)).getBlockPlaceData().getVec3().getVec312(0.5, 0.0, 0.5);
                    if (vec3.dotProduct(vec) > this.settingMaxTeleportDistance.getValue()
                    * this.settingMaxTeleportDistance.getValue()) {
                        break;
                    }

                    enabled = this.checkVec3(vec, vec3);
                }

                return index == index2 ? index : index2 - 1;
            }
        }
    }
}
