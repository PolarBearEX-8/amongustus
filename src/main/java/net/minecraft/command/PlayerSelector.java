package net.minecraft.command;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;
import java.util.Map.Entry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.WorldSettings;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PlayerSelector {
   private static final Pattern tokenPattern = Pattern.compile("^@([pare])(?:\\[([\\w=,!-]*)\\])?$");
   private static final Pattern intListPattern = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
   private static final Pattern keyValueListPattern = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
   private static final Set WORLD_BINDING_ARGS = Sets.newHashSet(new String[]{"x", "y", "z", "dx", "dy", "dz", "rm", "r"});

   public static Map getScoreMap(Map map) {
      HashMap map2 = Maps.newHashMap();

      for (String str : (Iterable<String>)(map.keySet())) {
         if (str.startsWith("score_") && str.length() > "score_".length()) {
            map2.put(str.substring("score_".length()), MathHelper.parseIntWithDefault((String)map.get(str), 1));
         }
      }

      return map2;
   }

   private static BlockPos getPosFromArguments(Map map, BlockPos pos) {
      return new BlockPos(parseIntWithDefault(map, "x", pos.getX()), parseIntWithDefault(map, "y", pos.getY()), parseIntWithDefault(map, "z", pos.getZ()));
   }

   private static List filterResults(Map map, Class clazz, List list, String str, World world, BlockPos pos) {
      ArrayList list2 = Lists.newArrayList();
      String argument = getArgument(map, "type");
      argument = argument != null && argument.startsWith("!") ? argument.substring(1) : argument;
      boolean flag = !str.equals("e");
      boolean flag2 = str.equals("r") && argument != null;
      int n = parseIntWithDefault(map, "dx", 0);
      int n2 = parseIntWithDefault(map, "dy", 0);
      int n3 = parseIntWithDefault(map, "dz", 0);
      int n4 = parseIntWithDefault(map, "r", -1);
      Predicate predicate = Predicates.and(list);
      Predicate predicate2 = Predicates.and(EntitySelectors.selectAnything, predicate);
      if (pos != null) {
         boolean flag3 = world.playerEntities.size() < world.loadedEntityList.size() / 16;
         if (map.containsKey("dx") || map.containsKey("dy") || map.containsKey("dz")) {
            AxisAlignedBB area = getArea(pos, n, n2, n3);
            if (flag && flag3 && !flag2) {
               list2.addAll(world.getPlayers(clazz, Predicates.and(predicate2, new PlayerSelector.FilterPredicate11(area))));
            } else {
               list2.addAll(world.getEntitiesWithinAABB(clazz, area, predicate2));
            }
         } else if (n4 >= 0) {
            AxisAlignedBB box = new AxisAlignedBB(
               (double)(pos.getX() - n4),
               (double)(pos.getY() - n4),
               (double)(pos.getZ() - n4),
               (double)(pos.getX() + n4 + 1),
               (double)(pos.getY() + n4 + 1),
               (double)(pos.getZ() + n4 + 1)
            );
            if (flag && flag3 && !flag2) {
               list2.addAll(world.getPlayers(clazz, predicate2));
            } else {
               list2.addAll(world.getEntitiesWithinAABB(clazz, box, predicate2));
            }
         } else if (str.equals("a")) {
            list2.addAll(world.getPlayers(clazz, predicate));
         } else if (str.equals("p") || str.equals("r") && !flag2) {
            list2.addAll(world.getPlayers(clazz, predicate2));
         } else {
            list2.addAll(world.getEntities(clazz, predicate2));
         }
      } else if (str.equals("a")) {
         list2.addAll(world.getPlayers(clazz, predicate));
      } else if (str.equals("p") || str.equals("r") && !flag2) {
         list2.addAll(world.getPlayers(clazz, predicate2));
      } else {
         list2.addAll(world.getEntities(clazz, predicate2));
      }

      return list2;
   }

   public static IChatComponent matchEntitiesToChatComponent(ICommandSender commandSender, String str) {
      List list = matchEntities(commandSender, str, Entity.class);
      if (list.isEmpty()) {
         return null;
      } else {
         ArrayList list2 = Lists.newArrayList();

         for (Entity entity : (Iterable<Entity>)(list)) {
            list2.add(entity.getDisplayName());
         }

         return CommandBase.join(list2);
      }
   }

   private static List getTypePredicates(Map map, String str) {
      ArrayList list = Lists.newArrayList();
      String argument = getArgument(map, "type");
      boolean flag = argument != null && argument.startsWith("!");
      if (flag) {
         argument = argument.substring(1);
      }

      boolean flag2 = !str.equals("e");
      boolean flag3 = str.equals("r") && argument != null;
      if ((argument == null || !str.equals("e")) && !flag3) {
         if (flag2) {
            list.add(new PlayerSelector.FilterPredicate());
         }
      } else {
         list.add(new PlayerSelector.FilterPredicate2(argument, flag));
      }

      return list;
   }

   public static Entity matchOneEntity(ICommandSender commandSender, String str, Class clazz) {
      List list = matchEntities(commandSender, str, clazz);
      return list.size() == 1 ? (Entity)list.get(0) : null;
   }

   private static List getTeamPredicates(Map map) {
      ArrayList list = Lists.newArrayList();
      String argument = getArgument(map, "team");
      boolean flag = argument != null && argument.startsWith("!");
      if (flag) {
         argument = argument.substring(1);
      }

      if (argument != null) {
         list.add(new PlayerSelector.FilterPredicate5(argument, flag));
      }

      return list;
   }

   private static boolean isEntityTypeValid(ICommandSender commandSender, Map map) {
      String argument = getArgument(map, "type");
      argument = argument != null && argument.startsWith("!") ? argument.substring(1) : argument;
      if (argument != null && !EntityList.isStringValidEntityName(argument)) {
         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("commands.generic.entity.invalidType", new Object[]{argument});
         chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.RED);
         commandSender.addChatMessage(chatComponentTranslation);
         return false;
      } else {
         return true;
      }
   }

   private static List getXpLevelPredicates(Map map) {
      ArrayList list = Lists.newArrayList();
      int n = parseIntWithDefault(map, "lm", -1);
      int n2 = parseIntWithDefault(map, "l", -1);
      if (n > -1 || n2 > -1) {
         list.add(new PlayerSelector.FilterPredicate3(n, n2));
      }

      return list;
   }

   private static Map getArgumentMap(String str) {
      HashMap map = Maps.newHashMap();
      if (str == null) {
         return map;
      } else {
         int n = 0;
         int n2 = -1;

         for (Matcher i = intListPattern.matcher(str); i.find(); n2 = i.end()) {
            String str2 = null;
            switch (n++) {
               case 0:
                  str2 = "x";
                  break;
               case 1:
                  str2 = "y";
                  break;
               case 2:
                  str2 = "z";
                  break;
               case 3:
                  str2 = "r";
            }

            if (str2 != null && i.group(1).length() > 0) {
               map.put(str2, i.group(1));
            }
         }

         if (n2 < str.length()) {
            Matcher matcher = keyValueListPattern.matcher(n2 == -1 ? str : str.substring(n2));

            while (matcher.find()) {
               map.put(matcher.group(1), matcher.group(2));
            }
         }

         return map;
      }
   }

   private static String getArgument(Map map, String str) {
      return (String)map.get(str);
   }

   private static List getWorlds(ICommandSender commandSender, Map map) {
      ArrayList list = Lists.newArrayList();
      if (hasPositionArgs(map)) {
         list.add(commandSender.getEntityWorld());
      } else {
         Collections.addAll(list, MinecraftServer.getServer().worldServers);
      }

      return list;
   }

   private static List getRotationsPredicates(Map map) {
      ArrayList list = Lists.newArrayList();
      if (map.containsKey("rym") || map.containsKey("ry")) {
         int n = fixHorizontalAngle(parseIntWithDefault(map, "rym", 0));
         int n2 = fixHorizontalAngle(parseIntWithDefault(map, "ry", 359));
         list.add(new PlayerSelector.FilterPredicate9(n, n2));
      }

      if (map.containsKey("rxm") || map.containsKey("rx")) {
         int n3 = fixHorizontalAngle(parseIntWithDefault(map, "rxm", 0));
         int n4 = fixHorizontalAngle(parseIntWithDefault(map, "rx", 359));
         list.add(new PlayerSelector.FilterPredicate10(n3, n4));
      }

      return list;
   }

   public static boolean hasArguments(String str) {
      return tokenPattern.matcher(str).matches();
   }

   private static List getRadiusPredicates(Map map, BlockPos pos) {
      ArrayList list = Lists.newArrayList();
      int n = parseIntWithDefault(map, "rm", -1);
      int n2 = parseIntWithDefault(map, "r", -1);
      if (pos != null && (n >= 0 || n2 >= 0)) {
         int n3 = n * n;
         int n4 = n2 * n2;
         list.add(new PlayerSelector.FilterPredicate8(pos, n, n3, n2, n4));
      }

      return list;
   }

   private static boolean hasPositionArgs(Map map) {
      for (String str : (Iterable<String>)(WORLD_BINDING_ARGS)) {
         if (map.containsKey(str)) {
            return true;
         }
      }

      return false;
   }

   public static int fixHorizontalAngle(int n) {
      n %= 360;
      if (n >= 160) {
         n -= 360;
      }

      if (n < 0) {
         n += 360;
      }

      return n;
   }

   private static List getEntitiesFromPredicates(List list, Map map, ICommandSender commandSender, Class clazz, String str, BlockPos pos) {
      int n = parseIntWithDefault(map, "c", !str.equals("a") && !str.equals("e") ? 1 : 0);
      if (!str.equals("p") && !str.equals("a") && !str.equals("e")) {
         if (str.equals("r")) {
            Collections.shuffle((List<?>)list);
         }
      } else if (pos != null) {
         Collections.sort((List)list, new PlayerSelector.EntryComparator(pos));
      }

      Entity commandSenderEntity = commandSender.getCommandSenderEntity();
      if (commandSenderEntity != null && clazz.isAssignableFrom(commandSenderEntity.getClass()) && n == 1 && list.contains(commandSenderEntity) && !"r".equals(str)) {
         list = Lists.newArrayList(new Entity[]{commandSenderEntity});
      }

      if (n != 0) {
         if (n < 0) {
            Collections.reverse((List<?>)list);
         }

         list = list.subList(0, Math.min(Math.abs(n), list.size()));
      }

      return (List)list;
   }

   public static List matchEntities(ICommandSender commandSender, String str, Class clazz) {
      Matcher matcher = tokenPattern.matcher(str);
      if (matcher.matches() && commandSender.canCommandSenderUseCommand(1, "@")) {
         Map argumentMap = getArgumentMap(matcher.group(2));
         if (!isEntityTypeValid(commandSender, argumentMap)) {
            return Collections.emptyList();
         } else {
            String str2 = matcher.group(1);
            BlockPos posFromArguments = getPosFromArguments(argumentMap, commandSender.getPosition());
            List worlds = getWorlds(commandSender, argumentMap);
            ArrayList list = Lists.newArrayList();

            for (World world : (Iterable<World>)(worlds)) {
               if (world != null) {
                  ArrayList list2 = Lists.newArrayList();
                  list2.addAll(getTypePredicates(argumentMap, str2));
                  list2.addAll(getXpLevelPredicates(argumentMap));
                  list2.addAll(getGamemodePredicates(argumentMap));
                  list2.addAll(getTeamPredicates(argumentMap));
                  list2.addAll(getScorePredicates(argumentMap));
                  list2.addAll(getNamePredicates(argumentMap));
                  list2.addAll(getRadiusPredicates(argumentMap, posFromArguments));
                  list2.addAll(getRotationsPredicates(argumentMap));
                  list.addAll(filterResults(argumentMap, clazz, list2, str2, world, posFromArguments));
               }
            }

            return getEntitiesFromPredicates(list, argumentMap, commandSender, clazz, str2, posFromArguments);
         }
      } else {
         return Collections.emptyList();
      }
   }

   private static List getScorePredicates(Map map) {
      ArrayList list = Lists.newArrayList();
      Map scoreMap = getScoreMap(map);
      if (scoreMap != null && scoreMap.size() > 0) {
         list.add(new PlayerSelector.FilterPredicate6(scoreMap));
      }

      return list;
   }

   public static boolean matchesMultiplePlayers(String str) {
      Matcher matcher = tokenPattern.matcher(str);
      if (!matcher.matches()) {
         return false;
      } else {
         Map argumentMap = getArgumentMap(matcher.group(2));
         String str2 = matcher.group(1);
         int n = !"a".equals(str2) && !"e".equals(str2) ? 1 : 0;
         return parseIntWithDefault(argumentMap, "c", n) != 1;
      }
   }

   public static EntityPlayerMP matchOnePlayer(ICommandSender commandSender, String str) {
      return (EntityPlayerMP)matchOneEntity(commandSender, str, EntityPlayerMP.class);
   }

   private static int parseIntWithDefault(Map map, String str, int n) {
      return map.containsKey(str) ? MathHelper.parseIntWithDefault((String)map.get(str), n) : n;
   }

   private static List getGamemodePredicates(Map map) {
      ArrayList list = Lists.newArrayList();
      int n = parseIntWithDefault(map, "m", WorldSettings.GameType.NOT_SET.getID());
      if (n != WorldSettings.GameType.NOT_SET.getID()) {
         list.add(new PlayerSelector.FilterPredicate4(n));
      }

      return list;
   }

   private static List getNamePredicates(Map map) {
      ArrayList list = Lists.newArrayList();
      String argument = getArgument(map, "name");
      boolean flag = argument != null && argument.startsWith("!");
      if (flag) {
         argument = argument.substring(1);
      }

      if (argument != null) {
         list.add(new PlayerSelector.FilterPredicate7(argument, flag));
      }

      return list;
   }

   private static AxisAlignedBB getArea(BlockPos pos, int n, int n2, int n3) {
      boolean flag = n < 0;
      boolean flag2 = n2 < 0;
      boolean flag3 = n3 < 0;
      int n4 = pos.getX() + (flag ? n : 0);
      int n5 = pos.getY() + (flag2 ? n2 : 0);
      int n6 = pos.getZ() + (flag3 ? n3 : 0);
      int n7 = pos.getX() + (flag ? 0 : n) + 1;
      int n8 = pos.getY() + (flag2 ? 0 : n2) + 1;
      int n9 = pos.getZ() + (flag3 ? 0 : n3) + 1;
      return new AxisAlignedBB((double)n4, (double)n5, (double)n6, (double)n7, (double)n8, (double)n9);
   }


   public static final class FilterPredicate implements Predicate {
      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         return entity instanceof EntityPlayer;
      }
   }

   public static final class FilterPredicate2 implements Predicate {
      public final String entityType;
      public final boolean invert;

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public FilterPredicate2(String str, boolean flag) {
         this.entityType = str;
         this.invert = flag;
      }

      public boolean apply(Entity entity) {
         return EntityList.isStringEntityName(entity, this.entityType) != this.invert;
      }
   }

   public static final class FilterPredicate3 implements Predicate {
      public final int minLevel;
      public final int maxLevel;

      public boolean apply(Entity entity) {
         if (!(entity instanceof EntityPlayerMP)) {
            return false;
         } else {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entity;
            return (this.minLevel <= -1 || entityPlayerMP.experienceLevel >= this.minLevel) && (this.maxLevel <= -1 || entityPlayerMP.experienceLevel <= this.maxLevel);
         }
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public FilterPredicate3(int n, int n2) {
         this.minLevel = n;
         this.maxLevel = n2;
      }
   }

   public static final class FilterPredicate4 implements Predicate {
      public final int gameModeId;

      public boolean apply(Object obj) {
         return this.apply2((Entity)obj);
      }

      public FilterPredicate4(int n) {
         this.gameModeId = n;
      }

      public boolean apply2(Entity entity) {
         if (!(entity instanceof EntityPlayerMP)) {
            return false;
         } else {
            return ((EntityPlayerMP)entity).theItemInWorldManager.getGameType().getID() == this.gameModeId;
         }
      }
   }

   public static final class FilterPredicate5 implements Predicate {
      public final String teamName;
      public final boolean invert;

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         if (!(entity instanceof EntityLivingBase)) {
            return false;
         } else {
            Team team = ((EntityLivingBase)entity).getTeam();
            return (team == null ? "" : team.getRegisteredName()).equals(this.teamName) != this.invert;
         }
      }

      public FilterPredicate5(String str, boolean flag) {
         this.teamName = str;
         this.invert = flag;
      }
   }

   public static final class FilterPredicate6 implements Predicate {
      public final Map scoreMap;

      public FilterPredicate6(Map map) {
         this.scoreMap = map;
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         Scoreboard scoreboard = MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();

         for (Entry entry : (Iterable<Entry>)(this.scoreMap.entrySet())) {
            String key = (String)entry.getKey();
            boolean flag = false;
            if (key.endsWith("_min") && key.length() > 4) {
               flag = true;
               key = key.substring(0, key.length() - 4);
            }

            ScoreObjective objective = scoreboard.getObjective(key);
            if (objective == null) {
               return false;
            }

            String str = entity instanceof EntityPlayerMP ? entity.getName() : entity.getUniqueID().toString();
            if (!scoreboard.entityHasObjective(str, objective)) {
               return false;
            }

            int scorePoints = scoreboard.getValueFromObjective(str, objective).getScorePoints();
            if (scorePoints < (Integer)entry.getValue() && flag) {
               return false;
            }

            if (scorePoints > (Integer)entry.getValue() && !flag) {
               return false;
            }
         }

         return true;
      }
   }

   public static final class FilterPredicate7 implements Predicate {
      public final String entityName;
      public final boolean invert;

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         return entity.getName().equals(this.entityName) != this.invert;
      }

      public FilterPredicate7(String str, boolean flag) {
         this.entityName = str;
         this.invert = flag;
      }
   }

   public static final class FilterPredicate8 implements Predicate {
      public final BlockPos originPos;
      public final int minRadius;
      public final int minRadiusSq;
      public final int maxRadius;
      public final int maxRadiusSq;

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public FilterPredicate8(BlockPos pos, int n, int n2, int n3, int n4) {
         this.originPos = pos;
         this.minRadius = n;
         this.minRadiusSq = n2;
         this.maxRadius = n3;
         this.maxRadiusSq = n4;
      }

      public boolean apply(Entity entity) {
         int distanceSqToCenter = (int)entity.getDistanceSqToCenter(this.originPos);
         return (this.minRadius < 0 || distanceSqToCenter >= this.minRadiusSq) && (this.maxRadius < 0 || distanceSqToCenter <= this.maxRadiusSq);
      }
   }

   public static final class FilterPredicate9 implements Predicate {
      public final int minYaw;
      public final int maxYaw;

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public FilterPredicate9(int n, int n2) {
         this.minYaw = n;
         this.maxYaw = n2;
      }

      public boolean apply(Entity entity) {
         int n = PlayerSelector.fixHorizontalAngle((int)Math.floor((double)entity.rotationYaw));
         return this.minYaw > this.maxYaw ? n >= this.minYaw || n <= this.maxYaw : n >= this.minYaw && n <= this.maxYaw;
      }
   }

   public static final class FilterPredicate10 implements Predicate {
      public final int minPitch;
      public final int maxPitch;

      public FilterPredicate10(int n, int n2) {
         this.minPitch = n;
         this.maxPitch = n2;
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         int n = PlayerSelector.fixHorizontalAngle((int)Math.floor((double)entity.rotationPitch));
         return this.minPitch > this.maxPitch ? n >= this.minPitch || n <= this.maxPitch : n >= this.minPitch && n <= this.maxPitch;
      }
   }

   public static final class FilterPredicate11 implements Predicate {
      public final AxisAlignedBB boundingBox;

      public FilterPredicate11(AxisAlignedBB box) {
         this.boundingBox = box;
      }

      public boolean apply(Object obj) {
         return this.apply((Entity)obj);
      }

      public boolean apply(Entity entity) {
         return entity.posX >= this.boundingBox.minX && entity.posY >= this.boundingBox.minY && entity.posZ >= this.boundingBox.minZ
            ? entity.posX < this.boundingBox.maxX && entity.posY < this.boundingBox.maxY && entity.posZ < this.boundingBox.maxZ
            : false;
      }
   }

   public static final class EntryComparator implements Comparator {
      public final BlockPos originPos;

      public EntryComparator(BlockPos pos) {
         this.originPos = pos;
      }

      public int compare2(Entity entity, Entity entity2) {
         return ComparisonChain.start().compare(entity.getDistanceSq(this.originPos), entity2.getDistanceSq(this.originPos)).result();
      }

      @Override
      public int compare(Object a, Object b) {
         return this.compare2((Entity)a, (Entity)b);
      }
   }
}
