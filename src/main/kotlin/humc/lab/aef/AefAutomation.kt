package humc.lab.aef

import humc.lab.aef.spec.ScenarioConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * @author: humingchuan
 * @date: 2023-12-14 22:08
 * @description
 */
@Configuration
@ComponentScan("humc.lab.aef")
@EnableConfigurationProperties(ScenarioConfig::class)
class AefAutomation {
}