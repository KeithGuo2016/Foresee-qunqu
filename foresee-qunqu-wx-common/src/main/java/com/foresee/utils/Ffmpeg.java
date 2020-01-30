package com.foresee.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ffmpeg {
	private String ffmpegEXE;

	public Ffmpeg(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}
	
	public Ffmpeg() {
		super();
		
	}
	/**
	 * 视频加工
	 * @param videoInputPath 需要加工的原视频
	 * @param mp3InputPath 需要的背景音乐
	 * @param seconds 视频加工长度
	 * @param videoOutputPath 加工以后的视频
	 * @throws IOException
	 */
	public void convertor(String videoInputPath, String mp3InputPath,
			double seconds,String videoOutputPath) throws IOException {
		//命令行：ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
		List<String> command = new ArrayList<String>();
		command.add(ffmpegEXE);
		
		command.add("-i");
		command.add(videoInputPath);
		
		command.add("-i");
		command.add(mp3InputPath);
		
		command.add("-t");
		command.add(String.valueOf(seconds));
		
		command.add("-y");
		command.add(videoOutputPath);
		
		
		for(String c:command) {
			System.out.print(c+" ");
		}
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process=builder.start();
		
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		String line = "";
		while ( (line = br.readLine()) != null ) {
		}
		
		if (br != null) {
			br.close();
		}
		if (inputStreamReader != null) {
			inputStreamReader.close();
		}
		if (errorStream != null) {
			errorStream.close();
		}
		
	}
	/**
	 * 根据视频获取封面图
	 * @param videoInputPath 视频地址
	 * @param coverOutputPath 封面图地址
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void getCover(String videoInputPath, String coverOutputPath) throws IOException, InterruptedException {
//		ffmpeg.exe -ss 00:00:01 -i spring.mp4 -vframes 1 bb.jpg
		List<String> command = new java.util.ArrayList<String>();
		command.add(ffmpegEXE);
		
		// 指定截取第1秒
		command.add("-ss");
		command.add("00:00:01");
				
		command.add("-y");
		command.add("-i");
		command.add(videoInputPath);
		
		command.add("-vframes");
		command.add("1");
		
		command.add(coverOutputPath);
		
		for (String c : command) {
			System.out.print(c + " ");
		}
		
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		
		String line = "";
		while ( (line = br.readLine()) != null ) {
		}
		
		if (br != null) {
			br.close();
		}
		if (inputStreamReader != null) {
			inputStreamReader.close();
		}
		if (errorStream != null) {
			errorStream.close();
		}
	}

	public String getFfmpegEXE() {
		return ffmpegEXE;
	}

	public void setFfmpegEXE(String ffmpegEXE) {
		this.ffmpegEXE = ffmpegEXE;
	}
	
	/*
	 * public static void main(String[] args) throws IOException { Ffmpeg ffmpeg =
	 * new Ffmpeg("C:\\ffmpeg\\bin\\ffmpeg.exe"); ffmpeg.convertor("C:\\苏州大裤衩.mp4",
	 * "C:\\music.mp3", 7.1, "C:\\这是通过java生产的视频.mp4"); }
	 */
}
