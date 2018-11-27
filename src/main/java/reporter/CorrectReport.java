package reporter;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class CorrectReport {

    public void correctReport (String fileLocation) throws IOException {

        File file = new File(fileLocation);
        Document doc = Jsoup.parse(file, "UTF-8");

        fixColors(doc);
        propagateTimeStamp(doc);
        makeOverview(doc);

        FileUtils.writeStringToFile(file, doc.toString(), "UTF-8");
    }

    public void fixColors(Document doc) {
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
    }

    public void propagateTimeStamp(Document doc) {

        DecimalFormat df = new DecimalFormat("#.#####");

        Elements steps = doc.select("div.step");

        for(Element step : steps){

            Elements actions  = step.select("div.action");
            Double timestamp = 0.0;

            for(Element action: actions){

                String timeArr[] = action.select("span.timestamp").text().split(" ");

                for (int i=0; i < timeArr.length; i++) {

                    timestamp += Double.parseDouble(timeArr[i]);
                }
            }

            step.select("span.timestamp").first().text(String.valueOf(df.format(timestamp)));
        }

        Elements testCases = doc.select("div.test-case");

        for(Element testCase : testCases){

            steps  = testCase.select("div.step");
            Double timestamp = 0.0;

            for(Element step: steps){

                String timeArr[] = step.select("span.timestamp").text().split(" ");

                for (int i=0; i < timeArr.length; i++) {

                    timestamp += Double.parseDouble(timeArr[i]);
                }
            }

            testCase.select("span.timestamp").first().text(String.valueOf(df.format(timestamp/2)));
        }
    }

    public void makeOverview(Document doc) {
        Elements testCases = doc.select("div.test-case");

        Integer passed = 0;
        Integer failed = 0;
        Integer total = 0;

        for(Element testcase: testCases){
            total++;
            if(testcase.hasClass("FAILED"))
                failed++;
            else
                passed++;
        }

        String suiteTitle = doc.select("h2").first().text();
        doc.select(".table-suite").first().text(suiteTitle);
        doc.select(".table-passed").first().text(passed.toString());
        doc.select(".table-failed").first().text(failed.toString());
        doc.select(".table-total").first().text(total.toString());
    }
}
