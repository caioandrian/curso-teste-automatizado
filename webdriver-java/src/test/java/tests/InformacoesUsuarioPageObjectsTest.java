package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.runner.RunWith;
import pages.LoginPage;
import suporte.Web;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

// Classe do Easy Test que será usada para rodar o Teste Dirigido a Dados
@RunWith(DataDrivenTestRunner.class)

// Arquivo que será usado para receber os dados externos que serão usados via Easy Test
// Informar no data louder o arquivo criado na pasta resources do projeto
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv")

public class InformacoesUsuarioPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        // Usado para acessar um driver local em sua máquina
        navegador = Web.createChrome();

        // Usado para acessar um driver na Nuvel pelo Browser Stack
        //navegador = Web.createBrowserStack();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "login") String login,
                                                             @Param(name = "senha") String senha,
                                                             @Param(name = "tipo") String tipo,
                                                             @Param(name = "contato") String contato,
                                                             @Param(name = "mensagem") String mensagemEsperada){
        String textoToast = new LoginPage(navegador) // na página de login
                .clicarSignIn()
                .fazerLogin(login,senha)
                .clicarMe()
                .clicarNaAbaMoreDataAboutYou()
                .clicarNoBotaoAddMoreDataAbout()
                .adicionarContato(tipo, contato)
                .capturarTextoToast();

        // As boas práticas recomendam que apenas no final de um teste tenha uma validação
        assertEquals(mensagemEsperada, textoToast);
    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
