package com.zer.morewaterlogging.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlowableFluid.class)
public abstract class FlowableFluidMixin {

    @Shadow protected abstract void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state);

    /**
     * @since 1.1.0
     * makes disabled waterlogging blocks to break if they did so before
     */
    @Inject(method = "flow", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FluidFillable;tryFillWithFluid(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/fluid/FluidState;)Z", shift = At.Shift.BEFORE), cancellable = true)
    public void flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci) {
        if (state.contains(Properties.WATERLOGGED)) return;
        this.beforeBreakingBlock(world, pos, state);
        world.setBlockState(pos, fluidState.getBlockState(), 3);
        ci.cancel();
    }

    /**
     * @since 1.1.0
     * makes {@link FlowableFluid#canFill} return {@link net.minecraft.block.Material#blocksMovement} if waterlogging is disabled
     */
    @Inject(method = "canFill", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FluidFillable;canFillWithFluid(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/fluid/Fluid;)Z", shift = At.Shift.BEFORE), cancellable = true)
    private void canFill(BlockView world, BlockPos pos, BlockState state, Fluid fluid, CallbackInfoReturnable<Boolean> cir) {
        if (state.contains(Properties.WATERLOGGED)) return;
        cir.setReturnValue(!state.getMaterial().blocksMovement());
    }

}