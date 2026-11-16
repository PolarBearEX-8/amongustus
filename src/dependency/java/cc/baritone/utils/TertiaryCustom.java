package cc.baritone.utils;

import java.util.Objects;
import java.util.Optional;

public class TertiaryCustom {
   public final Custom custom;
   public final HeuristicValidator heuristicValidator;

   public final HeuristicValidator getHeuristicValidator() {
      return this.heuristicValidator;
   }

   public final Optional ofNullable() {
      return Optional.ofNullable(this.custom);
   }

   public TertiaryCustom(HeuristicValidator heuristicValidator2, Custom custom2) {
      Objects.requireNonNull(heuristicValidator2);
      this.custom = custom2;
      this.heuristicValidator = heuristicValidator2;
   }

   public TertiaryCustom(HeuristicValidator heuristicValidator2) {
      this(heuristicValidator2, null);
   }
}
