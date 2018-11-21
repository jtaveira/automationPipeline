package reporter;

import java.io.BufferedWriter;
import java.io.IOException;

public class HtmlReporter {

    public void writeToFile(BufferedWriter file, String message) throws IOException {
        file.write(message);
    }

    public void startReport(BufferedWriter file) throws IOException {
        file.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head>\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
                " </head>\n" +
                "<body>");
    }

    public void endReport(BufferedWriter file) throws IOException {
        file.write("</body>\n" +
                "</html>");
        file.close();
    }

    public void startTestReport(BufferedWriter file) throws IOException {
        file.write("<div class=\"test-case\"> Test Case");
    }

    public void endTestReport(BufferedWriter file) throws IOException {
        file.write("</div>");
    }

}
