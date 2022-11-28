package io.github.mohamed.sallam.awb;

public interface DAO_Group {


    /**
     * Deletes a software from blocked softwares list.
     * @param index of the software desired to be deleted.
     */
    public void deleteSoftware(int index) {

    }

    /**
     * Deletes website from blocked websites list.
     * @param index of the website desired to be deleted.
     */
    public void deleteWebsite(int index) {

    }

    /**
     * Adds a software to the blocked softwares list.
     * @param software the new blocked software.
     */
    public void addSoftware(String software) {

    }

    /**
     * Adds a website to the blocked websites list.
     * @param website the new blocked website.
     */
    public void addWebsite(String website) {

    }
}
