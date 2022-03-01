package Models;

public class House {
    private int id;
    private String fullnames;
    private String nid;
    private String houseno;
    private String telno;
    private String sector;
    private String cell;
    private String village;
    private String token;

    public House() {}

//    public House( String fullnames, String nid, String houseno, String telno, String sector, String cell, String village, String token) {
//        this.fullnames = fullnames;
//        this.nid = nid;
//        this.houseno = houseno;
//        this.telno = telno;
//        this.sector = sector;
//        this.cell = cell;
//        this.village = village;
//        this.token = token;
//    }

    public  void setId(int id) { this.id = id;}

    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }
    public String getCell() {
        return cell;
    }
    public void setCell(String cell) {
        this.cell = cell;
    }
    public String getVillage() {
        return village;
    }
    public void setVillage(String village) {
        this.village = village;
    }

    public String getToken() {
        int count = 0;
        String ALPHA_NUMERIC_STRING = "0123456789";
        StringBuilder builder = new StringBuilder();
        while (count < 5) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            count++;
        }
        token = builder.toString();
        return token;
    }
    public void setToken(String token){
        this.token = token;
    }
}
