package factory;

import imposto.*;

public class ImpostoFactory {
    public static Imposto getImposto(TipoImposto tipo) {
        switch (tipo) {
            case TIPOICMS:
                return new ICMS();
            case TIPOISS:
                return new ISS();
            case TIPOIPI:
                return new IPI();
            case TIPOPIS:
                return new PIS();
            default:
                throw new IllegalArgumentException("Tipo de imposto não encontrado");
        }
    }
}

