package net.augustus.cosmetics.molang;



import net.augustus.cosmetics.molang.ast.MolangVariable;
public class MolangQueryRegistrar {

   public static void registerVars(MolangParser molangParser) {
      molangParser.register(new MolangVariable("query.anim_time", 0.0));
      molangParser.register(new MolangVariable("query.actor_count", 0.0));
      molangParser.register(new MolangVariable("query.health", 0.0));
      molangParser.register(new MolangVariable("query.max_health", 0.0));
      molangParser.register(new MolangVariable("query.distance_from_camera", 0.0));
      molangParser.register(new MolangVariable("query.yaw_speed", 0.0));
      molangParser.register(new MolangVariable("query.is_in_water_or_rain", 0.0));
      molangParser.register(new MolangVariable("query.is_in_water", 0.0));
      molangParser.register(new MolangVariable("query.is_on_ground", 0.0));
      molangParser.register(new MolangVariable("query.time_of_day", 0.0));
      molangParser.register(new MolangVariable("query.is_on_fire", 0.0));
      molangParser.register(new MolangVariable("query.ground_speed", 0.0));
   }




}
