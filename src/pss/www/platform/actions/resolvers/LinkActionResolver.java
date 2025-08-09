package pss.www.platform.actions.resolvers;

import pss.core.win.submits.JAct;
import pss.core.win.submits.JActExternalLink;
import pss.www.platform.actions.results.JWebActionResult;

public class LinkActionResolver implements ActionResolverStrategy {
    @Override
    public boolean supports(JDoPssActionResolver context, JAct submit) {
        return submit instanceof JActExternalLink;
    }

    @Override
    public JWebActionResult handle(JDoPssActionResolver context, JAct submit) throws Throwable {
        return context.processRedirectLink(submit);
    }
}
