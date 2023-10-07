package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
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

    @ModifyArgs(method = "onPlaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void onPlaced(Args args, World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockPos argBlockPos = args.get(0);
        boolean isWater = world.getFluidState(argBlockPos).getFluid() == Fluids.WATER;
        BlockState argBlockState = args.get(1);
        args.set(1, argBlockState.with(Properties.WATERLOGGED, isWater));
    }

}