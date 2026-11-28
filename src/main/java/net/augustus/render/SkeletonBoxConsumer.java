package net.augustus.render;

import java.util.List;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public interface SkeletonBoxConsumer {
   void accept(ModelRenderer modelRenderer, ModelBox modelBox, List list);
}
