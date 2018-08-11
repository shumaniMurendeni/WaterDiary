package Utilities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waterdiary.OverviewActivity;
import com.example.waterdiary.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class DiaryEntryAdapter extends ArrayAdapter<DiaryEntry>{

    //region Global Variables
    public static List<DiaryEntry> diaryEntries;
    private Context mContext;
    private LayoutInflater mInflater;
    public static final String fileName = "Diary_Entries.txt";
    //endregion

    public DiaryEntryAdapter(@NonNull Context context) {

        super(context, R.layout.list_entries, diaryEntries);
        mInflater = LayoutInflater.from(context);
    }

    static {
        diaryEntries = new ArrayList<>();
        /*entryMap = new HashMap<>();*
        addEntry(new DiaryEntry("11 March 2018",15,2.3,2,0,4,
                4,1,0));
        addEntry(new DiaryEntry("12 March 2018",15,2.3,2,0,4,
                4,1,0));
        addEntry(new DiaryEntry("13 March 2018",15,2.3,2,0,4,
                4,1,0));
        /*try {
            //saveEntries();
            readEntries();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static void addEntry(DiaryEntry entry){
        diaryEntries.add(entry);
        OverviewActivity.mDataSource.createEntry(entry);
        //entryMap.put(entry.getDate(),entry);
    }


    public static String getSummary(){
        StringBuilder summary = new StringBuilder();
        double shower = 0,toilet=0,hygiene=0,laundry=0, dishes=0, cooking=0, cleaning=0, other=0,tot = 0;
        final int total = diaryEntries.size();

        for (DiaryEntry item:diaryEntries) {
            shower += item.getShower(); toilet +=item.getToilet(); hygiene += item.getHygiene();
            laundry += item.getLaundry(); dishes += item.getDishes(); cooking += item.getCooking();
            cleaning += item.getCleaning(); other += item.getOther(); tot += item.getTotalLitres();
        }

        summary.append(String.format("%-1s : %.2f %10s", OverviewActivity.mcontext.getString(R.string.shower), (float)shower,""));
        summary.append(String.format("%-1s : %.2f\n", OverviewActivity.mcontext.getString(R.string.toilet), (float)toilet));
        summary.append(String.format("%-1s : %.2f %10s", OverviewActivity.mcontext.getString(R.string.hygiene), (float)hygiene,""));
        summary.append(String.format("%-1s : %.2f\n", OverviewActivity.mcontext.getString(R.string.laundry), (float)laundry));
        summary.append(String.format("%-1s : %.2f %10s", OverviewActivity.mcontext.getString(R.string.dishes), (float)dishes,""));
        summary.append(String.format("%-1s : %.2f\n", OverviewActivity.mcontext.getString(R.string.cooking), (float)cooking));
        summary.append(String.format("%-1s : %.2f %10s", OverviewActivity.mcontext.getString(R.string.cleaning), (float)cleaning,""));
        summary.append(String.format("%-1s : %.2f\n\n", OverviewActivity.mcontext.getString(R.string.other), (float)other));
        summary.append(String.format("%-14s : %.2f\n",OverviewActivity.mcontext.getString(R.string.average),(float) (tot/total)));

        return summary.toString();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_entries,parent,false);
        }

        TextView key = convertView.findViewById(R.id.keyTView);
        TextView average = convertView.findViewById(R.id.TotalTView);

        DiaryEntry entry = diaryEntries.get(position);
        key.setText(entry.getDate());
        average.setText(String.format("%.2f %s",entry.getTotalLitres(),mInflater.getContext().getString(R.string.unit)));


        return convertView;
    }


}
