package net.a0.a0mod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(FarmlandBlock.class)
public abstract class A0modMixin {

	@WrapOperation(
			method = "fallOn",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/FarmlandBlock;turnToDirt(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
			)
	)
	private void preventMobTrampling(
			Entity sourceEntity,
			BlockState state,
			Level level,
			BlockPos pos,
			Operation<Void> original,
			Level enclosingLevel,
			BlockState enclosingState,
			BlockPos enclosingPos,
			Entity enclosingEntity,
			double fallDistance
	) {
		if (enclosingEntity instanceof Player) {
			original.call(sourceEntity, state, level, pos);
		}
	}
}