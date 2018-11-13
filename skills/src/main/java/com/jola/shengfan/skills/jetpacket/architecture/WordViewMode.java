//package com.jola.shengfan.skills.jetpacket.architecture;
//
//import android.app.Application;
//import android.arch.lifecycle.AndroidViewModel;
//import android.arch.lifecycle.LiveData;
//import android.support.annotation.NonNull;
//
//import java.util.List;
//
///**
// * Created by lenovo on 2018/11/8.
// */
//
//public class WordViewMode extends AndroidViewModel {
//
//    private WordRepository wordRepository;
//
//    private LiveData<List<Word>> mAllWords;
//
//
//
//    public WordViewMode(@NonNull Application application) {
//        super(application);
//        WordRepository wordRepository = new WordRepository(application);
//        mAllWords = wordRepository.getAllWords();
//    }
//
//    public void inset(Word word){
//        wordRepository.insertWord(word);
//    }
//
//    public LiveData<List<Word>> getAllWords(){
//        return mAllWords;
//    }
//
//    public void delete(Word word){
//        wordRepository.updateWord(word);
//    }
//
//}
