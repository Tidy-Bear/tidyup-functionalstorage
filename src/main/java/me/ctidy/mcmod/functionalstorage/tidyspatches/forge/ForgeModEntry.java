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

package me.ctidy.mcmod.functionalstorage.tidyspatches.forge;

import me.ctidy.mcmod.functionalstorage.tidyspatches.ModEnvConstants;
import me.ctidy.mcmod.functionalstorage.tidyspatches.client.ClientConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLEnvironment;

/**
 * ForgeModEntry
 *
 * @author Tidy-Bear
 * @since 2024/8/8
 */
@Mod(ModEnvConstants.MOD_ID)
public class ForgeModEntry {

    public ForgeModEntry() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(
                () -> "ANY", (remote, isServer) -> true
        ));
        if (Dist.CLIENT == FMLEnvironment.dist) {
            ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        }
    }

}
