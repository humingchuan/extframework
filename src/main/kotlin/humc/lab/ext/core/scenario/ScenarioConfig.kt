package humc.lab.ext.core.scenario

import humc.lab.ext.core.model.ExtImpl

/**
 * @author: Mingchuan Hu(Kevin)
 * @date: 2023-11-28 22:41
 * @description
 */
class ScenarioConfig<BizCfg>(
    val bizConfig: BizCfg,
    val extMap: Map<String, List<ExtImpl>>,
    /**
     * use case -> workflow
     */
    val workflowCode: Map<String, String>
)
