package patterns.generative.abstractFactory2.website;

import patterns.generative.abstractFactory2.Developer;
import patterns.generative.abstractFactory2.ProjectManager;
import patterns.generative.abstractFactory2.TeamProjectFactory;
import patterns.generative.abstractFactory2.Tester;

public class WebSiteTeamProject implements TeamProjectFactory {
    @Override
    public Developer getDeveloper() {
        return new PhpDeveloper();
    }

    @Override
    public Tester getTester() {
        return new ManualTester();
    }

    @Override
    public ProjectManager getPM() {
        return new WebSitePM();
    }
}
