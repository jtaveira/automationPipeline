package reporter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class HtmlReporter {

    public void startReport(BufferedWriter file) throws IOException {
        file.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head>\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"../template/style.css\">\n" +
                " </head>\n" +
                "<body>\n");
    }

    public void endReport(BufferedWriter file) throws IOException {
        file.write("</body><" +
                "script src=\"../template/myScript.js\"></script>\n" +
                "</html>");
        file.close();
    }

    public void startTestReport(BufferedWriter file) throws IOException {
        file.write("<div class=\"test-case PASSED\"><span>TEST CASE:</span> ");
    }

    public void setTestTitle(BufferedWriter file, String title) throws IOException {
        file.write(title);
    }

    public void endTestReport(BufferedWriter file) throws IOException {
        file.write("</div>" + "\n");
    }

    public void openStep(BufferedWriter file, String status, String title) throws IOException {
        file.write("<div class=\"step " + status + "\"><span>STEP:</span> " + title + "\n");
    }

    public void openAction(BufferedWriter file, String status, String title) throws IOException {
        file.write("<div class=\"action " + status + "\"><span>ACTION:</span> " + title + "\n");
    }

    public void openLocator(BufferedWriter file, String locator) throws IOException {
        file.write("<div class=\"locator\"><span>LOCATOR:</span> " + locator + "</div>" + "\n");
    }

    public void openException(BufferedWriter file, Throwable exception) throws IOException {

        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String exc = sw.toString();
        file.write("<div class=\"exception\"><span>EXCEPTION:</span> " + exc + "</div>" + "\n");
    }

    public void openScreenshot(BufferedWriter file, String image) throws IOException {
        file.write("<div class=\"screenshot\"><a href=\"" + image + "\"><img src=\"" + image + "\"></a></div>" + "\n");
    }

    public void closeAction(BufferedWriter file) throws IOException {
        file.write("</div>" + "\n");
    }

    public void closeStep(BufferedWriter file) throws IOException {
        file.write("</div>" + "\n");
    }

}
