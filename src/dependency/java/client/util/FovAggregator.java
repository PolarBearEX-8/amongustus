package client.util;

import client.setting.ClientSettingsBlackType;
import client.setting.WorldSettingsGameType;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.BlockPosComparator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFilterAdapter;
import net.minecraft.entity.EntityFilterHandler;
import net.minecraft.entity.EntityFilterImpl;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.AxisAlignedBBFilter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPosFilter;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class FovAggregator {
   public static final Pattern pattern = Pattern.compile("^@([pare])(?:\\[([\\w=,!-]*)\\])?$");
   public static final Pattern pattern2 = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
   public static final Pattern pattern3 = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
   public static final Set set = Sets.newHashSet(new String[]{"x", "y", "z", "dx", "dy", "dz", "rm", "r"});

   public static List resolveList(ICommandSender iCommandSender, String text, Class clazz) {
      Matcher matcher = pattern.matcher(text);
      if (matcher.matches() && iCommandSender.matchesText(1, "@")) {
         Map map = resolveMap(matcher.group(2));
         if (!matchesICommandSender(iCommandSender, map)) {
            return Collections.emptyList();
         } else {
            String text2 = matcher.group(1);
            BlockPos blockPos = resolveBlockPos(map, iCommandSender.getBlockPos());
            List list = resolveList(iCommandSender, map);
            ArrayList list2 = Lists.newArrayList();

            for (Object worldObj : list) { World world = (World)worldObj;
               if (world != null) {
                  ArrayList list3 = Lists.newArrayList();
                  list3.addAll(resolveList(map, text2));
                  list3.addAll(resolveList3(map));
                  list3.addAll(resolveList2(map));
                  list3.addAll(resolveListByMap(map));
                  list3.addAll(resolveList(map));
                  list3.addAll(resolveList5(map));
                  list3.addAll(resolveList(map, blockPos));
                  list3.addAll(resolveList4(map));
                  list2.addAll(resolveList(map, clazz, list3, text2, world, blockPos));
               }
            }

            return resolveList(list2, map, iCommandSender, clazz, text2, blockPos);
         }
      } else {
         return Collections.emptyList();
      }
   }

   private static boolean matchesMap(Map map) {
      for (Object textObj : set) { String text = (String)textObj;
         if (map.containsKey(text)) {
            return true;
         }
      }

      return false;
   }

   public static boolean matchesText(String text) {
      return pattern.matcher(text).matches();
   }

   private static List resolveList(Map map) {
      ArrayList list = Lists.newArrayList();
      Map map2 = resolveMap(map);
      if (map2 != null && map2.size() > 0) {
         list.add(new MinFilter(map2));
      }

      return list;
   }

   private static BlockPos resolveBlockPos(Map map, BlockPos blockPos) {
      return new BlockPos(computeCount(map, "x", blockPos.getX()), computeCount(map, "y", blockPos.getY()), computeCount(map, "z", blockPos.getZ()));
   }

   private static List resolveList2(Map map) {
      ArrayList list = Lists.newArrayList();
      int count = computeCount(map, "m", WorldSettingsGameType.NOT_SET.getIndex());
      if (count != WorldSettingsGameType.NOT_SET.getIndex()) {
         list.add(new EntityFilter(count));
      }

      return list;
   }

   private static List resolveList5(Map map) {
      ArrayList list = Lists.newArrayList();
      String text = get(map, "name");
      boolean flag = text != null && text.startsWith("!");
      if (flag) {
         text = text.substring(1);
      }

      if (text != null) {
         list.add(new net.minecraft.entity.EntityFilter(text, flag));
      }

      return list;
   }

   public static IChatComponent resolveIChatComponent(ICommandSender iCommandSender, String text) {
      List list = resolveList(iCommandSender, text, Entity.class);
      if (list.isEmpty()) {
         return null;
      } else {
         ArrayList list2 = Lists.newArrayList();

         for (Object entityObj : list) { Entity entity = (Entity)entityObj;
            list2.add(entity.getChatComponent());
         }

         return Listener87Entry.resolveIChatComponent(list2);
      }
   }

   private static String get(Map map, String text) {
      return (String)map.get(text);
   }

   private static int computeCount(Map map, String text, int count) {
      return map.containsKey(text) ? MathHelper.getIndex((String)map.get(text), count) : count;
   }

   private static List resolveList(Map map, BlockPos blockPos) {
      ArrayList list = Lists.newArrayList();
      int count = computeCount(map, "rm", -1);
      int count2 = computeCount(map, "r", -1);
      if (blockPos != null && (count >= 0 || count2 >= 0)) {
         int count3 = count * count;
         int count4 = count2 * count2;
         list.add(new BlockPosFilter(blockPos, count, count3, count2, count4));
      }

      return list;
   }

   private static Map resolveMap(String text) {
      HashMap map = Maps.newHashMap();
      if (text == null) {
         return map;
      } else {
         int count = 0;
         int count2 = -1;

         for (Matcher matcher = pattern2.matcher(text); matcher.find(); count2 = matcher.end()) {
            String text2 = null;
            switch (count++) {
               case 0:
                  text2 = "x";
                  break;
               case 1:
                  text2 = "y";
                  break;
               case 2:
                  text2 = "z";
                  break;
               case 3:
                  text2 = "r";
            }

            if (text2 != null && matcher.group(1).length() > 0) {
               map.put(text2, matcher.group(1));
            }
         }

         if (count2 < text.length()) {
            Matcher matcher2 = pattern3.matcher(count2 == -1 ? text : text.substring(count2));

            while (matcher2.find()) {
               map.put(matcher2.group(1), matcher2.group(2));
            }
         }

         return map;
      }
   }

   private static List resolveList(ICommandSender iCommandSender, Map map) {
      ArrayList list = Lists.newArrayList();
      if (matchesMap(map)) {
         list.add(iCommandSender.getWorldObj());
      } else {
         Collections.addAll(list, VanillaRunnable.getVanillaRunnable().worldServerArray);
      }

      return list;
   }

   public static int computeCount(int count) {
      int count2 = count % 360;
      if (count2 >= 160) {
         count2 -= 360;
      }

      if (count2 < 0) {
         count2 += 360;
      }

      return count2;
   }

   private static List resolveListByMap(Map map) {
      ArrayList list = Lists.newArrayList();
      String text = get(map, "team");
      boolean flag = text != null && text.startsWith("!");
      if (flag) {
         text = text.substring(1);
      }

      if (text != null) {
         list.add(new EntityFilterImpl(text, flag));
      }

      return list;
   }

   public static Map resolveMap(Map map) {
      HashMap map2 = Maps.newHashMap();

      for (Object textObj : map.keySet()) { String text = (String)textObj;
         if (text.startsWith("score_") && text.length() > "score_".length()) {
            map2.put(text.substring("score_".length()), MathHelper.getIndex((String)map.get(text), 1));
         }
      }

      return map2;
   }

   private static List resolveList(List list, Map map, ICommandSender iCommandSender, Class clazz, String text, BlockPos blockPos) {
      List entry = list;
      int count = computeCount(map, "c", !text.equals("a") && !text.equals("e") ? 1 : 0);
      if (!text.equals("p") && !text.equals("a") && !text.equals("e")) {
         if (text.equals("r")) {
            Collections.shuffle(list);
         }
      } else if (blockPos != null) {
         Collections.sort(list, new BlockPosComparator(blockPos));
      }

      Entity entity = iCommandSender.getEntity();
      if (entity != null && clazz.isAssignableFrom(entity.getClass()) && count == 1 && list.contains(entity) && !"r".equals(text)) {
         entry = Lists.newArrayList(new Entity[]{entity});
      }

      if (count != 0) {
         if (count < 0) {
            Collections.reverse((List<?>)entry);
         }

         entry = entry.subList(0, Math.min(Math.abs(count), entry.size()));
      }

      return (List)entry;
   }

   private static List resolveList4(Map map) {
      ArrayList list = Lists.newArrayList();
      if (map.containsKey("rym") || map.containsKey("ry")) {
         int count = computeCount(computeCount(map, "rym", 0));
         int count2 = computeCount(computeCount(map, "ry", 359));
         list.add(new RuntimeComponentImpl(count, count2));
      }

      if (map.containsKey("rxm") || map.containsKey("rx")) {
         int count3 = computeCount(computeCount(map, "rxm", 0));
         int count4 = computeCount(computeCount(map, "rx", 359));
         list.add(new EntityFilterHandler(count3, count4));
      }

      return list;
   }

   private static AxisAlignedBB resolveAxisAlignedBB(BlockPos blockPos, int count, int count2, int count3) {
      boolean flag = count < 0;
      boolean flag2 = count2 < 0;
      boolean flag3 = count3 < 0;
      int count4 = blockPos.getX() + (flag ? count : 0);
      int count5 = blockPos.getY() + (flag2 ? count2 : 0);
      int count6 = blockPos.getZ() + (flag3 ? count3 : 0);
      int count7 = blockPos.getX() + (flag ? 0 : count) + 1;
      int count8 = blockPos.getY() + (flag2 ? 0 : count2) + 1;
      int count9 = blockPos.getZ() + (flag3 ? 0 : count3) + 1;
      return new AxisAlignedBB((double)count4, (double)count5, (double)count6, (double)count7, (double)count8, (double)count9);
   }

   private static boolean matchesICommandSender(ICommandSender iCommandSender, Map map) {
      String text = get(map, "type");
      String text2 = text != null && text.startsWith("!") ? text.substring(1) : text;
      if (text2 != null && !SkippingEntityWithIdManager.matchesText(text2)) {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("commands.generic.entity.invalidType", new Object[]{text2});
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
         return false;
      } else {
         return true;
      }
   }

   public static Entity resolveEntity(ICommandSender iCommandSender, String text, Class clazz) {
      List list = resolveList(iCommandSender, text, clazz);
      return list.size() == 1 ? (Entity)list.get(0) : null;
   }

   private static List resolveList(Map map, String text) {
      ArrayList list = Lists.newArrayList();
      String text2 = get(map, "type");
      boolean flag = text2 != null && text2.startsWith("!");
      if (flag) {
         text2 = text2.substring(1);
      }

      boolean flag2 = !text.equals("e");
      boolean flag3 = text.equals("r") && text2 != null;
      if ((text2 == null || !text.equals("e")) && !flag3) {
         if (flag2) {
            list.add(new EntityFilterAdapter());
         }
      } else {
         list.add(new RuntimeComponentManager(text2, flag));
      }

      return list;
   }

   private static List resolveList3(Map map) {
      ArrayList list = Lists.newArrayList();
      int count = computeCount(map, "lm", -1);
      int count2 = computeCount(map, "l", -1);
      if (count > -1 || count2 > -1) {
         list.add(new net.minecraft.entity.player.EntityFilter(count, count2));
      }

      return list;
   }

   public static boolean matchesTextByText(String text) {
      Matcher matcher = pattern.matcher(text);
      if (!matcher.matches()) {
         return false;
      } else {
         Map map = resolveMap(matcher.group(2));
         String text2 = matcher.group(1);
         int count = !"a".equals(text2) && !"e".equals(text2) ? 1 : 0;
         return computeCount(map, "c", count) != 1;
      }
   }

   private static List resolveList(Map map, Class clazz, List list, String text, World world, BlockPos blockPos) {
      ArrayList list2 = Lists.newArrayList();
      String text2 = get(map, "type");
      String text3 = text2 != null && text2.startsWith("!") ? text2.substring(1) : text2;
      boolean flag = !text.equals("e");
      boolean flag2 = text.equals("r") && text3 != null;
      int count = computeCount(map, "dx", 0);
      int count2 = computeCount(map, "dy", 0);
      int count3 = computeCount(map, "dz", 0);
      int count4 = computeCount(map, "r", -1);
      Predicate predicate = Predicates.and(list);
      Predicate predicate2 = Predicates.and(LivingEntityFilter.predicate, predicate);
      if (blockPos != null) {
         int size = world.itemList7.size();
         int size2 = world.loadedEntityList.size();
         boolean flag3 = size < size2 / 16;
         if (map.containsKey("dx") || map.containsKey("dy") || map.containsKey("dz")) {
            AxisAlignedBB boundingBox = resolveAxisAlignedBB(blockPos, count, count2, count3);
            if (flag && flag3 && !flag2) {
               AxisAlignedBBFilter axisAlignedBBFilter = new AxisAlignedBBFilter(boundingBox);
               list2.addAll(world.resolveListByClass(clazz, Predicates.and(predicate2, axisAlignedBBFilter)));
            } else {
               list2.addAll(world.resolveList(clazz, boundingBox, predicate2));
            }
         } else if (count4 >= 0) {
            AxisAlignedBB boundingBox2 = new AxisAlignedBB(
               (double)(blockPos.getX() - count4),
               (double)(blockPos.getY() - count4),
               (double)(blockPos.getZ() - count4),
               (double)(blockPos.getX() + count4 + 1),
               (double)(blockPos.getY() + count4 + 1),
               (double)(blockPos.getZ() + count4 + 1)
            );
            if (flag && flag3 && !flag2) {
               list2.addAll(world.resolveListByClass(clazz, predicate2));
            } else {
               list2.addAll(world.resolveList(clazz, boundingBox2, predicate2));
            }
         } else if (text.equals("a")) {
            list2.addAll(world.resolveListByClass(clazz, predicate));
         } else if (text.equals("p") || text.equals("r") && !flag2) {
            list2.addAll(world.resolveListByClass(clazz, predicate2));
         } else {
            list2.addAll(world.resolveList(clazz, predicate2));
         }
      } else if (text.equals("a")) {
         list2.addAll(world.resolveListByClass(clazz, predicate));
      } else if (text.equals("p") || text.equals("r") && !flag2) {
         list2.addAll(world.resolveListByClass(clazz, predicate2));
      } else {
         list2.addAll(world.resolveList(clazz, predicate2));
      }

      return list2;
   }

   public static EntityPlayerAdapter resolveEntityPlayerAdapter(ICommandSender iCommandSender, String text) {
      return (EntityPlayerAdapter)resolveEntity(iCommandSender, text, EntityPlayerAdapter.class);
   }
}
