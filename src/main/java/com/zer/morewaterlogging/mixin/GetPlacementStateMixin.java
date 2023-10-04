package com.zer.morewaterlogging.mixin;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {
    AbstractRedstoneGateBlock.class, AnvilBlock.class, BannerBlock.class, BedBlock.class,
    BellBlock.class, DoorBlock.class, EndPortalFrameBlock.class, EndRodBlock.class, FenceGateBlock.class,
    HopperBlock.class, LeavesBlock.class, LecternBlock.class, PistonBlock.class, RedstoneWireBlock.class,
    RepeaterBlock.class, SkullBlock.class, StonecutterBlock.class, VineBlock.class, WallBannerBlock.class,
    WallMountedBlock.class, WallRedstoneTorchBlock.class, WallSkullBlock.class, WallTorchBlock.class
})
public abstract class GetPlacementStateMixin {

    @Inject(method = "getPlacementState(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/block/BlockState;", at = @At("RETURN"), cancellable = true)
    public void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        BlockState returnValue = cir.getReturnValue();
        if (returnValue == null) return;
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean isWater = fluidState.getFluid().equals(Fluids.WATER);
        cir.setReturnValue(returnValue.with(Properties.WATERLOGGED, isWater));
    }

}