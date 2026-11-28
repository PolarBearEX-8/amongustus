package baritone.pathing.calc.openset;

import baritone.pathing.calc.PathNode;

public interface IOpenSet {
   void insert(PathNode pathNode);

   void update(PathNode pathNode);

   boolean isEmpty();

   PathNode removeLowest();
}
