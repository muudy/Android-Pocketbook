package cn.jerryshell.pocketbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.jerryshell.pocketbook.modle.Item;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "pocketbook";
    private static final String TABLE_NAME = "packet_book";

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String MONEY = "money";
    public static final String DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "("
                + "_id integer primary key, "
                + ID + " varchar, "
                + TITLE + " varchar, "
                + MONEY + " integer, "
                + DATE + " varchar"
                + ")");
    }

    public long insertItem(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, item.getId().toString());
        contentValues.put(TITLE, item.getTitle());
        contentValues.put(MONEY, item.getMoney());
        contentValues.put(DATE, item.getDate());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public void updateItem(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, item.getId().toString());
        contentValues.put(TITLE, item.getTitle());
        contentValues.put(MONEY, item.getMoney());
        contentValues.put(DATE, item.getDate());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, contentValues, "id=?", new String[]{item.getId().toString()});
    }

    public int deleteItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, "id=?", new String[]{item.getId().toString()});
    }

    public Cursor countByMonth() {

        //String[] columns = new  String[] { "sum(money) AS summoney,date" };

        String[] columns =new  String[] { "sum(money) AS summoney,substr(date,1,7)AS date" };

        //select substr(日期,1,7),sum(金额) from table group by substr(日期,1,7)
        //date.substring(1,7);
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, columns, null, null,
                "substr(date,1,7)", null, null);
    }

    public Cursor queryAllItem() {
        SQLiteDatabase db = getReadableDatabase();

        /*
        * TABLE_NAME,表名
        * columns,列名
        * selection,WHERE  selection = ”City=?";
        * selectionArgs,selectionArgs = {"Beijing"};
                 也就是说selectionArgs中的字符串就是对应selection中的问号所代表的变量。实际上就是让selection中的过滤条件City可以动态的赋值，而不是写死在程序当中。
        * groupBy,分组
        * having,String having对应SQL语句HAVING后面的字符串
        * orderBy,id DESC,排序
        *
        * */
        return db.query(TABLE_NAME, null, null,
                null, null, null, "_id DESC");

    }

    public int deleteAllItem() {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
