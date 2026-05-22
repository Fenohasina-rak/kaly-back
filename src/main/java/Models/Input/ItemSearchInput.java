package Models.Input;

public class ItemSearchInput {
    private String searchKey;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public String toString() {
        return "ItemSearchInput{" +
                "searchKey='" + searchKey + '\'' +
                '}';
    }
}
