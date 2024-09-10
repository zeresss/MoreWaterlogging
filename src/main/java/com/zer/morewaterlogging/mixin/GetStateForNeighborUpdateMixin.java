package com.zer.morewaterlogging.mixin;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {
    AbstractPressurePlateBlock.class, BannerBlock.class, BedBlock.class, BellBlock.class, CakeBlock.class,
    CandleCakeBlock.class, CarpetBlock.class, DoorBlock.class, FenceGateBlock.class, PistonHeadBlock.class, WallBannerBlock.class
})
public abstract class GetStateForNeighborUpdateMixin {

    /**
     * @since 1.0.0
     * makes block correctly handle the flowing of water
     */
    @Inject(method = "getStateForNeighborUpdate", at = @At(value = "HEAD"))
    private void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (this instanceof NewWaterloggable && state.contains(Properties.WATERLOGGED) && state.get(Properties.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
    }

}