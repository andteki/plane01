

public class ResultStore {
    
    public void insert(double area, double perimeter) {
        SqliteStore store = new SqliteStore();
        store.setData(area, perimeter);
        store.insert();
    }    
}
