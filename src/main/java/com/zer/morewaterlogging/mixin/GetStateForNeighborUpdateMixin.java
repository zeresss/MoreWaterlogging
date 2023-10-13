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

@Mixin({
    AbstractPressurePlateBlock.class, BannerBlock.class, BedBlock.class, BellBlock.class, CakeBlock.class,
    CandleCakeBlock.class, CarpetBlock.class, DoorBlock.class, FenceGateBlock.class, FlowerPotBlock.class,
    PistonHeadBlock.class, WallRedstoneTorchBlock.class, RedstoneWireBlock.class, RepeaterBlock.class, TorchBlock.class,
    VineBlock.class, WallBannerBlock.class, TorchBlock.class, WallTorchBlock.class, TorchBlock.class
})
public abstract class GetStateForNeighborUpdateMixin {

    /**
     * @since 1.0.0
     * makes block correctly handle the flowing of water
     */
    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"))
    public void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (this instanceof NewWaterLoggable) if (state.get(Properties.WATERLOGGED))
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
    }

}