package db.com.bbtree.testdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bbtree.cardreader.greendao.gen.DaoMaster;

import org.greenrobot.greendao.database.Database;

import static com.bbtree.cardreader.greendao.gen.DaoMaster.dropAllTables;

/**
 * Created by qiujj on 2017/4/17.
 */

public class MyDataBaseHelper extends DaoMaster.OpenHelper {
    public MyDataBaseHelper(Context context, String name) {
        super(context, name);
    }

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("onUpgrade","============onUpgrade============");
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
        dropAllTables(db, true);
        onCreate(db);
    }
}
