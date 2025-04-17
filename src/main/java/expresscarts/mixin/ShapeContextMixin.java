package expresscarts.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import expresscarts.ExpressMinecartEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;

@Mixin(ShapeContext.class)
public interface ShapeContextMixin {
    @ModifyExpressionValue(method = "of(Lnet/minecraft/entity/Entity;)Lnet/minecraft/block/ShapeContext;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;areMinecartImprovementsEnabled(Lnet/minecraft/world/World;)Z"))
    private static boolean handleExpressMinecartEntity(boolean original, Entity entity) {
        return original || entity instanceof ExpressMinecartEntity;
    }
}
