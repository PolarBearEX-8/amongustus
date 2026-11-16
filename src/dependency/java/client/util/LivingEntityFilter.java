package client.util;

import com.google.common.base.Predicate;

public final class LivingEntityFilter {
   public static final Predicate predicate = new VirtualPredicateAdapter();
   public static final Predicate predicate2 = new StaticPredicateAdapter();
   public static final Predicate predicate3 = new ExtraPredicateAdapter();
   public static final Predicate predicate4 = new CorePredicateAdapter();
}
