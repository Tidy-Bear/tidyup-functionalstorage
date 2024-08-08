package me.ctidy.mcmod.demo;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ModEnvConstants
 *
 * @author ctidy
 * @since 2024/6/2
 */
public final class ModEnvConstants {

    public static final String MOD_ID = "ctidydemo";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation id(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    private ModEnvConstants() { }

}
