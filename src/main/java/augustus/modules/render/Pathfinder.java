package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import augustus.modes.render.PathfinderEntityTarget;
import augustus.modes.render.PathfinderRankedEntity;
import client.Client;
import client.ClientGlobals;
import client.event.EventKeyInput;
import client.event.KeyInputListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.module.ModuleManager;
import client.render.PathfinderRenderer;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.PathfinderTargetEntity;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.StringSetting;
import client.util.AStarPathCalculator;
import client.util.BooleanSupplier;
import client.util.BuildManager;
import client.util.ChatUtil;
import client.util.MovingObjectType;
import client.util.PathNode;
import client.util.PlayerUtil;
import client.util.PreEndHandManager;
import client.util.RayTraceUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockPlaceData;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class Pathfinder extends Module implements RenderListener, TickListener, KeyInputListener {
    public SettingGroup settingAutoTarget;
    public BooleanSetting settingAutoTarget2;
    public MultiSelectSetting settingTargets;
    public BooleanSetting settingChooseTargetByPathLength;
    public NumberSetting settingMaxConsideredTargets;
    public BooleanSetting settingMurderMystery;
    public NumberSetting settingMaxSearchtimePerTick;
    public NumberSetting settingMaxSteps;
    public NumberSetting settingTicksBetweenSearches;
    public NumberSetting settingMaxFallHeight;
    public BooleanSetting settingDiscourageFalldamage;
    public NumberSetting settingDiscourageFalldamageFactor;
    public StringSetting settingPickTarget;
    public StringSetting settingResetPickedTarget;
    public ColorSetting settingPathColor;
    public ColorSetting settingTargetColor;
    public Entity entity;
    public ArrayList itemList3;
    public ArrayList renderedPath;
    public ArrayList itemList4;
    public Entity entity2;
    public BlockPlaceData blockPlaceData;
    public AxisAlignedBB boundingBox;
    public int tickDelay;
    public AStarPathCalculator extendedClientManager;
    public Vec3 positionVec;

    public Pathfinder() {
        super("Pathfinder", "Pfadfinder", Color.RED, Category.RENDER);
        this.settingAutoTarget = new SettingGroup(this, "Auto Target");
        this.settingAutoTarget2 = new BooleanSetting(this, "Auto Target", true, this.settingAutoTarget);
        PathfinderTargetEntity[] pathfinderTargetEntityArray = PathfinderTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", pathfinderTargetEntityArray, this.settingAutoTarget).getMultiSelectSetting(() -> this.settingAutoTarget2.isEnabled());
        this.settingChooseTargetByPathLength = (BooleanSetting)new BooleanSetting(this, "Choose Target By Path Length", true, this.settingAutoTarget).getSetting(() -> this.settingAutoTarget2.isEnabled());
        this.settingMaxConsideredTargets = new NumberSetting(this, "Max considered Targets", 2.0, 2.0, 5.0, 0, this.settingAutoTarget).getNumberSetting(() -> this.isMaxConsideredTargetsVisible());
        this.settingMurderMystery = (BooleanSetting)new BooleanSetting(this, "Murder Mystery", false, this.settingAutoTarget).getSetting(() -> this.isMurderMysteryVisible());
        this.settingMaxSearchtimePerTick = new NumberSetting(this, "Max Searchtime per Tick", 5.0, 2.0, 40.0, 0);
        this.settingMaxSteps = new NumberSetting(this, "Max Steps", 4.0, 1.0, 20.0, 0);
        this.settingTicksBetweenSearches = new NumberSetting(this, "Ticks between searches", 4.0, 0.0, 20.0, 0);
        this.settingMaxFallHeight = new NumberSetting(this, "Max Fall Height", 255.0, 1.0, 255.0, 0);
        this.settingDiscourageFalldamage = new BooleanSetting(this, "Discourage Falldamage", false);
        this.settingDiscourageFalldamageFactor = new NumberSetting(this, "Discourage Falldamage Factor", 90.0, 1.0, 100.0, 0).getNumberSetting(() -> this.settingDiscourageFalldamage.isEnabled());
        this.entity = (Entity)null;
        this.itemList3 = (ArrayList)null;
        this.renderedPath = (ArrayList)null;
        ArrayList arrayList = new ArrayList();
        this.itemList4 = arrayList;
        this.entity2 = (Entity)null;
        this.blockPlaceData = (BlockPlaceData)null;
        this.boundingBox = (AxisAlignedBB)null;
        this.tickDelay = 0;
        this.extendedClientManager = new AStarPathCalculator();
        this.settingPickTarget = new StringSetting(this, "Pick Target");
        this.settingResetPickedTarget = new StringSetting(this, "Reset picked target");
        this.settingPathColor = new ColorSetting(this, "Path Color", Color.WHITE);
        Color color = new Color(255, 255, 255, 100);
        this.settingTargetColor = new ColorSetting(this, "Target Color", color);
        this.settingPickTarget.processAction(10001);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(40);
        this.registerEvent(14);
    }

    @Override
    public void onDisable() {
        this.itemList3 = null;
        this.entity = null;
        this.entity2 = null;
        this.blockPlaceData = null;
    }

    @Override
    public void onPreDisable() {
        this.itemList3 = null;
        this.entity = null;
    }

    @Override
    public void onKeyInput(EventKeyInput event) {
        int index = event.getIndex();
        int int3 = this.settingPickTarget.getInt3();
        if (index == int3) {
            MovingObjectPosition movingObjectPosition = this.getMovingObjectPosition();
            if (movingObjectPosition.typeOfHit == MovingObjectType.ENTITY) {
                if (movingObjectPosition.entityHit != this.entity2) {
                    this.entity2 = movingObjectPosition.entityHit;
                    this.blockPlaceData = null;
                    this.itemList3 = null;
                    this.entity = null;
                    this.updateState2();
                    this.updateState();
                    this.onTick();
                }
            } else {
                MovingObjectType typeOfHit = movingObjectPosition.typeOfHit;
                MovingObjectType movingObjectTypeKind = MovingObjectType.BLOCK;
                if (typeOfHit == movingObjectTypeKind) {
                    Vec3i vec3i = movingObjectPosition.sideHit.getVec3i();
                    BlockPos blockPos = movingObjectPosition.getBlockPos();
                    this.blockPlaceData = new BlockPlaceData(
                    blockPos.getX() + vec3i.getX(), blockPos.getY() + vec3i.getY(), blockPos.getZ() + vec3i.getZ()
                    );
                    this.boundingBox = BlockOverlay.getAxisAlignedBB(
                    
                    
                    minecraft.theWorld.getBlockState(blockPos).getBlock().processAction((World)minecraft.theWorld, blockPos),
                    
                    movingObjectPosition.sideHit
                    );
                    this.entity2 = null;
                    this.itemList3 = null;
                    this.entity = null;
                    this.updateState2();
                    this.updateState();
                    this.onTick();
                }
            }
        } else {
            int index2 = event.getIndex();
            int int32 = this.settingResetPickedTarget.getInt3();
            if (index2 == int32) {
                if (this.blockPlaceData == null && this.entity2 == null) {
                    return;
                }

                this.blockPlaceData = null;
                this.entity2 = null;
                this.itemList3 = null;
                this.entity = null;
                this.updateState2();
                this.updateState();
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glDisable(3008);
        if (BuildManager.canExecute()) {
            PreEndHandManager.updateState();
        }

        GlStateManager.disableAlpha();
        GL11.glDepthMask(false);
        GL11.glLineWidth(2.0F);
        GL11.glTranslated(
        -minecraft.getRenderManager().getRenderPosX(), -minecraft.getRenderManager().getRenderPosY(), -minecraft.getRenderManager().getRenderPosZ()
        );
        this.settingPathColor.getInt12(0);
        GlStateManager.color4f2(
        (float)this.settingPathColor.getInt7() / 255.0F,
        (float)this.settingPathColor.getInt10() / 255.0F,
        (float)this.settingPathColor.getInt13() / 255.0F,
        (float)this.settingPathColor.getInt4() / 255.0F
        );
        if (this.itemList3 != null && !this.itemList3.isEmpty()) {
            GL11.glBegin(3);
            if (this.entity2 != null) {
                double doubleEntry = PlayerUtil.getDouble(this.entity2.lastTickPosX, this.entity2.posX, (double)partialTicks);
                double doubleEntry2 = PlayerUtil.getDouble(this.entity2.lastTickPosY, this.entity2.posY, (double)partialTicks);
                double doubleEntry3 = PlayerUtil.getDouble(this.entity2.lastTickPosZ, this.entity2.posZ, (double)partialTicks);
                GL11.glVertex3d(doubleEntry, doubleEntry2, doubleEntry3);
            } else if (this.entity != null) {
                GL11.glVertex3d(
                PlayerUtil.getDouble(this.entity.lastTickPosX, this.entity.posX, (double)partialTicks),
                PlayerUtil.getDouble(this.entity.lastTickPosY, this.entity.posY, (double)partialTicks),
                PlayerUtil.getDouble(this.entity.lastTickPosZ, this.entity.posZ, (double)partialTicks)
                );
            }

            double lastTickPosX = minecraft.thePlayer.lastTickPosX;
            double posX = minecraft.thePlayer.posX;
            double offset = (double)partialTicks;
            double doubleEntry4 = PlayerUtil.getDouble(lastTickPosX, posX, offset);
            double doubleEntry5 = PlayerUtil.getDouble(minecraft.thePlayer.lastTickPosY, minecraft.thePlayer.posY, (double)partialTicks);
            double doubleEntry6 = PlayerUtil.getDouble(minecraft.thePlayer.lastTickPosZ, minecraft.thePlayer.posZ, (double)partialTicks);
            if (ClientGlobals.moduleManager.freeCam.isToggled()) {
                doubleEntry4 = PlayerUtil.getDouble(
                ClientGlobals.moduleManager.freeCam.reachDistance4, ClientGlobals.moduleManager.freeCam.reachDistance6, (double)partialTicks
                );
                doubleEntry5 = PlayerUtil.getDouble(
                ClientGlobals.moduleManager.freeCam.reachDistance, ClientGlobals.moduleManager.freeCam.reachDistance5, (double)partialTicks
                );
                doubleEntry6 = PlayerUtil.getDouble(
                ClientGlobals.moduleManager.freeCam.reachDistance3, ClientGlobals.moduleManager.freeCam.reachDistance1, (double)partialTicks
                );
            }

            ArrayList itemList32 = this.itemList3;
            if (this.renderedPath != null) {
                itemList32 = new ArrayList(Math.max(itemList32.size(), this.renderedPath.size()));
                int index5 = Math.min(this.itemList3.size(), this.renderedPath.size());

                for (int i = 0; i < index5; i++) {
                    itemList32.add(((Vec3)this.renderedPath.get(i)).getVec39((Vec3)this.itemList3.get(i), (double)partialTicks));
                }

                if (index5 < this.itemList3.size()) {
                    for (int i2 = index5; i2 < this.itemList3.size(); i2++) {
                        itemList32.add(this.itemList3.get(i2));
                    }
                }
            }

            Vec3 vec = null;
            int index = -1;
            double offset2 = Double.POSITIVE_INFINITY;

            for (int i3 = 0; i3 < itemList32.size() - 1; i3++) {
                Vec3 vec2 = (Vec3)itemList32.get(i3);
                Vec3 vec3 = (Vec3)itemList32.get(i3 + 1);
                double xCoord = vec2.xCoord;
                double zCoord = vec2.zCoord;
                double offset3 = vec3.xCoord - vec2.xCoord;
                double offset4 = vec3.yCoord - vec2.yCoord;
                double offset5 = vec3.zCoord - vec2.zCoord;
                Vec3 vec5 = this.getVec3(offset3, offset4, offset5, xCoord, vec2.yCoord, zCoord, doubleEntry4, doubleEntry5, doubleEntry6);
                double double4 = vec5.getDouble4(doubleEntry4, doubleEntry5, doubleEntry6);
                if (double4 < offset2) {
                    offset2 = double4;
                    vec = vec5;
                    index = i3;
                }
            }

            for (int i4 = 0; i4 < itemList32.size(); i4++) {
                Vec3 vec4 = (Vec3)itemList32.get(i4);
                GL11.glVertex3d(vec4.xCoord, vec4.yCoord + 0.01, vec4.zCoord);
                if (i4 == index) {
                    GL11.glVertex3d(vec.xCoord, vec.yCoord + 0.01, vec.zCoord);
                    break;
                }
            }

            GL11.glVertex3d(doubleEntry4, doubleEntry5, doubleEntry6);
            GL11.glEnd();
        }

        this.settingTargetColor.getInt12(0);
        GlStateManager.color4f2(
        (float)this.settingTargetColor.getInt7() / 255.0F,
        (float)this.settingTargetColor.getInt10() / 255.0F,
        (float)this.settingTargetColor.getInt13() / 255.0F,
        (float)this.settingTargetColor.getInt4() / 255.0F
        );
        if (this.blockPlaceData != null) {
            RenderUtils.drawBoundingBox(this.boundingBox);
        }

        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GlStateManager.enableLighting();
        GL11.glEnable(3553);
        if (BuildManager.canExecute()) {
            PreEndHandManager.updateState2();
        }

        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2848);
        GL11.glEnable(3008);
    }

    @Override
    public void onClientTick() {
        if (minecraft.thePlayer != null && minecraft.theWorld != null) {
            this.renderedPath = null;
            if (this.itemList3 != null) {
                Vec3 vec = (Vec3)this.itemList3.get(0);
                double offset = (vec.xCoord + 0.5 - minecraft.thePlayer.posX) * (vec.xCoord + 0.5 - minecraft.thePlayer.posX)
                + (vec.yCoord - minecraft.thePlayer.posY) * (vec.yCoord - minecraft.thePlayer.posY)
                + (vec.zCoord + 0.5 - minecraft.thePlayer.posZ) * (vec.zCoord + 0.5 - minecraft.thePlayer.posZ)
                - 1.0;
                if (!((offset == 0.0 ? 0.0 : (double)(offset < 0.0 ? -1 : 1)) > 0.0) && this.blockPlaceData != null
                || this.entity2 != null && minecraft.theWorld.getEntityByID(this.entity2.getEntityId()) == null) {
                    this.itemList3 = null;
                    this.entity = null;
                    this.blockPlaceData = null;
                    this.entity2 = null;
                    this.updateState2();
                }
            }

            if (!this.itemList4.isEmpty()) {
                this.updateState4();
            } else {
                if (this.tickDelay < 0) {
                    this.tickDelay = 0;
                }

                if (this.tickDelay == 0) {
                    this.updateState();
                } else {
                    this.tickDelay--;
                }

                if ((double)this.tickDelay > this.settingTicksBetweenSearches.getValue()) {
                    this.tickDelay = (int)this.settingTicksBetweenSearches.getValue();
                }
            }
        } else {
            this.itemList3 = null;
            this.entity = null;
            this.blockPlaceData = null;
            this.entity2 = null;
            this.updateState2();
        }
    }

    private void updateState() {
        this.itemList4.clear();
        Vec3 vec = minecraft.thePlayer.getVec32(1.0F);
        BlockPlaceData blockPlaceData2 = new BlockPlaceData((int)Math.floor(vec.xCoord), (int)Math.floor(vec.yCoord), (int)Math.floor(vec.zCoord));
        BlockPlaceData blockPlaceData3 = null;
        Entity entity3 = null;
        boolean enabled = false;
        if (this.entity2 != null) {
            Vec3 vec2 = this.entity2.getVec32(1.0F);
            blockPlaceData3 = new BlockPlaceData((int)Math.floor(vec2.xCoord), (int)Math.floor(vec2.yCoord), (int)Math.floor(vec2.zCoord));
        } else if (this.blockPlaceData != null) {
            blockPlaceData3 = this.blockPlaceData;
        } else if (this.settingAutoTarget2.isEnabled()) {
            if (this.settingChooseTargetByPathLength.isEnabled()) {
                this.updateState3();
                if (this.itemList4.isEmpty()) {
                    this.itemList3 = null;
                    this.entity = null;
                }

                return;
            }

            entity3 = this.getEntity();
            if (entity3 != null) {
                Vec3 vec3 = entity3.getVec32(1.0F);
                blockPlaceData3 = new BlockPlaceData((int)Math.floor(vec3.xCoord), (int)Math.floor(vec3.yCoord), (int)Math.floor(vec3.zCoord));
                enabled = entity3 instanceof EntityItem;
            }
        }

        if (blockPlaceData3 != null) {
            this.positionVec = vec;
            ArrayList itemList42 = this.itemList4;
            boolean toggled = Client.getClient().getModuleManager().jesus.isToggled();
            itemList42.add(new PathfinderEntityTarget(this, this.getPathfinderRenderer(blockPlaceData2, blockPlaceData3, toggled, enabled), entity3));
        } else {
            this.itemList3 = null;
            this.entity = null;
        }
    }

    private ArrayList getArrayList(ArrayList arrayList2) {
        ArrayList arrayList = new ArrayList(arrayList2.size());
        Vec3 vec = null;

        for (PathNode pathNode : (Iterable<PathNode>) arrayList2) {
            Vec3 vec2 = new Vec3(
            (double)pathNode.getBlockPlaceData().indexOffset1, (double)pathNode.getBlockPlaceData().indexOffset2, (double)pathNode.getBlockPlaceData().indexOffset
            );
            if (vec != null) {
                double coordinate = vec.dotProduct(vec2);
                if (coordinate > 2.0) {
                    double offset = Math.sqrt(coordinate);
                    double offset2 = (vec2.xCoord - vec.xCoord) / offset;
                    double offset3 = (vec2.yCoord - vec.yCoord) / offset;
                    double offset4 = (vec2.zCoord - vec.zCoord) / offset;

                    for (int i = 0; (double)i < offset - 1.0; i++) {
                        vec = new Vec3(vec.xCoord + offset2, vec.yCoord + offset3, vec.zCoord + offset4);
                        arrayList.add(vec.getVec312(0.5, 0.0, 0.5));
                    }
                }
            }

            arrayList.add(vec2.getVec312(0.5, 0.0, 0.5));
            vec = vec2;
        }

        return arrayList;
    }

    private PathfinderRenderer getPathfinderRenderer(BlockPlaceData blockPlaceData2, BlockPlaceData blockPlaceData3, boolean enabled, boolean enabled2) {
        PathfinderRenderer pathfinderRenderer = new PathfinderRenderer(blockPlaceData2, blockPlaceData3, enabled, true, false);
        pathfinderRenderer.state1 = true;
        pathfinderRenderer.state3 = this.settingDiscourageFalldamage.isEnabled();
        pathfinderRenderer.coordOffset4 = 1.001 - this.settingDiscourageFalldamageFactor.getDouble2(true) / 100.0;
        double double2 = this.settingMaxFallHeight.getDouble2(true);
        pathfinderRenderer.coordOffset2 = double2;
        if (enabled2) {
            pathfinderRenderer.coordOffset = 1.6;
        }

        return pathfinderRenderer;
    }

    private Entity getEntity() {
        Entity entity3 = null;
        double offset = 0.0;
        byte state = ((byte)(this.settingMurderMystery.isEnabled() ? 1 : 0));
        if ((byte)state != 0 && ClientGlobals.moduleManager.murdererFinder.isToggled() && RuntimeBridge.getSlotByKey() == -1) {
            state = 1;
        }

        byte state2 = state;

        for (Entity entity4 : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
            if (entity4 != minecraft.thePlayer && this.checkEntity(entity4, state2 != 0)) {
                double scaleFactor3 = (double)minecraft.thePlayer.getScaleFactor3(entity4);
                if (!(scaleFactor3 >= offset) || entity3 == null) {
                    entity3 = entity4;
                    offset = scaleFactor3;
                }
            }
        }

        return entity3;
    }

    private ArrayList getArrayList2(ArrayList arrayList2, int index) {
        ArrayList arrayList = new ArrayList(arrayList2.size());
        Iterator iterator = arrayList2.iterator();
        if (!iterator.hasNext()) {
            return this.extendedClientManager.getArrayList(arrayList, index, true);
        } else {
            Entity entity3 = (Entity)iterator.next();

            while (true) {
                arrayList.add(new PathfinderRankedEntity(this, entity3, entity3.getCoordinate(minecraft.thePlayer)));
                if (!iterator.hasNext()) {
                    return this.extendedClientManager.getArrayList(arrayList, index, true);
                }

                entity3 = (Entity)iterator.next();
            }
        }
    }

    private void updateState2() {
        this.tickDelay = (int)this.settingTicksBetweenSearches.getValue();
        this.itemList4.clear();
    }

    private Vec3 getVec3(double x, double x2, double x3, double x4, double x5, double x6, double x7, double y, double z) {
        double double7 = MathHelper.getDouble7(
        (x * x7 + x2 * y + x3 * z - (x * x4 + x2 * x5 + x3 * x6)) / (x * x + x2 * x2 + x3 * x3), 0.0, 1.0
        );
        double offset = x4 + double7 * x;
        double offset2 = x5 + double7 * x2;
        double offset3 = x6 + double7 * x3;
        return new Vec3(offset, offset2, offset3);
    }

    private boolean isMaxConsideredTargetsVisible() {
        return !this.settingAutoTarget2.isEnabled() ? false : this.settingChooseTargetByPathLength.isEnabled();
    }

    private ArrayList getArrayList3(ArrayList arrayList2) {
        int size = arrayList2.size();
        ArrayList arrayList = new ArrayList(size);
        Iterator iterator = arrayList2.iterator();

        while (iterator.hasNext()) {
            PathNode pathNode = (PathNode)iterator.next();
            double offset = (double)pathNode.getBlockPlaceData().indexOffset1 + 0.5;
            double indexOffset2 = (double)pathNode.getBlockPlaceData().indexOffset2;
            double offset2 = (double)pathNode.getBlockPlaceData().indexOffset + 0.5;
            Vec3 vec = new Vec3(offset, indexOffset2, offset2);
            arrayList.add(vec);
        }

        return arrayList;
    }

    private MovingObjectPosition getMovingObjectPosition() {
        double posX = minecraft.thePlayer.posX;
        double posY = minecraft.thePlayer.posY;
        double posZ = minecraft.thePlayer.posZ;
        float rotationYaw = minecraft.thePlayer.rotationYaw;
        float rotationPitch = minecraft.thePlayer.rotationPitch;
        boolean enabled = false;
        if (ClientGlobals.moduleManager.freeCam.isToggled()) {
            enabled = true;
            minecraft.thePlayer.posX = ClientGlobals.moduleManager.freeCam.positionVec.xCoord;
            minecraft.thePlayer.posY = ClientGlobals.moduleManager.freeCam.positionVec.yCoord;
            minecraft.thePlayer.posZ = ClientGlobals.moduleManager.freeCam.positionVec.zCoord;
            rotationYaw = ClientGlobals.moduleManager.freeCam.motionMultiplier8;
            rotationPitch = ClientGlobals.moduleManager.freeCam.motionMultiplier2;
        }

        MovingObjectPosition movingObjectPosition2 = RayTraceUtils.getMovingObjectPosition2(1.0F, rotationYaw, rotationPitch, 300.0, 300.0, 0.0);
        if (!enabled) {
            return movingObjectPosition2;
        } else {
            minecraft.thePlayer.posX = posX;
            minecraft.thePlayer.posY = posY;
            minecraft.thePlayer.posZ = posZ;
            return movingObjectPosition2;
        }
    }

    private boolean isMurderMysteryVisible() {
        if (this.settingAutoTarget2.isEnabled()) {
            if (ClientGlobals.moduleManager == null) {
                return true;
            }

            ModuleManager moduleManager = ClientGlobals.moduleManager;
            if (moduleManager.murdererFinder == null) {
                return true;
            }

            if (ClientGlobals.moduleManager.murdererFinder.isToggled()) {
                return true;
            }
        }

        return false;
    }

    private void updateState3() {
        ArrayList arrayList = new ArrayList();
        List list;
        boolean enabled;
        if (this.settingMurderMystery.isEnabled()
        && ClientGlobals.moduleManager.murdererFinder.isToggled()
        && RuntimeBridge.getSlotByKey() == -1
        && RuntimeBridge.findPathfinderIndex() != -1) {
            list = minecraft.theWorld.itemList7;
            enabled = this.settingMurderMystery.isEnabled();
        } else {
            list = minecraft.theWorld.loadedEntityList;
            enabled = this.settingMurderMystery.isEnabled();
        }

        boolean enabled2;
        if (enabled && ClientGlobals.moduleManager.murdererFinder.isToggled() && RuntimeBridge.getSlotByKey() == -1) {
            enabled2 = true;
        } else {
            enabled2 = false;
        }

        for (Entity entity3 : (Iterable<Entity>) list) {
            if (this.checkEntity(entity3, enabled2 & true)) {
                arrayList.add(entity3);
            }
        }

        ArrayList arrayList2 = this.getArrayList2(arrayList, (int)this.settingMaxConsideredTargets.getDouble2(true));
        boolean toggled = Client.getClient().getModuleManager().jesus.isToggled();
        Vec3 vec = this.positionVec = minecraft.thePlayer.getVec32(1.0F);
        BlockPlaceData blockPlaceData2 = new BlockPlaceData((int)Math.floor(vec.xCoord), (int)Math.floor(vec.yCoord), (int)Math.floor(vec.zCoord));
        this.itemList4.clear();

        for (PathfinderRankedEntity pathfinderRankedEntity : (Iterable<PathfinderRankedEntity>) arrayList2) {
            PathfinderRenderer pathfinderRenderer = this.getPathfinderRenderer(
            
            blockPlaceData2,
            new BlockPlaceData(
            (int)Math.floor(PathfinderRankedEntity.getEntity(pathfinderRankedEntity).posX),
            (int)Math.floor(PathfinderRankedEntity.getEntity(pathfinderRankedEntity).posY),
            (int)Math.floor(PathfinderRankedEntity.getEntity(pathfinderRankedEntity).posZ)
            ),
            toggled,
            PathfinderRankedEntity.getEntity(pathfinderRankedEntity) instanceof EntityItem
            );
            this.itemList4.add(new PathfinderEntityTarget(this, pathfinderRenderer, PathfinderRankedEntity.getEntity(pathfinderRankedEntity)));
        }
    }

    private void updateState4() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int index2 = 0;
        ArrayList arrayList4;
        Entity entity3;
        if (0 < this.itemList4.size()) {
            PathfinderRenderer pathfinderRenderer = ((PathfinderEntityTarget)this.itemList4.get(index2)).pathfinderRenderer;
            ArrayList arrayList3 = pathfinderRenderer.getArrayList3((long)this.settingMaxSearchtimePerTick.getValue());
            if (arrayList3 == null) {
                outerLoop:
                while (true) {
                    if ((double)pathfinderRenderer.indexOffset >= this.settingMaxSteps.getValue() || arrayList3 != null && arrayList3.isEmpty()) {
                        this.itemList4.remove(index2);
                        index2--;
                    }

                    while (true) {
                        if (++index2 >= this.itemList4.size()) {
                            break outerLoop;
                        }

                        pathfinderRenderer = ((PathfinderEntityTarget)this.itemList4.get(index2)).pathfinderRenderer;
                        arrayList3 = pathfinderRenderer.getArrayList3((long)this.settingMaxSearchtimePerTick.getValue());
                        if (arrayList3 == null || arrayList3.isEmpty()) {
                            break;
                        }

                        arrayList.add(arrayList3);
                        arrayList2.add(((PathfinderEntityTarget)this.itemList4.get(index2)).entity);
                        this.itemList4.remove(index2);
                        index2--;
                    }
                }
            } else {
                boolean empty = arrayList3.isEmpty();

                outerLoop:
                while (true) {
                    if (!empty) {
                        arrayList.add(arrayList3);
                        arrayList2.add(((PathfinderEntityTarget)this.itemList4.get(index2)).entity);
                        this.itemList4.remove(index2);
                        index2--;

                        while (true) {
                            if (++index2 >= this.itemList4.size()) {
                                break outerLoop;
                            }

                            pathfinderRenderer = ((PathfinderEntityTarget)this.itemList4.get(index2)).pathfinderRenderer;
                            arrayList3 = pathfinderRenderer.getArrayList3((long)this.settingMaxSearchtimePerTick.getValue());
                            if (arrayList3 != null) {
                                break;
                            }

                            if ((double)pathfinderRenderer.indexOffset >= this.settingMaxSteps.getValue()) {
                                this.itemList4.remove(index2);
                                index2--;
                            }
                        }
                    } else {
                        do {
                            if ((double)pathfinderRenderer.indexOffset >= this.settingMaxSteps.getValue() || arrayList3 != null && arrayList3.isEmpty()) {
                                this.itemList4.remove(index2);
                                index2--;
                            }

                            if (++index2 >= this.itemList4.size()) {
                                break outerLoop;
                            }

                            pathfinderRenderer = ((PathfinderEntityTarget)this.itemList4.get(index2)).pathfinderRenderer;
                            arrayList3 = pathfinderRenderer.getArrayList3((long)this.settingMaxSearchtimePerTick.getValue());
                        } while (arrayList3 == null);
                    }

                    empty = arrayList3.isEmpty();
                }
            }

            if (arrayList.isEmpty() && this.itemList4.isEmpty()) {
                this.updateState2();
                this.itemList3 = null;
                this.entity = null;
                return;
            }

            if (arrayList.isEmpty()) {
                return;
            }

            arrayList4 = null;
            entity3 = null;
            double offset = Double.POSITIVE_INFINITY;

            for (int i = 0; i < arrayList.size(); i++) {
                ArrayList arrayList5 = (ArrayList)arrayList.get(i);
                if (!arrayList5.isEmpty()) {
                    double doubleEntry = ((PathNode)arrayList5.get(0)).getDouble();
                    if (doubleEntry < offset) {
                        offset = doubleEntry;
                        arrayList4 = arrayList5;
                        entity3 = (Entity)arrayList2.get(i);
                    }
                }
            }
        } else {
            if (arrayList.isEmpty() && this.itemList4.isEmpty()) {
                this.updateState2();
                this.itemList3 = null;
                this.entity = null;
                return;
            }

            if (arrayList.isEmpty()) {
                return;
            }

            arrayList4 = null;
            entity3 = null;
            double offset2 = Double.POSITIVE_INFINITY;
            int index = 0;

            outerLoop:
            while (true) {
                if (index >= arrayList.size()) {
                    this.renderedPath = this.itemList3;
                    this.itemList3 = this.getArrayList3(arrayList4);
                    this.entity = entity3;
                    this.updateState2();
                    return;
                }

                ArrayList arrayList6 = (ArrayList)arrayList.get(index);
                if (!arrayList6.isEmpty()) {
                    double doubleEntry2 = ((PathNode)arrayList6.get(0)).getDouble();

                    while (true) {
                        if (doubleEntry2 < offset2) {
                            offset2 = doubleEntry2;
                            arrayList4 = arrayList6;
                            entity3 = (Entity)arrayList2.get(index);
                        }

                        do {
                            if (++index >= arrayList.size()) {
                                break outerLoop;
                            }

                            arrayList6 = (ArrayList)arrayList.get(index);
                        } while (arrayList6.isEmpty());

                        doubleEntry2 = ((PathNode)arrayList6.get(0)).getDouble();
                    }
                }

                index++;
            }
        }

        this.renderedPath = this.itemList3;
        this.itemList3 = this.getArrayList3(arrayList4);
        this.entity = entity3;
        this.updateState2();
    }

    private boolean checkEntity(Entity entity3, boolean enabled) {
        if (entity3 == minecraft.thePlayer) {
            return false;
        } else if (enabled) {
            if (RuntimeBridge.findPathfinderIndex() != -1 && !ClientGlobals.moduleManager.murdererFinder.entrySet.isEmpty()) {
                return ClientGlobals.moduleManager.murdererFinder.checkString(entity3.getText());
            } else if (!(entity3 instanceof EntityItem)) {
                return false;
            } else {
                EntityItem entityItem = (EntityItem)entity3;
                return entityItem.getItemStack() == null ? false : entityItem.getItemStack().getItem() == Items.goldIngot;
            }
        } else {
            return ChatUtil.checkEntity2(
            entity3,
            PathfinderTargetEntity.PLAYER.isSelected(),
            
            PathfinderTargetEntity.MOB.isSelected(),
            PathfinderTargetEntity.ANIMAL.isSelected(),
            PathfinderTargetEntity.VILLAGER.isSelected(),
            false,
            PathfinderTargetEntity.INVISIBLE.isSelected(),
            true,
            true,
            true,
            false,
            false,
            PathfinderTargetEntity.SLEEPING.isSelected(),
            0
            );
        }
    }
}
