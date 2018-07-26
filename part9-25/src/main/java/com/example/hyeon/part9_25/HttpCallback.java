package com.example.hyeon.part9_25;
// HttpRequesr에서 얻은 결과 데이터를 받기위해 구현되는 인터페이스
public interface HttpCallback {
    void onResult(String result);
}