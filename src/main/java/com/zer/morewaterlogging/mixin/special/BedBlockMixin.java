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
     * makes head bed part not copy foot bed part waterlogged property
     */
    @ModifyArgs(method = "onPlaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void onPlaced(Args args, World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (world.getFluidState(args.get(0)).getFluid() == Fluids.WATER)
            args.set(1, args.<BlockState>get(1).with(Properties.WATERLOGGED, true));
    }

    /**
     * @since 1.0.1
     * makes head bed part not cause air pocket when bed is broken
     */
    @ModifyArgs(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void onBreak(Args args, World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (world.getFluidState(args.get(0)).getFluid() == Fluids.WATER)
            args.set(1, Blocks.WATER.getDefaultState());
    }

}