package com.example.wordwallet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, "worddb", null, DATABASE_VERSION);
    }

    @Override
    /**
     * 단어장리스트 db는 id와 이름, 구분자 day_my(0이면 일일단어 1이면 내 단어 2면 북마크됨)를 가진다
     * 둘 다 null이 되면 안 됨
     *
     * 단어 DB는 단어(word), 뜻(meaning), 이미지(image) 속성이 존재하고 소속된 단어장 리스트 번호를 가진다
     *
     * (영상(video)은 따로 저장해두고 불러와서 쓰기로 함, 한 shortcut에 여러 단어가 존재하기 때문)
     * 이 중 단어와 뜻은 비워질 수 없고, 이미지는 null로 비울 수 있다.
     *
     */
    public void onCreate(SQLiteDatabase db) {
        String listSQL = "create table wordlist (" + "_id integer primary key autoincrement, "
                        + "name not null, " + "day_my integer not null, " +
                        " constraint boolcheck check(day_my==0 or day_my==1))";

        String wordSQL = "create table word (" + "_id integer primary key autoincrement, "
                        + "word not null, " + "meaning not null, " + "imagelink, "
                        + "listnumber references wordlist(_id))";

        db.execSQL(listSQL);
        db.execSQL(wordSQL);

        //기본 일일 단어장
        db.execSQL("insert into wordlist (name, day_my) values (?, 0)", new String[] {"Day 1"});
        db.execSQL("insert into wordlist (name, day_my) values (?, 0)", new String[] {"Day 2"});
        db.execSQL("insert into wordlist (name, day_my) values (?, 0)", new String[] {"Day 3"});

        //일일 단어장에 단어 추가
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 1)", new String[] {"government", "정부"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 1)", new String[] {"partially", "부분적으로"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 1)", new String[] {"middle explanation", "중간 설명"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 1)", new String[] {"subject", "주제"});

        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 2)", new String[] {"shutdown", "임시 휴업"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 2)", new String[] {"congress", "회의"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 2)", new String[] {"president", "대통령"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 2)", new String[] {"big sticking point", "큰 걸림돌"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 2)", new String[] {"impasse", "난관"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 2)", new String[] {"proposed border wall", "제안된 국경 벽"});


        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 3)", new String[] {"representatives", "대표자"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 3)", new String[] {"legislation", "법률 제정"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 3)", new String[] {"deadline was up", "마감일이 다가왔다"});
        db.execSQL("insert into word (word, meaning, listnumber) values (?, ?, 3)", new String[] {"agreement", "합의"});

        //기본 제공 나만의 단어장 하나 추가
        db.execSQL("insert into wordlist (name, day_my) values (?, 1)", new String[] {"나만의 단어장"});



    }

    //아직 사용하지 말 것
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table word");
            db.execSQL("drop table wordlist");
            onCreate(db);
        }
    }
}
