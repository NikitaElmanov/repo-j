package patterns.abstractFactory2.website;

import patterns.abstractFactory2.ProjectManager;

public class WebSitePM implements ProjectManager {
    @Override
    public void manageProject() {
        System.out.println(this.getClass().getSimpleName() + " is managing a website project...");
    }
}
