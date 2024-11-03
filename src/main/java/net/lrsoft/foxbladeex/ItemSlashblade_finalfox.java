package net.lrsoft.foxbladeex;

import net.lrsoft.foxbladeex.hypersystem.HS_advance;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.stats.AchievementList;

public class ItemSlashblade_finalfox {

    public static ItemSlashBladeNamed bladeNamed;
    public static ISpecialEffect HS_advance = SpecialEffects.register(new HS_advance());

    @SubscribeEvent
    public void init(InitEvent event) {
        String name2 = "flammpfeil.slashblade.named.foxex2";
        String name1 = "flammpfeil.slashblade.named.foxex";
        String name = "flammpfeil.slashblade.named.foxfinal";
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        ///////////////////////////////////////////////////////////////////////////////////////////
        ItemSlashBlade.specialAttacks.put(Integer.valueOf(12077), new SAsakuraend_ex());
        NBTTagCompound tag = new NBTTagCompound();// NBT
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(242));// 242
        ItemSlashBlade.setBaseAttackModifier(tag, 15.0f);//
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));//
        ItemSlashBlade.TextureName.set(tag, "named/foxex/texture3");
        ItemSlashBlade.ModelName.set(tag, "named/foxex/model2");
        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(12077));// saex
        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(1));//
        ItemSlashBlade.SummonedSwordColor.set(tag, 2302862);
        SpecialEffects.addEffect(customblade, HS_advance);
        GameRegistry.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add(name);
        customblade.addEnchantment(Enchantment.smite, 10);
        customblade.addEnchantment(Enchantment.baneOfArthropods, 10);
        customblade.addEnchantment(Enchantment.sharpness, 8);
        customblade.addEnchantment(Enchantment.power, 5);//
        customblade.addEnchantment(Enchantment.fireAspect, 5);
        customblade.addEnchantment(Enchantment.unbreaking, 3);
        /////////////////////////////////////////////////////////////////////////////////////////////
        ItemStack need1 = GameRegistry.findItemStack(SlashBlade.modid, name1, 0);
        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(need1);//
        ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(4500));
        ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(18));
        ItemSlashBladeNamed.IsDefaultBewitched.set(reqTag, Boolean.valueOf(true));

        ItemStack need2 = GameRegistry.findItemStack(SlashBlade.modid, name2, 1);
        NBTTagCompound reqTag2 = ItemSlashBlade.getItemTagCompound(need2);//
        ItemSlashBlade.KillCount.set(reqTag2, Integer.valueOf(4500));
        ItemSlashBlade.RepairCount.set(reqTag2, Integer.valueOf(18));
        ItemSlashBladeNamed.IsDefaultBewitched.set(reqTag2, Boolean.valueOf(true));

        ItemStack foxex = GameRegistry.findItemStack("flammpfeil.slashblade", name, 1);
        NBTTagCompound reqTag3 = ItemSlashBlade.getItemTagCompound(foxex);
        SlashBlade.addRecipe(
            name,
            new RecipeAwakeBlade(
                foxex,
                need1,
                new Object[] { "CXC", "ABA", "CEC", Character.valueOf('X'), new ItemStack(Blocks.dragon_egg),
                    Character.valueOf('B'), need1, Character.valueOf('C'), new ItemStack(Blocks.diamond_block),
                    Character.valueOf('E'), new ItemStack(Blocks.glowstone), Character.valueOf('A'),
                    new ItemStack(Blocks.obsidian), }));

        SlashBlade.addRecipe(
            name,
            new RecipeAwakeBlade(
                foxex,
                need2,
                new Object[] { "CXC", "ABA", "CEC", Character.valueOf('X'), new ItemStack(Blocks.dragon_egg),
                    Character.valueOf('B'), need2, Character.valueOf('C'), new ItemStack(Blocks.diamond_block),
                    Character.valueOf('A'), new ItemStack(Blocks.glowstone), Character.valueOf('E'),
                    new ItemStack(Blocks.obsidian), }));

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        NamedBladeManager.registerBladeSoul(tag, "foxfinal");//
        AchievementList.registerCraftingAchievement(name, customblade, AchievementList.getAchievement(name));
    }

}
