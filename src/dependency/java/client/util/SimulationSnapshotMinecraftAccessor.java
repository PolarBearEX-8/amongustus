package client.util;

import client.ClientGlobals;
import client.util.ArrayListRecord;
import client.util.MinecraftAccessor;
import client.util.SimulationSnapshot;
import client.util.SimulationSnapshotController;
import client.util.SimulationSnapshotRecord;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.util.MathHelper;

public class SimulationSnapshotMinecraftAccessor
implements MinecraftAccessor {
    public double value = 8.0;
    public double value2 = 0.0;
    public double value3 = 1.0;
    public double value4 = 0.0;

    public SimulationSnapshot resolveSimulationSnapshot(SimulationSnapshotController simulationSnapshotController) {
        this.value = 8.0;
        SimulationSnapshot simulationSnapshot = null;
        for (int i = 0; i < 3; ++i) {
            double d = this.value * ThreadLocalRandom.current().nextDouble(0.8, 1.0);
            double d2 = this.value * this.value3;
            double d3 = simulationSnapshotController.value;
            double d4 = simulationSnapshotController.arrayListRecord.value - this.value4;
            double d5 = d2 * 0.15;
            double d6 = d * 0.15;
            double d7 = this.value2;
            this.value2 = this.computeValue(d7, d6, d5, d4, d3);
            if (!Double.isFinite(this.value2) || Math.abs(this.value4 - simulationSnapshotController.arrayListRecord.value) < 0.001) {
                this.value4 = simulationSnapshotController.arrayListRecord.value;
                this.value2 = 0.0;
            }
            this.value4 += this.value2;
            double d8 = this.value4;
            SimulationSnapshotController simulationSnapshotController2 = simulationSnapshotController;
            SimulationSnapshot simulationSnapshot2 = this.resolveSimulationSnapshot(simulationSnapshotController2, d8);
            simulationSnapshot2.value *= simulationSnapshotController.value2;
            simulationSnapshot2.value2 *= simulationSnapshotController.value3;
            SimulationSnapshot simulationSnapshot3 = simulationSnapshotController.simulationSnapshot;
            simulationSnapshot = simulationSnapshot2.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot3);
            simulationSnapshot.value = MathHelper.getDouble4(simulationSnapshot.value);
            simulationSnapshot.value2 = MathHelper.getDouble7(simulationSnapshot.value2, -90.0, 90.0);
            ClientGlobals.moduleManager.line.itemList4.add((long)((double)System.currentTimeMillis() - (1.0 - (double)(i + 1) / 3.0) * 50.0));
            ClientGlobals.moduleManager.line.itemList6.add(MinecraftAccessor.minecraft.thePlayer.getVec35((float)(i + 1) / 3.0f));
            ClientGlobals.moduleManager.line.rotationHistory.add(new float[]{(float)simulationSnapshot.value, (float)simulationSnapshot.value2});
            while (!ClientGlobals.moduleManager.line.itemList4.isEmpty() && (double)(System.currentTimeMillis() - (Long)ClientGlobals.moduleManager.line.itemList4.get(0)) > ClientGlobals.moduleManager.line.settingAuraLineTime.getValue()) {
                ClientGlobals.moduleManager.line.itemList4.remove(0);
                ClientGlobals.moduleManager.line.rotationHistory.remove(0);
                ClientGlobals.moduleManager.line.itemList6.remove(0);
            }
        }
        ClientGlobals.moduleManager.line.itemList5.add(System.currentTimeMillis());
        ClientGlobals.moduleManager.line.itemList8.add(new float[]{(float)simulationSnapshot.value, (float)simulationSnapshot.value2});
        ClientGlobals.moduleManager.line.itemList7.add(MinecraftAccessor.minecraft.thePlayer.getVec35(1.0f));
        while (!ClientGlobals.moduleManager.line.itemList5.isEmpty() && (double)(System.currentTimeMillis() - (Long)ClientGlobals.moduleManager.line.itemList5.get(0)) > ClientGlobals.moduleManager.line.settingAuraLineTime.getValue()) {
            ClientGlobals.moduleManager.line.itemList5.remove(0);
            ClientGlobals.moduleManager.line.itemList8.remove(0);
            ClientGlobals.moduleManager.line.itemList7.remove(0);
        }
        return simulationSnapshot;
    }

    public double computeValue(double d, double d2, double d3, double d4, double d5) {
        double d6 = MathHelper.getDouble7(Math.min(d5 + 1.4142135623730951 * Math.sqrt(Math.max(d3 * d4, 0.0)), d4) - d, -d, d2);
        return d + d6;
    }

    private SimulationSnapshot resolveSimulationSnapshot(SimulationSnapshotController simulationSnapshotController, double d) {
        double d2 = 0.0;
        SimulationSnapshot simulationSnapshot = null;
        Iterator iterator = simulationSnapshotController.arrayListRecord.arrayList.iterator();
        while (iterator.hasNext()) {
            SimulationSnapshotRecord simulationSnapshotRecord = (SimulationSnapshotRecord)iterator.next();
            if (simulationSnapshot != null) {
                double d3 = simulationSnapshotRecord.simulationSnapshot.resolveSimulationSnapshot(simulationSnapshot).sqrt();
                if (d2 + d3 > d) {
                    double d4 = d - d2;
                    SimulationSnapshot simulationSnapshot2 = simulationSnapshotRecord.simulationSnapshot.resolveSimulationSnapshot(simulationSnapshot).getSimulationSnapshot().resolveSimulationSnapshotByDouble(d4);
                    return simulationSnapshot.resolveSimulationSnapshotBySimulationSnapsh(simulationSnapshot2);
                }
                d2 += d3;
            }
            simulationSnapshot = simulationSnapshotRecord.simulationSnapshot;
        }
        return ((SimulationSnapshotRecord)simulationSnapshotController.arrayListRecord.arrayList.get((int)(simulationSnapshotController.arrayListRecord.arrayList.size() - 1))).simulationSnapshot;
    }

    public double computeValue(ArrayListRecord arrayListRecord, double d) {
        SimulationSnapshot simulationSnapshot = ((SimulationSnapshotRecord)arrayListRecord.arrayList.get((int)(arrayListRecord.arrayList.size() - 1))).simulationSnapshot;
        SimulationSnapshot simulationSnapshot2 = ((SimulationSnapshotRecord)arrayListRecord.arrayList.get((int)(arrayListRecord.arrayList.size() - 2))).simulationSnapshot;
        return Math.abs(simulationSnapshot.resolveSimulationSnapshot((SimulationSnapshot)simulationSnapshot2).getSimulationSnapshot().value * d);
    }
}

