package com.zer.morewaterlogging.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Block.class)
public abstract class BlockMixin {

    @Shadow private BlockState defaultState;

    /**
     * @since 1.0.0
     * adds waterlogged property to default state
     */
    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void init(AbstractBlock.Settings settings, CallbackInfo ci) {
        if (this instanceof NewWaterloggable && defaultState.contains(Properties.WATERLOGGED)) {
            defaultState = defaultState.with(Properties.WATERLOGGED, false);
        }
    }

    /**
     * @since 1.0.0
     * adds waterlogged property to set default state method
     */
    @Inject(method = "setDefaultState", at = @At(value = "TAIL"))
    private void setDefaultState(BlockState state, CallbackInfo ci) {
        if (this instanceof NewWaterloggable && defaultState.contains(Properties.WATERLOGGED)) {
            defaultState = defaultState.with(Properties.WATERLOGGED, false);
        }
    }

    /**
     * @since 1.0.0
     * makes block initially waterlogged when placed underwater
     */
    @Inject(method = "getPlacementState", at = @At(value = "RETURN"), cancellable = true)
    private void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        if (this instanceof NewWaterloggable && cir.getReturnValue() != null && cir.getReturnValue().contains(Properties.WATERLOGGED)) {
            cir.setReturnValue(cir.getReturnValue().with(Properties.WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER));
        }
    }

}