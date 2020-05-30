package patterns.abstractFactory2.bank;

import patterns.abstractFactory2.Developer;
import patterns.abstractFactory2.ProjectManager;
import patterns.abstractFactory2.TeamProjectFactory;
import patterns.abstractFactory2.Tester;

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
