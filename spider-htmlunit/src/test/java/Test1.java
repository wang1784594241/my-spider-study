import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

/**
 * Created by Administrator on 2017/12/25.
 */
public class Test1 {

    @Test
    public void test1() throws Exception {
        try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            final HtmlPage page = webClient.getPage("http://music.163.com/#/discover/toplist");
            System.out.println(page.asXml());
        }
    }
}
