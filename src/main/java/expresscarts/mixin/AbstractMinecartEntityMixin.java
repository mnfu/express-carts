package expresscarts.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import expresscarts.ExpressMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.world.World;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends VehicleEntity {
    public AbstractMinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @ModifyExpressionValue(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;areMinecartImprovementsEnabled(Lnet/minecraft/world/World;)Z"))
    // Whenever areMinecartImprovementsEnabled is called inside AbstractMinecartEntity, also evaluate to true if the minecart is an ExpressMinecartEntity
    private boolean fakeImprovementsEnabledForExpressMinecartEntity(boolean original) {
        return ((AbstractMinecartEntity) (Object) this) instanceof ExpressMinecartEntity || original;
    }

}
