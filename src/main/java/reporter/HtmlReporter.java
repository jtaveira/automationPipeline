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
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
                " </head>\n" +
                "<body>\n");
    }

    public void endReport(BufferedWriter file) throws IOException {
        file.write("</body>\n" +
                "</html>");
        file.close();
    }

    public void startTestReport(BufferedWriter file) throws IOException {
        file.write("<div class=\"test-case\">TEST CASE: " + "\n");
    }

    public void setTestTitle(BufferedWriter file, String title) throws IOException {
        file.write(title);
    }

    public void endTestReport(BufferedWriter file) throws IOException {
        file.write("</div>" + "\n");
    }

    public void openStep(BufferedWriter file, String status, String title) throws IOException {
        file.write("<div class=\"step " + status + "\">STEP: " + title + "\n");
    }

    public void openAction(BufferedWriter file, String status, String title) throws IOException {
        file.write("<div class=\"action " + status + "\">ACTION: " + title + "\n");
    }

    public void openLocator(BufferedWriter file, String locator) throws IOException {
        file.write("<div class=\"locator\">LOCATOR: " + locator + "</div>" + "\n");
    }

    public void openException(BufferedWriter file, Throwable exception) throws IOException {

        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String exc = sw.toString();
        file.write("<div class=\"exception\">EXCEPTION: " + exc + "</div>" + "\n");
    }

    public void closeAction(BufferedWriter file) throws IOException {
        file.write("</div>" + "\n");
    }

    public void closeStep(BufferedWriter file) throws IOException {
        file.write("</div>" + "\n");
    }

}
