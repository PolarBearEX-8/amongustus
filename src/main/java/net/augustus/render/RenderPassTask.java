package net.augustus.render;


import net.augustus.utils.render.ShaderRenderPass;
public interface RenderPassTask {
   void execute(ShaderRenderPass shaderRenderPass);
}
