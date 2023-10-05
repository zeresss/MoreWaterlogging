package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PistonBlock.class)
public abstract class PistonBlockMixin {

    @ModifyArgs(method = "onSyncedBlockEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void onSyncedBlockEvent(Args args, BlockState state, World world, BlockPos pos, int type, int data) {
        BlockState argBlockState = args.get(1);
        if (!argBlockState.isOf(Blocks.MOVING_PISTON)) return;
        FluidState fluidState = world.getFluidState(args.get(0));
        boolean isWater = fluidState.getFluid() == Fluids.WATER;
        args.set(1, argBlockState.with(Properties.WATERLOGGED, isWater));
    }

    @ModifyArgs(method = "onSyncedBlockEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/PistonExtensionBlock;createBlockEntityPiston(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;ZZ)Lnet/minecraft/block/entity/BlockEntity;"))
    public void onSyncedBlockEntityEvent(Args args, BlockState state, World world, BlockPos pos, int type, int data) { 
        FluidState fluidState = world.getFluidState(args.get(0));
        boolean isWater = fluidState.getFluid() == Fluids.WATER;
        BlockState argBlockState = args.get(2);
        args.set(2, argBlockState.with(Properties.WATERLOGGED, isWater));
    }

}