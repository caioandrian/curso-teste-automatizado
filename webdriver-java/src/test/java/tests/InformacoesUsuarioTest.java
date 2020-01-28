package tests;

import static org.junit.Assert.*;

import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// Classe do Easy Test que será usada para rodar o Teste Dirigido a Dados
@RunWith(DataDrivenTestRunner.class)

// Arquivo que será usado para receber os dados externos que serão usados via Easy Test
// Informar no data louder o arquivo criado na pasta resources do projeto
@DataLoader(filePaths = "informacoesUsuarioTestData.csv")

public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        navegador = Web.createChrome();

        // Clicar no link com texto "Sign in" poderia ser pelo ID, NAME, CLASSE, ETC.
        navegador.findElement(By.linkText("Sign in")).click();

        // Identificando o formulário de id "signinbox"
        WebElement formularioSignInbox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com name "login" que está dentro do formulário signinbox o texto "julio0001"
        formularioSignInbox.findElement(By.name("login")).sendKeys("julio0001");

        // Digitar no campo com name "password" que está dentro do formulário signinbox o texto "123456"
        formularioSignInbox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link com texto "SIGN IN"
        // O texto a ser verificado sempre será o que o usuário vê na tela, no HTML está Sign in
        formularioSignInbox.findElement(By.name("password")).click();
        formularioSignInbox.findElement(By.linkText("SIGN IN")).click();

        // Identificar e clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Identificar e clicar em um link que possui o texto "MORE DATA.."
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "tipo")String tipo,
                                                             @Param(name = "contato")String contato,
                                                             @Param(name = "mensagem")String mensagemEsperada){
        // Identificar e clicar em um botão, pelo seu xPath
        // no F12, apertar Ctrl+F você pode procurar pelo xPath informando o que está procurando
        // duas barras significa procure em toda página, e uma barra / significa no primeiro que encontrar
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar o popup onde está o formulário com id "addmoredata"
        WebElement popUpAddmoredata = navegador.findElement(By.id("addmoredata"));

        // Na combo de name "type" a opção "Phone"
        WebElement campoType = popUpAddmoredata.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        // Identificar o campo de name "contact" e digitar "+5140110044"
        popUpAddmoredata.findElement(By.name("contact")).sendKeys(contato);

        // Identificar e clicar no link que tem o texto "SAVE" que está na div "addmoredata"
        popUpAddmoredata.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);
    }

    @Test
    public void removerUmContatoDeUmUsuario(){
        // Clicar pelo xpath no elemento //span[text()="+5149212121"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+5149212121\"]/following-sibling::a")).click();

        // Confirmar a janela javascript
        navegador.switchTo().alert().accept();

        // Validar que a mensagem apresentada foi "Rest in peace, dear phone!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        // Tirando screenshot do teste
        String screenshotArquivo = "C:\\Users\\caio\\IdeaProjects\\test-report\\taskit\\"
                + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";

        Screenshot.tirar(navegador, screenshotArquivo);

        // Aguardar até 10 segundos para que a janela desapareça
        // stalenessOf = aguardar até que o elemento desapareça da página.
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Clicar no link com o texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() {
        // Fechar o navegador
        //navegador.quit();
    }
}
