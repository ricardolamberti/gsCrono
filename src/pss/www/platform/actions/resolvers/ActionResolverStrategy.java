package pss.www.platform.actions.resolvers;

import pss.core.win.submits.JAct;
import pss.www.platform.actions.results.JWebActionResult;

public interface ActionResolverStrategy {
    boolean supports(JDoPssActionResolver context, JAct submit) throws Exception;
    JWebActionResult handle(JDoPssActionResolver context, JAct submit) throws Throwable;
}
