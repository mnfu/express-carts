package expresscarts.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;

import expresscarts.ExpressMinecartEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.MinecartItem;

@Mixin(MinecartItem.class)
public abstract class MinecartItemMixin {
    @ModifyExpressionValue(method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;areMinecartImprovementsEnabled(Lnet/minecraft/world/World;)Z"))
    private boolean fakeImprovementsEnabledForExpressMinecartEntity(boolean original,
            @Local AbstractMinecartEntity abstractMinecartEntity) {
        return original || abstractMinecartEntity instanceof ExpressMinecartEntity;
    }

}
