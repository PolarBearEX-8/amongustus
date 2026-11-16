package augustus.modules.misc;

import client.Client;
import client.ClientGlobals;
import client.event.EventWorldLoad;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.render.PathRenderer;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.MOBVillagerEnum;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NamedOption;
import client.util.ChatUtil;
import client.util.DefaultVertexFormats;
import client.util.MinecraftAccessor;
import client.util.PathNode;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.block.BlockPlaceData;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public class AutoWalk2
extends Module
implements TickListener,
WorldLoadListener,
RenderListener {
    public final MultiSelectSetting settingTargets = new MultiSelectSetting(this, "Targets", (NamedOption[])MOBVillagerEnum.values());
    public final ModeSetting settingDistance = new ModeSetting(this, "TargetSort", "Distance", new String[]{"Health", "Distance"});
    public final BooleanSetting settingDebugLine = new BooleanSetting(this, "DebugLine", true);
    public ArrayList arrayList;
    public EntityLivingBase entityLivingBase = null;
    public int count6 = 0;

    public AutoWalk2() {
        super("AutoWalk2", "AutomatischesLaufen die zweite", Color.BLACK, Category.MOVEMENT);
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onDisable() {
        this.entityLivingBase = null;
        this.arrayList = null;
    }

    @Override
    public void onEnable() {
        ((Module)this).registerEvent(40);
        ((Module)this).registerEvent(17);
        ((Module)this).registerEvent(32);
    }

    @Override
    public void onWorldEvent(EventWorldLoad eventWorldLoad) {
        this.entityLivingBase = null;
    }

    @Override
    public void onRender3D(float f) {
        if (!this.settingDebugLine.isEnabled() || this.arrayList == null || this.arrayList.isEmpty()) {
            return;
        }
        RenderUtils.updateState();
        GlStateManager.pushMatrix();
        GlStateManager.translate3d2(-MinecraftAccessor.minecraft.getRenderManager().getRenderPosX(), -MinecraftAccessor.minecraft.getRenderManager().getRenderPosY(), -MinecraftAccessor.minecraft.getRenderManager().getRenderPosZ());
        GlStateManager.color4f2(1.0f, 1.0f, 1.0f, 1.0f);
        WorldRenderer worldRenderer = Tessellator.getTessellator().getWorldRenderer();
        worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);
        for (PathNode pathNode : (Iterable<PathNode>)(Iterable)this.arrayList) {
            worldRenderer.getWorldRenderer((double)pathNode.getBlockPlaceData().indexOffset1 + 0.5, (double)pathNode.getBlockPlaceData().indexOffset2 + 0.01, (double)pathNode.getBlockPlaceData().indexOffset + 0.5).updateState();
        }
        Tessellator.getTessellator().updateState();
        GlStateManager.popMatrix();
        RenderUtils.prepareScissor();
    }

    @Override
    public void onPreTick() {
        long l = 0xCCDF32DC454L ^ 0x6270D13672BAL;
        int n = (int)(l >>> 48);
        int n2 = (int)(l << 16 >>> 32);
        int n3 = (int)(l << 48 >>> 48);
        EntityLivingBase entityLivingBase = this.getEntityLivingBase();
        if (entityLivingBase != null && MinecraftAccessor.minecraft.thePlayer.ticksExisted % 5 == 0) {
            short shortValue = (short)n3;
            int n4 = n2;
            short shortValue2 = (short)n;
            this.applyEntityLivingBase(shortValue2, n4, shortValue, entityLivingBase);
        }
        this.entityLivingBase = entityLivingBase;
        if (this.arrayList == null || this.arrayList.size() <= this.count6) {
            return;
        }
        PathNode pathNode = (PathNode)this.arrayList.get(this.count6);
        while (this.matchesPathNode(pathNode)) {
            ++this.count6;
            if (this.count6 >= this.arrayList.size()) {
                pathNode = null;
                break;
            }
            pathNode = (PathNode)this.arrayList.get(this.count6);
        }
        if (pathNode == null) {
            return;
        }
        float f = 180.0f;
        float f2 = 180.0f;
        Rotation rotation = ClientGlobals.rotationManager.getRotation3();
        Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0f);
        Rotation rotation2 = SimulatedPlayer.getRotation(pathNode.getBlockPlaceData().getVec3().getVec312(0.5, 0.5, 0.5), vec3, rotation, f2, f);
        float f3 = rotation2.yaw;
        ClientGlobals.rotationManager.applyModule2(f3, -4, this);
    }

    private boolean matchesPathNode(PathNode pathNode) {
        return pathNode.getBlockPlaceData().getVec3().getVec312(0.5, 0.5, 0.5).squareDistanceTo(MinecraftAccessor.minecraft.thePlayer.getVec32(1.0f)) < 1.0;
    }

    private EntityLivingBase getEntityLivingBase() {
        List list = this.settingDistance.getSelected().equals("Health") ? (List)MinecraftAccessor.minecraft.theWorld.loadedEntityList.stream().filter(elem -> this.matchesEntity((Entity)elem)).sorted(Comparator.comparingDouble(elem -> AutoWalk2.computeValueByEntity((Entity)elem))).collect(Collectors.toList()) : (List)MinecraftAccessor.minecraft.theWorld.loadedEntityList.stream().filter(elem -> this.matchesEntity((Entity)elem)).sorted(Comparator.comparingDouble(elem -> AutoWalk2.computeValue((Entity)elem))).collect(Collectors.toList());
        return list.isEmpty() ? null : (EntityLivingBase)list.get(0);
    }

    private boolean matchesEntity(Entity entity) {
        if (!(entity instanceof EntityLivingBase) || entity instanceof EntityPlayerSP) {
            return false;
        }
        boolean bl = MOBVillagerEnum.INVISIBLE.isSelected();
        boolean bl2 = MOBVillagerEnum.matchesMOBVillagerEnum(MOBVillagerEnum.ARMOR_STAND);
        boolean bl3 = MOBVillagerEnum.matchesMOBVillagerEnum(MOBVillagerEnum.VILLAGER);
        boolean bl4 = MOBVillagerEnum.matchesMOBVillagerEnum(MOBVillagerEnum.ANIMAL);
        boolean bl5 = MOBVillagerEnum.matchesMOBVillagerEnum(MOBVillagerEnum.MOB);
        boolean bl6 = MOBVillagerEnum.matchesMOBVillagerEnum(MOBVillagerEnum.PLAYER);
        return ChatUtil.checkEntity3(entity, bl6, bl5, bl4, bl3, bl2, bl, true, true, true, true, true);
    }

    private void applyEntityLivingBase(short shortValue, int n, short shortValue2, EntityLivingBase entityLivingBase) {
        Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec32(1.0f);
        Vec3 vec32 = entityLivingBase.getVec32(1.0f);
        BlockPlaceData blockPlaceData = new BlockPlaceData((int)Math.floor(vec3.xCoord), (int)Math.floor(vec3.yCoord), (int)Math.floor(vec3.zCoord));
        if (MinecraftAccessor.minecraft.thePlayer.boundingBox3 != null) {
            blockPlaceData = new BlockPlaceData((int)MinecraftAccessor.minecraft.thePlayer.boundingBox3.minX, (int)MinecraftAccessor.minecraft.thePlayer.boundingBox3.maxY, (int)MinecraftAccessor.minecraft.thePlayer.boundingBox3.minZ);
        }
        BlockPlaceData blockPlaceData2 = new BlockPlaceData((int)Math.floor(vec32.xCoord), (int)Math.floor(vec32.yCoord), (int)Math.floor(vec32.zCoord));
        boolean bl = ((Module)Client.getClient().getModuleManager().jesus).isToggled();
        boolean bl2 = bl && ((ModeSetting)Client.getClient().getModuleManager().jesus.settingMode).getSelected().equalsIgnoreCase("dolphin");
        PathRenderer pathRenderer = new PathRenderer(blockPlaceData, blockPlaceData2, bl, bl2, false);
        pathRenderer.state1 = true;
        this.arrayList = pathRenderer.getArrayList2(true, 1000L);
        if (this.arrayList == null) {
            return;
        }
        Collections.reverse(this.arrayList);
        this.count6 = 0;
    }

    private static double computeValue(Entity entity) {
        return MinecraftAccessor.minecraft.thePlayer.getDistanceSqToEntity(entity);
    }

    private static double computeValueByEntity(Entity entity) {
        return ((EntityLivingBase)entity).getFloat2();
    }
}

