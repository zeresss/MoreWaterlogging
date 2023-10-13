package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CakeBlock.class)
public abstract class CakeBlockMixin {

    /**
     * @since 1.0.0
     * makes candle placing on cake not cause air pocket
     */
    @ModifyArgs(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"))
    public void onUse(Args args, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean isWaterLogged = state.get(Properties.WATERLOGGED);
        BlockState argBlockState = args.get(1);
        args.set(1, argBlockState.with(Properties.WATERLOGGED, isWaterLogged));
    }

    /**
     * @since 1.0.1
     * makes eating when candle is placed on top not cause air pocket
     */
    @ModifyArgs(method = "tryEat", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldAccess;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    private static void tryEat(Args args, WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        FluidState fluidState = world.getFluidState(pos);
        boolean isWater = fluidState.getFluid() == Fluids.WATER;
        BlockState argBlockState = args.get(1);
        args.set(1, argBlockState.with(Properties.WATERLOGGED, isWater));
    }

}