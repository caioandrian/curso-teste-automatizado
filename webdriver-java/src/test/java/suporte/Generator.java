package suporte;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

// CLASSE CRIADA PARA SERVIR DE SUPORTE AO TESTE
// NELA SERÁ GERADO UM VALOR PARA DATA HORA PARA O SCREENSHOT

public class Generator {
    public static String dataHoraParaArquivo(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyyMMddHHmmss").format(ts); //20200124194200
    }
}
