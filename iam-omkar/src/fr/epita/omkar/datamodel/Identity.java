package fr.epita.omkar.datamodel;
/**
 * @author omkar         
 */
public class Identity {
	private Integer uid;
    private String displayName;
    private String email;

    /**
     * @param displayName 
     * @param email       
     * @param uid         
     */
    public Identity(String displayName, String email, Integer uid) {
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
    }

    /**
     * @return Display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName Display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return Unique ID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     *
     * @param uid Unique ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return Email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("[UID=%d] Identity [displayName=%s; email=%s]",
                this.uid, this.displayName, this.email);
    }


}
