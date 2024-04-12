package patterns.generative.abstractFactory2;

import patterns.generative.abstractFactory2.website.WebSiteTeamProject;

public class Main {
    public static void main(String[] args) {
        TeamProjectFactory factory = new WebSiteTeamProject();
        Developer developer = factory.getDeveloper();
        Tester tester = factory.getTester();
        ProjectManager projectManager = factory.getPM();

        System.out.println("they are working...");

        developer.writeCode();
        tester.testCode();
        projectManager.manageProject();
    }
}
