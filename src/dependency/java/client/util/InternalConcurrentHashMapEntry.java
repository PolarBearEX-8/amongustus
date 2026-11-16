package client.util;

import client.setting.EventPacketServerboundType;
import client.util.ClientManager;
import client.util.MinecraftAccessor;
import client.util.Packet;
import client.util.TimeHelper;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityVillager;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.util.Vec3;

public class InternalConcurrentHashMapEntry
implements MinecraftAccessor {
    public static final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    private static void applyPacketByPacket(Packet packet, char c, EntityLivingBase entityLivingBase, int n, char c2) {
        entityLivingBase.value16 = entityLivingBase.value12;
        entityLivingBase.value7 = entityLivingBase.value20;
        entityLivingBase.value17 = entityLivingBase.value10;
        entityLivingBase.value12 = entityLivingBase.coordOffset5;
        entityLivingBase.value20 = entityLivingBase.coordOffset20;
        entityLivingBase.value10 = entityLivingBase.coordOffset2;
        boolean cfr_ignored_0 = entityLivingBase instanceof EntityVillager;
        entityLivingBase.coordOffset5 = (double)entityLivingBase.count13 / 32.0;
        entityLivingBase.coordOffset20 = (double)entityLivingBase.count18 / 32.0;
        entityLivingBase.coordOffset2 = (double)entityLivingBase.count19 / 32.0;
        ClientManager.applyRunnable2(() -> InternalConcurrentHashMapEntry.put(entityLivingBase, packet));
        entityLivingBase.indexOffset23 = 3;
    }

    private static void applyPacket(Packet packet) {
        S12PacketEntityVelocity s12PacketEntityVelocity = (S12PacketEntityVelocity)packet;
        Entity entity = MinecraftAccessor.minecraft.theWorld.getEntityByID(s12PacketEntityVelocity.getEntityId());
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            entityLivingBase.value13 = (double)s12PacketEntityVelocity.getEntityId3() / 8000.0;
            entityLivingBase.value14 = (double)s12PacketEntityVelocity.getEntityId2() / 8000.0;
            entityLivingBase.value5 = (double)s12PacketEntityVelocity.getEntityId4() / 8000.0;
        }
    }

    private static void put(EntityLivingBase entityLivingBase, Packet packet) {
        entityLivingBase.entryMap.put(packet, new Vec3(entityLivingBase.coordOffset5, entityLivingBase.coordOffset20, entityLivingBase.coordOffset2));
    }

    public static void onPacketServerboundType(Packet packet, EventPacketServerboundType eventPacketServerboundType) {
        S18PacketEntityTeleport s18PacketEntityTeleport;
        Entity entity;
        S14PacketEntity s14PacketEntity;
        Entity entity2;
        long l = 0x74e82c3f8e58L;
        long l2 = 0x66EB50DCD5DBL ^ l ^ 0xE591E155D6FL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 32);
        int n3 = (int)(l2 << 48 >>> 48);
        if (eventPacketServerboundType != EventPacketServerboundType.CLIENTBOUND || !(packet instanceof S14PacketEntity) && !(packet instanceof S18PacketEntityTeleport) && !(packet instanceof S12PacketEntityVelocity)) {
            return;
        }
        if (packet instanceof S14PacketEntity && (entity2 = (Entity)concurrentHashMap.get((s14PacketEntity = (S14PacketEntity)packet).getIndex())) instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity2;
            entityLivingBase.count13 += s14PacketEntity.getByteFlag();
            entityLivingBase.count18 += s14PacketEntity.getByteFlag3();
            entityLivingBase.count19 += s14PacketEntity.getByteFlag4();
            char c = (char)n3;
            int n4 = n2;
            EntityLivingBase entityLivingBase2 = entityLivingBase;
            char c2 = (char)n;
            InternalConcurrentHashMapEntry.applyPacketByPacket(s14PacketEntity, c2, entityLivingBase2, n4, c);
        }
        if (packet instanceof S18PacketEntityTeleport && (entity = (Entity)concurrentHashMap.get((s18PacketEntityTeleport = (S18PacketEntityTeleport)packet).getIndex())) instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            entityLivingBase.count13 = s18PacketEntityTeleport.getCount3();
            entityLivingBase.count18 = s18PacketEntityTeleport.getCount2();
            entityLivingBase.count19 = s18PacketEntityTeleport.getCount();
            char c = (char)n3;
            int n5 = n2;
            EntityLivingBase entityLivingBase3 = entityLivingBase;
            char c3 = (char)n;
            InternalConcurrentHashMapEntry.applyPacketByPacket(s18PacketEntityTeleport, c3, entityLivingBase3, n5, c);
        }
        if (packet instanceof S12PacketEntityVelocity) {
            ClientManager.applyRunnable2(() -> InternalConcurrentHashMapEntry.applyPacket(packet));
        }
    }
}

