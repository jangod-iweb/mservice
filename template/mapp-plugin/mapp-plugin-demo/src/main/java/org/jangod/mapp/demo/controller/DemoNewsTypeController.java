package {{packageName}}.{{pluginName}}.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.jangod.iweb.core.action.AbstractController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mark
 * @since 2021-08-23
 */
@RestController
@RequestMapping("/{{pluginName}}/demoNewsType")
public class DemoNewsTypeController extends AbstractController {

}

