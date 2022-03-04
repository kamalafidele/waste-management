package DataHandlers;

public class HouseHandler {
    private int id;
    //    id, name, pin, sectorId, walletId
    private String name;
    private String pin;
    private String sectorId;
    private String walletId;
    private String message;

    public HouseHandler() {}

    public HouseHandler(int id, String name, String pin, String sectorId, String walletId, String message) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.sectorId = sectorId;
        this.walletId = walletId;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getSectorId() {
        return sectorId;
    }

    public void setSectorId(String sectorId) {
        this.sectorId = sectorId;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String genPin() {
        int count = 0;
        String ALPHA_NUMERIC_STRING = "0123456789";
        StringBuilder builder = new StringBuilder();
        while (count < 5) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            count++;
        }
        return builder.toString();
    }
}
