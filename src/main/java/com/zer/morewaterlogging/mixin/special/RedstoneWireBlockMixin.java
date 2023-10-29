package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RedstoneWireBlock.class)
public abstract class RedstoneWireBlockMixin {

    /**
     * @since 1.0.0
     * adds waterlogged property to default wire state
     */
    @Inject(method = "getDefaultWireState", at = @At("RETURN"), cancellable = true)
    public void getDefaultWireState(BlockView world, BlockState state, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        if (world.getFluidState(pos).getFluid() == Fluids.WATER)
            cir.setReturnValue(cir.getReturnValue().with(Properties.WATERLOGGED, true));
    }

}