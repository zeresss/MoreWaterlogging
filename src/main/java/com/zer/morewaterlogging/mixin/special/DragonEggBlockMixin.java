package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BlockState;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(DragonEggBlock.class)
public class DragonEggBlockMixin {

    /**
     * @since 1.1.0
     * makes dragon egg teleport waterlogged only if it teleports in water
     */
    @ModifyArgs(method = "teleport", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void teleport(Args args, BlockState state, World world, BlockPos pos) {
        boolean isWaterlogged = state.get(Properties.WATERLOGGED);
        boolean isOfWater = world.getFluidState(args.get(0)).isOf(Fluids.WATER);
        if (!isWaterlogged && isOfWater)
            args.set(1, state.with(Properties.WATERLOGGED, true));
        else if (isWaterlogged && !isOfWater)
            args.set(1, state.with(Properties.WATERLOGGED, false));
    }

    /**
     * @since 1.1.0
     * makes possible for dragon egg to teleport into water
     */
    @Redirect(method = "teleport", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isAir()Z"))
    public boolean teleportIntoWater(BlockState instance, BlockState state, World world, BlockPos pos) {
        if (instance.getFluidState().isOf(Fluids.WATER))
            return true;
        else
            return instance.isAir();
    }

}