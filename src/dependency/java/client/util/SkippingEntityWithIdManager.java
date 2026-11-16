package client.util;

import client.setting.MovementNumberProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.BlockPosEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityBat;
import net.minecraft.entity.EntityBlaze;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.EntityCaveSpiderAdapter;
import net.minecraft.entity.EntityCavespider;
import net.minecraft.entity.EntityChicken;
import net.minecraft.entity.EntityCow;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.entity.EntityEndercrystal;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.entity.EntityEndermite;
import net.minecraft.entity.EntityEyeofendersignal;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.entity.EntityFireball;
import net.minecraft.entity.EntityFireworksrocketentity;
import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityGiant;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLavaslime;
import net.minecraft.entity.EntityLeashknot;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityMonster;
import net.minecraft.entity.EntityMushroomcow;
import net.minecraft.entity.EntityOzelot;
import net.minecraft.entity.EntityPainting;
import net.minecraft.entity.EntityPig;
import net.minecraft.entity.EntityPigzombie;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.entity.EntityRabbit;
import net.minecraft.entity.EntitySheep;
import net.minecraft.entity.EntitySilverfish;
import net.minecraft.entity.EntitySkeleton;
import net.minecraft.entity.EntitySlime;
import net.minecraft.entity.EntitySmallfireball;
import net.minecraft.entity.EntitySnowball;
import net.minecraft.entity.EntitySnowman;
import net.minecraft.entity.EntitySpider;
import net.minecraft.entity.EntitySquid;
import net.minecraft.entity.EntityThrownegg;
import net.minecraft.entity.EntityThrownenderpearl;
import net.minecraft.entity.EntityThrownexpbottle;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.EntityVillagergolem;
import net.minecraft.entity.EntityWitch;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.entity.EntityWitherskull;
import net.minecraft.entity.EntityWolf;
import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.DiamondWeapon;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SkippingEntityWithIdManager {
   public static final Logger logger = LogManager.getLogger();
   public static final Map map4 = Maps.newHashMap();
   public static final Map map2 = Maps.newHashMap();
   public static final Map map = Maps.newHashMap();
   public static final Map map3 = Maps.newHashMap();
   public static final Map map5 = Maps.newHashMap();
   public static final Map map6 = Maps.newLinkedHashMap();

   public static Class resolveClass(int count) {
      return (Class)map.get(count);
   }

   public static String resolveText(int count) {
      return (String)map2.get(resolveClass(count));
   }

   public static Entity resolveEntity(int count, World world) {
      Entity entity = null;

      try {
         Class clazz = resolveClass(count);
         if (clazz != null) {
            entity = (Entity)clazz.getConstructor(World.class).newInstance(world);
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      if (entity == null) {
         logger.warn("Skipping Entity with id " + count);
      }

      return entity;
   }

   public static boolean matchesEntity(Entity entity, String text) {
      String text2 = resolveTextByEntity(entity);
      if (text2 == null && entity instanceof EntityPlayer) {
         text2 = "Player";
      } else if (text2 == null && entity instanceof HUIHRVManager) {
         text2 = "LightningBolt";
      }

      return text.equals(text2);
   }

   public static void updateState() {
   }

   public static boolean matchesText(String text) {
      return "Player".equals(text) || getList().contains(text);
   }

   public static int computeCount(String text) {
      Integer count = (Integer)map5.get(text);
      return count == null ? 90 : count;
   }

   public static Entity resolveEntityByNBTTagCompound(NBTTagCompound tagCompound, World world) {
      Entity entity = null;
      if ("Minecart".equals(tagCompound.resolveText("id"))) {
         tagCompound.put("id", MovementNumberProperty.resolveMovementNumberProperty(tagCompound.computeCount("Type")).getLabelText());
         tagCompound.remove("Type");
      }

      try {
         Class clazz = (Class)map4.get(tagCompound.resolveText("id"));
         if (clazz != null) {
            entity = (Entity)clazz.getConstructor(World.class).newInstance(world);
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      if (entity != null) {
         entity.applyNBTTagCompound2(tagCompound);
      } else {
         logger.warn("Skipping Entity with id " + tagCompound.resolveText("id"));
      }

      return entity;
   }

   public static String get(Class clazz) {
      return (String)map2.get(clazz);
   }

   public static Entity resolveEntityByText(String text, World world) {
      Entity entity = null;

      try {
         Class clazz = (Class)map4.get(text);
         if (clazz != null) {
            entity = (Entity)clazz.getConstructor(World.class).newInstance(world);
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      return entity;
   }

   public static String resolveTextByEntity(Entity entity) {
      return (String)map2.get(entity.getClass());
   }

   private static void applyClass(Class clazz, String text, int count, int count2, int count3) {
      applyClass(clazz, text, count);
      map6.put(count, new DuplicateStatIdManagerEntry(count, count2, count3));
   }

   private static void applyClass(Class clazz, String text, int count) {
      if (map4.containsKey(text)) {
         throw new IllegalArgumentException("ID is already registered: " + text);
      } else if (map.containsKey(count)) {
         throw new IllegalArgumentException("ID is already registered: " + count);
      } else if (count == 0) {
         throw new IllegalArgumentException("Cannot register to reserved id: " + count);
      } else if (clazz == null) {
         throw new IllegalArgumentException("Cannot register null clazz for id: " + count);
      } else {
         map4.put(text, clazz);
         map2.put(clazz, text);
         map.put(count, clazz);
         map3.put(clazz, count);
         map5.put(text, count);
      }
   }

   public static List getList() {
      Set keys = map4.keySet();
      ArrayList list = Lists.newArrayList();

      for (String text : (Iterable<String>)(keys)) {
         if ((((Class)map4.get(text)).getModifiers() & 1024) != 1024) {
            list.add(text);
         }
      }

      list.add("LightningBolt");
      return list;
   }

   public static int computeCount(Entity entity) {
      Integer count = (Integer)map3.get(entity.getClass());
      return count == null ? 0 : count;
   }

   static {
      applyClass(EntityItem.class, "Item", 1);
      applyClass(EntityXporb.class, "XPOrb", 2);
      applyClass(EntityThrownegg.class, "ThrownEgg", 7);
      applyClass(EntityLeashknot.class, "LeashKnot", 8);
      applyClass(EntityPainting.class, "Painting", 9);
      applyClass(EntityArrow.class, "Arrow", 10);
      applyClass(EntitySnowball.class, "Snowball", 11);
      applyClass(EntityFireball.class, "Fireball", 12);
      applyClass(EntitySmallfireball.class, "SmallFireball", 13);
      applyClass(EntityThrownenderpearl.class, "ThrownEnderpearl", 14);
      applyClass(EntityEyeofendersignal.class, "EyeOfEnderSignal", 15);
      applyClass(EntityThrownpotion.class, "ThrownPotion", 16);
      applyClass(EntityThrownexpbottle.class, "ThrownExpBottle", 17);
      applyClass(EntityItemframe.class, "ItemFrame", 18);
      applyClass(EntityWitherskull.class, "WitherSkull", 19);
      applyClass(EntityPrimedtnt.class, "PrimedTnt", 20);
      applyClass(EntityFallingsand.class, "FallingSand", 21);
      applyClass(EntityFireworksrocketentity.class, "FireworksRocketEntity", 22);
      applyClass(EntityArmorstand.class, "ArmorStand", 30);
      applyClass(EntityBoat.class, "Boat", 41);
      applyClass(VectorConverter.class, MovementNumberProperty.RIDEABLE.getLabelText(), 42);
      applyClass(VectorEvaluator.class, MovementNumberProperty.CHEST.getLabelText(), 43);
      applyClass(EnhancedDoubleEntry.class, MovementNumberProperty.FURNACE.getLabelText(), 44);
      applyClass(VectorCalculator.class, MovementNumberProperty.TNT.getLabelText(), 45);
      applyClass(BlockPosEntry.class, MovementNumberProperty.HOPPER.getLabelText(), 46);
      applyClass(DiamondWeapon.class, MovementNumberProperty.SPAWNER.getLabelText(), 47);
      applyClass(EntityCaveSpiderAdapter.class, MovementNumberProperty.COMMAND_BLOCK.getLabelText(), 40);
      applyClass(EntityMob.class, "Mob", 48);
      applyClass(EntityMonster.class, "Monster", 49);
      applyClass(EntityCreeper.class, "Creeper", 50, 894731, 0);
      applyClass(EntitySkeleton.class, "Skeleton", 51, 12698049, 4802889);
      applyClass(EntitySpider.class, "Spider", 52, 3419431, 11013646);
      applyClass(EntityGiant.class, "Giant", 53);
      applyClass(EntityZombie.class, "Zombie", 54, 44975, 7969893);
      applyClass(EntitySlime.class, "Slime", 55, 5349438, 8306542);
      applyClass(EntityGhast.class, "Ghast", 56, 16382457, 12369084);
      applyClass(EntityPigzombie.class, "PigZombie", 57, 15373203, 5009705);
      applyClass(EntityEnderman.class, "Enderman", 58, 1447446, 0);
      applyClass(EntityCavespider.class, "CaveSpider", 59, 803406, 11013646);
      applyClass(EntitySilverfish.class, "Silverfish", 60, 7237230, 3158064);
      applyClass(EntityBlaze.class, "Blaze", 61, 16167425, 16775294);
      applyClass(EntityLavaslime.class, "LavaSlime", 62, 3407872, 16579584);
      applyClass(EntityEnderdragon.class, "EnderDragon", 63);
      applyClass(EntityWitherboss.class, "WitherBoss", 64);
      applyClass(EntityBat.class, "Bat", 65, 4996656, 986895);
      applyClass(EntityWitch.class, "Witch", 66, 3407872, 5349438);
      applyClass(EntityEndermite.class, "Endermite", 67, 1447446, 7237230);
      applyClass(EntityGuardian.class, "Guardian", 68, 5931634, 15826224);
      applyClass(EntityPig.class, "Pig", 90, 15771042, 14377823);
      applyClass(EntitySheep.class, "Sheep", 91, 15198183, 16758197);
      applyClass(EntityCow.class, "Cow", 92, 4470310, 10592673);
      applyClass(EntityChicken.class, "Chicken", 93, 10592673, 16711680);
      applyClass(EntitySquid.class, "Squid", 94, 2243405, 7375001);
      applyClass(EntityWolf.class, "Wolf", 95, 14144467, 13545366);
      applyClass(EntityMushroomcow.class, "MushroomCow", 96, 10489616, 12040119);
      applyClass(EntitySnowman.class, "SnowMan", 97);
      applyClass(EntityOzelot.class, "Ozelot", 98, 15720061, 5653556);
      applyClass(EntityVillagergolem.class, "VillagerGolem", 99);
      applyClass(EntityHorse.class, "EntityHorse", 100, 12623485, 15656192);
      applyClass(EntityRabbit.class, "Rabbit", 101, 10051392, 7555121);
      applyClass(EntityVillager.class, "Villager", 120, 5651507, 12422002);
      applyClass(EntityEndercrystal.class, "EnderCrystal", 200);
   }
}
