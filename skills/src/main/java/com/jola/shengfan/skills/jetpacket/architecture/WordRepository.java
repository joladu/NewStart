package com.jola.shengfan.skills.jetpacket.architecture;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by lenovo on 2018/11/8.
 */

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application){
        WordRoomDatabase database = WordRoomDatabase.getInstance(application);
        wordDao = database.wordDao();
        mAllWords = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }


    public  void insertWord(Word word){
        new InsertAsyncTask(wordDao).execute(word);
    }

    public void deleteWord(Word word){
        wordDao.deleteWord(word);
    }

    public void updateWord(Word word){
        wordDao.updateWord(word);
    }



    private static class InsertAsyncTask extends AsyncTask<Word,Void,Void>{


        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWord(words[0]);
            return null;
        }
    }

}
