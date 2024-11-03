package net.lrsoft.foxbladeex;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import mods.flammpfeil.slashblade.EntityDirectAttackDummy;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;

public class SAspear_ex extends SpecialAttackBase {

    @Override
    public String toString() {
        return "spearex";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.worldObj;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);

        double playerDist = 7.0;
        float attackDist = (float) (playerDist / 3.0);

        if (!player.onGround) playerDist *= 0.35f;
        player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * playerDist;
        player.motionZ = Math.cos(Math.toRadians(player.rotationYaw)) * playerDist;

        if (!world.isRemote) {

            final int cost = -20;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, cost, false)) {
                ItemSlashBlade.damageItem(stack, 10, player);
            }

            ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();

            player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 300, 1, true));
            player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 300, 1, true));
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 250, 1, true));
            EntityDirectAttackDummy entityDA = new EntityDirectAttackDummy(world, player, false);
            entityDA.setLifeTime(7);
            if (entityDA != null) {
                world.spawnEntityInWorld(entityDA);
            }
        }
        world.playSoundAtEntity(player, "random.explode", 2.5F, 1.0F);
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.HiraTuki);
    }
}
