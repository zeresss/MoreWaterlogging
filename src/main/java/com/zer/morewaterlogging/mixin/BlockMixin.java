package com.zer.morewaterlogging.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Shadow
    private BlockState defaultState;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(AbstractBlock.Settings settings, CallbackInfo ci) {
        if (this instanceof NewWaterLoggable)
            defaultState = defaultState.with(Properties.WATERLOGGED, false);
    }

    @Inject(method = "setDefaultState", at = @At("TAIL"))
    public void setDefaultState(BlockState state, CallbackInfo ci) {
        if (this instanceof NewWaterLoggable)
            defaultState = defaultState.with(Properties.WATERLOGGED, false);
    }

    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    public void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        if (!(this instanceof NewWaterLoggable)) return;
        BlockState returnValue = cir.getReturnValue();
        if (returnValue == null) return;
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean isWater = fluidState.getFluid().equals(Fluids.WATER);
        cir.setReturnValue(returnValue.with(Properties.WATERLOGGED, isWater));
    }

}