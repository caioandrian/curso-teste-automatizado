package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    //private WebDriver navegador;

    // TODA PAGINA PRECISA TER UM ATRIBUTO PRIVADO WEBDRIVER
    // E UM CONSTRUTOR QUE ESTÁ AGUARDANDO UM WEBDRIVER
    // DESSA FORMA É POSSÍVEL NAVEGAR ENTRE AS PÁGINAS

    // Construtor
    /* public LoginPage(WebDriver navegador){
        this.navegador = navegador;
    }*/

    // DESSA FORMA TODA PAGE PRECISA EXTENDER A CLASSE BASEPAGE
    // Estendemos a classe BasePage para não precisarmos criar um novo construtor em toda classe de pages.
    public LoginPage(WebDriver navegador) {
        // Refere-se ao atributo da classe Pai (BasePage).
        super(navegador);
    }

    // Após clicar no Sign In iremos para a próxima página "LoginFormPage"
    // Mesmo que seja um popup
    public LoginFormPage clicarSignIn(){
        navegador.findElement(By.linkText("Sign in")).click();

        return new LoginFormPage(navegador);
    }
}
