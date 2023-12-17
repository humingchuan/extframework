package humc.lab.aef.spec

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties

/**
 * @author: humingchuan
 * @date: 2023-12-14 22:38
 * @description
 */
@ConfigurationProperties(prefix = "aef")
class ScenarioConfig {
    var featureMap: MutableMap<String, FeatureSpec> = mutableMapOf()
}