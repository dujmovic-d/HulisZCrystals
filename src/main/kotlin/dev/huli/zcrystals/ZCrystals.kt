package dev.huli.zcrystals


import com.cobblemon.mod.common.config.CobblemonConfig
import com.cobblemon.mod.common.platform.events.PlatformEvents
import com.cobblemon.mod.common.platform.events.ServerEvent
import com.mojang.brigadier.CommandDispatcher
import dev.huli.zcrystals.commands.GiveKeyItemCommand
import dev.huli.zcrystals.config.CobbleTransformationsConfig
import dev.huli.zcrystals.permissions.CobbleTransformationsPermissions
import dev.huli.zcrystals.util.BattleItemUtil
import dev.huli.zcrystals.util.ZCrystalsServerSide
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.server.MinecraftServer
import net.minecraft.server.command.ServerCommandSource
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

object ZCrystals {

    final var MOD_ID = "zcrystals"
    const val MOD_NAME = "ZCrystalsServerSide"
    lateinit var SERVER: MinecraftServer
    val log: Logger = LogManager.getLogger(MOD_NAME)
    lateinit var config: CobbleTransformationsConfig
    lateinit var permissions: CobbleTransformationsPermissions


    lateinit var configDir: File

    fun initialize() {
        config = CobbleTransformationsConfig()
        permissions = CobbleTransformationsPermissions()
        reload()

        if (config.cobbleTransformationsConfigDataManager.SERVERSIDE) {

            PolymerResourcePackUtils.markAsRequired()
            val isModValid = PolymerResourcePackUtils.addModAssets(MOD_ID)
                // Z-Crystals
                ZCrystalsServerSide.requestModel()
                ZCrystalsServerSide.registerItemGroup()

            // Register Battle Items
            BattleItemUtil.registerServerSideItems()
        }
        else{
            BattleItemUtil.registerClientSideItems()
        }

        // Load official Cobblemon's config.
        CobblemonConfig()

        CommandRegistrationCallback.EVENT.register(
            CommandRegistrationCallback { dispatcher, _, _ ->
                registerCommands(
                    dispatcher
                )
            }
        )
        PlatformEvents.SERVER_STARTED.subscribe { started: ServerEvent.Started ->
            run {
                this.SERVER = started.server
                reload()
            }
        }


    }


    private fun reload() {
        initDirs()
        initConfigs()
    }

    private fun initDirs() {
        this.configDir = File("${FabricLoader.getInstance().configDir}/zcrystals/")
        this.configDir.mkdir()
    }

    private fun initConfigs() {
        CobbleTransformationsConfig.writeConfig()
        config = CobbleTransformationsConfig.getConfig()!!
        CobbleTransformationsConfig.updateConfig(config)
    }


    private fun registerCommands(
        dispatcher: CommandDispatcher<ServerCommandSource>
    ) {
        GiveKeyItemCommand().register(dispatcher)
    }
}