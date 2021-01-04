package FFmpeg;

import Entity.iOT_VideoInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MacOS_FFmpeg implements Runnable{

    private iOT_VideoInfo iOT_videoInfo;

    public MacOS_FFmpeg(iOT_VideoInfo iOT_videoInfo) {
        this.iOT_videoInfo = iOT_videoInfo;
    }

    @Override
    public void run() {
        String PROPERTIES_NAME = "/Users/mac/javaProject/lproLiveStreamFFmpegEngine/src/Resources/FFmpeg_config.properties";
        String ff_from = "";
        String ff_config = "";
        String target_file = "";
        try(FileInputStream in = new FileInputStream(PROPERTIES_NAME)){
            Properties properties = new Properties();
            properties.load(in);
            ff_from = properties.getProperty("ffmpeg_from");
            ff_config = properties.getProperty("ffmpeg_config");
            target_file = properties.getProperty("target_path");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!ff_from.equals("") && !ff_config.equals("") && !target_file.equals("")){
            String [] ffmpeg_command = {"/bin/sh","-c",ff_from+" "+iOT_videoInfo.getRtmp_url_common()+" "+ff_config+" "+target_file+iOT_videoInfo.getApp_name()+".m3u8"};
//            Runtime runtime = Runtime.getRuntime();
//            System.out.println(Arrays.toString(ffmpeg_command));
//            runtime.exec(ffmpeg_command);
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(ffmpeg_command);
            processBuilder.redirectErrorStream(true);
            try {
                processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("已完成，请检查文件");
        }
    }
}