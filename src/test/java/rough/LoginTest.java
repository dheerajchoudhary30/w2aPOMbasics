package rough;

import java.util.concurrent.TimeUnit;

import com.w2a.base.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.SignUpPage;
import com.w2a.pages.ZohoAppPage;

public class LoginTest {

    public static void main(String[] args) {

        ZohoAppPage zapage = new ZohoAppPage();
        zapage.introducingZiaSearch();
        zapage.goToAllApps();
        zapage.goToCRM();

        zapage.switchToWindow();

        Page.menu.goToAccounts();

        Page.menu.signOut();


    }


}
