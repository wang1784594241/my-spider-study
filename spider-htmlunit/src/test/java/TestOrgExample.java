import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/12/25.
 */
public class TestOrgExample {

    @Test
    public void homePage() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
            Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

            final String pageAsXml = page.asXml();
            Assert.assertTrue(pageAsXml.contains("<body class=\"topBarDisabled\">"));

            final String pageAsText = page.asText();
            System.out.println(pageAsText);
            Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
        }
    }

    @Test
    public void homePage_Firefox() throws Exception {
        try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52)) {
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
            Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());
        }
    }

    @Test
    public void getElements() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://some_url");
            final HtmlDivision div = page.getHtmlElementById("some_div_id");
            final HtmlAnchor anchor = page.getAnchorByName("anchor_name");
        }
    }

    @Test
    public void getElements2() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://some_url");
            DomNodeList<DomElement> inputs = page.getElementsByTagName("input");
            for (DomElement input : inputs) {
                System.out.println(input);
            }
        }
    }

    @Test
    public void xpath() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

            //get list of all divs
            final List<?> divs = page.getByXPath("//div");

            //get div which has a 'name' attribute of 'John'
            final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@name='John']").get(0);
        }
    }

    @Test
    public void cssSelector() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

            //get list of all divs
            final DomNodeList<DomNode> divs = page.querySelectorAll("div");
            for (DomNode div : divs) {
            }

            //get div which has the id 'breadcrumbs'
            final DomNode div = page.querySelector("div#breadcrumbs");
        }
    }

    @Test
    public void homePage_proxy() throws Exception {
        int myProxyPort = 80;
        try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52, "myproxyserver", myProxyPort)) {

            //set proxy username and password
            final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
            credentialsProvider.addCredentials("username", "password");

            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
            Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
        }
    }

    @Test
    public void submittingForm() throws Exception {
        try (final WebClient webClient = new WebClient()) {

            // Get the first page
            final HtmlPage page1 = webClient.getPage("http://some_url");

            // Get the form that we are dealing with and within that form,
            // find the submit button and the field that we want to change.
            final HtmlForm form = page1.getFormByName("myform");

            final HtmlSubmitInput button = form.getInputByName("submitbutton");
            final HtmlTextInput textField = form.getInputByName("userid");

            // Change the value of the text field
            textField.setValueAttribute("root");

            // Now submit the form by clicking the button and get back the second page.
            final HtmlPage page2 = button.click();
        }
    }

    //table表格 http://htmlunit.sourceforge.net/table-howto.html
    //模拟键盘按键 http://htmlunit.sourceforge.net/keyboard-howto.html
    //frame获取  http://htmlunit.sourceforge.net/frame-howto.html
    //js操作  http://htmlunit.sourceforge.net/javascript-howto.html
}
