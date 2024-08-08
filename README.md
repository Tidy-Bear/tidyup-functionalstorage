# CTidy's Forge Demo (Forge Mod Dev Template)

CTidy's own Forge mod development template. Implementers should change `mod_id`, `version`, `author`, `licence`, etc., to actual values.

## Built-ins
### Env
- MinecraftForge (build by net.neoforged.gradle)
- SpongePowered Mixin
- ParchmentMC Mappings

### Mod dependencies
Allowed to add / remove freely.
- JEI (JustEnoughItems)
- Jade
- Catalogue
- Configured
- Searchables (Controlling's dependency)
- Controlling
- Spark
- Xaero's Minimap
- Xaero's World Map

## Notes
It's recommended to separate codes of mod itself (such as api), codes depending on Vanilla MC and codes depending on a specific platform (such as Forge, Fabric, etc.), though the template is only for Forge.
When implementing a certain use case, use codes from Vanilla MC instead of platform-specific APIs, unless there are striking defects on Vanilla, or you are ready to coding on each platform.

## LICENSE
The MIT license (LICENSE) applies to the template project itself, whereas the LGPL-v3 license (LICENSE_TEMPLATE) is merely provided as an example and can be freely replaced by actual implementors.
