package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {

    /**
     * @since 1.0.0
     * makes far bed part not copy close bed part waterlogged property
     */
    @ModifyArgs(method = "onPlaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void onPlaced(Args args, World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockState argBlockState = args.get(1);
        boolean isWater = argBlockState.getFluidState().getFluid() == Fluids.WATER;
        args.set(1, argBlockState.with(Properties.WATERLOGGED, isWater));
    }

    /**
     * @since 1.0.1
     * makes far bed part not cause air pocket when bed is broken
     */
    @ModifyArgs(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void onBreak(Args args, World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockState argBlockState = args.get(1);
        if (argBlockState.get(Properties.WATERLOGGED))
            args.set(1, Blocks.WATER.getDefaultState());
    }

}