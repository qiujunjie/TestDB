package db.com.bbtree.testdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bbtree.cardreader.greendao.gen.DaoMaster;
import com.bbtree.cardreader.greendao.gen.DaoSession;
import com.bbtree.cardreader.greendao.gen.UserDao;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean ENCRYPTED = true;
    private DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDataBaseHelper helper = new MyDataBaseHelper(this,ENCRYPTED ? "notes-db-encrypted" : "notes-db");
//        char[] chars = XorCharArray("123");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("1332") : helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();

        User user = new User("ssss",11);
        User user1 = new User("ssss1",11);
        User user2 = new User("ssss2",11);
        User user3 = new User("ssss3",11);
        User user4 = new User("ssss4",11);


        mDaoSession.insert(user);
        mDaoSession.insert(user1);
        mDaoSession.insert(user2);
        mDaoSession.insert(user3);
        mDaoSession.insert(user4);

        UserDao userDao = mDaoSession.getUserDao();
        List<User> list = userDao.queryBuilder().build().list();
        int size = list.size();
    }

    public static char[] XorCharArray(String input) {
        String secret = "test"; // just an alphanumeric dynamic string
        char[] arr = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            // some kind of xor
            char chr = ((char) (input.charAt(i) ^ secret.charAt(i % secret.length())));
            arr[i] = chr;
        }
        return arr;
    }
}
