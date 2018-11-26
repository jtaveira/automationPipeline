package reporter;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class CorrectReport {

    public void correctReport (String fileLocation) throws IOException {

        File file = new File(fileLocation);
        Document doc = Jsoup.parse(file, "UTF-8");

        Elements actions = doc.select("div.action");

        for(Element action : actions){
            if(action.hasClass("FAILED")){
                action.parent().removeClass("PASSED");
                action.parent().addClass("FAILED");
            }
        }

        Elements steps = doc.select("div.step");

        for(Element step : steps){
            if(step.hasClass("FAILED")){
                step.parent().removeClass("PASSED");
                step.parent().addClass("FAILED");
            }
        }

        FileUtils.writeStringToFile(file, doc.toString(), "UTF-8");
    }
}
