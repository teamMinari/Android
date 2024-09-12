//package com.nohjason.minari.screens.profile;
//
//import android.os.Bundle;
//import android.os.StrictMode;
//
//public class ResourceTracking {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // StrictMode 설정 추가
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
//                .detectLeakedClosableObjects()
//                .penaltyLog()
//                .build());
//
//        // Jetpack Compose 콘텐츠 설정
//        setContent {
//            MyApp()
//        }
//    }
//}
