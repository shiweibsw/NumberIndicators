#NumberIndicator

##概述

一种数字形式的Viewpager指示器，可以根据需要自定义各个样式的指示器形式；

##截图
![](http://upload-images.jianshu.io/upload_images/1179815-9b48f1b8d829e387.gif?imageMogr2/auto-orient/strip)

##更新日志

####V0.0.1
* 完成基础版

##用法

###引入插件
>Gradle:

        compile 'com.knigit.davion.numberindicator:library:0.0.1'
>Maven:

        <dependency>
          <groupId>com.knigit.davion.numberindicator</groupId>
          <artifactId>library</artifactId>
          <version>0.0.1</version>
          <type>pom</type>
        </dependency>
    
###使用
####1.XML；
          <com.knigit.davion.numberindicator.NumberIndicator
                    android:id="@+id/indicator2"
                    android:layout_centerInParent="true"
                    android:layout_width="match_parent"
                    android:layout_height="112dp"
                    app:ni_height="40dip"
                    app:ni_textcolor="@color/colorAccent"
                    app:ni_textsize="36"
                    app:ni_width="40dip"/>
                    
####2.数字指示形式；
         mIndicator1.setViewPager(mViewPager1);
####3.图片指示形式；
      List<Integer> indicators = new ArrayList<>();
              indicators.add(R.drawable.index_1);
              indicators.add(R.drawable.index_2);
              indicators.add(R.drawable.index_3);
              indicators.add(R.drawable.index_4);
              indicators.add(R.drawable.index_5);
      mIndicator2.setIndicatorWithImage(indicators);
      mIndicator2.setViewPager(mViewPager2);
