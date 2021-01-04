package Thread;

import Entity.iOT_VideoInfo;
import FFmpeg.MacOS_FFmpeg;
import JDBC.DataBase;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EngineStart {
    public static void main(String[] args) {
        try{
            ArrayList<iOT_VideoInfo> sourceArrays = new DataBase().sources();
            if(sourceArrays.size()>0){
                for(iOT_VideoInfo video : sourceArrays){
                    /**
                     * macOS & linux run
                     */
                    ExecutorService executorService = Executors.newCachedThreadPool();
                    executorService.execute(new MacOS_FFmpeg(video));
//                    RTSP.RTSP_MacOS_FFmpeg(video);
                    /**
                     * windows run
                     */
//                ExecutorService executorService = Executors.newCachedThreadPool();
//                executorService.execute(new Windows_FFmpeg(video));
//                    RTSP.RTSP_Windows_FFmpeg(video);
                }
            }
            else throw new RuntimeException("找不到任何摄像头资源，请检查数据库");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}