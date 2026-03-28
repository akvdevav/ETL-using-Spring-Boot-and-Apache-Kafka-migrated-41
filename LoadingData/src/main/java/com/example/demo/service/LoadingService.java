package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.MathOperation;
import com.example.demo.repository.LoadingRepository;

@Service
public class LoadingService {

    private final LoadingRepository repo;

    public LoadingService(LoadingRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void loadDataToDB(String data) {

        String[] gettingEveryCharAsString = data.split(",");
        MathOperation dataToBePushed = new MathOperation(
                gettingEveryCharAsString[0],
                gettingEveryCharAsString[1],
                gettingEveryCharAsString[2],
                gettingEveryCharAsString[3]);
        repo.save(dataToBePushed);
    }
}