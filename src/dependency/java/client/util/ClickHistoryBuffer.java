package client.util;

import client.event.EventPacket;
import client.util.DampenerObserver;
import client.util.MinecraftAccessor;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.PlayerDiggingActionType;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.server.S07PacketRespawn;

public class ClickHistoryBuffer
implements MinecraftAccessor {
    public int count2 = 0;
    public boolean enabled2 = false;
    public int count3;
    public int count;
    public boolean enabled5 = false;
    public int count5 = 0;
    public boolean enabled = false;
    public final ArrayList arrayList2;
    public final boolean enabled3;
    public final ArrayList arrayList3 = new ArrayList();
    public final boolean enabled4;
    public final ArrayList arrayList;
    public int count4 = 0;

    public void onPacket(EventPacket eventPacket) {
        TimeHelper.getStringArray();
        Packet packet = eventPacket.getPacket();
        if (packet instanceof C03PacketPlayer) {
            this.enabled2 = false;
            for (DampenerObserver dampenerObserver : (Iterable<DampenerObserver>)(Iterable)this.arrayList3) {
                if (this.count5 > 0 && !this.isActive()) continue;
                if (dampenerObserver.count == 0) {
                    ++this.count2;
                }
                this.arrayList.add(dampenerObserver.count);
                while (this.arrayList.size() > this.count) {
                    this.arrayList.remove(0);
                }
                ArrayList arrayList = this.arrayList;
                this.enabled2 = this.computeValue(arrayList) > 14.75;
            }
            this.arrayList3.clear();
            ++this.count4;
            ++this.count5;
            this.enabled = false;
        }
        if (packet instanceof C0APacketAnimation && !this.enabled5 && !this.enabled) {
            boolean bl;
            this.getClass();
            this.getClass();
            boolean bl2 = bl = this.count4 > 0;
            if (this.count4 <= 15 && this.count3 <= 15 && bl) {
                DampenerObserver dampenerObserver = new DampenerObserver(this.count4, this.count3);
                this.arrayList3.add(dampenerObserver);
            }
            this.count3 = this.count4;
            this.count4 = 0;
        }
        if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getUseEntityAction() == UseEntityAction.ATTACK) {
            this.count5 = 0;
        }
        if (packet instanceof C08PacketPlayerBlockPlacement) {
            this.enabled = true;
        }
        if (packet instanceof C07PacketPlayerDigging) {
            C07PacketPlayerDigging c07PacketPlayerDigging = (C07PacketPlayerDigging)packet;
            if (c07PacketPlayerDigging.getPlayerDiggingActionType() == PlayerDiggingActionType.START_DESTROY_BLOCK) {
                this.enabled5 = true;
                if (MinecraftAccessor.minecraft.playerController.processAction2()) {
                    this.enabled5 = false;
                }
            }
            if (c07PacketPlayerDigging.getPlayerDiggingActionType() == PlayerDiggingActionType.ABORT_DESTROY_BLOCK || c07PacketPlayerDigging.getPlayerDiggingActionType() == PlayerDiggingActionType.STOP_DESTROY_BLOCK) {
                this.enabled5 = false;
            }
        }
    }

    private boolean isActive() {
        TimeHelper.getStringArray();
        return MinecraftAccessor.minecraft.objectMouseOver != null && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase;
    }

    private double computeValue(Collection collection) {
        double d = 0.0;
        TimeHelper.getStringArray();
        double d2 = 0.0;
        for (Integer n : (Iterable<Integer>)(Iterable)collection) {
            d += n.doubleValue();
        }
        double d3 = d / (double)collection.size();
        for (Integer n : (Iterable<Integer>)(Iterable)collection) {
            d2 += (n.doubleValue() - d3) * (n.doubleValue() - d3);
        }
        return Math.sqrt(d2 / (double)collection.size());
    }

    public void onPacket2(EventPacket eventPacket) {
        if (eventPacket.getPacket() instanceof S07PacketRespawn) {
            this.count2 = 0;
        }
    }

    private double computeValueByList(List list) {
        TimeHelper.getStringArray();
        ArrayList<Integer> arrayList = new ArrayList<Integer>(list);
        if (!arrayList.isEmpty()) {
            arrayList.remove(0);
            arrayList.add(1);
            return arrayList.stream().mapToDouble(Number::doubleValue).average().orElse(0.0);
        }
        return 0.0;
    }

    private double computeValue(List list) {
        return 20.0 / this.computeValueByList(list);
    }

    public boolean canExecute() {
        return this.enabled2;
    }

    private static void applyAtomicDouble(AtomicDouble atomicDouble, double d, Integer n) {
        atomicDouble.getAndAdd(Math.pow(n.doubleValue() - d, 2.0));
    }

    public ClickHistoryBuffer(int n) {
        this.enabled3 = true;
        this.enabled4 = false;
        this.arrayList2 = new ArrayList();
        this.arrayList = new ArrayList();
        this.count = n;
    }
}

