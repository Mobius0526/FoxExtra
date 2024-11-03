package net.lrsoft.foxbladeex;

import net.lrsoft.foxbladeex.hypersystem.HS_basic;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.named.Fox;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.stats.AchievementList;

public class ItemSlashblade_foxex {

    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect HS_potion = SpecialEffects.register(new HS_basic());

    @SubscribeEvent
    public void init(InitEvent event) {
        String name = "flammpfeil.slashblade.named.foxex";//
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        ///////////////////////////////////////////////////////////////////////////////////////////
        ItemSlashBlade.specialAttacks.put(Integer.valueOf(12075), new SAslashdimension_ex());
        NBTTagCompound tag = new NBTTagCompound();// NBT
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(141));// 141
        ItemSlashBlade.setBaseAttackModifier(tag, 12.0f);// +12s
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));// 声明妖刀
        ItemSlashBlade.TextureName.set(tag, "named/foxex/texture");
        ItemSlashBlade.ModelName.set(tag, "named/foxex/model");
        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(12075));// saex
        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(1));//
        ItemSlashBlade.SummonedSwordColor.set(tag, 16711935);
        SpecialEffects.addEffect(customblade, HS_potion);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add(name);
        customblade.addEnchantment(Enchantment.power, 3);//
        customblade.addEnchantment(Enchantment.unbreaking, 3);
        customblade.addEnchantment(Enchantment.looting, 3);
        customblade.addEnchantment(Enchantment.sharpness, 6);
        /////////////////////////////////////////////////////////////////////////////////////////////
        ItemStack custombladeReqired2 = GameRegistry.findItemStack(SlashBlade.modid, Fox.nameWhite, 1);
        NBTTagCompound reqTag2 = ItemSlashBlade.getItemTagCompound(custombladeReqired2);//
        ItemSlashBlade.KillCount.set(reqTag2, Integer.valueOf(1000));
        ItemSlashBlade.ProudSoul.set(reqTag2, Integer.valueOf(12000));
        ItemSlashBlade.RepairCount.set(reqTag2, Integer.valueOf(6));
        ItemSlashBladeNamed.IsDefaultBewitched.set(reqTag2, Boolean.valueOf(true));
        ItemStack foxex = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        SlashBlade.addRecipe(
            name,
            new RecipeAwakeBlade(
                foxex,
                custombladeReqired2,
                new Object[] { "CAC", "XBX", "CXC", Character.valueOf('A'), new ItemStack(Items.nether_star),
                    Character.valueOf('B'), custombladeReqired2, Character.valueOf('C'),
                    new ItemStack(Blocks.glowstone), Character.valueOf('X'), new ItemStack(Blocks.diamond_block) }));

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        NamedBladeManager.registerBladeSoul(tag, "foxex");
        AchievementList.registerCraftingAchievement(name, customblade, AchievementList.getAchievement(name));
    }

}
