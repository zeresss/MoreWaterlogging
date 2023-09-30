package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Mixin(PistonBlock.class)
public abstract class PistonBlockMixin {

    @ModifyArgs(method = "onSyncedBlockEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void onSyncedBlockEvent(Args args, BlockState state, World world, BlockPos pos, int type, int data) {
        BlockState argBlockState = args.get(1);
        if (!argBlockState.isOf(Blocks.MOVING_PISTON)) return;
        FluidState fluidState = world.getFluidState(args.get(0));
        boolean isWater = fluidState.getFluid().equals(Fluids.WATER);
        args.set(1, argBlockState.with(Properties.WATERLOGGED, isWater));
    }

    @ModifyArgs(method = "onSyncedBlockEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/PistonExtensionBlock;createBlockEntityPiston(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;ZZ)Lnet/minecraft/block/entity/BlockEntity;"))
    public void onSyncedBlockEntityEvent(Args args, BlockState state, World world, BlockPos pos, int type, int data) { 
        FluidState fluidState = world.getFluidState(args.get(0));
        boolean isWater = fluidState.getFluid().equals(Fluids.WATER);
        BlockState argBlockState = args.get(2);
        args.set(2, argBlockState.with(Properties.WATERLOGGED, isWater));
    }

}