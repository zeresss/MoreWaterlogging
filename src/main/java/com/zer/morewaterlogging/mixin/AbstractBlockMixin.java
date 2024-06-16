package com.zer.morewaterlogging.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {

    /**
     * @since 1.0.0
     * makes block correctly handle the flowing of water
     */
    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"))
    private void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (this instanceof NewWaterloggable && state.contains(Properties.WATERLOGGED) && state.get(Properties.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
    }

    /**
     * @since 1.0.0
     * makes waterlogged block display water
     */
    @Inject(method = "getFluidState", at = @At("RETURN"), cancellable = true)
    private void getFluidState(BlockState state, CallbackInfoReturnable<FluidState> cir) {
        if (this instanceof NewWaterloggable && state.contains(Properties.WATERLOGGED) && state.get(Properties.WATERLOGGED)) {
            cir.setReturnValue(Fluids.WATER.getStill(false));
        }
    }

}