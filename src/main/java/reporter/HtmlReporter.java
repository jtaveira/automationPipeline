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
        file.write("<div class=\"test-case PASSED\"><button>+</button><span>TEST CASE:</span> ");
    }

    public void writeToReport(BufferedWriter file, String msg) throws IOException {
        file.write(msg);
    }

    public void setTestTitle(BufferedWriter file, String title) throws IOException {
        file.write(title);
    }

    public void endTestReport(BufferedWriter file) throws IOException {
        file.write("</div>" + "\n");
    }

    public void openStep(BufferedWriter file, String status, String title) throws IOException {
        file.write("<div class=\"step " + status + "\" style=\"display: none;\"><button>+</button><span>STEP:</span> " + title + "<span class=\"timestamp\"></span>" + "\n");
    }

    public void openAction(BufferedWriter file, String status, String title, String timestamp) throws IOException {
        file.write("<div class=\"action " + status + "\" style=\"display: none;\"><button>+</button><span>ACTION:</span> " + title + "<span class=\"timestamp\">" + timestamp + "</span>" + "\n");
    }

    public void openLocator(BufferedWriter file, String locator) throws IOException {
        file.write("<div class=\"locator\" style=\"display: none;\"><span>LOCATOR:</span> " + locator + "</div>" + "\n");
    }

    public void openException(BufferedWriter file, Throwable exception) throws IOException {

        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String exc = sw.toString();
        file.write("<div class=\"exception\" style=\"display: none;\"><span>EXCEPTION:</span> " + exc + "</div>" + "\n");
    }

    public void openScreenshot(BufferedWriter file, String image) throws IOException {
        file.write("<div class=\"screenshot\" style=\"display: none;\"><a href=\"" + image + "\"><img src=\"" + image + "\"></a></div>" + "\n");
    }

    public void closeAction(BufferedWriter file) throws IOException {
        file.write("</div>" + "\n");
    }

    public void closeStep(BufferedWriter file) throws IOException {
        file.write("</div>" + "\n");
    }

}
