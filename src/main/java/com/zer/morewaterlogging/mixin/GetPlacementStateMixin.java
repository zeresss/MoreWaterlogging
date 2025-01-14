package com.zer.morewaterlogging.mixin;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {
    AnvilBlock.class, BannerBlock.class, BedBlock.class, BellBlock.class, DoorBlock.class, EndPortalFrameBlock.class,
    EndRodBlock.class, FenceGateBlock.class, HopperBlock.class, LeavesBlock.class, LecternBlock.class, PistonBlock.class,
    SkullBlock.class, StonecutterBlock.class, ShulkerBoxBlock.class, WallBannerBlock.class, WallMountedBlock.class, WallSkullBlock.class
})
public abstract class GetPlacementStateMixin {

    /**
     * @since 1.0.0
     * makes block initially waterlogged when placed underwater
     */
    @Inject(method = "getPlacementState(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/block/BlockState;", at = @At(value = "RETURN"), cancellable = true)
    private void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        if (this instanceof NewWaterloggable && cir.getReturnValue() != null && cir.getReturnValue().contains(Properties.WATERLOGGED)) {
            cir.setReturnValue(cir.getReturnValue().with(Properties.WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER));
        }
    }

}