package client.util;

import client.event.EventMove;
import client.render.BoundingBoxDimension;
import client.rotation.Rotation;
import client.setting.ArrowDodgerPhase;
import client.util.ArrayListData;
import client.util.BacktrackRecord;
import client.util.CombatTargetFilter;
import client.util.CombatUtil;
import client.util.HitBoxUtils;
import client.util.MinecraftAccessor;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import client.util.RecordNode16Helper;
import client.util.SimulationSnapshot;
import client.util.TrajectoryPredictor;
import client.util.Vec3Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.GoldenSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class GenericFloatEntry
implements MinecraftAccessor {
    public float factor3 = 0.0f;
    public float factor = 0.0f;
    public boolean enabled = false;
    public int count10 = 0;
    public EntityLivingBase entityLivingBase = null;
    public float factor4 = 0.025f;
    public static final int count3 = 5;
    public float factor2 = 0.025f;
    public int count2 = 0;
    public int count7 = 3;
    public double value = 0.5;
    public int count11 = 0;
    public int count5 = 4;
    public double[] valueArray3;
    public int count = 0;
    public double[] valueArray2;
    public double value7 = 0.0;
    public double value3 = 0.0;
    public double value12;
    public boolean enabled5 = true;
    public GoldenSword goldenSword;
    public double[] valueArray;
    public double value2 = 0.0;
    public static final int count4 = 3;
    public double value6 = 3.0;
    public double value9 = 0.0;
    public double value4 = 2.0;
    public double value11 = 30.0;
    public static final int count6 = 13;
    public int count9 = 0;
    public double value10 = 0.0;
    public boolean enabled4 = true;
    public double value8 = 15.0;
    public boolean enabled3 = false;
    public int count8 = 2;
    public double value5 = 8.0;

    private GoldenSword resolveGoldenSwordByGoldenSword(GoldenSword goldenSword, EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase, Vec3 vec3, Vec3 vec32, double[] dArray, double[] dArray2) {
        double[] dArray3 = dArray;
        double[] dArray4 = dArray2;
        SimulationSnapshot simulationSnapshot = goldenSword.getSimulationSnapshot();
        double d = goldenSword.getValue2();
        double d2 = goldenSword.getValue3();
        double d3 = 1.0;
        double d4 = this.factor4 > 0.0f ? PlayerUtil.getDouble(0.5, Math.abs(RecordNode16Helper.computeFactor(this.factor3, 1.1f)), 0.5) : 0.5;
        double d5 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(dArray4, d4));
        double d6 = this.factor4 > 0.0f ? PlayerUtil.getDouble(0.5, Math.abs(RecordNode16Helper.computeFactor(this.factor3 - this.factor4, 1.1f)), 0.5) : 0.5;
        double d7 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(dArray3, d6));
        double d8 = HitBoxUtils.getDouble(d7, d5) + this.value10;
        double d9 = Math.abs(Math.sin((double)this.factor / Math.PI * 2.0 / 0.1));
        double d10 = PlayerUtil.getDouble(this.value12, 100.0 + (100.0 - this.value12), d9) / 100.0;
        double d11 = d8 * d10;
        boolean bl = goldenSword.isEnabled();
        double d12 = goldenSword.getValue();
        double d13 = TrajectoryPredictor.computeValue2(dArray4[0], dArray4[1], d);
        double d14 = this.value;
        double d15 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(dArray4, d14));
        double d16 = HitBoxUtils.getDouble(d, d15);
        if (d13 < 0.0 || d13 > 1.0) {
            if (bl) {
                this.value = ThreadLocalRandom.current().nextDouble(0.4, 0.6);
            }
            bl = false;
            this.value10 = 0.0;
        }
        if (Math.signum(d16) != Math.signum(d12)) {
            bl = true;
        }
        this.factor += this.factor2;
        for (int i = 0; i < 3; ++i) {
            double d17;
            double d18;
            double d19;
            double d20;
            double d21;
            double d22;
            double d23;
            Vec3 vec33;
            Vec3 vec34;
            EntityLivingBase entityLivingBase2;
            EntityPlayerSP entityPlayerSP2;
            double d24;
            double d25 = this.value;
            double d26 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(dArray4, d25));
            double d27 = this.value3;
            d12 = d24 = HitBoxUtils.getDouble(d, d26);
            if (d13 < 0.0 || d13 > 1.0) {
                if (bl) {
                    this.value = ThreadLocalRandom.current().nextDouble(0.4, 0.6);
                }
                bl = false;
                this.value10 = 0.0;
            }
            if (bl) {
                d24 = d11 / 3.0;
            }
            double d28 = d27 - d2;
            if (this.enabled5 && this.matchesEntityPlayerSPByEntityPlayerSP(entityPlayerSP2 = entityPlayerSP, entityLivingBase2 = entityLivingBase, vec34 = vec3, vec33 = vec32, d23 = d, d22 = d24, d21 = simulationSnapshot.value)) {
                dArray4 = this.valueArray;
                dArray3 = this.valueArray3;
                double d29 = this.factor4 > 0.0f ? PlayerUtil.getDouble(0.5, Math.abs(RecordNode16Helper.computeFactor(this.factor3, 1.1f)), 0.5) : 0.5;
                double d30 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(dArray4, d29));
                double d31 = this.factor4 > 0.0f ? PlayerUtil.getDouble(0.5, Math.abs(RecordNode16Helper.computeFactor(this.factor3 - this.factor4, 1.1f)), 0.5) : 0.5;
                double d32 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(dArray3, d31));
                d11 = HitBoxUtils.getDouble(d32, d30) + this.value10;
                double d33 = this.value;
                double d34 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(dArray4, d33));
                d24 = HitBoxUtils.getDouble(d, d34);
                simulationSnapshot.value /= Math.random() + 4.0;
                bl = false;
            }
            SimulationSnapshot simulationSnapshot2 = new SimulationSnapshot(d24 / 0.15, d28 / 0.15);
            double d35 = Math.signum(simulationSnapshot2.value) != Math.signum(simulationSnapshot.value) ? this.value5 * this.value6 : this.value5;
            double d36 = Math.signum(simulationSnapshot2.value2) != Math.signum(simulationSnapshot.value2) ? this.value5 * this.value6 : this.value5;
            ThreadLocalRandom.current().nextDouble(0.7, 1.0);
            ThreadLocalRandom.current().nextDouble(0.7, 1.0);
            Math.abs(this.value9 / 0.15 / 3.0);
            Math.signum(this.value9);
            Math.signum(simulationSnapshot2.value);
            double d37 = simulationSnapshot2.sqrt();
            double d38 = 1.0;
            double d39 = 1.0;
            if (Math.random() < 0.9 && simulationSnapshot.sqrt() < 13.333333333333334 && simulationSnapshot.computeValueBySimulationSnapsh(simulationSnapshot2) < 70.0) {
                this.value5 = Math.min(this.value5, d37) / 5.0;
            }
            double d40 = new SimulationSnapshot(d24 / 0.15 / d39, d28 / 0.15 / d38).sqrt();
            SimulationSnapshot simulationSnapshot3 = new SimulationSnapshot((simulationSnapshot2.value - simulationSnapshot.value) * Math.min(d40, this.value5) / d40, (simulationSnapshot2.value2 - simulationSnapshot.value2) * Math.min(d37, this.value5) / d37 / d38);
            if (Double.isNaN(simulationSnapshot3.value) || Double.isNaN(simulationSnapshot3.value2)) {
                double d41 = 0.01;
                simulationSnapshot3 = new SimulationSnapshot(Math.random(), Math.random()).resolveSimulationSnapshotByDouble(d41);
            }
            if (Math.signum(simulationSnapshot.value) == Math.signum(simulationSnapshot3.value) && -0.5 * ((d20 = Math.abs(simulationSnapshot.value)) * d20 / ((d19 = Math.abs(simulationSnapshot3.value)) * d19)) + d20 * d20 / d19 > Math.abs(simulationSnapshot2.value)) {
                simulationSnapshot3.value *= -1.0;
            }
            if (Math.signum(simulationSnapshot.value2) == Math.signum(simulationSnapshot3.value2) && -0.5 * ((d18 = Math.abs(simulationSnapshot.value2)) * d18 / ((d17 = Math.abs(simulationSnapshot3.value2)) * d17)) + d18 * d18 / d17 > Math.abs(simulationSnapshot2.value2)) {
                simulationSnapshot3.value2 *= -1.0;
            }
            SimulationSnapshot simulationSnapshot4 = new SimulationSnapshot(simulationSnapshot.value, simulationSnapshot.value2).resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot3);
            double d42 = 1.0 - d3;
            SimulationSnapshot simulationSnapshot5 = simulationSnapshot4.resolveSimulationSnapshotByDouble(d3);
            simulationSnapshot = simulationSnapshot.resolveSimulationSnapshotByDouble(d42).resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot5);
            d = MathHelper.getDouble4(d + simulationSnapshot.value * 0.15 * d3);
            d2 = MathHelper.getDouble7(d2 + simulationSnapshot.value2 * 0.15 * d3, -90.0, 90.0);
        }
        GoldenSword goldenSword2 = new GoldenSword(vec3, vec32, this.goldenSword.getSimulationSnapshot(), simulationSnapshot, d, d2);
        goldenSword2.setEnabled2(false);
        goldenSword2.setEnabled(bl);
        goldenSword2.setValue(d12);
        this.value10 = 0.0;
        if (bl) {
            this.value10 = d11 - HitBoxUtils.getDouble(this.goldenSword.getValue2(), d);
        }
        return goldenSword2;
    }

    private void applyEntityPlayerSP(EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase, boolean bl, boolean bl2) {
        long l = 0xdb41e3614bfL;
        long l2 = 0x23F10CB71B56L ^ l;
        int n = (int)((l2 ^ 0x7EE548A24886L) >>> 48);
        long l3 = l2 ^ 0x3C032E06C952L;
        long l4 = l2 ^ 0x5D77051786F8L;
        if (bl2 || entityPlayerSP.backtrackRecord4.getInt() < this.count5 + this.count7 + 1 || entityLivingBase.backtrackRecord.getInt() < this.count5 + this.count7 + 1) {
            AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(entityLivingBase.getVec32(1.0f), true);
            this.valueArray = TrajectoryPredictor.getCoordOffsetArray2(entityPlayerSP.getVec35(1.0f), axisAlignedBB);
            double d = Double.NaN;
            double d2 = this.value2;
            AxisAlignedBB axisAlignedBB2 = HitBoxUtils.getAxisAlignedBB(entityLivingBase.getVec32(0.0f), true);
            this.valueArray3 = TrajectoryPredictor.resolveDoubleArray(entityPlayerSP.getVec35(0.0f), axisAlignedBB2, d2, d);
            return;
        }
        this.valueArray3 = this.valueArray;
        int n2 = entityPlayerSP.ticksExisted - this.count11;
        EntityPlayerSP entityPlayerSP2 = entityPlayerSP;
        Vec3 vec3 = this.resolveVec3(entityPlayerSP2, n2, 0);
        int n3 = this.count11;
        int n4 = 0;
        EntityLivingBase entityLivingBase2 = entityLivingBase;
        Vec3 vec32 = this.resolveVec3(entityLivingBase2, n4, n3);
        AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(entityLivingBase.getVec32(1.0f), true);
        TrajectoryPredictor.getCoordOffsetArray2(vec3, axisAlignedBB);
        short shortValue = (short)n;
        double[] dArray = this.resolveDoubleArrayByEntityPlayerSP(shortValue, entityPlayerSP, entityLivingBase, 0);
        short shortValue2 = (short)n;
        double[] dArray2 = this.resolveDoubleArrayByEntityPlayerSP(shortValue2, entityPlayerSP, entityLivingBase, 1);
        double d = Double.NaN;
        double d3 = this.value2;
        AxisAlignedBB axisAlignedBB3 = HitBoxUtils.getAxisAlignedBB(vec32, true);
        double[] dArray3 = TrajectoryPredictor.resolveDoubleArray(vec3, axisAlignedBB3, d3, d);
        double d4 = dArray[3] + dArray[4];
        double d5 = -dArray[3] + dArray[4];
        double d6 = dArray2[3] + dArray2[4];
        double d7 = -dArray2[3] + dArray2[4];
        double d8 = this.valueArray != null ? this.valueArray[0] : d4;
        double d9 = this.valueArray != null ? this.valueArray[1] : d6;
        ArrayList<double[]> arrayList = new ArrayList<double[]>();
        ArrayList<double[]> arrayList2 = new ArrayList<double[]>();
        arrayList.add(new double[]{d4, dArray[0], dArray[1], dArray[2]});
        arrayList.add(new double[]{d5, -dArray[0], -dArray[1], dArray[2]});
        arrayList.add(new double[]{d8, 0.0, d4, 0.0});
        arrayList2.add(new double[]{d6, dArray2[0], dArray2[1], dArray2[2]});
        arrayList2.add(new double[]{d7, -dArray2[0], -dArray2[1], dArray2[2]});
        arrayList2.add(new double[]{d9, 0.0, d6, 0.0});
        double[] dArray4 = null;
        double[] dArray5 = null;
        double d10 = Double.MAX_VALUE;
        double d11 = this.factor4 > 0.0f ? PlayerUtil.getDouble(0.5, Math.abs(RecordNode16Helper.computeFactor(this.factor3, 1.1f)), 0.5) : 0.5;
        double d12 = this.factor4 > 0.0f ? PlayerUtil.getDouble(0.5, Math.abs(RecordNode16Helper.computeFactor(this.factor3 - this.factor4, 1.1f)), 0.5) : 0.5;
        double d13 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(dArray3, d11));
        double d14 = this.valueArray3 == null ? this.goldenSword.getValue2() : MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(this.valueArray3, d12));
        double d15 = HitBoxUtils.getDouble(d14, d13);
        for (double[] dArray6 : arrayList) {
            Iterator iterator = arrayList2.iterator();
            while (iterator.hasNext()) {
                double[] dArray7;
                double d16;
                double d17 = this.valueArray3 == null ? this.goldenSword.getValue2() : MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(this.valueArray3, d12));
                double d18 = Math.abs(HitBoxUtils.getDouble(d17, d16 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(new double[]{dArray6[0], (dArray7 = (double[])iterator.next())[0]}, d11))) - d15);
                if (!(d10 > d18) && dArray5 != null) continue;
                d10 = d18;
                dArray4 = dArray6;
                dArray5 = dArray7;
            }
        }
        this.valueArray = new double[]{dArray4[0], dArray5[0]};
        this.value9 = (dArray4[1] + dArray5[1]) / 2.0;
        if (bl || this.valueArray3 == null) {
            this.valueArray3 = this.valueArray;
        }
    }

    public Rotation resolveRotation(short shortValue, EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP, char c, int n, float f, float f2, double d, double d2, Vec3 vec3, Vec3 vec32) {
        float f3 = f;
        int n2 = 0;
        int n3 = 6580;
        int n4 = 1845808943;
        long l = ((long)n2 << 48 | (long)n3 << 48 >>> 16 | (long)n4 << 32 >>> 32) ^ 0x23F10CB71B56L;
        long l2 = l ^ 0x744272E93C1BL;
        long l3 = l ^ 0x497775255D68L;
        f3 = MathHelper.sqrt_float(f3);
        if (this.enabled && this.entityLivingBase != entityLivingBase) {
            this.count11 = this.count;
            this.enabled = false;
        }
        boolean bl = false;
        if (this.count9 != entityPlayerSP.ticksExisted - 1) {
            this.count = this.count11 = entityPlayerSP.ticksExisted - this.count8;
            this.enabled = false;
            this.value10 = 0.0;
            bl = true;
        }
        if (entityPlayerSP.ticksExisted - (this.enabled3 ? this.count : this.count11) > this.count5 || entityPlayerSP.ticksExisted < this.count11) {
            this.count = this.count11 = entityPlayerSP.ticksExisted - this.count8;
            this.enabled = false;
        }
        if (Math.random() < this.value7) {
            int n5 = this.count;
            int n6 = 0;
            EntityLivingBase entityLivingBase2 = entityLivingBase;
            int n7 = entityPlayerSP.ticksExisted - this.count;
            EntityPlayerSP entityPlayerSP2 = entityPlayerSP;
            Vec3 vec33 = this.resolveVec3(entityLivingBase2, n6, n5).getVec311(this.resolveVec3(entityPlayerSP2, n7, 0));
            Vec3 vec34 = entityLivingBase.getVec32(1.0f).getVec311(entityPlayerSP.getVec32(1.0f));
            double d3 = Math.abs(HitBoxUtils.getDouble(MathHelper.getDouble4(Math.atan2(vec33.zCoord, vec33.xCoord) / Math.PI * 180.0 - 90.0), MathHelper.getDouble4(Math.atan2(vec34.zCoord, vec34.xCoord) / Math.PI * 180.0 - 90.0)));
            double d4 = vec33.getCoordinate4(vec34);
            if (d3 < 10.0 && d4 < 2.0 || d3 < 1.0) {
                this.enabled = true;
                this.count11 = Math.max(entityPlayerSP.ticksExisted - this.count10, this.count11);
                if (entityPlayerSP.ticksExisted - this.count > this.count5 || entityPlayerSP.ticksExisted < this.count) {
                    this.count = this.count11 - this.count8;
                }
            }
        }
        if (this.goldenSword == null) {
            this.goldenSword = new GoldenSword(entityPlayerSP.getVec35(1.0f), entityLivingBase.getVec32(1.0f), SimulationSnapshot.simulationSnapshot, SimulationSnapshot.simulationSnapshot, f3, f2);
            this.value10 = 0.0;
        } else {
            double d5 = f2;
            double d6 = f3;
            this.goldenSword.applyDouble(d6, d5);
        }
        if (this.count9 != entityPlayerSP.ticksExisted - 1) {
            SimulationSnapshot simulationSnapshot = new SimulationSnapshot(HitBoxUtils.getDouble(MathHelper.getDouble4(MinecraftAccessor.minecraft.thePlayer.scaleFactor30), MathHelper.getDouble4(MinecraftAccessor.minecraft.thePlayer.scaleFactor11)) / 3.0 / 0.15, (double)((MinecraftAccessor.minecraft.thePlayer.scaleFactor27 - MinecraftAccessor.minecraft.thePlayer.scaleFactor20) / 3.0f) / 0.15);
            this.goldenSword.setSimulationSnapshot(simulationSnapshot);
        }
        this.applyEntityPlayerSP(entityPlayerSP, entityLivingBase, bl, false);
        this.count9 = entityPlayerSP.ticksExisted;
        double d7 = f2;
        EntityLivingBase entityLivingBase3 = entityLivingBase;
        EntityPlayerSP entityPlayerSP3 = entityPlayerSP;
        this.applyEntityPlayerSP(entityPlayerSP3, entityLivingBase3, d7);
        Vec3 vec35 = entityPlayerSP.getVec35(1.0f);
        Vec3 vec36 = entityLivingBase.getVec32(1.0f);
        this.factor3 += this.factor4;
        double[] dArray = this.valueArray;
        double[] dArray2 = this.valueArray3;
        Vec3 vec37 = vec36;
        Vec3 vec38 = vec35;
        EntityLivingBase entityLivingBase4 = entityLivingBase;
        EntityPlayerSP entityPlayerSP4 = entityPlayerSP;
        GoldenSword goldenSword = this.goldenSword;
        GoldenSword goldenSword2 = this.resolveGoldenSwordByGoldenSword(goldenSword, entityPlayerSP4, entityLivingBase4, vec38, vec37, dArray2, dArray);
        int n8 = entityPlayerSP.ticksExisted;
        double[] dArray3 = this.valueArray;
        Vec3 vec39 = vec35;
        Vec3 vec310 = vec36;
        Vec3 vec311 = vec3;
        GoldenSword goldenSword3 = goldenSword2;
        GoldenSword goldenSword4 = this.goldenSword;
        this.applyGoldenSword(goldenSword4, goldenSword3, vec311, vec310, vec39, dArray3, n8);
        this.goldenSword = goldenSword2;
        this.entityLivingBase = entityLivingBase;
        ++this.count2;
        return new Rotation(MathHelper.sqrt_float((float)(this.goldenSword.getValue2() + (this.enabled4 ? ThreadLocalRandom.current().nextDouble(-2.0, 2.0) : 0.0))), (float)(this.goldenSword.getValue3() + (this.enabled4 ? ThreadLocalRandom.current().nextDouble(-2.0, 2.0) : 0.0)));
    }

    private Vec3 resolveVec3(Vec3 vec3, Vec3 vec32, double d, boolean bl) {
        Vec3 vec33;
        Vec3 vec34 = vec3.getVec311(vec32);
        double d2 = vec34.lengthVector();
        if (d2 < d) {
            vec33 = new Vec3(0.0, 0.0, 0.0);
        } else {
            vec33 = vec34.getVec3(d / d2);
            if (bl) {
                RayTraceUtil.applyString("Limit from: " + d2 + " to: " + vec33.lengthVector());
            }
        }
        return vec3.getVec311(vec33);
    }

    private GoldenSword resolveGoldenSword(GoldenSword goldenSword, GoldenSword goldenSword2, GoldenSword goldenSword3) {
        double d = Math.abs(HitBoxUtils.getDouble(goldenSword.getValue2(), goldenSword2.getValue2()));
        if (Math.abs(HitBoxUtils.getDouble(goldenSword.getValue2(), goldenSword3.getValue2())) > d) {
            return goldenSword2;
        }
        return goldenSword3;
    }

    private void applyEntityPlayerSP(EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase, double d) {
        long l = 0x4e07105ae862L;
        long l2 = 0x23F10CB71B56L ^ l ^ 0x6807E864B33FL;
        EventMove eventMove = new EventMove(entityPlayerSP.secondaryFloatEntry.moveStrafe, entityPlayerSP.secondaryFloatEntry.moveForward, entityPlayerSP.secondaryFloatEntry.state, entityPlayerSP.secondaryFloatEntry.state2, ArrowDodgerPhase.PRE);
        double d2 = entityPlayerSP.posZ;
        double d3 = entityPlayerSP.posY;
        double d4 = entityPlayerSP.posX;
        ArrayList arrayList = CombatUtil.onMove(5, entityPlayerSP, d4, d3, d2, eventMove);
        int n = arrayList.size();
        ArrayList arrayList2 = CombatTargetFilter.resolveArrayListByEntityLivingBase(entityLivingBase, n);
        arrayList.add(entityPlayerSP.getVec32(1.0f));
        arrayList2.add(entityLivingBase.getVec32(1.0f));
        int n2 = Math.min(arrayList.size(), arrayList2.size());
        double[][] dArrayArray = new double[n2][];
        ArrayList<Double> arrayList3 = new ArrayList<Double>();
        for (int i = 0; i < n2; ++i) {
            AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB((Vec3)arrayList2.get(i), false);
            double[] dArray = TrajectoryPredictor.getCoordOffsetArrayByVec3(((Vec3)arrayList.get(i)).getVec312(0.0, entityPlayerSP.getHeight2(), 0.0), axisAlignedBB);
            dArrayArray[i] = dArray;
            arrayList3.add(dArray[0]);
            arrayList3.add(dArray[1]);
        }
        double d5 = d;
        double d6 = d;
        double d7 = 0.0;
        int n3 = -1;
        int n4 = 0;
        for (int i = 0; i < arrayList3.size(); ++i) {
            double d8 = (Double)arrayList3.get(i);
            int n5 = 0;
            int n6 = dArrayArray.length;
            for (int j = 0; j < n6; ++j) {
                double[] dArray = dArrayArray[j];
                if (!(d8 >= dArray[0]) || !(d8 <= dArray[1])) continue;
                ++n5;
            }
            if (n5 <= n3 && (n5 != n3 || !(Math.abs(d - d8) < d7))) continue;
            d5 = d8;
            d7 = Math.abs(d - d8);
            n3 = n5;
            n4 = i;
        }
        double d9 = 0.0;
        int n7 = -1;
        for (int i = 0; i < arrayList3.size(); ++i) {
            double d10;
            if (i == n4 || (d10 = ((Double)arrayList3.get(i)).doubleValue()) == d5) continue;
            int n8 = 0;
            for (double[] dArray : dArrayArray) {
                if (!(d10 >= dArray[0]) || !(d10 <= dArray[1])) continue;
                ++n8;
            }
            if (n8 <= n7 && (n8 != n7 || !(Math.abs(d5 - d10) < d9))) continue;
            d6 = d10;
            d9 = Math.abs(d5 - d10);
            n7 = n8;
            n4 = i;
        }
        this.valueArray2 = new double[]{Math.min(d5, d6), Math.max(d5, d6)};
        this.value3 = MathHelper.getDouble7(d, this.valueArray2[0], this.valueArray2[1]);
    }

    private Vec3 resolveVec3(EntityLivingBase entityLivingBase, BoundingBoxDimension boundingBoxDimension) {
        ArrayList<BoundingBoxDimension> arrayList = new ArrayList<BoundingBoxDimension>();
        arrayList.add(boundingBoxDimension);
        double[] dArray = CombatUtil.resolveDoubleArray((EntityPlayerSP)entityLivingBase, arrayList, 0, false);
        return new Vec3(dArray[0], dArray[1], dArray[2]);
    }

    public Vec3 resolveVec3(EntityLivingBase entityLivingBase, int n, int n2, int n3) {
        Vec3Entry vec3Entry;
        int n4 = n;
        long l = 0xeb34faf3bfcL;
        long l2 = 0x23F10CB71B56L ^ l ^ 0x2F6176EB3FD8L;
        if (entityLivingBase == MinecraftAccessor.minecraft.thePlayer) {
            if (n4 == 0) {
                return entityLivingBase.getVec32(1.0f);
            }
            int n5 = entityLivingBase.backtrackRecord4.getInt() - n4 - 1;
            if (n5 >= entityLivingBase.backtrackRecord4.getInt() || n5 < 0) {
                return entityLivingBase.getVec32(1.0f);
            }
            ArrayList<Object> arrayList = new ArrayList<Object>();
            while (n5 < entityLivingBase.backtrackRecord4.getInt()) {
                arrayList.add(entityLivingBase.backtrackRecord4.getObject(n5));
                ++n5;
            }
            int n6 = n3 - ((BoundingBoxDimension)arrayList.get((int)0)).indexOffset;
            ArrayList<Object> arrayList2 = arrayList;
            double[] dArray = CombatUtil.resolveDoubleArray((EntityPlayerSP)entityLivingBase, arrayList2, n6, false);
            return new Vec3(dArray[0], dArray[1], dArray[2]);
        }
        BacktrackRecord backtrackRecord = entityLivingBase.backtrackRecord3;
        if (backtrackRecord.getItemList().isEmpty()) {
            return entityLivingBase.getVec32(1.0f);
        }
        n4 = MinecraftAccessor.minecraft.thePlayer.ticksExisted - n2;
        if (n4 == 0) {
            return entityLivingBase.getVec32(1.0f);
        }
        if (!backtrackRecord.canExecute()) {
            vec3Entry = (Vec3Entry)backtrackRecord.getItemList().get(backtrackRecord.getItemList().size() - 1);
        } else {
            int n7 = 13 - n4 - 1;
            vec3Entry = (Vec3Entry)backtrackRecord.getObject(n7);
        }
        int n8 = n3 - n2;
        Vec3Entry vec3Entry2 = vec3Entry;
        return CombatTargetFilter.resolveVecByEntityLivingBase(entityLivingBase, vec3Entry2, n8);
    }

    public Vec3 resolveVec3(EntityLivingBase entityLivingBase, int n, int n2) {
        int n3 = MinecraftAccessor.minecraft.thePlayer.ticksExisted;
        int n4 = n2;
        int n5 = n;
        EntityLivingBase entityLivingBase2 = entityLivingBase;
        return this.resolveVec3(entityLivingBase2, n5, n4, n3);
    }

    private void applyGoldenSword(GoldenSword goldenSword, GoldenSword goldenSword2, Vec3 vec3, Vec3 vec32, Vec3 vec33, double[] dArray, int n) {
        double d = Math.abs(HitBoxUtils.getDouble(goldenSword.getValue2(), goldenSword2.getValue2()));
        double d2 = Math.abs(goldenSword.getValue3() - goldenSword2.getValue3());
        if ((d2 > 0.075 || d > 0.075) && (d < 0.075 || d2 < 0.075 || d / d2 < 0.1 || d2 / d < 0.1) && vec3.getValue() > 0.0) {
            AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec32, true);
            double[] dArray2 = this.valueArray2;
            double[] dArray3 = TrajectoryPredictor.getCoordOffsetArrayByVec3(MinecraftAccessor.minecraft.thePlayer.getVec35(1.0f), axisAlignedBB);
            if (Math.abs(dArray2[0] - dArray2[1]) < 4.0 && Math.abs(dArray3[0] - dArray3[1]) > Math.abs(dArray2[0] - dArray2[1])) {
                dArray2 = dArray3;
            }
            double d3 = goldenSword2.getValue2();
            double d4 = goldenSword2.getValue3();
            double d5 = TrajectoryPredictor.computeValue2(dArray[0], dArray[1], d3);
            double d6 = TrajectoryPredictor.computeValue(dArray2, d4);
            if (d5 >= 0.0 && d5 <= 1.0) {
                double d7 = PlayerUtil.getDouble(d5, Math.abs(RecordNode16Helper.computeFactor((float)n / 20.0f, -1.0f)), (double)Math.abs(RecordNode16Helper.computeFactor(3.0f, (float)n / 20.0f)) * 0.15);
                d3 = MathHelper.getDouble4(TrajectoryPredictor.getCoordinate(dArray, d7));
            }
            if (d6 >= 0.0 && d6 <= 1.0) {
                double d8 = PlayerUtil.getDouble(d6, Math.abs(RecordNode16Helper.computeFactor((float)n / 20.0f, -5.0f)), (double)Math.abs(RecordNode16Helper.computeFactor(3.0f, (float)n / 10.0f)) * 0.5 + 0.1);
                d4 = MathHelper.getDouble7(TrajectoryPredictor.getCoordinate(dArray2, d8), -90.0, 90.0);
            }
            double d9 = Math.abs(RecordNode16Helper.computeFactor((float)MinecraftAccessor.minecraft.thePlayer.ticksExisted / 20.0f, 1.0f)) + 2.0f;
            double d10 = MathHelper.getDouble7(HitBoxUtils.getDouble(goldenSword2.getValue2(), d3), -d9, d9);
            double d11 = goldenSword2.getValue2() + d10;
            goldenSword2.applyDouble(d11, d4);
        }
    }

    private boolean matchesEntityPlayerSPByEntityPlayerSP(EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase, Vec3 vec3, Vec3 vec32, double d, double d2, double d3) {
        AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec32, true);
        double[] dArray = TrajectoryPredictor.getCoordOffsetArray2(vec3, axisAlignedBB);
        dArray[0] = MathHelper.getDouble4(dArray[0]);
        dArray[1] = MathHelper.getDouble4(dArray[1]);
        double d4 = TrajectoryPredictor.getCoordinate(axisAlignedBB, vec3, d);
        double d5 = HitBoxUtils.getDouble(d, dArray[0]);
        double d6 = HitBoxUtils.getDouble(d, dArray[1]);
        double d7 = Math.signum(d5);
        double d8 = dArray[0];
        if (Math.abs(d6) < Math.abs(d5)) {
            d5 = d6;
            d7 = Math.signum(d6);
            d8 = dArray[1];
        }
        if (d5 > this.value8 && TrajectoryPredictor.getDouble4(MathHelper.getDouble4(d + d2), d8) > this.value11 && Math.signum(d2) == Math.signum(d3) && Math.signum(d2) != d7 && (d4 < 0.0 || d4 > 1.0)) {
            this.count11 = MinecraftAccessor.minecraft.thePlayer.ticksExisted;
            this.count = MinecraftAccessor.minecraft.thePlayer.ticksExisted;
            this.applyEntityPlayerSP(entityPlayerSP, entityLivingBase, false, false);
            return true;
        }
        return false;
    }

    private Vec3 resolveVecByEntityLivingBase(EntityLivingBase entityLivingBase, float f, int n, boolean bl, int n2) {
        int n3 = this.count11;
        int n4 = n;
        EntityLivingBase entityLivingBase2 = entityLivingBase;
        Vec3 vec3 = this.resolveVec3(entityLivingBase2, n4, n3);
        Vec3 vec32 = new Vec3(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ);
        if (vec3 == null) {
            vec3 = vec32;
        }
        return vec3;
    }

    private double[] resolveDoubleArrayByEntityPlayerSP(short shortValue, EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase, int n) {
        int n2 = 0;
        long l = 0x50a05a23476fL;
        long l2 = ((long)n2 << 48 | l << 16 >>> 16) ^ 0x23F10CB71B56L ^ 0x1E086E28C7B8L;
        ArrayListData arrayListData = new ArrayListData(this.count7);
        double d = Double.NaN;
        double d2 = 0.0;
        double d3 = 0.0;
        int n3 = entityPlayerSP.ticksExisted - this.count11;
        int n4 = 0;
        for (int i = this.count7; i > 0; --i) {
            int n5 = i + n3;
            BoundingBoxDimension boundingBoxDimension = (BoundingBoxDimension)entityPlayerSP.backtrackRecord4.getObject3(n5);
            Vec3 vec3 = boundingBoxDimension.positionVec2;
            int n6 = i + n3;
            Vec3 vec32 = (Vec3)entityLivingBase.backtrackRecord.getObject3(n6);
            if (this.enabled && this.count < boundingBoxDimension.indexOffset) {
                int n7 = boundingBoxDimension.indexOffset - 1;
                int n8 = 0;
                int n9 = entityPlayerSP.ticksExisted - this.count;
                EntityPlayerSP entityPlayerSP2 = entityPlayerSP;
                vec3 = this.resolveVec3(entityPlayerSP2, n9, n8, n7);
                int n10 = boundingBoxDimension.indexOffset;
                int n11 = this.count;
                int n12 = 0;
                EntityLivingBase entityLivingBase2 = entityLivingBase;
                vec32 = this.resolveVec3(entityLivingBase2, n12, n11, n10);
            }
            AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(vec32, true);
            double[] dArray = TrajectoryPredictor.getCoordOffsetArray2(vec3, axisAlignedBB);
            if (Double.isNaN(d)) {
                d = dArray[n];
                double d4 = 0.0;
                arrayListData.processAction(d4);
                d2 = d;
                n4 = ((BoundingBoxDimension)entityPlayerSP.backtrackRecord4.getObject3((int)0)).indexOffset - boundingBoxDimension.indexOffset;
                continue;
            }
            d3 = HitBoxUtils.getDouble(MathHelper.getDouble4(d2), MathHelper.getDouble4(dArray[n])) + d3;
            arrayListData.processAction(d3);
            d2 = dArray[n];
        }
        double[] dArray = arrayListData.getDoubleArray();
        double d5 = dArray[0] * (double)n4 + dArray[1];
        return new double[]{dArray[0], dArray[1], n4, d5, d};
    }
}

