package net.kaupenjoe.resourceslimes.data;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.data.custom.GemCuttingRecipeBuilder;
import net.kaupenjoe.resourceslimes.data.custom.GemInfusingRecipeBuilder;
import net.kaupenjoe.resourceslimes.data.custom.SlimeExtractCleaningRecipeBuilder;
import net.kaupenjoe.resourceslimes.data.custom.SlimeIncubationRecipeBuilder;
import net.kaupenjoe.resourceslimes.fluid.ModFluids;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.util.ModTags;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        this.customOreSmeltingRecipes(pFinishedRecipeConsumer);
        this.customOreBlastingRecipes(pFinishedRecipeConsumer);

        this.customGemCuttingRecipes(pFinishedRecipeConsumer);
        this.customGemInfusingRecipes(pFinishedRecipeConsumer);
        this.customExtractCleaningRecipes(pFinishedRecipeConsumer);
        this.customIncubationRecipes(pFinishedRecipeConsumer);
    }

    private void customIncubationRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        new SlimeIncubationRecipeBuilder(ModItems.ENERGY_SLIME_SPAWN_EGG.get(), 1,
                Items.SLIME_BALL, Items.IRON_INGOT, ModItems.CUT_CITRINE.get())
                .unlockedBy("iron_ingot", has(Items.SLIME_BALL)).save(pFinishedRecipeConsumer);
    }

    private void customExtractCleaningRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        var resources = SlimeResource.values();
        for (var resource : resources) {
            if(resource.equals(SlimeResource.EMPTY)) {
                continue;
            }
            
            ItemLike input = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ResourceSlimes.MOD_ID,
                    "slimey_" + resource.name().toLowerCase() + "_extract"));
            ItemLike output = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ResourceSlimes.MOD_ID,
                    resource.name().toLowerCase() + "_extract"));

            new SlimeExtractCleaningRecipeBuilder(input, output, 1)
                    .unlockedBy("has_slimey_" + resource.name().toLowerCase() + "_extract",
                            has(input)).save(pFinishedRecipeConsumer);
        }
    }

    // TODO: inventoryTrigger -> has
    private void customGemInfusingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        new GemInfusingRecipeBuilder(ModItems.CUT_CITRINE.get(), ModItems.INFUSED_CITRINE.get(), 1,
                new FluidStack(ModFluids.CITRINE_SLIME_FLUID.get(), 500))
                .unlockedBy("has_cut_citrine", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CUT_CITRINE.get()).build())).save(pFinishedRecipeConsumer);
        new GemInfusingRecipeBuilder(ModItems.CUT_ZIRCON.get(), ModItems.INFUSED_ZIRCON.get(), 1,
                new FluidStack(ModFluids.ZIRCON_SLIME_FLUID.get(), 500))
                .unlockedBy("has_cut_zircon", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CUT_ZIRCON.get()).build())).save(pFinishedRecipeConsumer);

        new GemInfusingRecipeBuilder(ModItems.CUT_DIAMOND.get(), ModItems.INFUSED_DIAMOND.get(), 1,
                new FluidStack(ModFluids.DIAMOND_SLIME_FLUID.get(), 500))
                .unlockedBy("has_cut_diamond", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CUT_DIAMOND.get()).build())).save(pFinishedRecipeConsumer);
        new GemInfusingRecipeBuilder(ModItems.CUT_EMERALD.get(), ModItems.INFUSED_EMERALD.get(), 1,
                new FluidStack(ModFluids.EMERALD_SLIME_FLUID.get(), 500))
                .unlockedBy("has_cut_emerald", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CUT_DIAMOND.get()).build())).save(pFinishedRecipeConsumer);

        new GemInfusingRecipeBuilder(ModItems.CUT_TANZANITE.get(), ModItems.INFUSED_TANZANITE.get(), 1,
                new FluidStack(ModFluids.TANZANITE_SLIME_FLUID.get(), 500))
                .unlockedBy("has_cut_diamond", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CUT_TANZANITE.get()).build())).save(pFinishedRecipeConsumer);
        new GemInfusingRecipeBuilder(ModItems.CUT_BLACK_OPAL.get(), ModItems.INFUSED_BLACK_OPAL.get(), 1,
                new FluidStack(ModFluids.BLACK_OPAL_SLIME_FLUID.get(), 500))
                .unlockedBy("has_cut_black_opal", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CUT_BLACK_OPAL.get()).build())).save(pFinishedRecipeConsumer);

        new GemInfusingRecipeBuilder(ModItems.PINK_SLIME_PEARL.get(), ModItems.INFUSED_PINK_SLIME_PEARL.get(), 1,
                new FluidStack(ModFluids.PINK_SLIME_PEARL_SLIME_FLUID.get(), 500))
                .unlockedBy("has_pink_slime_pearl", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.PINK_SLIME_PEARL.get()).build())).save(pFinishedRecipeConsumer);
    }

    private void customGemCuttingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        new GemCuttingRecipeBuilder(ModItems.UNCUT_CITRINE.get(), ModItems.CUT_CITRINE.get(), 1, 250)
                .unlockedBy("has_uncut_citrine", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.UNCUT_CITRINE.get()).build())).save(pFinishedRecipeConsumer);
        new GemCuttingRecipeBuilder(ModItems.UNCUT_ZIRCON.get(), ModItems.CUT_ZIRCON.get(), 1, 250)
                .unlockedBy("has_uncut_zircon", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.UNCUT_ZIRCON.get()).build())).save(pFinishedRecipeConsumer);

        new GemCuttingRecipeBuilder(Items.DIAMOND, ModItems.CUT_DIAMOND.get(), 1, 500)
                .unlockedBy("has_diamond", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DIAMOND).build())).save(pFinishedRecipeConsumer);
        new GemCuttingRecipeBuilder(Items.EMERALD, ModItems.CUT_EMERALD.get(), 1, 500)
                .unlockedBy("has_emerald", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.EMERALD).build())).save(pFinishedRecipeConsumer);

        new GemCuttingRecipeBuilder(ModItems.UNCUT_TANZANITE.get(), ModItems.CUT_TANZANITE.get(), 1, 1000)
                .unlockedBy("has_uncut_tanzanite", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.UNCUT_TANZANITE.get()).build())).save(pFinishedRecipeConsumer);
        new GemCuttingRecipeBuilder(ModItems.UNCUT_BLACK_OPAL.get(), ModItems.CUT_BLACK_OPAL.get(), 1, 1000)
                .unlockedBy("has_uncut_black_opal", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.UNCUT_BLACK_OPAL.get()).build())).save(pFinishedRecipeConsumer);
    }

    private void customOreSmeltingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        oreSmelting(pFinishedRecipeConsumer, List.of(ModBlocks.CITRINE_ORE.get(), ModBlocks.DEEPSLATE_CITRINE_ORE.get(), ModItems.RAW_CITRINE.get()),
                ModItems.UNCUT_CITRINE.get(),0.35F, 200, "citrine");
        oreSmelting(pFinishedRecipeConsumer, List.of(ModBlocks.ZIRCON_ORE.get(), ModBlocks.DEEPSLATE_ZIRCON_ORE.get(), ModItems.RAW_ZIRCON.get()),
                ModItems.UNCUT_ZIRCON.get(),0.5F, 200, "zircon");
        oreSmelting(pFinishedRecipeConsumer, List.of(ModBlocks.TANZANITE_ORE.get(), ModBlocks.DEEPSLATE_TANZANITE_ORE.get(), ModItems.RAW_TANZANITE.get()),
                ModItems.UNCUT_TANZANITE.get(),0.75F, 200, "tanzanite");
        oreSmelting(pFinishedRecipeConsumer, List.of(ModBlocks.BLACK_OPAL_ORE.get(), ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get(),
                        ModBlocks.END_BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()),
                ModItems.UNCUT_BLACK_OPAL.get(),1.15F, 200, "black_opal");
    }

    private void customOreBlastingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        oreBlasting(pFinishedRecipeConsumer, List.of(ModBlocks.CITRINE_ORE.get(), ModBlocks.DEEPSLATE_CITRINE_ORE.get(), ModItems.RAW_CITRINE.get()),
                ModItems.UNCUT_CITRINE.get(),0.35F, 100, "citrine");
        oreBlasting(pFinishedRecipeConsumer, List.of(ModBlocks.ZIRCON_ORE.get(), ModBlocks.DEEPSLATE_ZIRCON_ORE.get(), ModItems.RAW_ZIRCON.get()),
                ModItems.UNCUT_ZIRCON.get(),0.5F, 100, "zircon");
        oreBlasting(pFinishedRecipeConsumer, List.of(ModBlocks.TANZANITE_ORE.get(), ModBlocks.DEEPSLATE_TANZANITE_ORE.get(), ModItems.RAW_TANZANITE.get()),
                ModItems.UNCUT_TANZANITE.get(),0.75F, 100, "tanzanite");
        oreBlasting(pFinishedRecipeConsumer, List.of(ModBlocks.BLACK_OPAL_ORE.get(), ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get(),
                        ModBlocks.END_BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()),
                ModItems.UNCUT_BLACK_OPAL.get(),1.15F, 100, "black_opal");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE,
                pIngredients, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                      ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                                     SimpleCookingSerializer<?> pCookingSerializer, List<ItemLike> pIngredients,
                                     ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(itemlike), pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer,
                            ResourceSlimes.MOD_ID + ":" + (pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}