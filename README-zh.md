# CTidy's Forge Demo （Forge Mod 开发模板）

CTidy（熊老师）自己的 Forge Mod 开发模板。具体开发时，请将 `mod_id`、`version`、`author`、`licence` 等内容修改为实际值。

## 预置组件
### 开发环境
- MinecraftForge (通过 net.neoforged.gradle 构建)
- SpongePowered Mixin
- ParchmentMC Mappings

### Mod 依赖
可以按需要自由增删。
- JEI (JustEnoughItems)
- Jade
- Catalogue
- Configured
- Searchables (Controlling 前置)
- Controlling
- Spark
- Xaero's Minimap
- Xaero's World Map

## 备注
推荐将 Mod 本身的代码（如 api）、依赖于原版 MC 的代码 以及 依赖于指定平台（如 Forge、Fabric 等）的代码相分离，即便本模板是 Forge 专供。
当实现某个功能用例时，尽量优先使用原版 MC 的代码，再考虑指定平台的 API，除非原版代码具有过于显著的缺陷，或者你准备好在每个平台上都进行一次实现。

## 开源协议
MIT 协议 (LICENSE) 用于当前模板项目本身，而 LGPL-v3 协议 (LICENSE_TEMPLATE) 仅为示例，允许实现者自由替换。
