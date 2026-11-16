package client.util;

import client.util.CombatUtil;
import client.util.HitBoxUtils;
import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class CombatRandomizer
implements MinecraftAccessor {
    public static int selectWeightedIndex(ArrayList arrayList, int n, double d, double d2) {
        double d3 = d2 / 20.0;
        int n2 = (Integer)arrayList.get(0);
        int n3 = CombatRandomizer.computeCount(arrayList.size(), d, d3);
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        double d4 = 0.0;
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            int n8 = (Integer)iterator.next();
            switch (n8) {
                case 0: {
                    ++n4;
                    break;
                }
                case 1: {
                    ++n5;
                    break;
                }
                case 2: {
                    ++n6;
                }
            }
            n7 += n8;
        }
        if (arrayList.size() > n) {
            switch (n2) {
                case 0: {
                    --n4;
                    break;
                }
                case 1: {
                    --n5;
                    break;
                }
                case 2: {
                    --n6;
                }
            }
            n7 -= n2;
            arrayList.remove(0);
        }
        double d5 = (double)n7 / (double)arrayList.size();
        Iterator iterator2 = arrayList.iterator();
        while (iterator2.hasNext()) {
            int n9 = (Integer)iterator2.next();
            d4 += Math.pow((double)n9 - d5, 2.0);
        }
        double d6 = Math.sqrt(d4 / (double)arrayList.size());
        ++n3;
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        HashMap<Integer, Double> hashMap = new HashMap<Integer, Double>();
        for (int i = 0; i < 3; ++i) {
            if (i == 0 && n4 >= n3) continue;
            switch (i) {
                case 0: {
                    ++n4;
                    break;
                }
                case 1: {
                    ++n5;
                    break;
                }
                case 2: {
                    ++n6;
                }
            }
            int n10 = n7 + i;
            arrayList.add(i);
            double d7 = (double)n10 / (double)arrayList.size();
            double d8 = 0.0;
            Iterator iterator3 = arrayList.iterator();
            while (iterator3.hasNext()) {
                int n11 = (Integer)iterator3.next();
                d8 += Math.pow((double)n11 - d7, 2.0);
            }
            double d9 = Math.sqrt(d8 / (double)arrayList.size());
            if (d7 <= d3 && d9 >= d) {
                arrayList2.add(i);
            }
            double d10 = d5 - d7;
            double d11 = d9 - d6;
            if (d5 > d3 && d6 < d) {
                if (d7 < d3) {
                    hashMap.put(i, (d10 + d11) * 2.0);
                } else {
                    hashMap.put(i, d10 + d11);
                }
            } else if (d5 > d3) {
                hashMap.put(i, d11);
            } else {
                hashMap.put(i, d10);
            }
            switch (i) {
                case 0: {
                    --n4;
                    break;
                }
                case 1: {
                    --n5;
                    break;
                }
                case 2: {
                    --n6;
                }
            }
            n7 = n10 - i;
            arrayList.remove(arrayList.size() - 1);
        }
        if (!arrayList2.isEmpty()) {
            ArrayList<Integer> arrayList3 = new ArrayList<Integer>();
            if (arrayList2.size() > 1) {
                for (int i = 0; i < arrayList2.size(); ++i) {
                    if ((Integer)arrayList2.get(i) != 0) continue;
                    arrayList2.remove(i);
                }
            }
            Iterator iterator4 = arrayList2.iterator();
            while (iterator4.hasNext()) {
                int n12 = (Integer)iterator4.next();
                if (n12 == 0 && arrayList2.size() != 1) continue;
                arrayList3.add(n12);
            }
            if (arrayList3.size() > 1) {
                Iterator iterator5 = arrayList3.iterator();
                while (iterator5.hasNext()) {
                    int n13 = (Integer)iterator5.next();
                    if (n2 == n13) continue;
                    return n13;
                }
            }
            return (Integer)arrayList3.get(0);
        }
        System.out.println("Try to find best number");
        double d12 = -1337.0;
        int n14 = 1;
        boolean bl = true;
        Iterator iterator6 = hashMap.keySet().iterator();
        while (iterator6.hasNext()) {
            int n15 = (Integer)iterator6.next();
            double d13 = (Double)hashMap.get(n15);
            if (!(d13 > 0.0) || !(d13 > d12)) continue;
            d12 = d13;
            n14 = n15;
            bl = false;
        }
        if (bl) {
            System.out.println("Fucking no number found!");
        }
        return n14;
    }

    public static boolean matchesEntity(Entity entity, float f) {
        Vec3 vec3 = CombatUtil.resolveVec3(true, entity, f).getVec312(0.0, MinecraftAccessor.minecraft.thePlayer.getHeight2(), 0.0);
        Vec3 vec32 = CombatUtil.resolveVec3(false, entity, f).getVec312(0.0, MinecraftAccessor.minecraft.thePlayer.getHeight2(), 0.0);
        float f2 = entity.getHeight();
        AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(CombatRandomizer.resolveVec3(entity), false).offset(f2, f2, f2);
        Vec3 vec33 = new Vec3(MathHelper.getDouble7(vec3.xCoord, axisAlignedBB.minX, axisAlignedBB.maxX), MathHelper.getDouble7(vec3.yCoord, axisAlignedBB.minY, axisAlignedBB.maxY), MathHelper.getDouble7(vec3.zCoord, axisAlignedBB.minZ, axisAlignedBB.maxZ));
        Vec3 vec34 = new Vec3(MathHelper.getDouble7(vec32.xCoord, axisAlignedBB.minX, axisAlignedBB.maxX), MathHelper.getDouble7(vec32.yCoord, axisAlignedBB.minY, axisAlignedBB.maxY), MathHelper.getDouble7(vec32.zCoord, axisAlignedBB.minZ, axisAlignedBB.maxZ));
        return vec3.getCoordinate4(vec33) >= vec32.getCoordinate4(vec34);
    }

    private static Vec3 resolveVec3(Entity entity) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            if (entityLivingBase.count22 > 0) {
                return new Vec3(entityLivingBase.posX + (entityLivingBase.value6 - entityLivingBase.posX) / (double)entityLivingBase.count22, entityLivingBase.posY + (entityLivingBase.value8 - entityLivingBase.posY) / (double)entityLivingBase.count22, entityLivingBase.posZ + (entityLivingBase.value15 - entityLivingBase.posZ) / (double)entityLivingBase.count22);
            }
            return entity.getVec32(1.0f);
        }
        return entity.getVec32(1.0f);
    }

    private static int computeCount(int n, double d, double d2) {
        double d3 = (double)n * (1.0 - d2 / 1.0);
        double d4 = (double)n / 2.0 * (1.0 - Math.sqrt(1.0 - 4.0 * d * d / 1.0));
        if (!Double.isFinite(d4)) {
            d4 = (double)n / 2.0;
        }
        return (int)Math.ceil(Math.max(d3, d4));
    }
}

