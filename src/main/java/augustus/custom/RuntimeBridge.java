package augustus.custom;

import augustus.modes.render.ItemEspOption;
import cc.baritone.pathing.BlockPathTracer;
import cc.baritone.pathing.PathPoint;
import cc.baritone.pathing.TracedBlockPath;
import client.gui.NotificationEntry;
import client.network.PingTracker;
import client.render.ObjModelMesh;
import client.render.WorldToScreen;
import client.setting.ItemEspOptionAccess;
import client.setting.ShaderOptions;
import client.util.ByteRateFormatter;
import client.util.CombatRandomizer;
import client.util.ContainerHelper;
import client.util.DecimalFormatter;
import client.util.MurderMysteryHelper;
import client.util.PositionHistory;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

/**
 * Strongly-typed, zero-reflection bridge to deobfuscated core client helpers.
 * All ZKM dummy key parameters have been eliminated.
 */
@SuppressWarnings("rawtypes")
public final class RuntimeBridge {

    private RuntimeBridge() {}

    // 1. ItemEspOption constructor
    public static Object createItemEspOption(String name, boolean enabled) {
        return new ItemEspOption(name, enabled, (ItemEspOptionAccess) null);
    }

    // 2. getNotificationDuration
    public static int getNotificationDuration(NotificationEntry entry) {
        return entry != null ? entry.getDuration() : 0;
    }

    public static int getDuration(NotificationEntry entry) {
        return getNotificationDuration(entry);
    }

    // 3. getNotificationStartTime
    public static long getNotificationStartTime(NotificationEntry entry) {
        return entry != null ? entry.getStartTime() : 0L;
    }

    public static long getStartTime(NotificationEntry entry) {
        return getNotificationStartTime(entry);
    }

    // 4. renderNotification
    public static void renderNotification(NotificationEntry entry, float partialTicks, ShaderOptions shaderOptions) {
        if (entry != null) {
            entry.renderNotification(partialTicks, shaderOptions);
        }
    }

    // 5. raytraceEntity
    public static boolean raytraceEntity(Entity entity, float range) {
        return CombatRandomizer.matchesEntity(entity, range);
    }

    public static boolean canHitTarget(Entity entity, float range) {
        return CombatRandomizer.matchesEntity(entity, range);
    }

    // 6. calculateClickDelay
    public static int calculateClickDelay(ArrayList arrayList, int weight, double offset, double offset2) {
        return CombatRandomizer.selectWeightedIndex(arrayList, weight, offset, offset2);
    }

    public static int selectWeightedIndex(ArrayList arrayList, int weight, double offset, double offset2) {
        return CombatRandomizer.selectWeightedIndex(arrayList, weight, offset, offset2);
    }

    // 7. traceBlockPath
    public static TracedBlockPath traceBlockPath(PathPoint start, PathPoint end, int limit) {
        return BlockPathTracer.traceBlockPath(start, end, limit);
    }

    // 8. renderCustomModel
    public static void renderCustomModel() {
        ObjModelMesh.renderMesh();
    }

    public static void renderMesh() {
        ObjModelMesh.renderMesh();
    }

    // 9. loadCustomModel
    public static void loadCustomModel(String mtl, String obj) {
        ObjModelMesh.loadObjModel(mtl, obj);
    }

    public static void loadObjModel(String mtl, String obj) {
        ObjModelMesh.loadObjModel(mtl, obj);
    }

    // 10. getSlotByKey (0-arg clean signature)
    public static int getSlotByKey() {
        return MurderMysteryHelper.getDetectiveEntityId();
    }

    public static int getDetectiveEntityId() {
        return MurderMysteryHelper.getDetectiveEntityId();
    }

    // 11. findPathfinderIndex
    public static int findPathfinderIndex() {
        return MurderMysteryHelper.getMurdererEntityId();
    }

    public static int getMurdererEntityId() {
        return MurderMysteryHelper.getMurdererEntityId();
    }

    public static int getMurdererEntityId(char character) {
        return MurderMysteryHelper.getMurdererEntityId();
    }

    // 12. canClickInventory
    public static boolean canClickInventory() {
        return ContainerHelper.isContainerActive();
    }

    public static boolean isContainerActive() {
        return ContainerHelper.isContainerActive();
    }

    // 13. predictTargetPoints
    public static ArrayList predictTargetPoints(EntityPlayerSP player, EntityLivingBase target, int start, int end) {
        return PositionHistory.resolveArrayList(player, target, start, end);
    }

    public static ArrayList sliceTrackedPositions(EntityPlayerSP player, EntityLivingBase target, int start, int end) {
        return PositionHistory.resolveArrayList(player, target, start, end);
    }

    // 14. getTrajectoryPoint
    public static Vec3 getTrajectoryPoint(ArrayList arrayList, int index) {
        return PositionHistory.resolveTrackedPosition(arrayList, index);
    }

    public static Vec3 resolveTrackedPosition(ArrayList arrayList, int index) {
        return PositionHistory.resolveTrackedPosition(arrayList, index);
    }

    // 15. calculateTrajectory
    public static ArrayList calculateTrajectory(EntityPlayerSP player, EntityLivingBase target, int p1, int p2, int p3) {
        return PositionHistory.resolveArrayList(player, target, p1, p2, p3, true, (byte) 52, 10230076);
    }

    public static ArrayList predictPositions(EntityPlayerSP player, EntityLivingBase target, int p1, int p2, int p3) {
        return PositionHistory.resolveArrayList(player, target, p1, p2, p3, true, (byte) 52, 10230076);
    }

    // 16. findTargetTrajectories
    public static ArrayList findTargetTrajectories(EntityPlayerSP player, int p1, int p2, int p3) {
        return PositionHistory.resolveArrayList(player, p1, p2, p3, 0);
    }

    public static ArrayList collectRecentPositions(EntityPlayerSP player, int p1, int p2, int p3) {
        return PositionHistory.resolveArrayList(player, p1, p2, p3, 0);
    }

    // 17. formatNumberString
    public static String formatNumberString(double val, int places) {
        return DecimalFormatter.formatDecimal(val, places);
    }

    public static String formatDecimal(double val, int places) {
        return DecimalFormatter.formatDecimal(val, places);
    }

    // 18. projectBoundingBox
    public static double[] projectBoundingBox(AxisAlignedBB box, ScaledResolution sr) {
        return WorldToScreen.resolveDoubleArray(box, sr);
    }

    // 19. projectPoint
    public static Vec3 projectPoint(double x, double y, double z, ScaledResolution sr) {
        return WorldToScreen.resolveVec3(x, y, z, sr);
    }

    // 20. calculatePingDelta
    public static int calculatePingDelta() {
        return PingTracker.getEstimatedPing();
    }

    public static int getEstimatedPing() {
        return PingTracker.getEstimatedPing();
    }

    // 21. formatTimeDelta
    public static String formatTimeDelta(long bytes) {
        return ByteRateFormatter.formatByteRate(bytes);
    }

    public static String formatByteRate(long bytes) {
        return ByteRateFormatter.formatByteRate(bytes);
    }
}
