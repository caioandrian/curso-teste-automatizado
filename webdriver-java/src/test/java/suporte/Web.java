package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {
    // Variáveis de uso para acessar o STACK BROWSER
    // Substituir os XXXXXXXXX pela suas credencias obtidas no site
    // https://www.browserstack.com/automate/java
    public static final String USERNAME = "xxxxxxxx";
    public static final String AUTOMATE_KEY = "xxxxxxxx";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome(){
        // Abrindo um navegador pela variável instanciada do chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver79.exe");
        WebDriver navegador = new ChromeDriver();

        // Adicionando tempo de espera para tentar achar o elemento na página
        navegador.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        // Abrir a instância em tela cheia.
        navegador.manage().window().maximize();

        // Navegando para a página de taskit
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }

    // Carregar o driver que está na NUVEM
    // https://www.browserstack.com/automate/java
    public static WebDriver createBrowserStack(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "IE");
        caps.setCapability("browser_version", "11.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "7");
        caps.setCapability("resolution", "1024x768");

        caps.setCapability("name", "Bstack-[Java] Sample Test");
        caps.setCapability("browserstack.debug", "true");
        //caps.setCapability("resolution", "1024x768");
        //caps.setCapability("browserstack.video", "false");

        WebDriver navegador = null;

        // Substituir o primeiro URL pela URL do java.net
        try {
            navegador = new RemoteWebDriver(new URL(URL), caps);

            // Adicionando tempo de espera para tentar achar o elemento na página
            navegador.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

            // Navegando para a página de taskit
            navegador.get("http://www.juliodelima.com.br/taskit");

        } catch (MalformedURLException e) {
            System.out.println("Houveram problemas com a URL:" + e.getMessage());
        }

        return navegador;
    }

}
