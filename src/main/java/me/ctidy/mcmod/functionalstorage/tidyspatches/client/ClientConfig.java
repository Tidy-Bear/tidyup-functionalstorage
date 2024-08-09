/*
 * Copyright (c) 2024, Tidy-Bear.
 *
 * This file is part of "Functional Storage: Tidy's Patches".
 *
 * "Functional Storage: Tidy's Patches" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Functional Storage: Tidy's Patches" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "Functional Storage: Tidy's Patches".  If not, see <https://www.gnu.org/licenses/>.
 */

package me.ctidy.mcmod.functionalstorage.tidyspatches.client;

import com.electronwill.nightconfig.core.Config;
import me.ctidy.mcmod.functionalstorage.tidyspatches.ModEnvConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * ClientConfig
 *
 * @author Tidy-Bear
 * @since 2024/8/8
 */
@OnlyIn(Dist.CLIENT)
public class ClientConfig {

    public static final ForgeConfigSpec SPEC;
    public static final ClientConfig INSTANCE;

    public final ForgeConfigSpec.BooleanValue usingFlatItemAsIcon;

    static {
        Config.setInsertionOrderPreserved(true);
        Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        SPEC = specPair.getRight();
        INSTANCE = specPair.getLeft();
    }

    private static String nameTKey(final String key) {
        return ModEnvConstants.MOD_ID + ".config." + key;
    }

    private static String sectionTKey(final String key) {
        return nameTKey("section." + key);
    }

    private static String commentTKey(final String key) {
        return nameTKey(key) + ".tooltip";
    }

    public ClientConfig(ForgeConfigSpec.Builder builder) {
        builder.push("display").translation(sectionTKey("display"));

        usingFlatItemAsIcon = builder.comment("Whether using flat item texture (like in GUI) as drawer icons.",
                        "Changing this to false to turn back to the original rendering.",
                        "[Default: true]")
                .translation(nameTKey("flat_item_icon"))
                .define("flat_item_icon", true);

        builder.pop();
    }

}
