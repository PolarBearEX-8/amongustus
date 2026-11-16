package cc.baritone.utils;

import java.util.HashMap;
import java.util.Map;

public class HeuristicPlanner {
   public WAITINGCANCELEDEnum wAITINGCANCELEDEnum;
   public AStarHandler aStarHandler = new AStarHandler();
   public final Map map = new HashMap();

   public HeuristicPlanner withAStarHandler(AStarHandler aStarHandler2) {
      this.aStarHandler = aStarHandler2;
      return this;
   }

   public Map getMap() {
      return this.map;
   }

   public HeuristicPlanner withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum wAITINGCANCELEDEnum2) {
      this.wAITINGCANCELEDEnum = wAITINGCANCELEDEnum2;
      return this;
   }

   public HeuristicPlanner resolveHeuristicPlanner(BaseCustom baseCustom, boolean flag) {
      this.map.put(baseCustom, flag);
      return this;
   }

   public AStarHandler getAStarHandler() {
      return this.aStarHandler;
   }

   public WAITINGCANCELEDEnum getWAITINGCANCELEDEnum() {
      return this.wAITINGCANCELEDEnum;
   }
}
