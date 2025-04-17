package expresscarts.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
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

    @Unique
    // max speed in blocks per second
    private double expresscarts$MAX_SPEED = 16;

    @Unique
    // max speed multiplier when in water
    private double expresscarts$WATER_SPEED_MULTIPLER = 0.5;

    @Inject(method = "getMaxSpeed", at = @At("HEAD"), cancellable = true)
    // If  we're using this controller for an ExpressMinecartEntity, prevent crashing due to non-existent gamerule
    private void onGetMaxSpeed(CallbackInfoReturnable<Double> cir) {
        if (this.minecart instanceof ExpressMinecartEntity) {
            cir.setReturnValue(this.expresscarts$MAX_SPEED
                    * (this.minecart.isTouchingWater() ? this.expresscarts$WATER_SPEED_MULTIPLER : 1.0) / 20.0);
        }
    }
}
