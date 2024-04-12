package patterns.generative.abstractFactory2.bank;

import patterns.generative.abstractFactory2.Developer;
import patterns.generative.abstractFactory2.ProjectManager;
import patterns.generative.abstractFactory2.TeamProjectFactory;
import patterns.generative.abstractFactory2.Tester;

public class BankTeamProject implements TeamProjectFactory {
    @Override
    public Developer getDeveloper() {
        return new JavaDeveloper();
    }

    @Override
    public Tester getTester() {
        return new QATester();
    }

    @Override
    public ProjectManager getPM() {
        return new BankingPM();
    }
}
