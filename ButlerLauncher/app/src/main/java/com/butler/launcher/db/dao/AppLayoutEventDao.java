package com.butler.launcher.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.butler.launcher.db.event.AppLayoutEvent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "APP_LAYOUT_EVENT".
*/
public class AppLayoutEventDao extends AbstractDao<AppLayoutEvent, Long> {

    public static final String TABLENAME = "APP_LAYOUT_EVENT";

    /**
     * Properties of entity AppLayoutEvent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PackageName = new Property(1, String.class, "packageName", false, "PACKAGE_NAME");
        public final static Property AppName = new Property(2, String.class, "appName", false, "APP_NAME");
        public final static Property ClassName = new Property(3, String.class, "className", false, "CLASS_NAME");
        public final static Property VersionName = new Property(4, String.class, "versionName", false, "VERSION_NAME");
        public final static Property LaunchActivityName = new Property(5, String.class, "launchActivityName", false, "LAUNCH_ACTIVITY_NAME");
        public final static Property LayoutX = new Property(6, int.class, "layoutX", false, "LAYOUT_X");
        public final static Property IsShow = new Property(7, boolean.class, "isShow", false, "IS_SHOW");
        public final static Property Idd = new Property(8, int.class, "idd", false, "IDD");
    }


    public AppLayoutEventDao(DaoConfig config) {
        super(config);
    }
    
    public AppLayoutEventDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"APP_LAYOUT_EVENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PACKAGE_NAME\" TEXT," + // 1: packageName
                "\"APP_NAME\" TEXT," + // 2: appName
                "\"CLASS_NAME\" TEXT," + // 3: className
                "\"VERSION_NAME\" TEXT," + // 4: versionName
                "\"LAUNCH_ACTIVITY_NAME\" TEXT," + // 5: launchActivityName
                "\"LAYOUT_X\" INTEGER NOT NULL ," + // 6: layoutX
                "\"IS_SHOW\" INTEGER NOT NULL ," + // 7: isShow
                "\"IDD\" INTEGER NOT NULL );"); // 8: idd
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"APP_LAYOUT_EVENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AppLayoutEvent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String packageName = entity.getPackageName();
        if (packageName != null) {
            stmt.bindString(2, packageName);
        }
 
        String appName = entity.getAppName();
        if (appName != null) {
            stmt.bindString(3, appName);
        }
 
        String className = entity.getClassName();
        if (className != null) {
            stmt.bindString(4, className);
        }
 
        String versionName = entity.getVersionName();
        if (versionName != null) {
            stmt.bindString(5, versionName);
        }
 
        String launchActivityName = entity.getLaunchActivityName();
        if (launchActivityName != null) {
            stmt.bindString(6, launchActivityName);
        }
        stmt.bindLong(7, entity.getLayoutX());
        stmt.bindLong(8, entity.getIsShow() ? 1L: 0L);
        stmt.bindLong(9, entity.getIdd());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AppLayoutEvent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String packageName = entity.getPackageName();
        if (packageName != null) {
            stmt.bindString(2, packageName);
        }
 
        String appName = entity.getAppName();
        if (appName != null) {
            stmt.bindString(3, appName);
        }
 
        String className = entity.getClassName();
        if (className != null) {
            stmt.bindString(4, className);
        }
 
        String versionName = entity.getVersionName();
        if (versionName != null) {
            stmt.bindString(5, versionName);
        }
 
        String launchActivityName = entity.getLaunchActivityName();
        if (launchActivityName != null) {
            stmt.bindString(6, launchActivityName);
        }
        stmt.bindLong(7, entity.getLayoutX());
        stmt.bindLong(8, entity.getIsShow() ? 1L: 0L);
        stmt.bindLong(9, entity.getIdd());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AppLayoutEvent readEntity(Cursor cursor, int offset) {
        AppLayoutEvent entity = new AppLayoutEvent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // packageName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // appName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // className
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // versionName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // launchActivityName
            cursor.getInt(offset + 6), // layoutX
            cursor.getShort(offset + 7) != 0, // isShow
            cursor.getInt(offset + 8) // idd
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AppLayoutEvent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPackageName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAppName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setClassName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setVersionName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLaunchActivityName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLayoutX(cursor.getInt(offset + 6));
        entity.setIsShow(cursor.getShort(offset + 7) != 0);
        entity.setIdd(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AppLayoutEvent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AppLayoutEvent entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AppLayoutEvent entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
