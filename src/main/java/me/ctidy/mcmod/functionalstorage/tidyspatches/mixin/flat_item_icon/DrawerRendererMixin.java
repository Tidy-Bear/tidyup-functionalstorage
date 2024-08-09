/*
 * Copyright (c) 2024, Tidy-Bear.
 *
 * This file is part of "Functional Storage: Tidy's Patches".
 *
 * "Functional Storage: Tidy's Patches" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Functional Storage: Tidy's Patches" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "Functional Storage: Tidy's Patches".  If not, see <https://www.gnu.org/licenses/>.
 */

package me.ctidy.mcmod.functionalstorage.tidyspatches.mixin.flat_item_icon;

import com.buuz135.functionalstorage.block.tile.ControllableDrawerTile;
import com.buuz135.functionalstorage.client.DrawerRenderer;
import com.buuz135.functionalstorage.item.ConfigurationToolItem;
import com.buuz135.functionalstorage.util.NumberUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import me.ctidy.mcmod.functionalstorage.tidyspatches.client.ClientConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * DrawerRendererMixin
 *
 * @author Tidy-Bear
 * @since 2024/8/8
 */
@Mixin(value = DrawerRenderer.class, remap = false)
public abstract class DrawerRendererMixin {

    @Shadow
    public static void renderIndicator(PoseStack poseStack, MultiBufferSource buffer, int light, int overlay, float progress, ControllableDrawerTile.DrawerOptions options) {
    }

    @Shadow
    public static void renderText(PoseStack poseStack, MultiBufferSource renderer, int light, Component text, Direction side, float maxScale) {
    }

    // No one will modify them, right?
    private static final Matrix4f SCALE_3D = new Matrix4f().scale(.4F, .4F, .0001F);
    private static final Matrix4f SCALE_FLAT = new Matrix4f().scale(.4F);
    private static final Matrix4f SCALE_TEXT = new Matrix4f().scale(.5F, .5F, 1F);

    @Inject(method = "renderStack", at = @At("HEAD"), cancellable = true)
    private static void renderStack(PoseStack poseStack, MultiBufferSource buffer, int light, int overlay, ItemStack itemStack, int amount, int maxAmount, float scale, ControllableDrawerTile.DrawerOptions options, Level level, CallbackInfo ci) {
        if (!ClientConfig.INSTANCE.usingFlatItemAsIcon.get()) {
            return;
        }
        renderIndicator(poseStack, buffer, light, overlay, Math.min(1, amount / (float) maxAmount), options);

        if (options.isActive(ConfigurationToolItem.ConfigurationAction.TOGGLE_RENDER)) {
            renderIcon(poseStack, buffer, light, overlay, itemStack);
        }

        // count text
        poseStack.pushPose();
        // Using mulPoseMatrix() instead of scale() to avoid lighting issue because of scaling normal matrix
        poseStack.mulPoseMatrix(SCALE_TEXT);
        if (options.isActive(ConfigurationToolItem.ConfigurationAction.TOGGLE_NUMBERS)) {
            renderText(poseStack, buffer, light, Component.literal(NumberUtils.getFormatedBigNumber(amount)).withStyle(ChatFormatting.WHITE), Direction.NORTH, scale);
        }
        poseStack.popPose();
        ci.cancel();
    }

    private static void renderIcon(PoseStack poseStack, MultiBufferSource buffer, int light, int overlay, ItemStack itemStack) {
        Minecraft mc = Minecraft.getInstance();
        ItemRenderer itemRenderer = mc.getItemRenderer();
        BakedModel model = itemRenderer.getModel(itemStack, mc.level, null, 0);

        poseStack.pushPose();
        // Using mulPoseMatrix() instead of scale() to avoid lighting issue because of scaling normal matrix
        poseStack.mulPoseMatrix(model.isGui3d() ? SCALE_3D : SCALE_FLAT);
        itemRenderer.render(itemStack, ItemDisplayContext.GUI, false, poseStack, buffer, light, overlay, model);
        poseStack.popPose();
    }

}
