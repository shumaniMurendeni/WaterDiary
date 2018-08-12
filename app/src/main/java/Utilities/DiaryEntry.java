package Utilities;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.waterdiary.OverviewActivity;
import com.example.waterdiary.R;

import Database.DBEntry;


public class DiaryEntry implements Parcelable {

    private String date;
    private double shower;
    private double toilet;
    private double hygiene;
    private double laundry;
    private double dishes;
    private double cooking;
    private double cleaning;
    private double other;
    private double total;

    public DiaryEntry(String date, double shower, double toilet,
                      double hygiene, double laundry, double dishes, double cooking, double cleaning, double other) {
        this.date = date;
        this.shower = shower;
        this.toilet = toilet;
        this.hygiene = hygiene;
        this.laundry = laundry;
        this.dishes = dishes;
        this.cooking = cooking;
        this.cleaning = cleaning;
        this.other = other;
        total = calculateTotal();
    }

    private double calculateTotal() {
        return shower + toilet + hygiene + laundry + dishes + cooking
                + cleaning + other;
    }

    public DiaryEntry() {
        this.total = -1;
    }

    //<editor-fold desc="Property Methods">

    public double getTotalLitres() {
        if (total <0) {
            total = calculateTotal();
        }
        return this.total;
    }
    public double getShower() {
        return shower;
    }

    public double getToilet() {
        return toilet;
    }

    public double getHygiene() {
        return hygiene;
    }

    public double getLaundry() {
        return laundry;
    }

    public double getDishes() {
        return dishes;
    }

    public double getCooking() {
        return cooking;
    }

    public double getCleaning() {
        return cleaning;
    }

    public double getOther() {
        return other;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setShower(double shower) {
        this.shower = shower;
    }

    public void setToilet(double toilet) {
        this.toilet = toilet;
    }

    public void setHygiene(double hygiene) {
        this.hygiene = hygiene;
    }

    public void setLaundry(double laundry) {
        this.laundry = laundry;
    }

    public void setDishes(double dishes) {
        this.dishes = dishes;
    }

    public void setCooking(double cooking) {
        this.cooking = cooking;
    }

    public void setCleaning(double cleaning) {
        this.cleaning = cleaning;
    }

    public void setOther(double other) {
        this.other = other;
    }
    //</editor-fold>

    public String getDetails(){
        StringBuilder details = new StringBuilder();
        details.append(String.format("%-11s: %s\n", OverviewActivity.mcontext.getString(R.string.shower), getShower() + " " + OverviewActivity.mcontext.getString(R.string.unit)));
        details.append(String.format("%-14s: %s\n", OverviewActivity.mcontext.getString(R.string.toilet), getToilet() + " " + OverviewActivity.mcontext.getString(R.string.unit)));
        details.append(String.format("%-11s: %s\n", OverviewActivity.mcontext.getString(R.string.hygiene), getHygiene() + " " + OverviewActivity.mcontext.getString(R.string.unit)));
        details.append(String.format("%-10s: %s\n", OverviewActivity.mcontext.getString(R.string.laundry), getLaundry() + " " + OverviewActivity.mcontext.getString(R.string.unit)));
        details.append(String.format("%-14s: %s\n", OverviewActivity.mcontext.getString(R.string.dishes), getDishes() + " " + OverviewActivity.mcontext.getString(R.string.unit)));
        details.append(String.format("%-12s: %s\n", OverviewActivity.mcontext.getString(R.string.cooking), getCooking() + " " + OverviewActivity.mcontext.getString(R.string.unit)));
        details.append(String.format("%-11s: %s\n", OverviewActivity.mcontext.getString(R.string.cleaning), getCleaning() + " " + OverviewActivity.mcontext.getString(R.string.unit)));
        details.append(String.format("%-14s: %s\n", OverviewActivity.mcontext.getString(R.string.other), getOther() + " " + OverviewActivity.mcontext.getString(R.string.unit)));

        return details.toString();
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBEntry.date,date);
        contentValues.put(DBEntry.shower,shower);
        contentValues.put(DBEntry.toilet,toilet);
        contentValues.put(DBEntry.hygiene,hygiene);
        contentValues.put(DBEntry.laundry,laundry);
        contentValues.put(DBEntry.dishes,dishes);
        contentValues.put(DBEntry.cooking,cooking);
        contentValues.put(DBEntry.cleaning,cleaning);
        contentValues.put(DBEntry.other,other);

        return contentValues;
    }

    @Override
    public String toString() {
        return getDate()+", "+ getShower() +", "+ getToilet() +", "+getHygiene()
                +", "+ getLaundry() +", "+ getDishes() +", "+ getCooking()
                +", "+ getCleaning() +", "+ getOther() +"\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeDouble(this.shower);
        dest.writeDouble(this.toilet);
        dest.writeDouble(this.hygiene);
        dest.writeDouble(this.laundry);
        dest.writeDouble(this.dishes);
        dest.writeDouble(this.cooking);
        dest.writeDouble(this.cleaning);
        dest.writeDouble(this.other);
        dest.writeDouble(this.total);
    }

    protected DiaryEntry(Parcel in) {
        this.date = in.readString();
        this.shower = in.readDouble();
        this.toilet = in.readDouble();
        this.hygiene = in.readDouble();
        this.laundry = in.readDouble();
        this.dishes = in.readDouble();
        this.cooking = in.readDouble();
        this.cleaning = in.readDouble();
        this.other = in.readDouble();
        this.total = in.readDouble();
    }

    public static final Creator<DiaryEntry> CREATOR = new Creator<DiaryEntry>() {
        @Override
        public DiaryEntry createFromParcel(Parcel source) {
            return new DiaryEntry(source);
        }

        @Override
        public DiaryEntry[] newArray(int size) {
            return new DiaryEntry[size];
        }
    };
}
