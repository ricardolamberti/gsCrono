package pss.www.platform.actions.resolvers;

import pss.core.win.submits.JAct;
import pss.www.platform.actions.results.JWebActionResult;

public class SubmitActionResolver implements ActionResolverStrategy {
    @Override
    public boolean supports(JDoPssActionResolver context, JAct submit) throws Exception {
        return context.hasToSubmit(submit);
    }

    @Override
    public JWebActionResult handle(JDoPssActionResolver context, JAct submit) throws Throwable {
        return context.processSubmit(submit);
    }
}
