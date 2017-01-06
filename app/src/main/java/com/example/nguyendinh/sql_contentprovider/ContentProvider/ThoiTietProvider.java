package com.example.nguyendinh.sql_contentprovider.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.nguyendinh.sql_contentprovider.Data.DataHelper;

/**
 * Created by nguyendinh on 21/08/2016.
 */
public class ThoiTietProvider extends ContentProvider {
    SQLiteDatabase dulieuthoitiet;
    public static final String URI="content://vn.cusc.provider/thoitiet";
    public static final String PROVIDER ="vn.cusc.provider";
    // Uri dữ liêu
    private static final int TATCA = 1;
    private static final int MOT = 2;
    // qui định URI
    static final UriMatcher taoURI;
    static
    {
        taoURI = new UriMatcher(UriMatcher.NO_MATCH);
        taoURI.addURI(PROVIDER, "thoitiet", TATCA);
        taoURI.addURI(PROVIDER, "thoitiet/#", MOT);
    }


    @Override
    public boolean onCreate() {
        DataHelper db = new DataHelper(getContext());
        dulieuthoitiet = db.getWritableDatabase();
        return dulieuthoitiet !=null ? true :false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DataHelper.TEN_BANG);
        switch (taoURI.match(uri))
        {
            case TATCA:
                break;
            case MOT:
                String id = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(DataHelper.ID+"="+id);
                break;
            default:
                throw new IllegalArgumentException("Không hỗ trợ URI: " + uri);
        }
        Cursor cursor = queryBuilder.query(dulieuthoitiet,projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (taoURI.match(uri))
        {
            case TATCA:
                //tra ve danh sach
                return "vnd.android.cursor.dir/"+PROVIDER+"/thoitiet";
            case MOT:
                //tra ve 1 phan tu
                return "vnd.android.cursor.item/" + PROVIDER + "/thoitiet";
            default:
                throw new IllegalArgumentException("Không hỗ trợ URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if(taoURI.match(uri) == TATCA)
        {
            long id = dulieuthoitiet.insert(DataHelper.TEN_BANG,null,values);
            getContext().getContentResolver().notifyChange(uri,null);
            return Uri.parse(URI+"/"+id);
        }
        else
        {
            throw new IllegalArgumentException("Không hỗ trợ URI: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
       switch (taoURI.match(uri))
       {
           case TATCA:
               break;
           case MOT:
               String id = uri.getPathSegments().get(1);
               selection = DataHelper.ID+ "="+ id + (!TextUtils.isEmpty(selection) ?
                       " AND (" + selection + ')' : "");
               break;
           default:
               throw new IllegalArgumentException("khong ho tro URI:"+uri);
       }
        int deleteCount = dulieuthoitiet.delete(DataHelper.TEN_BANG,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (taoURI.match(uri)) {
            case TATCA:
                // do nothing
                break;
            case MOT:
                String id = uri.getPathSegments().get(1);
                selection = DataHelper.ID  + "=" + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : "");
                break;
            default:
                throw new IllegalArgumentException("Không hỗ trợ URI: " + uri);
        }
        int updateCount = dulieuthoitiet.update(DataHelper.TEN_BANG, values,
                selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return updateCount;
    }
}
