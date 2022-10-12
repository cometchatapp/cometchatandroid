package com.example.arteria;

/*
* D/Surface: Surface::disconnect(this=0xbcba8000,api=1)
D/GraphicBuffer: unregister, handle(0xdd92ef40) (w:1080 h:1920 s:1088 f:0x1 u:b00)
D/GraphicBuffer: unregister, handle(0xdd92ebc0) (w:1080 h:1920 s:1088 f:0x1 u:b00)
D/GraphicBuffer: unregister, handle(0xdd92ed80) (w:1080 h:1920 s:1088 f:0x1 u:b00)
D/OpenGLRenderer: eglDestroySurface = 0xc35266a8, 0xbcba8000
D/ViewRootImpl@1e63f5c[CometChatMessageListActivity]: Relayout returned: old=[0,0][1080,1920] new=[0,0][1080,1920] result=0x5 surface={valid=false 0} changed=true
D/Recording stopp: OnStop
D/Recording stopp: inside the function
V/MediaPlayer-JNI: stop
V/MediaPlayerNative: stop
E/MediaPlayerNative: stop called in state 1, mPlayer(0x0)
V/MediaPlayerNative: message received msg=100, ext1=-38, ext2=0
E/MediaPlayerNative: error (-38, 0)
V/MediaPlayerNative: callback application
V/MediaPlayerNative: back from callback
E/MediaPlayer: Error (-38,0)
D/ViewRootImpl@1e63f5c[CometChatMessageListActivity]: Relayout returned: old=[0,0][1080,1920] new=[0,0][1080,1920] result=0x1 surface={valid=false 0} changed=false
V/FA: Inactivity, disconnecting from the service

* */

public class AppConstants {
//    public static final String APP_ID = "32172e2c4682320"; // Replace with your App ID
//    public static final String REGION = "us"; // Replace with your App Region ("eu" or "us")
//    public static final String AUTH_KEY = "34801ed87812512beb2bc7cee6e11d8497b35925"; //Replace with your Auth Key.

    public static final String APP_ID = "218195c2e1481094"; // Replace with your App ID
    public static final String REGION = "us"; // Replace with your App Region ("eu" or "us")
    public static final String AUTH_KEY = "b68c88f6a7f00179a47b264aeaa062b9a5cebc9a"; //Replace with your Auth Key.

    private static final String TAG = "MY_TESTING_LOGS";
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
}
