package patterns.abstractFactory2;

public interface TeamProjectFactory {
    Developer getDeveloper();
    Tester getTester();
    ProjectManager getPM();
}
