package expresscarts.mixin;

import expresscarts.ExpressCarts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import expresscarts.ExpressMinecartEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.ExperimentalMinecartController;
import net.minecraft.entity.vehicle.MinecartController;

@Mixin(ExperimentalMinecartController.class)
public abstract class ExperimentalMinecartControllerMixin extends MinecartController {
    protected ExperimentalMinecartControllerMixin(AbstractMinecartEntity minecart) {
        super(minecart);
    }

    @Inject(method = "getMaxSpeed", at = @At("HEAD"), cancellable = true)
    // If  we're using this controller for an ExpressMinecartEntity, prevent crashing due to a non-existent gamerule
    private void onGetMaxSpeed(CallbackInfoReturnable<Double> cir) {
        if (this.minecart instanceof ExpressMinecartEntity) {
            cir.setReturnValue(ExpressCarts.MAX_MINECART_SPEED
                    * (this.minecart.isTouchingWater() ? ExpressCarts.WATER_SPEED_MULTIPLIER : 1.0) / 20.0);
        }
    }
}
