package utils;

public enum TipZahteva {
    LOGIN_KUPAC, LOGIN_PRODAVAC, LOGOUT, IZMENI_PODATKE_KUPAC, IZMENI_PODATKE_PRODAVAC;

    public static TipZahteva fromInteger(int x) {
        switch (x) {
            case 0:
                return LOGIN_KUPAC;
            case 1:
                return LOGIN_PRODAVAC;
            case 2:
                return LOGOUT;
            case 3:
                return IZMENI_PODATKE_KUPAC;
            case 4:
                return IZMENI_PODATKE_PRODAVAC;

        }
        return null;
    }
}
