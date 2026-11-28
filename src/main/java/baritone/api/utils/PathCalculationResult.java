package baritone.api.utils;

import baritone.api.pathing.calc.IPath;
import java.util.Objects;
import java.util.Optional;

public class PathCalculationResult {
   private final IPath path;
   private final PathCalculationResultType type;

   public final Optional getPath() {
      return Optional.ofNullable(this.path);
   }

   public PathCalculationResult(PathCalculationResultType type2, IPath path2) {
      Objects.requireNonNull(type2);
      this.path = path2;
      this.type = type2;
   }

   public final PathCalculationResultType getType() {
      return this.type;
   }

   public PathCalculationResult(PathCalculationResultType type2) {
      this(type2, null);
   }
}
