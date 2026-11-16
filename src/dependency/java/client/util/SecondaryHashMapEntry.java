package client.util;

import client.setting.ExtraOptionType;
import com.google.common.collect.Maps;
import java.util.HashMap;
import net.minecraft.entity.EntityBat;
import net.minecraft.entity.EntityBlaze;
import net.minecraft.entity.EntityCavespider;
import net.minecraft.entity.EntityChicken;
import net.minecraft.entity.EntityCow;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.entity.EntityEndermite;
import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityGiant;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.entity.EntityLavaslime;
import net.minecraft.entity.EntityMushroomcow;
import net.minecraft.entity.EntityOzelot;
import net.minecraft.entity.EntityPig;
import net.minecraft.entity.EntityPigzombie;
import net.minecraft.entity.EntityRabbit;
import net.minecraft.entity.EntitySheep;
import net.minecraft.entity.EntitySilverfish;
import net.minecraft.entity.EntitySkeleton;
import net.minecraft.entity.EntitySlime;
import net.minecraft.entity.EntitySnowman;
import net.minecraft.entity.EntitySpider;
import net.minecraft.entity.EntitySquid;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.EntityVillagergolem;
import net.minecraft.entity.EntityWitch;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.entity.EntityWolf;
import net.minecraft.entity.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;

public class SecondaryHashMapEntry {
   public static final HashMap hashMap = Maps.newHashMap();

   static {
      hashMap.put(EntityBat.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityChicken.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityCow.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityHorse.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityMushroomcow.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityOzelot.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityPig.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityRabbit.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntitySheep.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntitySnowman.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntitySquid.class, ExtraOptionType.IN_WATER);
      hashMap.put(EntityVillagergolem.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityWolf.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityVillager.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityEnderdragon.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityWitherboss.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityBlaze.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityCavespider.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityCreeper.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityEnderman.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityEndermite.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityGhast.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityGiant.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityGuardian.class, ExtraOptionType.IN_WATER);
      hashMap.put(EntityLavaslime.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityPigzombie.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntitySilverfish.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntitySkeleton.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntitySlime.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntitySpider.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityWitch.class, ExtraOptionType.ON_GROUND);
      hashMap.put(EntityZombie.class, ExtraOptionType.ON_GROUND);
   }

   public static ExtraOptionType get(Class clazz) {
      return (ExtraOptionType)hashMap.get(clazz);
   }
}
