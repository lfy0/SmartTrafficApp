package com.example.nappy.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.nappy.beans.AccountTable;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by liufangya on 2017/10/18.
 */

public class DataBaseHolder extends OrmLiteSqliteOpenHelper {


    private static final String TABLE_NAME = "sqlite-1.db";
    private Dao<AccountTable, Integer> userDao;
    private boolean isCreate=true;


    public DataBaseHolder(Context context) {
        super(context, TABLE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, AccountTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,AccountTable.class,true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static DataBaseHolder instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DataBaseHolder getHelper(Context context)
    {
        //context=context.getApplicationContext();
        if (instance == null)
        {
            synchronized (DataBaseHolder.class)
            {
                if (instance == null)
                    instance = new DataBaseHolder(context);
            }
        }
        return instance;
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<AccountTable, Integer> getUserDao() throws SQLException
    {
        if (userDao == null)
        {
            userDao = getDao(AccountTable.class);
        }
        return userDao;
    }


    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();
        userDao = null;
        isCreate=false;
    }

    public boolean isClose(){
        return isCreate;
    }


}
