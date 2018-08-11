package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.waterdiary.OverviewActivity;

public class DiaryEntryDB extends SQLiteOpenHelper {
    public static final String DB_fileName = "DiaryEntry.db";
    public static final int DB_Version = 1;
    public DiaryEntryDB(Context context) {
        super(context, DB_fileName, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBEntry.SQL_CREATE);
        Toast.makeText(OverviewActivity.mcontext,"Database created",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<newVersion){
            db.execSQL(DBEntry.SQL_DELETE);//drop sql string
            onCreate(db);//Create new sql database or database
        }
    }
}
