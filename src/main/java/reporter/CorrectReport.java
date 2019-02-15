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

    String failed = "FAILED";
    String passed = "PASSED";
    String style = "style";
    String displayBlock = "display: block;";
    String active = "active";
    String divStep = "div.step";
    String spanTimestamp = "span.timestamp";

    public void correctReport (String fileLocation) throws IOException {

        File file = new File(fileLocation);
        Document doc = Jsoup.parse(file, "UTF-8");

        fixColors(doc);
        propagateTimeStamp(doc);
        makeOverview(doc);
        openWrongTests(doc);

        FileUtils.writeStringToFile(file, doc.toString(), "UTF-8");
    }

    public void openWrongTests(Document doc) {
        Elements failedElements = doc.select(".action.FAILED");

        for(Element failedElement : failedElements){

            for (Element failedChild : failedElement.children()){
                if(failedChild.hasClass("locator") || failedChild.hasClass("exception") || failedChild.hasClass("screenshot")){
                    failedChild.attr(style, displayBlock);
                }
            }

            for (Element sibling : failedElement.parent().parent().children()){
                if(sibling.hasClass("step")){
                    sibling.attr(style, displayBlock);
                }
            }

            failedElement.attr(style, displayBlock);
            failedElement.child(0).addClass(active);
            failedElement.child(0).text("-");

            failedElement.parent().attr(style, displayBlock);
            failedElement.parent().child(0).addClass(active);
            failedElement.parent().child(0).text("-");

            failedElement.parent().parent().attr(style, displayBlock);
            failedElement.parent().parent().child(0).addClass(active);
            failedElement.parent().parent().child(0).text("-");
        }
    }

    public void fixColors(Document doc) {
        Elements actions = doc.select("div.action");

        for(Element action : actions){
            if(action.hasClass(failed)){
                action.parent().removeClass(passed);
                action.parent().addClass(failed);
            }
        }

        Elements steps = doc.select(divStep);

        for(Element step : steps){
            if(step.hasClass(failed)){
                step.parent().removeClass(passed);
                step.parent().addClass(failed);
            }
        }
    }

    public void propagateTimeStamp(Document doc) {

        DecimalFormat df = new DecimalFormat("#.#####");

        Elements steps = doc.select(divStep);

        for(Element step : steps){

            Elements actions  = step.select("div.action");
            Double timestamp = 0.0;

            for(Element action: actions){

                String timeArr[] = action.select(spanTimestamp).text().split(" ");

                for (int i=0; i < timeArr.length; i++) {

                    timestamp += Double.parseDouble(timeArr[i]);
                }
            }

            step.select(spanTimestamp).first().text(String.valueOf(df.format(timestamp)));
        }

        Elements testCases = doc.select("div.test-case");

        for(Element testCase : testCases){

            steps  = testCase.select(divStep);
            Double timestamp = 0.0;

            for(Element step: steps){

                String timeArr[] = step.select(spanTimestamp).text().split(" ");

                for (int i=0; i < timeArr.length; i++) {

                    timestamp += Double.parseDouble(timeArr[i]);
                }
            }

            testCase.select(spanTimestamp).first().text(String.valueOf(df.format(timestamp/2)));
        }
    }

    public void makeOverview(Document doc) {
        Elements testCases = doc.select("div.test-case");

        Integer passedCount = 0;
        Integer failedCount = 0;
        Integer total = 0;

        for(Element testcase: testCases){
            total++;
            if(testcase.hasClass(failed))
                failedCount++;
            else
                passedCount++;
        }

        String suiteTitle = doc.select("h2").first().text();
        doc.select(".table-suite").first().text(suiteTitle);
        doc.select(".table-passed").first().text(passedCount.toString());
        doc.select(".table-failed").first().text(failedCount.toString());
        doc.select(".table-total").first().text(total.toString());
    }
}
