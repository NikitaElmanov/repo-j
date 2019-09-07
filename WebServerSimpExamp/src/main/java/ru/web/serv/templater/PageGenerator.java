package ru.web.serv.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Objects;

public class PageGenerator {
    private static final String DIR = "templates";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public PageGenerator() {
        this.cfg = new Configuration();
    }

    public static PageGenerator instance(){
        if (Objects.isNull(pageGenerator)){
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String file, Object data){
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(DIR + File.separator + file);
            template.process(data, stream);
        } catch (IOException | TemplateException e){
            e.printStackTrace();
        }
        return stream.toString();
    }
}
