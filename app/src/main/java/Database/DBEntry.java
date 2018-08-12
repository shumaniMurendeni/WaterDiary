package Database;

public class DBEntry {
    public static final String tableName = "diaryEntries";
    public static final String dateID = "entryDate";
    public static final String showerID ="shower";
    public static final String toiletID = "toilet";
    public static final String hygieneID ="hygiene";
    public static final String laundryID = "laundry";
    public static final String dishesID ="dishes";
    public static final String cookingID = "cooking";
    public static final String cleaningID ="cleaning";
    public static final String otherID = "other";
    public static final String table = "`diaryEntries`";
    public static final String date = "`"+dateID+"`";
    public static final String shower ="`"+showerID+"`";
    public static final String toilet = "`"+toiletID+"`";
    public static final String hygiene ="`"+hygieneID+"`";
    public static final String laundry = "`"+laundryID+"`";
    public static final String dishes ="`"+dishesID+"`";
    public static final String cooking = "`"+cookingID+"`";
    public static final String cleaning ="`"+cleaningID+"`";
    public static final String other = "`"+otherID+"`";
    public static final String[] columns =
            {date,shower,toilet,hygiene,laundry,dishes,cooking,cleaning,other};

    public static final String SQL_CREATE =  "CREATE TABLE IF NOT EXISTS"+ table +"(" +
            date + "TEXT PRIMARY KEY NOT NULL,"+
            shower + "REAL DEFAULT 0," +
            toilet + "REAL DEFAULT 0," +
            hygiene + "REAL DEFAULT 0," +
            laundry + "REAL DEFAULT 0," +
            dishes + "REAL DEFAULT 0," +
            cooking + "REAL DEFAULT 0," +
            cleaning + "REAL DEFAULT 0," +
            other + "REAL DEFAULT 0" + ");";

    public static final String SQL_DELETE = "DROP TABLE " + table + ";";


}
