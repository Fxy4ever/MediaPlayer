手撸的视频播放器<br>
======
花了两天多的时间终于是做完了<br>
花了挺多的时间找资料的 <br>
发现网上的博客都不符合我自己的需求 于是就自己手撸了一个<br>
实现了一些功能 ～～后期会加上滑动手势监听～～<br>


使用方法<br>
-------
```
FMediaPlayer player = new FMediaPlayer()
                .setContext(this)
                .setSurfaceView(surfaceView)
                .setSeekBar(seekBar)
                .setProgressBar(progressBar)
                .setTextview(currentTime,totalTime)
                .setVideoPreView()
                .play();
                全屏的layout和activity拷着用就行
```
