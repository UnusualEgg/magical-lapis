package io.github.unusualegg.magicallapis.mixin;

import io.github.unusualegg.magicallapis.CanDespawnAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LapisInteractLivingEntity implements CanDespawnAccessor {
    @Unique
    protected boolean shouldDespawn = true;

    @Override
    public void setCanDespawn_magicallapis(boolean canDespawn) {
        shouldDespawn = canDespawn;
    }

    @Override
    public boolean getCanDespawn_magicallapis() {
        return shouldDespawn;
    }

    @Inject(method = "canDespawn", at = @At(value = "RETURN"), cancellable = true)
    private void canDespawnMixin(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(shouldDespawn);
    }

    @Inject(method = "writeNbt", at=@At(value="TAIL"))
    public void writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("canDespawn", shouldDespawn);
    }
    @Inject(method = "readNbt", at=@At(value="TAIL"))
    public void readNbt(NbtCompound nbt, CallbackInfo ci) {
        shouldDespawn = nbt.getBoolean("canDespawn");
    }

    //FIXME: this method gets overridden
    @Inject(method = "getDroppedItemId", at = @At(value = "RETURN"), cancellable = true)
    protected void getDroppedItemIdMixin(CallbackInfoReturnable<Integer> cir) {
        if (!shouldDespawn) {
            cir.setReturnValue(-1);
        }
    }

}
