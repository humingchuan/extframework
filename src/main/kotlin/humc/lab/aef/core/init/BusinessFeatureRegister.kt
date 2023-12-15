package humc.lab.aef.core.init

import humc.lab.aef.core.ext.ExtensionCenter
import humc.lab.aef.core.ext.api.Extension
import humc.lab.aef.core.scenario.BusinessFeature
import humc.lab.aef.core.scenario.BusinessProduct
import humc.lab.aef.core.scenario.BusinessScenario
import humc.lab.aef.core.scenario.ScenarioCenter
import humc.lab.aef.spec.FeatureSpec
import humc.lab.aef.spec.ScenarioConfig
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

/**
 * @author: humingchuan
 * @date: 2023-12-14 22:37
 * @description
 */
@Component
class BusinessFeatureRegister(
    private val extensionCenter: ExtensionCenter,
    private val scenarioCenter: ScenarioCenter
) :
    ApplicationListener<ContextRefreshedEvent>,
    ApplicationContextAware {
    private lateinit var ctx: ApplicationContext
    private var initialized: Boolean = false
    fun register() {
        val cfg = ScenarioConfig()
        val scenario1 = FeatureSpec()
        scenario1.code = "scenario1"
        scenario1.packagePrefix = "com.example.demo.scenario1"
        cfg.featureMap["scenario1"] = scenario1
        val scenario2 = FeatureSpec()
        scenario2.code = "scenario2"
        scenario2.packagePrefix = "com.example.demo.scenario2"
        cfg.featureMap["scenario2"] = scenario2
        val business1 = FeatureSpec()
        scenario2.code = "business1"
        scenario2.packagePrefix = "com.example.demo.business1"
        cfg.featureMap["business1"] = business1

        register(cfg)
    }

    fun register(cfg: ScenarioConfig) {
        val featureList = ctx.getBeansOfType(BusinessFeature::class.java)
        val extensionList = ctx.getBeansOfType(Extension::class.java)

        for (feature in featureList.values) {
            if (feature is BusinessScenario) {
                scenarioCenter.addScenario(feature)
            } else if (feature is BusinessProduct) {
                scenarioCenter.addProduct(feature)
            }
        }

        val packagesOfFeatures = mutableMapOf<String, FeatureSpec>()
        for (entry in cfg.featureMap.entries) {
            val feature = entry.value
            packagesOfFeatures[feature.packagePrefix] = feature
        }

        val featureToExtensions = mutableMapOf<String, MutableList<Extension<*>>>()
        extensionList.values.forEach { ext ->
            val packageName = ext::class.java.`package`.name
            packagesOfFeatures.entries.forEach { feature ->
                val featurePackage = feature.key
                if (packageName.contains(featurePackage)) {
                    val extList = featureToExtensions.computeIfAbsent(feature.value.code) { ArrayList() }
                    extList.add(ext)
                }
            }
        }

        for (entry in featureToExtensions.entries) {
            val feature = entry.key
            val extensions = entry.value
            extensions.forEach {
                extensionCenter.register(feature, it)
            }
        }
    }

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (initialized) {
            return
        }
        register()
        initialized = true
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.ctx = applicationContext
    }
}