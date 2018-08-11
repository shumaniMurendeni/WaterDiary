package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Utilities.DiaryEntry;

public class DiaryEntryDataSource {
    private Context mContext;
    private SQLiteDatabase mDataBase;
    SQLiteOpenHelper mDBHelper;

    public DiaryEntryDataSource(Context context) {
        this.mContext = context;
        mDBHelper = new DiaryEntryDB(mContext);
        mDataBase = mDBHelper.getWritableDatabase();

    }

    public void open(){mDataBase = mDBHelper.getWritableDatabase();}
    public void close(){mDBHelper.close();}

    public DiaryEntry createEntry(DiaryEntry entry){
        ContentValues contentValues = entry.toValues();
        open();
        mDataBase.insert(DBEntry.table,null,contentValues);
        close();
        return entry;
    }

    public List<DiaryEntry> getAllEntries(){
        List<DiaryEntry> diaryEntries = new ArrayList<>();
        DiaryEntry entry;
        Cursor cursor;
        cursor = mDataBase.query(DBEntry.table,DBEntry.columns,null,null,
                null,null,null);
        while(cursor.moveToNext()){

            entry = new DiaryEntry();
            entry.setDate(cursor.getString(cursor.getColumnIndex(DBEntry.dateID)));
            entry.setShower(cursor.getDouble(cursor.getColumnIndex(DBEntry.showerID)));
            entry.setToilet(cursor.getDouble(cursor.getColumnIndex(DBEntry.toiletID)));
            entry.setHygiene(cursor.getDouble(cursor.getColumnIndex(DBEntry.hygieneID)));
            entry.setLaundry(cursor.getDouble(cursor.getColumnIndex(DBEntry.laundryID)));
            entry.setDishes(cursor.getDouble(cursor.getColumnIndex(DBEntry.dishesID)));
            entry.setCooking(cursor.getDouble(cursor.getColumnIndex(DBEntry.cookingID)));
            entry.setCleaning(cursor.getDouble(cursor.getColumnIndex(DBEntry.cleaningID)));
            entry.setOther(cursor.getDouble(cursor.getColumnIndex(DBEntry.otherID)));

            diaryEntries.add(entry);
        }

        return diaryEntries;
    }
}
